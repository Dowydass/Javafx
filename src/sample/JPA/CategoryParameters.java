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
    @Column(name = "Cu_amount")
    private boolean cuAmount;
    @Column(name = "Cu_price")
    private boolean cuPrice;
    @Column(name = "gamintojas")
    private boolean gamintojas;
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
    @Column(name = "storis")
    private boolean storis;
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
    @Column(name = "Laidininko_izoliacija")
    private boolean laidininkoIzoliacija;
    @Column(name = "darbine_temperatura")
    private boolean darbine_temperatura;
    @Column(name = "Max_darbine_temperatura")
    private boolean max_darbine_temperatura;
    @Column(name = "apsvieta")
    private boolean apsvieta;
    @Column(name = "apvalkalas")
    private boolean apvalkalas;
    @Column(name = "CPR_klase")
    private boolean cpr_klase;
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
    @Column(name = "aptikimo_zona")
    private boolean aptikimoZona;
    @Column(name = "maksimali_darbine_itampa")
    private boolean maksimaliDarbineItampa;
    @Column(name = "iskrovimo_srove_8_20")
    private boolean iskrovimoSrove820;
    @Column(name = "iskrovimo_srove_10_350")
    private boolean iskrovimoSrove10350;
    @Column(name = "itampos_apsaugos_lygis")
    private boolean itamposApsaugosLygis;
    @Column(name = "Kategorija")
    private boolean kategorija;
    @Column(name = "CRI")
    private boolean CRI;
    @Column(name = "Garantija")
    private boolean garantija;
    @Column(name = "sertifikatai")
    private boolean sertifikatai;
    @Column(name = "Nema_jungtis")
    private boolean nemaJungtis;
    @Column(name = "virsitampiu_apsauga")
    private boolean virsitampiuApsauga;
    @Column(name = "ilgaamziskumas")
    private boolean ilgaamziskumas;
    @Column(name = "korpuso_atidarymas")
    private boolean korpusoAtidarymas;
    @Column(name = "optines_ir_elektrines_dalies_pertvara")
    private boolean optinesIrElektrinesDaliesPertvara;
    @Column(name = "valdymas")
    private boolean valdymas;
    @Column(name = "apatinis_diametras")
    private boolean apatinisDiametras;
    @Column(name = "virsutinis_diametras")
    private boolean virsutinisDiametras;
    @Column(name = "gembes_diametras")
    private boolean gembesDiametras;


    public CategoryParameters() {
    }

    public CategoryParameters(boolean cuAmount, boolean cuPrice, boolean gamintojas, boolean aukstis, boolean plotis, boolean gylis, boolean skersmuo, boolean ilgis, boolean apsaugos_laipsnis, boolean moduliu_skaicius, boolean vardine_srove, boolean vardine_itampa, boolean mechaninis_atsparumas_IK, boolean storis, boolean spalva, boolean korpuso_medziaga, boolean izoliacija, boolean svoris, boolean galia, boolean sviesos_srautas, boolean sviesos_spalvos_temperatura, boolean laidininkas, boolean laidininkoIzoliacija, boolean darbine_temperatura, boolean max_darbine_temperatura, boolean apsvieta, boolean apvalkalas, boolean cpr_klase, boolean isjungimo_geba, boolean isjungimo_charakteristika, boolean mechaninis_atsparumas, boolean skerspjuvis_Al, boolean skerspjuvis_Cu, boolean nuotekio_srove, boolean dydis, boolean plotas, boolean aptikimoZona, boolean maksimaliDarbineItampa, boolean iskrovimoSrove820, boolean iskrovimoSrove10350, boolean itamposApsaugosLygis, boolean kategorija, boolean CRI, boolean garantija, boolean sertifikatai, boolean nemaJungtis, boolean virsitampiuApsauga, boolean ilgaamziskumas, boolean korpusoAtidarymas, boolean optinesIrElektrinesDaliesPertvara, boolean valdymas, boolean apatinisDiametras, boolean virsutinisDiametras, boolean gembesDiametras) {
        this.cuAmount = cuAmount;
        this.cuPrice = cuPrice;
        this.gamintojas = gamintojas;
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
        this.storis = storis;
        this.spalva = spalva;
        this.korpuso_medziaga = korpuso_medziaga;
        this.izoliacija = izoliacija;
        this.svoris = svoris;
        this.galia = galia;
        this.sviesos_srautas = sviesos_srautas;
        this.sviesos_spalvos_temperatura = sviesos_spalvos_temperatura;
        this.laidininkas = laidininkas;
        this.laidininkoIzoliacija = laidininkoIzoliacija;
        this.darbine_temperatura = darbine_temperatura;
        this.max_darbine_temperatura = max_darbine_temperatura;
        this.apsvieta = apsvieta;
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
        this.aptikimoZona = aptikimoZona;
        this.maksimaliDarbineItampa = maksimaliDarbineItampa;
        this.iskrovimoSrove820 = iskrovimoSrove820;
        this.iskrovimoSrove10350 = iskrovimoSrove10350;
        this.itamposApsaugosLygis = itamposApsaugosLygis;
        this.kategorija = kategorija;
        this.CRI = CRI;
        this.garantija = garantija;
        this.sertifikatai = sertifikatai;
        this.nemaJungtis = nemaJungtis;
        this.virsitampiuApsauga = virsitampiuApsauga;
        this.ilgaamziskumas = ilgaamziskumas;
        this.korpusoAtidarymas = korpusoAtidarymas;
        this.optinesIrElektrinesDaliesPertvara = optinesIrElektrinesDaliesPertvara;
        this.valdymas = valdymas;
        this.apatinisDiametras = apatinisDiametras;
        this.virsutinisDiametras = virsutinisDiametras;
        this.gembesDiametras = gembesDiametras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCuAmount() {
        return cuAmount;
    }

    public void setCuAmount(boolean cuAmount) {
        this.cuAmount = cuAmount;
    }

    public boolean isCuPrice() {
        return cuPrice;
    }

    public void setCuPrice(boolean cuPrice) {
        this.cuPrice = cuPrice;
    }

    public boolean isGamintojas() {
        return gamintojas;
    }

    public void setGamintojas(boolean gamintojas) {
        this.gamintojas = gamintojas;
    }

    public boolean isAukstis() {
        return aukstis;
    }

    public void setAukstis(boolean aukstis) {
        this.aukstis = aukstis;
    }

    public boolean isPlotis() {
        return plotis;
    }

    public void setPlotis(boolean plotis) {
        this.plotis = plotis;
    }

    public boolean isGylis() {
        return gylis;
    }

    public void setGylis(boolean gylis) {
        this.gylis = gylis;
    }

    public boolean isSkersmuo() {
        return skersmuo;
    }

    public void setSkersmuo(boolean skersmuo) {
        this.skersmuo = skersmuo;
    }

    public boolean isIlgis() {
        return ilgis;
    }

    public void setIlgis(boolean ilgis) {
        this.ilgis = ilgis;
    }

    public boolean isApsaugos_laipsnis() {
        return apsaugos_laipsnis;
    }

    public void setApsaugos_laipsnis(boolean apsaugos_laipsnis) {
        this.apsaugos_laipsnis = apsaugos_laipsnis;
    }

    public boolean isModuliu_skaicius() {
        return moduliu_skaicius;
    }

    public void setModuliu_skaicius(boolean moduliu_skaicius) {
        this.moduliu_skaicius = moduliu_skaicius;
    }

    public boolean isVardine_srove() {
        return vardine_srove;
    }

    public void setVardine_srove(boolean vardine_srove) {
        this.vardine_srove = vardine_srove;
    }

    public boolean isVardine_itampa() {
        return vardine_itampa;
    }

    public void setVardine_itampa(boolean vardine_itampa) {
        this.vardine_itampa = vardine_itampa;
    }

    public boolean isMechaninis_atsparumas_IK() {
        return mechaninis_atsparumas_IK;
    }

    public void setMechaninis_atsparumas_IK(boolean mechaninis_atsparumas_IK) {
        this.mechaninis_atsparumas_IK = mechaninis_atsparumas_IK;
    }

    public boolean isStoris() {
        return storis;
    }

    public void setStoris(boolean storis) {
        this.storis = storis;
    }

    public boolean isSpalva() {
        return spalva;
    }

    public void setSpalva(boolean spalva) {
        this.spalva = spalva;
    }

    public boolean isKorpuso_medziaga() {
        return korpuso_medziaga;
    }

    public void setKorpuso_medziaga(boolean korpuso_medziaga) {
        this.korpuso_medziaga = korpuso_medziaga;
    }

    public boolean isIzoliacija() {
        return izoliacija;
    }

    public void setIzoliacija(boolean izoliacija) {
        this.izoliacija = izoliacija;
    }

    public boolean isSvoris() {
        return svoris;
    }

    public void setSvoris(boolean svoris) {
        this.svoris = svoris;
    }

    public boolean isGalia() {
        return galia;
    }

    public void setGalia(boolean galia) {
        this.galia = galia;
    }

    public boolean isSviesos_srautas() {
        return sviesos_srautas;
    }

    public void setSviesos_srautas(boolean sviesos_srautas) {
        this.sviesos_srautas = sviesos_srautas;
    }

    public boolean isSviesos_spalvos_temperatura() {
        return sviesos_spalvos_temperatura;
    }

    public void setSviesos_spalvos_temperatura(boolean sviesos_spalvos_temperatura) {
        this.sviesos_spalvos_temperatura = sviesos_spalvos_temperatura;
    }

    public boolean isLaidininkas() {
        return laidininkas;
    }

    public void setLaidininkas(boolean laidininkas) {
        this.laidininkas = laidininkas;
    }

    public boolean isLaidininkoIzoliacija() {
        return laidininkoIzoliacija;
    }

    public void setLaidininkoIzoliacija(boolean laidininkoIzoliacija) {
        this.laidininkoIzoliacija = laidininkoIzoliacija;
    }

    public boolean isDarbine_temperatura() {
        return darbine_temperatura;
    }

    public void setDarbine_temperatura(boolean darbine_temperatura) {
        this.darbine_temperatura = darbine_temperatura;
    }

    public boolean isMax_darbine_temperatura() {
        return max_darbine_temperatura;
    }

    public void setMax_darbine_temperatura(boolean max_darbine_temperatura) {
        this.max_darbine_temperatura = max_darbine_temperatura;
    }

    public boolean isApsvieta() {
        return apsvieta;
    }

    public void setApsvieta(boolean apsvieta) {
        this.apsvieta = apsvieta;
    }

    public boolean isApvalkalas() {
        return apvalkalas;
    }

    public void setApvalkalas(boolean apvalkalas) {
        this.apvalkalas = apvalkalas;
    }

    public boolean isCpr_klase() {
        return cpr_klase;
    }

    public void setCpr_klase(boolean cpr_klase) {
        this.cpr_klase = cpr_klase;
    }

    public boolean isIsjungimo_geba() {
        return isjungimo_geba;
    }

    public void setIsjungimo_geba(boolean isjungimo_geba) {
        this.isjungimo_geba = isjungimo_geba;
    }

    public boolean isIsjungimo_charakteristika() {
        return isjungimo_charakteristika;
    }

    public void setIsjungimo_charakteristika(boolean isjungimo_charakteristika) {
        this.isjungimo_charakteristika = isjungimo_charakteristika;
    }

    public boolean isMechaninis_atsparumas() {
        return mechaninis_atsparumas;
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

    public boolean isNuotekio_srove() {
        return nuotekio_srove;
    }

    public void setNuotekio_srove(boolean nuotekio_srove) {
        this.nuotekio_srove = nuotekio_srove;
    }

    public boolean isDydis() {
        return dydis;
    }

    public void setDydis(boolean dydis) {
        this.dydis = dydis;
    }

    public boolean isPlotas() {
        return plotas;
    }

    public void setPlotas(boolean plotas) {
        this.plotas = plotas;
    }

    public boolean isAptikimoZona() {
        return aptikimoZona;
    }

    public void setAptikimoZona(boolean aptikimoZona) {
        this.aptikimoZona = aptikimoZona;
    }

    public boolean isMaksimaliDarbineItampa() {
        return maksimaliDarbineItampa;
    }

    public void setMaksimaliDarbineItampa(boolean maksimaliDarbineItampa) {
        this.maksimaliDarbineItampa = maksimaliDarbineItampa;
    }

    public boolean isIskrovimoSrove820() {
        return iskrovimoSrove820;
    }

    public void setIskrovimoSrove820(boolean iskrovimoSrove820) {
        this.iskrovimoSrove820 = iskrovimoSrove820;
    }

    public boolean isIskrovimoSrove10350() {
        return iskrovimoSrove10350;
    }

    public void setIskrovimoSrove10350(boolean iskrovimoSrove10350) {
        this.iskrovimoSrove10350 = iskrovimoSrove10350;
    }

    public boolean isItamposApsaugosLygis() {
        return itamposApsaugosLygis;
    }

    public void setItamposApsaugosLygis(boolean itamposApsaugosLygis) {
        this.itamposApsaugosLygis = itamposApsaugosLygis;
    }

    public boolean isKategorija() {
        return kategorija;
    }

    public void setKategorija(boolean kategorija) {
        this.kategorija = kategorija;
    }

    public boolean isCRI() {
        return CRI;
    }

    public void setCRI(boolean CRI) {
        this.CRI = CRI;
    }

    public boolean isGarantija() {
        return garantija;
    }

    public void setGarantija(boolean garantija) {
        this.garantija = garantija;
    }

    public boolean isSertifikatai() {
        return sertifikatai;
    }

    public void setSertifikatai(boolean sertifikatai) {
        this.sertifikatai = sertifikatai;
    }

    public boolean isNemaJungtis() {
        return nemaJungtis;
    }

    public void setNemaJungtis(boolean nemaJungtis) {
        this.nemaJungtis = nemaJungtis;
    }

    public boolean isVirsitampiuApsauga() {
        return virsitampiuApsauga;
    }

    public void setVirsitampiuApsauga(boolean virsitampiuApsauga) {
        this.virsitampiuApsauga = virsitampiuApsauga;
    }

    public boolean isIlgaamziskumas() {
        return ilgaamziskumas;
    }

    public void setIlgaamziskumas(boolean ilgaamziskumas) {
        this.ilgaamziskumas = ilgaamziskumas;
    }

    public boolean isKorpusoAtidarymas() {
        return korpusoAtidarymas;
    }

    public void setKorpusoAtidarymas(boolean korpusoAtidarymas) {
        this.korpusoAtidarymas = korpusoAtidarymas;
    }

    public boolean isOptinesIrElektrinesDaliesPertvara() {
        return optinesIrElektrinesDaliesPertvara;
    }

    public void setOptinesIrElektrinesDaliesPertvara(boolean optinesIrElektrinesDaliesPertvara) {
        this.optinesIrElektrinesDaliesPertvara = optinesIrElektrinesDaliesPertvara;
    }

    public boolean isValdymas() {
        return valdymas;
    }

    public void setValdymas(boolean valdymas) {
        this.valdymas = valdymas;
    }

    public boolean isApatinisDiametras() {
        return apatinisDiametras;
    }

    public void setApatinisDiametras(boolean apatinisDiametras) {
        this.apatinisDiametras = apatinisDiametras;
    }

    public boolean isVirsutinisDiametras() {
        return virsutinisDiametras;
    }

    public void setVirsutinisDiametras(boolean virsutinisDiametras) {
        this.virsutinisDiametras = virsutinisDiametras;
    }

    public boolean isGembesDiametras() {
        return gembesDiametras;
    }

    public void setGembesDiametras(boolean gembesDiametras) {
        this.gembesDiametras = gembesDiametras;
    }

    @Override
    public String toString() {
        return "CategoryParameters{" +
                "cuAmount=" + cuAmount +
                ", cuPrice=" + cuPrice +
                ", gamintojas=" + gamintojas +
                ", aukstis=" + aukstis +
                ", plotis=" + plotis +
                ", gylis=" + gylis +
                ", skersmuo=" + skersmuo +
                ", ilgis=" + ilgis +
                ", apsaugos_laipsnis=" + apsaugos_laipsnis +
                ", moduliu_skaicius=" + moduliu_skaicius +
                ", vardine_srove=" + vardine_srove +
                ", vardine_itampa=" + vardine_itampa +
                ", mechaninis_atsparumas_IK=" + mechaninis_atsparumas_IK +
                ", storis=" + storis +
                ", spalva=" + spalva +
                ", korpuso_medziaga=" + korpuso_medziaga +
                ", izoliacija=" + izoliacija +
                ", svoris=" + svoris +
                ", galia=" + galia +
                ", sviesos_srautas=" + sviesos_srautas +
                ", sviesos_spalvos_temperatura=" + sviesos_spalvos_temperatura +
                ", laidininkas=" + laidininkas +
                ", laidininkoIzoliacija=" + laidininkoIzoliacija +
                ", darbine_temperatura=" + darbine_temperatura +
                ", max_darbine_temperatura=" + max_darbine_temperatura +
                ", apsvieta=" + apsvieta +
                ", apvalkalas=" + apvalkalas +
                ", cpr_klase=" + cpr_klase +
                ", isjungimo_geba=" + isjungimo_geba +
                ", isjungimo_charakteristika=" + isjungimo_charakteristika +
                ", mechaninis_atsparumas=" + mechaninis_atsparumas +
                ", skerspjuvis_Al=" + skerspjuvis_Al +
                ", skerspjuvis_Cu=" + skerspjuvis_Cu +
                ", nuotekio_srove=" + nuotekio_srove +
                ", dydis=" + dydis +
                ", plotas=" + plotas +
                ", aptikimoZona=" + aptikimoZona +
                ", maksimaliDarbineItampa=" + maksimaliDarbineItampa +
                ", iskrovimoSrove820=" + iskrovimoSrove820 +
                ", iskrovimoSrove10350=" + iskrovimoSrove10350 +
                ", itamposApsaugosLygis=" + itamposApsaugosLygis +
                ", kategorija=" + kategorija +
                ", CRI=" + CRI +
                ", garantija=" + garantija +
                ", sertifikatai=" + sertifikatai +
                ", nemaJungtis=" + nemaJungtis +
                ", virsitampiuApsauga=" + virsitampiuApsauga +
                ", ilgaamziskumas=" + ilgaamziskumas +
                ", korpusoAtidarymas=" + korpusoAtidarymas +
                ", optinesIrElektrinesDaliesPertvara=" + optinesIrElektrinesDaliesPertvara +
                ", valdymas=" + valdymas +
                ", apatinisDiametras=" + apatinisDiametras +
                ", virsutinisDiametras=" + virsutinisDiametras +
                ", gembesDiametras=" + gembesDiametras +
                '}';
    }
}


