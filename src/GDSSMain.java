import java.util.ArrayList;

public class GDSSMain {
    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("               GDSS");
        System.out.println("=================================");
        ArrayList<Kriteria> kriteriaGita = new ArrayList<>();
        kriteriaGita.add(new Kriteria("Harga", 15, false));
        kriteriaGita.add(new Kriteria("Jarak dengan Kampus", 10, false));
        kriteriaGita.add(new Kriteria("Fasilitas kos", 20, true));
        kriteriaGita.add(new Kriteria("Luas Kamar", 25, true));
        kriteriaGita.add(new Kriteria("Kebersihan", 15, true));
        kriteriaGita.add(new Kriteria("Keamanan", 15, true));

        ArrayList<Kriteria> kriteriaAyah = new ArrayList<>();
        kriteriaAyah.add(new Kriteria("Harga", 20, false));
        kriteriaAyah.add(new Kriteria("Jarak dengan Kampus", 15, false));
        kriteriaAyah.add(new Kriteria("Fasilitas kos", 15, true));
        kriteriaAyah.add(new Kriteria("Luas Kamar", 10, true));
        kriteriaAyah.add(new Kriteria("Kebersihan", 15, true));
        kriteriaAyah.add(new Kriteria("Keamanan", 25, true));

        ArrayList<Kriteria> kriteriaIbu = new ArrayList<>();
        kriteriaIbu.add(new Kriteria("Harga", 25, false));
        kriteriaIbu.add(new Kriteria("Jarak dengan Kampus", 10, false));
        kriteriaIbu.add(new Kriteria("Fasilitas kos", 15, true));
        kriteriaIbu.add(new Kriteria("Luas Kamar", 10, true));
        kriteriaIbu.add(new Kriteria("Kebersihan", 20, true));
        kriteriaIbu.add(new Kriteria("Keamanan", 20, true));

        ArrayList<Alternatif> alternatifs = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            alternatifs.add(new Alternatif("A"+(i+1)));   
        }

        ArrayList<DecisionMaker> dm = new ArrayList<>();
        dm.add(new DecisionMaker("Gita", kriteriaGita));
        dm.add(new DecisionMaker("Ayah", kriteriaAyah));
        dm.add(new DecisionMaker("Ibu", kriteriaIbu));

        int[][] peringkat = new int[][]{
            {3,4,4},
            {4,2,2},
            {8,7,8},
            {5,8,7},
            {7,5,6},
            {2,1,3},
            {1,3,1},
            {6,6,5}
        };

        System.out.println("Hasil perhitungan DSS :");
        System.out.println("----------------------------------------");
        System.out.print("| Alternatif |");
        for (int i = 0; i < dm.size(); i++) {
            System.out.printf(" %-5s |", dm.get(i).getNama());
        }
        System.out.println("\n---------------------------------------");
        for (int i = 0; i < peringkat.length; i++) {
            System.out.printf("| %-10s | ", alternatifs.get(i).getNamaAlternatif());
            for (int j = 0; j < peringkat[0].length; j++) {
                System.out.printf("%-5s | ",peringkat[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------------------------------\n");

        Borda bd = new Borda(dm, alternatifs, peringkat);

        bd.hitung();

        System.out.println();

        Copeland cp = new Copeland(dm, alternatifs, peringkat);
        cp.hitung();

    }
}
