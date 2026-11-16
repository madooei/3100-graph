package graph;

import map.Map;

/**
 * Adjacency-list implementation of the Graph ADT, backed by a map of maps.
 *
 * @param <V> the type of the vertices in this graph.
 */
public class AdjacencyListGraph<V> implements Graph<V> {

  private Map<V, Map<V, Double>> adjacency;  // vertex -> (out-neighbor -> weight)
  private int edgeCount;                     // how many edges the graph holds

  public AdjacencyListGraph() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean addVertex(V vertex) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean removeVertex(V vertex) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean containsVertex(V vertex) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean addEdge(V from, V to, double weight) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean addEdge(V from, V to) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean removeEdge(V from, V to) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public boolean containsEdge(V from, V to) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public double weight(V from, V to) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public Iterable<V> neighbors(V vertex) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public Iterable<V> vertices() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public int numVertices() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  @Override
  public int numEdges() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
