package main;

import java.util.ArrayList;
import java.util.Timer;

import characters.*;
import types.ReverseInvincibility;
import types.Solid;
import types.Vegetable;

public class Constants {
  
  public static final Vegetable[] CHARACTERS = {new Cabbage()};
  public static final int[] SOLIDS = {1, 7};
  
  public static final void VEGGIECOLLISION(Vegetable character) {
    ArrayList<Solid> solids = Main.getCurrentLevel().getSolids();
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
    ArrayList<Solid> solids = Main.getCurrentLevel().getSolids();
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
      System.out.println("DEAD.");
    }
    
    protag.setInvincible(true);
    protag.sprite = protag.getHurtSprite();
    Timer timer = new Timer();
    timer.schedule(new ReverseInvincibility(protag), time);
    protag.setInvincibilityTimer(timer);
    
  }
  
}

/*
 * 1|0|550
1|50|550
1|100|550
1|150|550
1|200|550
1|250|550
1|300|550
1|350|550
1|400|550
1|450|550
1|500|550
1|550|550
1|600|550
1|650|550
1|700|550
1|750|550
1|800|550
1|850|550
1|900|550
1|950|550
1|300|500
1|450|300
1|550|200
3|600|20
1|650|-50
1|450|-150
1|50|-300
1|-200|-550
1|0|-700
4|200|-1125
4|300|-1060
4|400|-880
3|320|-580
3|250|-670
1|400|-600
1|450|-600
1|500|-600
1|550|-600
1|600|-600
1|650|-600
1|700|-600
1|750|-600
1|800|-600
1|850|-600
1|900|-600
1|950|-600
1|1000|-650
2|1500|-688
1|1050|-600
1|1100|-600
1|1150|-600
1|1200|-600
1|1250|-600
1|1300|-600
1|1350|-600
1|1400|-600
1|1450|-600
1|1500|-600
1|1550|-600
1|1600|-650
1|1650|-600
1|1700|-600
1|1900|-600
1|2100|-600
1|2300|-600
1|2500|-600
1|3000|-600
1|3300|-600
1|3800|-600
2|2900|-688
1|3850|-600
1|3900|-600
1|3950|-600
5|3950|-1188|47|69
1|4000|-600
1|4050|-600
1|4100|-600
1|4150|-600
1|4200|-600
1|4250|-600
1|4300|-600
1|4350|-600
1|4400|-600
1|4450|-600
1|4500|-600
1|4550|-600
1|4600|-600
1|4650|-600
1|4700|-600
6|4800|-900|1|80
1|4750|-650
1|4800|-650
2|5450|-710
1|4850|-600
1|4900|-600
1|4950|-600
1|5000|-600
3|5080|-680
1|5150|-600
1|5200|-600
1|5250|-600
1|5300|-600
1|5350|-600
1|5400|-600
6|5400|-900|1|100
6|5600|-960|1|60
6|5000|-970|0|80
1|5450|-600
1|5500|-600
1|5550|-600
1|5600|-600
1|5650|-600
1|5700|-600
1|5750|-600
1|5800|-600
1|5850|-600
1|5900|-600
1|5850|-100
1|5900|-100
1|5950|-100
1|6000|-100
1|6050|-100
1|6300|-100
1|6350|-100
6|6710|-180|1|2|5|0
6|6710|-400|1|2|5|3
6|5700|-500|0|2
1|6700|-250
1|6750|-250
1|6800|-250
2|7200|-338|85
1|7100|-450
1|7150|-450
1|7200|-450
6|6700|-600|0|2
6|7500|-800|1|2
2|7550|-533|60
0|5800|-1100
*/
