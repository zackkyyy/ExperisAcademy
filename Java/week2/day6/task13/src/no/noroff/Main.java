package no.noroff;

import no.noroff.Animals.Animal;
import no.noroff.Animals.Bear;
import no.noroff.Animals.Lion;
import no.noroff.Animals.Parrot;

public class Main {
    public static void main(String[] args) {
        Animal[] animals = {new Lion(), new Parrot(), new Bear()};

        for(Animal a : animals) {
            a.printMovements();
            a.move();
            System.out.println();
        }
    }
}
