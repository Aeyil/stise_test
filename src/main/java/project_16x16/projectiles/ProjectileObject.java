package project_16x16.projectiles;

import java.util.ArrayList;

import project_16x16.ISideScroller;
import project_16x16.components.AnimationComponent;
import project_16x16.objects.CollidableObject;
import processing.core.PImage;
import processing.core.PVector;
import processing.data.JSONObject;
import project_16x16.scene.GameplayScene;
import project_16x16.Tileset;

public class ProjectileObject extends CollidableObject {

	public AnimationComponent animation;

	public PVector position;

	public PImage image;

	public int direction;
	public int prevDirection;
	public int width;
	public int height;
	
	public int spawnTime;

	public int speed;

	// Identification
	public String id;

	public boolean hit;

	public ProjectileObject(ISideScroller sideScroller, GameplayScene gameplayScene) {
		super(sideScroller, gameplayScene);

		id = "";
		spawnTime = applet.getFrameCount();
		animation = new AnimationComponent();
		position = new PVector(0, 0);
	}

	public void display() {
	}
	public void update() {
	}

	protected ArrayList<PImage> getAnimation(String name) {
		return Tileset.getAnimation(name);
	}

	@Override
	public void debug() {
	}

	@Override
	public JSONObject exportToJSON() {
		return null;
	}
}
