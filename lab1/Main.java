import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner scanner = new Scanner(System.in);
        Point[] pointArr = new Point[scanner.nextInt()];
        for (int i = 0; i < pointArr.length; i++) {
            pointArr[i] = new Point(scanner.nextDouble(), scanner.nextDouble());
        }
        System.out.println("Maximum Disc Coverage: " + maxCoverage(pointArr));

    }
    public static Circle createCircle(Point point, Point point2, double radius) {
        if (point.equalsTo(point2) || point.distanceTo(point2) > 2*radius) {
            return null;
        } else {
            Point mid = point.midPoint(point2);
            double angle = point.angleTo(point2) + Math.PI/2;
            double distance = point.distanceTo(mid);
            Point pointC = mid.moveTo(angle, Math.sqrt(Math.pow(radius, 2) - Math.pow(distance, 2)));
            return Circle.getCircle(pointC, radius);
        }
    }
    public static int maxCoverage(Point[] points) {

        int maxCoverage = 0;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                Circle c = Main.createCircle(points[i], points[j], 1); // create circle
                if (c != null) {
                    int coverage = 0;
                    for (int k = 0; k < points.length; k++) {
                        if (c.contains(points[k])) {
                            coverage++;
                        }
                    }
                    if (coverage > maxCoverage) {
                        maxCoverage = coverage;
                    }
                }    
               
            }

        }
        return maxCoverage;
    }

}
