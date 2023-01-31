import java.util.ArrayList;

public class Cube {
    public static long[] degreeInput = new long[3]; // 0 = x, 1 = y, 2 = z ranging from -infinity to infinity
    public static int[] degreeVisual = new int[3]; // 0 = x, 1 = y, 2 = z ranging from 0 to 360
    public static final Point black = new Point(0, 0, 0); // r = 0, g = 0, b = 0
    public static final Point white = new Point(255, 255, 255); // r = 255, g = 255, b = 255
    public static final ArrayList<Point> points = new ArrayList<Point>();

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1, 2);
        drawCube();
        Plane.create();
    }

    public static void setDegreeInput(long[] degreeInput) {
        if (degreeInput.length != 3)
            throw new IllegalArgumentException("Degree input must be an array of length 3");
        degreeInput = degreeInput;
        for (int i = 0; i < 3; i++) {
            degreeVisual[i] = (int) (degreeInput[i] % 360);
        }
    }

    public static void drawCube(){
        StdDraw.setPenRadius(1 / 255.0);
        for (int r = 0; r < 256; r++) {
            for (int g = 0; g < 256; g++) {
                StdDraw.setPenColor(r, g, 255);
                StdDraw.point(r / 255.0, g / 255.0);
            }
            StdDraw.show();
        }
    }
}

class Point {
    public double[] coordinates = new double[3];
    public int[] color = new int[3];
    public Point (int r, int g, int b) {
        color[0] = r;
        color[1] = g;
        color[2] = b;
        coordinates[0] = r / 255.0;
        coordinates[1] = g / 255.0;
        coordinates[2] = b / 255.0;
    }
}

class Plane {
    public static final Plane[] planes = new Plane[6];
    public Point[] points = new Point[2];
    public Plane(Point p1, Point p2) {
        points[0] = p1;
        points[1] = p2;
    }
    public static void create() {
        planes[0] = new Plane(new Point( 0, 0, 0), new Point(0, 255, 255));
        planes[1] = new Plane(new Point( 0, 0, 0), new Point(255, 0, 255));
        planes[2] = new Plane(new Point( 0, 0, 0), new Point(255, 255, 0));
        planes[3] = new Plane(new Point( 255, 255, 255), new Point(255, 0, 0));
        planes[4] = new Plane(new Point( 255, 255, 255), new Point(0, 255, 0));
        planes[5] = new Plane(new Point( 255, 255, 255), new Point(0, 0, 255));
    }
}