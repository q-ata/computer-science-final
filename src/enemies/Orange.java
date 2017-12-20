package enemies;

import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class Orange extends Enemy {
  
  private boolean left;
  private int moveAmount;
  private boolean currentDir = true;
  private int moved = 0;
  private int seedX = 5;
  private int seedY = 5;

  public Orange(Coordinates coords, boolean dir, int moveAmount) {
    
    super(coords, "file:resources/character/orange" + (dir ? ".png" : "_right.png"), new SolidData(60, 56, 0, 0));
    this.left = dir;
    this.moveAmount = moveAmount;
    this.setDmg(50);
    this.endurance = 3;
    
  }

  @Override
  public void enemyMovement() {
    
    this.moved += 2;
    this.x += currentDir ? 2 : -2;
    if (moveAmount == moved) {
      this.currentDir = !this.currentDir;
      this.moved = 0;
    }
    
    if (Main.getTick() != (byte) 60) {
      return;
    }
    OrangeSeed seed = new OrangeSeed(new Coordinates(this.x, this.y), this.left, this.getSeedX(), this.getSeedY());
    seed.setNeedsSpawn(false);
    Main.getCurrentLevel().getMapItems().add(seed);
    Main.getCurrentLevel().getEnemies().add(seed);
    
  }

  public int getSeedX() {
    return seedX;
  }

  public void setSeedX(int seedX) {
    this.seedX = seedX;
  }

  public int getSeedY() {
    return seedY;
  }

  public void setSeedY(int seedY) {
    this.seedY = seedY;
  }

}
