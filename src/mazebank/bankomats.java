package mazebank;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class bankomats {

	private static final String[] bankas = {"Swedbank", "SEB", "Citadele"};

    public static void apkalpot(ArrayList<Object> konti) {
        if (konti.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nav neviena konta", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String atmBanka = (String) JOptionPane.showInputDialog(null, "Izvēlies bankomāta banku",
                "Izvēlne", JOptionPane.QUESTION_MESSAGE, null, bankas, bankas[0]);
        if (atmBanka == null) return;

        int izv = Metodes.kontaIzvele(konti);
        norkarte karte = (norkarte) konti.get(izv);

        boolean banks = karte.getBanka().equals(atmBanka);
        double komBank = banks ? 0 : 0.99;

        String pinIev = JOptionPane.showInputDialog("Ievadi PIN kodu");
        if (pinIev == null || !pinIev.equals(karte.getPin())) {
            JOptionPane.showMessageDialog(null, "Nepareizs PIN!", "Kļūda", JOptionPane.ERROR_MESSAGE);
            return;
        }
	
}
}