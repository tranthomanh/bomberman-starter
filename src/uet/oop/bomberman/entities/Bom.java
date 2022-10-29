package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Bom extends Entity{
    Flame[][] flames = new Flame[4][2];

    public Bom(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img, 0);
        x = xUnit;
        y = yUnit;
        for(int i=0; i<4; i++)
            for(int j=0; j<2; j++)flames[i][j] = new Flame(0, 0, Sprite.explosion_horizontal2.getFxImage());
        initFlame();
    }

    public Bom() {
        isLive = false;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }

    @Override
    public void update(BombermanGame gp) {
        if(BomCount > 0)BomCount--;
        if(BomCount == 0)isLive = false;
    }

    public void createBom(BombermanGame gp) {
        BomCount = 140;
        isLive = true;
        gp.entities.add(this);
        for(int i=0; i<4; i++)
            for(int j=0; j<2; j++)gp.entities.add(flames[i][j]);
    }
    public void deleteBom(BombermanGame pane){
        pane.entities.remove(this);
        for(int i=0; i<4; i++)
            for(int j=0; j<2; j++)pane.entities.remove(flames[i][j]);
    }

    public boolean checkBom(BombermanGame pane,long startTime,boolean flameItem, Entity entity[][]){
        if(BomCount == 0)return true;
        if(BomCount>=100){
            setImage(Sprite.bomb_1.getFxImage());
        }
        else if(BomCount>=60){
            setImage(Sprite.bomb_2.getFxImage());
        }
        else {
            if (!flameItem) {
                if (BomCount >= 20) {
                    setImage(Sprite.bomb_exploded.getFxImage());
                    initFlame0();
                    brickBreak0(pane, entity);

                }
                else if (BomCount >= 14) {
                    setImage(Sprite.bomb_exploded1.getFxImage());
                    initFlame1();
                }

                else if (BomCount >= 8) {
                    setImage(Sprite.bomb_exploded2.getFxImage());
                    initFlame2();
                }
                else {
                    initFlame();
                }
            } else {
                if (BomCount >= 20) {

                    setImage(Sprite.bomb_exploded.getFxImage());
                    initFlameBuff0();

                }
                else if (BomCount >= 14) {
                    setImage(Sprite.bomb_exploded1.getFxImage());
                    initFlameBuff1();
                }

                else if (BomCount >= 8) {
                    setImage(Sprite.bomb_exploded2.getFxImage());
                    initFlameBuff2();
                }
                else{
                    initFlame();
                }
            }
        }


        return false;
    }
    //0 up
    //1 down
    //2 left
    //3 right
    public void initFlameBuff0(){
        flames[1][0].setImage(Sprite.explosion_vertical.getFxImage());
        flames[1][0].setx(x);
        flames[1][0].sety(y+32);
        flames[1][1].setImage(Sprite.explosion_vertical_down_last.getFxImage());
        flames[1][1].setx(x);
        flames[1][1].sety(y+64);
        flames[0][0].setImage(Sprite.explosion_vertical.getFxImage());
        flames[0][0].setx(x);
        flames[0][0].sety(y-32);
        flames[0][1].setImage(Sprite.explosion_vertical_top_last.getFxImage());
        flames[0][1].setx(x);
        flames[0][1].sety(y-64);
        flames[2][0].setImage(Sprite.explosion_horizontal.getFxImage());
        flames[2][0].setx(x-32);
        flames[2][0].sety(y);
        flames[2][1].setImage(Sprite.explosion_horizontal_left_last.getFxImage());
        flames[2][1].setx(x-64);
        flames[2][1].sety(y);
        flames[3][0].setImage(Sprite.explosion_horizontal.getFxImage());
        flames[3][0].setx(x+32);
        flames[3][0].sety(y);
        flames[3][1].setImage(Sprite.explosion_horizontal_right_last.getFxImage());
        flames[3][1].setx(x+64);
        flames[3][1].sety(y);

    }
    public void initFlameBuff1(){
        flames[1][0].setImage(Sprite.explosion_vertical1.getFxImage());
        flames[1][1].setImage(Sprite.explosion_vertical_down_last1.getFxImage());
        flames[0][0].setImage(Sprite.explosion_vertical1.getFxImage());
        flames[0][1].setImage(Sprite.explosion_vertical_top_last1.getFxImage());
        flames[2][0].setImage(Sprite.explosion_horizontal1.getFxImage());
        flames[2][1].setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
        flames[3][0].setImage(Sprite.explosion_horizontal1.getFxImage());
        flames[3][1].setImage(Sprite.explosion_horizontal_right_last1.getFxImage());
    }
    public void initFlameBuff2(){
        flames[1][0].setImage(Sprite.explosion_vertical2.getFxImage());
        flames[1][1].setImage(Sprite.explosion_vertical_down_last2.getFxImage());
        flames[0][0].setImage(Sprite.explosion_vertical2.getFxImage());
        flames[0][1].setImage(Sprite.explosion_vertical_top_last2.getFxImage());
        flames[2][0].setImage(Sprite.explosion_horizontal2.getFxImage());
        flames[2][1].setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
        flames[3][0].setImage(Sprite.explosion_horizontal2.getFxImage());
        flames[3][1].setImage(Sprite.explosion_horizontal_right_last2.getFxImage());
    }
    public void initFlame(){
        flames[1][0].setImage(Sprite.explosion_vertical.getFxImage());
        flames[1][0].setx(-50);
        flames[1][0].sety(-50);
        flames[1][1].setImage(Sprite.explosion_vertical_down_last.getFxImage());
        flames[1][1].setx(-50);
        flames[1][1].sety(-50);
        flames[0][0].setImage(Sprite.explosion_vertical.getFxImage());
        flames[0][0].setx(-50);
        flames[0][0].sety(-50);
        flames[0][1].setImage(Sprite.explosion_vertical_top_last.getFxImage());
        flames[0][1].setx(-50);
        flames[0][1].sety(-50);
        flames[2][0].setImage(Sprite.explosion_horizontal.getFxImage());
        flames[2][0].setx(-50);
        flames[2][0].sety(-50);
        flames[2][1].setImage(Sprite.explosion_horizontal_left_last.getFxImage());
        flames[2][1].setx(-50);
        flames[2][1].sety(-50);
        flames[3][0].setImage(Sprite.explosion_horizontal.getFxImage());
        flames[3][0].setx(-50);
        flames[3][0].sety(-50);
        flames[3][1].setImage(Sprite.explosion_horizontal_right_last.getFxImage());
        flames[3][1].setx(-50);
        flames[3][1].sety(-50);
    }
    public void initFlame0(){
        flames[1][1].setImage(Sprite.explosion_vertical_down_last.getFxImage());
        flames[1][1].setx(x);
        flames[1][1].sety(y+32);
        flames[0][1].setImage(Sprite.explosion_vertical_top_last.getFxImage());
        flames[0][1].setx(x);
        flames[0][1].sety(y-32);
        flames[2][1].setImage(Sprite.explosion_horizontal_left_last.getFxImage());
        flames[2][1].setx(x-32);
        flames[2][1].sety(y);
        flames[3][1].setImage(Sprite.explosion_horizontal_right_last.getFxImage());
        flames[3][1].setx(x+32);
        flames[3][1].sety(y);

    }
    public void initFlame1(){
        flames[1][1].setImage(Sprite.explosion_vertical_down_last1.getFxImage());
        flames[0][1].setImage(Sprite.explosion_vertical_top_last1.getFxImage());
        flames[2][1].setImage(Sprite.explosion_horizontal_left_last1.getFxImage());
        flames[3][1].setImage(Sprite.explosion_horizontal_right_last1.getFxImage());
    }
    public void initFlame2(){
        flames[1][1].setImage(Sprite.explosion_vertical_down_last2.getFxImage());
        flames[0][1].setImage(Sprite.explosion_vertical_top_last2.getFxImage());
        flames[2][1].setImage(Sprite.explosion_horizontal_left_last2.getFxImage());
        flames[3][1].setImage(Sprite.explosion_horizontal_right_last2.getFxImage());
    }
    public void brickBreak0(BombermanGame pane, Entity obj[][]){
        int x =(int) this.x / 32;
        int y = (int) this.y / 32;
        if(obj[y-1][x] instanceof Wall||obj[y-1][x] instanceof Brick){
            flames[0][1].setx(-50);
            flames[0][1].sety(-50);
            if(obj[y-1][x] instanceof Brick){
                pane.entities.remove(obj[y-1][x]);
                Entity oj = new Grass(x, y-1, Sprite.grass.getFxImage());
                oj.miss = 60;
                obj[y-1][x] = oj;
                pane.entities.add(0, oj);
            }
        }
        if(obj[y+1][x] instanceof Wall||obj[y+1][x] instanceof Brick){
            flames[1][1].setx(-50);
            flames[1][1].sety(-50);
            //System.out.println(obj[x][y+1].type);
            if(obj[y+1][x] instanceof Brick){
                pane.entities.remove(obj[y+1][x]);
                Entity oj = new Grass(x, y+1, Sprite.grass.getFxImage());
                oj.miss = 60;
                obj[y+1][x] = oj;
                pane.entities.add(0, oj);
            }
        }
        if(obj[y][x-1] instanceof Wall||obj[y][x-1] instanceof Brick){
            flames[2][1].setx(-50);
            flames[2][1].sety(-50);
            if(obj[y][x-1] instanceof Brick){
                pane.entities.remove(obj[y][x-1]);
                Entity oj = new Grass(x-1, y, Sprite.grass.getFxImage());
                oj.miss = 60;
                obj[y][x-1] = oj;
                pane.entities.add(0, oj);
            }
        }
        if(obj[y][x+1] instanceof Wall||obj[y][x+1] instanceof Brick){
            flames[3][1].setx(-50);
            flames[3][1].sety(-50);
            if(obj[y][x+1] instanceof Brick){
                pane.entities.remove(obj[y][x+1]);
                Entity oj = new Grass(x+1,y,Sprite.grass.getFxImage());
                oj.miss = 60;
                obj[y][x+1] = oj;
                pane.entities.add(0, oj);
            }
        }

    }
}
