package enemies;

import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class OrangeSeed extends Enemy {
  
  private boolean left;
  private int speedX;
  private int speedY;

  public OrangeSeed(Coordinates coords, boolean dir, int speedX, int speedY) {
    
    super(coords, "file:resources/character/orange_seed" + (dir ? "_left.png" : ".png"), new SolidData(35, 35, 0, 0), 0, true);
    this.left = dir;
    this.setDmg(30);
    this.endurance = Math.ceil(100 / Main.getGame().getProtag().getProjData().dmg);
    this.speedX = speedX;
    this.speedY = speedY;
    this.hp = 1;
    
  }

  @Override
  public void enemyMovement() {
    
    this.y += this.speedY;
    this.x += left ? -this.speedX : this.speedX;
    
  }

}
