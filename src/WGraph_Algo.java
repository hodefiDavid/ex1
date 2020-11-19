import java.io.*;
import java.util.*;

public class WGraph_Algo implements weighted_graph_algorithms {

	private weighted_graph g;

	// default constructor
	public WGraph_Algo() {
	}


	@Override
	public boolean equals(weighted_graph_algorithms wga) {
		return this.g.equals(wga.getGraph());
	}


	public WGraph_Algo(weighted_graph g) {
		init(g);
	}

	/**
	 * Init the graph on which this set of algorithms operates on.
	 * 
	 * @param g
	 */
	@Override
	public void init(weighted_graph g) {
		this.g = g;

	}

	/**
	 * Return the underlying graph of which this class works.
	 * 
	 * @return
	 */
	@Override
	public weighted_graph getGraph() {
		return this.g;
	}

	/**
	 * Compute a deep copy of this weighted graph.
	 * 
	 * @return
	 */
	@Override
	public weighted_graph copy() {

		weighted_graph g1 = new WGraph_DS();

		for (node_info i : g.getV()) {
			g1.addNode(i.getKey());
		}

		for (node_info i : g.getV()) {
			for (node_info j : g.getV(i.getKey())) {

				g1.connect(i.getKey(), j.getKey(), g.getEdge(i.getKey(), j.getKey()));
			}
		}
		return g1;
	}

	/**
	 * Returns true if and only if (iff) there is a valid path from EVREY node to
	 * each other node. NOTE: assume ubdirectional graph.
	 * 
	 * @return
	 */
	@Override
	public boolean isConnected() {

		if (this.g.getV().iterator().hasNext()) {
			node_info temp = this.g.getV().iterator().next();

			// initialize all the tags of the nodes to -1
			for (node_info i : g.getV()) {
				i.setTag(-1);
			}
			// because I set the "setDistance" function to get two nodes and the function
			// stop when its get to the destination
			// I put the temp node twice so the function does not stop until it reach all
			// the connected nodes
			setDistance(temp, temp);
			// now I check if at least one node not connected - if is not connected its tag
			// remain -1
			for (node_info i : this.g.getV())
				if (i.getTag() == -1) {
					return false;
				}
		}
		return true;
	}

	/**
	 * returns the length of the shortest path between src to dest Note: if no such
	 * path --> returns -1
	 * 
	 * @param src  - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public double shortestPathDist(int src, int dest) {

		node_info Src = g.getNode(src);
		node_info Dest = g.getNode(dest);

		if (Src != null && Dest != null) {

			// if the dest is the src itself
			if (Src == Dest)
				return 0;

			// initialize the nodes tag
			for (node_info i : g.getV()) {
				i.setTag(-1);
			}

			setDistance(Src, Dest);

			return Dest.getTag();
		}

		return -1;
	}

	/**
	 * returns the the shortest path between src to dest - as an ordered List of
	 * nodes: src--> n1-->n2-->...dest see:
	 * https://en.wikipedia.org/wiki/Shortest_path_problem Note if no such path -->
	 * returns null;
	 * 
	 * @param src  - start node
	 * @param dest - end (target) node
	 * @return
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {

		ArrayList<node_info> ll = new ArrayList<>();

		double flag = shortestPathDist(src, dest);
		if (flag != -1) {
			node_info Src = this.g.getNode(src);
			node_info Dest = this.g.getNode(dest);

			return ShortPath(Dest, Src, ll, flag);
		}

		return null;

	}

	private void setDistance(node_info n, node_info dest) {

		PriorityQueue<node_info> q = new PriorityQueue<>();

		n.setTag(0);
		q.add(n);

		while (!q.isEmpty()) {
			node_info temp = q.poll();

			boolean flag = true;

			if (dest.getTag() > 0) {
				{
					while (flag) {
						flag = false;
						if (temp != null && temp.getTag() > dest.getTag()) {
							temp = q.poll();
							flag = true;
						}
					}
				}
			}

			if (temp != null) {
				for (node_info i : g.getV(temp.getKey())) {
					double edge = this.g.getEdge(temp.getKey(), i.getKey());
					double SEdge = edge + temp.getTag();// i.getTag() == -1 ||
					if (i.getTag() == -1 || (i.getTag() > SEdge && i.getTag() != 0)) {
						q.add(i);
						i.setTag(SEdge);
					}
				}
			}
		}
	}

	/**
	 *
	 * return list with the path from one node to the other
	 *
	 * @@param node_info src
	 * @@param node_info dest
	 * @@param ArrayList<node_info> ll
	 * @@param integer distance
	 */
	private List<node_info> ShortPath(node_info dest, node_info src, ArrayList<node_info> ll, double distance) {
		// check if the nodes are even connected return an empty path if they dosnt
		// connected
		double index = distance;
		Stack<node_info> stack = new Stack<>();

		stack.add(dest);
		node_info temp = stack.peek();

		while (temp != src) {

			for (node_info i : g.getV(temp.getKey())) {
				double edge = g.getEdge(i.getKey(), temp.getKey());
				if (temp.getTag() == i.getTag() + edge && i.getTag() != -1) {
					stack.add(i);
					temp.setTag(-1);
					index = index - edge;
					temp = stack.peek();
					break;
				}
			}

		}
		int t = stack.size();
		for (int i = 0; i < t; i++) {
			ll.add(stack.pop());
		}
		return ll;
	}

	/**
	 * taken from:
	 * https://stackoverflow.com/questions/17293991/how-to-write-and-read-java-serialized-objects-into-a-file
	 * Saves this weighted (undirected) graph to the given file name
	 * 
	 * @param file - the file name (may include a relative path).
	 * @return true - iff the file was successfully saved
	 */
	@Override

	public boolean save(String file) {
		boolean ans = false;
		ObjectOutputStream oos;
		try {
			FileOutputStream fout = new FileOutputStream(file, false);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(this.g);
			ans = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ans;

	}

	/**
	 * This method load a graph to this graph algorithm. if the file was
	 * successfully loaded - the underlying graph of this class will be changed (to
	 * the loaded one), in case the graph was not loaded the original graph should
	 * remain "as is".
	 * 
	 * @param file - file name
	 * @return true - iff the graph was successfully loaded.
	 */
	@Override
	public boolean load(String file) {
		try {
			FileInputStream streamIn = new FileInputStream(file);
			ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
			weighted_graph readCase = (weighted_graph) objectinputstream.readObject();
			this.init(readCase);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}
}
