import java.util.ArrayList;
import java.util.List;

class Terrain {

    private int height;
    private int width;

    private final int minGridLength;

    public Terrain() {
        this.minGridLength = 100; //[m]
    }

    public int getMinGridLength() {
        return minGridLength;
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
