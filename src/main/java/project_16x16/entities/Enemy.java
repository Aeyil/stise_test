package project_16x16.entities;

import java.awt.event.KeyEvent;

import processing.core.PImage;
import processing.core.PVector;
import processing.data.JSONObject;
import project_16x16.ISideScroller;
import project_16x16.DebugType;
import project_16x16.Tileset;
import project_16x16.Utility;
import project_16x16.objects.CollidableObject;
import project_16x16.objects.EditableObject;
import project_16x16.scene.GameplayScene;

/**
 * <h1>Enemy Class</h1>
 * <p>
 * This class handles the enemy parent behavior.
 * </p>
 */
public class Enemy extends Entity {

	private PImage image;

	public int health;

	/**
	 * Constructor
	 * 
	 * @param sideScroller SideScroller game controller.
	 */
	public Enemy(ISideScroller sideScroller, GameplayScene gameplayScene, int health, float gravity, int speedWalk, int speedJump,
				 int width, int height, int collisionRange) {
		super(sideScroller, gameplayScene);
		image = Tileset.getTile(0, 258, 14, 14, 4);
		this.health = health; //2
		this.gravity = gravity; //1
		this.speedWalk = speedWalk; //7
		this.speedJump = speedJump; //18
		this.width = width;
		this.height = height;
		this.collisionRange = collisionRange;
	}

	/**
	 * The display method controls how to display the character to the screen with
	 * what animation.
	 */
	public void display() {
		applet.pushMatrix();
		applet.translate(position.x, position.y);
		if (state.facingDir == LEFT) {
			applet.scale(-1, 1);
		}
		applet.image(image, 0, 0);
		applet.noTint();
		applet.popMatrix();
		if (applet.getDebug() == DebugType.ALL) {
			applet.strokeWeight(1);
			applet.stroke(0, 255, 200);
			applet.noFill();
			applet.rect(position.x, position.y, width, height); // display player bounding box
		}
	}

	/**
	 * The update method handles updating the character.
	 */
	public void update() {
		// velocity.set(0, velocity.y + gravity);

		checkForCollision();
		if (velocity.y != 0) {
			state.flying = true;
		}
		position.add(velocity);
		if (position.y > OUT_OF_BOUNDS_DISTANCE) { // out of bounds check
			// Destroy(gameObject);
		}
		if (applet.getDebug() == DebugType.ALL) {
			applet.noFill();
			applet.stroke(255, 0, 0);
			applet.strokeWeight(1);
			applet.ellipse(position.x, position.y, collisionRange * 2, collisionRange * 2);
		}
	}

	@Override
	public void debug() {
		// TODO Auto-generated method stub
	}

	@Override
	public JSONObject exportToJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}
