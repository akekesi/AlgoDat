// Diese Klasse implementiert nur *zentrierte* reguläre Polygone, also mit midpoint = (0, 0).

public class RegularPolygon extends ConvexPolygon {
    // TODO
    // constructor
    public RegularPolygon(int N, double radius) {
        super(new Vector2D[N]);
        for (int i = 0; i < N; i++) {
            this.vertices[i] = new Vector2D(
                    radius * Math.cos(i * 2 * Math.PI / N),
                    radius * Math.sin(i * 2 * Math.PI / N)
            );
        }
    }

    public RegularPolygon(RegularPolygon polygon) {
        // TODO
        super(new Vector2D[polygon.vertices.length]);
        for (int i = 0; i < polygon.vertices.length; i++) {
            this.vertices[i] = new Vector2D(
                    polygon.vertices[i].getX(),
                    polygon.vertices[i].getY()
            );
        }
    }

    // method
    public void resize(double newradius) {
        // TODO
        double factor = newradius / this.vertices[0].getX();
        for (Vector2D vertex : this.vertices) {
            vertex.setX(vertex.getX() * factor);
            vertex.setY(vertex.getY() * factor);
        }
    }

    // override
    @Override
    public String toString() {
        // TODO
        return "RegularPolygon{N=" + this.vertices.length + ", radius=" + this.vertices[0].getX() + "}";
    }
}
