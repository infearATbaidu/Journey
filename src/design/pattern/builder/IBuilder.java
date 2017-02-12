package design.pattern.builder;

/**
 * Created by infear on 2017/2/12.
 */
public interface IBuilder {
    IBuilder buildPart1();
    IBuilder buildPart2();
    Entity build();
}
