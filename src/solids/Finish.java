package solids;

import types.Block;
import types.Coordinates;
import types.SolidData;

public class Finish extends Block {

  public Finish(Coordinates coords) {
    super(coords, "file:resources/finish.png", new SolidData(100, 100, 0, 0));
  }

  @Override
  public void collisionProperties() {
    
    return;
    
  }

}
