package sample.JPA;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sample.utils.Validation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ReadExcelWithProductCatalog {
    public static List<ProductCatalog> readFileUsingPOI(File file) throws IOException {
        List<ProductCatalog> products = new ArrayList<>();


        FileInputStream inputStream = new FileInputStream(file);

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        try {


            Iterator iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row nextRow = (Row) iterator.next();
                if (nextRow.getRowNum() == 0)
                    continue;

                ProductCatalog productCatalog = new ProductCatalog();
                Iterator cellIterator = nextRow.cellIterator();

                while (cellIterator.hasNext()) {
                    Cell cell = (Cell) cellIterator.next();
                    int columnIndex = cell.getColumnIndex();
                    switch (columnIndex + 1) {
                        case 1:
                            productCatalog.setCatalogNo(cell.getStringCellValue());
                            break;
                        case 2:
                            productCatalog.setSymbol(cell.getStringCellValue());
                            break;
                        case 3:
                            productCatalog.setPriceNet(cell.getNumericCellValue());
                            break;
                        case 4:
                            String imageUrl = cell.getStringCellValue();
                            if (imageUrl.equals("")) {
                                productCatalog.setImage_url(null);
                            } else if (Validation.isValidImageUrl(imageUrl)) {
                                productCatalog.setImage_url(imageUrl);
                            }
                            break;
                        case 5:
                            productCatalog.setGroupId((int) cell.getNumericCellValue());
                            break;
                        case 6:
                            Double cuAmount = cell.getNumericCellValue();
                            if (cuAmount == null) {
                                productCatalog.setCuAmount(0);
                            } else {
                                productCatalog.setCuAmount(cuAmount);
                            }
                            break;
                        case 7:
                            Double cuPrice = cell.getNumericCellValue();
                            if (cuPrice == null) {
                                productCatalog.setCuPrice(0);
                            } else {
                                productCatalog.setCuPrice(cuPrice);
                            }
                            break;
                        case 8:
                            String manufacturer = cell.getStringCellValue();
                            if (manufacturer.equals("")) {
                                productCatalog.setGamintojas(null);
                            } else {
                                productCatalog.setGamintojas(manufacturer);
                            }
                            break;

                        case 9:
                            Double aukstis = cell.getNumericCellValue();
                            if (aukstis == null) {
                                productCatalog.setAukstis(0);
                            } else {
                                productCatalog.setAukstis(aukstis);
                            }
                            break;
                        case 10:
                            Double plotis = cell.getNumericCellValue();
                            if (plotis == null) {
                                productCatalog.setPlotis(0);
                            } else {
                                productCatalog.setPlotis(plotis);
                            }
                            break;
                        case 11:
                            Double gylis = cell.getNumericCellValue();
                            if (gylis == null) {
                                productCatalog.setGylis(0);
                            } else {
                                productCatalog.setGylis(gylis);
                            }
                            break;
                        case 12:
                            Double skersmuo = cell.getNumericCellValue();
                            if (skersmuo == null) {
                                productCatalog.setSkersmuo(0);
                            } else {
                                productCatalog.setSkersmuo(skersmuo);
                            }
                            break;
                        case 13:
                            Double ilgis = cell.getNumericCellValue();
                            if (ilgis == null) {
                                productCatalog.setIlgis(0);
                            } else {
                                productCatalog.setIlgis(ilgis);
                            }
                            break;
                        case 14:
                            String apsaugos_laipsnis = cell.getStringCellValue();
                            if (apsaugos_laipsnis.equals("")) {
                                productCatalog.setApsaugos_laipsnis(null);
                            } else {
                                productCatalog.setApsaugos_laipsnis(apsaugos_laipsnis);
                            }
                            break;
                        case 15:
                            Double moduliu_skaicius = cell.getNumericCellValue();
                            if (moduliu_skaicius == null) {
                                productCatalog.setModuliu_skaicius(0);
                            } else {
                                productCatalog.setModuliu_skaicius(moduliu_skaicius);
                            }
                            break;
                        case 16:
                            String vardine_srove = cell.getStringCellValue();
                            if (vardine_srove.equals("")) {
                                productCatalog.setVardine_srove(null);
                            } else {
                                productCatalog.setVardine_srove(vardine_srove);
                            }
                            break;
                        case 17:
                            String vardine_itampa = cell.getStringCellValue();
                            if (vardine_itampa.equals("")) {
                                productCatalog.setVardine_itampa(null);
                            } else {
                                productCatalog.setVardine_itampa(vardine_itampa);
                            }
                            break;
                        case 18:
                            String mechaninis_atsparumas_IK = cell.getStringCellValue();
                            if (mechaninis_atsparumas_IK.equals("")) {
                                productCatalog.setMechaninis_atsparumas_IK(null);
                            } else {
                                productCatalog.setMechaninis_atsparumas_IK(mechaninis_atsparumas_IK);
                            }
                            break;
                        case 19:
                            Double storis = cell.getNumericCellValue();
                            if (storis == null) {
                                productCatalog.setStoris(0);
                            } else {
                                productCatalog.setStoris(storis);
                            }
                            break;
                        case 20:
                            String spalva = cell.getStringCellValue();
                            if (spalva.equals("")) {
                                productCatalog.setSpalva(null);
                            } else {
                                productCatalog.setSpalva(spalva);
                            }
                            break;
                        case 21:
                            String korpuso_medziaga = cell.getStringCellValue();
                            if (korpuso_medziaga.equals("")) {
                                productCatalog.setKorpuso_medziaga(null);
                            } else {
                                productCatalog.setKorpuso_medziaga(korpuso_medziaga);
                            }
                            break;
                        case 22:
                            String izoliacija = cell.getStringCellValue();
                            if (izoliacija.equals("")) {
                                productCatalog.setIzoliacija(null);
                            } else {
                                productCatalog.setIzoliacija(izoliacija);
                            }
                            break;
                        case 23:
                            Double svoris = cell.getNumericCellValue();
                            if (svoris == null) {
                                productCatalog.setSvoris(0);
                            } else {
                                productCatalog.setSvoris(svoris);
                            }
                            break;
                        case 24:
                            String galia = cell.getStringCellValue();
                            if (galia.equals("")) {
                                productCatalog.setGalia(null);
                            } else {
                                productCatalog.setGalia(galia);
                            }
                            break;
                        case 25:
                            Double sviesos_srautas = cell.getNumericCellValue();
                            if (sviesos_srautas == null) {
                                productCatalog.setSviesos_srautas(0);
                            } else {
                                productCatalog.setSviesos_srautas(sviesos_srautas);
                            }
                            break;
                        case 26:
                            String sviesos_spalvos_temperatura = cell.getStringCellValue();
                            if (sviesos_spalvos_temperatura.equals("")) {
                                productCatalog.setSviesos_spalvos_temperatura(null);
                            } else {
                                productCatalog.setSviesos_spalvos_temperatura(sviesos_spalvos_temperatura);
                            }
                            break;
                        case 27:
                            String laidininkas = cell.getStringCellValue();
                            if (laidininkas.equals("")) {
                                productCatalog.setLaidininkas(null);
                            } else {
                                productCatalog.setLaidininkas(laidininkas);
                            }
                            break;
                        case 28:
                            String laidininkoIzoliacija = cell.getStringCellValue();
                            if (laidininkoIzoliacija.equals("")) {
                                productCatalog.setLaidininkoIzoliacija(null);
                            } else {
                                productCatalog.setLaidininkoIzoliacija(laidininkoIzoliacija);
                            }
                            break;
                        case 29:
                            String darbine_temperatura = cell.getStringCellValue();
                            if (darbine_temperatura.equals("")) {
                                productCatalog.setDarbine_temperatura(null);
                            } else {
                                productCatalog.setDarbine_temperatura(darbine_temperatura);
                            }
                            break;
                        case 30:
                            String max_darbine_temperatura = cell.getStringCellValue();
                            if (max_darbine_temperatura.equals("")) {
                                productCatalog.setMax_darbine_temperatura(null);
                            } else {
                                productCatalog.setMax_darbine_temperatura(max_darbine_temperatura);
                            }
                            break;
                        case 31:
                            String apsvieta = cell.getStringCellValue();
                            if (apsvieta.equals("")) {
                                productCatalog.setApsvieta(null);
                            } else {
                                productCatalog.setApsvieta(apsvieta);
                            }
                        case 32:
                            String apvalkalas = cell.getStringCellValue();
                            if (apvalkalas.equals("")) {
                                productCatalog.setApvalkalas(null);
                            } else {
                                productCatalog.setApvalkalas(apvalkalas);
                            }
                            break;
                        case 33:
                            String CPR_klase = cell.getStringCellValue();
                            if (CPR_klase.equals("")) {
                                productCatalog.setCpr_klase(null);
                            } else {
                                productCatalog.setCpr_klase(CPR_klase);
                            }
                            break;
                        case 34:
                            String isjungimo_geba = cell.getStringCellValue();
                            if (isjungimo_geba.equals("")) {
                                productCatalog.setIsjungimo_geba(null);
                            } else {
                                productCatalog.setIsjungimo_geba(isjungimo_geba);
                            }
                            break;
                        case 35:
                            String isjungimo_charakteristika = cell.getStringCellValue();
                            if (isjungimo_charakteristika.equals("")) {
                                productCatalog.setIsjungimo_charakteristika(null);
                            } else {
                                productCatalog.setIsjungimo_charakteristika(isjungimo_charakteristika);
                            }
                            break;
                        case 36:
                            String mechaninis_atsparumas = cell.getStringCellValue();
                            if (mechaninis_atsparumas.equals("")) {
                                productCatalog.setMechaninis_atsparumas(null);
                            } else {
                                productCatalog.setMechaninis_atsparumas(mechaninis_atsparumas);
                            }
                            break;
                        case 37:
                            String skerspjuvis_Al = cell.getStringCellValue();
                            if (skerspjuvis_Al.equals("")) {
                                productCatalog.setSkerspjuvis_Al(null);
                            } else {
                                productCatalog.setSkerspjuvis_Al(skerspjuvis_Al);
                            }
                            break;
                        case 38:
                            String skerspjuvis_Cu = cell.getStringCellValue();
                            if (skerspjuvis_Cu.equals("")) {
                                productCatalog.setSkerspjuvis_Cu(null);
                            } else {
                                productCatalog.setSkerspjuvis_Cu(skerspjuvis_Cu);
                            }
                            break;
                        case 39:
                            String nuotekio_srove = cell.getStringCellValue();
                            if (nuotekio_srove.equals("")) {
                                productCatalog.setNuotekio_srove(null);
                            } else {
                                productCatalog.setNuotekio_srove(nuotekio_srove);
                            }
                            break;
                        case 40:
                            String dydis = cell.getStringCellValue();
                            if (dydis.equals("")) {
                                productCatalog.setDydis(null);
                            } else {
                                productCatalog.setDydis(dydis);
                            }
                            break;
                        case 41:
                            String plotas = cell.getStringCellValue();
                            if (plotas.equals("")) {
                                productCatalog.setPlotas(null);
                            } else {
                                productCatalog.setPlotas(plotas);
                            }
                            break;
                        case 42:
                            String aptikimoZona = cell.getStringCellValue();
                            if (aptikimoZona.equals("")) {
                                productCatalog.setAptikimoZona(null);
                            } else {
                                productCatalog.setAptikimoZona(aptikimoZona);
                            }
                            break;
                        case 43:
                            String maxDarbineItampa = cell.getStringCellValue();
                            if (maxDarbineItampa.equals("")) {
                                productCatalog.setMaksimaliDarbineItampa(null);
                            } else {
                                productCatalog.setMaksimaliDarbineItampa(maxDarbineItampa);
                            }
                            break;
                        case 44:
                            String iskrovimoSrove820 = cell.getStringCellValue();
                            if (iskrovimoSrove820.equals("")) {
                                productCatalog.setIskrovimoSrove820(iskrovimoSrove820);
                            } else {
                                productCatalog.setIskrovimoSrove820(iskrovimoSrove820);
                            }
                            break;
                        case 45:
                            String iskrovimoSrove10350 = cell.getStringCellValue();
                            if (iskrovimoSrove10350.equals("")) {
                                productCatalog.setIskrovimoSrove10350(null);
                            } else {
                                productCatalog.setIskrovimoSrove10350(iskrovimoSrove10350);
                            }
                            break;
                        case 46:
                            String itamposApsaugosLygis = cell.getStringCellValue();
                            if (itamposApsaugosLygis.equals("")) {
                                productCatalog.setItamposApsaugosLygis(null);
                            } else {
                                productCatalog.setItamposApsaugosLygis(itamposApsaugosLygis);
                            }
                            break;
                        case 47:
                            String kategorija = cell.getStringCellValue();
                            if (kategorija.equals("")) {
                                productCatalog.setKategorija(null);
                            } else {
                                productCatalog.setKategorija(kategorija);
                            }
                            break;
                        case 48:
                            Integer cri = (int) cell.getNumericCellValue();
                            if (cri == null) {
                                productCatalog.setCRI(0);
                            } else {
                                productCatalog.setCRI(cri);
                            }
                            break;
                        case 49:
                            String garantija = cell.getStringCellValue();
                            if (garantija.equals("")) {
                                productCatalog.setGarantija(null);
                            } else {
                                productCatalog.setGarantija(garantija);
                            }
                            break;
                        case 50:
                            String sertifikatai = cell.getStringCellValue();
                            if (sertifikatai.equals("")) {
                                productCatalog.setSertifikatai(null);
                            } else {
                                productCatalog.setSertifikatai(sertifikatai);
                            }
                            break;
                        case 51:
                            String nemaJungtis = cell.getStringCellValue();
                            if (nemaJungtis.equals("")) {
                                productCatalog.setNemaJungtis(null);
                            } else {
                                productCatalog.setNemaJungtis(nemaJungtis);
                            }
                            break;
                        case 52:
                            String virsItampiuApsauga = cell.getStringCellValue();
                            if (virsItampiuApsauga.equals("")) {
                                productCatalog.setVirsitampiuApsauga(null);
                            } else {
                                productCatalog.setVirsitampiuApsauga(virsItampiuApsauga);
                            }
                            break;
                        case 53:
                            String ilgaamziskumas = cell.getStringCellValue();
                            if (ilgaamziskumas.equals("")) {
                                productCatalog.setIlgaamziskumas(null);
                            } else {
                                productCatalog.setIlgaamziskumas(ilgaamziskumas);
                            }
                            break;
                        case 54:
                            String korpusoAtidarymas = cell.getStringCellValue();
                            if (korpusoAtidarymas.equals("")) {
                                productCatalog.setKorpusoAtidarymas(null);
                            } else {
                                productCatalog.setKorpusoAtidarymas(korpusoAtidarymas);
                            }
                            break;
                        case 55:
                            String optinesIrElektrinesDaliesPertvara = cell.getStringCellValue();
                            if (optinesIrElektrinesDaliesPertvara.equals("")) {
                                productCatalog.setOptinesIrElektrinesDaliesPertvara(null);
                            } else {
                                productCatalog.setOptinesIrElektrinesDaliesPertvara(optinesIrElektrinesDaliesPertvara);
                            }
                            break;
                        case 56:
                            String valdymas = cell.getStringCellValue();
                            if (valdymas.equals("")) {
                                productCatalog.setValdymas(null);
                            } else {
                                productCatalog.setValdymas(valdymas);
                            }
                            break;
                        case 57:
                            Integer apatinisDiametras =(int) cell.getNumericCellValue();
                            if (apatinisDiametras == null) {
                                productCatalog.setApatinisDiametras(0);
                            } else {
                                productCatalog.setApatinisDiametras(apatinisDiametras);
                            }
                            break;
                        case 58:
                            Integer virsutinisDiametras =(int) cell.getNumericCellValue();
                            if (virsutinisDiametras == null) {
                                productCatalog.setVirsutinisDiametras(0);
                            } else {
                                productCatalog.setVirsutinisDiametras(virsutinisDiametras);
                            }
                            break;
                        case 59:
                            Integer gembesDiametras =(int) cell.getNumericCellValue();
                            if (gembesDiametras == null) {
                                productCatalog.setGembesDiametras(0);
                            } else {
                                productCatalog.setGembesDiametras(gembesDiametras);
                            }
                            break;
                    }

                }
                if (productCatalog.getCatalogNo().isEmpty() && productCatalog.getSymbol().isEmpty() && productCatalog.getGroupId() == 0) {
                    break;
                }
                products.add(productCatalog);

            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            workbook.close();
            inputStream.close();
        }
        return products;
    }
}
