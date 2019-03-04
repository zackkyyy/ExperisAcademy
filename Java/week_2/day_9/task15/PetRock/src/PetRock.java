public class PetRock {
    private String name;
    private boolean happy = false;

    public PetRock(String name) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isHappy() {
        return happy;
    }

    public void playWithRock() {
        happy = true;
    }

    public String getHappyMessage() {
        if(!happy) {
            throw new IllegalStateException();
        }

        return "I'm happy!";
    }

    public int getFavNumber() {
        return 42;
    }

    // This line causes 93% line coverage
    public void waitTillHappy() {
        while (!happy) {
            // do nothing!
        }
    }
}
