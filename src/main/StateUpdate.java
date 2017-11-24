package main;

import types.Vegetable;

public class StateUpdate {
  
  public static void update() {
    
    Vegetable protag = Main.getProtag();
    
    protag.xVel = 5;
    Constants.CHARACTERCOLLISION(protag);
    if (protag.right || protag.left) {
      protag.x += protag.right ? protag.xVel : -protag.xVel;
    }
    
    if (!Constants.CHARACTERGRAVITY(protag)) {
      protag.yVel += 1;
    }
    else {
      protag.yVel = 0;
      protag.jumps = 0;
    }
    
    protag.y += protag.yVel;
    
  }

}
