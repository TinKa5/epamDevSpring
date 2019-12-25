package epam.ua.javacore.view;


import epam.ua.javacore.controller.SkillController;
import epam.ua.javacore.model.Developer;

import java.util.InputMismatchException;
import java.util.Scanner;

import static epam.ua.javacore.util.ViewUtil.print;

public class mainView {

    static final String MAIN = "1-Skills\n2-Accounts\n3-Developers\n0-Exit";

    public static void main(String[] args) {

        new mainView().run();


    }

    public static void run(){
        print(MAIN);
        Scanner scan = new Scanner(System.in);

        Integer choise;
        try {
            do {
                choise = scan.nextInt();
                setChoise(choise);
            } while (choise!=0);
        } catch (InputMismatchException e) {
        }

    }

    private static void setChoise(int i){
        SkillController controller=new SkillController();

        switch (i){
            case(1):
                new SkillView().printMain();
                break;
            case(2):
                new AccountView().printMain();
                break;
            case(3):
                new DeveloperView().printMain();
                break;
        }
    }

}
