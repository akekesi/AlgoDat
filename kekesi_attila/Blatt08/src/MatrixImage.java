import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MatrixImage implements Image {
    public int[][] field;
    
    /**
     * A Constructor for the MatrixImage class
     * @param sx the size on the x-axis
     * @param sy the size on the y-axis
     */
    public MatrixImage(int sx, int sy) {
        field = new int[sx][sy];
    }

    /**
     * A deep dopy constructor for a given MatrixImage that
     * @param that the to be copied MatrixImage
     */
     public MatrixImage(MatrixImage that) {
        this(that.sizeX(), that.sizeY());
        for (int x = 0; x < sizeX(); x++) {
            field[x] = that.field[x].clone();
        }
    }
    
    /**
     * Initializes a MatrixImage from a given file
     * @param filename the file
     * @throws java.io.FileNotFoundException if there is no such file
     */
    public MatrixImage(String filename) throws java.io.FileNotFoundException {
        System.setIn(new FileInputStream(filename));
        Scanner in = new Scanner(System.in);
        int sx = in.nextInt();
        int sy = in.nextInt();
        field = new int[sx][sy];
        for (int y = 0; y < sy; y++) {
            for (int x = 0; x < sx; x++) {
                field[x][y] = in.nextInt();
            }
        }
    }


    /**
     * Check coordinate whether it is outside the image
     * @param p coordinate
     */
    public void checkCoordinate(Coordinate p) throws InputMismatchException {
        if (p.x < 0 || p.y < 0 || p.x > this.sizeX() - 1 || p.y > this.sizeY() - 1) {
            throw new InputMismatchException("Coordinate (" + p.x + ", " + p.y + ") is outside the image");
        }
    }

    /**
    * @return the size on the x-axis
    */
    @Override
    public int sizeX() {
        return field.length;
    }
    
    /**
    * @return the size on the y-axis
    */
    @Override
    public int sizeY() {
        return field[0].length;
    }
    
    /**
     * Calculates the contrast between two coordinates/nodes
     * @param p0 first coordinate
     * @param p1 second coordinate
     * @return the absolute value of the contrast
     * @throws InputMismatchException if the coordinates are not in the image
     */
    @Override
    public double contrast(Coordinate p0, Coordinate p1) throws InputMismatchException {
        // TODO
        checkCoordinate(p0);
        checkCoordinate(p1);
        return Math.abs(this.field[p0.x][p0.y] - this.field[p1.x][p1.y]);
    }
    
    /**
     * Removes the given vertical path from the image.
     * Create a deep copy of the image with the correct new Matrix size.
     * @param path the do be deleted vertical path
     */
    @Override
    public void removeVPath(int[] path) {
        // TODO
        int xRemovedVPath;
        int[][] fieldRemovedVPath = new int[this.sizeX() - 1][this.sizeY()];
        for (int y = 0; y < this.sizeY(); y++) {
            xRemovedVPath = 0;
            for (int x = 0; x < this.sizeX(); x++) {
                if (x != path[y]) {
                    fieldRemovedVPath[xRemovedVPath][y] =this.field[x][y];
                    xRemovedVPath++;
                }
            }
        }
        this.field = fieldRemovedVPath;
    }

    @Override
    public String toString() {
        String str = "";
        for (int y = 0; y < sizeY(); y++) {
            for (int x = 0; x < sizeX(); x++) {
                str += field[x][y] + " ";
            }
            str += "\n";
        }
        return str;
    }

    @Override
    public void render() {
        System.out.println(toString());
    }
}
