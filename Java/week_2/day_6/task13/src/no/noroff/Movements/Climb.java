package no.noroff.Movements;

import no.noroff.Animals.Animal;

public interface Climb {
    public static void getMovement(Animal a) {
        System.out.println("The " + a.getType() + " climbs.");
    }
}
