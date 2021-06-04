package az.test.az.test.transition;

public interface Transition {
    int getValue(long time);
    boolean isComplete(long time);
    long getEndTime();
}
