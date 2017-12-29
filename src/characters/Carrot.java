package characters;

import javafx.scene.image.Image;
import types.BasicAbility;
import types.ProjectileData;
import types.SolidData;
import types.Vegetable;

public class Carrot extends Vegetable {

  public Carrot() {
    
    super("file:resources/character/carrot_sprite.png", "file:resources/character/carrot_sprite_left.png", "file:resources/character/carrot_invincible.png", new SolidData(45, 110, 0, 0),
        new ProjectileData(8, "file:resources/character/carrot_projectile", 96, 32, 140, 5), new BasicAbility[] {});
    this.name = "Carrol Cabbage";
    this.setSpeed(5);
    this.res = 0.4;
    this.setNameWidth(270);
    this.setNameHeight(59);
    this.setSelectSprite(new Image("file:resources/character/carrot_select.png"));
    
  }

}
