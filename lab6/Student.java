public class Student extends KeyableMap<String, String, Module> implements Keyable<String>{
    
    public Student(String id) {
        super(id);
    }
    
    @Override
    public String getKey() {
        return super.keyOfKeyableMap;
    }

    @Override
    public Student put(Module module) {
        return (Student) super.put(module);
    }

}
