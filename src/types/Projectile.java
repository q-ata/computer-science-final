package types;

import main.Main;

public class Projectile extends Solid {
  
  private byte direction;
  private int velocity;
  public double dmg;

  public Projectile(Coordinates coords, byte direction, ProjectileData data) {
    
    super(coords, data.spriteLocation + (direction == 1 ? "_right" : "_left") + ".png", new SolidData(data.w, data.h, 0, 0));
    this.setDirection(direction);
    this.setVelocity(data.velocity);
    this.dmg = data.dmg;
    this.vx = Main.getGame().getProtag().vx;
    this.vy = Main.getGame().getProtag().vy + Math.round((Main.getGame().getProtag().h / 2) - (this.h / 2));
    
  }

  public byte getDirection() {
    return direction;
  }

  public void setDirection(byte direction) {
    this.direction = direction;
  }

  public int getVelocity() {
    return velocity;
  }

  public void setVelocity(int velocity) {
    this.velocity = velocity;
  }

}
