package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * The contract test suite for the Graph ADT, written against the Graph
 * interface alone. A concrete subclass supplies createGraph() to pick the
 * implementation.
 */
public abstract class GraphTest {

  protected Graph<String> graph;

  protected abstract Graph<String> createGraph();

  @BeforeEach
  public void setup() {
    graph = createGraph();
  }

  @Test
  public void newGraphHasNoVertices() {
    assertEquals(0, graph.numVertices());
  }

  @Test
  public void newGraphHasNoEdges() {
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void verticesOfNewGraphIsEmpty() {
    assertFalse(graph.vertices().iterator().hasNext());
  }

  @Test
  public void containsVertexOnEmptyGraphReturnsFalse() {
    assertFalse(graph.containsVertex("Paris"));
  }

  @Test
  public void removeVertexOnEmptyGraphReturnsFalse() {
    assertFalse(graph.removeVertex("Paris"));
  }

  @Test
  public void containsEdgeOnEmptyGraphReturnsFalse() {
    assertFalse(graph.containsEdge("Paris", "Lyon"));
  }

  @Test
  public void removeEdgeOnEmptyGraphReturnsFalse() {
    assertFalse(graph.removeEdge("Paris", "Lyon"));
  }

  @Test
  public void neighborsOnEmptyGraphThrows() {
    try {
      graph.neighbors("Paris");
      fail("expected IllegalArgumentException when the vertex is not in the graph");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void weightOnEmptyGraphThrows() {
    try {
      graph.weight("Paris", "Lyon");
      fail("expected IllegalArgumentException when the edge is not present");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeOnEmptyGraphThrows() {
    try {
      graph.addEdge("Paris", "Lyon", 5.0);
      fail("expected IllegalArgumentException when the endpoints are not in the graph");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addVertexNewReturnsTrue() {
    assertTrue(graph.addVertex("Paris"));
  }

  @Test
  public void addVertexGrowsNumVertices() {
    graph.addVertex("Paris");
    assertEquals(1, graph.numVertices());
  }

  @Test
  public void containsVertexAddedReturnsTrue() {
    graph.addVertex("Paris");
    assertTrue(graph.containsVertex("Paris"));
  }

  @Test
  public void containsVertexAbsentReturnsFalse() {
    graph.addVertex("Paris");
    assertFalse(graph.containsVertex("Lyon"));
  }

  @Test
  public void addVertexDoesNotAddEdges() {
    graph.addVertex("Paris");
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void addVertexDuplicateReturnsFalse() {
    graph.addVertex("Paris");
    assertFalse(graph.addVertex("Paris"));
  }

  @Test
  public void addVertexDuplicateKeepsNumVertices() {
    graph.addVertex("Paris");
    graph.addVertex("Paris");
    assertEquals(1, graph.numVertices());
  }

  @Test
  public void verticesOfOneVertexGraphIsThatVertex() {
    graph.addVertex("Paris");
    assertEquals(listOf("Paris"), sorted(graph.vertices()));
  }

  @Test
  public void neighborsOfVertexWithNoEdgesIsEmpty() {
    graph.addVertex("Paris");
    assertFalse(graph.neighbors("Paris").iterator().hasNext());
  }

  @Test
  public void removeOnlyVertexReturnsTrue() {
    graph.addVertex("Paris");
    assertTrue(graph.removeVertex("Paris"));
  }

  @Test
  public void removeOnlyVertexTakesItOut() {
    graph.addVertex("Paris");
    graph.removeVertex("Paris");
    assertFalse(graph.containsVertex("Paris"));
  }

  @Test
  public void removeOnlyVertexLeavesNoVertices() {
    graph.addVertex("Paris");
    graph.removeVertex("Paris");
    assertEquals(0, graph.numVertices());
  }

  @Test
  public void removeVertexAbsentReturnsFalse() {
    graph.addVertex("Paris");
    assertFalse(graph.removeVertex("Lyon"));
  }

  @Test
  public void removeVertexAbsentKeepsNumVertices() {
    graph.addVertex("Paris");
    graph.removeVertex("Lyon");
    assertEquals(1, graph.numVertices());
  }

  @Test
  public void removedVertexCanBeAddedAgain() {
    graph.addVertex("Paris");
    graph.removeVertex("Paris");
    assertTrue(graph.addVertex("Paris"));
  }

  @Test
  public void addEdgeNewReturnsTrue() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    assertTrue(graph.addEdge("Paris", "Lyon", 5.0));
  }

  @Test
  public void addEdgeGrowsNumEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertEquals(1, graph.numEdges());
  }

  @Test
  public void addEdgeKeepsNumVertices() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertEquals(2, graph.numVertices());
  }

  @Test
  public void containsEdgeAddedReturnsTrue() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertTrue(graph.containsEdge("Paris", "Lyon"));
  }

  @Test
  public void edgeIsDirected() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertFalse(graph.containsEdge("Lyon", "Paris"));
  }

  @Test
  public void weightReturnsTheRecordedWeight() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertEquals(5.0, graph.weight("Paris", "Lyon"));
  }

  @Test
  public void weightOfReverseDirectionThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    try {
      graph.weight("Lyon", "Paris");
      fail("expected IllegalArgumentException when the edge is not present");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void weightOfAbsentEdgeThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.weight("Paris", "Lyon");
      fail("expected IllegalArgumentException when the edge is not present");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeWithoutWeightReturnsTrue() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    assertTrue(graph.addEdge("Paris", "Lyon"));
  }

  @Test
  public void addEdgeWithoutWeightRecordsWeightOne() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon");
    assertEquals(1.0, graph.weight("Paris", "Lyon"));
  }

  @Test
  public void addEdgeDuplicateReturnsFalse() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertFalse(graph.addEdge("Paris", "Lyon", 9.0));
  }

