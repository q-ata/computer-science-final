package main;

import types.InformationBar;
import types.MapItem;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Render {
  
  private static Canvas canvas = Main.getCanvas();
  private static GraphicsContext gc = Main.getGc();
  
  public static void render() {
    
    Render.gc.fillRect(0, 0, Render.canvas.getWidth(), Render.canvas.getHeight());
    Render.gc.drawImage(Main.bg, -Main.visibleX - 800, -Main.visibleY - 1800);
    
    for (MapItem item : Main.getMapItems()) {
      
      Render.gc.drawImage(item.sprite, item.vx, item.vy);
      
    }
    
    Render.gc.drawImage(Main.getProtag().sprite, Main.getProtag().vx, Main.getProtag().vy);
    
    Render.gc.fillRect(0, 600, 1000, 80);
    
    double healthWidth = ((double) 380 / 100 * Main.getProtag().hp);
    Render.gc.drawImage(InformationBar.getHealthbar(), 0, 0, healthWidth < 0 ? 0 : healthWidth, 35, 450, 610, healthWidth < 0 ? 0 : healthWidth, 35);
    double ammobarWidth = ((double) 380 / 10 * Main.getProtag().getProjCooldownFraction());
    Render.gc.drawImage(InformationBar.getAmmobar(), 0, 0, ammobarWidth, 25, 450, 645, ammobarWidth, 25);
    
  }

}