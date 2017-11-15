package types;

public class Solid extends MapItem {
  
  public int w;
  public int h;
  public int offx;
  public int offy;

  public Solid(Coordinates coords, String spriteLocation, SolidData data) {
    super(coords, spriteLocation);
    this.w = data.w;
    this.h = data.h;
    this.offx = data.offx;
    this.offy = data.offy;
  }

}
