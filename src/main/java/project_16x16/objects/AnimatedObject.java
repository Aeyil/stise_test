package project_16x16.objects;

import java.util.ArrayList;

import project_16x16.ISideScroller;
import project_16x16.components.AnimationComponent;
import processing.core.PImage;
import project_16x16.scene.GameplayScene;
import project_16x16.Tileset;

public class AnimatedObject extends CollidableObject {

	// Animation Component
	public AnimationComponent animation;

	// Collision Component
	public GameObject collision;

	public PImage image;

	public AnimatedObject(ISideScroller sideScroller, GameplayScene gameplayScene) {
		super(sideScroller, gameplayScene);
		type = ObjectType.OBJECT;
		animation = new AnimationComponent();
	}

	public void display() {
	}

	public void update() {
	}

	public void delete() {
	}
	
	/**
	 * 
	 * @param name the name of a tile
	 * @return the animation of a given tile
	 */
	protected ArrayList<PImage> getAnimation(String name) {
		return Tileset.getAnimation(name);
	}

	/**
	 * Returns a specific tile given its coordinates and dimensions
	 * 
	 * @param x coordinate
	 * @param y coordinate
	 * @param w width
	 * @param h height
	 *
	 * @return a tile given its coordinates and dimensions
	 **/
	protected PImage g(int x, int y, int w, int h) {
		return Tileset.getTile(x, y, w, h);
	}

	protected PImage g(int x, int y, int w, int h, float s) {
		return Tileset.getTile(x, y, w, h);
	}

	@Override
	public void debug() {
		applet.stroke(255, 190, 200);
		applet.noFill();
		applet.rect(position.x, position.y, width, height);
	}
}
