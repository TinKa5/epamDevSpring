package epam.ua.javacore.view;

import epam.ua.javacore.controller.AccountController;
import epam.ua.javacore.service.AccountService;

import java.util.InputMismatchException;
import java.util.Scanner;

import static epam.ua.javacore.util.ViewUtil.print;

public class AccountView {
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
        AccountController controller=new AccountController();
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
                print("Enter content");
                String content=sc.nextLine();
                print("Enter status: 1-Active,2-Nonactive");
                Integer stat=sc.nextInt();
                String status=(stat==1)?"ACTIVE":((stat==2)?"NONACTIVE":null);
                print(controller.add(content,status));
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
