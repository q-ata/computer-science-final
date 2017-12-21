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
  private boolean basicAllowed = true;
  private Timer invincibilityTimer;
  private Timer shootTimer;
  private int projCooldownFraction = 10;
  private int speed;
  private BasicAbility basic;
  private Image selectSprite;
  private int nameWidth;
  private int nameHeight;
  
  public Vegetable(String spriteLocation, String hurt, SolidData data, ProjectileData projData, BasicAbility basic) {
    
    super(new Coordinates(0, 0), spriteLocation, data);
    this.vx = 460;
    this.vy = 260;
    this.projData = projData;
    this.setNonHurtSprite(this.sprite);
    this.setHurtSprite(new Image(hurt));
    this.setBasic(basic);
    this.setSelectSprite(new Image("file:resources/character/cabbage_select.png"));
    
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
    Main.getCurrentLevel().getProjectiles().add(proj);
    Main.getCurrentLevel().getMapItems().add(proj);
    this.setProjCooldown(true);
    this.setProjCooldownFraction(0);
    
    Timer resetter = new Timer();
    resetter.schedule(new ResetProjectileCooldown(this), this.getProjData().cd);
    Timer updateFraction = new Timer();
    updateFraction.scheduleAtFixedRate(new AmmoCooldownUpdater(this, updateFraction), 0, this.getProjData().cd / 10);
    
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

  public Timer getInvincibilityTimer() {
    return invincibilityTimer;
  }

  public void setInvincibilityTimer(Timer invincibilityTimer) {
    this.invincibilityTimer = invincibilityTimer;
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

  public Timer getShootTimer() {
    return shootTimer;
  }

  public void setShootTimer(Timer shootTimer) {
    this.shootTimer = shootTimer;
  }

  public int getProjCooldownFraction() {
    return projCooldownFraction;
  }

  public void setProjCooldownFraction(int projCooldownFraction) {
    this.projCooldownFraction = projCooldownFraction;
  }

  public BasicAbility getBasic() {
    return basic;
  }

  public void setBasic(BasicAbility basic) {
    this.basic = basic;
  }

  public Image getSelectSprite() {
    return selectSprite;
  }

  public void setSelectSprite(Image selectSprite) {
    this.selectSprite = selectSprite;
  }

  public int getNameWidth() {
    return nameWidth;
  }

  public void setNameWidth(int nameWidth) {
    this.nameWidth = nameWidth;
  }

  public int getNameHeight() {
    return nameHeight;
  }

  public void setNameHeight(int nameHeight) {
    this.nameHeight = nameHeight;
  }

}
