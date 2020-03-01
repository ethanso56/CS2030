public class RecycledLoader extends Loader {
    public RecycledLoader(int id) {
        super(id);
    }

    public RecycledLoader(int id, int normalArrivalTime, int workingStartTime, int workingEndTime, String cruiseIdentifier) {
        super(id, normalArrivalTime, workingStartTime, workingEndTime + 60, cruiseIdentifier);
    }

    @Override 
    public Loader serve(Cruise cruise) {
        if (cruise == null) {
            return this;
        } else {
            if (canServe(cruise)) {
                return new RecycledLoader(this.getId(), cruise.getNormalArrivalTime(), cruise.getArrivalTime(), cruise.getServiceCompletionTime(), cruise.getIdentifier());
            } else {
                return null;
            }
        }     
    }

    @Override
    public String toString() {
        if (this.getWorkingStartTime() == -1 && this.getWorkingEndTime() == -1) {
            return "Loader " + this.getId();
        } else {
            return "Loader " + this.getId() + " (recycled) serving " + this.getCruiseIdentifier() + "@" + String.format("%04d", this.getNormalArrivalTime());
        }
    }
}
