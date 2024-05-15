public abstract class Aquatic {
    private boolean is_aquatic;

    public Aquatic(boolean is_aquatic){
        this.is_aquatic = is_aquatic;
    }

    public boolean getAquatic(){
        return is_aquatic;
    }
}