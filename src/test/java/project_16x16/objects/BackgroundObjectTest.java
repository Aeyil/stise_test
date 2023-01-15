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

class BackgroundObjectTest {

    private BackgroundObject backgroundObject;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;
    private static PImage image;
    private static MockedStatic<Tileset> tileset;

    private final static String LEVEL_STRING = "Level1";
    private final static int DIRECTIONS = 20;


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
        backgroundObject = null;
        sideScroller = null;
        gameplayScene = null;
    }

    @AfterAll
    static void overallTearDown(){
        tileset.close();
        image=null;
    }

    @Test
    void testConstructor(){
        backgroundObject=new BackgroundObject(sideScroller,gameplayScene);
        assertEquals(ObjectType.BACKGROUND, backgroundObject.type);
    }

    @Test
    void testConstructorSideScrollerGameplaySceneDirectionsString(){
        backgroundObject=new BackgroundObject(sideScroller, gameplayScene, LEVEL_STRING, DIRECTIONS,DIRECTIONS);
        assertEquals(20,backgroundObject.position.x);
        assertEquals(20,backgroundObject.position.y);
        assertNotNull(backgroundObject.image);
    }

    @Test
    void testConstructorSideScrollerGameplaySceneString(){
        backgroundObject=new BackgroundObject(sideScroller, gameplayScene, LEVEL_STRING);
        assertEquals(0,backgroundObject.position.x);
        assertEquals(0,backgroundObject.position.y);
        assertNotNull(backgroundObject.image);
    }

    @Test
    void display() {
        backgroundObject=new BackgroundObject(sideScroller, gameplayScene);
        backgroundObject.display();
        assertNotNull(backgroundObject.applet);
    }

    @Test
    void displayPosition() {
        backgroundObject=new BackgroundObject(sideScroller, gameplayScene);
        backgroundObject.width=9;
        backgroundObject.height=9;
        backgroundObject.display();
        assertNotNull(backgroundObject.applet);
    }

    @Test
    void setGraphic() {
        backgroundObject=new BackgroundObject(sideScroller, gameplayScene);
        backgroundObject.setGraphic(LEVEL_STRING);
        assertEquals(LEVEL_STRING,backgroundObject.id);
        assertEquals(0,backgroundObject.width);
        assertEquals(0,backgroundObject.height);
    }

    @Test
    void setImageWidth() {
        backgroundObject=new BackgroundObject(sideScroller,gameplayScene);
        backgroundObject.setImageWidth(10);
        assertEquals(10,backgroundObject.width);
    }

    @Test
    void setImageHeight() {
        backgroundObject=new BackgroundObject(sideScroller,gameplayScene);
        backgroundObject.setImageHeight(10);
        assertEquals(10,backgroundObject.height);
    }

    @Test
    void debug() {
        backgroundObject=new BackgroundObject(sideScroller,gameplayScene);
        backgroundObject.debug();
        verify(sideScroller).stroke(50,255,120);
        verify(sideScroller).noFill();
        verify(sideScroller).rect(0,0,0,0);
    }

    @Test
    void exportToJSON() {
        backgroundObject=new BackgroundObject(sideScroller,gameplayScene);
        JSONObject obj =backgroundObject.exportToJSON();
        JSONObject exp =new JSONObject();
        exp.setString("id", null);
        exp.setString("type", "BACKGROUND");
        exp.setInt("x", 0);
        exp.setInt("y", 0);
        assertEquals(exp.toString(),obj.toString());
    }
}