
package amiralbattı;

import java.util.Random;

/*
Gemi class ı masaya yerleşecek olan gemilerin özelliklerini içerir

yon : 0 yatay ekseni , 1 ise dikey ekseni ifade eder
baslangicX, baslangicY: geminin yerleştirilmeye başlanacağı yeri ifade eder
size : geminin boyutunu ifade eder
*/
public class Gemi extends OyunMasası{
    private int yon, baslangicX, baslangicY, size;
    
    OyunMasası masa = new OyunMasası();
    public Gemi(){
        rastgeleGemiUret();
    }
    
    private void rastgeleGemiUret(){
        Random rnd = new Random();
        this.yon = rnd.nextInt(2); // 0 ya da 1 döndürür geminin sahip olduğu yön
        this.size = rnd.nextInt(4) + 3; // çok zor olmaması adına 3 ile 6 arasında bir büyüklük olacak
        this.baslangicX = rnd.nextInt(10); // 0. indeksle 9.indeks arasında rasgele bir sayı
        this.baslangicY = rnd.nextInt(10);
        //baslangicX ile size arasında bir ilişki olduğu için masanın sınırları içerisinde kalmasını istiyoruz
        if(this.yon == 0){ // yatay yönde ise 
            
            while(!sinirIcerisinde()){
                this.baslangicX = rnd.nextInt(10);
            }
            
        }
        else if (this.yon == 1){ // dikey yönde ise
            while(!sinirIcerisinde()){
                this.baslangicY = rnd.nextInt(10);
            }
        }

    }
    //üretilen geminin masanın sınırları çerçevesinde olmasını kontrol eden boolean method
    public boolean sinirIcerisinde(){
       if(this.yon == 0){
            return  0 <= baslangicX && baslangicX + this.size < 10 && 0 <= baslangicY && baslangicY < 10;  
       }
       else{
           return  0 <= baslangicX && baslangicX < 10 && 0 <= baslangicY && baslangicY + this.size < 10;  
       }
    }
    
  
    public int getYon() {
        return yon;
    }

    public void setYon(int yon) {
        this.yon = yon;
    }

    public int getBaslangicX() {
        return baslangicX;
    }

    public void setBaslangicX(int baslangicX) {
        this.baslangicX = baslangicX;
    }

    public int getBaslangicY() {
        return baslangicY;
    }

    public void setBaslangicY(int baslangicY) {
        this.baslangicY = baslangicY;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Gemi{" + "yon=" + yon + ", baslangicX=" + baslangicX + ", baslangicY=" + baslangicY + ", size=" + size + '}';
    }
    
    
    
    
    
    
}
