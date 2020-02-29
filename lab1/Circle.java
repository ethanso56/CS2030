
public class Circle {
    Point center;
    double radius;

    private Circle(Point point, double radius) {
        this.center = point;
        this.radius = radius;
    }

    public static Circle getCircle(Point point, double radius) {
        if (radius == 0 || radius < 0) {
            return null;
        } else {
            return new Circle(point, radius);
        }
    }

    @Override
    public String toString() {
        return "circle of radius " + this.radius + " centered at " + this.center;
    }

    public boolean contains(Point point) {
        if (this == null) {
            return false;
        }
        return this.center.distanceTo(point) <= this.radius;
    }

}
