package types;

import javafx.scene.image.Image;
// Any item on the map.
public abstract class MapItem {
  
  public int x;
  public int y;
  public int vx;
  public int vy;
  public Image sprite;
  public int id;
  
  public MapItem(Coordinates coords, String spriteLocation) {
    
    this.x = coords.x;
    this.y = coords.y;
    // Visible X, where the item appears on screen.
    this.vx = this.x;
    this.vy = this.y;
    this.sprite = new Image(spriteLocation);
    
  }

}
