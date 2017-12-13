package main;

import java.util.Timer;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import types.LevelParser;
import types.ResetBasicActive;
import types.Vegetable;

public class KeyboardPressedHandler implements EventHandler<KeyEvent> {

  public void handle(KeyEvent key) {
    
    if (Main.getState() == 0 && key.getCode() == KeyCode.ENTER) {
        Main.getIntroPlayer().dispose();
        Main.setState((byte) (Main.getState() + 1));
        return;
    }
    else if (Main.getState() == (byte) 1 || Main.getState() == (byte) 2) {
      
      if (key.getCode() == KeyCode.UP) {
        Main.setSelection(Main.getSelection() == (byte) 1 ? (byte) 3 : (byte) (Main.getSelection() - 1));
      }
      else if (key.getCode() == KeyCode.DOWN) {
        Main.setSelection(Main.getSelection() == (byte) 3 ? (byte) 1 : (byte) (Main.getSelection() + 1));
      }
      else if (key.getCode() == KeyCode.ENTER) {
        if (Main.getState() == (byte) 1) {
          Main.setState((byte) 2);
          return;
        }
        if (Main.getSelection() == (byte) 1) {
          Main.setState((byte) 3);
        }
        else if (Main.getSelection() == (byte) 2) {
          Main.setState((byte) 4);
        }
        else {
          System.exit(0);
        }
        Main.setSelection((byte) 1);
      }
      
    }
    else if (Main.getState() == (byte) 3) {
      
      return;
      
    }
    else if (Main.getState() == (byte) 4) {
      
      if (key.getCode() == KeyCode.RIGHT) {
        Main.setSelection((byte) (Main.getSelection() + 1));
      }
      else if (key.getCode() == KeyCode.LEFT) {
        Main.setSelection((byte) (Main.getSelection() - 1));
      }
      else if (key.getCode() == KeyCode.ENTER) {
        Main.setCurrentLevel(LevelParser.parseLevel(Main.getSelection()));
        Main.setState((byte) 5);
      }
      
    }
    else if (Main.getState() == (byte) 5) {
      Vegetable protag = Main.getProtag();
      
      if (key.getCode() == KeyCode.W) {
        if (protag.isBasicActive() && protag.getBasic().isPhysics()) {
          return;
        }
        protag.jump();
        protag.jumpReleased = false;
      }
      else if (key.getCode() == KeyCode.D) {
        protag.right = true;
        protag.xVel = protag.getSpeed();
        protag.lastDirection = 1;
      }
      else if (key.getCode() == KeyCode.A) {
        protag.left = true;
        protag.xVel = protag.getSpeed();
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
        timer.schedule(new ResetBasicActive(protag), protag.getBasic().getLength());
      }
    }
    
  }

}
