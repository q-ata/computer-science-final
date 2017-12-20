package main;

import java.util.ArrayList;

import types.Enemy;
import types.MapItem;
import types.Projectile;
import types.Solid;
import types.Vegetable;

public class StateUpdate {
  
  public static void update() {
    
    Vegetable protag = Main.getProtag();
    
    if (protag.isBasicActive() && protag.getBasic().isPhysics()) {
      protag.doBasic();
    }
    else {
      protag.xVel = protag.getSpeed() + (protag.up ? 1 : 0);
      Constants.VEGGIECOLLISION(protag);
      if (protag.right || protag.left) {
        int increment = protag.right ? protag.xVel : -protag.xVel;
        protag.x += increment;
        Main.visibleX += increment;
      }
      
      if (!Constants.VEGGIEGRAVITY(protag)) {
        if (protag.jumps == 0 && protag.yVel > 5) {
          protag.jumps = 1;
        }
        if (protag.yVel < 18) {
          protag.yVel += 1;
        }
      }
      else {
        protag.yVel = 0;
        protag.jumps = 0;
        protag.up = false;
      }
    }
    
    protag.y += protag.yVel;
    Main.visibleY += protag.yVel;
    
    ArrayList<Integer> projectilesToRemove = new ArrayList<Integer>();
    for (int i = 0; i < Main.getCurrentLevel().getProjectiles().size(); i++) {
      Projectile proj = Main.getCurrentLevel().getProjectiles().get(i);
      proj.x += proj.getDirection() == 1 ? proj.getVelocity() : -proj.getVelocity();
      boolean hitSolid = false;
      for (Solid solid : Main.getCurrentLevel().getSolids()) {
        if (Constants.SOLIDCOLLISION(proj, solid)) {
          Main.getCurrentLevel().getProjectiles().remove(i);
          Main.getCurrentLevel().getMapItems().remove(proj);
          hitSolid = true;
          break;
        }
      }
      if (!hitSolid) {
        for (Enemy enemy : Main.getCurrentLevel().getEnemies()) {
          if (Constants.SOLIDCOLLISION(proj, enemy)) {
            enemy.hp -= proj.dmg * enemy.endurance;
            projectilesToRemove.add(i);
            break;
          }
        }
      }
    }
    int projCount = 0;
    for (int index : projectilesToRemove) {
      Main.getCurrentLevel().getMapItems().remove(Main.getCurrentLevel().getProjectiles().get(index - projCount));
      Main.getCurrentLevel().getProjectiles().remove(index - projCount);
      projCount++;
    }
    
    ArrayList<Integer> toRemove = new ArrayList<Integer>();
    for (int i = 0; i < Main.getCurrentLevel().getEnemies().size(); i++) {
      Enemy enemy = Main.getCurrentLevel().getEnemies().get(i);
      if (!enemy.isSpawned()) {
        if ((enemy.x - protag.x < 500 + (protag.w / 2) && enemy.y - protag.y < 300 - (protag.h / 2)) || !enemy.isNeedsSpawn()) {
          enemy.setSpawned(true);
        }
      }
      else {
        enemy.enemyMovement();
        if (enemy.isOneTime()) {
          for (Solid solid : Main.getCurrentLevel().getSolids()) {
            if (Constants.SOLIDCOLLISION(enemy, solid)) {
              Main.getCurrentLevel().getMapItems().remove(enemy);
              Main.getCurrentLevel().getEnemies().remove(enemy);
              break;
            }
          }
        }
        if (enemy.hp <= 0) {
          toRemove.add(i);
          Main.getCurrentLevel().getMapItems().remove(enemy);
        }
        else if (Constants.SOLIDCOLLISION(protag, enemy)) {
          if (enemy.isSolid()) {
            if (protag.xVel > 0) {
              protag.x = enemy.x - protag.w;
            }
            else if (protag.xVel < 0) {
              protag.x = enemy.x + enemy.w;
            }
            Main.visibleX = protag.x - 460;
          }
          if (!protag.isInvincible()) {
            protag.hp -= enemy.getDmg() * protag.res;
            Constants.TAKECHARDAMAGE(protag, 1000);
          }
          if (enemy.isOneTime()) {
            toRemove.add(i);
            Main.getCurrentLevel().getMapItems().remove(enemy);
          }
        }
      }
    }
    int count = 0;
    for (int index : toRemove) {
      Main.getCurrentLevel().getEnemies().remove(index - count);
      count++;
    }
    
    for (MapItem item : Main.getCurrentLevel().getMapItems()) {
      item.vx = item.x - Main.visibleX;
      item.vy = item.y - Main.visibleY;
    }
    
  }

}
