package uet.oop.bomberman;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionChecker{
    BombermanGame gp;
    public CollisionChecker(BombermanGame gp){
        this.gp = gp;
    }
    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.getX() + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.getY() + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / Sprite.SCALED_SIZE;
        int entityRightCol = entityRightWorldX / Sprite.SCALED_SIZE;
        int entityTopRow = entityTopWorldY / Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomWorldY / Sprite.SCALED_SIZE;
        int tileNum1 = 0, tileNum2 = 0;
        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / Sprite.SCALED_SIZE;
                tileNum1 = gp.entity[entityTopRow][entityLeftCol].type;
                tileNum2 = gp.entity[entityTopRow][entityRightCol].type;
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / Sprite.SCALED_SIZE;
                tileNum1 = gp.entity[entityBottomRow][entityLeftCol].type;
                tileNum2 = gp.entity[entityBottomRow][entityRightCol].type;
                break;
            case "left":
                entityLeftCol= (entityLeftWorldX - entity.speed) / Sprite.SCALED_SIZE;
                tileNum1 = gp.entity[entityTopRow][entityLeftCol].type;
                tileNum2 = gp.entity[entityBottomRow][entityLeftCol].type;
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / Sprite.SCALED_SIZE;
                tileNum1 = gp.entity[entityTopRow][entityRightCol].type;
                tileNum2 = gp.entity[entityBottomRow][entityRightCol].type;
                break;
        }
        if(tileNum1 >= 6 && tileNum1 <= 7 || tileNum2 >= 6 && tileNum2 <= 7){
            gp.collision = true;
        }
    }
}
