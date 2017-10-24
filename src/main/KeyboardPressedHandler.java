package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardPressedHandler implements EventHandler<KeyEvent> {

  public void handle(KeyEvent key) {
    
    // Is run every time a key is pressed.
    
    System.out.println("PRESSED: " + key.getCode());
    
  }

}
