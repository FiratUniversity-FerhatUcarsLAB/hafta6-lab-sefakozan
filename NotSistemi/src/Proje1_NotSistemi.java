/**
 * Ad Soyad: [Muhammet Sefa KOZAN]
 * Numara: [250541057]
 * Proje: [Ogrenci Not Sistemi]
 * Tarih: [14.11.2025 03:56:00]
 *
 * Bu program vize, final ve odev notlarina gore ogrenci notlarini hesaplar.
 * Harf notu, gecme/kalma durumu, onur listesi uygunlugu ve butunleme hakkini belirler.
 */

import java.util.Scanner;

public class Proje1_NotSistemi {
    private final Scanner scanner;
    final double PASSING_GRADE = 50.0;
    final double HONOR_THRESHOLD = 85.0;

    public Proje1_NotSistemi() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * STATIC vs NON-STATIC KAVRAMI
     * ---------------------------------
     * STATIC (Sinif Seviyesi):
     * - Nesne olusturmadan dogrudan sinif ismiyle cagrilabilir
     * - Ornek: Math.sqrt(16) - Math sinifindan nesne olusturmadik
     * - Bellekte bir kez olusturulur, tum nesneler tarafindan paylasilir
     *
     * NON-STATIC (Nesne Seviyesi):
     * - Once nesne olusturulmali, sonra o nesne uzerinden cagrilir
     * - Ornek: scanner.nextInt() - scanner nesnesi uzerinden cagriliyor
     * - Her nesne kendi kopyasina sahiptir
     *
     * NEDEN NESNE OLUSTURDUK?
     * ---------------------------------
     * Problem:
     * - main() metodu STATIC
     * - startNotSistemi() metodu NON-STATIC
     * - scanner degiskeni de NON-STATIC
     * - Static bir metod (main), non-static uyelere (scanner, startNotSistemi) dogrudan erisamez!
     *
     * Cozum:
     * - main icinde bir NESNE (manager) olusturduk
     * - manager sayesinde non-static metodlara ve degiskenlere eristik
     *
     * MAIN METODU NEDEN HER ZAMAN STATIC?
     * ---------------------------------
     * Zorunluluk: main MUTLAKA "public static void main(String[] args)" olmali
     *
     * Sebep:
     * 1. JVM (Java Sanal Makinesi) programi baslatirken once bir nesne aramaz
     * 2. Direkt olarak "ClassName.main()" seklinde static main'i cagirir
     * 3. Eger main static olmasaydi:
     *    - JVM once nesne olusturmak zorunda kalirdi
     *    - Ama hangi siniftan? Ne parametrelerle? -> Belirsizlik olsuyor
     * 4. Static oldugu icin JVM nesne olusturmadan calistirabilir
     *
     * Bu bir Java dil kuralidir, degistirilemez!
     */
    public static void main(String[] args) {
        // Instance oluşturmadan once:
        // startNotSistemi() metodunu cagiramayiz (non-static)
        // scanner'a erisemeyiz (non-static)

        // Instance olusturulduktan sonra:
        // Proje1_NotSistemi sinifindan bir nesne (instance) olustur
        // manager artik scanner'a ve startNotSistemi() metoduna erisebilir
        Proje1_NotSistemi manager = new Proje1_NotSistemi();
        
        // manager nesnesi uzerinden non-static metodu cagir
        // Bu metod icinde scanner kullanilabilir cunku artik bir nesne baglamindayiz
        manager.startNotSistemi();
    }

    /**
     * Not sistemini baslatan ana metod
     * Ogrenci notlarini alir, hesaplar ve akademik durum bilgilerini yazar
     */
    public void startNotSistemi(){
        // Kullanicidan alinacak akademik notlar
        System.out.print("Vize  : ");
        int midtermScore = this.scanner.nextInt();
        System.out.print("Final : ");
        int finalScore = this.scanner.nextInt();
        System.out.print("Odev  : ");
        int homeworkScore = this.scanner.nextInt();

        // Agirlikli ortalama ve akademik durum bilgilerini cagirilir
        double average = calculateAverage(midtermScore, finalScore, homeworkScore);
        char letterGrade = getLetterGrade(average);
        String passStatus = isPassingGrade(average);
        String honorListStatus = isHonorList(average, midtermScore, finalScore, homeworkScore);
        String retakeStatus = hasRetakeRight(average);

        // Ogrenci not raporunu goruntule
        System.out.print("\n===== OGRENCI NOT RAPORU =====\n");
        System.out.printf("Vize Notu     : %d\n", midtermScore);
        System.out.printf("Final Notu    : %d\n", finalScore);
        System.out.printf("Odev Notu     : %d\n", homeworkScore);
        System.out.println("-".repeat(31));
        System.out.printf("Ortalama      : %.1f\n", average);
        System.out.printf("Harf Notu     : %c\n", letterGrade);
        System.out.printf("Durum         : %s\n", passStatus);
        System.out.printf("Onur Listesi  : %s\n", honorListStatus);
        System.out.printf("Butunleme     : %s\n", retakeStatus);

        scanner.close();
    }

    // Ogrenci notlarinin agirlikli ortalamasini hesaplar
    // Agirlikli ortalama (Vize %30, Final %40, Odev %30)
    public double calculateAverage(int midtermScore, int finalScore, int homeworkScore){
        return (midtermScore * 0.3) + (finalScore * 0.4) + (homeworkScore * 0.3);
    }

    // Agirlikli ortalama notuna gore harf notu belirler
    public char getLetterGrade(double average){
        if (average >= 90) return 'A';
        if (average >= 80) return 'B';
        if (average >= 70) return 'C';
        if (average >= 60) return 'D';
        return 'F';
    }

    // Agirlikli ortalama notuna gore ogrencinin gecip gecmedigini belirler
    public String isPassingGrade(double average){
        return (average >= PASSING_GRADE) ? "GECTI" : "KALDI";
    }

    // Onur listesi uygunlugunu kontrol eder
    // Ogrenci ortalama >= 85 ve tum notlar >= 70 olmali
    public String isHonorList(double average, int midtermScore, int finalScore, int homeworkScore){
        return (average >= HONOR_THRESHOLD && midtermScore >= 70 && finalScore >= 70 && homeworkScore >= 70) ? "EVET" : "HAYIR";
    }

    // Butunleme sinavi hakki olup olmadigini belirler
    // Agirlikli ortalama 40 ile 50 arasinda (dahil) ise ogrenci butunlemeye girebilir
    public String hasRetakeRight(double average){
        return (average >= 40 && average <= 50) ? "VAR" : "YOK";
    }
}