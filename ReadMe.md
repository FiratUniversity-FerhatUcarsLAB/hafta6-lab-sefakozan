[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/3iWAPEJw)
[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=21642772&assignment_repo_type=AssignmentRepo)
# Algoritma ve Programlama I - Laboratuvar Çalışmaları - 6
## Seçim Yapıları (if-else, switch-case)

**Doç. Dr. Ferhat Uçar**  
**TA: Arş. Grv. Ömer Miraç Kökçam**  
Teknoloji Fakültesi - Yazılım Mühendisliği

---

## 📋 Genel Bilgiler

- **Konu:** Boolean, if-else ve switch-case yapıları
- **Toplam Puan:** 100
- **Proje Sayısı:** 3
- **Tahmini Süre:** 4-6 saat
- **Teslim:** GitHub Classroom üzerinden

---

## 🎯 Öğrenme Hedefleri

Bu laboratuvar çalışmasında:
- Boolean değişkenler ve mantıksal operatörlerle çalışmayı
- if-else ve iç içe if yapılarını kullanmayı
- switch-case yapılarıyla çoklu seçim yapmayı
- Metot tanımlama ve kullanma pratiği yapmayı
- Gerçek hayat problemlerini kodla çözmeyi öğreneceksiniz

---

## 📁 Proje Dosyaları

Repository'nizde şu dosyalar bulunmalıdır:

```
.
├── README.md
├── Proje1_NotSistemi.java
├── Proje2_SinemaBileti.java
└── Proje3_RestoranSiparis.java
```

---

## 🚀 Projeler

### Proje 1: Öğrenci Not Değerlendirme Sistemi (30 puan)

**Amaç:** Öğrenci notlarını değerlendiren ve detaylı rapor veren program

**Gereksinimler:**
- 3 sınav notu + 1 ödev notu alma
- Ortalama hesaplama: Vize %30 + Final %40 + Ödev %30
- Geçme/kalma kontrolü (≥50 geçti)
- Harf notu belirleme (A-F arası)
- Onur listesi kontrolü (ort ≥85 VE tüm notlar ≥70)
- Bütünleme hakkı kontrolü (40 ≤ ort < 50)

**Zorunlu Metotlar (5 adet):**
1. `calculateAverage(vize, final, odev)` → double
2. `isPassingGrade(ortalama)` → boolean
3. `getLetterGrade(ortalama)` → char
4. `isHonorList(ortalama, vize, final, odev)` → boolean
5. `hasRetakeRight(ortalama)` → boolean

---

### Proje 2: Sinema Bileti Fiyatlandırma Sistemi (35 puan)

**Amaç:** Gün, saat, yaş, meslek ve film türüne göre dinamik bilet fiyatı hesaplama

**Temel Fiyatlar:**
- Hafta içi matine (12:00 öncesi): 45 TL
- Hafta içi normal: 65 TL
- Hafta sonu matine: 55 TL
- Hafta sonu normal: 85 TL

**İndirimler:**
- Öğrenci: %20 (Pzt-Prş), %15 (Cum-Paz)
- 65+ yaş: %30
- 12 yaş altı: %25
- Öğretmen: %35 (sadece Çarşamba)

**Film Türü Ekstraları:**
- 3D: +25 TL
- IMAX: +35 TL
- 4DX: +50 TL

**Zorunlu Metotlar (7 adet):**
1. `isWeekend(gun)` → boolean
2. `isMatinee(saat)` → boolean
3. `calculateBasePrice(gun, saat)` → double
4. `calculateDiscount(yas, meslek, gun)` → double
5. `getFormatExtra(filmTuru)` → double
6. `calculateFinalPrice(...)` → double
7. `generateTicketInfo(...)` → void

**Switch-case kullanımı zorunlu:**
- Gün seçimi (1-7)
- Meslek seçimi (1=Öğrenci, 2=Öğretmen, 3=Diğer)
- Film türü (1=2D, 2=3D, 3=IMAX, 4=4DX)

---

### Proje 3: Akıllı Restoran Sipariş Sistemi (35 puan)

**Amaç:** Menü kategorileri ve özel indirimleri yöneten sipariş sistemi

**Menü Kategorileri:**
1. **Ana Yemekler:** Izgara Tavuk (85₺), Adana Kebap (120₺), Levrek (110₺), Mantı (65₺)
2. **Başlangıçlar:** Çorba (25₺), Humus (45₺), Sigara Böreği (55₺)
3. **İçecekler:** Kola (15₺), Ayran (12₺), Meyve Suyu (35₺), Limonata (25₺)
4. **Tatlılar:** Künefe (65₺), Baklava (55₺), Sütlaç (35₺)

**Özel Durumlar:**
- Combo menü (Ana + İçecek + Tatlı): %15 indirim
- 200 TL üzeri: %10 indirim
- Happy Hour (14:00-17:00): İçeceklerde %20 indirim
- Öğrenci (hafta içi): %10 ekstra indirim
- Bahşiş önerisi: %10

**Zorunlu Metotlar (8 adet):**
1. `getMainDishPrice(secim)` → double
2. `getAppetizerPrice(secim)` → double
3. `getDrinkPrice(secim)` → double
4. `getDessertPrice(secim)` → double
5. `isComboOrder(anaVar, icecekVar, tatliVar)` → boolean
6. `isHappyHour(saat)` → boolean
7. `calculateDiscount(tutar, combo, ogrenci, saat)` → double
8. `calculateServiceTip(tutar)` → double

**Her kategori için switch-case kullanımı zorunlu!**

---

## ⚙️ Teknik Gereksinimler

