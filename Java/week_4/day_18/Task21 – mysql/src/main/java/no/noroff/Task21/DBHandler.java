package no.noroff.Task21;

import no.noroff.Task21.model.Character;
import no.noroff.Task21.model.User;
import no.noroff.Task21.model.Class;

import javax.persistence.*;
import java.util.List;

public class DBHandler {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("db");

    public boolean addUser(String username, String password){
        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean result = false;

        try{
            transaction = manager.getTransaction();
            transaction.begin();

            User user = new User();

            user.setUsername(username);
            user.setPassword(password);

            manager.persist(user);

            transaction.commit();
            result = true;
        }catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return result;
    }

    public boolean addCharacter(String username, String character_name, int level, String className){
        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean result = false;

        try{
            transaction = manager.getTransaction();
            transaction.begin();

            Character character = new Character();

            character.setCharacter_name(character_name);
            character.setUsername(username);
            character.setLevel(level);
            character.setClassName(className);

            manager.persist(character);

            transaction.commit();
            result = true;
        }catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return result;
    }

    public boolean addClass(String className, String abilityName){
        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;
        boolean result = false;

        try{
            transaction = manager.getTransaction();
            transaction.begin();

            Class c = new Class();
            c.setName(className);
            c.setAbility(abilityName);

            manager.persist(c);

            transaction.commit();
            result = true;
        }catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return result;
    }

    public List<User> getUser(int id){
        List<User> userList = null;

        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = manager.getTransaction();
            transaction.begin();

            if(id >= 1) {
                userList = manager.createQuery("SELECT s FROM User s WHERE id = " + id, User.class).getResultList();
            } else {
                userList = manager.createQuery("SELECT s FROM User s", User.class).getResultList();
            }

            transaction.commit();
        }catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return userList;
    }

    public List<Character> getCharacter(int id){
        List<Character> characterList = null;

        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = manager.getTransaction();
            transaction.begin();

            if(id >= 1) {
                characterList = manager.createQuery("SELECT s FROM Character s WHERE id = " + id, Character.class).getResultList();
            } else {
                characterList = manager.createQuery("SELECT s FROM Character s", Character.class).getResultList();
            }

            transaction.commit();
        }catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return characterList;
    }

    public List<Class> getClass(String className){
        List<Class> classList = null;

        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = manager.getTransaction();
            transaction.begin();

            if(!className.equals("")) {
                classList = manager.createQuery("SELECT s FROM Class s WHERE name like '%" + className + "%'", Class.class).getResultList();
            } else {
                classList = manager.createQuery("SELECT s FROM Class s", Class.class).getResultList();
            }

            transaction.commit();
        }catch (Exception e){
            if(transaction != null) {
                transaction.rollback();
            }

            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return classList;
    }

    public void printAll() {
        for(User u : getUser(-1)) {
            System.out.println(u);
        }

        for(Character c : getCharacter(-1)) {
            System.out.println(c);
        }

        for(Class c : getClass("")) {
            System.out.println(c);
        }
    }
}
