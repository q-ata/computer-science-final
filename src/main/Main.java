package main;

import java.io.File;
import java.io.FileInputStream;
import java.util.Timer;

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

public class Main extends Application {
  
  // Set canvas, graphics context and scene.
  private static Canvas canvas;
  private static GraphicsContext gc;
  private static Scene scene;
  private static MediaPlayer introPlayer;
  private static SoundManager soundManager;
  
  // Array of all MapItems that might need to be rendered.
  public static int visibleX;
  public static int visibleY;
  private static String currentLevelLocation;
  private static String currentLevelBackground;
  private static Level currentLevel;
  
  private static Vegetable protag = Constants.CHARACTERS[0];
  private static InformationBar infoBar = new InformationBar();
  private static Font font;
  
  private static byte selection = 1;
  private static byte tick = 1;
  private static byte state = 0;
  private static byte fps = 0;
  
  public static void main(String[] args) {
    
    // Launch javafx application, runs the start method.
    launch(args);
    
  }

  public void start(Stage stage) {
    
    try {
      
      // Create a new 1000 by 680 canvas.
      Main.setCanvas(new Canvas(1000, 680));
      Main.setGc(Main.getCanvas().getGraphicsContext2D());
      Group group = new Group();
      group.getChildren().add(Main.getCanvas());
      Scene scene = new Scene(group);
      // Add an event handler for when a key is pressed. KeyPressedHandler.handle is called on key press.
      scene.setOnKeyPressed(new KeyboardPressedHandler());
      // Add an event handler for when a key is released. KeyReleasedHandler.handle is called on key release.
      scene.setOnKeyReleased(new KeyboardReleasedHandler());
      Main.setScene(scene);
      // Sets the contents of the stage to the canvas.
      stage.setScene(Main.getScene());
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
      Main.setIntroPlayer(videoPlayer);
      // Settings for the MediaPlayer.
      videoPlayer.setAutoPlay(true);
      videoPlayer.setVolume(0.5);
      videoPlayer.setOnEndOfMedia(() -> {
        videoPlayer.dispose();
        Main.setState((byte) 1);
      });
      MediaView viewer = new MediaView(videoPlayer);
      viewer.setPreserveRatio(false);
      viewer.setSmooth(true);
      viewer.setFitHeight(600);
      viewer.setFitWidth(1000);
      // Add the video to the scene.
      ((Group) scene.getRoot()).getChildren().add(viewer);
      stage.initStyle(StageStyle.DECORATED);
      
      // Main.bg = new Image("file:resources/map/bg-1.png");
      Main.setSoundManager(new SoundManager());
      Font.loadFont(new FileInputStream(new File("./resources/fonts/BradBunR.ttf")), 24);
      Main.setFont(24);
      Main.getGc().setFont(Main.getFont());
      
      // Shows the stage/window.
      stage.show();
      
      // Sets fill color to white.
      gc.setFill(Color.WHITE);
      
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
            
            if (Main.state == 1) {
              
              Render.renderProfile();
              
            }
            else if (Main.state == 2) {
              
              Render.renderTitle();
              
            }
            else if (Main.state == 3) {
              
              Render.renderCharacterSelect();
              
            }
            else if (Main.state == 4) {
              
              Render.renderLevelSelect();
              
            }
            else if (Main.state == 5) {
              
              Render.renderLevel();
              StateUpdate.update();
              
            }
            
            // Increment tick. Reset if 60.
            setTick((byte) (getTick() == 60 ? 1 : getTick() + 1));
            Main.setFps((byte) (Main.getFps() + 1));
            
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
  
  // Getters and setters.

  public static Canvas getCanvas() {
    return canvas;
  }

  public static void setCanvas(Canvas canvas) {
    Main.canvas = canvas;
  }

  public static GraphicsContext getGc() {
    return gc;
  }

  public static void setGc(GraphicsContext gc) {
    Main.gc = gc;
  }

  public static Scene getScene() {
    return scene;
  }

  public static void setScene(Scene scene) {
    Main.scene = scene;
  }

  public static Vegetable getProtag() {
    return protag;
  }

  public static void setProtag(Vegetable protag) {
    Main.protag = protag;
  }

  public static byte getState() {
    return state;
  }

  public static void setState(byte state) {
    Main.state = state;
  }

  public static MediaPlayer getIntroPlayer() {
    return introPlayer;
  }

  public static void setIntroPlayer(MediaPlayer introPlayer) {
    Main.introPlayer = introPlayer;
  }

  public static SoundManager getSoundManager() {
    return soundManager;
  }

  public static void setSoundManager(SoundManager soundManager) {
    Main.soundManager = soundManager;
  }

  public static InformationBar getInfoBar() {
    return infoBar;
  }

  public static void setInfoBar(InformationBar infoBar) {
    Main.infoBar = infoBar;
  }

  public static Font getFont() {
    return font;
  }

  public static void setFont(int size) {
    Main.font = new Font("Brady Bunch Remastered", size);
    Main.getGc().setFont(Main.font);
  }

  public static byte getFps() {
    return fps;
  }

  public static void setFps(byte fps) {
    Main.fps = fps;
  }

  public static byte getSelection() {
    return selection;
  }

  public static void setSelection(byte selection) {
    Main.selection = selection;
  }

  public static String getCurrentLevelLocation() {
    return currentLevelLocation;
  }

  public static void setCurrentLevelLocation(String currentLevelLocation) {
    Main.currentLevelLocation = currentLevelLocation;
  }

  public static String getCurrentLevelBackground() {
    return currentLevelBackground;
  }

  public static void setCurrentLevelBackground(String currentLevelBackground) {
    Main.currentLevelBackground = currentLevelBackground;
  }

  public static Level getCurrentLevel() {
    return currentLevel;
  }

  public static void setCurrentLevel(Level currentLevel) {
    Main.currentLevel = currentLevel;
  }

  public static byte getTick() {
    return tick;
  }

  public static void setTick(byte tick) {
    Main.tick = tick;
  }

}
