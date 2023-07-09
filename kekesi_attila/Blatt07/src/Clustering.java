import java.util.*;
import java.awt.Color;

/**
 * This class solves a clustering problem with the Prim algorithm.
 */
public class Clustering {
	EdgeWeightedGraph G;
	List<List<Integer>>clusters;
	List<List<Integer>>labeled;

	/**
	 * Constructor for the Clustering class, for a given EdgeWeightedGraph and no labels.
	 * @param G a given graph representing a clustering problem
	 */
	public Clustering(EdgeWeightedGraph G) {
		this.G=G;
	    clusters= new LinkedList<List<Integer>>();
	}

    /**
	 * Constructor for the Clustering class, for a given data set with labels
	 * @param in input file for a clustering data set with labels
	 */
	public Clustering(In in) {
            int V = in.readInt();
            int dim= in.readInt();
            G= new EdgeWeightedGraph(V);
            labeled=new LinkedList<List<Integer>>();
            LinkedList labels= new LinkedList();
            double[][] coord = new double [V][dim];
            for (int v = 0;v<V; v++ ) {
                for(int j=0; j<dim; j++) {
                	coord[v][j]=in.readDouble();
                }
                String label= in.readString();
                    if(labels.contains(label)) {
                    	labeled.get(labels.indexOf(label)).add(v);
                    }
                    else {
                    	labels.add(label);
                    	List<Integer> l= new LinkedList<Integer>();
                    	labeled.add(l);
                    	labeled.get(labels.indexOf(label)).add(v);
                    	System.out.println(label);
                    }                
            }
             
            G.setCoordinates(coord);
            for (int w = 0; w < V; w++) {
                for (int v = 0;v<V; v++ ) {
                	if(v!=w) {
                	double weight=0;
                    for(int j=0; j<dim; j++) {
                    	weight= weight+Math.pow(G.getCoordinates()[v][j]-G.getCoordinates()[w][j],2);
                    }
                    weight=Math.sqrt(weight);
                    Edge e = new Edge(v, w, weight);
                    G.addEdge(e);
                	}
                }
            }
	    clusters= new LinkedList<List<Integer>>();
	}

	public void connectedComponents(Queue<Edge> edges) {
		// TODO
		UF uf = new UF(this.G.V());
		for (Edge edge : edges) {
			uf.union(edge.either(), edge.other(edge.either()));
		}
		int ufFind;
		Queue<Integer> parents = new LinkedList<Integer>();
		for (int i = 0; i < this.G.V(); i++) {
			ufFind = uf.find(i);
			if (!parents.contains((ufFind))) {
				parents.add(ufFind);
			}
		}
		for (Integer parent : parents) {
			List<Integer> cluster = new LinkedList<Integer>();
			for (int i = 0; i < this.G.V(); i++) {
				if (parent == uf.find(i)) {
					cluster.add(i);
				}
			}
			this.clusters.add(cluster);
		}
	}

    /**
	 * This method finds a specified number of clusters based on a MST.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * @param numberOfClusters number of expected clusters
	 */
	public void findClusters(int numberOfClusters){
		// TODO
		Edge edgeMax;
		PrimMST primMST = new PrimMST(this.G);
		Queue<Edge> edgesPrimMST = (Queue<Edge>) primMST.edges();

		if (numberOfClusters < 2) {
			numberOfClusters = 0;
		} else {
			numberOfClusters--;
		}
		for (int i = 0; i < numberOfClusters; i++) {
			edgeMax = new Edge(0, 0, Double.NEGATIVE_INFINITY);
			for (Edge edge : edgesPrimMST) {
				if (edgeMax.weight() < edge.weight()) {
					edgeMax = edge;
				}
			}
			edgesPrimMST.remove(edgeMax);
		}
		connectedComponents(edgesPrimMST);
	}
	
