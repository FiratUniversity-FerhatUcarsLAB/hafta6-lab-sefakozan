/**
 * Ad Soyad: [Muhammet Sefa KOZAN]
 * Numara: [250541057]
 * Proje: [Sinema Bilet Sistemi]
 * Tarih: [14.11.2025 03:56:00]
 *
 * Bu program sinema bilet fiyatlarini hesaplar.
 * Gun, saat, yas, meslek ve film turune gore temel fiyat, indirim ve ekstra ucretleri belirler.
 */

import java.util.Scanner;

public class Proje2_SinemaBileti {
    private final Scanner scanner;

    public Proje2_SinemaBileti() {
        this.scanner = new Scanner(System.in);
    }

    static void main(String[] args) {
        Proje2_SinemaBileti manager = new Proje2_SinemaBileti();
        manager.generateTicketInfo();
        manager.processTicketPurchase();
    }

    // Fiyat ve indirim bilgilerini goster
    public void generateTicketInfo(){
        System.out.println("=== Temel Fiyatlar ===");
        System.out.println("Hafta ici matine  (12:00 oncesi)  : 45 TL");
        System.out.println("Hafta ici normal                  : 65 TL");
        System.out.println("Hafta sonu matine (12:00 oncesi)  : 55 TL");
        System.out.println("Hafta sonu normal                 : 85 TL");

        System.out.println("\n=== Indirimler ===");
        System.out.println("Ogrenci       : %20 (Pazartesi-Persembe), %15 (Cuma-Pazar)");
        System.out.println("65+ yas       : %30 (her gun)");
        System.out.println("12 yas alti   : %25 (her gun)");
        System.out.println("Ogretmen      : %35 (sadece Carsamba)");

        System.out.println("\n=== Film Turu Ekstra ===");
        System.out.println("1. 3D film    : +25 TL");
        System.out.println("2. IMAX       : +35 TL");
        System.out.println("3. 4DX        : +50 TL");
    }

    // Kullanicidan girisleri al ve bilet fiyatini hesapla
    public void processTicketPurchase(){
        System.out.println("\n\n======== Bilet Tercihleriniz =======");
        System.out.print("Gun (1-7)           : ");
        int day = scanner.nextInt();

        System.out.print("Saat (8.00-23.00)   : ");
        double hour = scanner.nextDouble();

        System.out.print("Yas                 : ");
        int age = scanner.nextInt();

        System.out.print("Meslek (1-3)        : ");
        int occupation = scanner.nextInt();

        System.out.print("Film Turu (1-4)     : ");
        int filmType = scanner.nextInt();

        // Fiyat hesaplamalari
        double basePrice = calculateBasePrice(day, hour);
        double discount = calculateDiscount(age, occupation, day);
        double formatExtra = getFormatExtra(filmType);
        double finalPrice = calculateFinalPrice(basePrice, discount, formatExtra);

        // Detayli hesaplama ekrani
        System.out.println("\n============ Hesaplama ============ ");
        System.out.printf(" Temel fiyat          : %6.1f TL\n", basePrice);

        // Indirim turunu goster
        double discountAmount = basePrice * discount;
        if (discountAmount > 0) {
            String discountName = getDiscountName(occupation, age, day);
            System.out.printf(" %-20s : %6.1f TL\n", discountName, -discountAmount);
        }

        // Format ekstra ucretini goster
        if (formatExtra > 0) {
            String formatName = getFormatName(filmType);
            System.out.printf(" %-20s : %+6.1f TL\n", formatName, formatExtra);
        }

        // Toplam fiyat
        System.out.println("------------------------------------");
        System.out.printf(" TOPLAM FIYAT         : %6.1f TL", finalPrice);

        scanner.close();
    }

    // Indirim adini getir
    private String getDiscountName(int occupation, int age, int day) {
        if (occupation == 1) return "Ogrenci indirimi";
        if (occupation == 2 && day == 3) return "Ogretmen indirimi";
        if (age > 65) return "65+ indirimi";
        if (age < 12) return "12 yas alti indirimi";
        return "";
    }

    // Format adini getir
    private String getFormatName(int filmType) {
        switch (filmType) {
            case 2: return "3D ekstra";
            case 3: return "IMAX ekstra";
            case 4: return "4DX ekstra";
            default: return "";
        }
    }

    // Hafta sonu kontrolu
    public boolean isWeekend(int day){
        return day == 6 || day == 7;
    }

    // Matine seans kontrolu
    public boolean isMatinee(double hour){
        return hour >= 8 && hour < 12;
    }

    // Temel fiyat hesapla
    public int calculateBasePrice(int day, double hour){
        boolean matine = isMatinee(hour);
        boolean weekend = isWeekend(day);

        if (weekend) return matine ? 55 : 85;
        return matine ? 45 : 60;
    }

    // Indirim oranini hesapla
    public double calculateDiscount(int age, int occupation, int day){
        switch (occupation){
            case 1: // Ogrenci
                return isWeekend(day) ? 0.15 : 0.20;
            case 2: // Ogretmen
                return day == 3 ? 0.35 : 0;
            case 3: // Diger
                if (age > 65) return 0.30;
                if (age < 12) return 0.25;
                return 0;
            default:
                return 0;
        }
    }

    // Film format ekstra ucretini getir
    public double getFormatExtra(int filmType){
        switch (filmType){
            case 2: return 25;  // 3D
            case 3: return 35;  // IMAX
            case 4: return 50;  // 4DX
            default: return 0;  // 2D
        }
    }

    // Toplam fiyat hesapla
    public double calculateFinalPrice(double basePrice, double discount, double formatExtra){
        return basePrice - (basePrice * discount) + formatExtra;
    }
}