### ✅ Kullanılacak Kavramlar
- Boolean değişkenler ve ifadeler
- İlişkisel operatörler (<, >, ==, !=, <=, >=)
- Mantıksal operatörler (&&, ||, !)
- if, if-else, if-else-if yapıları
- İç içe if yapıları
- switch-case yapıları
- Metot tanımlama ve kullanma
- Scanner ile girdi alma
- printf ile formatli çıktı

### ❌ Kullanılmayacak
- Döngüler (for, while, do-while)
- Diziler (arrays)

---

## 📝 Kimlik Bilgileri

**Her dosyanın başına şu bilgileri ekleyin:**

```java
/**
 * Ad Soyad: [Adınız Soyadınız]
 * Öğrenci No: [Numaranız]
 * Proje: [Proje Adı]
 * Tarih: [Teslim Tarihi]
 */
```

---

## 🎯 Değerlendirme Kriterleri

| Kriter | Puan |
|--------|------|
| Metot Tanımları | 45 |
| if-else Kullanımı | 20 |
| switch-case Kullanımı | 15 |
| Boolean ve Mantıksal Op. | 10 |
| Çıktı Formatı | 5 |
| Kimlik Bilgileri | 5 |
| **TOPLAM** | **100** |

**Proje Dağılımı:**
- Proje 1: 30 puan
- Proje 2: 35 puan
- Proje 3: 35 puan

---

## 📚 Test Senaryoları

### Proje 1 Test Örnekleri:
```
Test 1: Vize=85, Final=90, Ödev=88
Beklenen: Ort=87.9, B, Geçti, Onur Listesi=EVET

Test 2: Vize=40, Final=45, Ödev=48
Beklenen: Ort=44.7, F, Bütünleme Hakkı=EVET

Test 3: Vize=90, Final=92, Ödev=65
Beklenen: Ort=85.1, B, Onur Listesi=HAYIR (bir not <70)
```

### Proje 2 Test Örnekleri:
```
Test 1: Perşembe(4), Saat=10, Yaş=22, Öğrenci, 3D
Beklenen: Temel=45₺, İnd=%20→36₺, 3D+25₺ = 61₺

Test 2: Çarşamba(3), Saat=20, Yaş=35, Öğretmen, IMAX
Beklenen: Temel=65₺, İnd=%35→42.25₺, IMAX+35₺ = 77.25₺
```

### Proje 3 Test Örnekleri:
```
Test 1: Adana(120₺)+Humus(45₺)+Meyve Suyu(35₺)+Künefe(65₺)
        Saat=15, Öğrenci, Çarşamba
        Ara toplam: 265₺
        Combo -15%: -39.75₺
        Happy Hour (içecek) -20%: -7₺
        Öğrenci -10%: -21.83₺
        Toplam: 196.42₺
        Bahşiş önerisi: 19.64₺
```

---

## 💡 Başarı İçin İpuçları

1. **Kağıda çizin:** Karar ağaçlarını kodlamadan önce çizin
2. **Adım adım ilerleyin:** Bir metot yaz → test et → geç
3. **Boolean değişkenler kullanın:** Karmaşık koşulları bölün
   ```java
   boolean isWeekend = (day == 6 || day == 7);
   boolean isStudent = (occupation == 1);
   if (isWeekend && isStudent) { ... }
   ```
4. **Switch'te break unutmayın!**
5. **Sınır değerleri test edin:** 0, negatif, maksimum değerler
6. **printf kullanın:** `printf("%.2f", sayi)` ile düzgün format

---

## ⚠️ Yaygın Hatalar

### 1. Noktalı Virgül Hatası
```java
// YANLIŞ!
if (grade >= 50); 
{
    System.out.println("Geçti");
}
```

### 2. Eşitlik Operatörü
```java
if (day = 1)   // YANLIŞ! Atama operatörü
if (day == 1)  // DOĞRU! Karşılaştırma
```

### 3. Switch'te break Unutma
```java
switch(day) {
    case 1: 
        price = 45;
        // break UNUTULDU!
    case 2:
        price = 65;
        break;
}
```

### 4. Short-Circuit Unutma
```java
// YANLIŞ - number 0 olursa hata!
if (sum / number > 5 && number != 0)

// DOĞRU - önce 0 kontrolü
if (number != 0 && sum / number > 5)
```

---

## 📤 Teslim Süreci

1. Repository'yi klonlayın
2. Her dosyaya kimlik bilgilerinizi ekleyin
3. Kodları yazın ve test edin
4. Commit yapın:
   ```bash
   git add .
   git commit -m "Lab odevi tamamlandi"
   git push origin main
   ```

---

## 🆘 Yardım ve Kaynaklar

### İletişim
- **Office Hours:** Cuma 10:00-12:00
- **E-posta:** fucar@firat.edu.tr
- **GitHub Discussions:** Sorularınız için

### Ders Materyalleri
- Week 7 Sunum (if-else, switch-case)
- Think Java - Chapter 3
- GitHub Repo - Örnek kodlar

### Faydalı Araçlar
- Java Visualizer - Kod akışını görselleştir
- IntelliJ IDEA Debugger - Adım adım çalıştır

---

## 📌 Son Notlar

- **Her hesaplama için ayrı metot yazın** - Bu zorunludur!
- **Main metodunda minimum hesaplama** - Sadece koordinasyon
- **Kod temizliği önemli** - Yorumlar ve girintiler
- **Test, test, test!** - Her metodu ayrı ayrı test edin

---

> **"Her if, her else, sizin kararlarınız!"**  
> **"Algoritma kurmak, problem çözmek demektir."**

**Başarılar! 🚀**
