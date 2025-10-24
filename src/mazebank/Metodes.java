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

}
