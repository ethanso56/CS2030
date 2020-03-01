public class LongerThan implements BooleanCondition<String> {
    private final int limit;

    public LongerThan(int limit) {
        this.limit = limit;
    }
    
    
    public boolean test(String t) {
        if (t.length() > this.limit) {
            return true;
         } else {
            return false;
         }
     } 
}
