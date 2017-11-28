package characters;

import types.ProjectileData;
import types.SolidData;
import types.Vegetable;

public class Cabbage extends Vegetable {

  public Cabbage() {
    
    super("file:resources/character/cabbage_sprite.png", "file:resources/character/cabbage_invincible.png", new SolidData(80, 80, 0, 0),
        new ProjectileData(10, "file:resources/character/lettuce_projectile", 78, 50, 200, 10));
    this.name = "Cabbage";
    
  }

  @Override
  public void basic() {
    // TODO Auto-generated method stub
    
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
