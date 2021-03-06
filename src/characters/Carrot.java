package characters;

import abilities.CarrotLifeLeech;
import abilities.CarrotMachineGun;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import types.BasicAbility;
import types.ProjectileData;
import types.SolidData;
import types.Vegetable;
// Carrot character.
public class Carrot extends Vegetable {

  public Carrot() {
    
    super("file:resources/character/carrot/carrot_sprite.png", "file:resources/character/carrot/carrot_sprite_left.png", "file:resources/character/carrot/carrot_invincible.png", "file:resources/character/carrot/carrot_invincible_left.png", new SolidData(44, 110, 0, 0),
        new ProjectileData(8, "file:resources/character/carrot/carrot_projectile", 96, 32, 160, 5), new BasicAbility[] {new CarrotMachineGun(KeyCode.K, 0), new CarrotLifeLeech(KeyCode.L, 1)});
    this.name = "Carrol Carrot";
    this.setSpeed(5);
    this.res = 0.6;
    this.setNameWidth(249);
    this.setNameHeight(59);
    this.setSelectSprite(new Image("file:resources/character/carrot/carrot_select.png"));
    this.setIcon(new Image("file:resources/icons/carrot_icon.png"));
    this.setProfile(new Image("file:resources/character/carrot/carrot_profile.png"));
    this.setStats(new Image("file:resources/character/carrot/carrot_stats.png"));
    
  }

}
