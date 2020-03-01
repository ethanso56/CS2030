public class SolidSphere extends Sphere implements Solid {
    
    private final double density;

    public SolidSphere(double radius, double density) {
        super(radius);
        this.density = density;
    }
    @Override
    public double getDensity() {
        return this.density;
    }
    
    @Override
    public double getMass() {
        return this.density * this.getVolume();
    }
    
    @Override
    public SolidSphere setRadius(double radius) {
        return new SolidSphere(radius, this.getDensity());
    }

    
    @Override
    public String toString() {
        return "SolidSphere [" + String.format("%.2f", this.radius) + "] with a mass of " + String.format("%.2f", this.getMass());
    }
    
}
