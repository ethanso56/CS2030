
public class BigCruise extends Cruise {

    private static final int numOfPassengersEveryMin = 50;
    private static final int metersPerLoader = 40;

    public BigCruise(String identifier, int arrivalTime, int length, int numOfPassengers) {
        super(identifier, arrivalTime, length/metersPerLoader + ((length % metersPerLoader == 0) ? 0 : 1), numOfPassengers/numOfPassengersEveryMin + ((numOfPassengers % numOfPassengersEveryMin == 0) ? 0 : 1));
    }
}
