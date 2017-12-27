package types;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public abstract class BasicAbility {
  
  private KeyCode activator;
  private boolean physics;
  private int length;
  private int cooldown;
  private Image icon;
  private Vegetable user;
  private boolean active = false;
  private boolean allowed = true;
  
  public BasicAbility(boolean physics, int length, int cooldown, String iconLocation, KeyCode activator) {
    
    this.setPhysics(physics);
    this.setLength(length);
    this.setCooldown(cooldown);
    this.setIcon(new Image(iconLocation));
    this.setActivator(activator);
    
  }
  
  public abstract void doBasic();
  public abstract void basicEnd();
  public abstract void basic();

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

  public Vegetable getUser() {
    return user;
  }

  public void setUser(Vegetable user) {
    this.user = user;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isAllowed() {
    return allowed;
  }

  public void setAllowed(boolean allowed) {
    this.allowed = allowed;
  }

  public KeyCode getActivator() {
    return activator;
  }

  public void setActivator(KeyCode activator) {
    this.activator = activator;
  }

}
