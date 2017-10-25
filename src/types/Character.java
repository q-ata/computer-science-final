package types;

import javafx.scene.image.Image;

public abstract class Character extends Solid {
  
  public int res;
  public Image[] sprites;
  public String name;

  public Character(Coordinates coords, String spriteLocation, SolidData data) {
    super(coords, spriteLocation, data);
    
  }
  
  public abstract void basic();
  public abstract void ultimate();
  public abstract void passive();

}
