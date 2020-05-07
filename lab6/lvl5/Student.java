import java.util.Optional;

public class Student extends KeyableMap<String, String, Module> {
    public Student(String id) {
        super(id);
    }

    public Student put(String code, String assessment, String grade) {
        Optional<Module> mod = this.get(code);
        mod.ifPresentOrElse(x -> x.put(new Assessment(assessment, grade)),
                () -> this.put(new Module(code).put(new Assessment(assessment, grade))));
        return this;
    }

    @Override
    public Student put(Module module) {
        return (Student) super.put(module);
    }

}
