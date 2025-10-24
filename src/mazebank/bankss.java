package mazebank;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class bankss {
	
	private static final String[] bankas = {"Swedbank", "SEB", "Citadele"};
    private static final String[] veidi = {"NoreKarte", "KredKarte"};
    private static final String[] atbilde = {"Jā", "Nē"};

    public static void main(String[] args) {
    	String izvele;
        int izvelesID;
        String[] darbibas = {"Jauns konts", "Noņemt kontu",
                "Kontu saraksts", "Kārtot pēc atlikuma", "Bankomāts",
                "Aizvērt programmu"};
        ArrayList<Object> konti = new ArrayList<>();
        
        do {
        	izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību",
                    "Izvēlne", JOptionPane.QUESTION_MESSAGE, null
                    , darbibas, darbibas[0]);
            if (izvele == null) break;

            izvelesID = Arrays.asList(darbibas).indexOf(izvele);
            
            switch(izvelesID) {
            
            case 0:
            	izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies kartes veidu",
                        "Izvēlne", JOptionPane.QUESTION_MESSAGE, null
                        , veidi, veidi[0]);
                if (izvele == null) break;

                String banka = (String) JOptionPane.showInputDialog(null, "Izvēlies banku",
                        "Izvēlne", JOptionPane.QUESTION_MESSAGE, null
                        , bankas, bankas[0]);
                if (banka == null) break;

                String vards = Metodes.virknesParbaude("Ievadi vārdu");
                if (vards == null) break;
                String uzvards = Metodes.virknesParbaude("Ievadi uzvārdu");
                if (uzvards == null) break;
                String persKods = Metodes.ciparuParbaude("Ievadi personas kodu (11 cipari)", 11);
                if (persKods == null) break;
                String smartId = Metodes.ciparuParbaude("Ievadi Smart ID kodu (6 cipari)", 6);
                if (smartId == null) break;
                double derG = Metodes.skaitlaParbaude("Ievadi derīguma termiņu gados (min 1)", 1, 10);
                if (derG < 0) break;
                int derigGadi = (int) derG; 
                String paraksts = Metodes.virknesParbaude("Ievadi parakstu");
                if (paraksts == null) break;

                if (izvele.equals("NoreKarte")) {
                    konti.add(new norkarte(banka, vards, uzvards, persKods, smartId,
                    		derigGadi, paraksts));
                    JOptionPane.showMessageDialog(null, "Veiksmīgi izveidota norēķinu karte",
                            "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    
                }
                break;
            	
            	
            case 1:
            	
            	break;
            	
            case 2:
            	
            	break;
            	
            case 3:
            	
            	break;
            	
            case 4:
            	
            	break;
            	
            case 5:
            	
            	break;
            
            }
            
            
            
            
            
        }while (izvelesID != 5);
    }
	
}
