package com.ltp.pys.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personel")
@Data
public class personel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;

    @NotBlank(message = "Ad boş olamaz")
    @Size(min = 2, max = 30, message = "Ad en az 2, en fazla 50 karakter olmalıdır")
    String ad;

    @NotBlank(message = "Soyad boş olamaz")
    @Size(min = 2, max = 30, message = "Soyad en az 2, en fazla 50 karakter olmalıdır")
    String soyad;

    @NotBlank(message = "Telefon numarası boş olamaz")
    @Size(min = 11, max = 11, message = "Telefon numarası 11 karakter olmalıdır")
    String tel_no;

    @NotBlank(message = "Doğum tarihi boş olamaz")
    String dogum_tarihi;

    @NotBlank(message = "Ünvan boş olamaz")
    @Size(min = 2, max = 30, message = "Ünvan en az 2, en fazla 50 karakter olmalıdır")
    String unvan;

    @NotBlank(message = "Maaş boş olamaz")
    @Min(value = 1, message = "Maaş 0'dan büyük olmalıdır")
    String maas;
}
