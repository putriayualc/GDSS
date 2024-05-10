import java.util.ArrayList;

public class Borda {
    private ArrayList<DecisionMaker> decisionMakers;
    private ArrayList<Alternatif> alternatifs;
    private int[][] peringkat;

    public Borda(ArrayList<DecisionMaker> dm, ArrayList<Alternatif> at, int[][] p) {
        this.decisionMakers = dm;
        this.alternatifs = at;
        this.peringkat = p;
    }

    public void hitung(){
        int[][] totalPeringkat = totalPeringkat();
        int[] pembobot = bobot();
        int[] skorBORDA = skorBorda(pembobot, totalPeringkat);
        System.out.println("+====HASIL AKHIR BORDA====+");
        System.out.printf("| %-10s | %-10s |\n", "Alternatif", "Skor BORDA");
        System.out.println("===========================");
        for (int i = 0; i < alternatifs.size(); i++) {
            System.out.printf("| %-10s | %-10s |\n",alternatifs.get(i).getNamaAlternatif(), skorBORDA[i]);
        }
        System.out.println("===========================");

        int rank1=0;
        int max = 0;
        for (int i = 0; i < skorBORDA.length; i++) {
            if (skorBORDA[i] > max) {
                max = skorBORDA[i];
                rank1 = i;
            }
        }

        System.out.println("Menurut perhitungan dengan metode BORDA, Alternatif " + alternatifs.get(rank1).getNamaAlternatif() +" merupakan alternatif terbaik dengan skor akhir BORDA = "+skorBORDA[rank1]);
    }

    private int[][] totalPeringkat(){
        int[][] totPer = new int[peringkat.length][peringkat.length];
        for (int i = 0; i < peringkat.length; i++) {
            for (int j = 0; j < peringkat[0].length; j++) {
                int rank = peringkat[i][j];
                totPer[i][rank-1]++;
            }
        }
        return totPer;
    }

    private int[] bobot(){
        int[] bobot = new int[alternatifs.size()];
        for (int i = 0; i < bobot.length; i++) {
            bobot[i]=bobot.length-(i+1);
        }
        return bobot;
    }

    private int[] skorBorda(int[] bobot, int[][] peringkat){
        int[] skor = new int[alternatifs.size()];
        for (int i = 0; i < peringkat.length; i++) {
            for (int j = 0; j < peringkat[0].length; j++) {
                skor[i] += peringkat[i][j]*bobot[j]; 
            }
        }
        return skor;
    }
    

}
