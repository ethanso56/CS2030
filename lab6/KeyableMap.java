import java.util.*;

public class KeyableMap<T, K, V extends Keyable<K>> {
    
    public T keyOfKeyableMap;
    public Map<K, V> map;
    
    public KeyableMap(T keyOfKeyableMap) {
        this.keyOfKeyableMap = keyOfKeyableMap;
        this.map = new HashMap<K, V>();
    }

    public V get(K key) {
            return this.map.get(key);
    }
    
    public KeyableMap<T, K, V> put(V item) {
        this.map.put(item.getKey(), item);     
        return this;
    }

    @Override 
    public String toString() {
        if (this.map.isEmpty()) {
            return "";
        }
        String temp = map.values().toString();
        temp = temp.substring(1, temp.length() - 1);
        return keyOfKeyableMap + ": {"+ temp + "}";
    }
}
