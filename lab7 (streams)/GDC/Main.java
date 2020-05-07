import java.util.stream.*;
import java.util.Arrays;

class Pair {
    private final int first;
    private final int second;

    Pair(int first, int second) {
        this.first = first;
        this.second = second;
    }

    public int getFirst() {
        return this.first;
    }

    public int getSecond() {
        return this.second;
    }
}


public class Main {

    public static int gcd(int m, int n) {
        return Stream.iterate(new Pair(m, n), pair -> pair.getSecond() > 0,
                    pair -> new Pair(pair.getSecond(), pair.getFirst() % pair.getSecond())).reduce((a, b) -> b).get().getSecond();
    }



}
