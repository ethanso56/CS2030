import java.util.Optional;
import java.util.Map;
import java.util.HashMap;

public class KeyableMap<T, K, V extends Keyable<K>> implements Keyable<T>{
    public T keyOfKeyableMap;
    public Map<K, V> map;

    public KeyableMap(T keyOfKeyableMap) {
        this.keyOfKeyableMap = keyOfKeyableMap;
        this.map = new HashMap<K, V>();
    }

    public Optional<V> get(K key) {
        return Optional.ofNullable(this.map.get(key));
    }

    public KeyableMap<T, K, V> put(V item) {
        this.map.put(item.getKey(), item);
        return this;
    }

    @Override
    public T getKey() {
        return this.keyOfKeyableMap;
    }

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
       sb.append(this.keyOfKeyableMap.toString());
       sb.append(": {");
       for (V val : this.map.values()) {
           sb.append(val.toString());
           sb.append(", ");
       }
       if (this.map.size() > 0)
           sb.delete(sb.length() - 2, sb.length());
       sb.append("}");
       return sb.toString();
    }


}
