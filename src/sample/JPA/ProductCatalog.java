package sample.JPA;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "product_catalog")
public class ProductCatalog {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementation")
    @GenericGenerator(name = "incrementation", strategy = "increment")
    private int id;
    @Column(name = "catalog_no")
    private String catalogNo;
    @Column(name = "symbol", length = 1024)
    private String symbol;
    @Column(name = "price_net")
    private double priceNet;
    @Column(name = "stock")
    private int stock;
    @Column(name = "group_id")
    private int groupId;
    //@ManyToOne(targetEntity = Categories.class, fetch = FetchType.EAGER, cascade={CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    //@JoinColumn(name = "group_id", referencedColumnName = "id")
    @Column(name = "aukstis")
    private double aukstis;
    @Column(name = "plotis")
    private double plotis;
    @Column(name = "gylis")
    private double gylis;
    @Column(name = "skersmuo")
    private double skersmuo;
    @Column(name = "ilgis")
    private double ilgis;
    @Column(name = "apsaugos_laipsnis")
    private String apsaugos_laipsnis;
    @Column(name = "moduliu_skaicius")
    private double moduliu_skaicius;
    @Column(name = "vardine_srove")
    private String vardine_srove;
    @Column(name = "vardine_itampa")
    private String vardine_itampa;
    @Column(name = "mechaninis_atsparumas_IK")
    private String mechaninis_atsparumas_IK;
    @Column(name = "spalva")
    private String spalva;
    @Column(name = "korpuso_medziaga")
    private String korpuso_medziaga;
    @Column(name = "izoliacija")
    private String izoliacija;
    @Column(name = "svoris")
    private double svoris;
    @Column(name = "galia")
    private String galia;
    @Column(name = "sviesos_srautas")
    private double sviesos_srautas;
    @Column(name = "sviesos_spalvos_temperatura")
    private String sviesos_spalvos_temperatura;
    @Column(name = "laidininkas")
    private String laidininkas;
    @Column(name = "darbine_temperatura")
    private String darbine_temperatura;
    @Column(name = "Max_darbine_temperatura")
    private String max_darbine_temperatura;
    @Column(name = "apvalkalas")
    private String apvalkalas;
    @Column(name = "CPR_klase")
    private String cpr_klase;
    @Column(name = "isjungimo_geba")
    private String isjungimo_geba;
    @Column(name = "isjungimo_charakteristika")
    private String isjungimo_charakteristika;
    @Column(name = "mechaninis_atsparumas")
    private String mechaninis_atsparumas;
    @Column(name = "skerspjuvis_Al")
    private String skerspjuvis_Al;
    @Column(name = "skerspjuvis_Cu")
    private String skerspjuvis_Cu;
    @Column(name = "nuotekio_srove")
    private String nuotekio_srove;
    @Column(name = "dydis")
    private String dydis;
    @Column(name = "plotas")
    private String plotas;
    @Column(name = "image_url", length = 1024)
    private String image_url;
    @Column(name = "date")
    private Date date;


    public ProductCatalog(int id, String catalogNo, String symbol, double priceNet, int stock, int groupId, Date date) {
        this.id = id;
        this.catalogNo = catalogNo;
        this.symbol = symbol;
        this.priceNet = priceNet;
        this.stock = stock;
        this.groupId = groupId;
        this.date = date;
    }

