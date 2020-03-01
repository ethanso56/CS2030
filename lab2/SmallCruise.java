public class SmallCruise extends Cruise {

    private static final int numOfLoaders = 1;
    private static final int serviceTime = 30;

    public SmallCruise(String identifier, int arrivalTime) {
        super(identifier, arrivalTime, numOfLoaders, serviceTime);
    }

}