  @Test
  public void addEdgeDuplicateReplacesTheWeight() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Paris", "Lyon", 9.0);
    assertEquals(9.0, graph.weight("Paris", "Lyon"));
  }

  @Test
  public void addEdgeDuplicateKeepsNumEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Paris", "Lyon", 9.0);
    assertEquals(1, graph.numEdges());
  }

  @Test
  public void addEdgeWithoutWeightDuplicateReplacesTheWeight() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Paris", "Lyon");
    assertEquals(1.0, graph.weight("Paris", "Lyon"));
  }

  @Test
  public void addVertexDuplicateKeepsItsEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addVertex("Paris");
    assertTrue(graph.containsEdge("Paris", "Lyon"));
  }

  @Test
  public void neighborsOfSourceIsTheTarget() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertEquals(listOf("Lyon"), sorted(graph.neighbors("Paris")));
  }

  @Test
  public void neighborsOfTargetIsEmpty() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertFalse(graph.neighbors("Lyon").iterator().hasNext());
  }

  @Test
  public void removeEdgeReturnsTrue() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertTrue(graph.removeEdge("Paris", "Lyon"));
  }

  @Test
  public void removeEdgeTakesItOut() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeEdge("Paris", "Lyon");
    assertFalse(graph.containsEdge("Paris", "Lyon"));
  }

  @Test
  public void removeEdgeShrinksNumEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeEdge("Paris", "Lyon");
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void removeEdgeKeepsBothVertices() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeEdge("Paris", "Lyon");
    assertEquals(2, graph.numVertices());
  }

  @Test
  public void removeEdgeTwiceReturnsFalse() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeEdge("Paris", "Lyon");
    assertFalse(graph.removeEdge("Paris", "Lyon"));
  }

  @Test
  public void removeEdgeReverseDirectionReturnsFalse() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertFalse(graph.removeEdge("Lyon", "Paris"));
  }

  @Test
  public void removeEdgeReverseDirectionKeepsTheEdge() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeEdge("Lyon", "Paris");
    assertTrue(graph.containsEdge("Paris", "Lyon"));
  }

  @Test
  public void removeEdgeAbsentKeepsNumEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeEdge("Lyon", "Paris");
    assertEquals(1, graph.numEdges());
  }

  @Test
  public void removeEdgeFromAbsentVertexReturnsFalse() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertFalse(graph.removeEdge("Nice", "Lyon"));
  }

  @Test
  public void containsEdgeFromAbsentVertexReturnsFalse() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    assertFalse(graph.containsEdge("Nice", "Lyon"));
  }

  @Test
  public void removeSourceVertexRemovesItsEdge() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeVertex("Paris");
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void removeTargetVertexRemovesTheEdgeIntoIt() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeVertex("Lyon");
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void removeTargetVertexLeavesSourceWithNoNeighbors() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.removeVertex("Lyon");
    assertFalse(graph.neighbors("Paris").iterator().hasNext());
  }

  @Test
  public void undirectedRoadIsTwoEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon");
    graph.addEdge("Lyon", "Paris");
    assertEquals(2, graph.numEdges());
  }

  @Test
  public void undirectedRoadContainsReverseEdge() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon");
    graph.addEdge("Lyon", "Paris");
    assertTrue(graph.containsEdge("Lyon", "Paris"));
  }

  @Test
  public void edgesInBothDirectionsKeepSeparateWeights() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Lyon", "Paris", 7.0);
    assertEquals(5.0, graph.weight("Paris", "Lyon"));
  }

  @Test
  public void verticesVisitsEveryVertexOnce() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    assertEquals(listOf("Lyon", "Nice", "Paris"), sorted(graph.vertices()));
  }

  @Test
  public void neighborsVisitsEveryOutNeighborOnce() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Paris", "Nice", 2.0);
    assertEquals(listOf("Lyon", "Nice"), sorted(graph.neighbors("Paris")));
  }

  @Test
  public void neighborsDoesNotIncludeInNeighbors() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Paris", "Nice", 2.0);
    graph.addEdge("Lyon", "Nice", 3.0);
    assertEquals(listOf("Nice"), sorted(graph.neighbors("Lyon")));
  }

  @Test
  public void removeEdgeKeepsTheOtherEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Paris", "Nice", 2.0);
    graph.removeEdge("Paris", "Lyon");
    assertTrue(graph.containsEdge("Paris", "Nice"));
  }

  @Test
  public void removeVertexRemovesItsOutEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Lyon", "Nice", 3.0);
    graph.addEdge("Paris", "Nice", 2.0);
    graph.removeVertex("Lyon");
    assertFalse(graph.containsEdge("Lyon", "Nice"));
  }

  @Test
  public void removeVertexRemovesItsInEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Lyon", "Nice", 3.0);
    graph.addEdge("Paris", "Nice", 2.0);
    graph.removeVertex("Lyon");
    assertFalse(graph.containsEdge("Paris", "Lyon"));
  }

  @Test
  public void removeVertexShrinksNumEdgesByItsEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Lyon", "Nice", 3.0);
    graph.addEdge("Paris", "Nice", 2.0);
    graph.removeVertex("Lyon");
    assertEquals(1, graph.numEdges());
  }

  @Test
  public void removeVertexKeepsTheOtherEdges() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Lyon", "Nice", 3.0);
    graph.addEdge("Paris", "Nice", 2.0);
    graph.removeVertex("Lyon");
    assertTrue(graph.containsEdge("Paris", "Nice"));
  }

  @Test
  public void removeVertexKeepsTheOtherVertices() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    graph.addVertex("Nice");
    graph.addEdge("Paris", "Lyon", 5.0);
    graph.addEdge("Lyon", "Nice", 3.0);
    graph.addEdge("Paris", "Nice", 2.0);
    graph.removeVertex("Lyon");
    assertEquals(listOf("Nice", "Paris"), sorted(graph.vertices()));
  }

  @Test
  public void addManyVerticesKeepsEveryVertex() {
    for (int i = 0; i < 20; i++) {
      graph.addVertex("city" + i);
    }
    assertEquals(20, graph.numVertices());
  }

  @Test
  public void addManyEdgesFromOneVertexKeepsEveryNeighbor() {
    for (int i = 0; i < 20; i++) {
      graph.addVertex("city" + i);
    }
    for (int i = 1; i < 20; i++) {
      graph.addEdge("city0", "city" + i);
    }
    int count = 0;
    for (String neighbor : graph.neighbors("city0")) {
      count++;
    }
    assertEquals(19, count);
  }

  @Test
  public void removeVertexWithManyInEdgesRemovesThemAll() {
    for (int i = 0; i < 20; i++) {
      graph.addVertex("city" + i);
    }
    for (int i = 1; i < 20; i++) {
      graph.addEdge("city" + i, "city0");
    }
    graph.removeVertex("city0");
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void removeVertexWithManyInEdgesLeavesNoNeighborPointingAtIt() {
    for (int i = 0; i < 20; i++) {
      graph.addVertex("city" + i);
    }
    for (int i = 1; i < 20; i++) {
      graph.addEdge("city" + i, "city0");
    }
    graph.removeVertex("city0");
    for (int i = 1; i < 20; i++) {
      assertFalse(graph.neighbors("city" + i).iterator().hasNext());
    }
  }

  @Test
  public void addEdgeMissingFromThrows() {
    graph.addVertex("Lyon");
    try {
      graph.addEdge("Paris", "Lyon", 5.0);
      fail("expected IllegalArgumentException when from is not in the graph");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeMissingToThrows() {
    graph.addVertex("Paris");
    try {
      graph.addEdge("Paris", "Lyon", 5.0);
      fail("expected IllegalArgumentException when to is not in the graph");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeSelfLoopThrows() {
    graph.addVertex("Paris");
    try {
      graph.addEdge("Paris", "Paris", 5.0);
      fail("expected IllegalArgumentException when from and to are equal");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeWithoutWeightMissingEndpointThrows() {
    graph.addVertex("Paris");
    try {
      graph.addEdge("Paris", "Lyon");
      fail("expected IllegalArgumentException when to is not in the graph");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeWithoutWeightSelfLoopThrows() {
    graph.addVertex("Paris");
    try {
      graph.addEdge("Paris", "Paris");
      fail("expected IllegalArgumentException when from and to are equal");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeMissingEndpointDoesNotAddAnEdge() {
    graph.addVertex("Paris");
    try {
      graph.addEdge("Paris", "Lyon", 5.0);
    } catch (IllegalArgumentException e) {
      // the failed call should leave the graph unchanged
    }
    assertEquals(0, graph.numEdges());
  }

  @Test
  public void neighborsMissingVertexThrows() {
    graph.addVertex("Paris");
    try {
      graph.neighbors("Lyon");
      fail("expected IllegalArgumentException when the vertex is not in the graph");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addVertexNullThrows() {
    try {
      graph.addVertex(null);
      fail("expected IllegalArgumentException when adding a null vertex");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void removeVertexNullThrows() {
    try {
      graph.removeVertex(null);
      fail("expected IllegalArgumentException when removing a null vertex");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void containsVertexNullThrows() {
    try {
      graph.containsVertex(null);
      fail("expected IllegalArgumentException when calling containsVertex with null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeNullFromThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.addEdge(null, "Lyon", 5.0);
      fail("expected IllegalArgumentException when from is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeNullToThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.addEdge("Paris", null, 5.0);
      fail("expected IllegalArgumentException when to is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void addEdgeWithoutWeightNullFromThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.addEdge(null, "Lyon");
      fail("expected IllegalArgumentException when from is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void removeEdgeNullFromThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.removeEdge(null, "Lyon");
      fail("expected IllegalArgumentException when from is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void removeEdgeNullToThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.removeEdge("Paris", null);
      fail("expected IllegalArgumentException when to is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void containsEdgeNullFromThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.containsEdge(null, "Lyon");
      fail("expected IllegalArgumentException when from is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void containsEdgeNullToThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.containsEdge("Paris", null);
      fail("expected IllegalArgumentException when to is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void weightNullFromThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.weight(null, "Lyon");
      fail("expected IllegalArgumentException when from is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void weightNullToThrows() {
    graph.addVertex("Paris");
    graph.addVertex("Lyon");
    try {
      graph.weight("Paris", null);
      fail("expected IllegalArgumentException when to is null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  @Test
  public void neighborsNullThrows() {
    try {
      graph.neighbors(null);
      fail("expected IllegalArgumentException when calling neighbors with null");
    } catch (IllegalArgumentException e) {
      return;
    }
  }

  // The vertices an iterable visits, in ascending order.
  private List<String> sorted(Iterable<String> vertices) {
    List<String> result = new ArrayList<>();
    for (String vertex : vertices) {
      result.add(vertex);
    }
    Collections.sort(result);
    return result;
  }

  // The given vertices as a list, in the order given.
  private List<String> listOf(String... vertices) {
    List<String> result = new ArrayList<>();
    for (String vertex : vertices) {
      result.add(vertex);
    }
    return result;
  }
}
