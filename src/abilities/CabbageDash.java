package abilities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.Main;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;
import types.Solid;

public class CabbageDash extends BasicAbility {
  
  private byte basicDirection;
  private final Image[] basicAbilitySprites = {new Image("file:resources/character/cabbage_basic_active_right.png"), new Image("file:resources/character/cabbage_basic_active_left.png")};
  private final Image placeholderSprite = new Image("file:resources/character/cabbage_sprite.png");

  public CabbageDash(KeyCode activator, int index) {
    super(true, 500, 6000, "file:resources/icons/cabbage_dash.png", activator, index);
  }

  @Override
  public void doBasic() {
    
    this.getUser().xVel = this.basicDirection == 1 ? 10 : -10;
    for (Solid solid : Main.getCurrentLevel().getSolids()) {
      if (this.getUser().xVel > 0 && solid.x <= this.getUser().x + this.getUser().w + this.getUser().xVel && solid.x + solid.w > this.getUser().x && solid.y < this.getUser().y + this.getUser().h && solid.y + solid.h > this.getUser().y) {
        this.getUser().xVel = 0;
        this.getUser().x = solid.x - this.getUser().w;
        Main.visibleX = this.getUser().x - 460;
        break;
      }
      else if (solid.x + solid.w >= this.getUser().x + this.getUser().xVel && solid.x < this.getUser().x + this.getUser().w && solid.y < this.getUser().y + this.getUser().h && solid.y + solid.h > this.getUser().y) {
        this.getUser().xVel = 0;
        this.getUser().x = solid.x + solid.h;
        Main.visibleX = this.getUser().x - 460;
        break;
      }
    }
    this.getUser().x += this.getUser().xVel;
    Main.visibleX += this.getUser().xVel;
    this.getUser().yVel = 0;
    
  }

  @Override
  public void basicEnd() {
    
    this.getUser().sprite = this.placeholderSprite;
    this.getUser().setInvincible(false);
    
  }

  @Override
  public void basic() {
    
    this.getUser().hurt = false;
    this.basicDirection = this.getUser().lastDirection;
    this.getUser().sprite = this.basicAbilitySprites[this.basicDirection == 1 ? 0 : 1];
    this.getUser().setInvincible(true);
    SoundManager.playPlayer(Sounds.CABBAGEDASH, 0.5);
    if (this.getUser().getInvincibilityTimer() != null) {
      this.getUser().getInvincibilityTimer().cancel();
      this.getUser().getInvincibilityTimer().purge();
    }
    
  }

}
