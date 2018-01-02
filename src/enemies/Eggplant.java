package enemies;

import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class Eggplant extends Enemy {
  
  private int upSpeed = 5;
  private int downSpeed = 12;
  private int threshold = 300;
  private int wait = 30;
  private int currentWait = 0;
  private int yNeeded;
  private int minY;
  private byte state = 0;

  public Eggplant(Coordinates coords) {
    super(coords, "file:resources/character/eggplant.png", new SolidData(132, 68, 0, 0), 80);
    this.yNeeded = this.y - this.threshold;
    this.minY = this.y;
    this.init();
  }
  
  public Eggplant(Coordinates coords, int upSpeed, int downSpeed, int threshold, int wait) {
    super(coords, "file:resources/character/eggplant.png", new SolidData(132, 68, 0, 0), 80);
    
    this.upSpeed = upSpeed;
    this.downSpeed = downSpeed;
    this.threshold = threshold;
    this.yNeeded = this.y - this.threshold;
    this.minY = this.y;
    this.wait = wait;
    this.init();
    
  }
  
  private void init() {
    this.endurance = 1.2;
    this.setDmg(65);
  }

  @Override
  public void enemyMovement() {
    switch (this.state) {
    case 0:
      this.y -= this.upSpeed;
      if (this.y <= this.yNeeded) {
        this.state = (byte) 1;
      }
      break;
    case 1:
      if (++this.currentWait == this.wait) {
        this.state = (byte) 2;
        this.currentWait = 0;
      }
      break;
    case 2:
      this.y += this.downSpeed;
      if (this.y >= this.minY) {
        this.state = (byte) 3;
        this.y = this.minY;
      }
      break;
    case 3:
      if (++this.currentWait == this.wait) {
        this.state = (byte) 0;
        this.currentWait = 0;
      }
      break;
     default:
       this.hp = 0;
       break;
    }
  }

}
