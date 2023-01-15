package project_16x16.objects;

import processing.core.PImage;
import processing.core.PVector;
import project_16x16.DebugType;
import project_16x16.ISideScroller;
import project_16x16.Tileset;
import project_16x16.scene.GameplayScene;

public class GameObject extends CollidableObject {

	private PImage image;
	private float pixelOffsetX = 0;
	private float pixelOffsetY = 0;

	public GameObject(ISideScroller sideScroller, GameplayScene gameplayScene) {
		super(sideScroller, gameplayScene);
		type = ObjectType.COLLISION;
	}

	public GameObject(ISideScroller sideScroller, GameplayScene gameplayScene, int w, int h, int x, int y, boolean child) {
		this(sideScroller, gameplayScene, w, h, x, y);

		super.child = child;
	}

	public GameObject(ISideScroller sideScroller, GameplayScene gameplayScene, int w, int h, int x, int y) {
		this(sideScroller, gameplayScene);

		// Get From Game Graphics Image
		position = new PVector(x, y);
		width = w;
		height = h;
	}

	public GameObject(ISideScroller sideScroller, GameplayScene gameplayScene, String t, int x, int y) {
		this(sideScroller, gameplayScene);

		// Get From Game Graphics Image
		setGraphic(t);
		position = new PVector(x, y);
	}

	public void display() {
		if (height / 4 % 2 != 0) {
			pixelOffsetY = 2;
		}
		if (width / 4 % 2 != 0) {
			pixelOffsetX = 2;
		}
		if (id == null) {
			if (applet.getDebug() == DebugType.ALL) {
				applet.noFill();
				applet.strokeWeight(1);
				applet.stroke(0, 255, 200);
				applet.rect(position.x + pixelOffsetX, position.y + pixelOffsetY, width, height);
			}
		}
		else {
			applet.image(image, position.x + pixelOffsetX, position.y + pixelOffsetY, width, height);
		}
	}

	public void setGraphic(String name) {
		image = Tileset.getTile(name);
		id = name;
		width = image.width;
		height = image.height;
	}

	// WARNING: This can distort images
	public void setImageWidth(int w) {
		width = w;
	}

	public void setImageHeight(int h) {
		height = h;
	}

	@Override
	public void debug() {
		applet.stroke(50, 120, 255);
		applet.noFill();
		applet.rect(position.x, position.y, width, height);
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
