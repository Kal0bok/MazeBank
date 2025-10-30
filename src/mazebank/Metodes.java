package mazebank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Metodes {
	
	public static String virkneParbaud(String zinojums) {
	    String virkne;
	    while (true) {
	        virkne = JOptionPane.showInputDialog(null, zinojums,
	                "Datu ievade", JOptionPane.INFORMATION_MESSAGE);
	        if (virkne == null) {
	            return null; 
	        }
	        if (Pattern.matches("^[\\p{L} .]+$", virkne)) {
	            return virkne; 
	        } else {
	            JOptionPane.showMessageDialog(null,
	                    "Nepareizs formāts! Ievadiet tikai burtus un atstarpes.", 
	                    "Nekorekti dati", JOptionPane.WARNING_MESSAGE);
	        }
	    }
	}
	
	public static Double skaitlaParbaude(String zinojums, double min, double max) {
        String ievade;
        Double skaitlis;
        while (true) {
            ievade = JOptionPane.showInputDialog(null, zinojums,
                    "Datu ievade", JOptionPane.INFORMATION_MESSAGE);
            if (ievade == null)
                return -1.0;
            try {
                skaitlis = Double.valueOf(ievade);
                if (skaitlis < min || skaitlis > max) {
                    JOptionPane.showMessageDialog(null,
                            "Norādīts nederīgs skaitlis", "Nekorekti dati",
                            JOptionPane.WARNING_MESSAGE);
                    continue;
                }
                return skaitlis;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null,
                        "Netika ievadīts pareizs skaitlis", "Nekorekti dati",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }
	
	public static String ciparuParbaude(String zinojums, int garums) {
        String ievade;
        while (true) {
            ievade = JOptionPane.showInputDialog(null, zinojums,
                    "Datu ievade", JOptionPane.INFORMATION_MESSAGE);
            if (ievade == null)
                return null;
            if (Pattern.matches("^\\d{" + garums + "}$", ievade)) {
                return ievade;
            } else {
                JOptionPane.showMessageDialog(null,
                        "Nepareizs formāts! Vajag tikai ciparus!", "Nekorekti dati",
                        JOptionPane.WARNING_MESSAGE);  
            } 
        }      
    }
	
	public static String ciparuParbaudee(String zinojums, int garums) {
        String ievade;
        while (true) {
            ievade = JOptionPane.showInputDialog(null, zinojums,
                    "Datu ievade", JOptionPane.INFORMATION_MESSAGE);
            if (ievade == null)
                return null;
            if (Pattern.matches("^[0-9]{6}-[0-9]{5}$", ievade)) {
                return ievade;
            } else {
                JOptionPane.showMessageDialog(null,
                		"Nepareizs formāts! Vajag tikai ciparus!", "Nekorekti dati",
                        JOptionPane.WARNING_MESSAGE);  
            } 
        }      
    }
	
	public static int kontaIzvele(ArrayList<norkarte> konti) {
        String[] kSaraksts = new String[konti.size()];
        for (int i = 0; i < kSaraksts.length; i++) {
            norkarte k = (norkarte) konti.get(i);
            kSaraksts[i] = k.getBanka() + " " + k.noteiktAtlikumu() + "EUR";
        }

        String izveletais = (String) JOptionPane.showInputDialog(null,
                "Izvēlies kontu: ", "Izvēle", JOptionPane.QUESTION_MESSAGE, null,
                kSaraksts, kSaraksts[0]);

        return Arrays.asList(kSaraksts).indexOf(izveletais);
    }
	
}
