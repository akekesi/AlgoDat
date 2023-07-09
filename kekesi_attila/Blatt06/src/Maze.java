import java.util.LinkedList;
import java.util.List;
import java.util.Stack;


/**
 * Class that represents a maze with N*N junctions.
 * 
 * @author Vera Röhr
 */
public class Maze{
    private final int N;
    private Graph M;    //Maze
    public int startnode;
        
	public Maze(int N, int startnode) {
		
        if (N < 0) throw new IllegalArgumentException("Number of vertices in a row must be nonnegative");
        this.N = N;
        this.M= new Graph(N*N);
        this.startnode= startnode;
        buildMaze();
	}
	
    public Maze (In in) {
    	this.M = new Graph(in);
    	this.N= (int) Math.sqrt(M.V());
    	this.startnode=0;
    }

	
    /**
     * Adds the undirected edge v-w to the graph M.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
		// TODO
        M.addEdge(v, w);
    }
    
    /**
     * Returns true if there is an edge between 'v' and 'w'
     * @param v one vertex
     * @param w another vertex
     * @return true or false
     */
    public boolean hasEdge( int v, int w) {
        // TODO
        if (M.adj(v).contains(w) || v == w) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Builds a grid as a graph.
     * @return Graph G -- Basic grid on which the Maze is built
     */
    public Graph mazegrid() {
		// TODO
        int v;
        Graph grid= new Graph(this.N * this.N);
        for (int i = 0; i < this.N; i++) {
            for (int j = 0; j < this.N; j++) {
                v = (i * this.N) + j;
                if (0 < j && !grid.adj(v - 1).contains(v)) {
                    grid.addEdge(v, v - 1);
                }
                if (j < this.N - 1 && !grid.adj(v + 1).contains(v)) {
                    grid.addEdge(v, v + 1);
                }
                if (0 < i && !grid.adj(v - N).contains(v)) {
                    grid.addEdge(v, v - this.N);
                }
                if (i < this.N - 1 && !grid.adj(v + N).contains(v)) {
                    grid.addEdge(v, v + this.N);
                }
            }
        }
        return grid;
    }
    
    /**
     * Builds a random maze as a graph.
     * The maze is build with a randomized DFS as the Graph M.
     */
    private void buildMaze() {
		// TODO
        RandomDepthFirstPaths randomDepthFirstPaths = new RandomDepthFirstPaths(this.mazegrid(), this.startnode);
        randomDepthFirstPaths.randomDFS(this.mazegrid());
        for (int i = 0; i < this.N * this.N; i++) {
            List<Integer> path = randomDepthFirstPaths.pathTo(i);
            for (int j = 0; j < path.size() - 1; j++) {
                if (!hasEdge(path.get(j), path.get(j + 1)) && !hasEdge(path.get(j + 1), path.get(j))) {
                    addEdge(path.get(j), path.get(j + 1));
                }
            }
        }
    }

    /**
     * Find a path from node v to w
     * @param v start node
     * @param w end node
     * @return List<Integer> -- a list of nodes on the path from v to w (both included) in the right order.
     */
    public List<Integer> findWay(int v, int w){
		// TODO
        RandomDepthFirstPaths randomDepthFirstPaths = new RandomDepthFirstPaths(this.M, w);
        randomDepthFirstPaths.randomDFS(this.M);
        return randomDepthFirstPaths.pathTo(v);
    }

    /**
     * @return Graph M
     */
    public Graph M() {
    	return M;
    }

    public static void main(String[] args) {
		// TEST
        int N = 25;
        int s = 0;
        int v = s;
        int w = N - 1;

        Maze maze = new Maze(N, s);
        System.out.println(maze.mazegrid());
        System.out.println(maze.M());
        GridGraph gridGraph = new GridGraph(maze.M());

        List<Integer> path = maze.findWay(v, w);
        System.out.println(path);
        GridGraph gridGraphPath = new GridGraph(maze.M, path);
    }
}
