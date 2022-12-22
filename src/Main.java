import java.util.*;
import java.util.stream.Collectors;

public class Main {

    final static int MAX_SAYI = 13;
    final static int ISTEKADAKI_TAS_SAYISI = 14;
    final static int OYUNCU_SAYISI = 4;
    final static int TOPLAM_TAS_SAYISI = 106;
    final static String[] RENKLER = {"Sarı", "Mavi", "Siyah", "Kırmızı"};

    public static void main(String[] args) {
        List<Tas> taslar = taslariOlustur();
        okeyBelirle(taslar);

        List<Oyuncu> oyuncular = new ArrayList<>();
        oyuncular.add(new Oyuncu(1));
        oyuncular.add(new Oyuncu(2));
        oyuncular.add(new Oyuncu(3));
        oyuncular.add(new Oyuncu(4));


        taslariDagit(oyuncular, taslar);

        taslariSirala(oyuncular);

        ciftKontrolEt(oyuncular);


        Map<Integer, Integer> kalanTaslar = new HashMap<>();
        for(Oyuncu oyuncu : oyuncular){
            int key = oyuncu.getOyuncuNumarasi();
            int value = oyuncu.getCiftKullanilmayanTasSayisi();
            kalanTaslar.put(key, value);
        }

        System.out.println(kalanTaslar);

        List<Integer> kalanTaslarListe = new ArrayList<>(kalanTaslar.values());
        kalanTaslarListe.sort(Comparator.naturalOrder());
        int min = kalanTaslarListe.get(0);
        List<Integer> kazananOyuncular = kalanTaslar.entrySet().stream().filter(e -> e.getValue() == min).map(Map.Entry::getKey).toList();

        System.out.println("Çift Dizilime Göre Bitmeye En Yakın Olan Oyuncu/Oyuncular:");
        for(Integer kazanan : kazananOyuncular){
            System.out.println(kazanan + " Numaralı Oyuncu");
        }
    }

    public static List<Tas> taslariOlustur(){
        List<Tas> butunTaslar = new ArrayList<>();
        for(int i=0; i<2; i++) {
            for(int sayi=1; sayi<=MAX_SAYI; sayi++) {
                for(String renk : RENKLER) {
                    Tas tas = new Tas(renk, sayi);
                    butunTaslar.add(tas);
                }
            }
            butunTaslar.add(new Tas("Sahte Okey", 0, true));
        }
        Collections.shuffle(butunTaslar);
        return butunTaslar;
    }

    public static void okeyBelirle(List<Tas> butunTaslar){
        Tas gosterge = new Tas();
        Tas okey = new Tas();
        Random r = new Random();
        do {
            gosterge = butunTaslar.get(r.nextInt(TOPLAM_TAS_SAYISI));
        }while(gosterge.getSayi() == 0);//Göstergenin sahte okey olmaması kontrolü


        okey.setRenk(gosterge.getRenk());

        if(gosterge.getSayi() == 13) {
            okey.setSayi(1);
        }
        else {
            okey.setSayi(gosterge.getSayi()+1);
        }

        for(int i=0; i<TOPLAM_TAS_SAYISI; i++) {
            if(butunTaslar.get(i).getRenk().equals(okey.getRenk()) && butunTaslar.get(i).getSayi() == okey.getSayi()) {
                butunTaslar.get(i).setOkey(true);
                butunTaslar.get(i).setSayi(99);//Karışıklık olmaması için okey taşının sayısını 99 olarak belirledim
            }
            if(butunTaslar.get(i).getRenk().equals(gosterge.getRenk()) && butunTaslar.get(i).getSayi() == gosterge.getSayi()) {
                butunTaslar.get(i).setGosterge(true);
            }
            if(butunTaslar.get(i).isSahteOkey()) { //Sahte okeyin değerlerinin okey değerlerine eşitlenmesi
                butunTaslar.get(i).setRenk(okey.getRenk());
                butunTaslar.get(i).setSayi(okey.getSayi());
            }
        }

        for(int i=0; i<TOPLAM_TAS_SAYISI; i++) {//Taş destesinden bir adet gösterge taşının çıkarılması
            if(butunTaslar.get(i).isGosterge()) {
                butunTaslar.remove(i);
                break;
            }
        }
        /*System.out.println("---------------------------------------------");
        System.out.println(gosterge);
        System.out.println("---------------------------------------------");
        System.out.println(okey);
        System.out.println("---------------------------------------------");*/
    }

    public static List<Oyuncu> taslariDagit(List<Oyuncu> oyuncular, List<Tas> butunTaslar) {
        int sayac = 0;
        for(Oyuncu oyuncu : oyuncular) {//Oyunculara 14'er adet taş verilmesi
            List<Tas> verilecekTaslar = butunTaslar.subList(sayac * ISTEKADAKI_TAS_SAYISI, (sayac + 1) * ISTEKADAKI_TAS_SAYISI);
            oyuncu.getIsteka().addAll(verilecekTaslar);
            sayac++;
        }
        Random r = new Random();
        oyuncular.get(r.nextInt(OYUNCU_SAYISI)).getIsteka().add(butunTaslar.get(OYUNCU_SAYISI * ISTEKADAKI_TAS_SAYISI));//Rastgele bir oyuncuya ekstra 1 adet taş verilmesi
        return oyuncular;
    }

