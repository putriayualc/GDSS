import java.util.ArrayList;

public class Copeland {
    private ArrayList<Alternatif> alternatifs;
    private ArrayList<DecisionMaker> decisionMakers;
    private int[][] peringkat;

    public Copeland(ArrayList<DecisionMaker> dm,ArrayList<Alternatif> alternatifs, int[][] peringkat) {
        this.alternatifs = alternatifs;
        this.decisionMakers = dm;
        this.peringkat = peringkat;
    }

    public void hitung(){
        String[][] preferensi = tabelPreferensi();
        String[][] menang = perbandingan(preferensi);
        int[][] skor = skor(menang);
        System.out.println("+===HASIL COPELAND SCORE===+");
        System.out.printf("| %-10s | %-10s |\n", "Alternatif", "Skor COPELAND");
        System.out.println("===========================");
        for (int i = 0; i < alternatifs.size(); i++) {
            System.out.printf("| %-10s | %-10s |\n",alternatifs.get(i).getNamaAlternatif(), skor[i][2]);
        }
        System.out.println("===========================");
        int rank1=0;
        int max = 0;
        for (int i = 0; i < skor.length; i++) {
            if (skor[i][2] > max) {
                max = skor[i][2];
                rank1 = i;
            }
        }

        System.out.println("Menurut perhitungan dengan metode COPELAND, Alternatif " + alternatifs.get(rank1).getNamaAlternatif() +" merupakan alternatif terbaik dengan skor akhir COPELAND = "+skor[rank1][2]);
    }

    private String[][] tabelPreferensi(){
        String[][] tP = new String[decisionMakers.size()][alternatifs.size()];

        for (int i = 0; i < peringkat.length; i++) {
            for (int j = 0; j < peringkat[0].length; j++) {
                tP[j][peringkat[i][j]-1] = alternatifs.get(i).getNamaAlternatif();
            }
        }

        return tP;
    }

    private String[][] perbandingan(String[][] preferensi){
        int jml = (alternatifs.size() * (alternatifs.size()-1))/2;
        String[][] menang = new String[jml][3];//kolom pertama vs kolom kedua, kolom ketiga = pemenang
        for (int i = 0, baris = 0; i < alternatifs.size(); i++) {
            for (int j = i + 1; j < alternatifs.size(); j++) {
                menang[baris][0] = alternatifs.get(i).getNamaAlternatif();
                menang[baris][1] = alternatifs.get(j).getNamaAlternatif();
                baris++;
            }
        }

        for (int i = 0; i < menang.length; i++) {
            int sum =0;
            for (int baris = 0; baris < preferensi.length; baris++) {
                if(cariIndex(menang[i][0], preferensi[baris])<cariIndex(menang[i][1], preferensi[baris])){
                    sum++;
                }
            }
            
            if(sum >= (decisionMakers.size()/2+1)){
                menang[i][2] = menang[i][0];
            }else{
                menang[i][2] = menang[i][1];
            }
        }

        return menang;
    }
    
    private int cariIndex(String cari, String[] arraye){
        int index = -1;
        for (int i = 0; i < arraye.length; i++) {
            if (arraye[i].equalsIgnoreCase(cari)) {
                index = i;
            }
        }
        return index;
    }

    private int[][] skor(String[][] menang){
        int[][] skor = new int[alternatifs.size()][3];

        for (int at = 0; at < skor.length; at++) {
            for (int i = 0; i < menang.length; i++) {
                if (menang[i][2].equalsIgnoreCase(alternatifs.get(at).getNamaAlternatif())) {
                    skor[at][0]++;
                }
                skor[at][1] = (alternatifs.size()-1)-skor[at][0];
                skor[at][2] = skor[at][0] - skor[at][1];
            }
        }
        
        return skor;
    }
}
