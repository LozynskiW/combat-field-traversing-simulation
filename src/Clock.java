import java.sql.Time;

class Clock {

    private final Time timeStep;

    private Time endTime;

    private Time currentTime = new Time(0);

    private boolean isCountingOver;

    public Clock() {
        this.timeStep = new Time(1);
        this.isCountingOver = true;
    }

    public Clock(Time timeStep) {
        this.timeStep = timeStep;
        this.isCountingOver = true;
    }

    public Time getCurrentTime() {
        return currentTime;
    }

    public Time getTimeStep() {
        return timeStep;
    }

    public Time getEndTime() {
        return endTime;
    }

    public boolean isCountingOver() {
        return isCountingOver;
    }

    public void setEndTime(Time endTime) {

        this.endTime = endTime;

        if (endTime.getTime() > this.currentTime.getTime()) {
            this.isCountingOver = false;
        }

    }

    public void incrementTime() {

        if (this.isCountingOver) return;

        long t1 = this.currentTime.getTime();
        long t2 = this.timeStep.getTime();

        Time newTime = new Time(t1+t2);

        if (newTime.getTime() > this.endTime.getTime()) {
            this.isCountingOver = true;

        } else {
            this.currentTime = newTime;
        }
    }

    @Override
    public String toString() {
        return "Clock{" +
                "timeStep=" + timeStep +
                ", endTime=" + endTime +
                ", currentTime=" + currentTime +
                ", isCountingOver=" + isCountingOver +
                '}';
    }
}
