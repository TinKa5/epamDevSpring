package epam.ua.javacore.util;

import epam.ua.javacore.model.Entity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

    public class ModelUtil {

        public static <T extends Entity> String setToString(Set<T> set){
            String s="";
            for (T entity:set) {
                if(!s.equals("")){s+=",";}
                s+=entity.toString();
            }
            return s;
        }
}
