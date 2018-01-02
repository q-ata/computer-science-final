package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import types.Vegetable;

public class KeyboardReleasedHandler implements EventHandler<KeyEvent> {

  public void handle(KeyEvent key) {
    
    if (Main.getGame().getState() != 5) {
      return;
    }
    
    Vegetable protag = Main.getGame().getProtag();
    
    // Is run every time a key is released.
    
    if (key.getCode() == KeyCode.W) {
      protag.jumpReleased = true;
    }
    else if (key.getCode() == KeyCode.D) {
      protag.right = false;
    }
    else if (key.getCode() == KeyCode.A) {
      protag.left = false;
    }
    
  }

}
