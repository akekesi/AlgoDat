import java.util.Arrays;


public class ConvexPolygon extends Polygon {
    // TODO
    // constructor
    public ConvexPolygon(Vector2D[] vertices) {
        this.vertices = vertices;
    }

    // interface
    public double perimeter() {
        int x;
        Vector2D side;
        double perimeter = 0;
        int n = this.vertices.length;
        for (int i = 0; i < n; i++) {
            x = i;
            if (i == 0) {
                x = n;
            }
            side = new Vector2D(this.vertices[x-1].getX() - this.vertices[i].getX(), this.vertices[x-1].getY() - this.vertices[i].getY());
            perimeter += side.length();
        }
        return perimeter;
    }

    public double area() {
        return totalArea(new Polygon[]{this});
    }

    // override
    @Override
    public String toString() {
        return "ConvexPolygon(" + Arrays.toString(this.vertices) + ")";
    }

    // method
    public static Polygon[] somePolygons() {
        int n;
        double radius = 1;

        // vertices: 3
        double[][] points_3 = {
                {0, 0},
                {10, 0},
                {5, 5}
        };
        n = points_3.length;
        Vector2D[] vertices_3 = new Vector2D[n];
        for (int i = 0; i < n; i++) {
            vertices_3[i] = new Vector2D(points_3[i][0], points_3[i][1]);
        }
        Polygon polygon_3 = new ConvexPolygon(vertices_3);

        // vertices: 4
        double[][] points_4 = {
                {0, 0},
                {10, -5},
                {12, 2},
                {3, 17}
        };
        n = points_4.length;
        Vector2D[] vertices_4 = new Vector2D[n];
        for (int i = 0; i < n; i++) {
            vertices_4[i] = new Vector2D(points_4[i][0], points_4[i][1]);
        }
        Polygon polygon_4 = new ConvexPolygon(vertices_4);

        // vertices: 5
        n = 5;
        Vector2D[] vertices_5 = new Vector2D[n];
        for (int i = 0; i < n; i++) {
            vertices_5[i] = new Vector2D(radius * Math.cos(i * 2 * Math.PI / n), radius * Math.sin(i * 2 * Math.PI / n));
        }
        Polygon polygon_5 = new ConvexPolygon(vertices_5);

        // vertices: 6
        n = 6;
        Vector2D[] vertices_6 = new Vector2D[n];
        for (int i = 0; i < n; i++) {
            vertices_6[i] = new Vector2D(radius * Math.cos(i * 2 * Math.PI / n), radius * Math.sin(i * 2 * Math.PI / n));
        }
        Polygon polygon_6 = new ConvexPolygon(vertices_6);

        return new Polygon[]{polygon_3, polygon_4, polygon_5, polygon_6};
    }

    public static double totalArea(Polygon[] polygons) {
        double area = 0;
        Triangle triangle;
        for (Polygon polygon : polygons) {
            for (int i = 0; i < polygon.vertices.length - 2; i++) {
                triangle = new Triangle(polygon.vertices[0], polygon.vertices[i+1], polygon.vertices[i+2]);
                area += triangle.area();
            }
        }
        return area;
    }
}
