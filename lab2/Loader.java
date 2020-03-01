public class Loader {
   
    private final int id;
    private final int normalArrivalTime;
    private final int workingStartTime;
    private final int workingEndTime;
    private final String cruiseIdentifier;

    Loader(int id) {
        this.id = id;
        this.normalArrivalTime = -1;
	    this.workingStartTime = -1;
    	this.workingEndTime = -1;
        this.cruiseIdentifier = null;
    }  

    Loader(int id, int normalArrivalTime, int workingStartTime, int workingEndTime, String cruiseIdentifier) {
        this.id = id;
        this.normalArrivalTime = normalArrivalTime;
        this.workingStartTime = workingStartTime;
        this.workingEndTime = workingEndTime;
        this.cruiseIdentifier = cruiseIdentifier;
        
    }

    public int getId() {
        return this.id;
    }
    public int getNormalArrivalTime() {
        return this.normalArrivalTime;
    }

    public int getWorkingStartTime() {
        return this.workingStartTime;
    }

    public int getWorkingEndTime() {
        return this.workingEndTime;
    }
    public String getCruiseIdentifier() {
        return this.cruiseIdentifier;
    }


    public boolean isWorking() {
	if (this.workingStartTime == -1 && this.workingEndTime == -1) {
		return false;
	} else {
		return true;
	}
   } 

//    public boolean canServe(Cruise cruise) {
         // if arrival time is between the arrival time and service completion time
//         if (isWorking()) {
//		 if  (cruise.getArrivalTime() >= this.workingStartTime && cruise.getArrivalTime() <= this.workingEndTime) {
//            		 return false;
//        	 } else {
//        		 return true;
//		 }		 
//   	 } else {
//		 return true;
//	    }
//    }
//

   public boolean canServe(Cruise cruise) {
       if (isWorking()) {
           if (cruise.getArrivalTime() >= this.workingStartTime && cruise.getArrivalTime() < this.workingEndTime) {
               return false;
           } else if (cruise.getServiceCompletionTime() > this.workingStartTime && cruise.getServiceCompletionTime() < this.workingEndTime) {
               return false;
           } else {
               return true;
           }    
       } else {
           return true;
       }
  }
    
    public Loader serve(Cruise cruise) {
        if (cruise == null) {
            return this;
        } else {
             if (canServe(cruise)) {
                return new Loader(this.id, cruise.getNormalArrivalTime(),  cruise.getArrivalTime(), cruise.getServiceCompletionTime(), cruise.getIdentifier());
             } else {
                return null;
            }
        }
    }


    @Override
    public String toString() {
        if (this.workingStartTime == -1 && this.workingEndTime == -1) {            return "Loader " + id;
        } else {
           return "Loader " + id + " serving " + cruiseIdentifier + "@" + String.format("%04d", normalArrivalTime);
        }
    }
 }
