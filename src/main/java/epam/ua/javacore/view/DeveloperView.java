package epam.ua.javacore.view;

import epam.ua.javacore.controller.DeveloperController;
import epam.ua.javacore.service.DeveloperService;


import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static epam.ua.javacore.util.ModelUtil.toLong;
import static epam.ua.javacore.util.ViewUtil.print;

public class DeveloperView {
    String MAIN = "1-Get all \n2-Get Id\n3-Add element\n4-Delete\n0-Exit\n";

   public void printMain() {
        print(MAIN);
        Scanner scan = new Scanner(System.in);
        Integer choise;
        try {
            do {
                choise = scan.nextInt();
                setChoise(choise);
            } while (true);
        } catch (InputMismatchException e) {
        }
    }

    private void setChoise(int i){
        DeveloperController controller=new DeveloperController();
        Scanner sc=new Scanner(System.in);
        switch (i){
            case(1):
                print(controller.getAll());
                break;
            case(2):
                print("Write id: ");
                print(controller.get(sc.nextLong()));
                break;
            case(3):
                print("Enter name dev");
                String name=sc.nextLine();
                print("Enter id skills set using ,");
                String set=sc.nextLine();
                String[] skills=set.split(",");
                Set<Long> skillsId=Stream.of(skills).map(x->toLong(x)).collect(Collectors.toSet());
                print("Enter id account");
                Long account=sc.nextLong();
                print(controller.add(name,skillsId,account));
                break;
            case(4):
                print("Write id: ");
                controller.delete(sc.nextLong());
                print("Success delete");
                break;
            case(0):
                mainView.run();
        }
    }
}
