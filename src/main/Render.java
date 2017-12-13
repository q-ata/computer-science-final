package main;

import types.InformationBar;
import types.MapItem;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Render {
  
  private static Canvas canvas = Main.getCanvas();
  private static GraphicsContext gc = Main.getGc();
  private static Image profileSelect = new Image("file:resources/profile select.png");
  private static Image titleScreen = new Image("file:resources/title screen.png");
  private static Image levelSelect = new Image("file:resources/level select.png");
  
  public static void renderProfile() {
    
    Render.gc.drawImage(Render.profileSelect, 0, 0);
    switch (Main.getSelection()) {
    case (byte) 1:
      System.out.println("SELECTED PROFILE 1");
      break;
    case (byte) 2:
      System.out.println("SELECTED PROFILE 2");
      break;
    case (byte) 3:
      System.out.println("SELECTED PROFILE 3");
      break;
    default:
      System.exit(0);
    }
    
  }
  
  public static void renderTitle() {
    
    Render.gc.drawImage(Render.titleScreen, 0, 0);
    switch (Main.getSelection()) {
    case (byte) 1:
      System.out.println("ON CHARACTER SELECT");
      break;
    case (byte) 2:
      System.out.println("ON LEVEL SELECT");
      break;
    case (byte) 3:
      System.out.println("ON EXIT KEY");
      break;
    default:
      System.exit(0);
    }
    
  }
  
  public static void renderCharacterSelect() {
    
    return;
    
  }
  
  public static void renderLevelSelect() {
    
    Render.gc.drawImage(Render.levelSelect, 0, 0);
    Main.setFont(800);
    Render.gc.fillText(Integer.toString(Main.getSelection()), 100, 600);
    
  }
  
  public static void renderLevel() {
    
    Render.gc.fillRect(0, 0, Render.canvas.getWidth(), Render.canvas.getHeight());
    Render.gc.drawImage(Main.getCurrentLevel().getBackground(), -Main.visibleX - 800, -Main.visibleY - 1800);
    
    for (MapItem item : Main.getCurrentLevel().getMapItems()) {
      
      Render.gc.drawImage(item.sprite, item.vx, item.vy);
      
    }
    
    Render.gc.drawImage(Main.getProtag().sprite, Main.getProtag().vx, Main.getProtag().vy);
    
    Render.gc.fillRect(0, 600, 1000, 80);
    
    Render.gc.drawImage(InformationBar.getBackground(), 0, 600);
    double healthWidth = ((double) 380 / 100 * Main.getProtag().hp);
    Render.gc.drawImage(InformationBar.getHealthbar(), 0, 0, healthWidth < 0 ? 0 : healthWidth, 35, 350, 610, healthWidth < 0 ? 0 : healthWidth, 35);
    double ammobarWidth = ((double) 380 / 10 * Main.getProtag().getProjCooldownFraction());
    Render.gc.drawImage(InformationBar.getAmmobar(), 0, 0, ammobarWidth, 25, 350, 645, ammobarWidth, 25);
    
    if (Main.getProtag().isBasicAllowed()) {
      
      Main.setFont(24);
      Image basicIcon = Main.getProtag().getBasic().getIcon();
      Render.gc.drawImage(basicIcon, 260, 610);
      Render.gc.fillText("K", 276, 670);
      
    }
    else {
      
      Main.setFont(68);
      Render.gc.fillText("X", 262, 655);
      
    }
    
    Main.setFont(28);
    Render.gc.drawImage(InformationBar.getProfile(), 5, 605);
    Render.gc.fillText("Cody Cabbage", 80, 622);
    Render.gc.drawImage(InformationBar.getCharStats(), 80, 630);
    
  }

}