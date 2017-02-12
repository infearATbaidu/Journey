package design.pattern.builder;

/**
 * Created by infear on 2017/2/12.
 */
public class Director {
    Entity gen(IBuilder iBuilder){
        return iBuilder.buildPart1().buildPart2().build();
    }

    public static void main(String args[]){
        Director director = new Director();
        Entity entity = director.gen(new Builder());
        System.out.println(entity);
    }
}
