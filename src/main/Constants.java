package main;

import java.util.ArrayList;

import characters.*;
import types.Solid;
import types.Vegetable;

public class Constants {
  
  public static final Vegetable[] CHARACTERS = {new Cabbage()};
  public static final int[] SOLIDS = {1};
  
  public static final boolean CHARTOUCHINGGROUND(Vegetable character) {
    ArrayList<Solid> solids = Main.getSolids();
    boolean touched = false;
    for (Solid solid : solids) {
        // Checks bottom side collision.
        if (character.y + character.h >= solid.y &&
            // Checks top side collision.
            character.y <= solid.y + solid.h &&
            // Checks right side collision.
            character.x + character.w >= solid.x &&
            // Checks left side collision.
            character.x <= solid.x + solid.w) {
          int diff = solid.y != 0 ? (character.y + character.h) % solid.y : 0;
          character.y -= diff;
          touched = true;
          break;
      }
    }
    return touched;
  }

}
