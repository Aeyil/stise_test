package project_16x16.objects;

import processing.core.PVector;
import project_16x16.ISideScroller;
import project_16x16.scene.GameplayScene;

public abstract class CollidableObject extends EditableObject implements Collidable{

    public String flag;
    public CollidableObject(ISideScroller sideScroller, GameplayScene gameplayScene) {
        super(sideScroller, gameplayScene);
        flag = "";
        position = new PVector(0, 0);
    }

    public boolean collidesWith(Collidable collidable){
        return (collidable.getPosition().x + collidable.getWidth() / 2 > position.x - width / 2
                && collidable.getPosition().x - collidable.getWidth() / 2 < position.x + width / 2)
                && (collidable.getPosition().y + collidable.getHeight() / 2 > position.y - height / 2
                && collidable.getPosition().y - collidable.getHeight() / 2 < position.y + height / 2);
    }

    @Override
    public PVector getPosition() {
        return position;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
