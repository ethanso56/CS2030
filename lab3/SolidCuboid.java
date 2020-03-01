public class SolidCuboid extends Cuboid implements Solid {
    
    private final double density;

    public SolidCuboid(double height, double width, double length, double density) {
        super(height, width, length);
        this.density = density;
    }
    
    @Override
    public double getDensity() {
        return this.density;
    }
    
    @Override
    public double getMass() {
        return this.getDensity() * this.getVolume();
    }
    
    @Override 
    public SolidCuboid setHeight(double height) {
        return new SolidCuboid(height, this.width, this.length, this.density);
    }

    @Override
    public SolidCuboid setWidth(double width) {
        return new SolidCuboid(this.height, width, this.length, this.density);
    }

    @Override
    public SolidCuboid setLength(double length) {
        return new SolidCuboid(this.height, this.width, length, this.density);
    }



    @Override
    public String toString() {
        return "SolidCuboid [" + String.format("%.2f", this.height) + " x " + String.format("%.2f", this.width) + " x " + String.format("%.2f", this.length) + "]" + " with a mass of " + String.format("%.2f", this.getMass());
    }


}
