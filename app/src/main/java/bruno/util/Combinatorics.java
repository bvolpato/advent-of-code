package bruno.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Combinatorics {
  public static <T> List<List<T>> getPermutations(List<T> array) {
    List<List<T>> ans = new ArrayList<>();
    buildPermutations(new ArrayList<>(array), 0, ans);
    return ans;
  }

  protected static <T> void buildPermutations(List<T> array, int i, List<List<T>> ans) {
    if (i == array.size() - 1) {
      ans.add(new ArrayList<>(array));
    } else {
      for (int j = i; j < array.size(); j++) {
        Collections.swap(array, i, j);
        buildPermutations(array, i + 1, ans);
        Collections.swap(array, i, j);
      }
    }
  }
}
