package project_16x16.objects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PImage;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.scene.GameplayScene;

import static org.junit.jupiter.api.Assertions.*;

class CollidableObjectTest {

    private CollidableObject coll;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;
    private PImage image;

    private final static String LEVEL_STRING = "Level1";

    @BeforeEach
    void setUp() {
        sideScroller = Mockito.mock(SideScroller.class);
        gameplayScene = Mockito.mock(GameplayScene.class);
        image = new PImage();
        try(MockedStatic<Tileset> tileset = Mockito.mockStatic(Tileset.class)){
            tileset.when(() -> Tileset.getTile(289, 256, 20, 21, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(310, 256, 20, 21, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(279, 301, 9, 9, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(289, 301, 9, 9, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(298, 301, 9, 9, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(307, 301, 9, 9, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(279, 291, 9, 9, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(289, 291, 9, 9, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(298, 291, 9, 9, 4)).thenReturn(image);
            tileset.when(() -> Tileset.getTile(307, 291, 9, 9, 4)).thenReturn(image);

        }
    }

    @AfterEach
    void tearDown() {
        coll = null;
        sideScroller = null;
        gameplayScene = null;
    }

    @Test

    void testConstructorSideScrollerGameplayScene(){
        coll=new CollidableObject(sideScroller, gameplayScene);
        assertEquals(0,coll.position.x);
        assertEquals(0, coll.position.y);
        assertEquals(coll.type.COLLISION,coll.type);
        assertEquals("",coll.flag);
    }


    @Test
    void display() {

    }

    @Test
    void setGraphic() {
    }

    @Test
    void setImageWidth() {
    }

    @Test
    void setImageHeight() {
    }

    @Test
    void debug() {
    }

    @Test
    void exportToJSON() {
    }
}