package project_16x16.objects;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PImage;
import project_16x16.PClass;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.Utility;
import project_16x16.components.AnimationComponent;
import project_16x16.entities.Player;
import project_16x16.projectiles.MagicProjectile;
import project_16x16.projectiles.Swing;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class MagicSourceObjectTest {

    private MagicSourceObject gameObject;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;
    private static PImage image;
    private static MockedStatic<Tileset> tileset;
    private static MockedStatic<Utility> utility;

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
        ArrayList<PImage> list= new ArrayList<>();
        list.add(image);
        tileset.when(() -> Tileset.getAnimation(anyString())).thenReturn(list);
        utility=Mockito.mockStatic(Utility.class);
        utility.when(()->Utility.rotateImage(any(PImage.class),anyFloat())).thenReturn(image);
        utility.when(()->Utility.resizeImage(any(PImage.class),anyFloat())).thenReturn(image);
    }

    @BeforeEach
    void setUp() {
        sideScroller = Mockito.mock(SideScroller.class);
        gameplayScene = Mockito.mock(GameplayScene.class);
    }

    @AfterEach
    void tearDown() {
        gameObject = null;
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
    void constructor() {
        gameObject=new MagicSourceObject(sideScroller,gameplayScene);
        assertEquals(ObjectType.OBJECT,gameObject.type);
        assertEquals("MAGIC_SOURCE",gameObject.id);
        assertEquals(48,gameObject.width);
        assertEquals(48,gameObject.height);
        assertEquals(-80,gameObject.position.y);
    }

    @Test
    void update() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        p.swings=new ArrayList<>();
        gameplayScene.projectileObjects=new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        p.swings.add(new Swing(sideScroller,gameplayScene,DIRECTIONS,DIRECTIONS,DIRECTIONS));
        gameObject=new MagicSourceObject(sideScroller,gameplayScene);
        gameObject.update();
        assertNotNull(gameplayScene.projectileObjects);
    }

    @Test
    void updateSwingNotActivated() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        p.swings=new ArrayList<>();
        gameplayScene.projectileObjects=new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        Swing s=new Swing(sideScroller,gameplayScene,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        s.activated=false;
        p.swings.add(s);
        gameObject=new MagicSourceObject(sideScroller,gameplayScene);
        gameObject.update();
        assertNotNull(gameplayScene.projectileObjects);
    }

    @Test
    void collidesWithSwingFALSE() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject=new MagicSourceObject(sideScroller,gameplayScene);
        assertFalse(gameObject.collidesWith(new Swing(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,37)));
    }

    @Test
    void collidesWithPlayerFALSE() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller,null,false);
        gameObject=new MagicSourceObject(sideScroller,gameplayScene);
        assertFalse(gameObject.collidesWith(p));
    }
}