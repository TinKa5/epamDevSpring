package epam.ua.javacore.util;

import epam.ua.javacore.exception.NotFoundException;

public class Validate {
    public static void checkNotFoundWithId(boolean found, Long id) throws NotFoundException{
        checkNotFound(found, "id=" + id);
    }

    public static <T> T checkNotFoundWithId(T object, Long id) throws NotFoundException{
        return checkNotFound(object, "id=" + id);
    }

    public static <T> T checkNotFound(T object) throws NotFoundException{
        checkNotFound(object != null, " new id. Entity does not create");
        return object;
    }

    public static <T> T checkNotFound(T object, String msg) throws NotFoundException{
        checkNotFound(object != null, msg);
        return object;
    }

    public static void checkNotFound(boolean found, String msg) throws NotFoundException{
        if (!found) {
            throw new NotFoundException("Not exist entity with " + msg);
        }
    }



}
