public class Material {
    protected String name;
    protected double density;

    public Material(String name, double density) {
        this.name = name;
        this.density = density;
    }

    public double getDensity() {
        return this.density;
    }

    @Override
    public String toString() {
       return "";
    }

}
