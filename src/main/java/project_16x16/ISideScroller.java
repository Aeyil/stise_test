package project_16x16;

import processing.core.PVector;

public interface ISideScroller extends IPApplet{

    public void swapToScene(GameScene newScene);
    public void returnScene();

    public boolean isMousePressEvent();
    public boolean isMouseReleaseEvent();
    public boolean isKeyPressEvent();
    public void setKeyPressEvent(boolean keyPressEvent);
    public boolean isKeyDown(int k);

    public int getWidth();
    public int getHeight();
    public int getMouseX();
    public int getMouseY();
    public PVector getGameResolution();
    public int getFrameCount();
    public char getKey();
    public Camera getCamera();
    public DebugType getDebug();
    public int getMouseButton();
    public boolean getMousePressed();

    public PVector getMouseCoordScreen();
    public PVector getMouseCoordGame();
}
