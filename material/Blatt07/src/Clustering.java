import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.awt.Color;

/**
 * This class solves a clustering problem with the Prim algorithm.
 */
public class Clustering {
	EdgeWeightedGraph G;
	List <List<Integer>>clusters; 
	List <List<Integer>>labeled; 
	
	/**
	 * Constructor for the Clustering class, for a given EdgeWeightedGraph and no labels.
	 * @param G a given graph representing a clustering problem
	 */
	public Clustering(EdgeWeightedGraph G) {
            this.G=G;
	    clusters= new LinkedList <List<Integer>>();
	}
	
    /**
	 * Constructor for the Clustering class, for a given data set with labels
	 * @param in input file for a clustering data set with labels
	 */
	public Clustering(In in) {
            int V = in.readInt();
            int dim= in.readInt();
            G= new EdgeWeightedGraph(V);
            labeled=new LinkedList <List<Integer>>();
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
                    	List <Integer> l= new LinkedList <Integer>();
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
	    clusters= new LinkedList <List<Integer>>();
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
	}
	
	/**
	 * Evaluates the clustering based on a fixed number of clusters.
	 * @return array of the number of the correctly classified data points per cluster
	 */
	public int[] validation() {
		// TODO
	}
	
	/**
	 * Calculates the coefficient of variation.
	 * For the formula see exercise sheet.
	 * @param part list of edges
	 * @return coefficient of variation
	 */
	public double coefficientOfVariation(List <Edge> part) {
		// TODO
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
		for(List <Integer> cluster: clusters) {
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
		// FOR TESTING
    }
}

