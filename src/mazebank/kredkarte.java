package mazebank;

public class kredkarte extends norkarte {
    private double kreditaLimits;

    public kredkarte(String banka, String vards, String uzvards,
    		String personasKods,String smartIdKods,
                     int derigumaGadi, String paraksts, double kreditaLimits) {
        super(banka, vards, uzvards, personasKods, smartIdKods, derigumaGadi, paraksts);
    }

    public boolean iznema(double summa) {
        if (noteiktAtlikumu() + getKreditaLimits() >= summa) {
            depozits(-summa);
            return true;
        }
        return false;
    }
    
    public double getKreditaLimits() {
		return kreditaLimits;
	}
    
    public void setKreditaLimits(double kreditaLimits) {
		this.kreditaLimits = kreditaLimits;
	}
    @Override
    public String izvadit() {
        return super.izvadit() + "\nKredīta limits: " + getKreditaLimits();
    }
	}
