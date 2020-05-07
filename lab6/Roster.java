public class Roster extends KeyableMap<String, String, Student> implements Keyable<String>{
    
    public Roster(String year) {
        super(year);
    }
    
    @Override
    public String getKey() {
        return super.keyOfKeyableMap;
    }

    @Override
    public Roster put(Student student) {
        return (Roster) super.put(student);
    }
    
    public String getGrade(String studentID, String module, String assessment) throws NoSuchRecordException {
        try {
            return this.get(studentID).get(module).get(assessment).getGrade();
        } catch (NullPointerException ex) {
            throw new NoSuchRecordException("No such record: " + studentID + " " + module + " " + assessment);
        }
    }
}
