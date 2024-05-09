import java.util.ArrayList;

public class DecisionMaker {
    private String nama;
    private ArrayList<Kriteria> kriterias;

    
    public DecisionMaker(String nama, ArrayList<Kriteria> kriterias) {
        this.nama = nama;
        this.kriterias = kriterias;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public ArrayList<Kriteria> getKriterias() {
        return kriterias;
    }
    public void setKriterias(ArrayList<Kriteria> kriterias) {
        this.kriterias = kriterias;
    }
}
