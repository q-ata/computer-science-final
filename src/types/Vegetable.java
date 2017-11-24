package types;

import javafx.scene.image.Image;

public abstract class Vegetable extends Character {
  
  public int res = 1;
  public byte jumps = 0;
  private Image[] projectileSprites;
  
  public Vegetable(String spriteLocation, SolidData data, Image[] sprites, Image[] projs) {
    super(new Coordinates(0, 0), spriteLocation, data, sprites);
    this.setProjectileSprites(projs);
  }
  
  public void jump() {
    if (this.jumps == 0) {
      this.yVel = -20;
    }
    else if (this.jumps == 1) {
      this.yVel = this.yVel >= 0 ? -15 : this.yVel - 11;
    }
    else {
      return;
    }
    jumps++;
    this.up = true;
  }
  
  public void shootProjectile() {
    
  }
  
  public abstract void basic();
  public abstract void ultimate();
  public abstract void passive();

  public Image[] getProjectileSprites() {
    return projectileSprites;
  }

  public void setProjectileSprites(Image[] projectileSprites) {
    this.projectileSprites = projectileSprites;
  }

}
