package types;

public class ProjectileData {
  
  public int velocity;
  public String spriteLocation;
  public int w;
  public int h;
  public int cd;
  public int dmg;
  
  public ProjectileData(int velocity, String spriteLocation, int w, int h, int cd, int dmg) {
    
    this.velocity = velocity;
    this.spriteLocation = spriteLocation;
    this.w = w;
    this.h = h;
    this.cd = cd;
    this.dmg = dmg;
    
  }

}
