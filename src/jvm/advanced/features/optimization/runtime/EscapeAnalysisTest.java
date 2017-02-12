package jvm.advanced.features.optimization.runtime;

/**
 * Created by zhanggang02 on 2016/7/17.
 */
public class EscapeAnalysisTest {
    private int i;

    public EscapeAnalysisTest(int i) {
        this.i = i;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public static void main(String args[]) {
        long start = System.currentTimeMillis();
        for (int i = 0; i != 100000; i++) {
            EscapeAnalysisTest t1 = new EscapeAnalysisTest(i);
            EscapeAnalysisTest t2 = new EscapeAnalysisTest(i + 1);
            int j = t1.getI() * t2.getI();
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
