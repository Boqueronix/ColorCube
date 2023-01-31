import java.util.ArrayList;

public class Cube {
    public long[] degreeInput = new long[3]; // 0 = x, 1 = y, 2 = z ranging from -infinity to infinity
    public int[] degreeVisual = new int[3]; // 0 = x, 1 = y, 2 = z ranging from 0 to 360
    public final Point black = new Point(0, 0, 0);
    public final Point white = new Point(255, 255, 255);
    public final ArrayList<Point> points = new ArrayList<Point>();

    public static void main(String[] args) {

    }

    public void setDegreeInput(long[] degreeInput) {
        if (degreeInput.length != 3)
            throw new IllegalArgumentException("Degree input must be an array of length 3");
        this.degreeInput = degreeInput;
        for (int i = 0; i < 3; i++) {
            degreeVisual[i] = (int) (degreeInput[i] % 360);
        }
    }
}