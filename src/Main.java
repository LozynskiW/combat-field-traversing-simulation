import java.sql.Time;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        Clock clock = new Clock(new Time(1000));
        clock.setEndTime(new Time(1000*60));

        Unit unit = new Unit();
        unit.setTimeToCover100m(new Time(60000));

        Unit uni2 = new Unit();
        uni2.setTimeToCover100m(new Time(3000));

        MeanOfCombat tank = new MeanOfCombat(5000, 3000, 0.3);
        MeanOfCombat mortar = new MeanOfCombat(3000, 2000, 0.2);

        Deque<Compass> movementPath1 = new LinkedList<>();
        movementPath1.add(Compass.NORTH);
        movementPath1.add(Compass.NORTH);
        movementPath1.add(Compass.NORTH_EAST);
        movementPath1.add(Compass.EAST);
        movementPath1.add(Compass.EAST);
        movementPath1.add(Compass.EAST);
        movementPath1.add(Compass.NORTH_EAST);
        movementPath1.add(Compass.NORTH_EAST);
        movementPath1.add(Compass.NORTH_EAST);
        movementPath1.add(Compass.NORTH);
        movementPath1.add(Compass.NORTH);
        movementPath1.add(Compass.NORTH);
        movementPath1.add(Compass.EAST);
        movementPath1.add(Compass.EAST);
        movementPath1.add(Compass.EAST);

        Deque<Compass> movementPath2 = new LinkedList<>();
        movementPath1.add(Compass.SOUTH);
        movementPath1.add(Compass.SOUTH);
        movementPath1.add(Compass.SOUTH_WEST);
        movementPath1.add(Compass.SOUTH);
        movementPath1.add(Compass.WEST);
        movementPath1.add(Compass.WEST);
        movementPath1.add(Compass.SOUTH_WEST);
        movementPath1.add(Compass.SOUTH_WEST);
        movementPath1.add(Compass.SOUTH_WEST);
        movementPath1.add(Compass.SOUTH_WEST);
        movementPath1.add(Compass.SOUTH);
        movementPath1.add(Compass.SOUTH);
        movementPath1.add(Compass.WEST);
        movementPath1.add(Compass.WEST);
        movementPath1.add(Compass.WEST);

        unit.setName("1 brigade");
        unit.setMovementPath(movementPath1);
        unit.setStartingPosition(new int[]{0,0});
        unit.setInCombat(false);
        unit.setTimeToCover100m(new Time(1000*60));
        unit.addMeanOfCombat(tank);

        uni2.setName("2 brigade");
        uni2.setMovementPath(movementPath1);
        uni2.setStartingPosition(new int[]{10,10});
        uni2.setInCombat(false);
        uni2.setTimeToCover100m(new Time(1000*60));
        uni2.addMeanOfCombat(mortar);

        List<Unit> unitList = new ArrayList<>();
        unitList.add(unit);
        unitList.add(uni2);

        Terrain terrain = new Terrain();
        terrain.setHeight(1000);
        terrain.setWidth(1000);

        Simulation simulationTest = new Simulation(0.5, 100);

        simulationTest.setUnitList(unitList);
        simulationTest.setClock(clock);
        simulationTest.setTerrain(terrain);

        simulationTest.simulate();

        System.out.println(simulationTest.getUnitList().get(0).getPastPositions());

    }
}