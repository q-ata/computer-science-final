package types;

public class ProjectileData {
  
  public int velocity;
  public String spriteLocation;
  public int w;
  public int h;
  public int cd;
  public double dmg;
  public boolean burst = false;
  public int count;
  public int interval;
  
  public ProjectileData(int velocity, String spriteLocation, int w, int h, int cd, double dmg) {
    this.init(velocity, spriteLocation, w, h, cd, dmg);
  }
  
  public ProjectileData(int velocity, String spriteLocation, int w, int h, int cd, double dmg, int count, int interval) {
    
    this.init(velocity, spriteLocation, w, h, cd, dmg);
    this.count = count;
    this.interval = interval;
    this.burst = true;
    
  }
  
  private void init(int velocity, String spriteLocation, int w, int h, int cd, double dmg) {
    this.velocity = velocity;
    this.spriteLocation = spriteLocation;
    this.w = w;
    this.h = h;
    this.cd = cd;
    this.dmg = dmg;
  }

}
