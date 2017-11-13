package types;

import javafx.scene.image.Image;

public abstract class Character extends Solid {
  
  public Image[] sprites;
  public String name;

  public Character(Coordinates coords, String spriteLocation, SolidData data, Image[] sprites) {
    super(coords, spriteLocation, data);
    
    this.sprites = sprites;
    
  }

}
