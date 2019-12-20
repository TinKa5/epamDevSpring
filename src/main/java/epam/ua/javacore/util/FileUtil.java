package epam.ua.javacore.util;


import java.io.*;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epam.ua.javacore.util.ModelUtil.toLong;

public class FileUtil {


    public static Stream<String> readFromFile(String file) {
        Stream<String> res = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            res = br.lines();
            br.close();
        } catch (IOException e) {
        }
        return res;
    }

    public static String readFromFile(String file, Long id) {
        Stream<String> strings = readFromFile(file);
        return strings.filter(str -> toLong(str.split("", 1)[0]) == id).findFirst().get();

    }

    public static void writeToFile(String file,String s){
        writeToFile(file,Stream.of(s),true);
    }


    public static void deleteFromFile(String file,Long id){
       Stream<String> stream=readFromFile(file).filter(str -> toLong(str.split("")[0]) != id);
       writeToFile(file,stream,false);
    }


    private static void writeToFile(String file,Stream<String> s,boolean append) {
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,append))) {
             List<String> list=s.collect(Collectors.toList());
             for (String str:list) {
                 bw.write(str);
             }
         } catch (IOException e) {
        }


    }




}
