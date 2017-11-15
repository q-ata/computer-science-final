package main;

import types.Vegetable;

public class StateUpdate {
  
  public static void update() {
    
    Vegetable protag = Main.getProtag();
    
    if (protag.right) {
      protag.x += protag.xVel;
    }
    else if (protag.left) {
      protag.x -= protag.xVel;
    }
    protag.y += protag.yVel;
    
    if (!Constants.CHARTOUCHINGGROUND(protag)) {
      protag.yVel += 1;
    }
    else {
      protag.yVel = 0;
      protag.jumps = 0;
    }
    
  }

}
