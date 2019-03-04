package no.noroff.Movements;

import no.noroff.Animals.Animal;

public interface Walk {
    public static void getMovement(Animal a) {
        System.out.println("The " + a.getType() + " walks.");
    }
}
