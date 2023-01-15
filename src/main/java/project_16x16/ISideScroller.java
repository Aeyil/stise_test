package project_16x16;

import processing.core.PVector;

public interface ISideScroller extends IPApplet{

    void swapToScene(GameScene newScene);
    void returnScene();

    boolean isMousePressEvent();
    boolean isMouseReleaseEvent();
    boolean isKeyPressEvent();
    void setKeyPressEvent(boolean keyPressEvent);
    boolean isKeyDown(int k);

    int getWidth();
    int getHeight();
    int getMouseX();
    int getMouseY();
    PVector getGameResolution();
    int getFrameCount();
    char getKey();
    Camera getCamera();
    DebugType getDebug();
    int getMouseButton();
    boolean getMousePressed();

    PVector getMouseCoordScreen();
    PVector getMouseCoordGame();
}
