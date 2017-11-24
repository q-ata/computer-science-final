package types;

import javafx.scene.image.Image;

public abstract class Character extends Solid {
  
  public Image[] sprites;
  public String name;
  public int xVel = 5;
  public int yVel = 0;
  public boolean right = false;
  public boolean left = false;
  public boolean up = false;

  public Character(Coordinates coords, String spriteLocation, SolidData data, Image[] sprites) {
    super(coords, spriteLocation, data);
    
    this.sprites = sprites;
    
  }

}
