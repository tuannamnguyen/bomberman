package uet.oop.bomberman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObj.Balloon;
import uet.oop.bomberman.entities.movingObj.Bomber;
import uet.oop.bomberman.entities.movingObj.Oneal;
import uet.oop.bomberman.entities.staticObj.BombItem;
import uet.oop.bomberman.entities.staticObj.Brick;
import uet.oop.bomberman.entities.staticObj.FlameItem;
import uet.oop.bomberman.entities.staticObj.Grass;
import uet.oop.bomberman.entities.staticObj.Portal;
import uet.oop.bomberman.entities.staticObj.SpeedItem;
import uet.oop.bomberman.entities.staticObj.Wall;
import uet.oop.bomberman.graphics.Sprite;


public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    public static HashSet<KeyCode> currentlyPressedKey = new HashSet<>();

    private GraphicsContext gc;
    private Canvas canvas;
    public static final long startNanoTime = System.nanoTime();
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        //Handle key events
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        canvas.setOnKeyPressed(e -> {
            currentlyPressedKey.add(e.getCode());
        });
        canvas.setOnKeyReleased(e -> {
            currentlyPressedKey.remove(e.getCode());
        });

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        createMap();
        
        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

        
        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.setTitle("Bomberman Game");
        stage.show();
    }

    public void createMap() {
        try {
            FileReader map = new FileReader("res\\levels\\lvl1.txt");
            Scanner fileReader = new Scanner(map);
            
            for (int i = 0; fileReader.hasNextLine(); i++) {
                String content = fileReader.nextLine();
                for (int j = 0; j < content.length(); j++) {

                    if (content.charAt(j) == '#') {
                        Entity wall = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(wall);
                    } else {
                        Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        
                        if (content.charAt(j) == 'p') {
                            Entity bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                            entities.add(bomber);
                        } else if (content.charAt(j) == '1') {
                            Entity balloon = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                            entities.add(balloon);
                        } else if (content.charAt(j) == '2') {
                            Entity oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage());
                            entities.add(oneal);
                        } else if (content.charAt(j) == 'b') {
                            Entity bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                            stillObjects.add(bombItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(j) == 'f') {
                            Entity flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            stillObjects.add(flameItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(j) == 's') {
                            Entity speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            stillObjects.add(speedItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(j) == 'x') {
                            Entity portal = new Portal(j, i, Sprite.portal.getFxImage());
                            stillObjects.add(portal);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFound");
            e.printStackTrace();
        }
        
        
    }

    public void update() {
        entities.forEach(Entity::update);
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Entity> getStillObjects() {
        return stillObjects;
    }
    
    public BombermanGame currentGame() {
        return this;
    }
}
