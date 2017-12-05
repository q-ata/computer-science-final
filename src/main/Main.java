package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.*;

import enemies.*;
import solids.*;
import types.Coordinates;
import types.Enemy;
import types.MapItem;
import types.Projectile;
import types.Solid;
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
  private static ArrayList<MapItem> mapItems = new ArrayList<MapItem>();
  private static ArrayList<Solid> solids = new ArrayList<Solid>();
  public static int visibleX;
  public static int visibleY;
  
  private static Vegetable protag = Constants.CHARACTERS[0];
  private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
  
  public static byte tick = 1;
  private static byte state = 0;
  public static Image bg;
  
  public static void main(String[] args) {
    
    // Launch javafx application, runs the start method.
    launch(args);
    
  }

  public void start(Stage stage) {
    
    try {
      
      // Create a new 1000 by 600 canvas.
      Main.setCanvas(new Canvas(1000, 600));
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
      
      Main.bg = new Image("file:resources/map/bg-1.png");
      Main.setSoundManager(new SoundManager());
      
      // Shows the stage/window.
      stage.show();
      
      // Sets fill color to white.
      gc.setFill(Color.BLACK);
      
      // Create a BufferedReader to read level data.
      String currentDir = new File("").getAbsolutePath();
      BufferedReader levelReader = new BufferedReader(new FileReader(currentDir + "/resources/map/level1.veggietale"));
      // Initialize list for level data.
      ArrayList<String> levelLines = new ArrayList<String>();
      
      // Read every line from file and add it to levelLines.
      try {
        String line = levelReader.readLine();
        while (line != null) {
          levelLines.add(line);
          line = levelReader.readLine();
        }
      }
      finally {
        levelReader.close();
      }
      
      ArrayList<MapItem> mapItems = Main.getMapItems();
      
      for (int i = 0; i < levelLines.size(); i++) {
        // Split each line by "|" into an array.
        String[] data = levelLines.get(i).split("\\|");
        int type = Integer.parseInt(data[0]);
        Coordinates coords = new Coordinates(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        if (type == 1) {
          mapItems.add(new Brick(coords));
        }
        else if (type == 2) {
          Tomato tomato = new Tomato(coords);
          Main.getMapItems().add(tomato);
          Main.getEnemies().add(tomato);
        }
        else if (type == 3) {
          Knife knife = new Knife(coords);
          Main.getMapItems().add(knife);
          Main.getEnemies().add(knife);
        }
        else if (type == 4) {
          KnifeDown knifeDown = new KnifeDown(coords);
          Main.getMapItems().add(knifeDown);
          Main.getEnemies().add(knifeDown);
        }
        else if (type == 5) {
          DoubleTomatoWallLevelOne doubleTomatoWallLevelOne = new DoubleTomatoWallLevelOne(coords, new int[] {Integer.parseInt(data[3]), Integer.parseInt(data[4])});
          Main.getMapItems().add(doubleTomatoWallLevelOne);
          Main.getEnemies().add(doubleTomatoWallLevelOne);
        }
        else if (type == 6) {
          Orange orange = new Orange(coords, Integer.parseInt(data[3]) == 1 ? true : false, Integer.parseInt(data[4]));
          Main.getMapItems().add(orange);
          Main.getEnemies().add(orange);
        }
        else if (type == 0) {
          Main.getProtag().x = coords.x;
          Main.getProtag().y = coords.y;
          Main.visibleX = coords.x - 500 + (Main.getProtag().w / 2);
          Main.visibleY = coords.y - 300 + (Main.getProtag().h / 2);
        }
        if (IntStream.of(Constants.SOLIDS).anyMatch((x) -> x == type)) {
          Main.getSolids().add((Solid) mapItems.get(i));
        }
        
      }
      
      for (int i = 0; i < Main.getMapItems().size(); i++) {
        Main.getMapItems().get(i).id = i;
      }
      
      // Creating the gameloop.
      Timeline gameLoop = new Timeline();
      // Loop continues running indefinitely.
      gameLoop.setCycleCount(Timeline.INDEFINITE);
      
      KeyFrame keyframe = new KeyFrame(
        // Running EventHandler.handle every 0.016 seconds, or 60 times a second.
        Duration.seconds(0.016),
        new EventHandler<ActionEvent>() {

          public void handle(ActionEvent event) {
            
            // Everything here is run 60 times a second.
            
            if (Main.state == 0) {
              // Update state of all objects (collision, map item x/y location)
              StateUpdate.update();
              // Render everything to the screen.
              Render.render();
            }
            else if (Main.state == 1) {
              System.out.println("DEAD.");
              Main.setState((byte) 0);
              // Main.getGc().drawImage(new Image("file:resources/loser.png"), 0, 0);
            }
            
            // Increment tick. Reset if 60.
            tick = (byte) (tick == 60 ? 1 : tick + 1);
            
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

  public static ArrayList<MapItem> getMapItems() {
    return mapItems;
  }

  public static void setMapItems(ArrayList<MapItem> mapItems) {
    Main.mapItems = mapItems;
  }

  public static Vegetable getProtag() {
    return protag;
  }

  public static void setProtag(Vegetable protag) {
    Main.protag = protag;
  }

  public static ArrayList<Solid> getSolids() {
    return solids;
  }

  public static ArrayList<Projectile> getProjectiles() {
    return projectiles;
  }

  public static void appendProjectile(Projectile projectile) {
    Main.projectiles.add(projectile);
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

  public static ArrayList<Enemy> getEnemies() {
    return enemies;
  }

  public static void setEnemies(ArrayList<Enemy> enemies) {
    Main.enemies = enemies;
  }

  public static SoundManager getSoundManager() {
    return soundManager;
  }

  public static void setSoundManager(SoundManager soundManager) {
    Main.soundManager = soundManager;
  }

}
