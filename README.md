## Manuel eklentiler

Mysql bağlatı bilgilerinin tutulduğu application.properties dosyasının içeriği şu şekilde olmalıdır :

```
  # Veritabanı bağlantı bilgileri
  spring.datasource.url=your_jdbc_url
  spring.datasource.username=your_username
  spring.datasource.password=your_password
  
  # Hibernate konfigürasyonu
  spring.jpa.hibernate.ddl-auto=update
  spring.jpa.show-sql=true
  spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
