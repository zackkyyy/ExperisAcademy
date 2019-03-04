package no.noroff.Animals;

import no.noroff.Movements.Climb;
import no.noroff.Movements.Run;
import no.noroff.Movements.Swim;
import no.noroff.Movements.Walk;

public class Bear extends Omnivore implements Swim, Run, Walk, Climb {
    public Bear() {
        super("Bear");
    }
}
