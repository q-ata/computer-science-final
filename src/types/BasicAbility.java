package types;

import javafx.scene.image.Image;

public class BasicAbility {
  
  private boolean physics;
  private int length;
  private int cooldown;
  private Image icon;
  
  public BasicAbility(boolean physics, int length, int cooldown, String iconLocation) {
    
    this.setPhysics(physics);
    this.setLength(length);
    this.setCooldown(cooldown);
    this.setIcon(new Image(iconLocation));
    
  }

  public boolean isPhysics() {
    return physics;
  }

  public void setPhysics(boolean physics) {
    this.physics = physics;
  }

  public int getLength() {
    return length;
  }

  public void setLength(int length) {
    this.length = length;
  }

  public int getCooldown() {
    return cooldown;
  }

  public void setCooldown(int cooldown) {
    this.cooldown = cooldown;
  }

  public Image getIcon() {
    return icon;
  }

  public void setIcon(Image icon) {
    this.icon = icon;
  }

}
