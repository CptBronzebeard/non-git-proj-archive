import ru.itmo.isddev.Ilin.fraction.*;
import ru.itmo.isddev.Ilin.util.*;
import java.math.BigInteger;

public class TestLRU {
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) {
		try {
			LruCache<Integer, Integer> cache = new LruCache<Integer, Integer>(3);
			cache.put(1,1);
			cache.put(2,2);
			cache.put(3,3);
			cache.get(1);
			cache.put(4,4);
			System.out.println(cache.getMap());
	}
	catch(Throwable t) {
		t.printStackTrace();
	}
	}
}