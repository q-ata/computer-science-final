package enemies;

import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;

public class Knife extends Enemy {

  public Knife(Coordinates coords) {
    
    super(coords, "file:resources/character/knife.png", new SolidData(10, 82, 0, 0));
    this.endurance = 0;
    this.setDmg(Main.getProtag().hp * Main.getProtag().res);
    
  }

  @Override
  public void enemyMovement() {
    
    return;
    
  }

}
