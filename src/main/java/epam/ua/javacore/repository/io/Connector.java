package epam.ua.javacore.repository.io;

import epam.ua.javacore.model.Account;
import epam.ua.javacore.model.Developer;
import epam.ua.javacore.model.Entity;
import epam.ua.javacore.model.Skill;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * For create relate between object.
 * It apply do get(id) for Developer without loading all objects Skill and Account
 * It use recurse calling "get' lower object for high level object (if they haven't loaded yet in "loadingClasses")
 *
 * Its structure is:
 *            Skill.class--------------------------------Account.class---------------
 *        /                \                          /                \
 *      /                    \                     /                     \
 *  skillRepository.Class  loadingClasses   accountRepository.class   loadingClasses
 *                             |                                            |
 *                        skillObject1                                 accountObject1
 *                        skillObject2                                    .....
 *                          .....
 */
public class Connector {

    HashMap<Class,Load> loadList=new HashMap<Class, Load>(){{
        put(Developer.class,new Load(DeveloperRepositoryImpl.class));
        put(Skill.class,new Load(SkillRepositoryImpl.class));
        put(Account.class,new Load(AccountRepositoryImpl.class));
    }};



    private class Load{
        Class loader;
        Map<Long, Entity> loadingClasses;
        Load(Class loader){
            this.loader=loader;
            this.loadingClasses=new HashMap<Long, Entity>();

        }

    }

    public Entity createConnection(Class connectingClass,Long id){

        if (!loadList.containsKey(connectingClass)){return null;};

        Load load=loadList.get(connectingClass);
        Entity entity=load.loadingClasses.get(id);

        if (entity!=null){                                      //if this object was load early return it
           return entity;
        }else {                                                 //else load this object with calling "get" from GenericRepository
           return loadingConnectionObject(load,id);
        }


    }

    private Entity loadingConnectionObject(Load load, Long id) {
        try {
            Object objectRepo = load.loader.newInstance();
            Method method = load.loader.getDeclaredMethod("get", Long.class);
            return  (Entity) method.invoke(objectRepo, id);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e2) {
            return null;
        }
    }

    public void registration(Entity entity){
        loadList.get(entity.getClass()).loadingClasses.put(entity.getId(),entity);
    }
}
