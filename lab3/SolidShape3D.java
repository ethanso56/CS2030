public class SolidShape3D {
    protected Shape3D shape3D;
    protected Material material;

    public SolidShape3D(Shape3D shape3D, Material material) {
        this.shape3D = shape3D;
        this.material = material;
    }

    public double getMass() {
        return this.material.getDensity() * shape3D.getVolume();
    }

    public double getDensity() {
        return this.material.getDensity();
    }
    
    
    @Override
    public String toString() {
        if (shape3D.getName() == "Cuboid") {
            return "SolidCuboid [" + String.format("%.2f", shape3D.getHeight()) + " x " + String.format("%.2f", shape3D.getWidth()) + " x " + String.format("%.2f", shape3D.getLength()) + "] with a mass of " + String.format("%.2f", this.getMass());
        } else {
            return "SolidSphere [" + String.format("%.2f", shape3D.getRadius()) + "] with a mass of " + String.format("%.2f", this.getMass());
        }
    }   
}
