package main;

import types.MapItem;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class Render {
  
  private static Canvas canvas = Main.getCanvas();
  private static GraphicsContext gc = Main.getGc();
  private static MapItem[] items = Main.getMapItems();
  
  public static void render() {
    
    gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
      
    for (MapItem item : items) {
      
      gc.drawImage(item.sprite, item.x, item.y);
      
    }
    
  }

}
