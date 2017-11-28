package main;

import java.util.ArrayList;
import java.util.Timer;

import characters.*;
import types.ReverseInvincibility;
import types.Solid;
import types.Vegetable;

public class Constants {
  
  public static final Vegetable[] CHARACTERS = {new Cabbage()};
  public static final int[] SOLIDS = {1};
  
  public static final void VEGGIECOLLISION(Vegetable character) {
    ArrayList<Solid> solids = Main.getSolids();
    for (Solid solid : solids) {
      if (character.up && character.y <= solid.y + solid.h && character.x + character.w > solid.x && character.x < solid.x + solid.w && character.y + character.h > solid.y + solid.h) {
        character.yVel = 0;
        character.y = solid.y + solid.h;
        Main.visibleY = character.y - 260;
      }
      if (character.right && solid.x <= character.x + character.w + character.xVel && solid.x + solid.w > character.x && solid.y < character.y + character.h && solid.y + solid.h > character.y) {
        character.xVel = 0;
        character.x = solid.x - character.w;
        Main.visibleX = character.x - 460;
      }
      else if (character.left && solid.x + solid.w >= character.x - character.xVel && solid.x < character.x + character.w && solid.y < character.y + character.h && solid.y + solid.h > character.y) {
        character.xVel = 0;
        character.x = solid.x + solid.h;
        Main.visibleX = character.x - 460;
      }
    }
  }
  
  public static final boolean VEGGIEGRAVITY(Vegetable character) {
    ArrayList<Solid> solids = Main.getSolids();
    boolean touchingGround = false;
    for (Solid solid : solids) {
      if (character.y + character.h + character.yVel >= solid.y &&
          // Checks top side collision.
          character.y + character.h <= solid.y &&
          // Checks right side collision.
          character.x + character.w > solid.x &&
          // Checks left side collision.
          character.x < solid.x + solid.w) {
        character.y = solid.y - character.h;
        touchingGround = true;
        Main.visibleY = character.y - 260;
      }
    }
    return touchingGround;
  }
  
  public static final boolean SOLIDCOLLISION(Solid solid, Solid toCollide) {
    boolean collision = false;
      if (solid.x + solid.w > toCollide.x &&
          solid.x < toCollide.x + toCollide.w &&
          solid.y + solid.h > toCollide.y &&
          solid.y < toCollide.y + toCollide.h) {
        collision = true;
      }
    return collision;
  }
  
  public static final void TAKECHARDAMAGE(Vegetable protag, int time) {
    
    if (protag.hp <= 0) {
      Main.setState((byte) 1);
    }
    
    protag.setInvincible(true);
    protag.sprite = protag.getHurtSprite();
    Timer timer = new Timer();
    timer.schedule(new ReverseInvincibility(protag), time);
    
  }
  
}
