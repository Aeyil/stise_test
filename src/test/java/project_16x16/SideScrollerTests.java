package project_16x16;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import processing.event.KeyEvent;

import java.util.HashSet;

public class SideScrollerTests {

    private SideScroller sideScroller;
    private static int key_A = 65;
    private static KeyEvent event = new KeyEvent(new Object(),1,1,1,'A',key_A);

    @BeforeEach
    public void setup(){
        sideScroller = new SideScroller();
        sideScroller.keysDown = new HashSet<Integer>();
    }

    @AfterEach
    public void tearDown(){
        sideScroller = null;
    }

    @Test
    public void keysDown_initialized_SetIsEmpty_True(){
        Assertions.assertTrue(sideScroller.keysDown.isEmpty());
    }
    @Test
    public void keyPressed_addsKeyEventAInHashSet_isKeyDown_A_IsTrue(){
        //HashSet keysDown is empty, size == 0
        Assertions.assertTrue(sideScroller.keysDown.isEmpty());
        sideScroller.keyPressed(event);
        Assertions.assertTrue(sideScroller.isKeyDown(key_A));
    }
    @Test
    public void keyPressed_noKeyEvent_isKeyDown_A_IsFalse(){
        Assertions.assertFalse(sideScroller.isKeyDown(key_A));
    }
    @Test
    public void keyPressed_addsKeyEventAInHashSet_keyPressEventTrue(){
        sideScroller.keyPressed(event);
        Assertions.assertTrue(sideScroller.isKeyPressEvent());
    }
    @Test
    public void mousePressed_isMousePressEvent_isTrue(){
        sideScroller.mousePressed();
        Assertions.assertTrue(sideScroller.isMousePressEvent());
    }
    @Test
    public void mousePressed_isMouseReleasedEvent_isFalse(){
        sideScroller.mousePressed();
        Assertions.assertFalse(sideScroller.isMouseReleaseEvent());
    }
    @Test
    public void no_mousePressed_isMousePressEvent_isFalse(){
        Assertions.assertFalse(sideScroller.isMousePressEvent());
    }
    @Test
    public void no_mousePressed_isMouseReleasedEvent_isFalse(){
        Assertions.assertFalse(sideScroller.isMouseReleaseEvent());
    }
    @Test
    public void mouseReleased_isMouseReleased_isTrue(){
        sideScroller.mouseReleased();
        Assertions.assertTrue(sideScroller.isMouseReleaseEvent());
    }
    @Test
    public void mouseReleased_isMousePressed_isFalse(){
        sideScroller.mouseReleased();
        Assertions.assertFalse(sideScroller.isMousePressEvent());
    }
    @Test
    public void no_mouseReleased_isMouseReleased_isFalse(){
        Assertions.assertFalse(sideScroller.isMouseReleaseEvent());
    }
    @Test
    public void no_mouseReleased_isMousePressed_isFalse(){
        Assertions.assertFalse(sideScroller.isMousePressEvent());
    }

}
