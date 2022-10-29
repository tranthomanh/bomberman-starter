package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

public class Doll extends Entity{
    int counter = 0;
    int type = 0;
    public Doll(int xUnit, int yUnit, Image img, int speed) {
        super(xUnit, yUnit, img, speed);
        solidArea = new Rectangle(0,  0, Sprite.SCALED_SIZE - 1, Sprite.SCALED_SIZE - 1);
        type = 1;
    }

    @Override
    public void update(BombermanGame gp) {
        if(direction == null)direction = "up";
        spriteCounter++;
        if(spriteCounter == 20){
            //System.out.println(getX() + " " + getY());
            spriteCounter = 0;
            counter+=add;
            if(counter == 5)add = -1;
            else if(counter == 0)add = 1;
            collision = false;
            gp.collisionChecker.checkTile(this);
            boolean ok = true;
            if(collision){
                boolean[] b = new boolean[4];
                 b[0] = gp.collisionChecker.canMoveUp(this);
                 b[1] = gp.collisionChecker.canMoveDown(this);
                 b[2] = gp.collisionChecker.canMoveLeft(this);
                 b[3] = gp.collisionChecker.canMoveRight(this);
                ArrayList<Integer> arrayList = new ArrayList<>();
                 for(int i=0; i<4; i++)if(b[i])arrayList.add(i);
                 if(arrayList.size() == 0){
                     ok = false;
                 }
                 else{
                     int num = arrayList.size();
                     int cur = (int)(Math.random() * num);
                     switch (arrayList.get(cur)){
                         case 0:
                             direction = "up";
                             break;
                         case 1:
                             direction = "down";
                             break;
                         case 2:
                             direction = "left";
                             break;
                         case 3:
                             direction = "right";
                             break;
                     }
                 }
            }
            //System.out.println(ok);
            if(ok == false)return;
            switch (direction){
                case "up":
                    setY(-speed);
                    break;
                case "down":
                    setY(speed);
                    break;
                case "left":
                    setX(-speed);
                    break;
                case "right":
                    setX(speed);
                    break;
            }
        }
    }

    @Override
    public void render(GraphicsContext gc) {

        if(counter == 40){
            counter = 0;
            type = 1 - type;
        }
        counter++;
        if(type == 0){
            Sprite sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, counter , 40);
            gc.drawImage(sprite.getFxImage(), x, y);
        }
        else{
            Sprite sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, counter , 40);
            gc.drawImage(sprite.getFxImage(), x, y);
        }
    }
}
