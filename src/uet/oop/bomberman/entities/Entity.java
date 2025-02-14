package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int y;
    protected Image img;
    public int spriteCounter = 0;
    public int miss = 0;
    public int add = 1;
    public int counter = 0;
    public int speed = 0;
    public Rectangle solidArea;
    public String direction = null;
    public int type = 0;
    public int BomCount = 0;
    public int countDead = 0;
    public boolean collision = false;
    public boolean isLive = true;
    BombermanGame pane;
    public Entity(){

    }
    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img, int speed, BombermanGame pane) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.speed = speed;
        this.pane = pane;
    }
    public void setImage(Image img){
        this.img = img;
    }

    public void setX(int x) {
        this.x += x;
    }

    public void setY(int y) {
        this.y += y;
    }
    public void setx(int x){ this.x = x;}
    public void sety(int y){ this.y = y;}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Image getImg(){
        return img;
    }
    public void checkLive(){

    }
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update(BombermanGame gp);
    public void updateDead(BombermanGame gp){

    }
}
