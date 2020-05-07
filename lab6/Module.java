public class Module extends KeyableMap<String, String, Assessment> implements Keyable<String>{
    
    public Module(String moduleCode) {
        super(moduleCode);
    }
    
    @Override
    public String getKey() {
        return super.keyOfKeyableMap;
    }

    @Override
    public Module put(Assessment assessment) {
        //super.map.put(assessment.getKey(), assessment);
        //return this;
        return (Module) super.put(assessment);
    }
}
