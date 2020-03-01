public class Sphere implements Shape3D {
    protected final String name = "Sphere";
    protected final double radius;

    public Sphere(double radius) {
        this.radius = radius;
    }
    @Override
    public double getRadius() {
        return this.radius;
    }

    public Sphere setRadius(double radius) {
        return new Sphere(radius);
    }

    @Override
    public String getName() {
        return this.name;
    }
    
    @Override
    public double getVolume() {
        return (4.0 / 3) * Math.PI * Math.pow(radius, 3);
    }
    
    @Override
    public double getSurfaceArea() {
        return 4 * Math.PI * Math.pow(radius, 2);
    }
    
    @Override
    public double getHeight() {
        return -1;
    }

    @Override
    public double getWidth() {
        return -1;
    }

    @Override
    public double getLength() {
        return -1;
    }

    @Override
    public String toString() {
        return "Sphere [" + String.format("%.2f", this.radius) + "]";
    }


}
