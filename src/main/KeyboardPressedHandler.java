package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import types.Vegetable;

public class KeyboardPressedHandler implements EventHandler<KeyEvent> {

  public void handle(KeyEvent key) {
    
    Vegetable protag = Main.getProtag();
    
    // Is run every time a key is pressed.
    
    if (key.getCode() == KeyCode.W) {
      protag.jump();
    }
    else if (key.getCode() == KeyCode.D) {
      protag.right = true;
    }
    else if (key.getCode() == KeyCode.A) {
      protag.left = true;
    }
    
  }

}
