package no.noroff.Animals;

import no.noroff.Movements.*;

import java.util.ArrayList;
import java.util.Random;

public abstract class Animal {
    private String type;
    private ArrayList<Movements> movements = new ArrayList<>();

    public Animal(String type) {
        this.type = type;
        checkMovements();
    }

    public String getType() {
        return type;
    }

    /**
     * Checks which interfaces are implemented for this animal
     */
    private void checkMovements() {
        if(this instanceof Climb) {
            movements.add(Movements.CLIMB);
        }

        if(this instanceof Fly) {
            movements.add(Movements.FLY);
        }

        if(this instanceof Run) {
            movements.add(Movements.RUN);
        }

        if(this instanceof Swim) {
            movements.add(Movements.SWIM);
        }

        if(this instanceof Walk) {
            movements.add(Movements.WALK);
        }
    }

    /**
     * Prints available movements for this animal
     */
    public void printMovements() {
        System.out.print("The " + type + " can ");

        for(int i = 0; i < movements.size(); i++) {
            if(i+1 >= movements.size()) {
                System.out.print(movements.get(i) + ".");
            } else if(i+2 >= movements.size()) {
                System.out.print(movements.get(i) + " and ");
            } else {
                System.out.print(movements.get(i) + ", ");
            }
        }

        System.out.println();
    }

    /**
     * Randomly picks an available movement for this animal
     */
    public void move() {
        Random r = new Random();
        int i = r.nextInt(movements.size());
        Movements movement = movements.get(i);

        switch (movement) {
            case CLIMB:
                Climb.getMovement(this);
                break;
            case FLY:
                Fly.getMovement(this);
                break;
            case RUN:
                Run.getMovement(this);
                break;
            case SWIM:
                Swim.getMovement(this);
                break;
            case WALK:
                Walk.getMovement(this);
                break;
        }
    }
}
