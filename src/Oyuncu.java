import java.util.ArrayList;
import java.util.List;

public class Oyuncu {
    private int oyuncuNumarasi;
    private List<Tas> isteka;
    private int ciftSayisi;
    private int seriSayisi;
    private int ciftKullanilmayanTasSayisi;//Çift dizilimde oyuncunun istekasında kalan kullanılmayan taş sayısı
    private int seriKullanilmayanTasSayisi;//Seri dizilimde oyuncunun istekasında kalan kullanılmayan taş sayısı

    public Oyuncu() {}

    public Oyuncu(int oyuncuNumarasi) {
        super();
        this.oyuncuNumarasi = oyuncuNumarasi;
        this.isteka = new ArrayList<>();
        this.ciftSayisi = 0;
        this.seriSayisi = 0;
        this.ciftKullanilmayanTasSayisi = 0;
        this.seriKullanilmayanTasSayisi = 0;
    }


    public int getOyuncuNumarasi() {
        return oyuncuNumarasi;
    }

    public void setOyuncuNumarasi(int oyuncuNumarasi) {
        this.oyuncuNumarasi = oyuncuNumarasi;
    }

    public List<Tas> getIsteka() {
        return isteka;
    }
    public void setIsteka(List<Tas> isteka) {
        this.isteka = isteka;
    }
    public int getCiftSayisi() {
        return ciftSayisi;
    }
    public void setCiftSayisi(int ciftSayisi) {
        this.ciftSayisi = ciftSayisi;
    }
    public int getSeriSayisi() {
        return seriSayisi;
    }
    public void setSeriSayisi(int seriSayisi) {
        this.seriSayisi = seriSayisi;
    }

    public int getCiftKullanilmayanTasSayisi() {
        return ciftKullanilmayanTasSayisi;
    }

    public void setCiftKullanilmayanTasSayisi(int ciftKullanilmayanTasSayisi) {
        this.ciftKullanilmayanTasSayisi = ciftKullanilmayanTasSayisi;
    }

    public int getSeriKullanilmayanTasSayisi() {
        return seriKullanilmayanTasSayisi;
    }

    public void setSeriKullanilmayanTasSayisi(int seriKullanilmayanTasSayisi) {
        this.seriKullanilmayanTasSayisi = seriKullanilmayanTasSayisi;
    }

    @Override
    public String toString() {
        return "Oyuncu{" +
                "oyuncuNumarasi=" + oyuncuNumarasi +
                ", isteka=" + isteka +
                ", ciftSayisi=" + ciftSayisi +
                ", seriSayisi=" + seriSayisi +
                ", ciftKullanilmayanTasSayisi=" + ciftKullanilmayanTasSayisi +
                ", seriKullanilmayanTasSayisi=" + seriKullanilmayanTasSayisi +
                "}\n";
    }
}
