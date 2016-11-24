package com.pieces.service.utils;

import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.vo.EnquiryBillsVo;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Author: koabs
 * 7/28/16.
 * 解析上传的报价excel文件
 */
public class ExcelParse {

    static Logger logger = LoggerFactory.getLogger(ExcelParse.class);

    public static List<EnquiryCommoditys> parseEnquiryXLS(InputStream inp) throws IOException, InvalidFormatException {

        List<EnquiryCommoditys> list = new ArrayList<>();

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
            EnquiryCommoditys commoditys = new EnquiryCommoditys();

            for (int cn = 0; cn < 7; cn++) {
                // 0商品名称(必填)	1切制规格（必填）	2等级（必填）	3产地（数量）	4数量（必填）	5期望单价（元/公斤）	6期望交期（必填）
                Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                if (c == null) {
                    // The spreadsheet is empty in this cell
                    continue;
                } else {
                    try {
                        switch (cn) {
                            case 0:
                                commoditys.setCommodityName(getCellValue(c));
                                break;
                            case 1:
                                commoditys.setSpecs(getCellValue(c));
                                break;
                            case 2:
                                commoditys.setLevel(getCellValue(c));
                                break;
                            case 3:
                                commoditys.setOrigin(getCellValue(c));
                                break;
                            case 4:
                                commoditys.setAmount(Double.valueOf(getCellValue(c)).intValue());
                                break;
                            case 5:
                                commoditys.setExpectPrice(Double.valueOf(getCellValue(c)));
                                break;
                            case 6:
                                Date date = new Date(Double.valueOf(getCellValue(c)).longValue());
                                commoditys.setExpectDate(date);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        //TODO: continue
                    }


                }
            }

            if(commoditys.getCommodityName()!=null){
                list.add(commoditys);
            }
        }

        inp.close();
        return list;
    }

    /**
     * 导出询价信息excel
     * @param enquiryBillsVo
     * @return
     */
    public static Workbook  exportEnquiryInfo(EnquiryBillsVo enquiryBillsVo){
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet();
        Row r = null;
        Cell c = null;
        String[] titles = null;
        wb.setSheetName(0, "询价商品");
        // 设置询价单信息 cell title
        titles = new String[]{"ID", "商品名称", "切制规格", "规格等级", "产地", "数量(公斤)", "期望单价(元/公斤)",
                "期望交货日期", "裸价(元/公斤)", "报价有效期"};
        s.setColumnWidth(0, 5 * 256);
        s.setColumnWidth(1, 25 * 256);
        s.setColumnWidth(2, 10 * 256);
        s.setColumnWidth(3, 15 * 256);
        s.setColumnWidth(4, 20 * 256);
        s.setColumnWidth(5, 10 * 256);
        s.setColumnWidth(6, 12 * 256);
        s.setColumnWidth(7, 15 * 256);
        s.setColumnWidth(8, 15 * 256);
        s.setColumnWidth(9, 15 * 256);
        r = s.createRow(0);
        for (int cellnum = 0; cellnum < titles.length; cellnum++) {
            c = r.createCell(cellnum);
            c.setCellValue(titles[cellnum]);
        }

        CreationHelper createHelper = wb.getCreationHelper();
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-MM-dd"));

        List<EnquiryCommoditys> list = enquiryBillsVo.getEnquiryCommoditys();
        for (int rownum = (short) 1; rownum < list.size()+1; rownum++) {

            r = s.createRow(rownum);
            EnquiryCommoditys commodity = list.get(rownum-1);

            c = r.createCell(0);
            c.setCellValue(commodity.getId());
            c = r.createCell(1);
            c.setCellValue(commodity.getCommodityName());
            c = r.createCell(2);
            c.setCellValue(commodity.getSpecs());
            c = r.createCell(3);
            c.setCellValue(commodity.getLevel());
            c = r.createCell(4);
            c.setCellValue(commodity.getOrigin());
            c = r.createCell(5);
            c.setCellValue(commodity.getAmount());
            c = r.createCell(6);
            c.setCellValue(commodity.getExpectPrice());
            c = r.createCell(7);
            c.setCellValue(commodity.getExpectDate());
            c.setCellStyle(cellStyle);
        }
        Sheet s1 = wb.createSheet();
        wb.setSheetName(1, "询价单详情");
        return wb;
    }

    /**
     * 导入询价信息
     * @param inp
     * @return
     */
    public static Map<Integer,EnquiryCommoditys> importEnquiryInfo(InputStream inp) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);

        // Decide which rows to process
        int rowStart = Math.max(sheet.getFirstRowNum(), 1);
        int rowEnd = sheet.getLastRowNum();
        Map<Integer,EnquiryCommoditys> map = new HashMap<>();
        for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
            Row r = sheet.getRow(rowNum);
            if (r == null) {
                continue;
            }
            EnquiryCommoditys commoditys = new EnquiryCommoditys();

            for (int cn = 0; cn <= 10; cn++) {
                //"0 ID", "1 商品名称", "2 切制规格", "3 规格等级", "4 产地", "5 数量(公斤)", "6 期望单价(元/公斤)",
                //        "7 期望交货日期", "8 裸价(元/公斤)", "9 报价有效期"
                Cell c = r.getCell(cn, Row.RETURN_BLANK_AS_NULL);
                if (c == null) {
                    // The spreadsheet is empty in this cell
                    continue;
                } else {
                    try {
                        switch (cn) {
                            case 0:
                                commoditys.setId(Integer.valueOf(getCellValue(c)));
                                break;
                            case 8:
                                commoditys.setMyPrice(Double.valueOf(getCellValue(c)));
                                break;
                            case 9:
                                Date date = new Date(Double.valueOf(getCellValue(c)).longValue());
                                commoditys.setExpireDate(date);
                                break;
                            default:
                                break;

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.info("importEnquiryInfo:" + cn +":", e.getMessage());
                        //TODO: continue
                    }
                }
            }
            map.put(commoditys.getId(), commoditys);
        }
        inp.close();
        return map;
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
                    value = String.valueOf(c.getDateCellValue().getTime());
                } else {
                    BigDecimal bigDecimal = new BigDecimal(c.getNumericCellValue());
                    value = bigDecimal.toString();
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

    /**
     * 返回excel
     * @param response
     * @param workbook
     * @param fileName
     */
    public static void returnExcel(HttpServletResponse response, Workbook workbook, String fileName) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            workbook.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/octet-stream");
            String name = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="
                    + name + ".xls");
            ServletOutputStream out = null;
            out = response.getOutputStream();
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                bis = new BufferedInputStream(is);
                bos = new BufferedOutputStream(out);
                byte[] buff = new byte[2048];
                int bytesRead;
                // Simple read/write loop.
                while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                    bos.write(buff, 0, bytesRead);
                }

                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null)
                    bis.close();
                if (bos != null)
                    bos.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
