package az.test.pwm;

public class ErrorDiffusionTest {
    public static void main(String[] args) {
        DiffusionProvider p = new DiffusionProvider();
        p.setLevel(35);
        int total = 0;
        for (int i=0; i < 10000; i++) {
            int v = p.get();
            total += v;
            System.out.println(v);
        }
        System.out.println(((double)total)/10000);
    }

    public static class DiffusionProvider implements Provider, PWMLeveled {
        private int sum = 0;
        private int level = 0;

        @Override
        public int get() {
            sum += level;
            int result = sum > level ? 100 : 0;
            sum -= result;
            // System.out.println("s:" + sum + " l:" + level + " r:" + result);
            return result;
        }

        @Override
        public void setLevel(int level) {
            this.level = level;
        }
    }

    public interface Provider {
        int get();
    }
    public interface PWMLeveled {
        void setLevel(int level);
    }
}
