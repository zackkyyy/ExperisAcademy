package no.noroff.Animals;

import no.noroff.Movements.Climb;
import no.noroff.Movements.Fly;
import no.noroff.Movements.Walk;

public class Parrot extends Herbivore implements Walk, Fly, Climb {
    public Parrot() {
        super("Parrot");
    }
}
