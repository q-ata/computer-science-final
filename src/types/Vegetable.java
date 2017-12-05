package types;

import java.util.Timer;

import javafx.scene.image.Image;
import main.Main;
import main.SoundManager;

public abstract class Vegetable extends Character {
  
  public double res = 1;
  public byte jumps = 0;
  public boolean jumpReleased = true;
  public byte lastDirection = 1;
  public int xVel;
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
  private boolean basicActive = false;
  private boolean basicPhysics = false;
  private int basicLength = 0;
  private int basicCooldown;
  private boolean basicAllowed = true;
  private Timer invincibilityTimer;
  private int speed;
  
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
    SoundManager.playPlayer(1, 0.8);
    jumps++;
    this.up = true;
  }
  
  public void shootProjectile() {
    
    if (this.isProjCooldown()) {
      return;
    }
    
    SoundManager.playPlayer(0, 0.5);
    
    Projectile proj = new Projectile(new Coordinates(this.x, this.y + Math.round((this.h / 2) - (this.getProjData().h / 2))), this.lastDirection, this.getProjData());
    Main.appendProjectile(proj);
    Main.getMapItems().add(proj);
    this.setProjCooldown(true);
    
    Timer resetter = new Timer();
    resetter.schedule(new ResetProjectileCooldown(this), this.getProjData().cd);
    
  }
  
  public abstract void doBasic();
  public abstract void basicEnd();
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

  public boolean isBasicActive() {
    return basicActive;
  }

  public void setBasicActive(boolean basicActive) {
    this.basicActive = basicActive;
  }

  public boolean isBasicPhysics() {
    return basicPhysics;
  }

  public void setBasicPhysics(boolean basicPhysics) {
    this.basicPhysics = basicPhysics;
  }

  public int getBasicLength() {
    return basicLength;
  }

  public void setBasicLength(int basicLength) {
    this.basicLength = basicLength;
  }

  public Timer getInvincibilityTimer() {
    return invincibilityTimer;
  }

  public void setInvincibilityTimer(Timer invincibilityTimer) {
    this.invincibilityTimer = invincibilityTimer;
  }

  public int getBasicCooldown() {
    return basicCooldown;
  }

  public void setBasicCooldown(int basicCooldown) {
    this.basicCooldown = basicCooldown;
  }

  public boolean isBasicAllowed() {
    return basicAllowed;
  }

  public void setBasicAllowed(boolean basicAllowed) {
    this.basicAllowed = basicAllowed;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

}
