package types;

import java.util.Timer;

import javafx.scene.image.Image;
import main.Main;
import main.SoundManager;
import main.Sounds;

public abstract class Vegetable extends Character {
  // Character resistance.
  public double res = 1;
  // Current times jumped.
  public byte jumps = 0;
  public boolean jumpReleased = true;
  // Last direction moved.
  public byte lastDirection = 1;
  // Velocity.
  public int xVel;
  public int yVel = 0;
  // Directions being moved in.
  public boolean right = false;
  public boolean left = false;
  public boolean up = false;
  // Current character health.
  public int hp = 100;
  // Current character lifesteal.
  public double lifesteal = 0;
  // Whether of not the character is hurt.
  public boolean hurt = false;
  // Amount of time the character has been falling for.
  public int fallTime = 0;
  private ProjectileData projData;
  private boolean projCooldown = false;
  private Timer shotController = new Timer();
  // Whether the character is invincible.
  private boolean invincible = false;
  private Image hurtSprite;
  private Timer invincibilityTimer;
  // Character abilities.
  private BasicAbility[] abilities;
  private Timer shootTimer;
  private int projCooldownFraction = 10;
  private int speed;
  private Image selectSprite;
  private int nameWidth;
  private int nameHeight;
  private boolean spriteDirectional = false;
  private Image spriteLeft;
  private Image hurtLeft;
  private Image icon;
  // Character stats image.
  private Image stats;
  // Character profile image.
  private Image profile;
  
  public Vegetable(String spriteLocation, String hurt, SolidData data, ProjectileData projData, BasicAbility[] abilities) {
    
    super(new Coordinates(0, 0), spriteLocation, data);
    this.init(hurt, projData, abilities);
    
  }
  
  public Vegetable(String spriteLocation, String spriteLocationLeft, String hurt, String hurtLeft, SolidData data, ProjectileData projData, BasicAbility[] abilities) {
    
    super(new Coordinates(0, 0), spriteLocation, data);
    this.init(hurt, projData, abilities);
    this.setSpriteDirectional(true);
    this.setSpriteLeft(new Image(spriteLocationLeft));
    this.setHurtLeft(new Image(hurtLeft));
    
  }
  
  private void init(String hurt, ProjectileData projData, BasicAbility[] abilities) {
    this.vx = 500 - (this.w / 2);
    this.vy = 300 - (this.h / 2);
    this.projData = projData;
    this.setAbilities(abilities);
    this.setHurtSprite(new Image(hurt));
  }
  
  public void jump() {
    // Jump with height depending on how many times the character has jumped.
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
    SoundManager.playPlayer(Sounds.JUMP, 0.8);
    jumps++;
    this.up = true;
  }
  
  public void shootProjectile() {
    
    if (this.isProjCooldown()) {
      return;
    }
    
    SoundManager.playPlayer(Sounds.SHOOT);
    // If the shot method is burst, create projectiles on a timer.
    if (this.getProjData().burst) {
      this.shotController = new Timer();
      this.shotController.scheduleAtFixedRate(new ShootProjectile(this, this.getProjData().count, this.shotController), 0, this.getProjData().interval);
    }
    else {
      // Otherwise, create a single projectile.
      Projectile proj = new Projectile(new Coordinates(this.x, this.y + Math.round((this.h / 2) - (this.getProjData().h / 2))), this.lastDirection, this.getProjData());
      Main.getGame().getCurrentLevel().getProjectiles().add(proj);
      Main.getGame().getCurrentLevel().getMapItems().add(proj);
    }
    this.setProjCooldown(true);
    this.setProjCooldownFraction(0);
    // Reset projectile cooldown.
    Timer resetter = new Timer();
    resetter.schedule(new ResetProjectileCooldown(this), this.getProjData().cd);
    Timer updateFraction = new Timer();
    updateFraction.scheduleAtFixedRate(new AmmoCooldownUpdater(this, updateFraction), 0, this.getProjData().cd / 10);
    
  }
  
  // Called whenever an ability is used.
  public void useAbility(int index) {
    BasicAbility ability = this.abilities[index];
    ability.setActive(true);
    ability.basic();
    ability.setAllowed(false);
    // If the ability can have stacks, reduce stacks and re-enable ability if any remain.
    if (ability.isStacked()) {
      ability.setCurStacks(ability.getCurStacks() - 1);
      if (ability.getCurStacks() > 0) {
        ability.setAllowed(true);
      }
    }
  }
  
  // Reinstances the character, refreshing its health and abilities.
  public void reinstance() {
    
    // Refresh and enable all abilities.
    for (BasicAbility ability : this.abilities) {
      if (ability.isActive() || ability.isReinstance()) {
        ability.basicEnd();
        ability.setActive(false);
      }
      ability.setAllowed(true);
      if (ability.isStacked()) {
        ability.setCurStacks(ability.getMaxStacks());
      }
    }
    // Set health.
    this.hp = 100;
    // Cancel  movement.
    this.left = this.up = this.right = false;
    // Makes the character vulnerable.
    this.hurt = false;
    
  }
  
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

  public Timer getInvincibilityTimer() {
    return invincibilityTimer;
  }

  public void setInvincibilityTimer(Timer invincibilityTimer) {
    this.invincibilityTimer = invincibilityTimer;
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

  public BasicAbility[] getAbilities() {
    return abilities;
  }

  public void setAbilities(BasicAbility[] abilities) {
    this.abilities = abilities;
  }

  public boolean isSpriteDirectional() {
    return spriteDirectional;
  }

  public void setSpriteDirectional(boolean spriteDirectional) {
    this.spriteDirectional = spriteDirectional;
  }

  public Image getSpriteLeft() {
    return spriteLeft;
  }

  public void setSpriteLeft(Image spriteLeft) {
    this.spriteLeft = spriteLeft;
  }

  public Image getIcon() {
    return icon;
  }

  public void setIcon(Image icon) {
    this.icon = icon;
  }

  public Image getHurtLeft() {
    return hurtLeft;
  }

  public void setHurtLeft(Image hurtLeft) {
    this.hurtLeft = hurtLeft;
  }

  public Image getStats() {
    return stats;
  }

  public void setStats(Image stats) {
    this.stats = stats;
  }

  public Image getProfile() {
    return profile;
  }

  public void setProfile(Image profile) {
    this.profile = profile;
  }

}