    public ProductCatalog(String catalogNo, String symbol, double priceNet, int stock, int groupId, double aukstis, double plotis, double gylis, double skersmuo, double ilgis, String apsaugos_laipsnis, double moduliu_skaicius, String vardine_srove, String vardine_itampa, String mechaninis_atsparumas_IK, String spalva, String korpuso_medziaga, String izoliacija, double svoris, String galia, double sviesos_srautas, String sviesos_spalvos_temperatura, String laidininkas, String darbine_temperatura, String max_darbine_temperatura, String apvalkalas, String cpr_klase, String isjungimo_geba, String isjungimo_charakteristika, String mechaninis_atsparumas, String skerspjuvis_Al, String skerspjuvis_Cu, String nuotekio_srove, String dydis, String plotas, String image_url, Date date) {
        this.catalogNo = catalogNo;
        this.symbol = symbol;
        this.priceNet = priceNet;
        this.stock = stock;
        this.groupId = groupId;
        this.aukstis = aukstis;
        this.plotis = plotis;
        this.gylis = gylis;
        this.skersmuo = skersmuo;
        this.ilgis = ilgis;
        this.apsaugos_laipsnis = apsaugos_laipsnis;
        this.moduliu_skaicius = moduliu_skaicius;
        this.vardine_srove = vardine_srove;
        this.vardine_itampa = vardine_itampa;
        this.mechaninis_atsparumas_IK = mechaninis_atsparumas_IK;
        this.spalva = spalva;
        this.korpuso_medziaga = korpuso_medziaga;
        this.izoliacija = izoliacija;
        this.svoris = svoris;
        this.galia = galia;
        this.sviesos_srautas = sviesos_srautas;
        this.sviesos_spalvos_temperatura = sviesos_spalvos_temperatura;
        this.laidininkas = laidininkas;
        this.darbine_temperatura = darbine_temperatura;
        this.max_darbine_temperatura = max_darbine_temperatura;
        this.apvalkalas = apvalkalas;
        this.cpr_klase = cpr_klase;
        this.isjungimo_geba = isjungimo_geba;
        this.isjungimo_charakteristika = isjungimo_charakteristika;
        this.mechaninis_atsparumas = mechaninis_atsparumas;
        this.skerspjuvis_Al = skerspjuvis_Al;
        this.skerspjuvis_Cu = skerspjuvis_Cu;
        this.nuotekio_srove = nuotekio_srove;
        this.dydis = dydis;
        this.plotas = plotas;
        this.image_url = image_url;
        this.date = date;
    }

    public ProductCatalog(String catalogNo, String symbol, double priceNet, int stock, int groupId, Date date) {
        this.catalogNo = catalogNo;
        this.symbol = symbol;
        this.priceNet = priceNet;
        this.stock = stock;
        this.groupId = groupId;
        this.date = date;
    }


    public ProductCatalog() {
    }

    public double getSkersmuo() {
        return skersmuo;
    }

    public void setSkersmuo(double skersmuo) {
        this.skersmuo = skersmuo;
    }

    public double getIlgis() {
        return ilgis;
    }

    public void setIlgis(double ilgis) {
        this.ilgis = ilgis;
    }

    public String getApsaugos_laipsnis() {
        return apsaugos_laipsnis;
    }

    public void setApsaugos_laipsnis(String apsaugos_laipsnis) {
        this.apsaugos_laipsnis = apsaugos_laipsnis;
    }

    public double getModuliu_skaicius() {
        return moduliu_skaicius;
    }

    public void setModuliu_skaicius(double moduliu_skaicius) {
        this.moduliu_skaicius = moduliu_skaicius;
    }

    public String getVardine_srove() {
        return vardine_srove;
    }

    public void setVardine_srove(String vardine_srove) {
        this.vardine_srove = vardine_srove;
    }

    public String getMechaninis_atsparumas_IK() {
        return mechaninis_atsparumas_IK;
    }

    public void setMechaninis_atsparumas_IK(String mechaninis_atsparumas_IK) {
        this.mechaninis_atsparumas_IK = mechaninis_atsparumas_IK;
    }

    public String getKorpuso_medziaga() {
        return korpuso_medziaga;
    }

    public void setKorpuso_medziaga(String korpuso_medziaga) {
        this.korpuso_medziaga = korpuso_medziaga;
    }

    public String getIzoliacija() {
        return izoliacija;
    }

    public void setIzoliacija(String izoliacija) {
        this.izoliacija = izoliacija;
    }

    public double getSvoris() {
        return svoris;
    }

    public void setSvoris(double svoris) {
        this.svoris = svoris;
    }

    public String getSviesos_spalvos_temperatura() {
        return sviesos_spalvos_temperatura;
    }

    public void setSviesos_spalvos_temperatura(String sviesos_spalvos_temperatura) {
        this.sviesos_spalvos_temperatura = sviesos_spalvos_temperatura;
    }

    public String getLaidininkas() {
        return laidininkas;
    }

