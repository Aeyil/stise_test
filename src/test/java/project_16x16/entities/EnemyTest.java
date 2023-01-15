package project_16x16.entities;

import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import processing.core.PImage;
import processing.core.PVector;
import project_16x16.SideScroller;
import project_16x16.Tileset;
import project_16x16.objects.GameObject;
import project_16x16.objects.EditableObject;
import project_16x16.scene.GameplayScene;

import java.util.ArrayList;

public class EnemyTest {

    @Mock
    private SideScroller sideScroller;
    @Mock
    private GameplayScene scene;
    @Mock
    private PImage image;

    private Enemy enemy;
    private ArrayList<EditableObject> objectList;

    @BeforeEach
    public void before(){
        scene = Mockito.mock(GameplayScene.class);
        sideScroller = Mockito.mock(SideScroller.class);
        objectList = new ArrayList<>();
        scene.objects = objectList;
        try(MockedStatic<Tileset> tileset_mocked = Mockito.mockStatic(Tileset.class)){
            tileset_mocked.when(() -> Tileset.getTile(0, 258, 14, 14, 4))
                    .thenReturn(image);
            enemy = new Enemy(sideScroller,scene,2,1,7,18,56,40,145);
        }
    }

    @Test
    public void constructorTest(){
        Assert.assertNotNull(enemy);
    }

    @Test
    public void displayTest(){
        enemy.display();
    }

    @Test
    public void updateTestNoStateChange(){
        enemy.update();
        Assert.assertFalse(enemy.state.flying);
    }

    @Test
    public void updateTestStateChangeFlying(){
        enemy.velocity.y = 1;
        enemy.update();
        Assertions.assertTrue(enemy.state.flying);
    }

    @Test
    public void getVelocityTest(){
        PVector vel = enemy.getVelocity();
        Assertions.assertEquals(vel.x,enemy.velocity.x);
        Assertions.assertEquals(vel.y,enemy.velocity.y);
        Assertions.assertEquals(vel.z,enemy.velocity.z);
        Assertions.assertNotSame(vel,enemy.velocity);
    }

    @Test
    public void getStateTest(){
        Assertions.assertEquals(enemy.state,enemy.getState());
    }

    // Note: Enemy Collision Variables
    //    width = (56)
    //    height = (40)
    //    collisionRange = 145

    @Test
    public void checkEnemyCollisionTestIgnoresSelf(){
        enemy.velocity.set(5f,5f);
        objectList.add(enemy);
        enemy.checkForCollision();
        Assertions.assertEquals(5f, enemy.velocity.x);
        Assertions.assertEquals(5f, enemy.velocity.y);
    }

    @Test
    public void checkEnemyCollisionTestDashingXCollision(){
        enemy.position.set(0,0);
        enemy.velocity.set(50f,0f);
        enemy.state.dashing = true;
        GameObject collisionObject = new GameObject(sideScroller,scene,160,100,100,0);
        objectList.add(collisionObject);
        enemy.checkForCollision();
        Assertions.assertEquals(enemy.velocity.x,0f);
        Assertions.assertFalse(enemy.state.dashing);
        Assertions.assertEquals(collisionObject.position.x-collisionObject.width/2-enemy.width/2,enemy.position.x);
    }

    @Test
    public void checkEnemyCollisionTestFlyingYCollision(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,50f);
        enemy.state.flying = true;
        GameObject collisionObject = new GameObject(sideScroller,scene,160,100,0,100);
        objectList.add(collisionObject);
        enemy.checkForCollision();
        Assertions.assertEquals(enemy.velocity.y,0f);
        Assertions.assertFalse(enemy.state.flying);
        Assertions.assertTrue(enemy.state.landing);
        Assertions.assertEquals(collisionObject.position.y-collisionObject.height/2-enemy.height/2,enemy.position.y);
    }

    @Test
    public void checkEnemyCollisionTestJumpingYCollision(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,-50f);
        enemy.state.jumping = true;
        GameObject collisionObject = new GameObject(sideScroller,scene,160,100,0,-100);
        objectList.add(collisionObject);
        enemy.checkForCollision();
        Assertions.assertEquals(enemy.velocity.y,0f);
        Assertions.assertFalse(enemy.state.jumping);
        Assertions.assertEquals(collisionObject.position.y+collisionObject.height/2+enemy.height/2,enemy.position.y);
    }

    @Test
    public void collidesFuturXNoCollision(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,0f);
        GameObject collisionObject = new GameObject(sideScroller,scene,10,40,33,0);
        Assertions.assertFalse(enemy.collidesFutureX(collisionObject));
    }

    @Test
    public void collidesFuturXCollisionThroughPositiveVelocity(){
        enemy.position.set(0,0);
        enemy.velocity.set(5f,0f);
        GameObject collisionObject = new GameObject(sideScroller,scene,10,40,33,0);
        Assertions.assertTrue(enemy.collidesFutureX(collisionObject));
    }

    @Test
    public void collidesFuturXCollisionThroughNegativeVelocity(){
        enemy.position.set(0,0);
        enemy.velocity.set(-5f,0f);
        GameObject collisionObject = new GameObject(sideScroller,scene,10,40,-33,0);
        Assertions.assertTrue(enemy.collidesFutureX(collisionObject));
    }

    @Test
    public void collidesFuturXCollisionThroughPosition(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,0f);
        GameObject collisionObject = new GameObject(sideScroller,scene,10,40,32,0);
        Assertions.assertTrue(enemy.collidesFutureX(collisionObject));
    }
    @Test
    public void collidesFuturYNoCollision(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,0f);
        GameObject collisionObject = new GameObject(sideScroller,scene,56,10,0,25);
        Assertions.assertFalse(enemy.collidesFutureY(collisionObject));
    }

    @Test
    public void collidesFuturYCollisionThroughPositiveVelocity(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,5f);
        GameObject collisionObject = new GameObject(sideScroller,scene,56,10,0,25);
        Assertions.assertTrue(enemy.collidesFutureY(collisionObject));
    }

    @Test
    public void collidesFuturYCollisionThroughNegativeVelocity(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,-5f);
        GameObject collisionObject = new GameObject(sideScroller,scene,56,10,0,-25);
        Assertions.assertTrue(enemy.collidesFutureY(collisionObject));
    }

    @Test
    public void collidesFuturYCollisionThroughPosition(){
        enemy.position.set(0,0);
        enemy.velocity.set(0f,0f);
        GameObject collisionObject = new GameObject(sideScroller,scene,56,10,0,24);
        Assertions.assertTrue(enemy.collidesFutureY(collisionObject));
    }

}
