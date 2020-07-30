package ru.itmo.isddev.Ilin.util;
import java.util.LinkedHashMap;
import java.util.Map;

public class LruCache<K, V> {

    Map<K, V> map = new LinkedHashMap ();

    public LruCache (int limit) {
           map = new LinkedHashMap <K, V> (16, 0.75f, true) {
               @Override
               protected boolean removeEldestEntry(final Map.Entry<K, V> eldest) {
                   return map.size() > limit;
               }
           };
    }
    
    public void put(K key, V value) {
        map.put(key, value);
    }

    
    public V get(K key) {
        return map.get(key);
    }

    
    public void remove(K key) {
        map.remove(key);
    }

    //Testing purposes only
    public Map getMap(){
      return map;
    }

}