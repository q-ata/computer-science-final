package types;

import javafx.scene.image.Image;

public abstract class Vegetable extends Character {
  
  public boolean up = false;
  public boolean right = false;
  public boolean left = false;
  public int res = 1;
  public byte jumps = 0;
  
  public Vegetable(String spriteLocation, SolidData data, Image[] sprites) {
    super(new Coordinates(0, 0), spriteLocation, data, sprites);
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
  }
  
  public abstract void basic();
  public abstract void ultimate();
  public abstract void passive();

}
