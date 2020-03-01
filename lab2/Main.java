import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numOfCruise = scanner.nextInt();

     

            Loader[] loaders = new Loader[30*9];
    
            for (int i = 0; i < 30*9; i++) {
                loaders[i] = new Loader(i+1);
            }

            Cruise[] cruiseSchedule = new Cruise[numOfCruise];
        

            for (int i = 0; i < numOfCruise; i++) {
                String id = scanner.next();
                if (id.substring(0, 1).equals("B")) {
                    cruiseSchedule[i] = new BigCruise(id, scanner.nextInt(), scanner.nextInt(), scanner.nextInt());
                } else {
                    cruiseSchedule[i] = new SmallCruise(id, scanner.nextInt());
                }
            }   
            
            Main.getSchedule(loaders, cruiseSchedule);
        
    }

    public static void getSchedule(Loader[] loadersArr, Cruise[] cruisesArr) {
        for (int i = 0; i < cruisesArr.length; i++) {
            int loadersNeeded = cruisesArr[i].getNumOfLoadersRequired();
            for (int j = 0; j < loadersArr.length; j++) {
                
                if (loadersArr[j].serve(cruisesArr[i]) == null) {
                    continue;
                } else {
                    System.out.println(loadersArr[j].serve(cruisesArr[i]));
                    loadersArr[j] = loadersArr[j].serve(cruisesArr[i]);
                    
                }
                loadersNeeded--;
                if (loadersNeeded == 0) {
                    break;
                }
            }   
        }
    }
}
