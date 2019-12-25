package epam.ua.javacore.util;

import epam.ua.javacore.exeption.CreatingEntityException;
import epam.ua.javacore.exeption.NotExistIDException;

import java.util.Set;

public class Validate {
    public static void idValidation(Long id){
        if (id<=0) {throw new NotExistIDException();}

    }

    public static void idValidation(Set<Long> id){

        for (Long k:id){
            if (k<=0) {throw new NotExistIDException();}
        }

    }

    public static void entityValidation(String s){
        if (s.isEmpty()){throw new CreatingEntityException();}
    }
}
