package characters;

import abilities.CabbageDash;
import types.BasicAbility;
import types.ProjectileData;
import types.SolidData;
import types.Vegetable;

public class Cabbage extends Vegetable {
  
  public Cabbage() {
    
    super("file:resources/character/cabbage_sprite.png", "file:resources/character/cabbage_invincible.png", new SolidData(80, 80, 0, 0),
        new ProjectileData(10, "file:resources/character/lettuce_projectile", 78, 50, 200, 10), new BasicAbility[] {new CabbageDash()});
    this.name = "Cody Cabbage";
    this.setSpeed(5);
    this.res = 0.8;
    this.setNameWidth(248);
    this.setNameHeight(59);
    
  }
  
 }
