public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // copy constructor
    public Position(Position that) {
        this(that.x, that.y);
    }

    /**
     * Displace the position by the specified values.
     *
     * @param xd Displacement in x-direction
     * @param yd Displacement in y-direction
     */
    public void displace(int xd, int yd) {
        x += xd;
        y += yd;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object o) {
        /* TODO */
        if (o == this) {
            return true;
        }
        if (!(o instanceof Position)) {
            return false;
        }
        Position position = (Position) o;
        if (position.x == this.x && position.y == this.y) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        /* TODO */
        return this.x + (this.y * 20);
    }
}
