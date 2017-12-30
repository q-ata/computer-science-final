package main;

import java.util.ArrayList;

import types.BasicAbility;
import types.Enemy;
import types.MapItem;
import types.Projectile;
import types.Solid;
import types.Vegetable;

public class StateUpdate {
  
  public static synchronized void update() {
    
    Vegetable protag = Main.getProtag();
    
    // Check if the character has an in progress ability that affects physics. Do not enact character physics if so.
    boolean physicsOff = false;
    for (BasicAbility ability: protag.getAbilities()) {
      if (ability.isActive() && ability.isPhysics()) {
        ability.doBasic();
        physicsOff = true;
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
        Main.visibleX += increment;
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
      }
      else {
        // Reset jumps and velocity if the character is touching the ground.
        protag.yVel = 0;
        protag.jumps = 0;
        protag.up = false;
      }
    }
    
    // Set characters y position.
    protag.y += protag.yVel;
    Main.visibleY += protag.yVel;
    
    // Loop over and do physics for all projectiles.
    ArrayList<Integer> projectilesToRemove = new ArrayList<Integer>();
    for (int i = 0; i < Main.getCurrentLevel().getProjectiles().size(); i++) {
      Projectile proj = Main.getCurrentLevel().getProjectiles().get(i);
      // Increase projectile x position.
      proj.x += proj.getDirection() == 1 ? proj.getVelocity() : -proj.getVelocity();
      // Loop over all solids, and queue the projectile for deletion if it collides with any.
      boolean hitSolid = false;
      for (Solid solid : Main.getCurrentLevel().getSolids()) {
        if (Constants.SOLIDCOLLISION(proj, solid)) {
          projectilesToRemove.add(i);
          hitSolid = true;
          break;
        }
      }
      // If the projectile did not hit a solid, check for collision with all enemies.
      if (!hitSolid) {
        for (Enemy enemy : Main.getCurrentLevel().getEnemies()) {
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
      Main.getCurrentLevel().getMapItems().remove(Main.getCurrentLevel().getProjectiles().get(index - projCount));
      Main.getCurrentLevel().getProjectiles().remove(index - projCount);
      projCount++;
    }
    
    // Loop over and do physics for all spawned enemies.
    ArrayList<Integer> toRemove = new ArrayList<Integer>();
    for (int i = 0; i < Main.getCurrentLevel().getEnemies().size(); i++) {
      Enemy enemy = Main.getCurrentLevel().getEnemies().get(i);
      // If a new enemy appears on screen, spawn it and enable physics for it.
      if (!enemy.isSpawned()) {
        if ((enemy.x - protag.x < 500 + (protag.w / 2) && enemy.y - protag.y < 300 - (protag.h / 2)) || !enemy.isNeedsSpawn()) {
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
          for (Solid solid : Main.getCurrentLevel().getSolids()) {
            if (Constants.SOLIDCOLLISION(enemy, solid)) {
              Main.getCurrentLevel().getMapItems().remove(enemy);
              toRemove.add(i);
              delete = true;
              break;
            }
          }
        }
        // If the enemy's health drops to 0 or below, queue it for deletion.
        if (enemy.hp <= 0) {
          toRemove.add(i);
          Main.getCurrentLevel().getMapItems().remove(enemy);
        }
        // If the enemy should not already be deleted, check if it makes contact with the player.
        else if (!delete && Constants.SOLIDCOLLISION(protag, enemy)) {
          // If the enemy is solid, do horizontal collision detection (vertical collision will not be needed).
          if (enemy.isSolid()) {
            if (protag.xVel > 0) {
              protag.x = enemy.x - protag.w;
            }
            else if (protag.xVel < 0) {
              protag.x = enemy.x + enemy.w;
            }
            Main.visibleX = protag.x - 460;
          }
          // If the player is not invincible, take damage.
          if (!protag.isInvincible()) {
            // Take damage equal to the enemy's damage multiplied by the character's resistance.
            protag.hp -= enemy.getDmg() * protag.res;
            Constants.TAKECHARDAMAGE(protag, 1000);
          }
          // If the enemy should be deleted after making contact once, queue it for deletion.
          if (enemy.isOneTime()) {
            toRemove.add(i);
            Main.getCurrentLevel().getMapItems().remove(enemy);
          }
        }
      }
    }
    // Delete all enemoes queued for deletion.
    int count = 0;
    for (int index : toRemove) {
      Main.getCurrentLevel().getEnemies().remove(index - count);
      count++;
    }
    
    // Loop over all map items and change the coordinates at which they should appear on screen. This is relative to the player position.
    for (MapItem item : Main.getCurrentLevel().getMapItems()) {
      item.vx = item.x - Main.visibleX;
      item.vy = item.y - Main.visibleY;
    }
    
  }

}
