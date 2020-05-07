import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Roster roster = new Roster("test");
        int numOfRecords = scanner.nextInt();

        for (int i = 0; i < numOfRecords; i++) {
            String studentName = scanner.next();

            if (roster.get(studentName) == null) {
                Student student = new Student(studentName);
                roster.put(student);
            }
            String moduleCode = scanner.next();
            Student student = roster.get(studentName);
            
            if (student.get(moduleCode) == null) {
                Module module = new Module(moduleCode);
                student.put(module);
            }
            Module module = student.get(moduleCode);
            Assessment assessment = new Assessment(scanner.next(), scanner.next());
            module.put(assessment);
        }

        while (scanner.hasNext()) {
            String studentName = scanner.next();
            String module = scanner.next();
            String assessmentName = scanner.next();

            try {
                System.out.println(roster.getGrade(studentName, module, assessmentName));
            } catch (NullPointerException ex) {
                System.out.println("NoSuchRecordException: " + ex.getMessage());
            } 

        }
        

    }


}
