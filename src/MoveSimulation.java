import java.sql.Time;
import java.util.List;

class MoveSimulation {

    private List<Unit> unitList;

    private Terrain terrain;

    private boolean isEverythingProperlyConfigured() {

        if (this.unitList.isEmpty()) return false;

        if (this.terrain.equals(null)) return false;

        for (Unit unit : this.unitList) {

            if (!this.isWithinBoundariesOfTerrain(unit.getCurrentPosition(), unit.getMovementPath().getFirst())) return false;

        }

        return true;

    }

    public boolean isWithinBoundariesOfTerrain(Position position, Compass nextMove) {

        if ((position.getCoordinates()[0] < 0) || (position.getCoordinates()[1] < 0)) return false;

        int nextPosInYAxis = position.getCoordinates()[0] + nextMove.getMovement()[0];
        int nextPosInXAxis = position.getCoordinates()[1] + nextMove.getMovement()[1];

        if (nextPosInYAxis >= terrain.getHeight()) return false;

        if (nextPosInXAxis >= terrain.getWidth()) return false;

        return true;

    }

    public void simulate(Time time) {

        int combatMoveModifier = 1;

        for (Unit unit : this.unitList) {

            if (unit.isInCombat()) combatMoveModifier = 5;

            if (time.getTime() % (combatMoveModifier*unit.getTimeToCover100m().getTime()) == 0) {
                System.out.println(time);
                unit.move();
            }

            combatMoveModifier = 1;

        }


    }

    public MoveSimulation(List<Unit> unitList, Terrain terrain) {
        this.unitList = unitList;
        this.terrain = terrain;
    }

    public void simulate(int time) {



    }

    public List<Unit> getUnitList() {
        return unitList;
    }

    public void setUnitList(List<Unit> unitList) {
        this.unitList = unitList;
    }

    public Terrain getTerrain() {
        return terrain;
    }

    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }
}
