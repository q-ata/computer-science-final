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
    for (int i = 0; i < Main.getProjectiles().size(); i++) {
      Projectile proj = Main.getProjectiles().get(i);
      proj.x += proj.getDirection() == 1 ? proj.getVelocity() : -proj.getVelocity();
      boolean hitSolid = false;
      for (Solid solid : Main.getSolids()) {
        if (Constants.SOLIDCOLLISION(proj, solid)) {
          Main.getProjectiles().remove(i);
          Main.getMapItems().remove(proj);
          hitSolid = true;
          break;
        }
      }
      if (!hitSolid) {
        for (Enemy enemy : Main.getEnemies()) {
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
      Main.getMapItems().remove(Main.getProjectiles().get(index - projCount));
      Main.getProjectiles().remove(index - projCount);
      projCount++;
    }
    
    ArrayList<Integer> toRemove = new ArrayList<Integer>();
    for (int i = 0; i < Main.getEnemies().size(); i++) {
      Enemy enemy = Main.getEnemies().get(i);
      if (!enemy.isSpawned()) {
        if (enemy.x - protag.x < 500 + (protag.w / 2) && enemy.y - protag.y < 300 - (protag.h / 2)) {
          enemy.setSpawned(true);
        }
      }
      else {
        enemy.enemyMovement();
        if (enemy.isOneTime()) {
          for (Solid solid : Main.getSolids()) {
            if (Constants.SOLIDCOLLISION(enemy, solid)) {
              Main.getMapItems().remove(enemy);
              Main.getEnemies().remove(enemy);
              break;
            }
          }
        }
        if (enemy.hp <= 0) {
          toRemove.add(i);
          Main.getMapItems().remove(enemy);
        }
        else if (Constants.SOLIDCOLLISION(protag, enemy)) {
          if (enemy.isSolid()) {
            if (protag.right) {
              protag.x = enemy.x - protag.w;
            }
            else if (protag.left) {
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
            Main.getMapItems().remove(enemy);
          }
        }
      }
    }
    int count = 0;
    for (int index : toRemove) {
      Main.getEnemies().remove(index - count);
      count++;
    }
    
    for (MapItem item : Main.getMapItems()) {
      item.vx = item.x - Main.visibleX;
      item.vy = item.y - Main.visibleY;
    }
    
  }

}
