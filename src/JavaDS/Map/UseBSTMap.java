package JavaDS.Map;

import java.util.Map;
import java.util.Set;

public class UseBSTMap {
    public static void main(String[] args) {
        BSTMap map = new BSTMap();

        map.put(13L, "十三");
        map.put(2L, "二");
        map.put(7L, "七");

        System.out.println(map.get(3L));    // null

        System.out.println(map.get(7L));    // 七

        System.out.println(map.keySet());

        System.out.println(map.values());

        Set<Map.Entry<Long, String>> entries = map.entrySet();
        for (Map.Entry<Long, String> entry : entries) {
            Long key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " => " + value);
        }
    }
}
