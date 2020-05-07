import java.util.Optional;
import java.util.NoSuchElementException;

public class Roster extends KeyableMap<String, String, Student> {
    public Roster(String year) {
        super(year);
    }

    @Override
    public Roster put(Student student) {
        return (Roster) super.put(student);
    }

    public String getGrade(String studentID, String code, String assessment) throws NoSuchRecordException {
       try {
            Optional<String> grade = this.get(studentID).flatMap(x -> x.get(code)).flatMap(x -> x.get(assessment)).map(x -> x.getGrade());
            return grade.orElseThrow();
       } catch (NoSuchElementException ex) {
            throw new NoSuchRecordException(String.format("No such record: %s %s %s", studentID, code, assessment));
       }
    }

    public Roster put(String studentName, String code, String assessment, String grade) {
      Optional<Student> s = this.get(studentName);
      s.ifPresentOrElse(x -> x.put(code, assessment, grade),
              () -> this.put(new Student(studentName).put(new Module(code).put(new Assessment(assessment, grade)))));
      return this;
    }
}
