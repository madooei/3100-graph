package graph;

/**
 * Builds the chapter's road map: four cities joined by two-way roads, each road
 * added as one edge in each direction with its distance in miles. Then walks
 * the roads leaving New York and removes Providence.
 */
public class Main {

  public static void main(String[] args) {
    Graph<String> roads = new AdjacencyListGraph<>();
    roads.addVertex("Albany");
    roads.addVertex("Boston");
    roads.addVertex("New York");
    roads.addVertex("Providence");
    addRoad(roads, "Albany", "Boston", 170.0);
    addRoad(roads, "Albany", "New York", 150.0);
    addRoad(roads, "Boston", "Providence", 50.0);
    addRoad(roads, "New York", "Providence", 180.0);
    System.out.println("vertices: " + roads.numVertices());  // 4
    System.out.println("edges: " + roads.numEdges());        // 8, two per road

    System.out.println("roads leaving New York:");
    for (String neighbor : roads.neighbors("New York")) {
      double miles = roads.weight("New York", neighbor);
      System.out.println("  " + neighbor + " " + miles);  // Albany 150.0 and Providence 180.0
    }

    System.out.println("Boston -> Albany: " + roads.containsEdge("Boston", "Albany"));  // true

    System.out.println("remove Providence: " + roads.removeVertex("Providence"));  // true
    System.out.println("vertices: " + roads.numVertices());  // 3
    System.out.println("edges: " + roads.numEdges());        // 4, its two roads are gone
  }

  // Adds a two-way road as one directed edge in each direction.
  private static void addRoad(Graph<String> roads, String city, String other, double miles) {
    roads.addEdge(city, other, miles);
    roads.addEdge(other, city, miles);
  }
}
