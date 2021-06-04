package az.test.az.test.transition;

public class ChainTransitionTest {

    public static void main(String[] args) {
        ChainTransition chain = new ChainTransition();
        chain.addTransition(new LinearTransition(0, (long) 0, 100, 10));
        chain.addTransition(new LinearTransition(100, (long)10, 100, 20));
        chain.addTransition(new LinearTransition(100, (long)20, 50, 30));
        chain.addTransition(new LinearTransition(50, (long)30, 20, 30));
        chain.addTransition(new LinearTransition(20, (long)30, 0, 40));
        chain.addTransition(new LinearTransition(0, (long)40, 72, 50));
        chain.addTransition(new LinearTransition(72, (long)50, 50, 60));
        chain.addTransition(new LinearTransition(0, (long)70, 0, 80));
        chain.addTransition(new LinearTransition(100, (long)80, 0, 90));

        for (long t=0; t < 100; t++) {
            System.out.println(chain.getValue(t));

        }
    }
}
