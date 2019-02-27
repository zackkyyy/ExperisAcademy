package no.noroff.Movements;

import no.noroff.Animals.Animal;

public interface Swim {
    public static void getMovement(Animal a) {
        System.out.println("The " + a.getType() + " swims.");
    }
}
