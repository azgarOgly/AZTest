package az.test.az.test.transition;

public class TransitionTest {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis()+100;
        long endTime = startTime + 1200;
        LinearTransition lt = new LinearTransition(0, 100, startTime, 1000);
        while (true) {
            long time = System.currentTimeMillis();
            System.out.println(lt.getValue(time));
            if (time >= endTime) {
                break;
            }
        }
    }
}
