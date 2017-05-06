package basic.collections;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TreeMapTest {
	public static void main(String[] args) {

		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("hj", 200);
		map.put("uu", 30);
		map.put("po", 100);

		Iterator<String> iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			Object key = iterator.next();
			System.out.println("map.get(key) is :" + map.get(key));
		}

	}

}
