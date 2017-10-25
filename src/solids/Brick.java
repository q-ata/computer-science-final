package solids;

import types.Coordinates;
import types.Solid;
import types.SolidData;

public class Brick extends Solid {

  public Brick(Coordinates coords) {
    super(coords, "file:resources/blocks/brick.png", new SolidData(50, 50, 0, 0, false));
  }

}
