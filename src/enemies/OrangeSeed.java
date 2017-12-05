package enemies;

import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class OrangeSeed extends Enemy {
  
  private boolean left;

  public OrangeSeed(Coordinates coords, boolean dir) {
    
    super(coords, "file:resources/character/orange_seed" + (dir ? "_left.png" : ".png"), new SolidData(35, 35, 0, 0), true);
    this.left = dir;
    this.setDmg(30);
    this.endurance = 0;
    
  }

  @Override
  public void enemyMovement() {
    
    this.y += 5;
    this.x += left ? -5 : 5;
    
  }

}
