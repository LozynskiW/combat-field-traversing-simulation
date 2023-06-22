import java.util.Arrays;

class Position {

    private int[] coordinates;

    public Position() {
    }

    public Position(int y, int x) {
        this.coordinates = new int[]{y,x};
    }

    public Position(int[] coordinates) {
        this.coordinates = coordinates;
    }


    public void changeCoordinatesToNextMove(Compass compass) {

        this.coordinates[0] += compass.getMovement()[0];
        this.coordinates[1] += compass.getMovement()[1];
    }

    public int[] getCoordinates() {

        int[] coordinates = new int[2];
        coordinates[0] = this.coordinates[0];
        coordinates[1] = this.coordinates[1];

        return coordinates;
    }

    public void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "Position{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Arrays.equals(coordinates, position.coordinates);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(coordinates);
    }
}
