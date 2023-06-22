import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.sql.Time;
import java.util.Objects;

class Unit {

    private Position currentPosition;

    private LinkedList<Position> pastPositions;
    private Deque<Compass> movementPath; //FIFO
    private List<MeanOfCombat> meansOfCombat;
    private Time timeToCover100m; //miliseconds

    private boolean isInCombat;

    public Unit() {
        this.isInCombat = false;
        this.pastPositions = new LinkedList<>();
        this.setStartingPosition(new Position(0,0));
    }

    public void move() {

        if (this.movementPath.isEmpty()) return;

        this.addPastPosition(this.currentPosition);

        this.currentPosition.changeCoordinatesToNextMove(
                Objects.requireNonNull(this.movementPath.pollFirst())
        );
    }

    public boolean isTargetDetected() {

        for (MeanOfCombat meanOfCombat : this.meansOfCombat) {



        }

        return false;

    }

    public void attack(Unit unit) {

    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setStartingPosition(Position position) {

        this.currentPosition = position;
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

    private void addPastPosition(Position position) {

        Position pastPosition = new Position();
        pastPosition.setCoordinates(position.getCoordinates());

        this.pastPositions.add(pastPosition);
    }

    public LinkedList<Position> getPastPositions() {
        return this.pastPositions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit unit = (Unit) o;
        return isInCombat == unit.isInCombat && Objects.equals(currentPosition, unit.currentPosition) && Objects.equals(pastPositions, unit.pastPositions) && Objects.equals(movementPath, unit.movementPath) && Objects.equals(meansOfCombat, unit.meansOfCombat) && Objects.equals(timeToCover100m, unit.timeToCover100m);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currentPosition, pastPositions, movementPath, meansOfCombat, timeToCover100m, isInCombat);
    }

    @Override
    public String toString() {
        return "Unit{" +
                "currentPosition=" + currentPosition +
                ", pastPositions=" + pastPositions +
                ", movementPath=" + movementPath +
                ", meansOfCombat=" + meansOfCombat +
                ", timeToCover100m=" + timeToCover100m +
                ", isInCombat=" + isInCombat +
                '}';
    }
}
