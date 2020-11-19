import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class WGraph_DS implements weighted_graph, Serializable {

    private HashMap<Integer, node_info> graphNodes;
    private int edge_size;
    private int mode_count;

    public WGraph_DS() {
        this.graphNodes = new HashMap<>();
        this.edge_size = 0;
        this.mode_count = 0;
    }


    /**
     * return the node_info by the node_id,
     *
     * @param key - the node_id
     * @return the node_info by the node_id, null if none.
     */
    @Override
    public node_info getNode(int key) {
        if (this.graphNodes.containsKey(key) == true) {
            return this.graphNodes.get(key);
        }
        return null;
    }

    /**
     * return true iff (if and only if) there is an edge between node1 and node2
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return boolean
     */
    @Override
    public boolean hasEdge(int node1, int node2) {
        NodeInfo temp = (NodeInfo) this.graphNodes.get(node1);
        return temp.hasNi(node2);
    }

    /**
     * return the weight if the edge (node1, node1). In case
     * there is no such edge - should return -1
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     * @return double
     */
    @Override
    public double getEdge(int node1, int node2) {
        if (hasEdge(node1, node2)) {
            NodeInfo temp = (NodeInfo) this.graphNodes.get(node1);
            double edge = temp.neighborsDis.get(node2);
            return edge;
        }
        return -1;
    }

    /**
     * add a new node to the graph with the given key.
     * Note: this method should run in O(1) time.
     * Note2: if there is already a node with such a key -> no action should be performed.
     *
     * @param key
     */
    @Override
    public void addNode(int key) {
        if (!this.graphNodes.containsKey(key)) {
            this.graphNodes.put(key, new NodeInfo(key));
        }
    }

    /**
     * Connect an edge between node1 and node2, with an edge with weight >=0.
     * Note: this method should run in O(1) time.
     * Note2: if the edge node1-node2 already exists - the method simply updates the weight of the edge.
     *
     * @param node1
     * @param node2
     * @param w
     */
    @Override
    public void connect(int node1, int node2, double w) {
        if (w < 0) {
            throw new IllegalArgumentException("edge Type cannot be negative");
        }
        if (node1 == node2)
            return;

        NodeInfo a = (NodeInfo) this.getNode(node1);
        NodeInfo b = (NodeInfo) this.getNode(node2);
        if (b != null && a != null) {

            if (!this.hasEdge(node1, node2)) {

                a.addNi(b);
                b.addNi(a);
                a.neighborsDis.put(node2, w);
                b.neighborsDis.put(node1, w);

                this.mode_count++;
                this.edge_size++;
            }
        }

    }

    /**
     * This method return a pointer (shallow copy) for a
     * Collection representing all the nodes in the graph.
     * Note: this method should run in O(1) time
     *
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV() {

        return this.graphNodes.values();
    }

    /**
     * This method returns a Collection containing all the
     * nodes connected to node_id
     * Note: this method should run in O(1) time.
     *
     * @param node_id
     * @return Collection<node_info>
     */
    @Override
    public Collection<node_info> getV(int node_id) {

        if (this.graphNodes.containsKey(node_id)) {
            NodeInfo temp = (NodeInfo) this.graphNodes.get(node_id);
            return temp.getNi();
        }
        return null;
    }

    /**
     * Delete the node (with the given ID) from the graph -
     * and removes all edges which starts or ends at this node.
     * This method should run in O(n), |V|=n, as all the edges should be removed.
     *
     * @param key
     * @return the data of the removed node (null if none).
     */
    @Override
    public node_info removeNode(int key) {

        NodeInfo temp = (NodeInfo) this.graphNodes.get(key);
        if (temp != null) {

            this.graphNodes.remove(key);

            LinkedList<node_info> ll = new LinkedList<>(temp.getNi());

            for (node_info i : ll) {
                edge_size--;
                mode_count++;
                int tempKey = i.getKey();
                NodeInfo temp1 = (NodeInfo) this.graphNodes.get(tempKey);
                temp1.removeNode(temp);
                temp.removeNode(temp1);
            }

        }

        return temp;
    }


    /**
     * Delete the edge from the graph,
     * Note: this method should run in O(1) time.
     *
     * @param node1
     * @param node2
     */
    @Override
    public void removeEdge(int node1, int node2) {

        if (this.graphNodes.containsKey(node1) && this.graphNodes.containsKey(node2)) {
            NodeInfo a = (NodeInfo) this.getNode(node1);
            NodeInfo b = (NodeInfo) this.getNode(node2);

            if (this.hasEdge(node1, node2)) {

                a.getNi().remove(b);
                b.getNi().remove(a);
                a.neighborsDis.remove(node2);
                b.neighborsDis.remove(node1);

                edge_size--;
                mode_count++;
            }
        }
    }

    /**
     * return the number of vertices (nodes) in the graph.
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int nodeSize() {
        return this.graphNodes.size();
    }

    /**
     * return the number of edges (undirectional graph).
     * Note: this method should run in O(1) time.
     *
     * @return
     */
    @Override
    public int edgeSize() {
        return this.edge_size;
    }

    /**
     * return the Mode Count - for testing changes in the graph.
     * Any change in the inner state of the graph should cause an increment in the ModeCount
     *
     * @return integer
     */
    @Override
    public int getMC() {
        return this.mode_count;
    }


    /**
     * return String that represent the graph.
     *
     * @return String
     */
    @Override
    public String toString() {
        String s = "";
        for (node_info i : this.graphNodes.values())
            s += i + ": " + ((NodeInfo) i).getNi() + " | ";
        return "Graph{" +
                "\ngraphNodes= " + graphNodes +
                "\nneighborNodes " + s +
                '}';
    }

    /**
     * return true if the two graph are equal.
     *
     * @return boolean
     */
    public boolean equals(WGraph_DS g) {
        //check if the size equal in both of the graphs if not return false
        if (this.graphNodes.size() == g.getV().size()) {
            //check if the graphs have the same keys if not return false
            for (node_info i : this.getV()) {
                if (g.getNode(i.getKey()) == null) {
                    return false;
                }
            }
            //check if the graphs have the same neighbors and the same distance between each node if not return false
            for (node_info i : this.getV()) {
                for (node_info n : this.getV(i.getKey())) {
                    if (g.getEdge(i.getKey(), n.getKey()) != this.getEdge(i.getKey(), n.getKey())) {
                        return false;
                    }
                }
            }
        } else {
            return false;
        }
        return true;
    }

    private class NodeInfo implements node_info, Comparable<node_info>, Serializable {

        private int key;
        private HashMap<Integer, node_info> neighborNodes;
        private HashMap<Integer, Double> neighborsDis;
        private double Tag;
        private String remark;

        public NodeInfo(int key) {
            this.key = key;
            this.neighborNodes = new HashMap<>();
            this.neighborsDis = new HashMap<>();
            this.remark = "";
            this.setTag(-1);
        }

        //copying without neighbors
        public NodeInfo(NodeInfo n) {
            this.key = n.getKey();
            this.remark = n.getInfo();
            this.setTag(n.getTag());
            this.neighborNodes = new HashMap<>();
            this.neighborsDis = new HashMap<>();
        }


        /**
         * Return the key (id) associated with this node.
         * Note: each node_info should have a unique key.
         *
         * @return
         */
        public int getKey() {

            return this.key;
        }

        /**
         * This method returns a collection with all the Neighbor nodes of this node_info
         */

        public Collection<node_info> getNi() {
            return this.neighborNodes.values();
        }

        /**
         * This method returns a collection with all the Neighbor nodes of this node_info ande thir distance
         */

        public Collection<Double> getNiDis() {
            return this.neighborsDis.values();
        }

        /**
         * return true iff this<==>key are adjacent, as an eÃdge between them.
         *
         * @param key
         * @return
         */

        public boolean hasNi(int key) {
            return this.neighborNodes.containsKey(key);
        }

        /**
         * This method adds the node_info (t) to this node_info.
         *
         * @param t
         */

        public void addNi(NodeInfo t) {
            if (!this.neighborNodes.containsKey(t.getKey())) {
                this.neighborNodes.put(t.getKey(), t);
            }
        }

        /**
         * Removes the edge this-key,
         *
         * @param node
         */

        public void removeNode(NodeInfo node) {
            int key = node.getKey();
            if (this.neighborNodes.containsKey(key)) {
                this.neighborNodes.remove(key);
                this.neighborsDis.remove(key);
            }
        }

        /**
         * return the remark (meta data) associated with this node.
         *
         * @return
         */

        public String getInfo() {
            return this.remark;
        }

        /**
         * Allows changing the remark (meta data) associated with this node.
         *
         * @param s
         */

        public void setInfo(String s) {
            this.remark = s;
        }

        /**
         * Temporal data (aka color: e,g, white, gray, black)
         * which can be used be algorithms
         *
         * @return
         */
        public double getTag() {
            return this.Tag;
        }

        /**
         * Allow setting the "tag" value for temporal marking an node - common
         * practice for marking by algorithms.
         *
         * @param t - the new value of the tag
         */

        public void setTag(double t) {
            this.Tag = t;

        }

        @Override
        public String toString() {
            return "{" + key + "}";
        }

        @Override
        public int compareTo(node_info o) {

            if (this.Tag > o.getTag())
                return 1;
            if (this.Tag < o.getTag())
                return -1;

            return 0;
        }


    }
}
