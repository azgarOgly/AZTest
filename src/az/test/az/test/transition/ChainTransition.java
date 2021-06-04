package az.test.az.test.transition;

import java.util.ArrayList;
import java.util.List;

public class ChainTransition implements Transition {
    private Transition currentTransition;
    private final List<Transition> transitions = new ArrayList<>();
    private long endTime;

    public int getValue(long time) {
        int result = currentTransition.getValue(time);
        if (currentTransition.isComplete(time)) {
            currentTransition = getNextTransision();
        }
        return result;
    }

    public void addTransition(Transition transition) {
        endTime = transition.getEndTime();
        if (transitions.isEmpty()) {
            currentTransition = transition;
        }
        transitions.add(transition);
    }

    @Override
    public boolean isComplete(long time) {
        return endTime <= time;
    }

    @Override
    public long getEndTime() {
        return endTime;
    }

    private Transition getNextTransision() {
        int indexOfCurrent = transitions.indexOf(currentTransition);
        return indexOfCurrent == transitions.size()-1 ? currentTransition : transitions.get(indexOfCurrent+1);
    }
}
