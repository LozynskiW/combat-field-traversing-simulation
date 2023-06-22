import java.util.ArrayList;
import java.util.List;

class Terrain {

    private int height;
    private int width;

    private final int minGridLength;

    public Terrain(int minGridLength) {
        this.minGridLength = minGridLength;
    }

    public int getMinGridLength() {
        return minGridLength;
    }

    public double calculateDistanceBetweenUnits(Unit unitA, Unit unitB) {

        return 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
