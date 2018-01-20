package enemies;

import main.Main;
import types.Coordinates;
import types.Enemy;
import types.SolidData;
// Downwards facing knife enemy.
public class KnifeDown extends Enemy {

  public KnifeDown(Coordinates coords) {
    
    super(coords, "file:resources/character/knife/knife_down.png", new SolidData(48, 120, 0, 0), 0);
    this.endurance = 0;
    this.setDmg(Main.getGame().getProtag().hp / Main.getGame().getProtag().res);
    this.setSolid(true);
    
  }

  @Override
  public void enemyMovement() {
    
    return;
    
  }

}
