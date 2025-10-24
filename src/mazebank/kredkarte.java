package mazebank;


public class kredkarte extends norkarte {
    private double kreditaLimits;

    public kredkarte(String banka, String vards, String uzvards,
    		String personasKods,String smartIdKods,
                     int derigumaGadi, String paraksts, double kreditaLimits) {
        super(banka, vards, uzvards, personasKods, smartIdKods, derigumaGadi, paraksts);

    }
}