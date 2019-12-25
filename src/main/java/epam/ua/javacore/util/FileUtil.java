package epam.ua.javacore.util;


import epam.ua.javacore.exeption.FileWorkException;
import epam.ua.javacore.model.Skill;
import epam.ua.javacore.repository.io.Connector;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epam.ua.javacore.util.ModelUtil.toLong;
import static epam.ua.javacore.util.ViewUtil.print;

public class FileUtil {


    public static Stream<String> readFromFile(String file) {
        LinkedList<String> list=new LinkedList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(new File(file))))
        {
            String s=br.readLine();

            while (s!=null) {
                if (s!=""){list.add(s);}
                s = br.readLine();
            }
        }catch (IOException e){
        }
        return list.stream();
    }

    public static String readFromFile(String file, Long id) throws NoSuchElementException {
        Stream<String> strings = readFromFile(file);
        return strings.filter(str -> toLong(str.split(" ")[0]).equals(id)).findFirst().get();

    }

    public static void writeToFile(String file,String s){
        s="\n"+s;
        writeToFile(file,Stream.of(s),true);
    }


    public static Long getMaxId(String file)throws NoSuchElementException{
        String s=readFromFile(file).max((str1,str2)->toLong(str1.split(" ")[0]).compareTo(toLong(str2.split(" ")[0]))).get();
        return toLong(s.split(" ")[0]);
    }
    public static void deleteFromFile(String file,Long id){
       Stream<String> stream=readFromFile(file).filter(str -> toLong(str.split("")[0]) != id);
       writeToFile(file,stream,false);
    }


    private static void writeToFile(String file,Stream<String> s,boolean append) {

         try (BufferedWriter bw = new BufferedWriter(new FileWriter(file,append))) {
             List<String> list=s.collect(Collectors.toList());
             boolean first=true;
             for (String str:list) {
                 if (!first) {bw.write("\n");}
                 bw.write(str);
                 first=false;
             }
         } catch (IOException e) {
        }


    }




}
