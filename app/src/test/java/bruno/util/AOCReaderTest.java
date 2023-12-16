package bruno.util;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class AOCReaderTest {

  @Test
  public void testLines() {
    List<String> lines = AOCReader.lines("a\nb\nc");
    assertEquals(3, lines.size());
    assertEquals("a", lines.get(0));
    assertEquals("b", lines.get(1));
    assertEquals("c", lines.get(2));
  }

  @Test
  public void testGrid() {
    char[][] grid = AOCReader.grid("abc\ndef\nghj");
    assertEquals(3, grid.length);
    assertArrayEquals(new char[] {'a', 'b', 'c'}, grid[0]);
    assertArrayEquals(new char[] {'d', 'e', 'f'}, grid[1]);
    assertArrayEquals(new char[] {'g', 'h', 'j'}, grid[2]);
  }
}
