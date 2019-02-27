package no.noroff.Animals;

import no.noroff.Movements.Climb;
import no.noroff.Movements.Run;
import no.noroff.Movements.Walk;

public class Lion extends Carnivore implements Walk, Run, Climb {
    public Lion() {
        super("Lion");
    }
}
