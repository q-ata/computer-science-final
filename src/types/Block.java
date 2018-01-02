package types;

public abstract class Block extends Solid {

  public Block(Coordinates coords, String spriteLocation, SolidData data) {
    super(coords, spriteLocation, data);
  }
  
  public abstract void collisionProperties();

}
