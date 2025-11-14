/**
 * Ad Soyad: [Muhammet Sefa KOZAN]
 * Numara: [250541057]
 * Proje: [Restoran Siparis Sistemi]
 * Tarih: [14.11.2025]
 *
 * Bu program restoran siparisi icin fiyat hesaplar.
 * Combo indirimi, ogrenci indirimi, happy hour ve servis ucreti hesaplar.
 *
 * Kampanyalar:
 * - Combo indirimi: Ana yemek + icecek + tatli alinirsa %15
 * - Happy hour: 14:00-17:00 arasi icecekler %20 indirimli
 * - Ogrenci indirimi: Hafta ici ogrencilere %10 indirim
 * - Servis ucret onerisi: Indirimli tutar uzerinden %10
 */

import java.util.Scanner;

public class Proje3_RestoranSiparis {
    private final Scanner scanner;

    // Constructor - Scanner nesnesini olustur
    public Proje3_RestoranSiparis() {
        this.scanner = new Scanner(System.in);
    }

    static void main(String[] args) {
        // Yeni bir restoran siparis yoneticisi olustur
        Proje3_RestoranSiparis manager = new Proje3_RestoranSiparis();

        manager.printMenu();
        // Siparis islemini baslat ve hesaplamalari yap
        manager.processingOrder();
    }

    // Menu bilgilerini ekrana yazdir
    public void printMenu(){
        System.out.println("============== MENU ==============");
        System.out.println("  Ana Yemekler");
        System.out.println("1. Izgara Tavuk    -  85 TL");
        System.out.println("2. Adana Kebap     - 120 TL");
        System.out.println("3. Levrek          - 110 TL");
        System.out.println("4. Mantı           -  65 TL");
        System.out.println("\n  Baslangiclar");
        System.out.println("1. Corba           -  25 TL");
        System.out.println("2. Humus           -  45 TL");
        System.out.println("3. Sigara Boregi   -  55 TL");
        System.out.println("\n  İcecekler");
        System.out.println("1. Kola            -  15 TL");
        System.out.println("2. Ayran           -  12 TL");
        System.out.println("3. Taze Meyve Suyu -  35 TL");
        System.out.println("4. Limonata        -  25 TL");
        System.out.println("\n  Tatlilar");
        System.out.println("1. Kunefe          -  65 TL");
        System.out.println("2. Baklava         -  55 TL");
        System.out.println("3. Sutlac          -  35 TL");
    }

    // Bu metod kullanicidan tum bilgileri alir ve fiyat hesaplamalarini yapar
    public void processingOrder(){
        // === KULLANICI GIRISLERINI AL ===
        System.out.println("\n\n========== Tercihleriniz =========");

        System.out.print("Ana Yemek (1-4)    : ");
        int mainDishNum = scanner.nextInt();

        System.out.print("Baslangic (0-3)    : ");
        int appetizerNum = scanner.nextInt();

        System.out.print("Icecek (0-4)       : ");
        int drinkNum = scanner.nextInt();

        System.out.print("Tatli (0-4)        : ");
        int dessertNum = scanner.nextInt();

        // Siparis saati (happy hour kontrolu icin gerekli)
        System.out.print("Saat (8.00-23.00)  : ");
        double hour = scanner.nextDouble();

        // Ogrenci durumu (E/Evet veya H/Hayir)
        System.out.print("Ogrenci misiniz?   : ");
        String isStudent = scanner.next();

        System.out.print("Hangi gun? (1-7)   : ");
        int day = scanner.nextInt();

        // === FIYAT HESAPLAMALARI ===

        // Her kategoriden secilen urunlerin fiyati alinir
        int mainDishPrice = getMainDishPrice(mainDishNum);
        int appetizerPrice = getAppetizerPrice(appetizerNum);
        int drinkPrice = getDrinkPrice(drinkNum);
        int dessertPrice = getDessertPrice(dessertNum);

        // araToplam: Tum urunlerin toplam fiyati
        int subtotal = mainDishPrice + appetizerPrice + drinkPrice + dessertPrice;

        // Combo kontrol edilir ve indirim orani cekilir (ana yemek + icecek + tatli varsa %15)
        double comboDisRate = isComboOrder(mainDishNum != 0, drinkNum != 0, dessertNum != 0);

        // Ogrenci durumunu boolean'a cevir (E veya Evet ise true)
        boolean isStudentBool = isStudent.equalsIgnoreCase("E") || isStudent.equalsIgnoreCase("Evet");

        // Happy hour durumu kontrol edilir ve indirim orani cekilir (14:00-17:00 arasi ise %20)
        double happyHourRate = isHappyHour(hour);

        // === INDIRIM HESAPLAMALARI ===

        // toplamIndirim =  combo + happy hour + ogrenci
        double totalDiscount = calculateDiscount(subtotal, comboDisRate, isStudentBool, happyHourRate, drinkPrice, day);

        // indirimliTutar =  araToplam - toplamIndirim
        double afterDiscount = subtotal - totalDiscount;

        // Servis ucreti (%10 bahsis onerisi)
        double serviceTip = calculateServiceTip(afterDiscount);

        // === FATURA EKRANI ===

        System.out.println("\n=========== Hesaplama ============");
        System.out.printf(" Ara Toplam           : %7.2f TL\n", (double)subtotal);

        // indirim turlerinin ayri ayri toplam degerleri hesaplanir
        double comboDiscount = (comboDisRate > 0) ? subtotal * comboDisRate : 0;
        double happyHourDiscount = (happyHourRate > 0 && drinkNum != 0) ? drinkPrice * happyHourRate : 0;
        // Ogrenci indirimi onceki indirimlerden sonra kalan tutar uzerinden uygulanir
        double studentDiscount = (isStudentBool && day >= 1 && day <= 5) ?
            (subtotal - comboDiscount - happyHourDiscount) * 0.10 : 0;

        // Combo indirimi varsa ekrana yaz
        if (comboDiscount > 0) {
            System.out.printf(" Combo indirimi       : %%%.0f -> %7.2f TL\n", comboDisRate * 100, -comboDiscount);
        }

        // Happy hour indirimi varsa ekrana yaz
        if (happyHourDiscount > 0) {
            System.out.printf(" Happy hour (icecek)  : %%%.0f -> %7.2f TL\n", happyHourRate * 100, -happyHourDiscount);
        }

        // Ogrenci indirimi varsa ekrana yaz
        if (studentDiscount > 0) {
            System.out.printf(" Ogrenci indirimi     : %%10 -> %7.2f TL\n", -studentDiscount);
        }

        // Toplam tutar (indirimli veya indirimsiz)
        System.out.println("                        -----------");
        System.out.printf(" Toplam               : %7.2f TL\n", afterDiscount);

        // Bahsis onerisi (indirimli tutar uzerinden %10)
        System.out.printf(" Bahsis onerisi       : %7.2f TL (%%10)\n", serviceTip);

        scanner.close();
    }

