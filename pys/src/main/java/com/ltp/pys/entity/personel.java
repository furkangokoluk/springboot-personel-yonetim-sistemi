package com.ltp.pys.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity

@Table(name = "personel")
@Data
public class personel {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String ad;
    String soyad;
    String tel_no;
    String dogum_tarihi;
    String unvan;
    String maas;
}
