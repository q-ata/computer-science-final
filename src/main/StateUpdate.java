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
    
    protag.xVel = 5 + (protag.up ? 1 : 0);
    Constants.VEGGIECOLLISION(protag);
    if (protag.right || protag.left) {
      int increment = protag.right ? protag.xVel : -protag.xVel;
      protag.x += increment;
      Main.visibleX += increment;
    }
    
    if (!Constants.VEGGIEGRAVITY(protag)) {
      if (protag.yVel < 18) {
        protag.yVel += 1;
      }
    }
    else {
      protag.yVel = 0;
      protag.jumps = 0;
      protag.up = false;
    }
    
    protag.y += protag.yVel;
    Main.visibleY += protag.yVel;
    
    ArrayList<Integer> projectilesToRemove = new ArrayList<Integer>();
    for (int i = 0; i < Main.getProjectiles().size(); i++) {
      Projectile proj = Main.getProjectiles().get(i);
      proj.x += proj.getDirection() == 1 ? proj.getVelocity() : -proj.getVelocity();
      for (Solid solid : Main.getSolids()) {
        if (Constants.SOLIDCOLLISION(proj, solid)) {
          Main.getProjectiles().remove(i);
          Main.getMapItems().remove(proj);
        }
      }
      for (Enemy enemy : Main.getEnemies()) {
        if (Constants.SOLIDCOLLISION(proj, enemy)) {
          enemy.hp -= proj.dmg * enemy.endurance;
          projectilesToRemove.add(i);
        }
      }
    }
    for (int index : projectilesToRemove) {
      Main.getMapItems().remove(Main.getProjectiles().get(index));
      Main.getProjectiles().remove(index);
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
        if (enemy.hp <= 0) {
          toRemove.add(i);
          Main.getMapItems().remove(enemy);
        }
        else if (Constants.SOLIDCOLLISION(protag, enemy) && !protag.isInvincible()) {
          protag.hp -= enemy.getDmg() * protag.res;
          Constants.TAKECHARDAMAGE(protag, 1000);
        }
      }
    }
    for (int index : toRemove) {
      Main.getEnemies().remove(index);
    }
    
    for (MapItem item : Main.getMapItems()) {
      item.vx = item.x - Main.visibleX;
      item.vy = item.y - Main.visibleY;
    }
    
  }

}
