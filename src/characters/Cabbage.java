package characters;

import abilities.CabbageDash;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import types.BasicAbility;
import types.ProjectileData;
import types.SolidData;
import types.Vegetable;
// Cabbage character.
public class Cabbage extends Vegetable {
  
  public Cabbage() {
    
    super("file:resources/character/cabbage/cabbage_sprite.png", "file:resources/character/cabbage/cabbage_invincible.png", new SolidData(80, 80, 0, 0),
        new ProjectileData(10, "file:resources/character/cabbage/lettuce_projectile", 78, 50, 200, 10), new BasicAbility[] {new CabbageDash(KeyCode.K, 0)});
    this.name = "Cody Cabbage";
    this.setSpeed(5);
    this.res = 0.8;
    this.setNameWidth(248);
    this.setNameHeight(59);
    this.setSelectSprite(new Image("file:resources/character/cabbage/cabbage_select.png"));
    this.setIcon(new Image("file:resources/icons/cabbage_icon.png"));
    this.setProfile(new Image("file:resources/character/cabbage/cabbage_profile.png"));
    this.setStats(new Image("file:resources/character/cabbage/cabbage_stats.png"));
    
  }
  
 }
