package epam.ua.javacore.util;

import java.util.stream.Stream;

public class ViewUtil {
    public static void print(String s){
        System.out.println(s);
    }
    public static void print(Stream<String> s){
        s.forEach(x->System.out.println(x+", "));
    }
}
