package main;

import java.util.ArrayList;

import characters.*;
import types.Character;
import types.Solid;
import types.Vegetable;

public class Constants {
  
  public static final Vegetable[] CHARACTERS = {new Cabbage()};
  public static final int[] SOLIDS = {1};
  
  public static final boolean charIsTouchingGround(Character character) {
    ArrayList<Solid> solids = Main.getSolids();
    boolean touched = false;
    for (Solid solid : solids) {
      if (character.y + character.h >= solid.y && character.y < solid.y + solid.h) {
        touched = true;
        break;
      }
    }
    return touched;
  }

}
