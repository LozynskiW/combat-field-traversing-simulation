import java.util.*;
import java.sql.Time;

class Unit {

    private String name;

    private int[] currentPosition;

    private final LinkedList<int[]> pastPositions;
    private Deque<Compass> movementPath; //FIFO
    private List<MeanOfCombat> meansOfCombat;
    private Time timeToCover100m; //miliseconds

    private boolean isInCombat;

    public Unit() {
        this.isInCombat = false;
        this.pastPositions = new LinkedList<>();
        this.setStartingPosition(new int[]{0,0});
        this.meansOfCombat = new ArrayList<>();
        this.movementPath = new LinkedList<>();
    }

    public void move() {

        if (this.movementPath.isEmpty()) return;

        this.addPastPosition(this.currentPosition);

        this.changeCoordinatesToNextMove(
                Objects.requireNonNull(this.movementPath.pollFirst())
        );
    }

    private void changeCoordinatesToNextMove(Compass compass) {

        this.currentPosition[0] += compass.getMovement()[0];
        this.currentPosition[1] += compass.getMovement()[1];
    }

    public boolean attack() {

        for (MeanOfCombat meanOfCombat : this.meansOfCombat) {

            if (Util.wasAttackSuccessful(meanOfCombat.getPossibilityForOneShotElimination())) return true;

        }

        return false;
    }

    public void loseMeansOfCombat() {

        if (this.meansOfCombat.size() > 0) this.meansOfCombat.remove(0);

    }

    public boolean isInFiringRange(double distance) {

        if (this.meansOfCombat == null) return false;

        if (this.meansOfCombat.size() == 0) return false;

        for (MeanOfCombat meanOfCombat : this.meansOfCombat) {

            if (meanOfCombat.getFiringRange() >= distance) return true;

        }

        return false;
    }

    public int[] getCurrentPosition() {
        return currentPosition;
    }

    public void setStartingPosition(int[] coordinates) {

        this.currentPosition = coordinates;
        this.pastPositions.clear();
    }

    public void setMovementPath(Deque<Compass> movementPath) {

        if (movementPath.size() > 1) this.movementPath = movementPath;
    }

    public Deque<Compass> getMovementPath() {
        return movementPath;
    }

    public List<MeanOfCombat> getMeansOfCombat() {
        return meansOfCombat;
    }

    public void setMeansOfCombat(List<MeanOfCombat> meansOfCombat) {
        this.meansOfCombat = meansOfCombat;
    }

    public void addMeanOfCombat(MeanOfCombat meansOfCombat) {
        this.meansOfCombat.add(meansOfCombat);
    }

    public Time getTimeToCover100m() {
        return timeToCover100m;
    }

    public void setTimeToCover100m(Time timeToCover100m) {
        this.timeToCover100m = timeToCover100m;
    }

    public boolean isInCombat() {
        return isInCombat;
    }

    public void setInCombat(boolean inCombat) {
        isInCombat = inCombat;
    }

    private void addPastPosition(int[] prevCoordinates) {

        int[] prevCoordinatesClone = new int[2];

        prevCoordinatesClone[0] = prevCoordinates[0];
        prevCoordinatesClone[1] = prevCoordinates[1];

        this.pastPositions.add(prevCoordinatesClone);
    }

    public LinkedList<int[]> getPastPositions() {
        return this.pastPositions;
    }

    public double getFiringRate() {

        return 1/(this.meansOfCombat.size() * this.meansOfCombat.get(0).getFiringRate());
    }

    public long getFiringTime() {

        return (long) (1/this.getFiringRate());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return Objects.equals(name, unit.name) && Objects.equals(timeToCover100m, unit.timeToCover100m);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, timeToCover100m);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "name='" + name + '\'' +
                ", currentPosition=" + Arrays.toString(currentPosition) +
                ", pastPositions=" + pastPositions +
                ", movementPath=" + movementPath +
                ", meansOfCombat=" + meansOfCombat +
                ", timeToCover100m=" + timeToCover100m +
                ", isInCombat=" + isInCombat +
                '}';
    }
}
