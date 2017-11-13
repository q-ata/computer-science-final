package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import types.Vegetable;

public class KeyboardReleasedHandler implements EventHandler<KeyEvent> {

  public void handle(KeyEvent key) {
    
    Vegetable protag = Main.getProtag();
    
    // Is run every time a key is released.
    
    if (key.getCode() == KeyCode.W) {
      
    }
    else if (key.getCode() == KeyCode.D) {
      protag.right = false;
    }
    else if (key.getCode() == KeyCode.A) {
      protag.left = false;
    }
    
  }

}
