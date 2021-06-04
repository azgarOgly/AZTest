package az.test;

public class TimeTest {
    public static void main(String[] args) {
        int counter = 0;
        int timeTotal = 3000;

        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        long sumT = 0;
        long minNano = Long.MAX_VALUE;
        long maxNano = Long.MIN_VALUE;
        long sumTN = 0;
        long start = System.currentTimeMillis();
        long startNano = System.nanoTime();
        long lastTime = start;
        long lastNano = startNano;
        while (true) {
            long tN = System.nanoTime();
            long t = System.currentTimeMillis();
            long dt = t - lastTime;
            long dtN = tN - lastNano;

            counter++;
            min = Math.min(min, dt);
            max = Math.max(max, dt);
            sumT += dt;
            minNano = Math.min(minNano, dtN);
            maxNano = Math.max(maxNano, dtN);
            sumTN += dtN;

            lastTime = t;
            lastNano = tN;

            if (t - start >= timeTotal) {
                break;
            }
        }
        long avg = sumT / counter;
        long avgNano = sumTN / counter;
        System.out.println("time: { min " + min + " - avg " + avg + " - max " + max + "} ms");
        System.out.println("nano: { min " + minNano + " - avg " + avgNano + " - max " + maxNano + "} ns");
        System.out.println("cycles: { " + counter + " in " + (lastTime - start) + " ms }");
    }
}
