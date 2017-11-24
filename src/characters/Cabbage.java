package characters;

import javafx.scene.image.Image;
import types.SolidData;
import types.Vegetable;

public class Cabbage extends Vegetable {

  public Cabbage() {
    super("file:resources/character/cabbage_1.png", new SolidData(80, 80, 0, 0), new Image[] {new Image("file:resources/character/cabbage_1.png")}, new Image[] {new Image("file:resources/character/cabbage_projectile.png")});
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
