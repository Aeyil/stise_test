package project_16x16.objects;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PImage;
import processing.data.JSONObject;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

class AnimatedObjectTest {
    private AnimatedObject animatedObject;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;
    private static PImage image;
    private static MockedStatic<Tileset> tileset;

    private final static String LEVEL_STRING = "Level1";

    @BeforeAll
    static void overallSetUp(){
        image=new PImage();
        tileset = Mockito.mockStatic(Tileset.class);
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
        tileset.when(() -> Tileset.getTile(307, 291, 9, 9)).thenReturn(image);
        tileset.when(() -> Tileset.getTile(anyString())).thenReturn(image);
        tileset.when(() -> Tileset.getAnimation(anyString())).thenReturn(new ArrayList<PImage>());
    }

    @BeforeEach
    void setUp() {
        sideScroller = Mockito.mock(SideScroller.class);
        gameplayScene = Mockito.mock(GameplayScene.class);
    }

    @AfterEach
    void tearDown() {
        animatedObject = null;
        sideScroller = null;
        gameplayScene = null;
    }

    @AfterAll
    static void overallTearDown(){
        tileset.close();
        image=null;
    }

    @Test
    void constructor() {
        animatedObject =new AnimatedObject(sideScroller,gameplayScene);
        assertNotNull(animatedObject.animation);
    }

    @Test
    void getAnimation() {
        animatedObject =new AnimatedObject(sideScroller,gameplayScene);
        assertEquals(new ArrayList<>(), animatedObject.getAnimation(LEVEL_STRING));
    }

    @Test
    void g4() {
        animatedObject =new AnimatedObject(sideScroller,gameplayScene);
        assertEquals(image, animatedObject.g(307, 291, 9, 9));
    }

    @Test
    void g5() {
        animatedObject =new AnimatedObject(sideScroller,gameplayScene);
        assertEquals(image, animatedObject.g(307, 291, 9, 9, 4));
    }

    @Test
    void debug() {
        animatedObject =new AnimatedObject(sideScroller,gameplayScene);
        animatedObject.debug();
        verify(sideScroller).stroke(255,190,200);
        verify(sideScroller).noFill();
        verify(sideScroller).rect(0,0,0,0);
    }

    @Test
    void exportToJSON() {
        animatedObject =new AnimatedObject(sideScroller,gameplayScene);
        JSONObject obj = animatedObject.exportToJSON();
        JSONObject exp =new JSONObject();
        exp.setString("id", null);
        exp.setString("type", "OBJECT");
        exp.setInt("x", 0);
        exp.setInt("y", 0);
        assertEquals(exp.toString(),obj.toString());
    }
}