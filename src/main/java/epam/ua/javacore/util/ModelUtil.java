package epam.ua.javacore.util;

import epam.ua.javacore.model.Entity;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ModelUtil {
    public static <T extends Entity> String setToStringId(Set<T> set){
        String s="";
        for (T entity:set) {
            if(!s.equals("")){s+=",";}
            Long t=(Long)entity.getId();
            s+=t.toString();
        }
        return s;
    }

    public static <T extends Entity> String setToString(Set<T> set){
        String s="";
        for (T entity:set) {
            if(!s.equals("")){s+=",";}
            s+=entity.toString();
        }
        return s;
    }



    public static Collection<Long> StringToSetId(String s){
        List result=new LinkedList();
        for (String i:s.split(",")) {
            result.add(Long.parseLong(i));
        };
        return result;
    }

    public static Long toLong(String s){
        return Long.parseLong(s);
    }


}
