package project_16x16.objects;

import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PApplet;
import processing.core.PImage;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.Utility;
import project_16x16.components.AnimationComponent;
import project_16x16.entities.Player;
import project_16x16.projectiles.ProjectileObject;
import project_16x16.projectiles.Swing;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MirrorBoxObjectTest {


    private MirrorBoxObject gameObject;
    private SideScroller sideScroller;
    private GameplayScene gameplayScene;
    private static PImage image;
    private static MockedStatic<Tileset> tileset;
    private static MockedStatic<Utility> utility;

    private final static int DIRECTIONS = 20;
    private final static int RIGHT = 0;
    private final static int DOWN = 1;
    private final static int LEFT = 2;
    private final static int UP = 3;


    @BeforeAll
    static void overallSetUp() {
        image = new PImage();
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
        ArrayList<PImage> list = new ArrayList<>();
        list.add(image);
        tileset.when(() -> Tileset.getAnimation(anyString())).thenReturn(list);
        utility = Mockito.mockStatic(Utility.class);
        utility.when(() -> Utility.rotateImage(any(PImage.class), anyFloat())).thenReturn(image);
        utility.when(() -> Utility.resizeImage(any(PImage.class), anyFloat())).thenReturn(image);
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
    static void overallTearDown() {
        tileset.close();
        utility.close();
        image = null;
    }

    @Test
    void constructor() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        assertEquals(0, gameObject.direction);
        assertEquals(gameObject.type.OBJECT, gameObject.type);
        assertEquals("MIRROR_BOX", gameObject.id);
        assertEquals(64, gameObject.width);
        assertEquals(64, gameObject.height);
        assertNotNull(gameObject.collision);
        assertNotNull(gameplayScene.objects);
    }

    @Test
    void displaySetMBRIGHT() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.direction = RIGHT;
        gameObject.display();
        verify(sideScroller).pushMatrix();
        verify(sideScroller).translate(0, 0);
        verify(sideScroller).image(image, 0, 0);
        verify(sideScroller).popMatrix();
    }

    @Test
    void displaySetMBLEFT() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.direction = LEFT;
        gameObject.display();
        verify(sideScroller).pushMatrix();
        verify(sideScroller).translate(0, 0);
        verify(sideScroller).image(image, 0, 0);
        verify(sideScroller).popMatrix();
    }

    @Test
    void displaySetMBUP() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.direction = UP;
        gameObject.display();
        verify(sideScroller).pushMatrix();
        verify(sideScroller).translate(0, 0);
        verify(sideScroller).image(image, 0, 0);
        verify(sideScroller).popMatrix();
    }

    @Test
    void displaySetMBDOWN() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.direction = DOWN;
        gameObject.display();
        verify(sideScroller).pushMatrix();
        verify(sideScroller).translate(0, 0);
        verify(sideScroller).image(image, 0, 0);
        verify(sideScroller).popMatrix();
    }

    @Test
    void update() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameplayScene.objects = new ArrayList<>();
        gameplayScene.projectileObjects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.rotating = false;
        gameObject.activated = false;
        gameObject.update();
        assertNotNull(gameObject.image);
        assertFalse(gameObject.rotating);
        assertFalse(gameObject.activated);
    }

    @Test
    void activateMirrorBoxTrue() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.rotating = true;
        gameObject.activated = true;
        gameObject.activateMirrorBox(true);
        assertNotNull(gameObject.image);
    }

    @Test
    void activateMirrorBoxFalse() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.rotating = true;
        gameObject.activated = true;
        gameObject.activateMirrorBox(false);
        assertNotNull(gameObject.image);
    }

    @Test
    void setMirrorDirection() {
        gameplayScene.objects = new ArrayList<>();
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        gameObject.animation.ended = true;
        gameObject.rotating = true;
        gameObject.setMirrorDirection();
        assertFalse(gameObject.rotating);
        assertFalse(gameObject.animation.ended);
        assertEquals(1, gameObject.direction);
        assertNotNull(gameObject.image);
    }

    @Test
    void projectileDirectionR() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        ProjectileObject projectileObject = new ProjectileObject(sideScroller, gameplayScene);
        gameObject.direction = RIGHT;
        gameObject.projectileDirection(projectileObject);
        assertEquals(0, projectileObject.prevDirection);
        assertEquals(0, projectileObject.direction);
        assertEquals(0, projectileObject.position.x);
        assertEquals(0, projectileObject.position.y);
    }

    @Test
    void projectileDirectionD() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        ProjectileObject projectileObject = new ProjectileObject(sideScroller, gameplayScene);
        gameObject.direction = DOWN;
        gameObject.projectileDirection(projectileObject);
        assertEquals(0, projectileObject.prevDirection);
        assertEquals(0, projectileObject.direction);
        assertEquals(0, projectileObject.position.x);
        assertEquals(0, projectileObject.position.y);
    }

    @Test
    void projectileDirectionL() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        ProjectileObject projectileObject = new ProjectileObject(sideScroller, gameplayScene);
        gameObject.direction = LEFT;
        gameObject.projectileDirection(projectileObject);
        assertEquals(0, projectileObject.prevDirection);
        assertEquals(0, projectileObject.direction);
        assertEquals(0, projectileObject.position.x);
        assertEquals(0, projectileObject.position.y);
    }

    @Test
    void projectileDirectionU() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        ProjectileObject projectileObject = new ProjectileObject(sideScroller, gameplayScene);
        gameObject.direction = UP;
        gameObject.projectileDirection(projectileObject);
        assertEquals(0, projectileObject.prevDirection);
        assertEquals(0, projectileObject.direction);
        assertEquals(0, projectileObject.position.x);
        assertEquals(0, projectileObject.position.y);
    }

    @Test
    void collidesWithSwing() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        assertTrue(gameObject.collidesWithSwing(new Swing(sideScroller, gameplayScene, DIRECTIONS, DIRECTIONS, 37)));
    }

    @Test
    void collidesWithProjectile() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        assertTrue(gameObject.collidesWithProjectile(new ProjectileObject(sideScroller, gameplayScene)));
    }

    @Test
    void bounceProjectileX() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        ProjectileObject projectileObject = new ProjectileObject(sideScroller, gameplayScene);
        gameObject.bounceProjectile(projectileObject, 0, DIRECTIONS, 'x');
        assertEquals(0, projectileObject.prevDirection);
        assertEquals(DIRECTIONS, projectileObject.direction);
        assertEquals(0, projectileObject.position.x);
    }

    @Test
    void bounceProjectileY() {
        AnimationComponent.assignApplet(sideScroller);
        Player p = new Player(sideScroller, null, false);
        gameplayScene.objects = new ArrayList<>();
        when(gameplayScene.getPlayer()).thenReturn(p);
        gameObject = new MirrorBoxObject(sideScroller, gameplayScene);
        ProjectileObject projectileObject = new ProjectileObject(sideScroller, gameplayScene);
        gameObject.bounceProjectile(projectileObject, 0, DIRECTIONS, 'y');
        assertEquals(0, projectileObject.prevDirection);
        assertEquals(DIRECTIONS, projectileObject.direction);
        assertEquals(0, projectileObject.position.y);
    }


}