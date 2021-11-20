package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Balloon;
import uet.oop.bomberman.entities.BombItem;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.FlameItem;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Oneal;
import uet.oop.bomberman.entities.SpeedItem;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class BombermanGame extends Application {

    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;

    private GraphicsContext gc;
    private Canvas canvas;
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
        Scene scene = new Scene(root, 1280, 720);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();
    }

    public void createMap() {
        try {
            File map = new File("src\\resources\\levels\\lvl1.txt");
            Scanner fileReader = new Scanner(map);
            
            for (int i = 1; fileReader.hasNextLine(); i++) {
                String content = fileReader.nextLine();
                for (int j = 0; j < content.length(); j++) {
                    if (content.charAt(i) == '#') {
                        Entity wall = new Wall(j, i, Sprite.wall.getFxImage());
                        stillObjects.add(wall);
                    } else {
                        Entity grass = new Grass(j, i, Sprite.grass.getFxImage());
                        stillObjects.add(grass);
                        
                        if (content.charAt(i) == 'p') {
                            Entity bomber = new Bomber(j, i, Sprite.player_right_1.getFxImage());
                            entities.add(bomber);
                        } else if (content.charAt(i) == '1') {
                            Entity balloon = new Balloon(j, i, Sprite.balloom_right1.getFxImage());
                            entities.add(balloon);
                        } else if (content.charAt(i) == '2') {
                            Entity oneal = new Oneal(j, i, Sprite.oneal_right1.getFxImage());
                            entities.add(oneal);
                        } else if (content.charAt(i) == 'b') {
                            Entity bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                            stillObjects.add(bombItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(i) == 'f') {
                            Entity flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            stillObjects.add(flameItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(i) == 's') {
                            Entity speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            stillObjects.add(speedItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFound");
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
}
