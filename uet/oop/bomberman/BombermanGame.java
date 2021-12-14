package uet.oop.bomberman;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.movingObj.Bomber;
import uet.oop.bomberman.entities.movingObj.enemy.Balloon;
import uet.oop.bomberman.entities.movingObj.enemy.Enemy;
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
    public static int enemyCount = 0;
    public static int points = 0;
    private int currentLevel = 1;

    private GraphicsContext gc;
    private Canvas canvas;
    private static List<Entity> entities = new ArrayList<>();
    private static List<Entity> stillObjects = new ArrayList<>();

    public static void main(String[] args) {
        Sound.ThemeSound();
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
                if (!this.bombExisted()) {
                    Bomb bomb = new Bomb(bomber.getX() / 32, bomber.getY() / 32, Sprite.bomb.getFxImage());
                    stillObjects.add(bomb);
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

        HBox scoreBar = new HBox();
        Text score = new Text();

        score.setStyle("-fx-font: 24 arial;");
        scoreBar.setStyle("-fx-background-color: #336699;");
        scoreBar.getChildren().add(score);

        // Tao root container
        BorderPane root = new BorderPane();
        score.textProperty().bind(Bindings.createStringBinding(() -> ("Score: " + points)));
        root.setCenter(canvas);
        root.setTop(scoreBar);

        createMap(currentLevel);

        // Game loop
        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (bomber.isRemoved()) {
                    canvas.setDisable(true);
                }

                render();
                update();

                if (levelFinished()) {
                    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    currentLevel++;
                    createMap(currentLevel);
                }
            }
        };
        timer.start();

        // Tao scene
        Scene gameScene = new Scene(root);
        Scene menuScene = new Scene(menu(stage, gameScene));

        // Them scene vao stage
        stage.setScene(menuScene);
        stage.setTitle("Bomberman Game");
        stage.show();
    }

    public void createMap(int level) {
        try {
            FileReader map = new FileReader("src/uet/oop/bomberman/res/levels/lvl" + level +".txt");
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
                            enemyCount++;
                        } else if (content.charAt(j) == '2') {
                            Entity oneal = new Oneal(j, i, Sprite.oneal_left1.getFxImage(), bomber);
                            entities.add(oneal);
                            enemyCount++;
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
                if (entities.get(i) instanceof Enemy) {
                    if(entities.get(i) instanceof Balloon) {
                        Sound.playSound("balloonDeath");
                    } else if (entities.get(i) instanceof Oneal ) {
                        Sound.playSound("skullHeadDeath");
                    }
                    enemyCount--;
                    points += 10;
                    System.out.println(points);
                }
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

        for (int i = stillObjects.size() - 1; i >= 0; i--) {
            Entity e = stillObjects.get(i);
            if (e instanceof Grass) {
                continue;
            }

            if (e.getX() == x && e.getY() == y) {
                return e;
            }
        }

        return null;
    }

    private boolean bombExisted() {
        int count = 0;
        for (Entity e : stillObjects) {
            if (e instanceof Bomb) {
                count++;
            }
        }

        if (count == Bomber.bombCount) {
            return true;
        }

        return false;
    }

    private boolean levelFinished() {
        return Bomber.hitPortal && enemyCount == 0;

    }

    private VBox menu(Stage stage, Scene gameScene) {
        VBox vbox = new VBox();
        try {
            Image background = new Image(new FileInputStream("src/uet/oop/bomberman/res/menu.png"));
            ImageView imageView = new ImageView(background);
            imageView.setFitHeight(431);
            imageView.setFitWidth(768);

            Button play = new Button("Play");
            play.setOnAction(e -> {
                stage.setScene(gameScene);
            });

            vbox.getChildren().addAll(imageView, play);
            vbox.setAlignment(Pos.CENTER);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return vbox;
    }
}
