package project_16x16.projectiles;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PImage;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.Utility;
import project_16x16.components.AnimationComponent;
import project_16x16.objects.GameObject;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;

class MagicProjectileTest {
    private MagicProjectile magic;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;

    private AnimationComponent animationComponent;
    private static PImage image;
    private static MockedStatic<Tileset> tileset;
    private static MockedStatic<Utility> utility;

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
        utility=Mockito.mockStatic(Utility.class);
        utility.when(()->Utility.rotateImage(any(PImage.class),anyFloat())).thenReturn(image);
        utility.when(()->Utility.resizeImage(any(PImage.class),anyFloat())).thenReturn(image);
    }

    @BeforeEach
    void setUp() {
        animationComponent = Mockito.mock(AnimationComponent.class);
        sideScroller = Mockito.mock(SideScroller.class);
        gameplayScene = Mockito.mock(GameplayScene.class);
    }

    @AfterEach
    void tearDown() {
        magic = null;
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
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS);
        assertEquals("MAGIC",magic.id);
        assertEquals(DIRECTIONS,magic.position.x);
        assertEquals(DIRECTIONS,magic.position.y);
        assertEquals(DIRECTIONS,magic.direction);
        assertEquals(DIRECTIONS,magic.prevDirection);
    }

    @Test
    void debug() {
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,DIRECTIONS);
        magic.debug();
        verify(sideScroller).stroke(255,0,0);
        verify(sideScroller).noFill();
        verify(sideScroller).rect(20,20,32,32);
    }

    @Test
    void debugLEFT() {
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,37);
        magic.debug();
        verify(sideScroller).stroke(255,0,0);
        verify(sideScroller).noFill();
        verify(sideScroller).rect(20,20,32,32);
    }

    @Test
    void updateIf() {
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,37);
        magic.update();
        assertEquals(DIRECTIONS-magic.speed,magic.position.x);
    }

//method never used
   /* @Test
    void destroyProjectile() {
    }*/

    @Test
    void moveProjectileRIGHT() {
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,39);
        magic.moveProjectile();
        assertEquals(DIRECTIONS+magic.speed,magic.position.x);
    }

    @Test
    void moveProjectileUP() {
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,38);
        magic.moveProjectile();
        assertEquals(DIRECTIONS-magic.speed,magic.position.y);
    }

    @Test
    void moveProjectileDOWN() {
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,40);
        magic.moveProjectile();
        assertEquals(DIRECTIONS+magic.speed,magic.position.y);
    }

    @Test
    void hit() {
        magic=new MagicProjectile(sideScroller,gameplayScene, DIRECTIONS,DIRECTIONS,40);
        magic.hit(new GameObject(sideScroller,gameplayScene));
        assertTrue(magic.hit);
    }
}