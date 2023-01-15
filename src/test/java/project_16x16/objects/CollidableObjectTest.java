package project_16x16.objects;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import processing.core.PImage;
import processing.data.JSONObject;
import project_16x16.PClass;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.scene.GameplayScene;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

class CollidableObjectTest {
    private CollidableObject coll;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;

    private final static String LEVEL_STRING = "Level1";
    private final static int DIRECTIONS = 20;
    private final static boolean CHILD = false;

    private static MockedStatic<Tileset> tileset;
    private static PImage image;

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
            tileset.when(() -> Tileset.getTile(anyString())).thenReturn(image);
    }

    @BeforeEach
    void setUp() {
        sideScroller = Mockito.mock(SideScroller.class);
        gameplayScene = Mockito.mock(GameplayScene.class);
    }

    @AfterEach
    void tearDown() {
        coll = null;
        sideScroller = null;
        gameplayScene = null;
    }

    @AfterAll
    static void overallTearDown(){
        tileset.close();
        image=null;
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
    void testConstructorSideScrollerGameplaySceneDirections(){
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        assertEquals(20,coll.position.x);
        assertEquals(20, coll.position.y);
        assertEquals(coll.type.COLLISION,coll.type);
        assertEquals("",coll.flag);
        assertEquals(20,coll.width);
        assertEquals(20,coll.height);
    }

    @Test
    void testConstructorSideScrollerGameplaySceneDirectionsChild(){
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS, CHILD);
        assertEquals(20,coll.position.x);
        assertEquals(20, coll.position.y);
        assertEquals(coll.type.COLLISION,coll.type);
        assertEquals("",coll.flag);
        assertEquals(20,coll.width);
        assertEquals(20,coll.height);
        assertFalse(coll.child);
    }

    @Test
    void testConstructorSideScrollerGameplaySceneDirectionsString(){
        coll=new CollidableObject(sideScroller, gameplayScene, LEVEL_STRING, DIRECTIONS,DIRECTIONS);
        assertEquals(20,coll.position.x);
        assertEquals(20, coll.position.y);
        assertEquals(coll.type.COLLISION,coll.type);
        assertEquals("",coll.flag);
        assertEquals(LEVEL_STRING,coll.id);
    }

    @Test
    void display() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.display();
        assertNotNull(coll.applet);
        assertNull(coll.applet.debug);
    }

    @Test
    void displayDEBUG() {
        sideScroller.debug= SideScroller.DebugType.ALL;
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.display();
        verify(sideScroller).noFill();
        verify(sideScroller).strokeWeight(1);
        verify(sideScroller).stroke(0,255,200);
        verify(sideScroller).rect(DIRECTIONS+2, DIRECTIONS+2, DIRECTIONS,DIRECTIONS);
    }

    @Test
    void setGraphic() {
        coll=new CollidableObject(sideScroller, gameplayScene);
        coll.setGraphic(LEVEL_STRING);
        assertEquals(LEVEL_STRING,coll.id);
        assertEquals(0,coll.width);
        assertEquals(0,coll.height);
    }

    @Test
    void setImageWidth() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.setImageWidth(10);
        assertEquals(10,coll.width);
    }

    @Test
    void setImageHeight() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.setImageHeight(10);
        assertEquals(10,coll.height);
    }

    @Test
    void debug() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.debug();
        verify(sideScroller).stroke(50,120,255);
        verify(sideScroller).noFill();
        verify(sideScroller).rect(DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
    }

    @Test
    void exportToJSON() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        JSONObject obj =coll.exportToJSON();
        JSONObject exp =new JSONObject();
        exp.setString("id", null);
        exp.setString("type", "COLLISION");
        exp.setInt("x", DIRECTIONS);
        exp.setInt("y", DIRECTIONS);
        assertEquals(exp.toString(),obj.toString());
    }
}