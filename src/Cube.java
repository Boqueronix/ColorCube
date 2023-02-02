import java.awt.*;
import java.util.ArrayList;

public class Cube {
    public static long[] degreeInput = new long[3]; // 0 = x, 1 = y, 2 = z ranging from -infinity to infinity
    public static int[] degreeVisual = new int[3]; // 0 = x, 1 = y, 2 = z ranging from 0 to 360
    public static final Point blue = new Point(0, 0, 255); // r = 0, g = 0, b = 255
    public static final Point magenta = new Point(255, 0, 255); // r = 255, g = 0, b = 255
    public static final Point white = new Point(255, 255, 255); // r = 255, g = 255, b = 255
    public static final Point cyan = new Point(0, 255, 255); // r = 0, g = 255, b = 255
    public static final Point black = new Point(0, 0, 0); // r = 0, g = 0, b = 0
    public static final Point red = new Point(255, 0, 0); // r = 255, g = 0, b = 0
    public static final Point yellow = new Point(255, 255, 0); // r = 255, g = 255, b = 0
    public static final Point green = new Point(0, 255, 0); // r = 0, g = 255, b = 0
    public static final Point[] vertices = new Point[]{blue, magenta, white, cyan, black, red, yellow, green};

    public static final ArrayList<Point> pointsSpline = new ArrayList<>(); // all points on the spline / gradient

    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-1, 2);
        setDegreeInput(new long[]{179, 179, 179});
        drawCube();
        Plane.create();
    }

    public static void setDegreeInput(long[] dI) {
        if (dI.length != 3)
            throw new IllegalArgumentException("Degree input must be an array of length 3");
        degreeInput = dI;
        for (int i = 0; i < 3; i++) {
            degreeVisual[i] = (int) (dI[i] % 360);
        }
    }

    public static void drawCube(){
        StdDraw.square(-0.5, -0.5, 0.5);
        StdDraw.square(1.5, -0.5, 0.5);
        StdDraw.square(-0.5, 1.5, 0.5);
        StdDraw.square(1.5, 1.5, 0.5);
        StdDraw.setPenRadius(1 / 255.0);
        //Booleans
        boolean rNormal = degreeVisual[1] <= 180 && degreeVisual[2] <= 180 || degreeVisual[1] > 180 && degreeVisual[2] > 180;
        boolean gNormal = degreeVisual[0] <= 180 && degreeVisual[2] <= 180 || degreeVisual[0] > 180 && degreeVisual[2] > 180;
        boolean bNormal = degreeVisual[0] <= 180 && degreeVisual[1] <= 180 || degreeVisual[0] > 180 && degreeVisual[1] > 180;
        //Draw
        for (/* int r = (rNormal) ? 0 : 256; (rNormal) ? r < 256 : r >= 0; r+= (rNormal) ? 1 : -1*/ int r = 0; r < 256; r++) {
            for (/*int g = (gNormal) ? 0 : 256; (gNormal) ? g < 256 : g >= 0; g+= (gNormal) ? 1 : -1*/ int g = 0; g < 256; g++) {
                for (/*int b = (bNormal) ? 0 : 256; (bNormal) ? b < 256 : b >= 0; b+= (bNormal) ? 1 : -1*/ int b = 0; b < 256; b++) {
                    //base
                    double x = r / 255.0;
                    double y = g / 255.0;
                    double z = b / 255.0;
                    //Rx
                    x = x;
                    y = y * Math.cos(Math.toRadians(degreeVisual[0])) - z * Math.sin(Math.toRadians(degreeVisual[0]));
                    z = y * Math.sin(Math.toRadians(degreeVisual[0])) + z * Math.cos(Math.toRadians(degreeVisual[0]));
                    //Ry
                    x = x * Math.cos(Math.toRadians(degreeVisual[1])) + z * Math.sin(Math.toRadians(degreeVisual[1]));
                    y = y;
                    z = -x * Math.sin(Math.toRadians(degreeVisual[1])) + z * Math.cos(Math.toRadians(degreeVisual[1]));
                    //Rz
                    x = x * Math.cos(Math.toRadians(degreeVisual[2])) - y * Math.sin(Math.toRadians(degreeVisual[2]));
                    y = x * Math.sin(Math.toRadians(degreeVisual[2])) + y * Math.cos(Math.toRadians(degreeVisual[2]));
                    z = z;
                    //draw
                    int vertices = -1;
                    for (int coordinate : new int[]{r % 256, g % 256, b % 256}) {
                        if (coordinate == 0 || coordinate == 255)
                            vertices++;
                    }
                    StdDraw.setPenColor((vertices > 0) ? StdDraw.BLACK : new Color(r, g, b));
                    StdDraw.point(x, y);
                }
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
//Not useful
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