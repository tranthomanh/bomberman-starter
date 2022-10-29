package uet.oop.bomberman;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class CollisionChecker{
    BombermanGame gp;
    public CollisionChecker(BombermanGame gp){
        this.gp = gp;
    }
    public boolean checkVal(int num){
        return num >= 6 && num <= 7;
    }
    public void checkTile(Entity entity){
        boolean ok = entity.getX() == 204 && entity.getY() == 32;
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
        if(checkVal(tileNum1) || checkVal(tileNum2)){
            entity.collision = true;
        }
    }
    public boolean canMoveUp(Entity entity){
        int entityLeftWorldX = entity.getX() + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.getY() + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / Sprite.SCALED_SIZE;
        int entityRightCol = entityRightWorldX / Sprite.SCALED_SIZE;
        int entityTopRow = entityTopWorldY / Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomWorldY / Sprite.SCALED_SIZE;
        int tileNum1, tileNum2;
        entityTopRow = (entityTopWorldY - entity.speed) / Sprite.SCALED_SIZE;
        tileNum1 = gp.entity[entityTopRow][entityLeftCol].type;
        tileNum2 = gp.entity[entityTopRow][entityRightCol].type;
        return checkVal(tileNum1) == false && checkVal(tileNum2) == false;
    }
    public boolean canMoveDown(Entity entity){
        int entityLeftWorldX = entity.getX() + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.getY() + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / Sprite.SCALED_SIZE;
        int entityRightCol = entityRightWorldX / Sprite.SCALED_SIZE;
        int entityTopRow = entityTopWorldY / Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomWorldY / Sprite.SCALED_SIZE;
        int tileNum1, tileNum2;
        entityBottomRow = (entityBottomWorldY + entity.speed) / Sprite.SCALED_SIZE;
        tileNum1 = gp.entity[entityBottomRow][entityLeftCol].type;
        tileNum2 = gp.entity[entityBottomRow][entityRightCol].type;
        return checkVal(tileNum1) == false && checkVal(tileNum2) == false;
    }
    public boolean canMoveLeft(Entity entity){
        int entityLeftWorldX = entity.getX() + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.getY() + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / Sprite.SCALED_SIZE;
        int entityRightCol = entityRightWorldX / Sprite.SCALED_SIZE;
        int entityTopRow = entityTopWorldY / Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomWorldY / Sprite.SCALED_SIZE;
        int tileNum1, tileNum2;
        entityLeftCol= (entityLeftWorldX - entity.speed) / Sprite.SCALED_SIZE;
        tileNum1 = gp.entity[entityTopRow][entityLeftCol].type;
        tileNum2 = gp.entity[entityBottomRow][entityLeftCol].type;
        return checkVal(tileNum1) == false && checkVal(tileNum2) == false;
    }
    public boolean canMoveRight(Entity entity){
        int entityLeftWorldX = entity.getX() + entity.solidArea.x;
        int entityRightWorldX = entityLeftWorldX + entity.solidArea.width;
        int entityTopWorldY = entity.getY() + entity.solidArea.y;
        int entityBottomWorldY = entityTopWorldY + entity.solidArea.height;
        int entityLeftCol = entityLeftWorldX / Sprite.SCALED_SIZE;
        int entityRightCol = entityRightWorldX / Sprite.SCALED_SIZE;
        int entityTopRow = entityTopWorldY / Sprite.SCALED_SIZE;
        int entityBottomRow = entityBottomWorldY / Sprite.SCALED_SIZE;
        int tileNum1, tileNum2;
        entityRightCol = (entityRightWorldX + entity.speed) / Sprite.SCALED_SIZE;
        tileNum1 = gp.entity[entityTopRow][entityRightCol].type;
        tileNum2 = gp.entity[entityBottomRow][entityRightCol].type;
        return checkVal(tileNum1) == false && checkVal(tileNum2) == false;
    }

}
