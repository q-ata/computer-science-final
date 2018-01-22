package enemies;

import types.Boss;
import types.BossAction;
import types.Coordinates;
import types.PineappleCoconutAction;
import types.PineappleLaserAction;
import types.PineappleRingsAction;
import types.PineappleRocketAction;
import types.SolidData;
// Pineapple boss enemy.
public class Pineapple extends Boss {

  public Pineapple(Coordinates coords) {
    super(coords, new String[] {"file:resources/character/pineapple/pineapple_sprite.png", "file:resources/character/pineapple/pineapple_rocket_right.png", "file:resources/character/pineapple/pineapple_rocket_left.png"},
        new BossAction[] {new PineappleRocketAction(), new PineappleLaserAction(), new PineappleRingsAction(), new PineappleCoconutAction()}, 2500, new SolidData(110, 280, 0, 0));
  }

}
