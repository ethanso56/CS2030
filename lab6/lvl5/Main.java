import java.util.Scanner;
import java.util.Optional;


public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfRecords = scanner.nextInt();
        Roster roster = new Roster("test");

        for (int i = 0; i < numOfRecords; i++) {
            roster.put(scanner.next(), scanner.next(), scanner.next(), scanner.next());
        }

        while (scanner.hasNext()) {
            try {
                String studentName = scanner.next();
                String code = scanner.next();
                String assessment = scanner.next();
                System.out.println(roster.getGrade(studentName, code, assessment));
            } catch (NoSuchRecordException ex) {
                System.out.println("NoSuchRecordException: " + ex.getMessage());
            }
        }
    }

}
