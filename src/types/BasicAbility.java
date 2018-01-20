package types;

import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

public abstract class BasicAbility {
  // Keybind used to activate ability.
  private KeyCode activator;
  // Whether or not the ability should defy physics.
  private boolean physics;
  // Length of ability.
  private int length;
  // Amount of time the ability has been used for.
  private int curLength = 0;
  // Cooldown of ability.
  private int cooldown;
  // Current time ability has been on cooldown.
  private int curCooldown = 0;
  // Icon of ability.
  private Image icon;
  // User that has the ability.
  private Vegetable user;
  // Whether or not the ability is active.
  private boolean active = false;
  // Whether or not the ability can be used.
  private boolean allowed = true;
  // Which ability it is in list.
  private int index;
  // Whether the ability should be reinstanced on death.
  private boolean reinstance = false;
  // If the ability has stacks.
  private boolean stacked = false;
  // Maximum amount of stacks.
  private int maxStacks;
  // Currently available stacks.
  private int curStacks;
  
  public BasicAbility(boolean physics, int length, int cooldown, String iconLocation, KeyCode activator, int index) {
    
    this.setPhysics(physics);
    this.setLength(length);
    this.setCooldown(cooldown);
    this.setIcon(new Image(iconLocation));
    this.setActivator(activator);
    this.setIndex(index);
    
  }
  // Run every tick the ability is active.
  public abstract void doBasic();
  // Run once the ability ends.
  public abstract void basicEnd();
  // Run when the ability is activated.
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

  public int getCurCooldown() {
    return curCooldown;
  }

  public void setCurCooldown(int curCooldown) {
    this.curCooldown = curCooldown;
  }

  public int getCurLength() {
    return curLength;
  }

  public void setCurLength(int curLength) {
    this.curLength = curLength;
  }

}
