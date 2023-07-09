import java.util.Iterator;
import java.util.Stack;

public class ShortestPathsTopological {
    private int s;
    private int[] parent;
    private double[] dist;

    public ShortestPathsTopological(WeightedDigraph G, int s) {
        // TODO
        this.s = s;
        this.parent = new int[G.V()];
        this.dist = new double[G.V()];
        for (int i = 0; i < this.dist.length; i++) {
            this.dist[i] = Double.MAX_VALUE;
        }
        this.parent[s] = s;
        this.dist[s] = 0.0;

        TopologicalWD topologicalWD = new TopologicalWD(G);
        topologicalWD.dfs(s);
        Stack<Integer> postorder = topologicalWD.order();

        while (!postorder.isEmpty()) {
            Iterator<DirectedEdge> neighbors = G.incident(postorder.pop()).iterator();
            while (neighbors.hasNext()) {
                relax(neighbors.next());
            }
        }
    }

    public void relax(DirectedEdge e) {
        // TODO
        double distNew = this.dist[e.from()] + e.weight();
        if (distNew < this.dist[e.to()]){
            this.parent[e.to()] = e.from();
            this.dist[e.to()] = distNew;
        }
    }

    public boolean hasPathTo(int v) {
        return parent[v] >= 0;
    }

    public Stack<Integer> pathTo(int v) {
        if (!hasPathTo(v)) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        for (int w = v; w != s; w = parent[w]) {
            path.push(w);
        }
        path.push(s);
        return path;
    }

    public String toString(int[] listInt) {
        String toString = "";
        boolean first = true;
        for (int n : listInt) {
            if (!first) {
                toString += ", ";
            }
            toString += n;
            first = false;
        }
        return toString;
    }

    public String toString(double[] listDouble) {
        String toString = "";
        boolean first = true;
        for (double n : listDouble) {
            if (!first) {
                toString += ", ";
            }
            toString += n;
            first = false;
        }
        return toString;
    }

    public static void main(String[] args) {
        int V1 = 6;
        WeightedDigraph weightedDigraph1 = new WeightedDigraph(V1);
        weightedDigraph1.addEdge(0, 1, 3);
        weightedDigraph1.addEdge(0, 2, 6);
        weightedDigraph1.addEdge(0, 3, 4);
        weightedDigraph1.addEdge(1, 2, 2);
        weightedDigraph1.addEdge(1, 4, 4);
        weightedDigraph1.addEdge(2, 4, 1);
        weightedDigraph1.addEdge(2, 5, 3);
        weightedDigraph1.addEdge(3, 2, 4);
        weightedDigraph1.addEdge(3, 5, 5);
        weightedDigraph1.addEdge(4, 5, 1);
        ShortestPathsTopological shortestPathsTopological1 = new ShortestPathsTopological(weightedDigraph1, 0);
        System.out.println("parent: " + shortestPathsTopological1.toString(shortestPathsTopological1.parent));
        System.out.println("dist:   " + shortestPathsTopological1.toString(shortestPathsTopological1.dist));

        int V2 = 8;
        WeightedDigraph weightedDigraph2 = new WeightedDigraph(V2);
        weightedDigraph2.addEdge(0, 1, 1);
        weightedDigraph2.addEdge(0, 2, 3);
        weightedDigraph2.addEdge(1, 3, 1);
        weightedDigraph2.addEdge(1, 4, 6);
        weightedDigraph2.addEdge(2, 4, 1);
        weightedDigraph2.addEdge(2, 6, 4);
        weightedDigraph2.addEdge(3, 5, 2);
        weightedDigraph2.addEdge(4, 6, 2);
        weightedDigraph2.addEdge(5, 7, 8);
        weightedDigraph2.addEdge(6, 7, 2);
        ShortestPathsTopological shortestPathsTopological2 = new ShortestPathsTopological(weightedDigraph2, 0);
        System.out.println("parent: " + shortestPathsTopological2.toString(shortestPathsTopological2.parent));
        System.out.println("dist:   " + shortestPathsTopological2.toString(shortestPathsTopological2.dist));
    }
}
