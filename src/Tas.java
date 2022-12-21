
public class Tas {
    private String renk;
    private int sayi;
    private boolean okey = false;
    private boolean gosterge = false;
    private boolean sahteOkey = false;
    private boolean ciftKullanilmis = false;

    private boolean seriKullanilmis = false;

    public Tas() {}

    public Tas(String renk, int sayi) {
        super();
        this.renk = renk;
        this.sayi = sayi;

    }

    public Tas(String renk, int sayi, boolean sahteOkey) {
        super();
        this.renk = renk;
        this.sayi = sayi;
        this.sahteOkey = sahteOkey;

    }

    public String getRenk() {
        return renk;
    }

    public void setRenk(String renk) {
        this.renk = renk;
    }

    public int getSayi() {
        return sayi;
    }

    public void setSayi(int sayi) {
        this.sayi = sayi;
    }

    public boolean isOkey() {
        return okey;
    }

    public void setOkey(boolean okey) {
        this.okey = okey;
    }

    public boolean isGosterge() {
        return gosterge;
    }

    public void setGosterge(boolean gosterge) {
        this.gosterge = gosterge;
    }

    public boolean isSahteOkey() {
        return sahteOkey;
    }

    public void setSahteOkey(boolean sahteOkey) {
        this.sahteOkey = sahteOkey;
    }

    public boolean isCiftKullanilmis() {
        return ciftKullanilmis;
    }

    public void setCiftKullanilmis(boolean ciftKullanilmis) {
        this.ciftKullanilmis = ciftKullanilmis;
    }

    public boolean isSeriKullanilmis() {
        return seriKullanilmis;
    }

    public void setSeriKullanilmis(boolean seriKullanilmis) {
        this.seriKullanilmis = seriKullanilmis;
    }

    @Override
    public String toString() {
        return "Tas{" +
                "renk='" + renk + '\'' +
                ", sayi=" + sayi +
                ", okey=" + okey +
                ", gosterge=" + gosterge +
                ", sahteOkey=" + sahteOkey +
                ", ciftKullanilmis=" + ciftKullanilmis +
                ", seriKullanilmis=" + seriKullanilmis +
                "}\n";
    }
}

