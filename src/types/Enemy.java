package types;

public abstract class Enemy extends Solid {
  
  public double endurance = 1;
  public int hp = 100;
  private int dmg = 0;
  private boolean spawned = false;

  public Enemy(Coordinates coords, String spriteLocation, SolidData data) {
    
    super(coords, spriteLocation, data);
    
  }
  
  public abstract void enemyMovement();

  public boolean isSpawned() {
    return spawned;
  }

  public void setSpawned(boolean spawned) {
    this.spawned = spawned;
  }

  public int getDmg() {
    return dmg;
  }

  public void setDmg(int dmg) {
    this.dmg = dmg;
  }

}
