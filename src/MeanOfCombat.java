class MeanOfCombat {

    private final double firingRate;
    private final double firingRange;

    private final double possibilityForOneShotElimination;

    private boolean isDestroyed;

    private int ammoUsed;

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

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void setDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }

    public int getAmmoUsed() {
        return ammoUsed;
    }

    public void setAmmoUsed(int ammoUsed) {
        this.ammoUsed = ammoUsed;
    }

    @Override
    public String toString() {
        return "MeanOfCombat{" +
                "firingRate=" + firingRate +
                ", firingRange=" + firingRange +
                ", possibilityForOneShotElimination=" + possibilityForOneShotElimination +
                ", isDestroyed=" + isDestroyed +
                ", ammoUsed=" + ammoUsed +
                '}';
    }
}
