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

        Deque<Compass> movementPath = new LinkedList<>();
        movementPath.add(Compass.NORTH);
        movementPath.add(Compass.NORTH);
        movementPath.add(Compass.NORTH_EAST);
        movementPath.add(Compass.EAST);

        unit.setMovementPath(movementPath);
        unit.setStartingPosition(new Position(new int[]{0,0}));
        unit.setInCombat(false);
        unit.setTimeToCover100m(new Time(1000*60));

        uni2.setMovementPath(movementPath);
        uni2.setStartingPosition(new Position(new int[]{5,5}));
        uni2.setInCombat(false);

        System.out.println(unit);
        List<Unit> unitList = new ArrayList<>();
        unitList.add(unit);
        unitList.add(uni2);

        Terrain terrain = new Terrain(100);
        terrain.setHeight(1000);
        terrain.setWidth(1000);

        MoveSimulation moveSimulation = new MoveSimulation(unitList, terrain);

        while (true) {

            moveSimulation.simulate(clock.getCurrentTime());

            clock.incrementTime();

            if (clock.isCountingOver()) break;

        }

        System.out.println(unit.getPastPositions());
        System.out.println(uni2.getPastPositions());

    }
}