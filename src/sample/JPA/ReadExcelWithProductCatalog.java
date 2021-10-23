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
                            String imageUrl;
                            if (cell.getCellType() == 0) {
                                imageUrl = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                imageUrl = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (imageUrl.equals("")) {
                                productCatalog.setGamintojas(null);
                            } else if (Validation.isValidImageUrl(imageUrl)) {
                                productCatalog.setImage_url(imageUrl);
                            }
                            break;
                        case 5:
                            productCatalog.setGroupId((int) cell.getNumericCellValue());
                            break;
                        case 6:
                            if (cell.getCellType() == 0) {
                                Double cuAmount = cell.getNumericCellValue();
                                if (cuAmount == null) {
                                    productCatalog.setCuAmount(0);
                                } else {
                                    productCatalog.setCuAmount(cuAmount);
                                }
                            }
                            break;
                        case 7:
                            if (cell.getCellType() == 0) {
                                Double cuPrice = cell.getNumericCellValue();
                                if (cuPrice == null) {
                                    productCatalog.setCuPrice(0);
                                } else {
                                    productCatalog.setCuPrice(cuPrice);
                                }
                            }
                            break;
                        case 8:
                            String manufacturer;
                            if (cell.getCellType() == 0) {
                                manufacturer = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                manufacturer = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (manufacturer.equals("")) {
                                productCatalog.setGamintojas(null);
                            } else {
                                productCatalog.setGamintojas(manufacturer);
                            }
                            break;
                        case 9:
                            if (cell.getCellType() == 0) {
                                Double aukstis = cell.getNumericCellValue();
                                if (aukstis == null) {
                                    productCatalog.setAukstis(0);
                                } else {
                                    productCatalog.setAukstis(aukstis);
                                }
                            }
                            break;
                        case 10:
                            if (cell.getCellType() == 0) {
                                Double plotis = cell.getNumericCellValue();
                                if (plotis == null) {
                                    productCatalog.setPlotis(0);
                                } else {
                                    productCatalog.setPlotis(plotis);
                                }
                            }
                            break;
                        case 11:
                            if (cell.getCellType() == 0) {
                                Double gylis = cell.getNumericCellValue();
                                if (gylis == null) {
                                    productCatalog.setGylis(0);
                                } else {
                                    productCatalog.setGylis(gylis);
                                }
                            }
                            break;
                        case 12:
                            if (cell.getCellType() == 0) {
                                Double skersmuo = cell.getNumericCellValue();
                                if (skersmuo == null) {
                                    productCatalog.setSkersmuo(0);
                                } else {
                                    productCatalog.setSkersmuo(skersmuo);
                                }
                            }
                            break;
                        case 13:
                            if (cell.getCellType() == 0) {
                                Double ilgis = cell.getNumericCellValue();
                                if (ilgis == null) {
                                    productCatalog.setIlgis(0);
                                } else {
                                    productCatalog.setIlgis(ilgis);
                                }
                            }
                            break;
                        case 14:
                            String apsaugos_laipsnis;
                            if (cell.getCellType() == 0) {
                                apsaugos_laipsnis = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                apsaugos_laipsnis = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (apsaugos_laipsnis.equals("")) {
                                productCatalog.setApsaugos_laipsnis(null);
                            } else {
                                productCatalog.setApsaugos_laipsnis(apsaugos_laipsnis);
                            }
                            break;
                        case 15:
                            if (cell.getCellType() == 0) {
                                Double moduliu_skaicius = cell.getNumericCellValue();
                                if (moduliu_skaicius == null) {
                                    productCatalog.setModuliu_skaicius(0);
                                } else {
                                    productCatalog.setModuliu_skaicius(moduliu_skaicius);
                                }
                            }
                            break;
                        case 16:
                            String vardine_srove;
                            if (cell.getCellType() == 0) {
                                vardine_srove = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                vardine_srove = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (vardine_srove.equals("")) {
                                productCatalog.setVardine_srove(null);
                            } else {
                                productCatalog.setVardine_srove(vardine_srove);
                            }
                            break;
                        case 17:
                            String vardine_itampa;
                            if (cell.getCellType() == 0) {
                                vardine_itampa = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                vardine_itampa = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (vardine_itampa.equals("")) {
                                productCatalog.setVardine_itampa(null);
                            } else {
                                productCatalog.setVardine_itampa(vardine_itampa);
                            }
                            break;
                        case 18:
                            String mechaninis_atsparumas_IK;
                            if (cell.getCellType() == 0) {
                                mechaninis_atsparumas_IK = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                mechaninis_atsparumas_IK = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (mechaninis_atsparumas_IK.equals("")) {
                                productCatalog.setMechaninis_atsparumas_IK(null);
                            } else {
                                productCatalog.setMechaninis_atsparumas_IK(mechaninis_atsparumas_IK);
                            }
                            break;
                        case 19:
                            if (cell.getCellType() == 0) {
                                Double storis = cell.getNumericCellValue();
                                if (storis == null) {
                                    productCatalog.setStoris(0);
                                } else {
                                    productCatalog.setStoris(storis);
                                }
                            }
                            break;
                        case 20:
                            String spalva;
                            if (cell.getCellType() == 0) {
                                spalva = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                spalva = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (spalva.equals("")) {
                                productCatalog.setSpalva(null);
                            } else {
                                productCatalog.setSpalva(spalva);
                            }
                            break;
                        case 21:
                            String korpuso_medziaga;
                            if (cell.getCellType() == 0) {
                                korpuso_medziaga = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                korpuso_medziaga = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (korpuso_medziaga.equals("")) {
                                productCatalog.setKorpuso_medziaga(null);
                            } else {
                                productCatalog.setKorpuso_medziaga(korpuso_medziaga);
                            }
                            break;
                        case 22:
                            String izoliacija;
                            if (cell.getCellType() == 0) {
                                izoliacija = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                izoliacija = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (izoliacija.equals("")) {
                                productCatalog.setIzoliacija(null);
                            } else {
                                productCatalog.setIzoliacija(izoliacija);
                            }
                            break;
                        case 23:
                            if (cell.getCellType() == 0) {
                                Double svoris = cell.getNumericCellValue();
                                if (svoris == null) {
                                    productCatalog.setSvoris(0);
                                } else {
                                    productCatalog.setSvoris(svoris);
                                }
                            }
                            break;
                        case 24:
                            String galia;
                            if (cell.getCellType() == 0) {
                                galia = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                galia = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (galia.equals("")) {
                                productCatalog.setGalia(null);
                            } else {
                                productCatalog.setGalia(galia);
                            }
                            break;
                        case 25:
                            if (cell.getCellType() == 0) {
                                Double sviesos_srautas = cell.getNumericCellValue();
                                if (sviesos_srautas == null) {
                                    productCatalog.setSviesos_srautas(0);
                                } else {
                                    productCatalog.setSviesos_srautas(sviesos_srautas);
                                }
                            }
                            break;
                        case 26:
                            String sviesos_spalvos_temperatura;
                            if (cell.getCellType() == 0) {
                                sviesos_spalvos_temperatura = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                sviesos_spalvos_temperatura = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (sviesos_spalvos_temperatura.equals("")) {
                                productCatalog.setSviesos_spalvos_temperatura(null);
                            } else {
                                productCatalog.setSviesos_spalvos_temperatura(sviesos_spalvos_temperatura);
                            }
                            break;
                        case 27:
                            String laidininkas;
                            if (cell.getCellType() == 0) {
                                laidininkas = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                laidininkas = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (laidininkas.equals("")) {
                                productCatalog.setLaidininkas(null);
                            } else {
                                productCatalog.setLaidininkas(laidininkas);
                            }
                            break;
                        case 28:
                            String laidininkoIzoliacija;
                            if (cell.getCellType() == 0) {
                                laidininkoIzoliacija = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                laidininkoIzoliacija = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (laidininkoIzoliacija.equals("")) {
                                productCatalog.setLaidininkoIzoliacija(null);
                            } else {
                                productCatalog.setLaidininkoIzoliacija(laidininkoIzoliacija);
                            }
                            break;
                        case 29:
                            String darbine_temperatura;
                            if (cell.getCellType() == 0) {
                                darbine_temperatura = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                darbine_temperatura = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (darbine_temperatura.equals("")) {
                                productCatalog.setDarbine_temperatura(null);
                            } else {
                                productCatalog.setDarbine_temperatura(darbine_temperatura);
                            }
                            break;
                        case 30:
                            String max_darbine_temperatura;
                            if (cell.getCellType() == 0) {
                                max_darbine_temperatura = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                max_darbine_temperatura = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (max_darbine_temperatura.equals("")) {
                                productCatalog.setMax_darbine_temperatura(null);
                            } else {
                                productCatalog.setMax_darbine_temperatura(max_darbine_temperatura);
                            }
                            break;
                        case 31:
                            String apsvieta;
                            if (cell.getCellType() == 0) {
                                apsvieta = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                apsvieta = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (apsvieta.equals("")) {
                                productCatalog.setApsvieta(null);
                            } else {
                                productCatalog.setApsvieta(apsvieta);
                            }
                            break;
                        case 32:
                            String apvalkalas;
                            if (cell.getCellType() == 0) {
                                apvalkalas = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                apvalkalas = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (apvalkalas.equals("")) {
                                productCatalog.setApvalkalas(null);
                            } else {
                                productCatalog.setApvalkalas(apvalkalas);
                            }
                            break;
                        case 33:
                            String CPR_klase;
                            if (cell.getCellType() == 0) {
                                CPR_klase = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                CPR_klase = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (CPR_klase.equals("")) {
                                productCatalog.setCpr_klase(null);
                            } else {
                                productCatalog.setCpr_klase(CPR_klase);
                            }
                            break;
                        case 34:
                            String isjungimo_geba;
                            if (cell.getCellType() == 0) {
                                isjungimo_geba = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                isjungimo_geba = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (isjungimo_geba.equals("")) {
                                productCatalog.setIsjungimo_geba(null);
                            } else {
                                productCatalog.setIsjungimo_geba(isjungimo_geba);
                            }
                            break;
                        case 35:
                            String isjungimo_charakteristika;
                            if (cell.getCellType() == 0) {
                                isjungimo_charakteristika = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                isjungimo_charakteristika = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (isjungimo_charakteristika.equals("")) {
                                productCatalog.setIsjungimo_charakteristika(null);
                            } else {
                                productCatalog.setIsjungimo_charakteristika(isjungimo_charakteristika);
                            }
                            break;
                        case 36:
                            String mechaninis_atsparumas;
                            if (cell.getCellType() == 0) {
                                mechaninis_atsparumas = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                mechaninis_atsparumas = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (mechaninis_atsparumas.equals("")) {
                                productCatalog.setMechaninis_atsparumas(null);
                            } else {
                                productCatalog.setMechaninis_atsparumas(mechaninis_atsparumas);
                            }
                            break;
                        case 37:
                            String skerspjuvis_Al;
                            if (cell.getCellType() == 0) {
                                skerspjuvis_Al = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                skerspjuvis_Al = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (skerspjuvis_Al.equals("")) {
                                productCatalog.setSkerspjuvis_Al(null);
                            } else {
                                productCatalog.setSkerspjuvis_Al(skerspjuvis_Al);
                            }
                            break;
                        case 38:
                            String skerspjuvis_Cu;
                            if (cell.getCellType() == 0) {
                                skerspjuvis_Cu = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                skerspjuvis_Cu = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (skerspjuvis_Cu.equals("")) {
                                productCatalog.setSkerspjuvis_Cu(null);
                            } else {
                                productCatalog.setSkerspjuvis_Cu(skerspjuvis_Cu);
                            }
                            break;
                        case 39:
                            String nuotekio_srove;
                            if (cell.getCellType() == 0) {
                                nuotekio_srove = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                nuotekio_srove = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (nuotekio_srove.equals("")) {
                                productCatalog.setNuotekio_srove(null);
                            } else {
                                productCatalog.setNuotekio_srove(nuotekio_srove);
                            }
                            break;
                        case 40:
                            String dydis;
                            if (cell.getCellType() == 0) {
                                dydis = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                dydis = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (dydis.equals("")) {
                                productCatalog.setDydis(null);
                            } else {
                                productCatalog.setDydis(dydis);
                            }
                            break;
                        case 41:
                            String plotas;
                            if (cell.getCellType() == 0) {
                                plotas = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                plotas = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (plotas.equals("")) {
                                productCatalog.setPlotas(null);
                            } else {
                                productCatalog.setPlotas(plotas);
                            }
                            break;
                        case 42:
                            String aptikimoZona;
                            if (cell.getCellType() == 0) {
                                aptikimoZona = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                aptikimoZona = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (aptikimoZona.equals("")) {
                                productCatalog.setAptikimoZona(null);
                            } else {
                                productCatalog.setAptikimoZona(aptikimoZona);
                            }
                            break;
                        case 43:
                            String maxDarbineItampa;
                            if (cell.getCellType() == 0) {
                                maxDarbineItampa = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                maxDarbineItampa = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (maxDarbineItampa.equals("")) {
                                productCatalog.setMaksimaliDarbineItampa(null);
                            } else {
                                productCatalog.setMaksimaliDarbineItampa(maxDarbineItampa);
                            }
                            break;
                        case 44:
                            String iskrovimoSrove820;
                            if (cell.getCellType() == 0) {
                                iskrovimoSrove820 = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                iskrovimoSrove820 = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (iskrovimoSrove820.equals("")) {
                                productCatalog.setIskrovimoSrove820(null);
                            } else {
                                productCatalog.setIskrovimoSrove820(iskrovimoSrove820);
                            }
                            break;
                        case 45:
                            String iskrovimoSrove10350;
                            if (cell.getCellType() == 0) {
                                iskrovimoSrove10350 = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                iskrovimoSrove10350 = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (iskrovimoSrove10350.equals("")) {
                                productCatalog.setIskrovimoSrove10350(null);
                            } else {
                                productCatalog.setIskrovimoSrove10350(iskrovimoSrove10350);
                            }
                            break;
                        case 46:
                            String itamposApsaugosLygis;
                            if (cell.getCellType() == 0) {
                                itamposApsaugosLygis = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                itamposApsaugosLygis = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (itamposApsaugosLygis.equals("")) {
                                productCatalog.setItamposApsaugosLygis(null);
                            } else {
                                productCatalog.setItamposApsaugosLygis(itamposApsaugosLygis);
                            }
                            break;
                        case 47:
                            String kategorija;
                            if (cell.getCellType() == 0) {
                                kategorija = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                kategorija = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (kategorija.equals("")) {
                                productCatalog.setKategorija(null);
                            } else {
                                productCatalog.setKategorija(kategorija);
                            }
                            break;
                        case 48:
                            if (cell.getCellType() == 0) {
                                if (cell.getCellType() == 0) {
                                    Integer cri = (int) cell.getNumericCellValue();
                                    if (cri == null) {
                                        productCatalog.setCRI(0);
                                    } else {
                                        productCatalog.setCRI(cri);
                                    }
                                }
                            }
                            break;
                        case 49:
                            String garantija;
                            if (cell.getCellType() == 0) {
                                garantija = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                garantija = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (garantija.equals("")) {
                                productCatalog.setGarantija(null);
                            } else {
                                productCatalog.setGarantija(garantija);
                            }
                            break;
                        case 50:
                            String sertifikatai;
                            if (cell.getCellType() == 0) {
                                sertifikatai = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                sertifikatai = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (sertifikatai.equals("")) {
                                productCatalog.setSertifikatai(null);
                            } else {
                                productCatalog.setSertifikatai(sertifikatai);
                            }
                            break;
                        case 51:
                            String nemaJungtis;
                            if (cell.getCellType() == 0) {
                                nemaJungtis = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                nemaJungtis = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (nemaJungtis.equals("")) {
                                productCatalog.setNemaJungtis(null);
                            } else {
                                productCatalog.setNemaJungtis(nemaJungtis);
                            }
                            break;
                        case 52:
                            String virsItampiuApsauga;
                            if (cell.getCellType() == 0) {
                                virsItampiuApsauga = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                virsItampiuApsauga = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (virsItampiuApsauga.equals("")) {
                                productCatalog.setVirsitampiuApsauga(null);
                            } else {
                                productCatalog.setVirsitampiuApsauga(virsItampiuApsauga);
                            }
                            break;
                        case 53:
                            String ilgaamziskumas;
                            if (cell.getCellType() == 0) {
                                ilgaamziskumas = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                ilgaamziskumas = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (ilgaamziskumas.equals("")) {
                                productCatalog.setIlgaamziskumas(null);
                            } else {
                                productCatalog.setIlgaamziskumas(ilgaamziskumas);
                            }
                            break;
                        case 54:
                            String korpusoAtidarymas;
                            if (cell.getCellType() == 0) {
                                korpusoAtidarymas = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                korpusoAtidarymas = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (korpusoAtidarymas.equals("")) {
                                productCatalog.setKorpusoAtidarymas(null);
                            } else {
                                productCatalog.setKorpusoAtidarymas(korpusoAtidarymas);
                            }
                            break;
                        case 55:
                            String optinesIrElektrinesDaliesPertvara;
                            if (cell.getCellType() == 0) {
                                optinesIrElektrinesDaliesPertvara = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                optinesIrElektrinesDaliesPertvara = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (optinesIrElektrinesDaliesPertvara.equals("")) {
                                productCatalog.setOptinesIrElektrinesDaliesPertvara(null);
                            } else {
                                productCatalog.setOptinesIrElektrinesDaliesPertvara(optinesIrElektrinesDaliesPertvara);
                            }
                            break;
                        case 56:
                            String valdymas;
                            if (cell.getCellType() == 0) {
                                valdymas = String.valueOf(cell.getNumericCellValue());
                            } else if (cell.getCellType() == 1) {
                                valdymas = cell.getStringCellValue();
                            } else {
                                break;
                            }
                            if (valdymas.equals("")) {
                                productCatalog.setValdymas(null);
                            } else {
                                productCatalog.setValdymas(valdymas);
                            }
                            break;
                        case 57:
                            if (cell.getCellType() == 0) {
                                Integer apatinisDiametras = (int) cell.getNumericCellValue();
                                if (apatinisDiametras == null) {
                                    productCatalog.setApatinisDiametras(0);
                                } else {
                                    productCatalog.setApatinisDiametras(apatinisDiametras);
                                }
                            }
                            break;
                        case 58:
                            if (cell.getCellType() == 0) {
                                Integer virsutinisDiametras = (int) cell.getNumericCellValue();
                                if (virsutinisDiametras == null) {
                                    productCatalog.setVirsutinisDiametras(0);
                                } else {
                                    productCatalog.setVirsutinisDiametras(virsutinisDiametras);
                                }
                            }
                            break;
                        case 59:
                            if (cell.getCellType() == 0) {
                                Integer gembesDiametras = (int) cell.getNumericCellValue();
                                if (gembesDiametras == null) {
                                    productCatalog.setGembesDiametras(0);
                                } else {
                                    productCatalog.setGembesDiametras(gembesDiametras);
                                }
                            }
                            break;
                    }

                }
                if (productCatalog.getCatalogNo().isEmpty() && productCatalog.getSymbol().isEmpty() && productCatalog.getGroupId() == 0) {
                    break;
                }
                products.add(productCatalog);

            }

        } catch (
                Exception e) {
            e.getStackTrace();
            System.out.println("Failo nuskaitymo klaida " + e);
            System.out.println("Failo nuskaitymo klaida " + e);
        } finally {
            workbook.close();
            inputStream.close();
        }
        return products;
    }
}
