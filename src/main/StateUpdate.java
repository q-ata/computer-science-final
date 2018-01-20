package main;

import java.util.ArrayList;

import types.BasicAbility;
import types.Enemy;
import types.MapItem;
import types.Projectile;
import types.Solid;
import types.Vegetable;

public class StateUpdate {
  
  private static final Game GAME = Main.getGame();
  
  public static synchronized void update() {
    
    Vegetable protag = StateUpdate.GAME.getProtag();
    
    // Check if the character has an in progress ability that affects physics. Do not enact character physics if so.
    boolean physicsOff = false;
    for (BasicAbility ability: protag.getAbilities()) {
      if (ability.isActive()) {
        ability.doBasic();
        if (ability.isPhysics()) {
          physicsOff = true;
        }
      }
    }
    
    if (!physicsOff) {
      
      // Give slight increase to movement while in the air.
      protag.xVel = protag.getSpeed() + (protag.up ? 1 : 0);
      
      // Check top, left, and right side collision.
      Constants.VEGGIECOLLISION(protag);
      
      if (protag.right || protag.left) {
        // If the character is moving sideways, increase x position.
        int increment = protag.right ? protag.xVel : -protag.xVel;
        protag.x += increment;
        StateUpdate.GAME.visibleX += increment;
      }
      
      // Check if the character is touching the ground.
      if (!Constants.VEGGIEGRAVITY(protag)) {
        // If the character is falling, and has not jumped yet, disable double jump.
        if (protag.jumps == 0 && protag.yVel > 5) {
          protag.jumps = 1;
        }
        // Increase y velocity (fall speed). Capped at 18 pixels/frame.
        if (protag.yVel < 18) {
          protag.yVel += 1;
        }
        if (++protag.fallTime == 200) {
          StateUpdate.GAME.setState((byte) 7);
          protag.fallTime = 0;
        }
      }
      else {
        // Reset jumps and velocity if the character is touching the ground.
        protag.yVel = 0;
        protag.jumps = 0;
        protag.up = false;
        protag.fallTime = 0;
      }
    }
    
    // Set characters y position.
    protag.y += protag.yVel;
    StateUpdate.GAME.visibleY += protag.yVel;
    
    // Loop over and do physics for all projectiles.
    ArrayList<Integer> projectilesToRemove = new ArrayList<Integer>();
    for (int i = 0; i < StateUpdate.GAME.getCurrentLevel().getProjectiles().size(); i++) {
      Projectile proj = StateUpdate.GAME.getCurrentLevel().getProjectiles().get(i);
      // Increase projectile x position.
      proj.x += proj.getDirection() == 1 ? proj.getVelocity() : -proj.getVelocity();
      // Loop over all solids, and queue the projectile for deletion if it collides with any.
      boolean hitSolid = false;
      for (Solid block : StateUpdate.GAME.getCurrentLevel().getBlocks()) {
        if (Constants.SOLIDCOLLISION(proj, block)) {
          projectilesToRemove.add(i);
          hitSolid = true;
          break;
        }
      }
      // If the projectile did not hit a solid, check for collision with all enemies.
      if (!hitSolid) {
        for (Enemy enemy : StateUpdate.GAME.getCurrentLevel().getEnemies()) {
          if (Constants.SOLIDCOLLISION(proj, enemy)) {
            // Damage the enemy if it hit, then prepare to remove the projectile.
            int amount = (int) (proj.dmg * enemy.endurance);
            enemy.hp -= amount;
            // Increase the character's health depending on its lifesteal amount.
            int lifesteal = (int) Math.ceil((amount * protag.lifesteal) + (enemy.hp < 0 ? enemy.hp : 0));
            protag.hp += lifesteal < 0 ? 0 : lifesteal;
            // Cap the character's health at 100.
            if (protag.hp > 100) {
              protag.hp = 100;
            }
            projectilesToRemove.add(i);
            break;
          }
        }
      }
    }
    // Remove all projectiles that made contact with something. Run after the projectiles have all been processed to avoid ConcurrentModificationException.
    int projCount = 0;
    for (int index : projectilesToRemove) {
      StateUpdate.GAME.getCurrentLevel().getMapItems().remove(StateUpdate.GAME.getCurrentLevel().getProjectiles().get(index - projCount));
      StateUpdate.GAME.getCurrentLevel().getProjectiles().remove(index - projCount);
      projCount++;
    }
    
    // Loop over and do physics for all spawned enemies.
    ArrayList<Integer> toRemove = new ArrayList<Integer>();
    for (int i = 0; i < StateUpdate.GAME.getCurrentLevel().getEnemies().size(); i++) {
      Enemy enemy = StateUpdate.GAME.getCurrentLevel().getEnemies().get(i);
      // If a new enemy appears on screen, spawn it and enable physics for it.
      if (!enemy.isSpawned()) {
        if (enemy.x + enemy.w > StateUpdate.GAME.visibleX && enemy.x < StateUpdate.GAME.visibleX + 1000 && enemy.y + enemy.h > StateUpdate.GAME.visibleY && enemy.y < StateUpdate.GAME.visibleY + 600 || !enemy.isNeedsSpawn()) {
          enemy.setSpawned(true);
        }
      }
      else {
        // Do movement for the enemy.
        enemy.enemyMovement();
        // Check if the enemy should be deleted after making contact with a solid and if the enemy is still alive.
        boolean delete = false;
        if (enemy.isOneTime() && enemy.hp > 0) {
          // Loop over all solids, if the enemy collides with any then queue it for deletion.
          for (Solid block : StateUpdate.GAME.getCurrentLevel().getBlocks()) {
            if (Constants.SOLIDCOLLISION(enemy, block)) {
              StateUpdate.GAME.getCurrentLevel().getMapItems().remove(enemy);
              toRemove.add(i);
              delete = true;
              break;
            }
          }
        }
        // If the enemy's health drops to 0 or below, queue it for deletion.
        if (enemy.hp <= 0) {
          StateUpdate.GAME.getCurrentLevel().score += enemy.getPoints();
          toRemove.add(i);
          StateUpdate.GAME.getCurrentLevel().getMapItems().remove(enemy);
        }
        // If the enemy should not already be deleted, check if it makes contact with the player.
        else if (!delete && Constants.SOLIDCOLLISION(protag, enemy)) {
          // If the enemy is solid, do horizontal collision detection (vertical collision will not be needed).
          if (enemy.isSolid()) {
            if (protag.right) {
              protag.x = enemy.x - protag.w;
            }
            else if (protag.left) {
              protag.x = enemy.x + enemy.w;
            }
            StateUpdate.GAME.visibleX = protag.x + (protag.w / 2) - 500;
          }
          // If the player is not invincible, take damage.
          if (!protag.isInvincible()) {
            // Take damage equal to the enemy's damage multiplied by the character's resistance.
            protag.hp -= enemy.getDmg() * protag.res;
            if (Constants.TAKECHARDAMAGE(protag, 800)) {
              StateUpdate.GAME.getCurrentLevel().end(false);
              return;
            }
          }
          // If the enemy should be deleted after making contact once, queue it for deletion.
          if (enemy.isOneTime()) {
            toRemove.add(i);
            StateUpdate.GAME.getCurrentLevel().getMapItems().remove(enemy);
          }
        }
      }
    }
    // Delete all enemies queued for deletion.
    int count = 0;
    for (int index : toRemove) {
      StateUpdate.GAME.getCurrentLevel().getEnemies().remove(index - count);
      count++;
    }
    
    // Process all abilities.
    BasicAbility[] abilities = protag.getAbilities();
    
    for (BasicAbility ability : abilities) {
      // If there is an active ability, increment the counter that specifies how long it has been active for.
      if (ability.isActive()) {
        ability.setCurLength(ability.getCurLength() + 1);
        // End the ability if the specified length has been reached.
        if (ability.getCurLength() == ability.getLength()) {
          ability.setActive(false);
          ability.setCurLength(0);
          ability.basicEnd();
        }
        continue;
      }
      // If the ability is on cooldown, decrease the cooldown until it becomes available again.
      if ((ability.isAllowed() && !ability.isStacked()) || (ability.isStacked() && ability.getCurStacks() == ability.getMaxStacks())) {
        continue;
      }
      ability.setCurCooldown(ability.getCurCooldown() + 1);
      // Enable the ability if it has been on cooldown long enough.
      if (ability.getCurCooldown() == ability.getCooldown()) {
        ability.setAllowed(true);
        ability.setCurCooldown(0);
        if (ability.isStacked()) {
          ability.setCurStacks(ability.getCurStacks() + 1);
        }
      }
    }
    
    // Check if the level has been completed.
    if (Constants.SOLIDCOLLISION(protag, StateUpdate.GAME.getCurrentLevel().getFinish())) {
      // If it has been, apply a score bonus.
      int timeBonus = StateUpdate.GAME.getCurrentLevel().getTimeBonus() - StateUpdate.GAME.getCurrentLevel().time;
      StateUpdate.GAME.getCurrentLevel().score += timeBonus < 0 ? 0 : timeBonus;
      StateUpdate.GAME.getCurrentLevel().score += protag.hp;
      StateUpdate.GAME.getCurrentLevel().end(true);
    }
    
    // Loop over all map items and change the coordinates at which they should appear on screen. This is relative to the player position.
    for (MapItem item : StateUpdate.GAME.getCurrentLevel().getMapItems()) {
      item.vx = item.x - StateUpdate.GAME.visibleX;
      item.vy = item.y - StateUpdate.GAME.visibleY;
    }
    
  }

}
