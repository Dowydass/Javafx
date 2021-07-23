package sample.JPA;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "category_parameters")
public class CategoryParameters {

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "incrementation")
    @GenericGenerator(name = "incrementation", strategy = "increment")
    private int id;
    @Column(name = "aukstis")
    private boolean aukstis;
    @Column(name = "plotis")
    private boolean plotis;
    @Column(name = "gylis")
    private boolean gylis;
    @Column(name = "skersmuo")
    private boolean skersmuo;
    @Column(name = "ilgis")
    private boolean ilgis;
    @Column(name = "apsaugos_laipsnis")
    private boolean apsaugos_laipsnis;
    @Column(name = "moduliu_skaicius")
    private boolean moduliu_skaicius;
    @Column(name = "vardine_srove")
    private boolean vardine_srove;
    @Column(name = "vardine_itampa")
    private boolean vardine_itampa;
    @Column(name = "mechaninis_atsparumas_IK")
    private boolean mechaninis_atsparumas_IK;
    @Column(name = "spalva")
    private boolean spalva;
    @Column(name = "korpuso_medziaga")
    private boolean korpuso_medziaga;
    @Column(name = "izoliacija")
    private boolean izoliacija;
    @Column(name = "svoris")
    private boolean svoris;
    @Column(name = "galia")
    private boolean galia;
    @Column(name = "sviesos_srautas")
    private boolean sviesos_srautas;
    @Column(name = "sviesos_spalvos_temperatura")
    private boolean sviesos_spalvos_temperatura;
    @Column(name = "laidininkas")
    private boolean laidininkas;
    @Column(name = "darbine_temperatura")
    private boolean darbine_temperatura;
    @Column(name = "Max_darbine_temperatura")
    private boolean max_darbine_temperatura;
    @Column(name = "apvalkalas")
    private boolean apvalkalas;
    @Column(name = "CPR_klase")
    private boolean CPR_klase;
    @Column(name = "isjungimo_geba")
    private boolean isjungimo_geba;
    @Column(name = "isjungimo_charakteristika")
    private boolean isjungimo_charakteristika;
    @Column(name = "mechaninis_atsparumas")
    private boolean mechaninis_atsparumas;
    @Column(name = "skerspjuvis_Al")
    private boolean skerspjuvis_Al;
    @Column(name = "skerspjuvis_Cu")
    private boolean skerspjuvis_Cu;
    @Column(name = "nuotekio_srove")
    private boolean nuotekio_srove;
    @Column(name = "dydis")
    private boolean dydis;
    @Column(name = "plotas")
    private boolean plotas;


    public CategoryParameters() {
    }

    public CategoryParameters(boolean aukstis, boolean plotis, boolean gylis, boolean skersmuo, boolean ilgis, boolean apsaugos_laipsnis, boolean moduliu_skaicius, boolean vardine_srove, boolean vardine_itampa, boolean mechaninis_atsparumas_IK, boolean spalva, boolean korpuso_medziaga, boolean izoliacija, boolean svoris, boolean galia, boolean sviesos_srautas, boolean sviesos_spalvos_temperatura, boolean laidininkas, boolean darbine_temperatura, boolean max_darbine_temperatura, boolean apvalkalas, boolean CPR_klase, boolean isjungimo_geba, boolean isjungimo_charakteristika, boolean mechaninis_atsparumas, boolean skerspjuvis_Al, boolean skerspjuvis_Cu, boolean nuotekio_srove, boolean dydis, boolean plotas) {
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
        this.CPR_klase = CPR_klase;
        this.isjungimo_geba = isjungimo_geba;
        this.isjungimo_charakteristika = isjungimo_charakteristika;
        this.mechaninis_atsparumas = mechaninis_atsparumas;
        this.skerspjuvis_Al = skerspjuvis_Al;
        this.skerspjuvis_Cu = skerspjuvis_Cu;
        this.nuotekio_srove = nuotekio_srove;
        this.dydis = dydis;
        this.plotas = plotas;
    }

    public int getId() {
        return id;
    }

    public boolean isAukstis() {
        return aukstis;
    }

    public boolean isPlotis() {
        return plotis;
    }

    public boolean isGylis() {
        return gylis;
    }

    public boolean isSkersmuo() {
        return skersmuo;
    }

    public boolean isIlgis() {
        return ilgis;
    }

    public boolean isApsaugos_laipsnis() {
        return apsaugos_laipsnis;
    }

    public boolean isModuliu_skaicius() {
        return moduliu_skaicius;
    }

    public boolean isVardine_srove() {
        return vardine_srove;
    }

    public boolean isVardine_itampa() {
        return vardine_itampa;
    }

    public boolean isMechaninis_atsparumas_IK() {
        return mechaninis_atsparumas_IK;
    }

    public boolean isSpalva() {
        return spalva;
    }

    public boolean isKorpuso_medziaga() {
        return korpuso_medziaga;
    }

    public boolean isIzoliacija() {
        return izoliacija;
    }

    public boolean isSvoris() {
        return svoris;
    }

    public boolean isGalia() {
        return galia;
    }

    public boolean isSviesos_srautas() {
        return sviesos_srautas;
    }

    public boolean isSviesos_spalvos_temperatura() {
        return sviesos_spalvos_temperatura;
    }

    public boolean isLaidininkas() {
        return laidininkas;
    }

    public boolean isDarbine_temperatura() {
        return darbine_temperatura;
    }

    public boolean isMax_darbine_temperatura() {
        return max_darbine_temperatura;
    }

    public boolean isApvalkalas() {
        return apvalkalas;
    }

    public boolean isCPR_klase() {
        return CPR_klase;
    }

    public boolean isIsjungimo_geba() {
        return isjungimo_geba;
    }

    public boolean isIsjungimo_charakteristika() {
        return isjungimo_charakteristika;
    }

    public boolean isMechaninis_atsparumas() {
        return mechaninis_atsparumas;
    }

    public boolean isNuotekio_srove() {
        return nuotekio_srove;
    }

    public boolean isDydis() {
        return dydis;
    }

    public boolean isPlotas() {
        return plotas;
    }

    public void setAukstis(boolean aukstis) {
        this.aukstis = aukstis;
    }

    public void setPlotis(boolean plotis) {
        this.plotis = plotis;
    }

    public void setGylis(boolean gylis) {
        this.gylis = gylis;
    }

    public void setSkersmuo(boolean skersmuo) {
        this.skersmuo = skersmuo;
    }

    public void setIlgis(boolean ilgis) {
        this.ilgis = ilgis;
    }

    public void setApsaugos_laipsnis(boolean apsaugos_laipsnis) {
        this.apsaugos_laipsnis = apsaugos_laipsnis;
    }

    public void setModuliu_skaicius(boolean moduliu_skaicius) {
        this.moduliu_skaicius = moduliu_skaicius;
    }

    public void setVardine_srove(boolean vardine_srove) {
        this.vardine_srove = vardine_srove;
    }

    public void setVardine_itampa(boolean vardine_itampa) {
        this.vardine_itampa = vardine_itampa;
    }

    public void setMechaninis_atsparumas_IK(boolean mechaninis_atsparumas_IK) {
        this.mechaninis_atsparumas_IK = mechaninis_atsparumas_IK;
    }

    public void setSpalva(boolean spalva) {
        this.spalva = spalva;
    }

    public void setKorpuso_medziaga(boolean korpuso_medziaga) {
        this.korpuso_medziaga = korpuso_medziaga;
    }

    public void setIzoliacija(boolean izoliacija) {
        this.izoliacija = izoliacija;
    }

    public void setSvoris(boolean svoris) {
        this.svoris = svoris;
    }

    public void setGalia(boolean galia) {
        this.galia = galia;
    }

    public void setSviesos_srautas(boolean sviesos_srautas) {
        this.sviesos_srautas = sviesos_srautas;
    }

    public void setSviesos_spalvos_temperatura(boolean sviesos_spalvos_temperatura) {
        this.sviesos_spalvos_temperatura = sviesos_spalvos_temperatura;
    }

    public void setLaidininkas(boolean laidininkas) {
        this.laidininkas = laidininkas;
    }

    public void setDarbine_temperatura(boolean darbine_temperatura) {
        this.darbine_temperatura = darbine_temperatura;
    }

    public void setMax_darbine_temperatura(boolean max_darbine_temperatura) {
        this.max_darbine_temperatura = max_darbine_temperatura;
    }

    public void setApvalkalas(boolean apvalkalas) {
        this.apvalkalas = apvalkalas;
    }

    public void setCPR_klase(boolean CPR_klase) {
        this.CPR_klase = CPR_klase;
    }

    public void setIsjungimo_geba(boolean isjungimo_geba) {
        this.isjungimo_geba = isjungimo_geba;
    }

    public void setIsjungimo_charakteristika(boolean isjungimo_charakteristika) {
        this.isjungimo_charakteristika = isjungimo_charakteristika;
    }

    public void setMechaninis_atsparumas(boolean mechaninis_atsparumas) {
        this.mechaninis_atsparumas = mechaninis_atsparumas;
    }

    public boolean isSkerspjuvis_Al() {
        return skerspjuvis_Al;
    }

    public void setSkerspjuvis_Al(boolean skerspjuvis_Al) {
        this.skerspjuvis_Al = skerspjuvis_Al;
    }

    public boolean isSkerspjuvis_Cu() {
        return skerspjuvis_Cu;
    }

    public void setSkerspjuvis_Cu(boolean skerspjuvis_Cu) {
        this.skerspjuvis_Cu = skerspjuvis_Cu;
    }

    public void setNuotekio_srove(boolean nuotekio_srove) {
        this.nuotekio_srove = nuotekio_srove;
    }

    public void setDydis(boolean dydis) {
        this.dydis = dydis;
    }

    public void setPlotas(boolean plotas) {
        this.plotas = plotas;
    }
}


