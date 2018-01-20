package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;

import characters.*;
import javafx.scene.input.KeyCode;
import types.Block;
import types.ReverseInvincibility;
import types.Solid;
import types.Vegetable;

public class Constants {
  
  private static final Game GAME = Main.getGame();
  
  // Array of all playable characters.
  public static final Vegetable[] CHARACTERS = {new Cabbage(), new Carrot(), new Broccoli()};
  
  // Array of all solid objects we want to check collision with.
  public static final int[] BLOCKS = {1, 7};
  
  // A map of KeyCodes and their string counterparts.
  public static HashMap<KeyCode, String> keyCodeMap;
  
  // Initialize the KeyCode map.
  public static final void initKeyCodeMap() {
    
    HashMap<KeyCode, String> map = new HashMap<KeyCode, String>();
    map.put(KeyCode.K, "K");
    map.put(KeyCode.L, "L");
    map.put(KeyCode.J, "J");
    map.put(KeyCode.SLASH, "/");
    Constants.keyCodeMap = map;
    
  }
  
  public static final int[] LEVELTRACKS = {1, 2, 3, 4};
  
  // Checks for character collision with all solids.
  public static final void VEGGIECOLLISION(Vegetable character) {
    ArrayList<Block> blocks = Constants.GAME.getCurrentLevel().getBlocks();
    for (Block block : blocks) {
      boolean collision = true;
      // If the character collides vertically, set the character's velocity and y position.
      if (character.up && character.y <= block.y + block.h && character.x + character.w > block.x && character.x < block.x + block.w && character.y + character.h > block.y + block.h) {
        character.yVel = 0;
        character.y = block.y + block.h;
        Constants.GAME.visibleY = character.y - 300 + (character.h / 2);
      }
      // If the character collides with a solid to the right.
      if (character.right && block.x <= character.x + character.w + character.xVel && block.x + block.w > character.x && block.y < character.y + character.h && block.y + block.h > character.y) {
        // Set the character's x velocity and x position.
        character.xVel = 0;
        character.x = block.x - character.w;
        Constants.GAME.visibleX = character.x - 500 + (character.w / 2);
      }
      // If the character collides with a solid to the left.
      else if (character.left && block.x + block.w >= character.x - character.xVel && block.x < character.x + character.w && block.y < character.y + character.h && block.y + block.h > character.y) {
        character.xVel = 0;
        character.x = block.x + block.h;
        Constants.GAME.visibleX = character.x - 500 + (character.w / 2);
      }
      else {
        collision = false;
      }
      // If a collision occurred, run the block's collision properties.
      if (collision) {
        block.collisionProperties();
      }
    }
  }
  
  // Handles character gravity.
  public static final boolean VEGGIEGRAVITY(Vegetable character) {
    ArrayList<Block> blocks = Constants.GAME.getCurrentLevel().getBlocks();
    boolean touchingGround = false;
    // If the character is still colliding with a solid, the character is touching the ground.
    for (Block block : blocks) {
      if (character.y + character.h + character.yVel >= block.y &&
          // Checks top side collision.
          character.y + character.h <= block.y &&
          // Checks right side collision.
          character.x + character.w > block.x &&
          // Checks left side collision.
          character.x < block.x + block.w) {
        character.y = block.y - character.h;
        touchingGround = true;
        Constants.GAME.visibleY = character.y - 300 + (character.h / 2);
        block.collisionProperties();
      }
    }
    return touchingGround;
  }
  
  // Checks for collision between any two solids.
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
  
  // Called when the character takes damage.
  public static final boolean TAKECHARDAMAGE(Vegetable protag, int time) {
    
    if (protag.hp <= 0) {
      return true;
    }
    
    // Makes the character invincible for a brief period of time.
    protag.setInvincible(true);
    protag.hurt = true;
    Timer timer = new Timer();
    timer.schedule(new ReverseInvincibility(protag), time);
    protag.setInvincibilityTimer(timer);
    return false;
  }
  
}


