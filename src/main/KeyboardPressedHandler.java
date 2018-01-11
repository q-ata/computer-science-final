package main;

import java.io.File;
import java.io.PrintWriter;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import types.BasicAbility;
import types.InformationBar;
import types.LevelParser;
import types.ProfileLoader;
import types.Vegetable;

public class KeyboardPressedHandler implements EventHandler<KeyEvent> {
  
  private final Game GAME = Main.getGame();
  
  public void handle(KeyEvent key) {
	  
    //If the player has clicked the enter button the state will change by one to skip over the intro or select a profile/level/character
    if (this.GAME.getState() == 0 && key.getCode() == KeyCode.ENTER) {
      this.GAME.getIntroPlayer().dispose();
      this.GAME.setState((byte) (this.GAME.getState() + 1));
      return;
    }
    //This will check if any of the arrow keys are clicked during what state to change the selected profile/level/character/exit
    else if (this.GAME.getState() == 1) {
      if (key.getCode() == KeyCode.UP) {
        this.GAME.setSelection(this.GAME.getSelection() == 1 ? (byte) 3 : (byte) (this.GAME.getSelection() - 1));
      }
      else if (key.getCode() == KeyCode.DOWN) {
        this.GAME.setSelection(this.GAME.getSelection() == 3 ? (byte) 1 : (byte) (this.GAME.getSelection() + 1));
      }
      else if (key.getCode() == KeyCode.ENTER) {
        this.GAME.setState((byte) 2);
        ProfileLoader.loadProfile(this.GAME.getSelection());
        this.GAME.setSelection((byte) 1);
        this.GAME.getGc().setFill(Color.WHITE);
      }
    }
    else if (this.GAME.getState() == 2) {
      if (key.getCode() == KeyCode.UP) {
        this.GAME.setSelection(this.GAME.getSelection() == 1 ? (byte) 4 : (byte) (this.GAME.getSelection() - 1));
      }
      else if (key.getCode() == KeyCode.DOWN) {
        this.GAME.setSelection(this.GAME.getSelection() == 4 ? (byte) 1 : (byte) (this.GAME.getSelection() + 1));
      }
      else if (key.getCode() == KeyCode.ENTER) {
        if (this.GAME.getSelection() == 1) {
          this.GAME.setState((byte) 3);
        }
        else if (this.GAME.getSelection() == 2) {
          this.GAME.setState((byte) 4);
        }
        else if (this.GAME.getSelection() == 3) {
          this.GAME.setState((byte) 8);
        }
        else {
          System.exit(0);
        }
        this.GAME.setSelection((byte) 1);
      }
    }
    else if (this.GAME.getState() == 3) {
      
      if (key.getCode() == KeyCode.RIGHT) {
        byte selection = (byte) (this.GAME.getSelection() + 1);
        if (selection == 0) {
          selection = (byte) (Constants.CHARACTERS.length);
        }
        else if (selection == Constants.CHARACTERS.length + 1) {
          selection = 1;
        }
        this.GAME.setSelection(selection);
      }
      else if (key.getCode() == KeyCode.LEFT) {
        byte selection = (byte) (this.GAME.getSelection() - 1);
        if (selection == 0) {
          selection = (byte) (Constants.CHARACTERS.length);
        }
        else if (selection == Constants.CHARACTERS.length + 1) {
          selection = 1;
        }
        this.GAME.setSelection(selection);
      }
      else if (key.getCode() == KeyCode.ENTER) {
        this.GAME.setProtag(Constants.CHARACTERS[this.GAME.getSelection() - 1]);
        InformationBar.setCharStats(Constants.CHARACTERS[this.GAME.getSelection() - 1].getStats());
        InformationBar.setProfile(Constants.CHARACTERS[this.GAME.getSelection() - 1].getProfile());
        this.GAME.setSelection((byte) 1);
        this.GAME.setState((byte) 2);
      }
      
    }
    //This is used to choose and set the level
    else if (this.GAME.getState() == 4) {
      
      if (key.getCode() == KeyCode.RIGHT) {
        if (this.GAME.getLevelsUnlocked() < this.GAME.getSelection() + 1) {
          return;
        }
        this.GAME.setSelection((byte) (this.GAME.getSelection() + 1));
      }
      else if (key.getCode() == KeyCode.LEFT) {
        if (this.GAME.getSelection() == 1) {
          return;
        }
        this.GAME.setSelection((byte) (this.GAME.getSelection() - 1));
      }
      else if (key.getCode() == KeyCode.ENTER) {
        this.GAME.setCurrentLevel(LevelParser.parseLevel(this.GAME.getSelection()));
        this.GAME.setState((byte) 5);
      }
      
    }
    //This is used to check for movement, shooting, and ability usage
    else if (this.GAME.getState() == 5) {
      Vegetable protag = this.GAME.getProtag();
      
      //If the W button is clicked on the keyboard and the character is not in ability mode, the jump method will be executed 
      if (key.getCode() == KeyCode.W) {
        boolean physicsOff = false;
        for (BasicAbility ability : protag.getAbilities()) {
          if (ability.isActive() && ability.isPhysics()) {
            physicsOff = true;
            break;
          }
        }
        if (physicsOff) {
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
      else {
        for (int i = 0; i < protag.getAbilities().length; i++) {
          BasicAbility ability = protag.getAbilities()[i];
          if (key.getCode() != ability.getActivator()) {
            continue;
          }
          if (ability.isActive() || !ability.isAllowed()) {
            SoundManager.playPlayer(Sounds.ABILITYUNAVAILABLE);
            continue;
          }
          protag.useAbility(i);
        }
      }
    }
    // Listener to handle the victory screen.
    else if (this.GAME.getState() == 6) {
      if (key.getCode() != KeyCode.ENTER) {
        return;
      }
      try {
        this.GAME.setState((byte) 2);
        if (this.GAME.getLevelsUnlocked() == this.GAME.getCurrentLevel().getLevelNumber()) {
          PrintWriter profileWriter;
          profileWriter = new PrintWriter(new File("").getAbsolutePath() + "/resources/save/profile" + String.valueOf(this.GAME.getCurrentProfile()) + ".veggiedata", "UTF-8");
          profileWriter.println(String.valueOf(this.GAME.getCurrentLevel().getLevelNumber() + 1));
          profileWriter.close();
          this.GAME.setLevelsUnlocked((byte) (this.GAME.getCurrentLevel().getLevelNumber() + 1));
        }
        this.GAME.getProtag().reinstance();
        this.GAME.setCurrentLevel(null);
      }
      catch(Exception e) {
        e.printStackTrace();
      }
    }
    // Listener to handle the defeat screen.
    else if (this.GAME.getState() == 7) {
      if (key.getCode() != KeyCode.ENTER && key.getCode() != KeyCode.ESCAPE) {
        return;
      }
      this.GAME.getProtag().reinstance();
      if (key.getCode() == KeyCode.ENTER) {
        this.GAME.setCurrentLevel(LevelParser.parseLevel(this.GAME.getCurrentLevel().getLevelNumber()));
        this.GAME.setState((byte) 5);
      }
      else {
        this.GAME.setCurrentLevel(null);
        this.GAME.setState((byte) 2);
      }
    }
    // Listener for options screen.
    else if (this.GAME.getState() == 8 && key.getCode() == KeyCode.ENTER) {
      this.GAME.setSelection((byte) 1);
      this.GAME.setState((byte) 2);
    }
    
  }

}
