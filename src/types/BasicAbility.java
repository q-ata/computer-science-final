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
  private int index;
  private boolean reinstance = false;
  private boolean stacked = false;
  private int maxStacks;
  private int curStacks;
  
  public BasicAbility(boolean physics, int length, int cooldown, String iconLocation, KeyCode activator, int index) {
    
    this.setPhysics(physics);
    this.setLength(length);
    this.setCooldown(cooldown);
    this.setIcon(new Image(iconLocation));
    this.setActivator(activator);
    this.setIndex(index);
    
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

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public boolean isReinstance() {
    return reinstance;
  }

  public void setReinstance(boolean reinstance) {
    this.reinstance = reinstance;
  }

  public boolean isStacked() {
    return stacked;
  }

  public void setStacked(boolean stacked) {
    this.stacked = stacked;
  }

  public int getMaxStacks() {
    return maxStacks;
  }

  public void setMaxStacks(int maxStacks) {
    this.maxStacks = maxStacks;
  }

  public int getCurStacks() {
    return curStacks;
  }

  public void setCurStacks(int curStacks) {
    this.curStacks = curStacks;
  }

}
