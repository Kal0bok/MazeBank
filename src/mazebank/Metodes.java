package mazebank;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class Metodes {
	
	public static String virknesParbaude(String zinojums) {
        String virkne;
        do {
            virkne = JOptionPane.showInputDialog(zinojums);
            if (virkne == null)
                return null;
        } while (!Pattern.matches("^[\\p{L} .]+$", virkne));
        return virkne;
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
                        "Nepareizs formāts! Vajag " + garums + " ciparus.", "Nekorekti dati",
                        JOptionPane.WARNING_MESSAGE);
                
                
            }
        }   
    }
	
	

}
