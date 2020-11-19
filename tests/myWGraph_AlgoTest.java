import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class myWGraph_AlgoTest {
    /**
     * test for the function getGraph
     * in the test I build a new weighted graph and added a 50 nodes
     * the assert true check if the graph and the return of the function getGraph are equals
     */
    @Test
    void getGraph() {
        weighted_graph g = new WGraph_DS();
        for (int i = 0; i < 50; i++) {
            g.addNode(i);
        }
        weighted_graph_algorithms ga = new WGraph_Algo(g);

        assertEquals(g,ga.getGraph(),"the function getGraph dosent work properly");
    }
    /**
     * test for the function copy
     * in the test I build a new weighted graph and added a 50 nodes
     * and then I added 50 edgs in the graph
     * the assert true check if the function return a deep copy of the graph
     */
    @Test
    void copy() {
        weighted_graph g = new WGraph_DS();
        for (int i = 0; i < 50; i++) {
            g.addNode(i);
        }
        for (int i = 0; i < 10; i++) {
            for (int j = 10; j < 15; j++) {
                g.connect(i, i + j, 2);
            }
        }
        weighted_graph_algorithms ga = new WGraph_Algo(g);

        weighted_graph copyOfG = ga.copy();

        assertEquals(copyOfG,ga.getGraph(),"the function copy dosent work properly");

        g.removeNode(49);
        assertNotEquals(copyOfG,ga.getGraph(),"the function copy dosent work properly");

        g.addNode(49);
        assertEquals(copyOfG,ga.getGraph(),"the function copy dosent work properly");

        g.removeEdge(0,12);
        assertNotEquals(copyOfG,ga.getGraph(),"the function copy dosent work properly");

    }
    /**
     * test for the function isConnected
     * in the test I build two weighted graph and added a 5 nodes
     * and then I connected them together
     * the assert true check if the function isConnected work properly,
     * by returning true when all the nodes are connected and false when I remove the middle node
     */
    @Test
    void isConnected() {
        weighted_graph g = new WGraph_DS();

	for (int i = 0; i < 5; i++) {
				g.addNode(i);
	}
	g.connect(0, 1, 1.2);
	g.connect(1, 2, 0);
	g.connect(2, 3, 5.2);
	g.connect(3, 4, 1.7);

	weighted_graph_algorithms ga = new WGraph_Algo(g);
	assertTrue(ga.isConnected(),"the function isConnected dosent work properly");

	g.removeNode(2);
	assertFalse(ga.isConnected(),"the function isConnected dosent work properly");

        weighted_graph g1 = new WGraph_DS();

        // check if the function work properly if the wight of edges is 0
        for (int i = 0; i < 5; i++) {
            g1.addNode(i);
        }
        g1.connect(0, 1, 0);
        g1.connect(1, 2, 0);
        g1.connect(2, 3, 0);
        g1.connect(3, 4, 0);

        weighted_graph_algorithms ga1 = new WGraph_Algo(g1);

        assertTrue(ga1.isConnected(),"the function isConnected dosent work properly");
        g1.removeNode(2);
        assertFalse(ga.isConnected(),"the function isConnected dosent work properly");
    }

    @Test
    void shortestPathDist() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void save() {
    }

    @Test
    void load() {
    }
}
