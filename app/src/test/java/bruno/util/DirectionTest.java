package bruno.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DirectionTest {

  @Test
  public void testRight() {
    assertEquals(Direction.RIGHT, Direction.UP.right());
    assertEquals(Direction.UP, Direction.UP.right().right().right().right());
  }

  @Test
  public void testLeft() {
    assertEquals(Direction.LEFT, Direction.UP.left());
    assertEquals(Direction.UP, Direction.UP.left().left().left().left());
  }
}
