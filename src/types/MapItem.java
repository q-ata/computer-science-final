package types;

import javafx.scene.image.Image;

public class MapItem {
  
  public int x;
  public int y;
  public Image sprite;
  
  public MapItem(Coordinates coords, String spriteLocation) {
    
    this.x = coords.x;
    this.y = coords.y;
    this.sprite = new Image(spriteLocation);
    
  }

}
