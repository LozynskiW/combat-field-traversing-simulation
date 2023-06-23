import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

class Simulation {

    private List<Unit> unitList;
    private List<Unit> shootingUnits;

    private Map<Unit, Time> detectingUnits;

    private Terrain terrain;

    private Clock clock;

    private final double intensityOfDetection;
    private final double intensityOfDetectionDistance;

    Simulation(double intensityOfDetection, double intensityOfDetectionDistance) {
        this.intensityOfDetection = intensityOfDetection;
        this.intensityOfDetectionDistance = intensityOfDetectionDistance;
        this.shootingUnits = new ArrayList<>();
        this.detectingUnits = new HashMap<>();
    }

    public void simulate() {

        if (!isEverythingProperlyConfigured()) {
            System.out.println("Misconfiguration: unit outside of terrain");
            return;

        }

        while (true) {

            if (clock.isCountingOver()) {
                System.out.println(clock.getCurrentTime().toString() + ": END OF SIMULATION" );
                break;
            }

            if (this.areUnitsInCombat(this.clock.getCurrentTime().getTime())) {

                this.flowWhenInCombat(clock.getCurrentTime());
            } else {

                this.flowWhenNotInCombat(clock.getCurrentTime());
            }

            clock.incrementTime();
        }
    }

    private boolean areUnitsInCombat(long currentTime) {

        double distance;

        if (this.detectingUnits.size() > 0) return true;

        if (this.shootingUnits.size() > 0) return true;

        for (Unit unitDetecting : unitList) {

            for (Unit unitBeingDetected : unitList) {

                if (unitDetecting.equals(unitBeingDetected)) continue;

                distance = Util.calculateDistanceBetweenUnits(unitDetecting,unitBeingDetected);

                if (unitDetecting.isInFiringRange(distance)) {

                    Time timeToDetect = this.calculateTimeToDetectEnemy(unitDetecting, unitBeingDetected);

                    long timeWhenDetectionWillBeDone = currentTime + timeToDetect.getTime();

                    this.detectingUnits.put(unitDetecting, new Time(timeWhenDetectionWillBeDone));
                    return true;
                }

            }
        }
        return false;
    }

    private void flowWhenNotInCombat(Time time) {

        for (Unit unit : unitList) {

            if (time.getTime() % (unit.getTimeToCover100m().getTime()) == 0) {
                unit.move();
                System.out.println(time + " " + unit.getName() + " moved to " + Arrays.toString(unit.getCurrentPosition()));
            }
        }

    }

    private void flowWhenInCombat(Time time) {

        for (Unit unit : unitList) {

            if (time.getTime() % (5*unit.getTimeToCover100m().getTime()) == 0) {
                unit.move();
                this.shootingUnits.clear();
                System.out.println(time + " " + unit.getName() + " moved to " + Arrays.toString(unit.getCurrentPosition()));
                continue;
            }

            if (this.detectingUnits.containsKey(unit)) {

                System.out.println(this.detectingUnits.get(unit).getTime() + " " + time.getTime());
                if (this.detectingUnits.get(unit).getTime() <= time.getTime()) {

                    this.detectingUnits.remove(unit);
                    this.shootingUnits.add(unit);
                    System.out.println(time + " " + unit.getName() + " started to detect enemy");
                    continue;
                }

            }

            if (this.shootingUnits.contains(unit)) {

                if (time.getTime() % unit.getFiringTime() == 0) {

                    if (unit.attack()) {

                        Unit unit1 = this.getTheOtherUnit(unit);
                        unit1.loseMeansOfCombat();
                        System.out.println(time + " " + unit.getName() + " successfully attacked enemy");
                        continue;
                    } else {
                        System.out.println(time + " " + unit.getName() + " attacked enemy, but missed");
                    }
                }
            }

        }

    }

    private Time calculateTimeToDetectEnemy(Unit unitDetecting, Unit unitBeingDetected) {

        double distanceBetweenUnits = Util.calculateDistanceBetweenUnits(unitDetecting, unitBeingDetected);

        int n = unitDetecting.getMeansOfCombat().size();
        int m = unitBeingDetected.getMeansOfCombat().size();

        double dist;

        if (distanceBetweenUnits >= this.terrain.getMinGridLength()) {
            dist = distanceBetweenUnits;
        } else {
            dist = terrain.getMinGridLength();
        }

        return new Time((long) (n*m*Math.pow(this.intensityOfDetectionDistance/ dist, 2) * this.intensityOfDetection));

    }

    private boolean isEverythingProperlyConfigured() {

        if (this.unitList.isEmpty()) return false;

        if (this.terrain.equals(null)) return false;

        for (Unit unit : this.unitList) {

            if (!Util.isWithinBoundariesOfTerrain(unit.getCurrentPosition(), this.terrain)) return false;

        }

        return true;

    }

    public Unit getTheOtherUnit(Unit unit) {

        return this.unitList.stream().filter(u -> !u.equals(unit)).toList().get(0);
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

    public Clock getClock() {
        return clock;
    }

    public void setClock(Clock clock) {
        this.clock = clock;
    }
}
