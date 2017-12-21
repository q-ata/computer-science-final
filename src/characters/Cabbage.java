package characters;

import java.util.Timer;

import javafx.scene.image.Image;
import main.Main;
import main.SoundManager;
import types.BasicAbility;
import types.ProjectileData;
import types.ResetBasicCooldown;
import types.Solid;
import types.SolidData;
import types.Vegetable;

public class Cabbage extends Vegetable {
  
  private byte basicDirection = 1;
  private final Image[] basicAbilitySprites = {new Image("file:resources/character/cabbage_basic_active_right.png"), new Image("file:resources/character/cabbage_basic_active_left.png")};
  private final Image placeholderSprite = new Image("file:resources/character/cabbage_sprite.png");
  
  public Cabbage() {
    
    super("file:resources/character/cabbage_sprite.png", "file:resources/character/cabbage_invincible.png", new SolidData(80, 80, 0, 0),
        new ProjectileData(10, "file:resources/character/lettuce_projectile", 78, 50, 200, 10), new BasicAbility(true, 500, 6000, "file:resources/icons/cabbage_dash.png"));
    this.name = "Cody Cabbage";
    this.setSpeed(5);
    this.res = 0.8;
    this.setNameWidth(248);
    this.setNameHeight(59);
    
  }
  
  @Override
  public void doBasic() {
    
    this.xVel = this.basicDirection == 1 ? 10 : -10;
    for (Solid solid : Main.getCurrentLevel().getSolids()) {
      if (this.xVel > 0 && solid.x <= this.x + this.w + this.xVel && solid.x + solid.w > this.x && solid.y < this.y + this.h && solid.y + solid.h > this.y) {
        this.xVel = 0;
        this.x = solid.x - this.w;
        Main.visibleX = this.x - 460;
        break;
      }
      else if (solid.x + solid.w >= this.x + this.xVel && solid.x < this.x + this.w && solid.y < this.y + this.h && solid.y + solid.h > this.y) {
        this.xVel = 0;
        this.x = solid.x + solid.h;
        Main.visibleX = this.x - 460;
        break;
      }
    }
    this.x += this.xVel;
    Main.visibleX += this.xVel;
    this.yVel = 0;
    
  }
  
  @Override
  public void basicEnd() {
    
    this.sprite = this.placeholderSprite;
    this.setInvincible(false);
    Timer timer = new Timer();
    timer.schedule(new ResetBasicCooldown(this), this.getBasic().getCooldown());
    
  }

  @Override
  public void basic() {
    
    this.basicDirection = this.lastDirection;
    this.sprite = this.basicAbilitySprites[this.basicDirection == 1 ? 0 : 1];
    this.setInvincible(true);
    SoundManager.playPlayer(2, 0.5);
    if (this.getInvincibilityTimer() != null) {
      this.getInvincibilityTimer().cancel();
      this.getInvincibilityTimer().purge();
    }
    this.setBasicAllowed(false);
    
  }

  @Override
  public void ultimate() {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void passive() {
    // TODO Auto-generated method stub
    
  }
  
 }
