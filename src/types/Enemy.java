package types;

public abstract class Enemy extends Solid {
  
  public double endurance = 1;
  public double hp = 100;
  private double dmg = 0;
  private boolean spawned = false;
  private boolean oneTime = false;
  private boolean solid = false;

  public Enemy(Coordinates coords, String spriteLocation, SolidData data) {
    
    super(coords, spriteLocation, data);
    
  }
  
  public Enemy(Coordinates coords, String spriteLocation, SolidData data, boolean oneTime) {
    
    super(coords, spriteLocation, data);
    this.setOneTime(oneTime);
    
  }
  
  public abstract void enemyMovement();

  public boolean isSpawned() {
    return spawned;
  }

  public void setSpawned(boolean spawned) {
    this.spawned = spawned;
  }

  public double getDmg() {
    return dmg;
  }

  public void setDmg(double dmg) {
    this.dmg = dmg;
  }

  public boolean isOneTime() {
    return oneTime;
  }

  public void setOneTime(boolean oneTime) {
    this.oneTime = oneTime;
  }

  public boolean isSolid() {
    return solid;
  }

  public void setSolid(boolean solid) {
    this.solid = solid;
  }

}
