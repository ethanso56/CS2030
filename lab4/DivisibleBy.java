public class DivisibleBy implements BooleanCondition<Integer> {
    private final int num;
    
    public DivisibleBy(int num) {
        this.num = num;
    }
    
  
    public boolean test(Integer t) {
        if ((t % this.num) == 0) {
           return true;
        } else {
           return false;
       }
    }  
}
