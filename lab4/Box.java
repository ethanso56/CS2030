public class Box<T> {
    private final T t;
    @SuppressWarnings("unchecked")
    private final static Box<?> EMPTY_BOX = new Box<>(null);
    
    private Box(T t) {
        this.t = t;
    }

    public static <U> Box<U> of(U u) {
        if (u == null) {
            return null;
        } else {
            return new Box<U>(u);
        }
    }
    
    public static <U> Box<U> ofNullable(U s) {
       if (s == null) {
          return Box.empty();
       } else {
          return of(s);
       }
   }  

    public T getT() {
        return this.t;
    }
    
    @SuppressWarnings("unchecked")
    public static <U> Box<U> empty() {  // returns empty box, box with a null item stored in it
        return (Box<U>) EMPTY_BOX;
    } 
    
    public Boolean isPresent() {
        return (this.t != null);

    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object obj) {
        if (this == Box.empty() && obj != Box.empty()) {
            return false;
        } else if (this == obj) {
            return true;
        } else if (obj instanceof Box) {          //both is box
            Box<T> newObj = (Box<T>) obj;
            if (this.t == newObj.getT()) {
                return true;
            } else {
                return this.t.equals(newObj.getT());
            }
        } else {
            return false;
        }
    }
    
    public Box<T> filter(BooleanCondition<? super T> conFunc) {
        if (this.t == null) {
            return empty();
        } else if (conFunc.test(this.t)) {
            return this;
        } else {
            return empty();
        }    
    }

    public <U> Box<U> map(Transformer<? super T, U> transformer) {
        if (this.t == null) {
            return empty();
         } else {
             return Box.ofNullable(transformer.transform(this.t));
         }
    }

    @Override
    public String toString() {
        if (this.t == null) {
            return "[]";
        } else {
            return "[" + this.t + "]";
        }
    }

}
