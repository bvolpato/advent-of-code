package bruno.util;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class MapUtilTest {

  @Test
  public void testInvertMapWithNonEmptyMap() {
    Map<String, Integer> original = new HashMap<>();
    original.put("One", 1);
    original.put("Two", 2);
    original.put("Three", 3);

    Map<Integer, String> inverted = MapUtil.invertMap(original);

    assertEquals(3, inverted.size());
    assertEquals("One", inverted.get(1));
    assertEquals("Two", inverted.get(2));
    assertEquals("Three", inverted.get(3));
  }

  @Test
  public void testInvertMapWithEmptyMap() {
    Map<String, Integer> original = new HashMap<>();
    Map<Integer, String> inverted = MapUtil.invertMap(original);

    assertTrue(inverted.isEmpty());
  }

  @Test
  public void testInvertMapWithNullValues() {
    Map<String, Integer> original = new HashMap<>();
    original.put("One", null);
    original.put(null, 2);

    Map<Integer, String> inverted = MapUtil.invertMap(original);

    assertEquals(2, inverted.size());
    assertEquals(null, inverted.get(2));
    assertEquals("One", inverted.get(null));
  }
}
