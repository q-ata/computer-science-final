package main;

import types.BasicAbility;
import types.InformationBar;
import types.MapItem;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Render {
  
  private static Canvas canvas = Main.getCanvas();
  private static GraphicsContext gc = Main.getGc();
  
  // Various resources and backgrounds.
  private static Image profileSelect = new Image("file:resources/profile select.png");
  private static Image titleScreen = new Image("file:resources/title screen.png");
  private static Image levelSelect = new Image("file:resources/level select.png");
  private static Image charSelect = new Image("file:resources/character_select.png");
  
  // Select game profile to play on.
  public static void renderProfile() {
    
    Render.gc.drawImage(Render.profileSelect, 0, 0);
    switch (Main.getSelection()) {
    case 1:
      System.out.println("SELECTED PROFILE 1");
      break;
    case 2:
      System.out.println("SELECTED PROFILE 2");
      break;
    case 3:
      System.out.println("SELECTED PROFILE 3");
      break;
    default:
      System.exit(0);
    }
    
  }
  
  public static void renderTitle() {
    
    Render.gc.drawImage(Render.titleScreen, 0, 0);
    switch (Main.getSelection()) {
    case 1:
      System.out.println("ON CHARACTER SELECT");
      break;
    case 2:
      System.out.println("ON LEVEL SELECT");
      break;
    case 3:
      System.out.println("ON EXIT KEY");
      break;
    default:
      System.exit(0);
    }
    
  }
  
  public static void renderCharacterSelect() {
    
    Render.gc.drawImage(Render.charSelect, 0, 0);
    Main.setFont(100);
    Render.gc.fillText("Character Select", 182, 80);
    Render.gc.drawImage(Main.getProtag().getSelectSprite(), 500 - (Constants.CHARACTERS[Main.getSelection() - 1].getSelectSprite().getWidth() / 2), 230 - (Constants.CHARACTERS[Main.getSelection() - 1].getSelectSprite().getHeight() / 2));
    Render.gc.fillText(Constants.CHARACTERS[Main.getSelection() - 1].name, 500 - (Constants.CHARACTERS[Main.getSelection() - 1].getNameWidth()), 500 - (Constants.CHARACTERS[Main.getSelection() - 1].getNameHeight() / 2));
    
  }
  
  // Render the level select.
  public static void renderLevelSelect() {
    
    Render.gc.drawImage(Render.levelSelect, 0, 0);
    Main.setFont(800);
    Render.gc.fillText(Integer.toString(Main.getSelection()), 100, 600);
    
  }
  
  public static void renderLevel() {
    
    // Render the background in the appropriate position.
    Render.gc.fillRect(0, 0, Render.canvas.getWidth(), Render.canvas.getHeight());
    Render.gc.drawImage(Main.getCurrentLevel().getBackground(), (Main.visibleX / 4) + 500, (Main.visibleY / 4) + 500, 1000, 600, 0, 0, 1000, 600);
    
    for (MapItem item : Main.getCurrentLevel().getMapItems()) {
      
    	// Draws all map items to the screen.
      Render.gc.drawImage(item.sprite, item.vx, item.vy);
      
    }
    
    // Renders the character.
    Render.gc.drawImage(Main.getProtag().sprite, Main.getProtag().vx, Main.getProtag().vy);
    
    // Renders a black bar to be used to display information.
    Render.gc.fillRect(0, 600, 1000, 80);
    
    // Renders current health and bar representing time before the character can shoot again.
    Render.gc.drawImage(InformationBar.getBackground(), 0, 600);
    double healthWidth = ((double) 380 / 100 * Main.getProtag().hp);
    Render.gc.drawImage(InformationBar.getHealthbar(), 0, 0, healthWidth < 0 ? 0 : healthWidth, 35, 350, 610, healthWidth < 0 ? 0 : healthWidth, 35);
    double ammobarWidth = ((double) 380 / 10 * Main.getProtag().getProjCooldownFraction());
    Render.gc.drawImage(InformationBar.getAmmobar(), 0, 0, ammobarWidth, 25, 350, 645, ammobarWidth, 25);
    
    int startingX = 260;
    for (BasicAbility ability : Main.getProtag().getAbilities()) {
      
      if (ability.isAllowed()) {
        
        Main.setFont(24);
        Image basicIcon = ability.getIcon();
        Render.gc.drawImage(basicIcon, startingX, 610);
        Render.gc.fillText(Constants.keyCodeMap.get(ability.getActivator()), startingX + 15, 670);
        
      }
      else {
        
        // If the ability has been used and is on cool down. An "X" will shown over the ability
        Main.setFont(68);
        Render.gc.fillText("X", startingX + 2, 655);
        
      }
      
      startingX += 50;
      
    }
    
    // Renders information about the character in the bottom left of the screen.
    Main.setFont(28);
    Render.gc.drawImage(InformationBar.getProfile(), 5, 605);
    Render.gc.fillText("Cody Cabbage", 80, 622);
    Render.gc.drawImage(InformationBar.getCharStats(), 80, 630);
    
  }

}