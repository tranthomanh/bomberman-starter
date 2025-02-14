package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSOutput;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.graphics.SpriteSheet;
import uet.oop.bomberman.items.Item;
import uet.oop.bomberman.items.Portal;
import uet.oop.bomberman.items.Speed;
import uet.oop.bomberman.items.WallPass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    public int worldWidth;
    public int worldHeight;
    
    private GraphicsContext gc;
    private Canvas canvas;
    public List<Entity> entities = new ArrayList<>();
    public List<Entity> stillObjects = new ArrayList<>();
    public List<Entity> minvoList = new ArrayList<>();
    public List<Entity> dollList = new ArrayList<>();
    Entity bomberman = new Bomber(0, 0 , Sprite.player_up.getFxImage(), 0, this);
    public Entity[][] entity;
    Scene scene;
    public int SpriteCounter = 0;
    public int numBomb = 1;
    private long curTime;
    public Bom bom = new Bom();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public boolean collision = false;
    public boolean hasBomb = false;
    public boolean bombIsPlanted = false;
    public boolean flameItem = false;
    public boolean isWallPass = false;
    public boolean isWin = false;
    public Item[][] items = new Item[14][32];
    public ArrayList<Item> itemsList = new ArrayList<>();
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
            AnimationTimer timer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    render();
                    update();
                    try {
                        Thread.sleep(1000 / 60);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            };
            timer.start();

            createMap("/maps/map01.txt");
        }

        public void createMap(String file) {
            try{
                InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = br.readLine();
                worldHeight = worldWidth = 0;
                int stt = 1;
                for(int i=0; i<line.length(); i++){
                    char cur = line.charAt(i);
                    int ch = cur - '0';
                    if(cur == ' '){
                        stt ++;
                    }
                    else if(stt == 1){
                        worldHeight = 10*worldHeight + ch;
                    }
                    else if(stt == 2){
                        worldWidth = 10*worldWidth + ch;
                    }
                    else {
                        bomberman.speed = 10* bomberman.speed + ch;
                    }
                }
                //System.out.println(bomberman.speed);
                entity = new Entity[worldHeight][worldWidth];
                for (int i = 0; i < worldHeight; i++) {
                    line = br.readLine();
                    line.split(" ");
                    for (int j = 0; j < worldWidth; j++) {
                        Entity object;
                        Image img;
                        char cur = line.charAt(j);
                        if(cur == '#'){
                            img = Sprite.wall.getFxImage();
                            object = new Wall(j,i,img, this);
                            stillObjects.add(object);
                            entity[i][j] = object;
                        }
                        else if(cur == 'p'){
                            bomberman.setX(j*Sprite.SCALED_SIZE);
                            bomberman.setY(i*Sprite.SCALED_SIZE);
                            bomberman.setImage(Sprite.player_up.getFxImage());
                            bomberman.speed = 4;
                            img = Sprite.grass.getFxImage();
                            object = new Grass(j,i,img, this);
                            stillObjects.add(object);
                            entity[i][j] = object;
                        }
                        else if(cur == '*'){
                            img = Sprite.brick.getFxImage();
                            object = new Brick(j,i,img, this);
                            stillObjects.add(object);
                            entity[i][j] = object;
                        }
                        else if(cur == '1'){
                            img = Sprite.doll_left1.getFxImage();
                            Entity doll = new Doll(j,i,img, 4, this);
                            img = Sprite.grass.getFxImage();
                            object = new Grass(j,i,img, this);
                            stillObjects.add(object);
                            entities.add(doll);
                            entity[i][j] = object;
                            dollList.add(doll);
                        }
                        else if(cur == '2'){
                            img = Sprite.minvo_left1.getFxImage();
                            Entity minvo = new Minvo(j,i, img, 8, this);
                            img = Sprite.grass.getFxImage();
                            object = new Grass(j,i,img, this);
                            stillObjects.add(object);
                            entities.add(minvo);
                            entity[i][j] = object;
                            minvoList.add(minvo);
                        }
                        else if(cur == 'P'){
                            Item portal = new Portal(j, i);
                            items[i][j] = portal;
                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage(), this);
                            stillObjects.add(brick);
                            itemsList.add(portal);
                            entity[i][j] = brick;
                        }
                        else if(cur == 's'){
                            Item speed = new Speed(j, i);
                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage(), this);
                            items[i][j] = speed;
                            itemsList.add(speed);
                            entity[i][j] = brick;
                            stillObjects.add(brick);
                        }
                        else if(cur == 'w'){
                            Item wallPass = new WallPass(j, i);
                            Entity brick = new Brick(j, i, Sprite.brick.getFxImage(), this);
                            items[i][j] = wallPass;
                            itemsList.add(wallPass);
                            stillObjects.add(brick);
                            entity[i][j] = brick;
                        }
                        else {
                            img = Sprite.grass.getFxImage();
                            object = new Grass(j, i, img, this);
                            stillObjects.add(object);
                            entity[i][j] = object;
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        }

        public void update() {
            if(isWin)return;
            ArrayList<Entity> deleteList = new ArrayList<>();
            entities.forEach(g -> {
                if(g.isLive == false && g.countDead == 0){
                    deleteList.add(g);
                }
            });
            deleteList.forEach(g -> {
               entities.remove(g);
            });
            if(bomberman.isLive == true){
                entities.forEach(g -> {
                    g.update(this);
                });
            }
            bomberman.update(this);
            sceneSetKey(this);
            if(bombIsPlanted){
                if(bom.checkBom(this, curTime, flameItem, entity)){
                    bombIsPlanted = false;
                    bom.deleteBom(this);
                    numBomb++;
                }
            }
        }
        public void sceneSetKey(BombermanGame gp){
            if(gp.bomberman.isLive == false)return;
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    bomberman.collision = false;
                    switch (keyEvent.getCode()){
                        case W:
                            bomberman.direction = "up";
                            break;
                        case S:
                            bomberman.direction = "down";
                            break;
                        case A:
                            bomberman.direction = "left";
                            break;
                        case D:
                            bomberman.direction = "right";
                            break;
                        case SPACE:
                            hasBomb = true;
                            bom = new Bom(bomberman.getX(), bomberman.getY(), Sprite.bomb.getFxImage(), gp);
                            break;
                    }
                    if(hasBomb){
                        bombIsPlanted = true;

                        if(bombIsPlanted && System.currentTimeMillis() - curTime > 1000 && numBomb > 0) {
                            bom.createBom(gp);
                            numBomb--;
                        }
                        curTime = System.currentTimeMillis();
                    }
                    if(bomberman.direction == null)return;
                    if(isWallPass == false)collisionChecker.checkTile(bomberman);
                    if(bomberman.collision == false && bomberman.isLive == true && isWin == false){
                        bomberman.counter ++;
                        if(bomberman.counter == 3)bomberman.counter = 0;
                        switch (bomberman.direction){
                            case "up":
                                bomberman.setY(-bomberman.speed);
                                if(bomberman.counter == 0)bomberman.setImage(Sprite.player_up.getFxImage());
                                else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_up_1.getFxImage());
                                else bomberman.setImage(Sprite.player_up_2.getFxImage());
                                break;
                            case "down":
                                bomberman.setY(bomberman.speed);
                                if(bomberman.counter == 0)bomberman.setImage(Sprite.player_down.getFxImage());
                                else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_down_1.getFxImage());
                                else bomberman.setImage(Sprite.player_down_2.getFxImage());
                                break;
                            case "left":
                                bomberman.setX(-bomberman.speed);
                                if(bomberman.counter == 0)bomberman.setImage(Sprite.player_left.getFxImage());
                                else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_left_1.getFxImage());
                                else bomberman.setImage(Sprite.player_left_2.getFxImage());
                                break;
                            case "right":
                                bomberman.setX(bomberman.speed);
                                if(bomberman.counter == 0)bomberman.setImage(Sprite.player_right.getFxImage());
                                else if(bomberman.counter == 1)bomberman.setImage(Sprite.player_right_1.getFxImage());
                                else bomberman.setImage(Sprite.player_right_2.getFxImage());
                                break;
                        }
                    }
                }
            });
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    switch (keyEvent.getCode()){
                        case W:
                            bomberman.direction = null;
                            break;
                        case S:
                            bomberman.direction = null;
                            break;
                        case A:
                            bomberman.direction = null;
                            break;
                        case D:
                            bomberman.direction = null;
                            break;
                        case SPACE:
                            hasBomb = false;
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
            itemsList.forEach(g -> {
                g.render(gc);
                if(g.isRender) System.out.println(g);
            });
            //if(hasBomb)bom.render(gc);
            entities.forEach(g -> g.render(gc));
            bomberman.render(gc);
        }
}
