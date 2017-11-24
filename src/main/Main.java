package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.stream.*;

import solids.*;
import types.Coordinates;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
  
  // Set canvas, graphics context and scene.
  private static Canvas canvas;
  private static GraphicsContext gc;
  private static Scene scene;
  
  // Array of all MapItems that might need to be rendered.
  private static MapItem[] mapItems;
  private static ArrayList<Solid> solids = new ArrayList<Solid>();
  
  private static Vegetable protag = Constants.CHARACTERS[0];
  private static ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
  
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
      stage.setTitle("game");
      // Shows the stage/window.
      stage.show();
      
      // Sets fill color to white.
      gc.setFill(Color.WHITE);
      
      // Create a BufferedReader to read level data.
      String currentDir = new File("").getAbsolutePath();
      BufferedReader levelReader = new BufferedReader(new FileReader(currentDir + "/resources/map/level1.txt"));
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
      
      Main.setMapItems(new MapItem[levelLines.size()]);
      MapItem[] mapItems = Main.getMapItems();
      
      for (int i = 0; i < levelLines.size(); i++) {
        // Split each line by "|" into an array.
        String[] data = levelLines.get(i).split("\\|");
        int type = Integer.parseInt(data[0]);
        Coordinates coords = new Coordinates(Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        if (type == 1) {
          mapItems[i] = new Brick(coords);
        }
        else if (type == 0) {
          Main.getProtag().x = coords.x;
          Main.getProtag().y = coords.y;
          mapItems[i] = Main.getProtag();
        }
        if (IntStream.of(Constants.SOLIDS).anyMatch((x) -> x == type)) {
          Main.getSolids().add((Solid) mapItems[i]);
        }
        
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
            
            // Update state of all objects (collision, map item x/y location)
            StateUpdate.update();
            // Render everything to the screen.
            Render.render();
            
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

  public static MapItem[] getMapItems() {
    return mapItems;
  }

  public static void setMapItems(MapItem[] mapItems) {
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

}
