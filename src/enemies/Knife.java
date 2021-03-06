package enemies;

import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;
// Knife enemy.
public class Knife extends Enemy {

  public Knife(Coordinates coords) {
    
    super(coords, "file:resources/character/knife/knife_up.png", new SolidData(48, 120, 0, 0), 0);
    this.endurance = 0;
    this.setDmg(Main.getGame().getProtag().hp / Main.getGame().getProtag().res);
    this.setSolid(true);
    
  }

  @Override
  public void enemyMovement() {
    
    return;
    
  }

}
