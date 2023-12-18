package bruno.util;

import static org.junit.Assert.assertEquals;

import java.util.List;
import org.junit.Test;

public class CombinatoricsTest {

  @Test
  public void testGetPermutationsInt() {
    List<List<Integer>> permutations = Combinatorics.getPermutations(List.of(1, 2, 3));
    assertEquals(6, permutations.size());
    assertEquals(List.of(1, 2, 3), permutations.get(0));
    assertEquals(List.of(1, 3, 2), permutations.get(1));
    assertEquals(List.of(2, 1, 3), permutations.get(2));
    assertEquals(List.of(2, 3, 1), permutations.get(3));
    assertEquals(List.of(3, 2, 1), permutations.get(4));
    assertEquals(List.of(3, 1, 2), permutations.get(5));
  }

  @Test
  public void testGetPermutationsString() {
    List<List<String>> permutations = Combinatorics.getPermutations(List.of("b", "c", "d"));
    assertEquals(6, permutations.size());
    assertEquals(List.of("b", "c", "d"), permutations.get(0));
    assertEquals(List.of("b", "d", "c"), permutations.get(1));
    assertEquals(List.of("c", "b", "d"), permutations.get(2));
    assertEquals(List.of("c", "d", "b"), permutations.get(3));
    assertEquals(List.of("d", "c", "b"), permutations.get(4));
    assertEquals(List.of("d", "b", "c"), permutations.get(5));
  }
}