    public int getMainDishPrice(int choice){
        switch (choice){
            case 1: return 85;
            case 2: return 120;
            case 3: return 110;
            case 4: return 65;
            default: return 0;
        }
    }

    public int getAppetizerPrice(int choice){
        switch (choice){
            case 1: return 25;
            case 2: return 45;
            case 3: return 55;
            default: return 0;
        }
    }

    public int getDrinkPrice(int choice){
        switch (choice){
            case 1: return 15;
            case 2: return 12;
            case 3: return 35;
            case 4: return 25;
            default: return 0;
        }
    }

    public int getDessertPrice(int choice){
        switch (choice){
            case 1: return 65;
            case 2: return 55;
            case 3: return 35;
            default: return 0;
        }
    }

    // Combo siparis kontrolu - ana yemek, icecek ve tatli hepsi varsa %15 indirim
    // @param hasMain: Ana yemek secildi mi
    // @param hasDrink: Icecek secildi mi
    // @param hasDessert: Tatli secildi mi
    // @return: Ucunu de iceriyorsa 0.15, degilse 0
    public double isComboOrder(boolean hasMain, boolean hasDrink, boolean hasDessert){
        return (hasMain && hasDrink && hasDessert) ? 0.15 : 0;
    }

    // Happy hour kontrolu - 14.00-17.00 arasi iceceklerde %20 indirim
    // @param hour: Siparis saati
    // @return: Happy hour ise 0.20, degilse 0
    public double isHappyHour(double hour){
        return (hour >= 14 && hour < 17) ? 0.20 : 0;
    }

    // Tum indirimleri hesaplanip dondurulur
    // Indirimler sirayla uygulanir: 1. Combo 2. Happy Hour 3. Ogrenci
    // @param subtotal: Ara toplam tutar
    // @param comboDisRate: Combo indirim orani (0 veya 0.15)
    // @param isStudent: Ogrenci mi
    // @param happyHourRate: Happy hour indirim orani (0 veya 0.20) (sadece icecek icin gecerli)
    // @param drinkPrice: Icecek fiyati
    // @param day: Hangi gun (1-7, ogrenci indirimi sadece hafta ici)
    // @return: Toplam indirim tutari
    public double calculateDiscount(double subtotal, double comboDisRate, boolean isStudent,
                                    double happyHourRate, int drinkPrice, int day){
        double totalDiscount = 0;

        // 1. Combo indirimi - tum siparis tutari uzerinden %15
        if (comboDisRate > 0) {
            totalDiscount = subtotal * comboDisRate;
        }

        // 2. Happy hour indirimi - sadece icecek fiyati uzerinden %20
        if (happyHourRate > 0 && drinkPrice > 0) {
            totalDiscount += drinkPrice * happyHourRate;
        }

        // 3. Ogrenci indirimi - onceki indirimlerden sonra kalan tutar uzerinden %10
        // Sadece hafta ici (gun 1-5 arasi) gecerli
        if (isStudent && day >= 1 && day <= 5) {
            double afterDiscounts = subtotal - totalDiscount;
            totalDiscount += afterDiscounts * 0.10;
        }

        return totalDiscount;
    }

    // Servis ucreti (bahsis) hesaplanir - indirimli tutar uzerinden %10
    // @param subtotal: Indirimli toplam tutar
    // @return: Bahsis tutari
    public double calculateServiceTip(double subtotal){
        return subtotal * 0.10;
    }
}