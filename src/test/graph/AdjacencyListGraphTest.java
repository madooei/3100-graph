package graph;

/** Runs the Graph contract suite against AdjacencyListGraph. */
public class AdjacencyListGraphTest extends GraphTest {

  @Override
  protected Graph<String> createGraph() {
    return new AdjacencyListGraph<>();
  }
}
