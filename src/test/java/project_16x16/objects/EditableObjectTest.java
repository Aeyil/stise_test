package project_16x16.objects;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import processing.core.PImage;
import processing.core.PVector;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.Utility;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static project_16x16.PClass.LEFT;


class EditableObjectTest {
    private CollidableObject coll;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;

    private final static String LEVEL_STRING = "Level1";
    private final static int DIRECTIONS = 20;
    private final static int DIRECTIONS_HALF = 10;
    private final static boolean CHILD_FALSE = false;
    private final static boolean CHILD_TRUE= true;

    private static MockedStatic<Tileset> tileset;
    private static MockedStatic<Utility> utility;
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
        tileset.when(() -> Tileset.getTile(null)).thenReturn(image);
//        tileset.when(() -> Tileset.getObjectClass(null)).thenReturn(MirrorBoxObject.class);
        utility=Mockito.mockStatic(Utility.class);
        utility.when(()->Utility.hoverGame(DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS)).thenReturn(false);
        utility.when(()->Utility.hoverGame(DIRECTIONS,DIRECTIONS,DIRECTIONS_HALF,DIRECTIONS_HALF)).thenReturn(true);
        utility.when(()->Utility.roundToNearest(anyFloat(),anyFloat())).thenReturn(0f);
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
        utility.close();
        image=null;
    }


    @Test
    void focus() {
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.focus();
        assertEquals(new PVector(0,0,0),coll.editOffset);
        assertTrue(coll.isFocused());
    }

    @Test
    void displayEditFocused() {
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.focus();
        coll.displayEdit();
        verify(sideScroller).strokeWeight(10);
        verify(sideScroller).noFill();
        verify(sideScroller).stroke(0,255,200);
        verify(sideScroller).rect(DIRECTIONS, DIRECTIONS, DIRECTIONS,DIRECTIONS);
        verify(sideScroller).strokeWeight(4);
    }

    @Test
    void displayEditUnfocused() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.unFocus();
        coll.displayEdit();
        verify(sideScroller,times(0)).strokeWeight(10);
    }

    @Test
    void unfocused() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.unFocus();
        assertFalse(coll.isFocused());
    }

    @Test
    void mouseHover() {
        when(sideScroller.getMouseX()).thenReturn(200);
        when(sideScroller.getMouseY()).thenReturn(90);
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        assertFalse(coll.mouseHover());
    }

    @Test
    void mouseHoverHoverGameTrue() {
        when(sideScroller.getMouseX()).thenReturn(400);
        when(sideScroller.getMouseY()).thenReturn(100);
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS_HALF,DIRECTIONS_HALF,DIRECTIONS,DIRECTIONS);
        assertTrue(coll.mouseHover());
    }

    @Test
    void mouseHoverHoverGameFalse() {
        when(sideScroller.getMouseX()).thenReturn(400);
        when(sideScroller.getMouseY()).thenReturn(100);
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        assertFalse(coll.mouseHover());
    }

    @Test
    void updateEditDoNothing() {
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS, CHILD_FALSE);
        coll.unFocus();
        coll.updateEdit();
        assertFalse(coll.isFocused());
    }

    @Test
    void updateEditCheckChild() {
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS, CHILD_TRUE);
        coll.focus();
        coll.updateEdit();
        assertTrue(coll.isFocused());
    }

    @Test
    void updateEditCheckApplet() {
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        when(sideScroller.isMouseReleaseEvent()).thenReturn(true);
        when(sideScroller.getMouseButton()).thenReturn(LEFT);
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS, CHILD_FALSE);
        coll.focus();
        coll.updateEdit();
        assertFalse(coll.isFocused());
    }

    @Test
    void updateEditCheckFocusEventEnableFocusFocusedObjNull() {
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        when(sideScroller.isMousePressEvent()).thenReturn(true);
        when(sideScroller.getMousePressed()).thenReturn(false);
        when(sideScroller.getMouseButton()).thenReturn(LEFT);
        when(sideScroller.getMouseX()).thenReturn(400);
        when(sideScroller.getMouseY()).thenReturn(100);
        gameplayScene.focusedObject=null;
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS_HALF,DIRECTIONS_HALF,DIRECTIONS,DIRECTIONS, CHILD_FALSE);
        coll.unFocus();
        coll.updateEdit();
        assertTrue(coll.isFocused());
        assertNotNull(gameplayScene.focusedObject);
        assertEquals(new PVector(0,0,0),coll.editOffset);
    }

    @Test
    void updateEditCheckFocusEventEnableFocusFocusedObjNullChangePosition() {
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        when(sideScroller.getMouseX()).thenReturn(400);
        when(sideScroller.getMouseY()).thenReturn(100);
        when(sideScroller.isMousePressEvent()).thenReturn(true);
        when(sideScroller.getMouseButton()).thenReturn(LEFT);
        when(sideScroller.getMousePressed()).thenReturn(true);
        gameplayScene.focusedObject=null;
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS_HALF,DIRECTIONS_HALF,DIRECTIONS,DIRECTIONS, CHILD_FALSE);
        coll.unFocus();
        coll.updateEdit();
        assertEquals(new PVector(0,0),coll.position);
    }

    @Test
    void updateEditCheckWhenFocusedDuplicateObjectShiftCollision() {
        gameplayScene.objects=new ArrayList<>();
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        when(sideScroller.isKeyDown(SideScroller.SHIFT)).thenReturn(true);
        when(sideScroller.getMouseX()).thenReturn(400);
        when(sideScroller.getMouseY()).thenReturn(100);
        when(sideScroller.isKeyPressEvent()).thenReturn(true);
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS_HALF,DIRECTIONS_HALF,DIRECTIONS,DIRECTIONS, CHILD_FALSE);
        coll.type= ObjectType.COLLISION;
        coll.focus();
        coll.updateEdit();
        assertNotNull(coll.gameplayScene.objects);
        assertFalse(coll.isFocused());
    }

//    @Test
//    void updateEditCheckWhenFocusedDuplicateObjectShiftObject() {
//        gameplayScene.objects=new ArrayList<>();
//        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
//        when(sideScroller.isKeyDown(SideScroller.SHIFT)).thenReturn(true);
//        sideScroller.mouseX=400;
//        sideScroller.mouseY=100;
//        sideScroller.keyPressEvent=true;
//        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS_HALF,DIRECTIONS_HALF,DIRECTIONS,DIRECTIONS, CHILD_FALSE);
//        coll.type= coll.type.OBJECT;
//        coll.focus();
//        coll.updateEdit();
//        assertNotNull(coll.gameplayScene.objects);
//        assertFalse(coll.applet.keyPressEvent);
//        assertFalse(coll.isFocused());
//    }

    @Test
    void updateEditCheckWhenFocusedDuplicateObjectShiftDEFAULT() {
        when(sideScroller.getMouseCoordGame()).thenReturn(new PVector(DIRECTIONS,DIRECTIONS));
        when(sideScroller.isKeyDown(SideScroller.SHIFT)).thenReturn(true);
        when(sideScroller.getMouseX()).thenReturn(400);
        when(sideScroller.getMouseY()).thenReturn(100);
        when(sideScroller.isKeyPressEvent()).thenReturn(true);
        coll=new CollidableObject(sideScroller, gameplayScene, DIRECTIONS_HALF,DIRECTIONS_HALF,DIRECTIONS,DIRECTIONS, CHILD_FALSE);
        coll.type= ObjectType.BACKGROUND;
        coll.focus();
        coll.updateEdit();
        assertFalse(coll.isFocused());
    }

}