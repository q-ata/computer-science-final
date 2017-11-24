package main;

import java.util.ArrayList;

import characters.*;
import types.Solid;
import types.Character;
import types.Vegetable;

public class Constants {
  
  public static final Vegetable[] CHARACTERS = {new Cabbage()};
  public static final int[] SOLIDS = {1};
  
  public static final void CHARACTERCOLLISION(Character character) {
    ArrayList<Solid> solids = Main.getSolids();
    for (Solid solid : solids) {
      if (character.right && solid.x <= character.x + character.w && solid.x + solid.w > character.x && solid.y < character.y + character.h && solid.y + solid.h > character.y) {
        int diff = (character.x + character.w) % solid.x;
        if (diff < 5) {
          character.xVel = diff;
        }
      }
      else if (character.left && solid.x + solid.w >= character.x && solid.x < character.x + character.w && solid.y < character.y + character.h && solid.y + solid.h > character.y) {
        int diff = (solid.x + solid.w) % character.x;
        if (diff < 5) {
          character.xVel = diff;
        }
      }
      if (character.up && character.y <= solid.y + solid.h && character.x + character.w > solid.x && character.x < solid.x + solid.w && character.y + character.h > solid.y + solid.h) {
        int diff = (solid.y + solid.h) % character.y;
        character.yVel = 0;
        character.y += diff + 1;
      }
    }
  }
  
  public static final boolean CHARACTERGRAVITY(Character character) {
    ArrayList<Solid> solids = Main.getSolids();
    boolean touchingGround = false;
    for (Solid solid : solids) {
      if (character.y + character.h + character.yVel >= solid.y &&
          // Checks top side collision.
          character.y + character.h < solid.y + solid.h &&
          // Checks right side collision.
          character.x + character.w > solid.x &&
          // Checks left side collision.
          character.x < solid.x + solid.w) {
        int diff = (solid.y) % (character.y + character.h);
        if (diff < character.yVel) {
          character.y += diff;
        }
        touchingGround = true;
      }
    }
    return touchingGround;
  }

}