    public static void taslariSirala(List<Oyuncu> oyuncular){//Taşların önce sayıya, sonra renge göre sıralanması
        Comparator<Tas> comparator = Comparator.comparing(Tas::getSayi).thenComparing(Tas::getRenk);
        for(Oyuncu oyuncu : oyuncular) {
            oyuncu.setIsteka(oyuncu.getIsteka().stream().sorted(comparator).collect(Collectors.toList()));
        }
    }

    public static void ciftKontrolEt(List<Oyuncu> oyuncular) {

        for(Oyuncu oyuncu : oyuncular) {
            int kullanilmayanTasSayisi = oyuncu.getIsteka().size();
            int istekadakiToplamTas = oyuncu.getIsteka().size();

            //Sıralanmış istekada seçilen taş ve bir sonraki taşın renklerinin ve sayılarının eşit olma durumu
            //Seçilen taşın okey olmaması, gösterge olmaması ve kullanılmış olmaması gerekli
            for(int i=0; i<istekadakiToplamTas-1; i++) {
                if(!oyuncu.getIsteka().get(i).isOkey() && !oyuncu.getIsteka().get(i).isGosterge() && !oyuncu.getIsteka().get(i).isCiftKullanilmis()) {
                    if(oyuncu.getIsteka().get(i).getSayi() == oyuncu.getIsteka().get(i+1).getSayi() && oyuncu.getIsteka().get(i).getRenk().equals(oyuncu.getIsteka().get(i+1).getRenk())) {
                        oyuncu.setCiftSayisi(oyuncu.getCiftSayisi() + 1);
                        kullanilmayanTasSayisi-=2;
                        oyuncu.getIsteka().get(i).setCiftKullanilmis(true);
                        oyuncu.getIsteka().get(i+1).setCiftKullanilmis(true);
                    }
                }
            }
            oyuncu.setCiftKullanilmayanTasSayisi(kullanilmayanTasSayisi);

            //Seçilen taşın okey ve gösterge olma durumları
            for(int j=0; j<istekadakiToplamTas; j++) {
                if(!oyuncu.getIsteka().get(j).isCiftKullanilmis()) {//Seçilen taşın kullanılmış olmama durumu
                    if(oyuncu.getIsteka().get(j).isOkey()) {//Seçilen taş okey ise
                        oyuncu.setCiftSayisi(oyuncu.getCiftSayisi() + 1);
                        kullanilmayanTasSayisi-=1;
                        oyuncu.setCiftKullanilmayanTasSayisi(kullanilmayanTasSayisi);
                        oyuncu.getIsteka().get(j).setCiftKullanilmis(true);
                        for(int k=0; k<istekadakiToplamTas; k++) {//Istekada okey olması durumunda rastgele kullanılmamış bir taş seçip okeyle birlikte çift olarak kullanma
                            if(!oyuncu.getIsteka().get(k).isCiftKullanilmis()) {
                                kullanilmayanTasSayisi-=1;
                                oyuncu.setCiftKullanilmayanTasSayisi(kullanilmayanTasSayisi);
                                oyuncu.getIsteka().get(k).setCiftKullanilmis(true);
                                break;
                            }
                        }
                    }
                    else if(oyuncu.getIsteka().get(j).isGosterge()) {//Seçilen taş gösterge ise
                        oyuncu.setCiftSayisi(oyuncu.getCiftSayisi() + 1);
                        kullanilmayanTasSayisi-=1;
                        oyuncu.setCiftKullanilmayanTasSayisi(kullanilmayanTasSayisi);
                        oyuncu.getIsteka().get(j).setCiftKullanilmis(true);
                    }
                }
            }
        }
    }

    /*public static void ayniRenkSiraliKontrolEt(List<Oyuncu> oyuncular) {

        for(Oyuncu oyuncu : oyuncular){
            Map<String, List<Tas>> map = new HashMap<String, List<Tas>>();
            for(Tas tas : oyuncu.getIsteka()){
                String key = tas.getRenk();
                if(map.containsKey(key)){
                    List<Tas> liste = map.get(key);
                    liste.add(tas);
                }
                else{
                    List<Tas> liste = new ArrayList<Tas>();
                    liste.add(tas);
                    map.put(key, liste);
                }
            }
            System.out.println(map);

            for (String renk : RENKLER){
                List<Tas> seriler = new ArrayList<Tas>();
                if(map.get(renk).size() > 2){
                    for (int i=1; i<map.get(renk).size(); i++){

                }

                    
                }
            }
        }

    }*/

    /*public static void ayniSayiFarkliRenkKontrolEt(List<Oyuncu> oyuncular) {

    }*/
}
