import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Encapsulates the result of a query: for a bus stop and a search string,
 * it stores a map of bus services that service stops with matching name.
 * e.g., stop 12345, name "MRT", with map contains:
 *    96: 11223 "Clementi MRT"
 *    95:  22334 "Beuno Vista MRT"
 *
 * @author: Ooi Wei Tsang
 * @version: CS2030 AY19/20 Semester 1, Lab 10
 */
class BusRoutes {
  final BusStop stop;
  final String name;
  final Map<BusService, CompletableFuture<Set<BusStop>>> services;

  /**
   * Constructor for creating a bus route.
   *
   * @param stop     The first bus stop.
   * @param name     The second bus stop.
   * @param services The set of bus services between the two stops.
   */


  public BusRoutes(BusStop stop, String name, Map<BusService, CompletableFuture<Set<BusStop>>> services) {
    this.stop = stop;
    this.name = name;
    this.services = services;
  }


  /**
   * Return a string describing the bus route.
   *
   * @return The first line contains the query information:
   * bus stop id and search string.  The remaining line contains
   * the bus services and matching stops served.
   */
  public CompletableFuture<String> description() {
    return CompletableFuture.supplyAsync(
            () -> {
              String result = "Search for: " + this.stop + " <-> " + name + ":\n";
              result += "From " + this.stop + "\n";

              for (BusService service : services.keySet()) {
                CompletableFuture<Set<BusStop>> stops = services.get(service);
                try {
                  result += describeService(service, stops).get();
                } catch (InterruptedException e) {
                  e.printStackTrace();
                } catch (ExecutionException e) {
                  e.printStackTrace();
                }
              }
              return result;
            }
    );


  }

  /**
   * Return a string representation a bus service and its matching stops.
   *
   * @return The first line contains the query information:
   * bus stop id and search string.  The remaining line contains
   * the bus services and matching stops served.
   */
  public CompletableFuture<String> describeService(BusService service, CompletableFuture<Set<BusStop>> stops) {
    return stops.thenApply(x -> {
      if (x.isEmpty()) {
        return "";
      }
      return x.stream()
              .filter(stop -> stop != this.stop)
              .reduce("- Can take " + service + " to:\n",
                      (str, stop) -> str += "  - " + stop + "\n",
                      (str1, str2) -> str1 + str2);
    });
  }
}