package no.noroff.repositories;

import org.junit.jupiter.api.Test;
import no.noroff.models.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryPersonTest {
    RepositoryPerson testRep = new RepositoryPerson();

    @Test
    void findPerson() {
        assertEquals(5,testRep.findPerson("Wokkk","Tran"));
        //assertNotEquals(5, testRep.findPerson("Mike, Kifle"));
    }

    @Test
    void findName() {
        String[] allPersons = {"Billy","Infante"};
        assertArrayEquals(allPersons,testRep.findName(1));

    }

    @Test
    void lookUp() {
        assertEquals("Mike",testRep.lookUp("kifle").get(0).getFirstName());
    }

    @Test
    void findRelations() {
        assertEquals("Mike",testRep.findRelations(1).get(0).getFirstname());
    }

    @Test
    void getPersons() {
        assertEquals("Billy",testRep.getPersons().get(0).getFirstName());
    }

    @Test
    void updateContactHome() {

    }

    @Test
    void updateContactWork() {
    }

    @Test
    void updateContactMobile() {
    }

    @Test
    void updateEmailWork() {
    }

    @Test
    void updateEmailPersonal() {
    }

    @Test
    void updatePersonFirstname() {
    }

    @Test
    void updatePersonLastname() {
    }

    @Test
    void updateBirthday() {
    }

    @Test
    void updatePostArea() {
    }

    @Test
    void updateStreetAddress() {
    }

    @Test
    void dropPerson() {
    }
}