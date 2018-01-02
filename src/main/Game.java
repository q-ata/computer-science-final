package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;

import types.BasicAbility;
import types.FpsResetter;
import types.InformationBar;
import types.Level;
import types.Vegetable;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Game extends Application {
  
  // Set canvas, graphics context and scene.
  private Canvas canvas;
  private GraphicsContext gc;
  private Scene scene;
  private MediaPlayer introPlayer;
  private SoundManager soundManager;
  
  public int visibleX;
  public int visibleY;
  private String currentLevelLocation;
  private String currentLevelBackground;
  private Level currentLevel;
  private byte currentProfile;
  
  private Vegetable protag;
  private InformationBar infoBar;
  private Font font;
  
  private byte selection = 1;
  private byte tick = 1;

  private byte state = 0;
  private byte fps = 0;
  
  // How many levels available to the player.
  private byte levelsUnlocked;
  
  public void initialize() {
    // Launching in constructor calls the method twice and crashes for some reason.
    Game.launch();
  }

  public void start(Stage stage) {
    
    try {
      
      // Makes the game accessible via Main.
      Main.setGame(this);
      
      // Create a new 1000 by 680 canvas.
      this.setCanvas(new Canvas(1000, 680));
      this.setGc(this.getCanvas().getGraphicsContext2D());
      Group group = new Group();
      group.getChildren().add(this.getCanvas());
      Scene scene = new Scene(group);
      // Add an event handler for when a key is pressed. KeyPressedHandler.handle is called on key press.
      scene.setOnKeyPressed(new KeyboardPressedHandler());
      // Add an event handler for when a key is released. KeyReleasedHandler.handle is called on key release.
      scene.setOnKeyReleased(new KeyboardReleasedHandler());
      this.setScene(scene);
      // Sets the contents of the stage to the canvas.
      stage.setScene(this.getScene());
      // Window title and icon.
      stage.setTitle("VeggieTales");
      stage.getIcons().add(new Image("file:resources/window_icon.png"));
      // Makes window a fixed size.
      stage.setResizable(false);
      // Ensures all Java processes are ended correctly when the window is closed.
      stage.setOnCloseRequest((event) -> System.exit(0));
      
      // Gets the path of the intro video.
      File intro = new File("resources/intro.mp4");
      String path = intro.toURI().toString();
      // Creates media objects to play the video.
      Media video = new Media(path);
      MediaPlayer videoPlayer = new MediaPlayer(video);
      this.setIntroPlayer(videoPlayer);
      // Settings for the MediaPlayer.
      videoPlayer.setAutoPlay(true);
      videoPlayer.setVolume(0.5);
      videoPlayer.setOnEndOfMedia(() -> {
        videoPlayer.dispose();
        this.setState((byte) 1);
      });
      MediaView viewer = new MediaView(videoPlayer);
      viewer.setPreserveRatio(false);
      viewer.setSmooth(true);
      viewer.setFitHeight(600);
      viewer.setFitWidth(1000);
      // Add the video to the scene.
      ((Group) scene.getRoot()).getChildren().add(viewer);
      stage.initStyle(StageStyle.DECORATED);
      
      // Wait for internal graphics to initialize then set fields.
      this.setProtag(Constants.CHARACTERS[0]);
      this.setInfoBar(new InformationBar());
      
      // Main.bg = new Image("file:resources/map/bg-1.png");
      this.setSoundManager(new SoundManager());
      Font.loadFont(new FileInputStream(new File("./resources/fonts/BradBunR.ttf")), 24);
      this.setFont(24);
      this.getGc().setFont(this.getFont());
      
      // Initializes the KeyCodeMap.
      Constants.initKeyCodeMap();
      
      // Binds a character to each character's ability array.
      for (Vegetable veggie : Constants.CHARACTERS) {
        for (BasicAbility ability : veggie.getAbilities()) {
          ability.setUser(veggie);
        }
      }
      
      // Shows the stage/window.
      stage.show();
      
      // Sets fill color to black.
      gc.setFill(Color.BLACK);
      
      // Default information bar data to Cabbage.
      InformationBar.setProfile(new Image("file:resources/character/cabbage_profile.png"));
      InformationBar.setCharStats(new Image("file:resources/character/cabbage_stats.png"));
      
      Timer fpsResetter = new Timer();
      fpsResetter.scheduleAtFixedRate(new FpsResetter(), 1000, 1000);
      
      // Creating the gameloop.
      Timeline gameLoop = new Timeline();
      // Loop continues running indefinitely.
      gameLoop.setCycleCount(Timeline.INDEFINITE);
      
      KeyFrame keyframe = new KeyFrame(
        // Running EventHandler.handle every 0.016 seconds, or 60 times a second.
        Duration.seconds(0.016),
        new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {
            
            /*
             * States:
             * -1 : Options menu
             * 0 : Playing opening
             * 1 : Profile select
             * 2 : Title screen
             * 3 : Character select
             * 4 : Level select
             * 5 : In game
             * 6 : Victory screen
             * 7 : Defeat screen
             */
            
            if (Main.getGame().getState() == 1) {  
              
              Render.renderProfile();
              
            }
            else if (Main.getGame().getState() == 2) {
              
              Render.renderTitle();
              
            }
            else if (Main.getGame().getState() == 3) {
              
              Render.renderCharacterSelect();
              
            }
            else if (Main.getGame().getState() == 4) {
              
              Render.renderLevelSelect();
              
            }
            else if (Main.getGame().getState() == 5) {
              
              StateUpdate.update();
              Render.renderLevel();
              
            }
            else if (Main.getGame().getState() == 6) {
              
              Render.renderVictory();
              
            }
            else if (Main.getGame().getState() == 7) {
              
              Render.renderDefeat();
              
            }
            
            // Increment tick. Reset if 60.
            setTick((byte) (getTick() == 60 ? 1 : getTick() + 1));
            Main.getGame().setFps((byte) (Main.getGame().getFps() + 1));
            
          }
        }
      );
      
      gameLoop.getKeyFrames().add(keyframe);
      // Starts running the loop.
      gameLoop.play();
      
    }
    catch(Exception e) {
      // Logs error to console if exception in try block.
      e.printStackTrace();
    }
    
  }
  
  public Canvas getCanvas() {
    return canvas;
  }

  public void setCanvas(Canvas canvas) {
    this.canvas = canvas;
  }

  public GraphicsContext getGc() {
    return gc;
  }

  public void setGc(GraphicsContext gc) {
    this.gc = gc;
  }

  public Scene getScene() {
    return scene;
  }

  public void setScene(Scene scene) {
    this.scene = scene;
  }

  public MediaPlayer getIntroPlayer() {
    return introPlayer;
  }

  public void setIntroPlayer(MediaPlayer introPlayer) {
    this.introPlayer = introPlayer;
  }

  public int getVisibleX() {
    return visibleX;
  }

  public void setVisibleX(int visibleX) {
    this.visibleX = visibleX;
  }

  public int getVisibleY() {
    return visibleY;
  }

  public void setVisibleY(int visibleY) {
    this.visibleY = visibleY;
  }

  public String getCurrentLevelLocation() {
    return currentLevelLocation;
  }

  public void setCurrentLevelLocation(String currentLevelLocation) {
    this.currentLevelLocation = currentLevelLocation;
  }

  public String getCurrentLevelBackground() {
    return currentLevelBackground;
  }

  public void setCurrentLevelBackground(String currentLevelBackground) {
    this.currentLevelBackground = currentLevelBackground;
  }

  public Level getCurrentLevel() {
    return currentLevel;
  }

  public void setCurrentLevel(Level currentLevel) {
    this.currentLevel = currentLevel;
  }

  public byte getCurrentProfile() {
    return currentProfile;
  }

  public void setCurrentProfile(byte currentProfile) {
    this.currentProfile = currentProfile;
  }

  public Vegetable getProtag() {
    return protag;
  }

  public void setProtag(Vegetable protag) {
    this.protag = protag;
  }

  public InformationBar getInfoBar() {
    return infoBar;
  }

  public void setInfoBar(InformationBar infoBar) {
    this.infoBar = infoBar;
  }

  public Font getFont() {
    return font;
  }

  public void setFont(int size) {
    this.font = new Font("Brady Bunch Remastered", size);
    this.getGc().setFont(this.font);
  }

  public byte getSelection() {
    return selection;
  }

  public void setSelection(byte selection) {
    this.selection = selection;
  }

  public byte getTick() {
    return tick;
  }

  public void setTick(byte tick) {
    this.tick = tick;
  }

  public byte getState() {
    return state;
  }

  public void setState(byte state) {
    this.state = state;
  }

  public byte getFps() {
    return fps;
  }

  public void setFps(byte fps) {
    this.fps = fps;
  }

  public byte getLevelsUnlocked() {
    return levelsUnlocked;
  }

  public void setLevelsUnlocked(byte levelsUnlocked) {
    this.levelsUnlocked = levelsUnlocked;
  }

  public SoundManager getSoundManager() {
    return soundManager;
  }

  public void setSoundManager(SoundManager soundManager) {
    this.soundManager = soundManager;
  }

}
