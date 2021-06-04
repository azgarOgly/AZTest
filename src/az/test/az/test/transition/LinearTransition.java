package az.test.az.test.transition;

public class LinearTransition implements Transition {
    private final int startValue;
    private final int endValue;
    private final long startTime;
    private final long endTime;

    public LinearTransition(int startValue, int endValue, long startTime, long length) {
        this(startValue, startTime, endValue, startTime+length);
    }

    public LinearTransition(int startValue, long startTime, int endValue, long endTime) {
        this.startValue = startValue;
        this.endValue = endValue;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getValue(long time) {
        if (time <= startTime) return startValue;
        if (time >= endTime) return endValue;
        long length = endTime - startTime;
        if (length == 0) { return endValue; }
        float dt = time - startTime;
        float value = startValue + (float)(endValue-startValue) / length * dt;
        return (int)value;
    }

    @Override
    public boolean isComplete(long time) {
        return endTime <= time;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }
}
