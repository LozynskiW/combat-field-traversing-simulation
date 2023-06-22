class MeanOfCombat {

    private final double firingRate;
    private final double firingRange;

    private final double possibilityForOneShotElimination;

    public MeanOfCombat(double firingRate, int firingRange, double possibilityForOneShotElimination) {
        this.firingRate = firingRate;
        this.firingRange = firingRange;
        this.possibilityForOneShotElimination = possibilityForOneShotElimination;
    }

    public double getFiringRate() {
        return firingRate;
    }

    public double getFiringRange() {
        return firingRange;
    }

    public double getPossibilityForOneShotElimination() {
        return possibilityForOneShotElimination;
    }

    @Override
    public String toString() {
        return "MeanOfCombat{" +
                "firingRate=" + firingRate +
                ", firingRange=" + firingRange +
                ", possibilityForOneShotElimination=" + possibilityForOneShotElimination +
                '}';
    }
}
