package bruno.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {

  public static <V, K> Map<V, K> invertMap(Map<K, V> map) {
    Map<V, K> inv = new HashMap<>();
    for (Map.Entry<K, V> entry : map.entrySet()) {
      inv.put(entry.getValue(), entry.getKey());
    }
    return inv;
  }
}
