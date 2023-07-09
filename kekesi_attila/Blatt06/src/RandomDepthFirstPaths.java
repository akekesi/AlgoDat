import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.LinkedList;


public class RandomDepthFirstPaths {
  	private boolean[] marked;           // marked[v] = is there an s-v path?
    private int[] edgeTo;               // edgeTo[v] = last edge on s-v path
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
    public RandomDepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        postorder = new LinkedList<Integer>();
        preorder  = new LinkedList<Integer>();

        validateVertex(s);
    }

	public void randomDFS(Graph G) {
		randomDFS(G,s);
	}
	    
    // depth first search from v
    private void randomDFS(Graph G, int v) {
		// TODO
        marked[v] = true;
        preorder.add(v);
        if (v == s) {
            edgeTo[s] = s;
        }
        Collections.shuffle(G.adj(v));
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                distTo[w] = distTo[v] + 1;
                randomDFS(G, w);
            }
        }
        postorder.add(v);
    }
    
    public void randomNonrecursiveDFS(Graph G) {
		// TODO
        marked = new boolean[G.V()];
        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++) {
            Collections.shuffle(G.adj(v));
            adj[v] = G.adj(v).iterator();
        }
        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<Integer>();
        marked[s] = true;
        stack.push(s);
        edgeTo[s] = s;
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
     *         {@code s} and vertex {@code v}, as an Iterable
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

        RandomDepthFirstPaths randomDepthFirstPaths = new RandomDepthFirstPaths(graph, s);

        System.out.println("recursive:");
        randomDepthFirstPaths.randomDFS(graph);

//        System.out.println("nonrecursive:");
//        randomDepthFirstPaths.randomNonrecursiveDFS(graph);

        System.out.println("preorder:  " + randomDepthFirstPaths.toString(randomDepthFirstPaths.preorder));
        System.out.println("postorder: " + randomDepthFirstPaths.toString(randomDepthFirstPaths.postorder));
        System.out.println("edgeTo:    " + randomDepthFirstPaths.toString(randomDepthFirstPaths.edgeTo));
        System.out.println("distTo:    " + randomDepthFirstPaths.toString(randomDepthFirstPaths.distTo));
        System.out.println("pathTo:    " + randomDepthFirstPaths.pathTo(v));
        System.out.println();
        System.out.println("preorder:  " + randomDepthFirstPaths.toString(randomDepthFirstPaths.preorder));
        System.out.println("postorder: " + randomDepthFirstPaths.toString(randomDepthFirstPaths.postorder));
        System.out.println("edgeTo:    " + randomDepthFirstPaths.toString(randomDepthFirstPaths.edgeTo));
        System.out.println("distTo:    " + randomDepthFirstPaths.toString(randomDepthFirstPaths.distTo));
        System.out.println("pathTo:    " + randomDepthFirstPaths.pathTo(v));
    }
}
