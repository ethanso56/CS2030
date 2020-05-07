import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.Comparator;

public class ImmutableList<T> {
    private final List<T> list;

    //constructor that takes in a generic List containing the items
    public ImmutableList(List<T> list) {
        this.list = new ArrayList<T>(list);
    }

    // constructor that takes in a sequence
    @SafeVarargs 
    public ImmutableList(T... t) {
       
        List<T> l = new ArrayList<T>();
        for (T item: t) {
            l.add(item);
        }
        this.list = l;
    } 

    public ImmutableList<T> add(T t) {
        List<T> l = new ArrayList<T>(this.list);
        l.add(t);
        return new ImmutableList<T>(l);
    } 

    public ImmutableList<T> remove(T t) {
        List<T> l = new ArrayList<T>(this.list);
        l.remove(t);
        return new ImmutableList<T>(l);
    }

    public ImmutableList<T> replace(T t, T u) {
        List<T> l = new ArrayList<T>(this.list);
        Collections.replaceAll(l, t, u);
        return new ImmutableList<T>(l);
    }

    public ImmutableList<T> filter(Predicate<? super T> pred) {
        ArrayList<T> l = new ArrayList<>();
        for (T item: this.list) {
            if (pred.test(item)) {
                l.add(item);
            }
        }
        return new ImmutableList<T>(l);
    } 

    public <U> ImmutableList<U> map(Function <? super T, ? extends U> mapper) {
        ArrayList<U> l = new ArrayList<>();
        for (T item: this.list) {
            l.add(mapper.apply(item));
        }
        return new ImmutableList<U>(l);
    }

    public ImmutableList<T> limit(long l) {
        if (l > this.list.size()-1) {
            return new ImmutableList<T>(this.list);
        } else if (l < 0) {
            throw new IllegalArgumentException("limit size < 0");
        } else {
            ArrayList<T> newList = new ArrayList<T>(this.list.subList(0, (int) l));
            return new ImmutableList<T>(newList);
        }
    }
    
    public ImmutableList<T> sorted() throws IllegalStateException {
        if (this.list.isEmpty()) {
            return new ImmutableList<T>();
        }

        List<T> newList = new ArrayList<T>(this.list);
        List<T> result = new ArrayList<T>();
        for (int i = 0; i < newList.size(); i++) {
            if (newList.get(i) instanceof Comparable) {
                result.add(newList.get(i));
            } else {
                throw new IllegalStateException("List elements do not implement Comparable");
            }
        } 
        @SuppressWarnings("unchecked")
        Comparator<T> c = (Comparator<T>) Comparator.naturalOrder();
        result.sort(c);
        return new ImmutableList<T>(result);
    }
    
    public ImmutableList<T> sorted(Comparator<? super T> c) throws NullPointerException {
        if (c == null) {
            throw new NullPointerException("Comparator is null");
        } else {
            List<T> newList = new ArrayList<>(this.list);
            newList.sort(c);
            return new ImmutableList<T>(newList);
        }
    }

    public Object[] toArray() {
        ArrayList<T> newList = new ArrayList<T>(this.list);
        return newList.toArray();
    }

    public <U> U[] toArray(U[] u) throws ArrayStoreException {
       
        if (u == null) {
           throw new NullPointerException("Input array cannot be null");
        } 
        
        try {      
            ArrayList<T> newList = new ArrayList<T>(this.list);   
            return newList.toArray(u);
        }       
             
        catch (ArrayStoreException e) {
            throw new ArrayStoreException("Cannot add element to array as it is the wrong type");
            
        }
            
    }

    @Override
    public String toString() {
        return Arrays.toString(this.list.toArray());
    }
}
