package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 20;
    public static final int HEIGHT = 15;
    
    private GraphicsContext gc;
    private Canvas canvas;
    private List<Entity> entities = new ArrayList<>();
    private List<Entity> stillObjects = new ArrayList<>();
    Entity bomberman = new Bomber(1,1,Sprite.player_right.getFxImage(),8);
    Scene scene;
    public int SpriteCounter = 0;
    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

        @Override
        public void start(Stage stage) {
            // Tao Canvas
            canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
            gc = canvas.getGraphicsContext2D();

            // Tao root container
            Group root = new Group();
            root.getChildren().add(canvas);

            // Tao scene
            scene = new Scene(root);

            // Them scene vao stage
            stage.setScene(scene);
            stage.show();
            entities.add(bomberman);
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    render();
                    update();
                }
            };
            timer.start();

            createMap();
        }

        public void createMap() {
            for (int i = 0; i < WIDTH; i++) {
                for (int j = 0; j < HEIGHT; j++) {
                    Entity object;
                    if (j == 0 || j == HEIGHT - 1 || i == 0 || i == WIDTH - 1) {
                        object = new Wall(i, j, Sprite.wall.getFxImage());
                    }
                    else {
                        object = new Grass(i, j, Sprite.grass.getFxImage());
                    }
                    stillObjects.add(object);
                }
            }
        }

        public void update() {
            entities.forEach(g -> {
                g.update();
                //System.out.println(g.getX());
                //System.out.println(g.getY());
            });
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    bomberman.counter ++;
                    SpriteCounter++;
                    if(bomberman.counter == 3)bomberman.counter = 0;
                    switch (keyEvent.getCode()){
                        case W:
                            bomberman.up = true;
                            bomberman.setY(-bomberman.speed);
                            break;
                        case S:
                            bomberman.down = true;
                            bomberman.setY(bomberman.speed);
                            break;
                        case A:
                            bomberman.left = true;
                            bomberman.setX(-bomberman.speed);
                            break;
                        case D:
                            bomberman.right = true;
                            bomberman.setX(bomberman.speed);
                            break;
                    }
                    SpriteCounter = 0;
                    if(bomberman.up == true){
                        if(bomberman.counter == 0)bomberman.setImage(Sprite.player_up.getFxImage());
                        else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_up_1.getFxImage());
                        else bomberman.setImage(Sprite.player_up_2.getFxImage());
                    }
                    if(bomberman.down == true){
                        if(bomberman.counter == 0)bomberman.setImage(Sprite.player_down.getFxImage());
                        else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_down_1.getFxImage());
                        else bomberman.setImage(Sprite.player_down_2.getFxImage());
                    }
                    if(bomberman.left == true){
                        if(bomberman.counter == 0)bomberman.setImage(Sprite.player_left.getFxImage());
                        else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_left_1.getFxImage());
                        else bomberman.setImage(Sprite.player_left_2.getFxImage());
                    }
                    if(bomberman.right == true){
                        if(bomberman.counter == 0)bomberman.setImage(Sprite.player_right.getFxImage());
                        else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_right_1.getFxImage());
                        else bomberman.setImage(Sprite.player_right_2.getFxImage());
                    }
                }
            });
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()){
                        case W:
                            bomberman.up = false;
                            break;
                        case S:
                            bomberman.down = false;
                            break;
                        case A:
                            bomberman.left = false;
                            break;
                        case D:
                            bomberman.right = false;
                            break;
                    }
                }
            });
        }

        public void render() {
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            stillObjects.forEach(g -> {
                g.render(gc);
            });
            entities.forEach(g -> g.render(gc));
        }
}
