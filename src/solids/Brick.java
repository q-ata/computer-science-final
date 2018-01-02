package solids;

import types.Block;
import types.Coordinates;
import types.SolidData;

public class Brick extends Block {

  public Brick(Coordinates coords) {
    super(coords, "file:resources/blocks/brick.png", new SolidData(50, 50, 0, 0));
  }

  @Override
  public void collisionProperties() {
    
    return;
    
  }

}
