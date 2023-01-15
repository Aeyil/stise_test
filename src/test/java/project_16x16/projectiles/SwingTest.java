package project_16x16.projectiles;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PImage;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.components.AnimationComponent;
import project_16x16.entities.Player;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class SwingTest {

    private Swing swing;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;

    private AnimationComponent animationComponent;
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
        ArrayList<PImage> list= new ArrayList<>();
        list.add(image);
        tileset.when(() -> Tileset.getAnimation(anyString())).thenReturn(list);
    }

    @BeforeEach
    void setUp() {
        animationComponent = Mockito.mock(AnimationComponent.class);
        sideScroller = Mockito.mock(SideScroller.class);
        gameplayScene = Mockito.mock(GameplayScene.class);
    }

    @AfterEach
    void tearDown() {
        swing = null;
        sideScroller = null;
        gameplayScene = null;
    }

    @AfterAll
    static void overallTearDown(){
        tileset.close();
        image=null;
    }


    @Test
    void initializeSwingViaConstructorLEFT() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        when(gameplayScene.getPlayer()).thenReturn(p);
        swing=new Swing(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,37);
        assertEquals(37,swing.direction);
        assertEquals(-40,swing.position.x);
        assertEquals(DIRECTIONS,swing.position.y);
        assertNotNull(swing.image);
    }

    @Test
    void initializeSwingViaConstructorRIGHT() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        when(gameplayScene.getPlayer()).thenReturn(p);
        swing=new Swing(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,39);
        assertEquals(39,swing.direction);
        assertEquals(80,swing.position.x);
        assertEquals(DIRECTIONS,swing.position.y);
        assertNotNull(swing.image);
    }

    @Test
    void displayRight() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        when(gameplayScene.getPlayer()).thenReturn(p);
        swing=new Swing(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,39);
        swing.display();
        verify(sideScroller).pushMatrix();
        verify(sideScroller).translate(80,DIRECTIONS);
        verify(sideScroller).image(image,0,0);
        verify(sideScroller).popMatrix();
    }

    @Test
    void displayLeft() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        when(gameplayScene.getPlayer()).thenReturn(p);
        swing=new Swing(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,37);
        swing.display();
        verify(sideScroller).pushMatrix();
        verify(sideScroller).translate(-40,DIRECTIONS);
        verify(sideScroller).scale(-1,1);
        verify(sideScroller).image(image,0,0);
        verify(sideScroller).popMatrix();
    }

}