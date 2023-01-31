public class Point {
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
