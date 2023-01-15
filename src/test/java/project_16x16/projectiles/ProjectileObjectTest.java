package project_16x16.projectiles;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PImage;
import processing.core.PVector;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.objects.CollidableObject;
import project_16x16.objects.GameObject;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class ProjectileObjectTest {

    private ProjectileObject projectileObject;
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
        projectileObject = null;
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
        projectileObject =new ProjectileObject(sideScroller,gameplayScene);
        assertEquals("",projectileObject.id);
        assertEquals(0,projectileObject.spawnTime);
        assertNotNull(projectileObject.animation);
        assertEquals(new PVector(0,0),projectileObject.position);
    }

    @Test
    void collidesFalse() {
        projectileObject =new ProjectileObject(sideScroller,gameplayScene);
        projectileObject.width=0;
        projectileObject.height=0;
        projectileObject.position.x=0;
        projectileObject.position.y=0;
        CollidableObject coll= new CollidableObject(sideScroller,gameplayScene,DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.width=0;
        coll.height=0;
        coll.position.x=0;
        coll.position.y=0;
        assertFalse(projectileObject.collides(coll));
    }

    @Test
    void collidesTrue() {
        projectileObject =new ProjectileObject(sideScroller,gameplayScene);
        projectileObject.width=0;
        projectileObject.height=0;
        projectileObject.position.x=1;
        projectileObject.position.y=1;
        CollidableObject coll= new CollidableObject(sideScroller,gameplayScene,DIRECTIONS,DIRECTIONS,DIRECTIONS,DIRECTIONS);
        coll.width=1;
        coll.height=1;
        coll.position.x=0;
        coll.position.y=0;
        assertFalse(projectileObject.collides(coll));
    }

    @Test
    void getAnimation() {
        projectileObject=new ProjectileObject(sideScroller,gameplayScene);
        assertEquals(new ArrayList<>(),projectileObject.getAnimation(LEVEL_STRING));
    }

    @Test
    void exportToJSON() {
        projectileObject =new ProjectileObject(sideScroller,gameplayScene);
        assertNull(projectileObject.exportToJSON());
    }
}