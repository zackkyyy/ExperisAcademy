package no.noroff.DataHibernate;

import no.noroff.DataHibernate.jpa.person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@SpringBootApplication
public class DataHibernateApplication {

	private static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("mamps");

	public static void main(String[] args) {

		addPerson("Jane", "Doe");

		for (person p: getPeople()) {
			System.out.println(p.toString());
		}

		SpringApplication.run(DataHibernateApplication.class, args);
	}

	private static List<person> getPeople(){
		List<person> peopleList = null;

		EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try{
			transaction = manager.getTransaction();
			transaction.begin();

			peopleList = manager.createQuery("SELECT s FROM person s", person.class).getResultList();

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

		return peopleList;
	}

	private static void addPerson(String fistName, String lastName){
		EntityManager manager =  ENTITY_MANAGER_FACTORY.createEntityManager();
		EntityTransaction transaction = null;

		try{
			transaction = manager.getTransaction();
			transaction.begin();

			person newPerson = new person();

			newPerson.setFirstName(fistName);
			newPerson.setLastName(lastName);

			manager.persist(newPerson);

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
	}

}
