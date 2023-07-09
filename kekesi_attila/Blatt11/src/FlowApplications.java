import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;


public class FlowApplications {

    /**
     * cloneFlowNetwork() makes a deep copy of a FlowNetwork
     * (FlowNetwork has unfortunately no copy constructor)
     *
     * @param flowNetwork the flow network that should be cloned
     * @return cloned flow network (deep copy) with same order of edges
     */
    private static FlowNetwork cloneFlowNetwork(FlowNetwork flowNetwork) {
        int V = flowNetwork.V();
        FlowNetwork clone = new FlowNetwork(V);

//        Simple version (but reverses order of edges)
//        for (FlowEdge e : flowNetwork.edges()) {
//            FlowEdge eclone = new FlowEdge(e.from(), e.to(), e.capacity());
//            clone.addEdge(eclone);
//        }

        for (int v = 0; v < flowNetwork.V(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<FlowEdge> reverse = new Stack<>();
            for (FlowEdge e : flowNetwork.adj(v)) {
                if (e.to() != v) {
                    FlowEdge eclone = new FlowEdge(e.from(), e.to(), e.capacity());
                    reverse.push(eclone);
                }
            }
            while (!reverse.isEmpty()) {
                clone.addEdge(reverse.pop());
            }
        }
        return clone;
    }

    /**
     * Convert Graph to FlowNetwork with Edges with capacities = 1.
     * @param graph the graph to convert
     * @return flowNetwork the converted graph as FlowNetwork
     */
    public static FlowNetwork graphToFlowNetwork(Graph graph) {
        // TODO
        int capacity = 1;
        int V = graph.V();
        FlowNetwork flowNetwork = new FlowNetwork(V);
        for (int i = 0; i < V; i++) {
            for (int j : graph.adj(i)) {
                FlowEdge flowEdge = new FlowEdge(i, j, capacity);
                flowNetwork.addEdge(flowEdge);
            }
        }
        return flowNetwork;
    }

    /**
     * Get a path of flowNetwork from s to t.
     * @param flowNetwork   FlowNetwork
     * @param s             node on one end of the path
     * @param t             node on the other end of the path
     * @param usedEdges     already used edges
     * @return a path of flowNetwork from s to t
     */
    public static LinkedList<FlowEdge> getPath(FlowNetwork flowNetwork, int s, int t, LinkedList usedEdges) {
        // TODO
        boolean foundEdge;
        LinkedList<FlowEdge> path = new LinkedList<>();
        while (s != t) {
            foundEdge = false;
            for (FlowEdge flowEdge : flowNetwork.adj(s)) {
                if (flowEdge.flow() > 0 && flowEdge.from() == s && !usedEdges.contains(flowEdge)) {
                    path.add(flowEdge);
                    usedEdges.add(flowEdge);
                    s = flowEdge.to();
                    foundEdge = true;
                    break;
                }
            }
            if (!foundEdge) {
                return null;
            }
        }
        return path;
    }

    /**
     * Convert path as LinkedList<FlowEdge> to path as LinkedList<Integer>.
     * @param pathFlowEdge a path from s to t
     * @return path the converted path
     */
    public static LinkedList<Integer> pathFlowEdgeToInteger(LinkedList<FlowEdge> pathFlowEdge) {
        // TODO
        boolean first = true;
        LinkedList<Integer> path = new LinkedList<>();
        for (FlowEdge flowEdge : pathFlowEdge) {
            if (first) {
                path.add(flowEdge.from());
                first = false;
            }
            path.add(flowEdge.to());
        }
        return path;
    }

    /**
     * Reverse flowEdge.
     *
     * @param flowEdge the FlowEdge to reverse
     * @return flowEdgeReverse the reversed FlowEdge
     */
    public static FlowEdge flowEdgeReverse(FlowEdge flowEdge) {
        // TODO
        return new FlowEdge(flowEdge.to(), flowEdge.from(), flowEdge.capacity(), flowEdge.flow());
    }

    /**
     * Reverse flowNetwork.
     *
     * @param flowNetwork the FlowNetwork to reverse
     * @return flowNetworkReverse the reversed FlowNetwork
     */
    public static FlowNetwork flowNetworkReverse(FlowNetwork flowNetwork) {
        // TODO
        FlowNetwork flowNetworkReverse = new FlowNetwork(flowNetwork.V());
        for (FlowEdge flowEdge : flowNetwork.edges()) {
            FlowEdge flowEdgeReverse = flowEdgeReverse(flowEdge);
            flowNetworkReverse.addEdge(flowEdgeReverse);
        }
        return flowNetworkReverse;
    }

    /**
     * Copy flowNetwork.
     *
     * @param flowNetwork the FlowNetwork to copy
     * @return flowNetworkCopy the copied FlowNetwork
     */
    public static FlowNetwork flowNetworkCopy(FlowNetwork flowNetwork) {
        // TODO
        FlowNetwork flowNetworkCopy = new FlowNetwork(flowNetwork.V());
        for (FlowEdge flowEdge : flowNetwork.edges()) {
            FlowEdge flowEdgeCopy = new FlowEdge(flowEdge.from(), flowEdge.to(), flowEdge.capacity(), flowEdge.flow());
            flowNetworkCopy.addEdge(flowEdgeCopy);
        }
        return flowNetworkCopy;
    }

    /**
     * Get minCut (list of edges of minCut)
     *
     * @param flowNetwork   the FlowNetwork which to get minCutEdges from
     * @param s             node on one end of the path
     * @param t             node on the other end of the path
     * @return minCutEdges list of FlowEdges of minCut
     */
    public static LinkedList<FlowEdge> getMinCut(FlowNetwork flowNetwork, int s, int t) {
        // TODO
        LinkedList<FlowEdge> minCutEdges = new LinkedList<>();
        FlowNetwork flowNetworkCopy = flowNetworkCopy(flowNetwork);
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetworkCopy, s, t);
        for (FlowEdge flowEdge : flowNetworkCopy.edges()) {
            if (fordFulkerson.inCut(flowEdge.from()) && !fordFulkerson.inCut(flowEdge.to())) {
                        minCutEdges.add(flowEdge);
            }
            if (!fordFulkerson.inCut(flowEdge.from()) && fordFulkerson.inCut(flowEdge.to())) {
                        minCutEdges.add(flowEdge);
            }
        }
        return minCutEdges;
    }

