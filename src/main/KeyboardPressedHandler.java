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
	  
    //If the player has clicked the enter button the state will change by one to skip over the intro or select a profile/level/character
    if (Main.getState() == 0 && key.getCode() == KeyCode.ENTER) {
        Main.getIntroPlayer().dispose();
        Main.setState((byte) (Main.getState() + 1));
        return;
    }
    //This will check if any of the arrow keys are clicked during what state to change the selected profile/level/character/exit
    else if (Main.getState() == 1 || Main.getState() == 2) {
      
      if (key.getCode() == KeyCode.UP) {
        Main.setSelection(Main.getSelection() == 1 ? (byte) 3 : (byte) (Main.getSelection() - 1));
      }
      else if (key.getCode() == KeyCode.DOWN) {
        Main.setSelection(Main.getSelection() == 3 ? (byte) 1 : (byte) (Main.getSelection() + 1));
      }
      else if (key.getCode() == KeyCode.ENTER) {
        if (Main.getState() == 1) {
          Main.setState((byte) 2);
          Main.setSelection((byte) 1);
          return;
        }
        if (Main.getSelection() == 1) {
          Main.setState((byte) 3);
        }
        else if (Main.getSelection() == 2) {
          Main.setState((byte) 4);
        }
        else {
          System.exit(0);
        }
        Main.setSelection((byte) 1);
      }
    }
    else if (Main.getState() == 3) {
      
      if (key.getCode() == KeyCode.RIGHT) {
        byte selection = (byte) (Main.getSelection() + 1);
        if (selection == 0) {
          selection = (byte) (Constants.CHARACTERS.length);
        }
        else if (selection == Constants.CHARACTERS.length + 1) {
          selection = 1;
        }
        Main.setSelection(selection);
      }
      else if (key.getCode() == KeyCode.LEFT) {
        byte selection = (byte) (Main.getSelection() - 1);
        if (selection == 0) {
          selection = (byte) (Constants.CHARACTERS.length);
        }
        else if (selection == Constants.CHARACTERS.length + 1) {
          selection = 1;
        }
        Main.setSelection(selection);
      }
      else if (key.getCode() == KeyCode.ENTER) {
        Main.setProtag(Constants.CHARACTERS[Main.getSelection() - 1]);
        Main.setSelection((byte) 1);
        Main.setState((byte) 2);
      }
      
    }
    //This is used to choose and set the level
    else if (Main.getState() == 4) {
      
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
    //This is used to check for movement, shooting, and ability usage
    else if (Main.getState() == 5) {
      Vegetable protag = Main.getProtag();
      
      //If the W button is clicked on the keyboard and the character is not in ability mode, the jump method will be executed 
      if (key.getCode() == KeyCode.W) {
        if (protag.isBasicActive() && protag.getBasic().isPhysics()) {
          return;
        }
        protag.jump();
        protag.jumpReleased = false;
      }
      //If the D button is clicked on the keyboard, you are moving to the right and set the last direction to be 1 (Right).
      else if (key.getCode() == KeyCode.D) {
        protag.right = true;
        protag.xVel = protag.getSpeed();
        protag.lastDirection = 1;
      }
      //If the A button is clicked on the keyboard, you are moving to the Left and set the last direction to be 2 (Left).
      else if (key.getCode() == KeyCode.A) {
        protag.left = true;
        protag.xVel = protag.getSpeed();
        protag.lastDirection = 2;
      }
      //If the M button is pressed, the shootProjectile method will run
      else if (key.getCode() == KeyCode.M) {
        protag.shootProjectile();
      }
      //If the K button is pressed, 
      else if (key.getCode() == KeyCode.K) {
        //If you are using using the ability, or it's on cooldown, 
        if (protag.isBasicActive() || !protag.isBasicAllowed()) {
          //stops it from running any further
          return;
        }
        //This will infrom the code that you have used he ability and create a timer for cooldown
        protag.setBasicActive(true);
        protag.basic();
        Timer timer = new Timer();
        timer.schedule(new ResetBasicActive(protag), protag.getBasic().getLength());
      }
    }
    
  }

}
