import java.util.Random;

class Util {

    public static double calculateDistanceBetweenUnits(Unit unitA, Unit unitB) {

        double yDistance = unitA.getCurrentPosition()[0] - unitB.getCurrentPosition()[0];
        double xDistance = unitA.getCurrentPosition()[1] - unitB.getCurrentPosition()[1];

        return Math.sqrt(Math.pow(xDistance, 2) + Math.pow(yDistance, 2));
    }

    public static boolean isWithinBoundariesOfTerrain(int[] coordinates, Terrain terrain) {

        if (coordinates[0] > terrain.getHeight()) return false;

        if (coordinates[1] > terrain.getWidth()) return false;

        return true;

    }

    public static boolean wasAttackSuccessful(double possibilityForOneShotElimination) {

        Random random = new Random();

        if (random.nextDouble(1) <= possibilityForOneShotElimination) return true;

        return false;
    }
}
