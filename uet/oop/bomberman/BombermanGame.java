package uet.oop.bomberman;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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
import uet.oop.bomberman.entities.movingObj.Bomber;
import uet.oop.bomberman.entities.movingObj.enemy.Balloon;
import uet.oop.bomberman.entities.movingObj.enemy.Oneal;
import uet.oop.bomberman.entities.stillObj.Bomb;
import uet.oop.bomberman.entities.stillObj.BombItem;
import uet.oop.bomberman.entities.stillObj.Brick;
import uet.oop.bomberman.entities.stillObj.FlameItem;
import uet.oop.bomberman.entities.stillObj.Grass;
import uet.oop.bomberman.entities.stillObj.Portal;
import uet.oop.bomberman.entities.stillObj.SpeedItem;
import uet.oop.bomberman.entities.stillObj.Wall;
import uet.oop.bomberman.graphics.Sprite;

public class BombermanGame extends Application {

    public static boolean gameOver = false;
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public static Bomber bomber;

    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();
    private static List<Entity> upgrades = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Handle key events
        canvas.setFocusTraversable(true);
        canvas.requestFocus();
        canvas.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.D) {
                bomber.setDirection("right");
            }

            if (e.getCode() == KeyCode.A) {
                bomber.setDirection("left");
            }

            if (e.getCode() == KeyCode.W) {
                bomber.setDirection("up");
            }

            if (e.getCode() == KeyCode.S) {
                bomber.setDirection("down");
            }

            if (e.getCode() == KeyCode.SPACE) {
                if (!Bomb.existed) {
                    Bomb bomb = new Bomb(bomber.getX() / 32, bomber.getY() / 32, Sprite.bomb.getFxImage());
                    stillObjects.add(bomb);
                    Bomb.existed = true;
                }

            }
        });

        canvas.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.A) {
                bomber.setDirection("none");
            }
            if (e.getCode() == KeyCode.S) {
                bomber.setDirection("none");
            }
            if (e.getCode() == KeyCode.D) {
                bomber.setDirection("none");
            }
            if (e.getCode() == KeyCode.W) {
                bomber.setDirection("none");
            }
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
            FileReader map = new FileReader("uet/oop/bomberman/res/levels/lvl1.txt");
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
                            bomber = new Bomber(j, i, Sprite.player_right.getFxImage());
                            entities.add(bomber);
                        } else if (content.charAt(j) == '1') {
                            Entity balloon = new Balloon(j, i, Sprite.balloom_left1.getFxImage());
                            entities.add(balloon);
                        } else if (content.charAt(j) == '2') {
                            Entity oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage(),bomber);
                            entities.add(oneal);
                        } else if (content.charAt(j) == 'b') {
                            Entity bombItem = new BombItem(j, i, Sprite.powerup_bombs.getFxImage());
                            upgrades.add(bombItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(j) == 'f') {
                            Entity flameItem = new FlameItem(j, i, Sprite.powerup_flames.getFxImage());
                            upgrades.add(flameItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(j) == 's') {
                            Entity speedItem = new SpeedItem(j, i, Sprite.powerup_speed.getFxImage());
                            upgrades.add(speedItem);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(j) == 'x') {
                            Entity portal = new Portal(j, i, Sprite.portal.getFxImage());
                            stillObjects.add(portal);

                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        } else if (content.charAt(j) == '*') {
                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage());
                            stillObjects.add(brick);
                        }
                    }
                }
            }

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFound");
            e.printStackTrace();
        }

    }

    public void update() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
            if (entities.get(i).isRemoved()) {
                entities.remove(i);
            }
        }

        for (int i = 0; i < stillObjects.size(); i++) {
            stillObjects.get(i).update();
            if (stillObjects.get(i).isRemoved()) {
                stillObjects.remove(i);
            }
        }
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        upgrades.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));

    }

    public static List<Entity> getEntities() {
        return entities;
    }

    public static List<Entity> getStillObjects() {
        return stillObjects;
    }

    public static Entity getAt(int x, int y) {
        for (Entity e : entities) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }

        for (Entity e : stillObjects) {
            if (e instanceof Grass) {
                continue;
            }

            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }

        for (Entity e : upgrades) {
            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }

        return null;
    }
}
