package game;

class Player {

    private final String name;
    
    protected Player(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
