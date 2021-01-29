
package amiralbattı;

import java.util.ArrayList;

/*
    Bu class oyunun oynanacağı masayı ifade eder.
*/
public class OyunMasası {
    
    private String [][] masaMatris;
    private ArrayList<Gemi> gemiler;
    
    //masa oluşturulduğunda yeni bir arraylist ve 2d array oluşturuyoruz
    public OyunMasası(){
        masaMatris = new String[10][10]; // 10x10 luk bir matris oluşturuyoruz
        masayıDoldur();
        gemiler = new ArrayList<Gemi>();
    }
    
    private void masayıDoldur(){ //oluşturulan 10x10 lik arrayi 0 larla dolduruyoruz ki grafik yüzümüz güzel gözüksün
         for(int i = 0 ; i < this.masaMatris.length; i++){
            for(int j = 0 ; j < this.masaMatris[0].length; j++){
                masaMatris[i][j] = "0";
            }
        }
    }
    
    public void masayıBastır(){ // masayı konsola bastırmamızı yarayan fonksiyon
      for(int i = 0 ; i < this.masaMatris.length; i++){
            for(int j = 0 ; j < this.masaMatris[0].length; j++){
                System.out.print(this.masaMatris[i][j]+" ");
            }
            System.out.println("");
        }
    }

    //parametre olarak girilen gemi var olan diğer gemilerle kesişmiyorsa bu gemi masaya ekleniyor
    public void masayaGemiEkle(Gemi g){
        if(gemiDetect(g) == false){//herhangi bir gemi ile kesişmiyorsa
            for(int i = 0 ; i < g.getSize(); i++){
                if(g.getYon() == 0){//yönü yatay ise
                    masaMatris[g.getBaslangicY()][g.getBaslangicX()+i] = String.valueOf(g.getSize());
                }
                else if(g.getYon() == 1 ){//yönü dikey ise
                    masaMatris[g.getBaslangicY()+i][g.getBaslangicX()] = String.valueOf(g.getSize());
                }
            }
        }
        else{//bir gemi ile kesişiyorsa bu geminin x ve y si değiştiriliyor ve tekrar kontrol ediliyor. Recursion kullanılark
            while(gemiDetect(g) == true){
                g = new Gemi();
            }
            masayaGemiEkle(g);
        }
    }
    
    //Geminin yerleştirileceği indekslerde daha önceden bir gemi olup olmadığı kontrol edilerek bir boolean döndürüyoruz
    public boolean gemiDetect(Gemi gemi){
        for(int i = 0 ; i < gemi.getSize(); i++){
            if(gemi.getYon() == 0){
                if(!masaMatris[gemi.getBaslangicY()][gemi.getBaslangicX()+i].equals("0")){
                    return true;
                } 
            }
            else if(gemi.getYon() == 1){
                if(!masaMatris[gemi.getBaslangicY()+i][gemi.getBaslangicX()].equals("0")){
                    return true;
                } 
            }
        }
        return false;
    }
    
    
    public String[][] getMasaMatris() {
        return masaMatris;
    }

  
    public void addGemi(){
        Gemi g = new Gemi();
        masayaGemiEkle(g);
        gemiler.add(g);
    }

    public ArrayList<Gemi> getGemiler() {
        return gemiler;
    }
    
    
}
