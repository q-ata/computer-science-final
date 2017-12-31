package solids;

import types.Coordinates;
import types.Solid;
import types.SolidData;

public class Finish extends Solid {

  public Finish(Coordinates coords) {
    super(coords, "file:resources/finish.png", new SolidData(100, 100, 0, 0));
  }

}
