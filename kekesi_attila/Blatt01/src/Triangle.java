import java.util.Arrays;


public class Triangle extends ConvexPolygon {
    // constructor
    public Triangle(Vector2D a, Vector2D b, Vector2D c) {
        // TODO
        super(new Vector2D[]{a, b, c});
    }

    public Triangle(Triangle triangle) {
        // TODO
        super(new Vector2D[]{
                triangle.vertices[0],
                triangle.vertices[1],
                triangle.vertices[2]
        });
    }

    // override
    @Override
    public double area() {
        // TODO
        Vector2D a = this.vertices[0];
        Vector2D b = this.vertices[1];
        Vector2D c = this.vertices[2];
        Vector2D side1 = new Vector2D(b.getX() - a.getX(),b.getY() - a.getY());
        Vector2D side2 = new Vector2D(c.getX() - b.getX(),c.getY() - b.getY());
        Vector2D side3 = new Vector2D(a.getX() - c.getX(),a.getY() - c.getY());
        double s1 = side1.length();
        double s2 = side2.length();
        double s3 = side3.length();
        double s = (s1 + s2 + s3)/2;
        return Math.sqrt(s * (s-s1) * (s-s2) * (s-s3));
    }

    @Override
    public String toString() {
        // TODO
        return "Triangle{" + Arrays.toString(this.vertices) + "}";
    }
}
