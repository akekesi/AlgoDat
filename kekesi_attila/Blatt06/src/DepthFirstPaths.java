/******************************************************************************
 *  Compilation:  javac DepthFirstPaths.java
 *  Execution:    java DepthFirstPaths G s
 *  Dependencies: Graph.java
 ******************************************************************************/

/**
 *  The {@code DepthFirstPaths} class represents a data type for finding
 *  paths from a source vertex <em>s</em> to every other vertex
 *  in an undirected graph.
 *  <p>
 *  This implementation uses depth-first search.
 *  The constructor takes time proportional to <em>V</em> + <em>E</em>,
 *  where <em>V</em> is the number of vertices and <em>E</em> is the number of edges.
 *  Each call to {@link #hasPathTo(int)} takes constant time;
 *  each call to {@link #pathTo(int)} takes time proportional to the length
 *  of the path.
 *  It uses extra space (not including the graph) proportional to <em>V</em>.
 *  <p>
 *  For additional documentation, see <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>   
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 *  
 *  DISCLAIMER:
 *  These methods have been partly adjusted to fit the excersise.
 */
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;


public class DepthFirstPaths {
    private boolean[] marked;           // marked[v] = is there an s-v path?
    private int[] edgeTo;               // edgeTo[v] = last adjacent node on s-v path (parent)
    private int[] distTo;               // distTo[v] = number of edges s-v path
    private final int s;                // source vertex
    private Queue<Integer> preorder;    // vertices in preorder
    private Queue<Integer> postorder;   // vertices in postorder

    /**
     * Computes a path between {@code s} and every other vertex in graph {@code G}.
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        distTo = new int[G.V()];
        marked = new boolean[G.V()];
        postorder = new LinkedList<Integer>();
        preorder  = new LinkedList<Integer>();

        validateVertex(s);
    }
    
    public void dfs(Graph G) {
    	dfs(G, s);
    }

    // depth first search from v
    private void dfs(Graph G, int v) {
    	//TODO: Zeilen hinzufuegen
        marked[v] = true;
        preorder.add(v);
//        if (v == s) {         // this is ok
//            edgeTo[s] = s;    // this is ok
//        }                     // this is ok
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                dfs(G, w);
            }
        }
        postorder.add(v);
    }

    public void nonrecursiveDFS(Graph G) {
    	//TODO: Zeilen hinzufuegen
        marked = new boolean[G.V()];
        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            adj[v] = G.adj(v).iterator();
        }
        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        stack.push(s);
//        edgeTo[s] = s;    // this is ok
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (!preorder.contains(v)) {
                preorder.add(v);
            }
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                if (!marked[w]) {
                    // discovered vertex w for the first time
                    marked[w] = true;
                    stack.push(w);
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                }
            }
            else {
                postorder.add(stack.pop());
            }
        }
    }

    /**
     * Is there a path between the source vertex {@code s} and vertex {@code v}?
     * @param v the vertex
     * @return {@code true} if there is a path, {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    /**
     * Returns a path between the source vertex {@code s} and vertex {@code v}, or
     * {@code null} if no such path.
     * @param  v the vertex
     * @return the sequence of vertices on a path between the source vertex
     *         {@code s} and vertex {@code v}, as an Iterable (beginning and end are included)
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * 
     * This method is different compared to the original one.
     */
    public List<Integer> pathTo(int v) {
		// TODO
        validateVertex(v);
        if (!preorder.contains(v)) {
            return null;
        }
        Stack<Integer> pathTo = new Stack<Integer>();
        pathTo.push(v);
        while (v != s) {
            v = edgeTo[v];
            pathTo.push(v);
        }
        return pathTo;
    }
    
    /**
     * Returns the vertices in postorder. This method differs from the original.
     * @return the vertices in postorder, as a queue of vertices
     */
    public Queue<Integer> post() {
        return postorder;
    }

    /**
     * Returns the vertices in preorder. This method differs from the original.
     * @return the vertices in preorder, as a queue of vertices 
     */
    public Queue<Integer> pre() {
        return preorder;
    }   
    
    /**
     * Returns the class variable edgeTo. This method differs from the original.
     * @return egdeTo
     */
    public int [] edge() {
    	return edgeTo;
    }
    
    /**
     * Returns the class variable distTo. This method differs from the original.
     * @return distTo
     */
    public int [] dist() {
    	return distTo;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * toString for Queue<Integer> like preorder and postorder
     */
    public String toString(Queue<Integer> queue) {
        String text = "[";
        boolean first = true;
        for (Integer element : queue) {
            if (!first) {
                text += ", ";
            }
            text += String.valueOf(element);
            first = false;
        }
        text += "]";
        return text;
    }

     /**
     * toString for int[] like edgeTo and distTo
     */
    public String toString(int[] list) {
        String text = "[";
        boolean first = true;
        for (int element : list) {
            if (!first) {
                text += ", ";
            }
            text += String.valueOf(element);
            first = false;
        }
        text += "]";
        return text;
    }

    public static void main(String[] args) {
        // TEST
        int V = 10;
        int s = 0;
        int v = 9;

        Graph graph = new Graph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(3, 8);
        graph.addEdge(5, 9);
        System.out.println(graph);

        DepthFirstPaths depthFirstPaths = new DepthFirstPaths(graph, s);

        System.out.println("recursive:");
        depthFirstPaths.dfs(graph);

//        System.out.println("nonrecursive:");
//        depthFirstPaths.nonrecursiveDFS(graph);

        System.out.println("preorder:  " + depthFirstPaths.toString(depthFirstPaths.preorder));
        System.out.println("postorder: " + depthFirstPaths.toString(depthFirstPaths.postorder));
        System.out.println("edgeTo:    " + depthFirstPaths.toString(depthFirstPaths.edgeTo));
        System.out.println("distTo:    " + depthFirstPaths.toString(depthFirstPaths.distTo));
        System.out.println("pathTo:    " + depthFirstPaths.pathTo(v));
        System.out.println();
        System.out.println("preorder:  " + depthFirstPaths.toString(depthFirstPaths.preorder));
        System.out.println("postorder: " + depthFirstPaths.toString(depthFirstPaths.postorder));
        System.out.println("edgeTo:    " + depthFirstPaths.toString(depthFirstPaths.edgeTo));
        System.out.println("distTo:    " + depthFirstPaths.toString(depthFirstPaths.distTo));
        System.out.println("pathTo:    " + depthFirstPaths.pathTo(v));
    }
}