    public void setLaidininkas(String laidininkas) {
        this.laidininkas = laidininkas;
    }


    public String getMax_darbine_temperatura() {
        return max_darbine_temperatura;
    }

    public void setMax_darbine_temperatura(String max_darbine_temperatura) {
        this.max_darbine_temperatura = max_darbine_temperatura;
    }

    public String getApvalkalas() {
        return apvalkalas;
    }

    public void setApvalkalas(String apvalkalas) {
        this.apvalkalas = apvalkalas;
    }

    public String getCpr_klase() {
        return cpr_klase;
    }

    public void setCpr_klase(String cpr_klase) {
        this.cpr_klase = cpr_klase;
    }

    public String getIsjungimo_geba() {
        return isjungimo_geba;
    }

    public void setIsjungimo_geba(String isjungimo_geba) {
        this.isjungimo_geba = isjungimo_geba;
    }

    public String getIsjungimo_charakteristika() {
        return isjungimo_charakteristika;
    }

    public void setIsjungimo_charakteristika(String isjungimo_charakteristika) {
        this.isjungimo_charakteristika = isjungimo_charakteristika;
    }

    public String getMechaninis_atsparumas() {
        return mechaninis_atsparumas;
    }

    public void setMechaninis_atsparumas(String mechaninis_atsparumas) {
        this.mechaninis_atsparumas = mechaninis_atsparumas;
    }

    public String getSkerspjuvis_Al() {
        return skerspjuvis_Al;
    }

    public void setSkerspjuvis_Al(String skerspjuvis_Al) {
        this.skerspjuvis_Al = skerspjuvis_Al;
    }

    public String getSkerspjuvis_Cu() {
        return skerspjuvis_Cu;
    }

    public void setSkerspjuvis_Cu(String skerspjuvis_Cu) {
        this.skerspjuvis_Cu = skerspjuvis_Cu;
    }

    public String getNuotekio_srove() {
        return nuotekio_srove;
    }

    public void setNuotekio_srove(String nuotekio_srove) {
        this.nuotekio_srove = nuotekio_srove;
    }

    public String getDydis() {
        return dydis;
    }

    public void setDydis(String dydis) {
        this.dydis = dydis;
    }

    public String getPlotas() {
        return plotas;
    }

    public void setPlotas(String plotas) {
        this.plotas = plotas;
    }

    public String getCatalogNo() {
        return catalogNo;
    }

    public void setCatalogNo(String catalogNo) {
        this.catalogNo = catalogNo;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getPriceNet() {
        return priceNet;
    }

    public void setPriceNet(double priceNet) {
        this.priceNet = Math.round(priceNet * 100.0) / 100.0;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpalva() {
        return spalva;
    }

    public void setSpalva(String spalva) {
        this.spalva = spalva;
    }

    public double getAukstis() {
        return aukstis;
    }

    public void setAukstis(double aukstis) {
        this.aukstis = aukstis;
    }

    public double getPlotis() {
        return plotis;
    }

    public void setPlotis(double plotis) {
        this.plotis = plotis;
    }

    public double getGylis() {
        return gylis;
    }

    public void setGylis(double gylis) {
        this.gylis = gylis;
    }

    public String getVardine_itampa() {
        return vardine_itampa;
    }

    public void setVardine_itampa(String vardine_itampa) {
        this.vardine_itampa = vardine_itampa;
    }

    public String getGalia() {
        return galia;
    }

    public void setGalia(String galia) {
        this.galia = galia;
    }

    public double getSviesos_srautas() {
        return sviesos_srautas;
    }

    public void setSviesos_srautas(double sviesos_srautas) {
        this.sviesos_srautas = sviesos_srautas;
    }

    public String getDarbine_temperatura() {
        return darbine_temperatura;
    }

    public void setDarbine_temperatura(String darbine_temperatura) {
        this.darbine_temperatura = darbine_temperatura;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ProductCatalog{" +
                "id=" + id +
                ", catalogNo=" + catalogNo +
                ", symbol='" + symbol + '\'' +
                ", priceNet=" + priceNet +
                ", stock=" + stock +
                ", groupId=" + groupId +
                ", date=" + date +
                '}';
    }
}