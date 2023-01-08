package project_16x16;

import project_16x16.scene.PScene;

public enum GameScene {
    MAIN_MENU(SideScroller.menu), GAME(SideScroller.game), PAUSE_MENU(SideScroller.pmenu), SETTINGS_MENU(SideScroller.settings), MULTIPLAYER_MENU(SideScroller.mMenu),
    HOST_MENU(SideScroller.mHostMenu), CLIENT_MENU(SideScroller.mClientMenu), AUDIO_SETTINGS(SideScroller.audioSettings);

    PScene scene;

    private GameScene(PScene scene) {
        this.scene = scene;
    }

    public PScene getScene() {
        return scene;
    }
}
