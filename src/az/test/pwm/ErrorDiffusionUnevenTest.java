package az.test.pwm;

public class ErrorDiffusionUnevenTest {

    private static final long TICK = 50;
    private static long time;

    public static void main(String[] args) {
        DiffusionProvider p = new DiffusionProvider();
        p.setLevel(50);
        int total = 0;
        for (int i=0; i < 10000; i++) {
            int v = p.get();
            total += v;
            System.out.println(v);
        }
        System.out.println(((double)total)/10000);
    }

    public static long getTime() {
        return time += Math.random()*TICK+1;
        //return time ++;
    }

    public static class DiffusionProvider {
        private int sum = 0;
        private int level = 0;
        private long lastTime = 0;
        private int lastValue = 0;

        public int get() {
            long time = getTime();
            long dt =  time - lastTime;
            int actual = lastValue * (int)dt;
            int expected = level * (int)dt;
            int error = expected - actual;
            sum += error;
            int result = sum > expected ? 100 : 0;
            System.out.println("s:" + sum + " dt:" + dt + " l:" + level + " r:" + result);
            lastTime = time; lastValue = result;
            return result;
        }

        public void setLevel(int level) {
            this.level = level;
        }
    }
}
