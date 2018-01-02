package solids;

import main.Main;
import types.Block;
import types.Coordinates;
import types.SolidData;

public class Portal extends Block {
  
  private Coordinates destination;

  public Portal(Coordinates coords, Coordinates destination) {
    super(coords, "file:resources/blocks/portal.png", new SolidData(100, 100, 0, 0));
    this.destination = destination;
  }

  @Override
  public void collisionProperties() {
    Main.getProtag().x = destination.x;
    Main.getProtag().y = destination.y;
    Main.visibleX = destination.x - 500 + (Main.getProtag().w / 2);
    Main.visibleY = destination.y - 300 + (Main.getProtag().h / 2);
  }

}
