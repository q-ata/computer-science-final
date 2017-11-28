package types;

public abstract class Character extends Solid {
  
  public String name;

  public Character(Coordinates coords, String spriteLocation, SolidData data) {
    super(coords, spriteLocation, data);
    
  }

}
