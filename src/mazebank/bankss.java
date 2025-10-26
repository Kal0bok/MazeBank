package mazebank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;


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
                	double lim = Metodes.skaitlaParbaude("Ievadi kredīta limitu", 100, 10000);
                    if (lim < 0) break;
                    konti.add(new kredkarte(banka, vards, uzvards, persKods, smartId, derigGadi, paraksts, lim));
                    JOptionPane.showMessageDialog(null, "Veiksmīgi izveidota kredītkarte",
                            "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            	
            case 1:
            	if (konti.size() > 0) {
                    int kontID = Metodes.kontaIzvele(konti);

                    konti.remove(kontID);
                    JOptionPane.showMessageDialog(null, "Veiksmīgi izdzēsts konts",
                            "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nav ievadīts neviens konts",
                            "Kļūda", JOptionPane.ERROR_MESSAGE);
                }
            	break;
            	
            case 2:
            	if (konti.size() > 0) {
                    String str = "Kontu skaits: " + konti.size() +
                            "\n_________________________________\n";
                    for (int i = 0; i < konti.size(); i++) {
                        str += ((norkarte) konti.get(i)).izvadit() +
                                "\n_________________________________\n";
                    }

                    JTextArea ta = new JTextArea(str, 10, 40);
                    ta.setEditable(false);
                    JScrollPane sp = new JScrollPane(ta);
                    sp.setVerticalScrollBarPolicy(
                            ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    JOptionPane.showMessageDialog(ta, sp, "Kontu saraksts",
                            JOptionPane.PLAIN_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nav ievadīts neviens konts",
                            "Kļūda", JOptionPane.ERROR_MESSAGE);
                }
            	break;
            	
            case 3:
            	if(konti.size() > 0) {
					String atb = (String) JOptionPane.showInputDialog(null,
							"Kārtot kontus pēc atlikuma augoši?", "Izvēle",
							JOptionPane.INFORMATION_MESSAGE, null, 
							atbilde, atbilde[0]);
					if(atb != null) {
						if(atb.equals("Jā")) {
							konti.sort(null);
							JOptionPane.showMessageDialog(null, 
									"Konti sakārtoti augoši!", "Kārtošana",
									JOptionPane.INFORMATION_MESSAGE);
						}else {
							konti.sort(Collections.reverseOrder());
							JOptionPane.showMessageDialog(null, 
									"Konti sakārtoti dilstoši!", "Kārtošana",
									JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "Nav ievadīts neviens konts",
							"Kļūda", JOptionPane.ERROR_MESSAGE);
					break;
				}
				break;
            	
            case 4:
            	bankomats.apkalpot(konti);
            	break;
            	
            case 5:
            	JOptionPane.showMessageDialog(null, "Programma apturēta", "Apturēta",
                        JOptionPane.PLAIN_MESSAGE);
            	break;
            
            }
            
            
            
            
            
        }while (izvelesID != 5);
    }
	
}
