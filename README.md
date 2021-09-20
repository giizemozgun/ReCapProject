# ReCapProject

Bu proje Çok Katmanlı Mimari temel alınarak geliştirilmiştir. Projede SOLID yazılım ilkeleri benimsenmiştir.Java ile kodlanmıştır. Veritabanı için PostgreSQL kullanılmaktadır.
Ayrıca, projede Front-End tarafı ve diğer uygulamalar ile iletişim kurmak için bir Web API kodlanmıştır. Sizlere projenin ana özelliklerinden bahsetmek istiyorum.

## Back-End
### Katmanlar
#### 1-Entities Layer: 
Program boyunca kullanılacak nesnelerin sınıflarının tanımlandığı yerdir. Bu katmandaki sınıfların her biri veritabanında bir tabloya karşılık gelir. 
Farklı tablolardan gelen verilerin birleştirildiği DTO (Data Transfer Object) ve bir HTTP isteği anında verileri sunucuya ileten Requests sınıflarını da içerir.
#### 2-Data Access Layer: 
Veritabanı bağlantılarının ve işlemlerinin yapıldığı katmandır. Veritabanı bağlantısı için gerekli yapılandırma burada yapılır.Jpa Repository kullanılmıştır.
Ayrıca veri çıkarma, ekleme, silme, güncelleme gibi işlemler de bu katmanda kodlanmıştır.
#### 3-Business Layer: 
İş kurallarının tanımlandığı ve kontrol edildiği katmandır. Programa bir komut geldiğinde hangi işlemleri yapması ve hangi kurallar dizisinden geçmesi 
gerektiği burada tanımlanır.
#### 4-Core Layer: 
Tüm katmanlarda ortak kullanılacak yapıların ve evrensel operasyonların kodlandığı kısımdır.
#### 5-Servis Katmanı (API): 
Front-End kısmı ve diğer platformların program ile haberleşip işlem yapmasını sağlayan servislerin yazıldığı kısımdır.

