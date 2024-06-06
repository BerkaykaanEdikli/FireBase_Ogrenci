package com.example.firebase_ogrenci;

public class Bilgi {
    String Id,projeAdi,projeAlani,sonTeslimTarihi;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getProjeAdi() {
        return projeAdi;
    }

    public void setProjeAdi(String projeAdi) {
        this.projeAdi = projeAdi;
    }

    public String getProjeAlani() {
        return projeAlani;
    }

    public void setProjeAlani(String projeAlani) {
        this.projeAlani = projeAlani;
    }

    public String getSonTeslimTarihi() {
        return sonTeslimTarihi;
    }

    public void setSonTeslimTarihi(String sonTeslimTarihi) {
        this.sonTeslimTarihi = sonTeslimTarihi;
    }
}