	/**
	 * This method finds clusters based on a MST and a threshold for the coefficient of variation.
	 *
	 * It is based in the idea that removing edges from a MST will create a
	 * partition into several connected components, which are the clusters.
	 * The edges are removed based on the threshold given. For further explanation see the exercise sheet.
	 *
	 * @param threshold for the coefficient of variation
	 */
	public void findClusters(double threshold){
		// TODO
		Edge edgeMin;
		boolean end = false;
		PrimMST primMST = new PrimMST(this.G);
		Queue<Edge> edgesPrimMST = (Queue<Edge>) primMST.edges();
		Queue<Edge> edgesNew = new LinkedList<>();
		while (!end) {
			edgeMin = new Edge(0, 0, Double.POSITIVE_INFINITY);
			for (Edge edge : edgesPrimMST) {
				if (edge.weight() < edgeMin.weight()) {
					edgeMin = edge;
				}
			}
			edgesNew.add(edgeMin);
			edgesPrimMST.remove(edgeMin);
			if (coefficientOfVariation((List<Edge>) edgesNew) > threshold) {
				edgesNew.remove(edgeMin);
				end = true;
			}
			if (edgesPrimMST.isEmpty()) {
				end = true;
			}
		}
		connectedComponents(edgesNew);
	}
	
	/**
	 * Evaluates the clustering based on a fixed number of clusters.
	 * @return array of the number of the correctly classified data points per cluster
	 */
	public int[] validation() {
		// TODO
		int nLabeled = this.labeled.size();
		int nClusters = this.clusters.size();
		int[] validation = new int[nLabeled];
		int n = Math.min(nLabeled, nClusters); // avoid error if clusters.size() < labeled.size()
		for (int i = 0; i < n; i++) {
			for (Integer c : this.clusters.get(i)) {
				if (this.labeled.get(i).contains(c)) {
					validation[i]++;
				}
			}
		}
		return validation;
	}

	/**
	 * Calculates the coefficient of variation.
	 * For the formula see exercise sheet.
	 * @param part list of edges
	 * @return coefficient of variation
	 */
	public double coefficientOfVariation(List<Edge> part) {
		// TODO
		double a;
		double b;
		double c;
		double sumX = 0;
		double sumXX = 0;
		double n = part.size();
		for (Edge edge : part) {
			sumX += edge.weight();
			sumXX += Math.pow(edge.weight(), 2);
		}
		a = 1 / n * sumX;
		b = 1 / n * sumXX;
		c = Math.pow(a, 2);
		return Math.sqrt(b - c) / a;
	}

	/**
	 * Plots clusters in a two-dimensional space.
	 */
	public void plotClusters() {
		int canvas=800;
	    StdDraw.setCanvasSize(canvas, canvas);
	    StdDraw.setXscale(0, 15);
	    StdDraw.setYscale(0, 15);
	    StdDraw.clear(new Color(0,0,0));
		Color[] colors= {new Color(255, 255, 255), new Color(128, 0, 0), new Color(128, 128, 128),
				new Color(0, 108, 173), new Color(45, 139, 48), new Color(226, 126, 38), new Color(132, 67, 172)};
	    int color=0;
		for(List<Integer> cluster: clusters) {
			if(color>colors.length-1) color=0;
		    StdDraw.setPenColor(colors[color]);
		    StdDraw.setPenRadius(0.02);
		    for(int i: cluster) {
		    	StdDraw.point(G.getCoordinates()[i][0], G.getCoordinates()[i][1]);
		    }
		    color++;
	    }
	    StdDraw.show();
	}

    public static void main(String[] args) {
		// TEST
//		In in = new In("./iris.txt");
		In in = new In("./iris_small.txt");
		Clustering clustering = new Clustering(in);

//		type: number of clasters
		int numberOfClasters = 2;
		clustering.findClusters(numberOfClasters);

//		type: threshold
//		double threshold = 0.41; // iris_small.txt: 0.41 --> 3 clusters
//		double threshold = 0.42; // iris_small.txt: 0.42 --> 2 clusters
//		clustering.findClusters(threshold);

//		clusters:
		System.out.println();
		System.out.println("clusters:");
		for (int i = 0; i < clustering.clusters.size(); i++) {
			System.out.println("cluster-" + i + ": " + clustering.clusters.get(i));
		}
//		validation:
		System.out.println();
		System.out.println("validation:");
		int[] validation = clustering.validation(); // ???
		for (int i = 0; i < validation.length; i++) {
			System.out.println("label-" + i + ": " + validation[i] + "/" + clustering.labeled.get(i).size());
		}
		clustering.plotClusters();
    }
}
