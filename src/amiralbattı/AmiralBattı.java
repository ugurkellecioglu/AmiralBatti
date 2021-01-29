
package amiralbattı;


import java.util.Scanner;

/*
Bu bir amiral battı oyunudur. 
Kullanıcıdan girilen zorluk seviyesine göre masaya gemi koyulur ve 
kullanıcının gemilerin büyüklükleri toplamından zorluğu ile doğru orantılı olarak ek hak tanınır.
Kullanıcı bir defa seçtiği yeri tekrar seçemez. Kullanıcının her atışta hakkı gider.

Oyunun amacı haklarımız bitmeden bütün gemileri bulmaktır.
Vurulan gemilerin yerleri X ile boşa giden yerler ise B ile ifade edilmektedir.
*/
public class AmiralBattı {
  

    
    static boolean oyunCheck = true; //her hamle sonrasında oyunun bitip bitmediğini kontrol etmek için bir boolean
    static int gemiSayisi = 0, atesHakki = 0; //kullanıcıdan alınan zorluğa göre belirlenecek değerler
    static OyunMasası gorunenMasa = new OyunMasası(); // kullanıcıya gözükecek olan masa
    static OyunMasası oyunMasa = new OyunMasası(); // gemilerin dizildiği arkaplandaki masa
    static String zorlukSecimi ;// kullanıcının zorluk seçimi
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
      
        
        //zorluk seçimine göre gemi sayısının belirtilmesi
            gemiSayisi = 5;
         //gemiSayisi degiskenine göre masaya gemi eklenmesi
         for(int i = 0 ; i < gemiSayisi ; i ++){
            oyunMasa.addGemi();
         }
         
         //toplam_buyukluk methodunun döndürdüğü değere göre atesHakki tanımlanması
    
                atesHakki = toplam_buyukluk(oyunMasa) + 10;
          
        
        
        //oyunMasa.masayıBastır(); //cevaplar
        
        
        //oyun öncesi bilgi kısmı
        System.out.println("Oyun Başlıyor..");
        System.out.println("Gemilerin hepsini vurmanız için " + atesHakki + " kere atış hakkınız vardır.");
        System.out.println("Masa da şuan " + gemiSayisi + " tane uzunluğu 3 ila 6 arasında olan gemi vardır.");
        System.out.println("SOL ÜST KÖŞE 1 1 noktasıdır.");
        System.out.println("Bir gemi vurursanız X olarak işaretlenir vurduğunuz yerde gemi yoksa B olarak işaretlenir.");
        System.out.println("X ve Y indeksi girerek ateş edeceğiniz yeri seçin. Örnek : 3 5");
        gorunenMasa.masayıBastır();
        //kullanıcıdan aldığımız inputu array şeklinde tutup bir eksiltiyoruz çünkü ilk indeks 0 0 fakat kullanıcının aklı karışmaması için 1 1 aldık.
        String [] kordinatlar = scan.nextLine().split(" ");
        int x = Integer.parseInt(kordinatlar[0]) - 1;
        int y = Integer.parseInt(kordinatlar[1]) - 1;
        
        while(atesHakki != 0){ //ateş hakkımız varken
            
            /*
                Seçilen x ve y görünenMasa da daha önce seçilmemişse
                asıl olan masada x ve y sini kontrol edip gemi olup olmadığını kontrol ediyoruz. 0 değilse gemi var demektir.
            **/
            if( gorunenMasa.getMasaMatris()[y][x].equals("0")){
                if(!oyunMasa.getMasaMatris()[y][x].equals("0")){// asıl masada bu indekste 0 yoksa bir gemi var demektir 
                System.out.println("Bir gemi buldunuz VURULDU! Kalan hakkınız: " + atesHakki);
                gorunenMasa.getMasaMatris()[y][x] = "X";
                gorunenMasa.masayıBastır();
                }
                else{ // 0 ise burada gemi var
                    System.out.println("Gemi bulamadınız. Kalan hakkınız: " + atesHakki);
                    gorunenMasa.getMasaMatris()[y][x] = "B";
                    gorunenMasa.masayıBastır();
                }
                kontrol(oyunMasa, gorunenMasa);//oyunun bitip bitmediğini kontrol eden method
                if(oyunCheck) break;//kontrol sonrasında oyun bitmişte break
                System.out.println("Yeni X ve Y ekseni girin");//bitmemişse devam ediyoruz ve atış hakkımızı düşüyüoruz
                kordinatlar = scan.nextLine().split(" ");
                --atesHakki;
            }
            else{//Görünenmasadaki indeks daha önceden seçilmişse yani 0'dan başka (X veya B) ise başka bir indeks girilmesini istiyoruz
                System.out.println("Burası daha önceden seçilmişti. Lütfen yeni bir indeks seçin. ");
                kordinatlar = scan.nextLine().split(" ");
            }
            x = Integer.parseInt(kordinatlar[0]) - 1;
            y = Integer.parseInt(kordinatlar[1]) - 1;
            

        }
        //sonucun basılması
        if(oyunCheck){
            System.out.println("Tebrikler oyunu kazandınız.");
        }
        else{
            System.out.println("Maalesef oyunu kaybettiniz.");
        }
        
        
    }
    
    //masadaki gemilerin büyüklüklerini topluyor
    public static int toplam_buyukluk(OyunMasası masa){
        int toplam = 0;
        for(Gemi gemi : masa.getGemiler()){
            toplam += gemi.getSize();
        }
        
        return toplam;
    }
    
    //Görünen masadaki indeksle buna karşılık gelen oyunMasası indeksini karşılaştırıyor.
    //Eğer GörünenMasadaki her X olan indeks oyunMasasındaki bir geminin indeksiyle örtüşüyorsa oyun bitmiş demektir.
    public static void kontrol(OyunMasası oyunmasa, OyunMasası gorunenMasa){
        oyunCheck = true;
        for(int i = 0 ; i < oyunmasa.getMasaMatris().length; i++){
            for(int j = 0 ; j < oyunmasa.getMasaMatris()[0].length; j++){
                if(!oyunmasa.getMasaMatris()[j][i].equals("0") && !gorunenMasa.getMasaMatris()[j][i].equals("X")){
                   oyunCheck = false; 
                   break;
                }
            }
            if(!oyunCheck) break;
        }
    }
    
}
