package design.pattern.singleton;

/**
 * Created by infear on 2017/3/4.
 * 静态内部类 static nested class
 */
public class Singleton {
    private final static class holder{
        private static Singleton instance = new Singleton();
    }

    private Singleton(){}
    public static Singleton getInstance(){
        return holder.instance;
    }
}
