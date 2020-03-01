public class Cruise {
    private final String identifier;
    private final int arrivalTime;   //HHMM
    private final int numOfLoaders;
    private final int serviceTime;

    public Cruise(String identifier, int arrivalTime, int numOfLoaders, int serviceTime) {
        this.identifier = identifier;
        this.arrivalTime = arrivalTime;
        this.numOfLoaders = numOfLoaders;
        this.serviceTime = serviceTime;
    }

    public int getNormalArrivalTime() {
	    return this.arrivalTime;
    }

    public int getArrivalTime() {
        int hours = this.arrivalTime/100;
        int mins = this.arrivalTime%100;
        return hours*60 + mins;
    }

    public int getServiceCompletionTime() {
        return getArrivalTime() + serviceTime;
    }

    public int getNumOfLoadersRequired() {
        return this.numOfLoaders;
    }

    public String getIdentifier() {
	     return this.identifier;
    }


    @Override
    public String toString() {
        return identifier + "@" + String.format("%04d", arrivalTime);
    }
}
