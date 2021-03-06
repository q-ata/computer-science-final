package enemies;

import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;

// Hardcoded enemy specifically for an obstacle on level one.
public class DoubleTomatoWallLevelOne extends Enemy {
  
  private int[] requiredKills = new int[2];

  public DoubleTomatoWallLevelOne(Coordinates coords, int[] requiredKills) {
    
    super(coords, "file:resources/process/double tomato wall.png", new SolidData(98, 588, 0, 0), 0);
    this.setRequiredKills(requiredKills);
    this.setDmg(0);
    this.endurance = 0.01;
    this.setSolid(true);
    
  }

  @Override
  public void enemyMovement() {
    // If both tomatoes have been killed, destroy all references to this enemy.
    if (Main.getGame().getCurrentLevel().getMapItems().get(this.getRequiredKills()[0]).id != this.getRequiredKills()[0] &&
        Main.getGame().getCurrentLevel().getMapItems().get(this.getRequiredKills()[1] - 1).id != this.getRequiredKills()[1]) {
      Main.getGame().getCurrentLevel().getMapItems().remove(this);
      Main.getGame().getCurrentLevel().getEnemies().remove(this);
    }
    
  }

  public int[] getRequiredKills() {
    return requiredKills;
  }

  public void setRequiredKills(int[] requiredKills) {
    this.requiredKills = requiredKills;
  }
  
}
