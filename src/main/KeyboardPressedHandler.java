package main;

import java.util.Timer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import types.ResetBasicActive;
import types.Vegetable;

public class KeyboardPressedHandler implements EventHandler<KeyEvent> {

  public void handle(KeyEvent key) {
    
    if (Main.getState() == 0 && key.getCode() == KeyCode.ENTER) {
        Main.getIntroPlayer().dispose();
        return;
    }
    
    Vegetable protag = Main.getProtag();
    
    // Is run every time a key is pressed.
    
    if (key.getCode() == KeyCode.W) {
      if (protag.isBasicActive() && protag.isBasicPhysics()) {
        return;
      }
      protag.jump();
      protag.jumpReleased = false;
    }
    else if (key.getCode() == KeyCode.D) {
      protag.right = true;
      protag.xVel = 5;
      protag.lastDirection = 1;
    }
    else if (key.getCode() == KeyCode.A) {
      protag.left = true;
      protag.xVel = 5;
      protag.lastDirection = 2;
    }
    else if (key.getCode() == KeyCode.M) {
      protag.shootProjectile();
    }
    else if (key.getCode() == KeyCode.K) {
      if (protag.isBasicActive() || !protag.isBasicAllowed()) {
        return;
      }
      protag.setBasicActive(true);
      protag.basic();
      Timer timer = new Timer();
      timer.schedule(new ResetBasicActive(protag), protag.getBasicLength());
    }
    
  }

}
