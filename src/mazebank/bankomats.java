package mazebank;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JOptionPane;

public class bankomats {

	private static final String[] bankas = {"Swedbank", "SEB", "Citadele"};

    public static void apkalpo(ArrayList<norkarte> konti) {
        if (konti.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena konta", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String atmBanka = (String) JOptionPane.showInputDialog(null, "Izvēlies bankomāta banku",
                "Izvēlne", JOptionPane.QUESTION_MESSAGE, null, bankas, bankas[0]);
        if (atmBanka == null) return;

        int izv = Metodes.kontaIzvele(konti);
        if (izv < 0 || izv >= konti.size()) return;
        norkarte karte = (norkarte) konti.get(izv);

        boolean banks = karte.getBanka().equals(atmBanka);
        double komBank = banks ? 0 : 0.99;
        
        int i = 0;
        do {
        String pinIev = code.bankomatsKods("PIN", konti, izv); 
        if (pinIev == null) {
            return; 
        }
        if (pinIev.isEmpty() || !pinIev.equals(karte.getPin())) {
            i++;
            if (i < 3) {
                JOptionPane.showMessageDialog(null, "Nepareizs PIN! Mēģinājumi atlikuši: " + (3 - i), "Kļūda", JOptionPane.ERROR_MESSAGE);
            } else {
                konti.remove(izv); 
                JOptionPane.showMessageDialog(null, "Tava karte ir izdzesta!!!", "Kļūda", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } else {
            break;
        }
    } while (i < 3);
        
        String izvele;
        int izvelesID;
        String[] darbibas = {"Izņemt naudu", "Pielikt naudu", "Apskatīt atlikumu", "Mainīt PIN", "Atcelt"};

        do {
            izvele = (String) JOptionPane.showInputDialog(null, "Izvēlies darbību",
                    "Bankomāts", JOptionPane.QUESTION_MESSAGE, null, darbibas, darbibas[0]);
            if (izvele == null) {
                JOptionPane.showMessageDialog(null, "Neaizmirsti savu karti!", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                break;
            }

            izvelesID = Arrays.asList(darbibas).indexOf(izvele);

            switch (izvelesID) {
                case 0: 
                    double maxIzn = karte.noteiktAtlikumu();
                    if (karte instanceof kredkarte) {
                        maxIzn += ((kredkarte) karte).getKreditaLimits(); 
                    }
                    double summa = Metodes.skaitlaParbaude("Cik izņemt?", 0.01, maxIzn);
                    if (summa < 0) break;
                    if (!banks) {
                        summa *= (1 - komBank);
                        JOptionPane.showMessageDialog(null, "Komisija 99%, saņemsi " + summa, "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    }
                    if (karte.iznemaa(summa + (banks ? 0 : summa * komBank / (1 - komBank)))) {
                        JOptionPane.showMessageDialog(null, "Izņemta " + summa, "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Nepietiek līdzekļu", "Kļūda", JOptionPane.ERROR_MESSAGE);
                    }
                    break;
                case 1: 
                    double depo = Metodes.skaitlaParbaude("Cik pielikt?", 0.01, 100000);
                    if (depo < 0) break;
                    karte.depozits(depo);
                    JOptionPane.showMessageDialog(null, "Pielikts " + depo + " EUR", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2: 
                    JOptionPane.showMessageDialog(null, "Atlikums: " + karte.noteiktAtlikumu() + " EUR", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 3: 
                	String jaunsPin = code.bankomatsKods("PIN", konti, izv); 

                    if (jaunsPin != null || jaunsPin.length() == 4) {
                        karte.mainPin(jaunsPin);
                        JOptionPane.showMessageDialog(null, "PIN nomainīts", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;       
                case 4: 
                    JOptionPane.showMessageDialog(null, "Neaizmirsti savu karti!", "Paziņojums", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        } while (izvelesID != 5);
    }
}