    /**
     * Check whether to list of FlowEdges are the same
     *
     * @param flowEdges1 list of edges 1
     * @param flowEdges2 list of edges 2
     * @return true/false they are some/not some
     */
    public static boolean compareFlowEdges(LinkedList<FlowEdge> flowEdges1, LinkedList<FlowEdge> flowEdges2) {
        // TODO
        if (flowEdges1.size() != flowEdges2.size()) {
            return false;
        }
        boolean same;
        for (FlowEdge flowEdge1 : flowEdges1) {
            same = false;
            for (FlowEdge flowEdge2 : flowEdges2) {
                if (flowEdge1.from() == flowEdge2.to() && flowEdge1.to() == flowEdge2.from()) {
                    same = true;
                }
            }
            if (!same) {
                return false;
            }
        }
        return true;
    }

    /**
     * numberOfEdgeDisjointPaths() returns the (maximum) number of edge-disjoint paths that exist in
     * an undirected graph between two nodes s and t using Edmonds-Karp.
     *
     * @param graph the graph that is to be investigated
     * @param s     node on one end of the path
     * @param t     node on the other end of the path
     * @return number of edge-disjoint paths in graph between s and t
     */
    public static int numberOfEdgeDisjointPaths(Graph graph, int s, int t) {
        // TODO
        FlowNetwork flowNetwork = graphToFlowNetwork(graph);
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, s, t);
        return (int) fordFulkerson.value();
    }

    /**
     * edgeDisjointPaths() returns a maximal set of edge-disjoint paths that exist in
     * an undirected graph between two nodes s and t using Edmonds-Karp.
     *
     * @param graph the graph that is to be investigated
     * @param s     node on one end of the path
     * @param t     node on the other end of the path
     * @return a {@code Bag} of edge-disjoint paths in graph between s and t
     * Each path is stored in a {@code LinkedList<Integer>}.
     */
    public static Bag<LinkedList<Integer>> edgeDisjointPaths(Graph graph, int s, int t) {
        // TODO
        Bag<LinkedList<Integer>> paths = new Bag<>();
        LinkedList<FlowEdge> usedEdges = new LinkedList<>();
        FlowNetwork flowNetwork = graphToFlowNetwork(graph);
        new FordFulkerson(flowNetwork, s, t);
        while (true) {
            LinkedList<FlowEdge> pathFlowEdge = getPath(flowNetwork, s, t, usedEdges);
            if (pathFlowEdge == null) {
                return paths;
            }
            paths.add(pathFlowEdgeToInteger(pathFlowEdge));
        }
    }

    /**
     * isUnique determines for a given Flow Network that has a guaranteed minCut,
     * if that one is unique, meaning it's the only minCut in that network
     *
     * @param flowNetworkIn the graph that is to be investigated
     * @param s             source node s
     * @param t             sink node t
     * @return true if the minCut is unique, otherwise false
     */
    public static boolean isUnique(FlowNetwork flowNetworkIn, int s, int t) {
        // TODO
        FlowNetwork flowNetworkInReverse = flowNetworkReverse(flowNetworkIn);
        LinkedList<FlowEdge> flowEdges = getMinCut(flowNetworkIn, s, t);
        LinkedList<FlowEdge> flowEdgesReverse = getMinCut(flowNetworkInReverse, t, s);
        return compareFlowEdges(flowEdges, flowEdgesReverse);
    }

    /**
     * findBottlenecks finds all bottleneck nodes in the given flow network
     * and returns the indices in a Linked List
     *
     * @param flowNetwork the graph that is to be investigated
     * @param s           index of the source node of the flow
     * @param t           index of the target node of the flow
     * @return {@code LinkedList<Integer>} containing all bottleneck vertices
     * @throws IllegalArgumentException is flowNetwork does not have a unique cut
     */
    public static LinkedList<Integer> findBottlenecks(FlowNetwork flowNetwork, int s, int t) {
        // TODO
        if (!isUnique(flowNetwork, s, t)) {
            throw new IllegalArgumentException("minCut of flowNetwork is not unique");
        }
        LinkedList<Integer> bottlenecks = new LinkedList<>();
        LinkedList<FlowEdge> flowEdges = getMinCut(flowNetwork, s, t);
        FlowNetwork flowNetworkCopy = flowNetworkCopy(flowNetwork);
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetworkCopy, s, t);
        int nodeSSide;
        for (FlowEdge flowEdge : flowEdges) {
            if (flowEdge.flow() > 0) {
                nodeSSide = flowEdge.from();
                if (!fordFulkerson.inCut(nodeSSide)) {
                    nodeSSide = flowEdge.to();
                }
                if (!bottlenecks.contains(nodeSSide)) {
                    bottlenecks.add(nodeSSide);
                }
            }
        }
        return bottlenecks;
    }

    public static void main(String[] args) {
        // Test for Task 3.1 and 3.2
        Graph graph = new Graph(new In("samples/Graph1.txt"));
//        Graph graph = new Graph(new In("samples/Graph4pathFinding-S-0.txt"));
//        Graph graph = new Graph(new In("samples/Graph4pathFinding-S-1.txt"));
//        Graph graph = new Graph(new In("samples/Graph4pathFinding-S-2.txt"));
//        Graph graph = new Graph(new In("samples/Graph4pathFinding-M-0.txt"));
//        Graph graph = new Graph(new In("samples/Graph4pathFinding-M-1.txt"));
//        Graph graph = new Graph(new In("samples/Graph4pathFinding-M-2.txt"));
        System.out.println("graph:");
        System.out.println(graph);

        FlowNetwork flowNetwork = graphToFlowNetwork(graph);
        System.out.println("flowNetwork before FordFulkerson:");
        System.out.println(flowNetwork);

        int s = 0;
        int t = graph.V() - 1;
        new FordFulkerson(flowNetwork, s, t);
        System.out.println("flowNetwork after FordFulkerson:");
        System.out.println(flowNetwork);

        System.out.println("edgeDisjointPaths:" );
        Bag<LinkedList<Integer>> paths = edgeDisjointPaths(graph, s, t);
        for (LinkedList<Integer> path : paths) {
            System.out.println(path);
        }

        int n = numberOfEdgeDisjointPaths(graph, s, t);
        System.out.println();
        System.out.println("numberOfEdgeDisjointPaths: " + n);

        // Example for Task 4.1 and 4.2
//        flowNetwork = new FlowNetwork(new In("samples/Flussgraph1.txt")); // false
//        flowNetwork = new FlowNetwork(new In("samples/Flussgraph2.txt")); // true
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-M-0.txt")); // s=47, t=42 --> true
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-M-1.txt"));
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-M-2.txt"));
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-M-3.txt"));
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-M-4.txt"));
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-S-0.txt")); // s=3, t=1 --> true
        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-S-1.txt")); // s=6, t=2 --> false
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-S-2.txt"));
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-S-3.txt"));
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4cuts-S-4.txt")); // s=5, t=0 --> true
//        s = 0;
//        t = flowNetwork.V() - 1;
        s = 6;
        t = 2;
        boolean unique = isUnique(flowNetwork, s, t);
        System.out.println();
        System.out.println("Is minCut unique? " + unique);

/*
        // Blatt11 Abbildung 4:
        // to test this without exception: comment out/delete exception in findBottlenecks
        flowNetwork = new FlowNetwork(new In("samples/Flussgraph1.txt"));
        LinkedList<Integer> bottlenecksB = findBottlenecks(flowNetwork, s, t);
        System.out.println();
        System.out.println("Bottlenecks (b): " + bottlenecksB); // to test this, comment out/delete exception in findBottlenecks

        flowNetwork = new FlowNetwork(new In("samples/Flussgraph1.txt"));
        flowNetwork = flowNetworkReverse(flowNetwork);
        LinkedList<Integer> bottlenecksC = findBottlenecks(flowNetwork, t, s);
        System.out.println("Bottlenecks (c): " + bottlenecksC); // to test this, comment out/delete exception in findBottlenecks
*/
        flowNetwork = new FlowNetwork(new In("samples/Flussgraph2.txt")); // [1, 2]
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-M-0.txt")); // not unique
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-M-1.txt")); // not unique
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-M-2.txt")); // [1, 7, 9, 10, 11, 15, 28, 30, 35, 37]
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-M-3.txt")); // not unique
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-M-4.txt")); // not unique
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-S-0.txt")); // not unique
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-S-1.txt")); // []
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-S-2.txt")); // []
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-S-3.txt")); // [0, 4]
//        flowNetwork = new FlowNetwork(new In("samples/FlowNetwork4bottlenecks-S-4.txt")); // not unique
        s = 0;
        t = flowNetwork.V() - 1;
        LinkedList<Integer> bottlenecks = findBottlenecks(flowNetwork, s, t);
        System.out.println();
        System.out.println("Bottlenecks: " + bottlenecks);
    }
}
