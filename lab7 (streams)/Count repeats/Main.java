import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

  public static long countRepeats(int... arr) {
      int len = arr.length;
      IntStream intStream = Arrays.stream(arr);
      List<Integer> list = new ArrayList<>();
      list.add(10);
      intStream.forEach(x -> list.add(x));
      IntStream stream = IntStream.range(1, len).filter(x -> list.get(x) == list.get(x+1) && list.get(x) != list.get(x-1));
      return stream.count();
  }


}
