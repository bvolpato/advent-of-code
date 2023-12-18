package bruno.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

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
