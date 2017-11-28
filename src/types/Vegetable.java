package types;

import java.util.Timer;

import javafx.scene.image.Image;
import main.Main;

public abstract class Vegetable extends Character {
  
  public int res = 1;
  public byte jumps = 0;
  public boolean jumpReleased = true;
  public byte lastDirection = 1;
  public int xVel = 5;
  public int yVel = 0;
  public boolean right = false;
  public boolean left = false;
  public boolean up = false;
  private ProjectileData projData;
  private boolean projCooldown = false;
  public int hp = 100;
  private boolean invincible = false;
  private Image hurtSprite;
  private Image nonHurtSprite;
  
  public Vegetable(String spriteLocation, String hurt, SolidData data, ProjectileData projData) {
    
    super(new Coordinates(0, 0), spriteLocation, data);
    this.vx = 460;
    this.vy = 260;
    this.projData = projData;
    this.setNonHurtSprite(this.sprite);
    this.setHurtSprite(new Image(hurt));
    
  }
  
  public void jump() {
    if (!jumpReleased) {
      return;
    }
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
    
    if (this.isProjCooldown()) {
      return;
    }
    
    Projectile proj = new Projectile(new Coordinates(this.x, this.y + Math.round((this.h / 2) - (this.getProjData().h / 2))), this.lastDirection, this.getProjData());
    Main.appendProjectile(proj);
    Main.getMapItems().add(proj);
    this.setProjCooldown(true);
    
    Timer resetter = new Timer();
    resetter.schedule(new ResetProjectileCooldown(this), this.getProjData().cd);
    
  }
  
  public abstract void basic();
  public abstract void ultimate();
  public abstract void passive();

  public ProjectileData getProjData() {
    return projData;
  }

  public void setProjData(ProjectileData projData) {
    this.projData = projData;
  }

  public boolean isProjCooldown() {
    return projCooldown;
  }

  public void setProjCooldown(boolean projCooldown) {
    this.projCooldown = projCooldown;
  }

  public boolean isInvincible() {
    return invincible;
  }

  public void setInvincible(boolean invincible) {
    this.invincible = invincible;
  }

  public Image getHurtSprite() {
    return hurtSprite;
  }

  public void setHurtSprite(Image hurtSprite) {
    this.hurtSprite = hurtSprite;
  }

  public Image getNonHurtSprite() {
    return nonHurtSprite;
  }

  public void setNonHurtSprite(Image nonHurtSprite) {
    this.nonHurtSprite = nonHurtSprite;
  }

}
