package abilities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import main.SoundManager;
import main.Sounds;
import types.BasicAbility;

public class CarrotLifeLeech extends BasicAbility {
  
  private Image[] newSprites = {new Image("file:resources/character/carrot_healing_sprite.png"), new Image("file:resources/character/carrot_healing_sprite_left.png"),
      new Image("file:resources/character/carrot_invincible_healing_sprite.png"), new Image("file:resources/character/carrot_invincible_healing_sprite_left.png")};
  private Image[] placeholders;

  public CarrotLifeLeech(KeyCode activator, int index) {
    super(false, 4000, 15000, "file:resources/icons/carrot_life_leech.png", activator, index);
  }

  @Override
  public void doBasic() {
    
  }

  @Override
  public void basicEnd() {
    
    this.getUser().lifesteal = 0.0;
    this.getUser().sprite = this.placeholders[0];
    this.getUser().setSpriteLeft(this.placeholders[1]);
    this.getUser().setHurtSprite(this.placeholders[2]);
    this.getUser().setHurtLeft(this.placeholders[3]);
    
  }

  @Override
  public void basic() {
    
    this.getUser().lifesteal = 0.12;
    
    this.placeholders = new Image[] {this.getUser().sprite, this.getUser().getSpriteLeft(), this.getUser().getHurtSprite(), this.getUser().getHurtLeft()};
    this.getUser().sprite = this.newSprites[0];
    this.getUser().setSpriteLeft(this.newSprites[1]);
    this.getUser().setHurtSprite(this.newSprites[2]);
    this.getUser().setHurtLeft(this.newSprites[3]);
    SoundManager.playPlayer(Sounds.CARROTLIFELEECH);
    
  }

}
