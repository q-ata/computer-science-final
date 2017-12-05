package main;

import types.MapItem;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Render {
  
  private static Canvas canvas = Main.getCanvas();
  private static GraphicsContext gc = Main.getGc();
  
  public static void render() {
    
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    gc.drawImage(Main.bg, -Main.visibleX - 800, -Main.visibleY - 1800);
    
    for (MapItem item : Main.getMapItems()) {
      
      gc.drawImage(item.sprite, item.vx, item.vy);
      
    }
    
    gc.drawImage(Main.getProtag().sprite, Main.getProtag().vx, Main.getProtag().vy);
    
  }

}