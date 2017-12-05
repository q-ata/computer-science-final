package types;

public abstract class Kamikaze extends Enemy {
  
  private int parent;

  public Kamikaze(Coordinates coords, String spriteLocation, SolidData data, int parent) {
    
    super(coords, spriteLocation, data);
    this.setParent(parent);
    
  }

  public int getParent() {
    return parent;
  }

  public void setParent(int parent) {
    this.parent = parent;
  }

}
