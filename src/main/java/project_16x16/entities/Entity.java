package project_16x16.entities;

import processing.core.PVector;
import project_16x16.DebugType;
import project_16x16.ISideScroller;
import project_16x16.Utility;
import project_16x16.objects.Collidable;
import project_16x16.objects.CollidableObject;
import project_16x16.objects.EditableObject;
import project_16x16.scene.GameplayScene;

public abstract class Entity extends CollidableObject {

    static final int OUT_OF_BOUNDS_DISTANCE = 2000;
    EntityState state;
    int collisionRange = 145;
    final PVector velocity = new PVector(0, 0);
    float gravity;
    int speedWalk;
    int speedJump;

    public Entity(ISideScroller sideScroller, GameplayScene scene){
        super(sideScroller,scene);
        state = new EntityState();
    }

    public void checkForCollision(){
        for (EditableObject possibleCollision : gameplayScene.objects) {
            if (possibleCollision.equals(this)) {
                continue;
            }
            if (possibleCollision instanceof Collidable) {
                checkForCollision((Collidable) possibleCollision);
            }
        }
    }

    public void checkForCollision(Collidable collision){
        if (Utility.fastInRange(position, collision.getPosition(), collisionRange)) { // In Player Range
            if (applet.getDebug() == DebugType.ALL) {
                applet.strokeWeight(2);
                applet.rect(collision.getPosition().x, collision.getPosition().y, collision.getWidth(), collision.getHeight());
                applet.fill(255, 0, 0);
                applet.ellipse(collision.getPosition().x, collision.getPosition().y, 5, 5);
                applet.noFill();
            }
            if (collidesFutureX(collision)) {
                // player left of collision
                if (position.x < collision.getPosition().x) {
                    position.x = collision.getPosition().x - collision.getWidth() / 2 - width / 2;
                    // player right of collision
                }
                else {
                    position.x = collision.getPosition().x + collision.getWidth() / 2 + width / 2;
                }
                velocity.x = 0;
                state.dashing = false;
            }
            if (collidesFutureY(collision)) {
                // player above collision
                if (position.y < collision.getPosition().y) {
                    if (state.flying) {
                        state.landing = true;
                    }
                    position.y = collision.getPosition().y - collision.getHeight() / 2 - height / 2;
                    state.flying = false;
                    // player below collision
                }
                else {
                    position.y = collision.getPosition().y + collision.getHeight() / 2 + height / 2;
                    state.jumping = false;
                }
                velocity.y = 0;
            }
        }
    }

    boolean collidesFutureX(Collidable collision) {
        return (position.x + velocity.x + width / 2 > collision.getPosition().x - collision.getWidth() / 2
                && position.x + velocity.x - width / 2 < collision.getPosition().x + collision.getWidth() / 2)
                && (position.y + 0 + height / 2 > collision.getPosition().y - collision.getHeight() / 2
                && position.y + 0 - height / 2 < collision.getPosition().y + collision.getHeight() / 2);
    }

    boolean collidesFutureY(Collidable collision) {
        return (position.x + 0 + width / 2 > collision.getPosition().x - collision.getWidth() / 2
                && position.x + 0 - width / 2 < collision.getPosition().x + collision.getWidth() / 2)
                && (position.y + velocity.y + height / 2 > collision.getPosition().y - collision.getHeight() / 2
                && position.y + velocity.y - height / 2 < collision.getPosition().y + collision.getHeight() / 2);
    }


    public PVector getVelocity() {
        return velocity;
    }

    public PVector getPosition() {
        return position;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public EntityState getState() {
        return state;
    }

}
