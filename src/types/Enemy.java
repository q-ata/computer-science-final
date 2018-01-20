package types;

public abstract class Enemy extends Solid {
  // Enemy resistance.
  public double endurance = 1;
  // Enemy health.
  public double hp = 100;
  // Enemy damage dealt.
  private double dmg = 0;
  // If the enemy has spawned.
  private boolean spawned = false;
  // If the enemy can only hit once.
  private boolean oneTime = false;
  // If the enemy is solid.
  private boolean solid = false;
  // If the enemy needs to spawn to be active.
  private boolean needsSpawn = true;
  // Points the enemy is worth.
  private int points;

  public Enemy(Coordinates coords, String spriteLocation, SolidData data, int points) {
    
    super(coords, spriteLocation, data);
    this.setPoints(points);
    
  }
  
  public Enemy(Coordinates coords, String spriteLocation, SolidData data, int points, boolean oneTime) {
    
    super(coords, spriteLocation, data);
    this.setOneTime(oneTime);
    this.setPoints(points);
    
  }
  // Enemy movement that is processed every tick.
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

  public boolean isNeedsSpawn() {
    return needsSpawn;
  }

  public void setNeedsSpawn(boolean needsSpawn) {
    this.needsSpawn = needsSpawn;
  }

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

}
