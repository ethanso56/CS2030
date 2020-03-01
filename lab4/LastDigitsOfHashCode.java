import java.lang.Math;

public class LastDigitsOfHashCode implements Transformer<Object, Integer> { 
    // transform the item of the box into an integer, the value of which is the last k digits of the hash code
                                    // of the content of the original box

    private final Integer k;

    public LastDigitsOfHashCode(int k) {
        this.k = k;
    }

   // public Integer transform(Integer t) {
   //     return (int) (t % (Math.pow(10, this.k)));
   // }

   // public Integer transform(String t) {
   //     int hashCode = t.hashCode();
   //     return (int) Math.abs((hashCode % (Math.pow(10, this.k))));
   // }
    
    public Integer transform(Object obj) {
        return (int) Math.abs(obj.hashCode() % Math.pow(10, k));
    }

    
}
