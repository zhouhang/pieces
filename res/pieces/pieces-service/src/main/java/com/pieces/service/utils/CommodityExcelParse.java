package com.pieces.service.utils;

import com.pieces.dao.model.Commodity;
import com.pieces.dao.vo.CommodityVo;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;

/**
 * Created by wangbin on 2016/8/26.
 */
public class CommodityExcelParse {

    public static List<CommodityVo> parseEnquiryXLS(InputStream inp) throws IOException, InvalidFormatException {

        List<CommodityVo> list = new ArrayList<>();

        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);

        // Decide which rows to process
        int rowStart = Math.max(sheet.getFirstRowNum(), 1);
        int rowEnd = sheet.getLastRowNum();

        for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
            Row r = sheet.getRow(rowNum);
            if (r == null) {
                continue;
            }
            CommodityVo commoditys = new CommodityVo();

            for (int cn = 0; cn < 8; cn++) {
                // 0商品名称(必填)	1切制规格（必填）	2等级（必填）	3产地（数量）	4数量（必填）	5期望单价（元/公斤）	6期望交期（必填）
                Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                if (c == null) {
                    // The spreadsheet is empty in this cell
                    continue;
                } else {
                    try {
                        switch (cn) {
                            case 0:
                                commoditys.setCategoryName(getCellValue(c));
                                break;
                            case 1:
                                commoditys.setBreedName(getCellValue(c));
                                break;
                            case 2:
                                commoditys.setName(getCellValue(c));
                                break;
                            case 3:
                                commoditys.setSpec(getCellValue(c));
                                break;
                            case 4:
                                commoditys.setLevel(getCellValue(c));
                                break;
                            case 5:
                                commoditys.setExterior(getCellValue(c));
                                break;
//                            case 6:
//                                commoditys.setFactoryStr(getCellValue(c));
//                                break;
                            case 7:
                                commoditys.setExecutiveStandard(getCellValue(c));

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            list.add(commoditys);
        }

        inp.close();
        return list;
    }


    private static String getCellValue(Cell c) {
        String value = "";
        // Do something useful with the cell's contents
        switch (c.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = c.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(c)) {
                    value =String.valueOf(c.getDateCellValue().getTime());
                } else {
                    value = String.valueOf(c.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = String.valueOf(c.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = c.getCellFormula();
                break;
            default:
        }
        return value;
    }


    public static void main(String[] args) throws IOException, InvalidFormatException {
        File file = new File("G:\\Downloads\\产品规格等级手册20160524.xls");

        List<CommodityVo> commodityVos =   CommodityExcelParse.parseEnquiryXLS(new FileInputStream(file));

        System.out.println(commodityVos);
    }

}
