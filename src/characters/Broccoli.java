package characters;

import abilities.BroccoliAlternateFire;
import abilities.BroccoliBlink;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import types.BasicAbility;
import types.ProjectileData;
import types.SolidData;
import types.Vegetable;

public class Broccoli extends Vegetable {

  public Broccoli() {
    super("file:resources/character/broccoli_sprite.png", "file:resources/character/broccoli_invincible.png", new SolidData(64, 72, 0, 0),
        new ProjectileData(18, "file:resources/character/shuriken_projectile", 30, 30, 750, 60), new BasicAbility[] {new BroccoliAlternateFire(KeyCode.K, 0), new BroccoliBlink(KeyCode.L, 1)});
    this.name = "Bridget Broccoli";
    this.setSpeed(5);
    this.res = 1.4;
    this.setNameWidth(248);
    this.setNameHeight(59);
    this.setSelectSprite(new Image("file:resources/character/broccoli_select.png"));
    this.setIcon(new Image("file:resources/icons/broccoli_icon.png"));
    this.setProfile(new Image("file:resources/character/broccoli_profile.png"));
    this.setStats(new Image("file:resources/character/broccoli_stats.png"));
  }

}
