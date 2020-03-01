public class Cuboid implements Shape3D {

    protected final String name = "Cuboid";
    protected final double height;
    protected final double width;
    protected final double length;

    public Cuboid(double height, double width, double length) {
        this.height = height;
        this.width = width;
        this.length = length;
    }
    @Override
    public double getHeight() {
        return this.height;
    }
    @Override
    public double getWidth() {
        return this.width;
    }
    @Override
    public double getLength() {
        return this.length;
    }

    @Override

    public double getRadius() {
        return -1;
    }

    @Override
    public double getVolume() {
        return this.height*this.width*this.length;
    }

    @Override
    public double getSurfaceArea() {
        return ((this.width*this.length)*2) + ((this.width*this.height)*2) + ((this.length*this.height)*2);
    }

    @Override
    public String getName() {
        return this.name;
    }

    public Cuboid setHeight(double height) {
        return new Cuboid(height, this.width, this.length);
    }

    public Cuboid setWidth(double width) {
        return new Cuboid(this.height, width, this.length);
    }

    public Cuboid setLength(double length) {
        return new Cuboid(this.height, this.width, length);
    }
    @Override
    public String toString() {
        return "Cuboid [" + String.format("%.2f",this.height) + " x " + String.format("%.2f", this.width) + " x " + String.format("%.2f",this.length) + "]";
    }



}
