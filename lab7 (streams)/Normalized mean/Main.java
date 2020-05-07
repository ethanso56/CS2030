import java.util.stream.*;
import java.util.DoubleSummaryStatistics;

public class Main {

    public static double normalizedMean(Stream<Integer> stream) {
        DoubleSummaryStatistics stats = stream
            .flatMapToDouble(num -> DoubleStream.of(num))
            .summaryStatistics();
        double avg = stats.getAverage();
        double min = stats.getMin();
        double max = stats.getMax();
        double result = ((avg - min) / (max - min));
        if (stats.getCount() < 2 || min == max) {
            return 0.0;
        } else {
            return result;
        }

    }
}
