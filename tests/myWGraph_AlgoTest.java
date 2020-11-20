import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

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
    /**
     * test for the function shortestPathDist
     * in the test I build two weighted graph and added a few nodes
     * and then I connected them together
     * the assert true check if the function shortestPathDist work properly,
     * by returning the right value in both of the graphs
     */
    @Test
    void shortestPathDist() {

        weighted_graph g = new WGraph_DS();
        for (int i = 0; i < 9; i++) {
            g.addNode(i);
        }
        g.connect(0,2,0);
        g.connect(0,3,2);
        g.connect(0,1,2);
        g.connect(1,2,3);
        g.connect(1,4,0);
        g.connect(3,7,8);
        g.connect(3,8,8);
        g.connect(4,5,1);
        g.connect(5,6,2.5);
        g.connect(6,7,5);
        g.connect(8,7,0.5);

        weighted_graph_algorithms ga = new WGraph_Algo(g);
        assertTrue(ga.shortestPathDist(0,7)==10,"the function shortestPathDist dosent work properly");

        // check if the function work properly if the wight of edges is 0
        weighted_graph g1 = new WGraph_DS();
        for (int i = 0; i < 6; i++) {
            g1.addNode(i);
        }

        g1.connect(0,5,0);
        g1.connect(0,1,0);
        g1.connect(1,2,1);
        g1.connect(2,3,0);
        g1.connect(5,4,2);
        g1.connect(3,4,0);

        weighted_graph_algorithms ga1 = new WGraph_Algo(g1);

        assertTrue(ga1.shortestPathDist(0,3)==1,"the function shortestPathDist dosent work properly");


    }
    /**
     * test for the function shortestPath
     * in the test I build two weighted graph and added a few nodes
     * and then I connected them together
     * the assert true check if the function shortestPath work properly,
     * by returning the right list in both of the graphs
     */
    @Test
    void shortestPath() {
        weighted_graph g = new WGraph_DS();
        for (int i = 0; i < 9; i++) {
            g.addNode(i);
        }
        g.connect(0,2,0);
        g.connect(0,3,2);
        g.connect(0,1,2);
        g.connect(1,2,3);
        g.connect(1,4,0);
        g.connect(3,7,8);
        g.connect(3,8,8);
        g.connect(4,5,1);
        g.connect(5,6,2.5);
        g.connect(6,7,5);
        g.connect(8,7,0.5);

        weighted_graph_algorithms ga = new WGraph_Algo(g);
        List<node_info> ll =  ga.shortestPath(0,7);

        assertTrue(ll.get(0).getKey()==0,"the function shortestPath dosent work properly");
        assertTrue(ll.get(1).getKey()==3,"the function shortestPath dosent work properly");
        assertTrue(ll.get(2).getKey()==7,"the function shortestPath dosent work properly");


        // check if the function work properly if the wight of edges is 0
        weighted_graph g1 = new WGraph_DS();
        for (int i = 0; i < 6; i++) {
            g1.addNode(i);
        }

        g1.connect(0,5,0);
        g1.connect(0,1,0);
        g1.connect(1,2,1);
        g1.connect(2,3,0);
        g1.connect(5,4,2);
        g1.connect(3,4,0);

        weighted_graph_algorithms ga1 = new WGraph_Algo(g1);
        List<node_info> ll1 =  ga1.shortestPath(0,3);
        assertTrue(ll1.get(0).getKey()==0,"the function shortestPath dosent work properly");
        assertTrue(ll1.get(1).getKey()==1,"the function shortestPath dosent work properly");
        assertTrue(ll1.get(2).getKey()==2,"the function shortestPath dosent work properly");
        assertTrue(ll1.get(3).getKey()==3,"the function shortestPath dosent work properly");

    }
    /**
     * test for the functions saveAndLoad
     * in the test I build a new weighted graph and added a few nodes
     * and then I connected them together
     * the assert equal check if the function saveAndLoad work properly,
     * by returning true when I build a new weighted graph from the save file
     * and after changing the source graph the assert equal return false
     * because loading a new graph from the save file is a deep copy of the graph
     */
    @Test
    void saveAndLoad() {
        weighted_graph g = new WGraph_DS();
        for (int i = 0; i < 9; i++) {
            g.addNode(i);
        }
        g.connect(0,2,0);
        g.connect(0,3,2);
        g.connect(0,1,2);
        g.connect(1,2,3);
        g.connect(1,4,0);
        g.connect(3,7,8);
        g.connect(3,8,8);
        g.connect(4,5,1);
        g.connect(5,6,2.5);
        g.connect(6,7,5);
        g.connect(8,7,0.5);

        weighted_graph_algorithms ga = new WGraph_Algo(g);
        ga.save("g_save");

        weighted_graph_algorithms ga1 =new WGraph_Algo();
        ga1.load("g_save");

        assertEquals(ga.getGraph(),ga1.getGraph(),"the functions save and load dosent work properly");

        g.removeNode(3);
        assertNotEquals(ga.getGraph(),ga1.getGraph(),"the functions save and load dosent work properly");

    }

    /**
     * test for the speed of the algorithms
     *
     */
    @Test
    void speed() {
        long start = new Date().getTime();

        weighted_graph g = new WGraph_DS();
        for (int i = 0; i < 100000; i++) {
            g.addNode(i);
        }
        double e = Math.E;
        for (int i = 0; i < 100000-10; i++) {
            for (int j = 0; j < 10; j++) {
                g.connect(i,j+i,((e*j)%50));
            }
        }
        weighted_graph_algorithms ga = new WGraph_Algo(g);
        List<node_info> ll = ga.shortestPath(0,3000);
        assertTrue(ll.size()==857,"the speed test(shortestPath) dosent work properly");

        double sum = 0;
        for(int i =0; i< ll.size()-1;i++){
            sum= sum+ g.getEdge(ll.get(i).getKey(),ll.get(i+1).getKey());
        }

        double shortestPathDistVal = ga.shortestPathDist(0,3000);

       assertTrue(shortestPathDistVal==sum,"the speed test(shortestPathDist) dosent work properly");


        weighted_graph g1 = new WGraph_DS();
        for (int i = 0; i < 1000; i++) {
            g1.addNode(i);
        }
        for (int i = 0; i < 500; i++) {
                g1.connect(0,i,((e*i)%50));
            }
        for (int i = 500; i < 1000; i++) {
            g1.connect(1,i,((e*i)%50));
        }
        weighted_graph_algorithms ga1 = new WGraph_Algo(g1);


        ga1.save("ga1_save");

        weighted_graph_algorithms ga2 =new WGraph_Algo();
        ga2.load("ga1_save");

        assertTrue(ga1.getGraph().nodeSize()==ga2.getGraph().nodeSize(),"the speed test(save and load) dosent work properly");
        assertTrue(ga1.getGraph().edgeSize()==ga2.getGraph().edgeSize(),"the speed test(save and load) dosent work properly");

        assertTrue(ga1.isConnected(),"the speed test(isConnected) dosent work properly");

        long end = new Date().getTime();
        double dt = (end-start)/1000.0;

        assertTrue(dt<15,"the speed test fail ");

    }

}
