package main;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class KeyboardReleasedHandler implements EventHandler<KeyEvent> {

  public void handle(KeyEvent key) {
    
    // Is run every time a key is released.
    
    System.out.println("RELEASED: " + key.getCode());
    
  }

}
