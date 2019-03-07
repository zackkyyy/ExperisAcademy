package no.noroff.Task21.database;

import no.noroff.Task21.model.Character;
import no.noroff.Task21.model.User;
import no.noroff.Task21.model.Class;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.List;

@Component
public class DBHandler {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("postgresdb");

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
            character.setClass_name(className);

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
            c.setClass_name(className);
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

    public List<User> getUser(int id, boolean showInfo){
        List<User> userList = null;

        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction transaction = null;

        try{
            transaction = manager.getTransaction();
            transaction.begin();

            if(showInfo) {
                userList = manager.createQuery("SELECT s FROM User s", User.class).getResultList();
            }
            else {
                userList = manager.createQuery("SELECT s FROM User s WHERE id = " + id, User.class).getResultList();
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
        for(User u : getUser(-1, true)) {
            System.out.println(u);
        }

        for(Character c : getCharacter(-1)) {
            System.out.println(c);
        }

        for(Class c : getClass("")) {
            System.out.println(c);
        }
    }

    public boolean createUserTable(){
        EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();

        boolean result = false;

        try{
            manager.createNativeQuery("CREATE TABLE IF NOT EXISTS user_info(" +
                                                "_id SERIAL NOT NULL PRIMARY," +
                                                "username VARCHAR(30) NOT NULL," +
                                                "password VARCHAR(30) NOT NULL").executeUpdate();

            result = true;
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return result;
    }

    public void addTests() {
        addUser("xX_Dragon_Xx", "password123");
        addUser("MrHacker", "Impossible1337");
        addUser("superman", "secretIdentity");
        addUser("hackerman1337", "hello111999");
        addUser("awesome", "topsecretpassword");

        addClass("Hunter", "Multi Shot");
        addClass("Paladin", "Sacrifice");
        addClass("Warlock", "Drain Life");
        addClass("Mage", "Time Warp");
        addClass("Death Knight", "Death Grip");

        addCharacter("xX_Dragon_Xx", "richkid", 55, "Paladin");
        addCharacter("xX_Dragon_Xx", "poorkid", 30, "Hunter");
        addCharacter("MrHacker", "hackzor1337", 70, "Mage");
        addCharacter("MrHacker", "hackzzz", 80, "Death Knight");
        addCharacter("superman", "Superman", 20, "Warlock");
        addCharacter("superman", "Shitkid", 10, "Hunter");
        addCharacter("superman", "troooolz", 30, "Death Knight");
        addCharacter("hackerman1337", "totalyLegit", 67, "Paladin");
        addCharacter("hackerman1337", "trustme", 87, "Mage");
        addCharacter("hackerman1337", "loyal", 56, "Warlock");
        addCharacter("awesome", "number1", 30, "Hunter");
    }
}
