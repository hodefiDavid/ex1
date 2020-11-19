# Object Oriented Program Course - Assignment 1

Hi!  If you want to learn about my code in assignment 1 , you can read this file. 
this assignment is some what extend of assignment 0 where I  created a data structure that can be represent as a undirected graphs, more about this see my assignment 0 readme file,  in this assignment  I added weight to the edges between the vertices. 
this data structure represent by weighted graphs and not directed.
this data structure support a number of algorithms including the ability to save and restore the graph from a file, calculating a short route - Minimum distances
According to the weight of the edges, and finding the shortest path - A collection of vertices between the source and the destination.

## WGraph_DS class
this class implements weighted graph, and contains sub class NodeInfo that represent the vertices.
the class can create a weighted graph with the function to add vertices, get the edge between two vertices, connect vertices with a specified weight, get collection of all vertices in the graph,  get collection of all vertices that connected to specified vertex in the graph, remove vertex from the graph, remove edge between two vertices, get the amount of vertices in the graph, get the amount of edges in the graph, get mode count - the numbers of changes in the graph and the function to string that create string the represent the graph.

### NodeInfo class 
 This class represents the vertices of the graph, this class used only in WGraph_DS class and not by the end user. 
contains the function to create a new vertices, get the key of vertex, get collection of all vertices that connected to the vertex in the graph, remove the vertex's edge from the graph, get the  info-meta data associated with this vertex, get the tag -used by the algorithms, compare two vertices according to their tag and function to string that create string the represent the vertex. 


## WGraph_Algo

This class represents an Undirected (positive) Weighted Graph Theory algorithms including the function clone()-(copy), initialize graph, is connected - tell the user if there is path from every  vertex to every vertices,  shortestPathDist - tell the user how far (the shortest way) the route from one vertex to the another vertex, shortestPath - give the user the shortest way from one vertex to the another vertex, save the graph as a file and load the saved file to a new graph.   

#### Dijkstra's Algorithm
in my algorithms I used one of the versions of Dijkstra's Algorithm 
so here some  information about the Algorithm 
-   Dijkstra's Algorithm basically starts at the node that you choose (the source node) and it analyzes the graph to find the shortest path between that node and all the other nodes in the graph.
-   The algorithm keeps track of the currently known shortest distance from each node to the source node and it updates these values if it finds a shorter path.
-   Once the algorithm has found the shortest path between the source node and another node, that node is marked as "visited" and added to the path.
-   The process continues until all the nodes in the graph have been added to the path. This way, we have a path that connects the source node to all other nodes following the shortest path possible to reach each node.
(for more explanation about Dijkstra's Algorithm check out the source that I  based on https://www.freecodecamp.org/news/dijkstras-shortest-path-algorithm-visual-introduction/)

##### Example of Dijkstra's algorithm
It is easier to explain with an example about the algorithm.
the example is from https://www.programiz.com/dsa/dijkstra-algorithm
![Start with a graph](https://cdn.programiz.com/sites/tutorial2program/files/dj-1.png)

![Choose a starting vertex and assign infinity path values to all other devices](https://cdn.programiz.com/sites/tutorial2program/files/dj-2.png)
Note: in my algorithm instead of ∞ I initialize the node’s tag to -1

![Go to each vertex and update its path length](https://cdn.programiz.com/sites/tutorial2program/files/dj-3.png)
Go to each vertex and update its path length
![If the path length of the adjacent vertex is lesser than new path length, don't update it](https://cdn.programiz.com/sites/tutorial2program/files/dj-4.png)
If the path length of the adjacent vertex is lesser than new path length, don't update it
![Avoid updating path lengths of already visited vertices](https://cdn.programiz.com/sites/tutorial2program/files/dj-5.png)
Avoid updating path lengths of already visited vertices
![After each iteration, we pick the unvisited vertex with the least path length. So we choose 5 before 7](https://cdn.programiz.com/sites/tutorial2program/files/dj-6.png)
After each iteration, we pick the unvisited vertex with the least path length. So we choose 5 before 7
![Notice how the rightmost vertex has its path length updated twice](https://cdn.programiz.com/sites/tutorial2program/files/dj-7.png)
Notice how the rightmost vertex has its path length updated twice
![Repeat until all the vertices have been visited](https://cdn.programiz.com/sites/tutorial2program/files/dj-8.png)Repeat until all the vertices have been visited