package epam.ua.javacore.exeption;

import java.io.IOException;

public class NotExistIDException extends IllegalArgumentException {

    public NotExistIDException(){
        System.out.print("This id doesn't exist");
    }


}
