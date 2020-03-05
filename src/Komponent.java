public abstract class Komponent {
    private int id;
    public boolean dostepnosc;

    public Komponent() { this.dostepnosc = true; }

    public void setId(int id) {this.id = id; }

    public int getId() {
        return id;
    }

    public boolean getDostepnosc() {
        return dostepnosc;
    }

    public String czyDostepna(){
        if(dostepnosc)
            return "dostepna";
        else
            return "niedostepna";
    }
}
