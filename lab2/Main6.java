import java.util.Scanner;

public class Main6 {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);
      int numOfCruise = scanner.nextInt();

          Loader[] loaders = new Loader[30*9];

          for (int i = 0; i < 30*9; i++) {
              if ((i+1) % 3 == 0) {
                  
                  loaders[i] = new RecycledLoader(i+1);
              } else {
                  loaders[i] = new Loader(i+1);
              }
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
         Main6.getSchedule(loaders, cruiseSchedule);
         //System.out.println(loaders[2].serve(cruiseSchedule[1]));
         // Loader x = new RecycledLoader(1);
          //System.out.println(x.serve(new Cruise("A1234", 0, 1, 30))); 
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
