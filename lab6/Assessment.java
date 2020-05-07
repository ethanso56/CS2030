public class Assessment implements Keyable<String> {
    String name;
    String grade;

    public Assessment(String name, String grade) {
        this.name = name;
        this.grade = grade;
    }
    
    public String getGrade() {
        return this.grade;
    }
    
    @Override
    public String getKey() {
        return this.name;
    }
    @Override
    public String toString() {
        return "{" + this.name + ": " + this.grade + "}";
    }
}
