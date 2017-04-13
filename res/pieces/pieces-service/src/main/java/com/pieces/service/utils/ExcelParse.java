package com.pieces.service.utils;

import com.pieces.dao.enums.OrderEnum;
import com.pieces.dao.model.EnquiryCommoditys;
import com.pieces.dao.model.OrderCommodity;
import com.pieces.dao.vo.EnquiryBillsVo;
import com.pieces.dao.vo.EnquiryCommoditysVo;
import com.pieces.dao.vo.OrderFormVo;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
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
                // 0商品名称(必填)	1切制规格（必填）片型	2等级（必填）	3产地
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
                                commoditys.setMyPrice(Double.valueOf(getCellValue(c)));
                                break;
                           default:
                               break;
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
     * @param list
     * @return
     */
    public static Workbook  exportEnquiryInfo( List<EnquiryCommoditys> list){
        Workbook wb = new HSSFWorkbook();
        Sheet s = wb.createSheet();
        Row r = null;
        Cell c = null;
        String[] titles = null;
        wb.setSheetName(0, "报价表");
//        // 数据有效性约束
//        CellRangeAddressList addressList = new CellRangeAddressList(
//                0, 65535, 5, 5);
//        DVConstraint dvConstraint = DVConstraint.createNumericConstraint(
//                DVConstraint.ValidationType.INTEGER,
//                DVConstraint.OperatorType.BETWEEN, "0", "100000");
//        DataValidation dataValidation = new HSSFDataValidation
//                (addressList, dvConstraint);
//        s.addValidationData(dataValidation);

        // 设置询价单信息 cell title
        titles = new String[]{"ID", "商品名称", "片型", "规格等级", "产地", "单价(元/公斤)"};
        s.setColumnWidth(0, 5 * 256);
        s.setColumnWidth(1, 25 * 256);
        s.setColumnWidth(2, 10 * 256);
        s.setColumnWidth(3, 15 * 256);
        s.setColumnWidth(4, 20 * 256);
        s.setColumnWidth(5, 10 * 256);

        r = s.createRow(0);
        for (int cellnum = 0; cellnum < titles.length; cellnum++) {
            c = r.createCell(cellnum);
            c.setCellValue(titles[cellnum]);
        }

        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setLocked(true);

        DataFormat format= wb.createDataFormat();
        CellStyle numberStyle = wb.createCellStyle();
        numberStyle.setDataFormat(format.getFormat("0.00"));

        for (int rownum = (short) 1; rownum < list.size()+1; rownum++) {

            r = s.createRow(rownum);
            EnquiryCommoditys commodity = list.get(rownum-1);

            c = r.createCell(0);
            c.setCellValue(commodity.getId());
            c.setCellStyle(cellStyle);
            c = r.createCell(1);
            c.setCellValue(commodity.getCommodityName());
            c.setCellStyle(cellStyle);
            c = r.createCell(2);
            c.setCellValue(commodity.getSpecs());
            c.setCellStyle(cellStyle);
            c = r.createCell(3);
            c.setCellValue(commodity.getLevel());
            c.setCellStyle(cellStyle);
            c = r.createCell(4);
            c.setCellValue(commodity.getOrigin());
            c.setCellStyle(cellStyle);
            c = r.createCell(5);
            c.setCellStyle(numberStyle);
            c.setCellValue(commodity.getMyPrice()==null?0:commodity.getMyPrice());
        }
//        Sheet s1 = wb.createSheet();
//        wb.setSheetName(1, "报价表");
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
                //"0 ID", "1 商品名称", "2 切制规格 片型", "3 规格等级", "4 产地", "5 单价(元/公斤)"
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
                            case 5:
                                commoditys.setMyPrice(Double.valueOf(getCellValue(c)));
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

    public static  List<EnquiryCommoditys> importQuoteInfo(InputStream inp) throws IOException, InvalidFormatException {
        Workbook wb = WorkbookFactory.create(inp);
        Sheet sheet = wb.getSheetAt(0);

        // Decide which rows to process
        int rowStart = Math.max(sheet.getFirstRowNum(), 1);
        int rowEnd = sheet.getLastRowNum();
        List<EnquiryCommoditys> enquiryCommodityses=new ArrayList<EnquiryCommoditys>();
        for (int rowNum = rowStart; rowNum <= rowEnd; rowNum++) {
            Row r = sheet.getRow(rowNum);
            if (r == null) {
                continue;
            }
            EnquiryCommoditys commoditys = new EnquiryCommoditys();

            for (int cn = 0; cn <= 10; cn++) {
                //"0 ID", "1 商品名称", "2 切制规格", "3 规格等级", "4 产地", "5 单价(元/公斤)"
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
                            case 1:
                                commoditys.setCommodityName(getCellValue(c));
                                break;
                            case 2:
                                commoditys.setSpecs(getCellValue(c));
                                break;
                            case 3:
                                commoditys.setLevel(getCellValue(c));
                                break;
                            case 4:
                                commoditys.setOrigin(getCellValue(c));
                                break;
                            case 5:
                                commoditys.setMyPrice(Double.valueOf(getCellValue(c)));
                                break;
                            default:
                                break;

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        logger.info("importQuoteInfo:" + cn +":", e.getMessage());
                    }
                }
            }
            enquiryCommodityses.add(commoditys);
        }
        inp.close();
        return enquiryCommodityses;
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
                // TODO: 针对数字型 公式
                BigDecimal bigDecimal = new BigDecimal(c.getNumericCellValue());
                value = bigDecimal.toString();
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
    public static void returnExcel(HttpServletResponse response, HttpServletRequest request, Workbook workbook, String fileName) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
            workbook.write(os);
            byte[] content = os.toByteArray();
            InputStream is = new ByteArrayInputStream(content);
            // 设置response参数，可以打开下载页面
            response.reset();
            response.setContentType("application/octet-stream");

            String name;
            if (request.getHeader("User-Agent").toUpperCase().indexOf("MSIE") > 0 ||
                    request.getHeader("User-Agent").toUpperCase().indexOf("TRIDENT") > 0) {
                name = URLEncoder.encode(fileName, "UTF-8");
            } else {
                name = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
            }

            response.setHeader("Content-disposition", "attachment;filename="
                    + name + ".xls");
            ServletOutputStream out = response.getOutputStream();
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

    /**
     * 导出订单详情
     * @param orderInfo
     * @return
     */
   public static Workbook exportOrderInfo(OrderFormVo orderInfo) {
       Workbook wb = new HSSFWorkbook();
       Sheet s = wb.createSheet();
       Row r;
       Cell c;
       Font font = wb.createFont();
       font.setFontName("宋体");
       font.setFontHeightInPoints((short) 12);//设置字体

       CellStyle h2 = wb.createCellStyle();
//       h2.setFillPattern(CellStyle.FINE_DOTS);
//       h2.setFillBackgroundColor(HSSFColor.SKY_BLUE.index);
       h2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       h2.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
       h2.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);

       h2.setFont(font);
       h2.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
       h2.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
       h2.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
       h2.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
//       h2.setBottomBorderColor();

       CellStyle h1 = wb.createCellStyle();
       h1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
       h1.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
       h1.setFillBackgroundColor(HSSFColor.LIGHT_BLUE.index);
       h1.setFont(font);
       h1.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
       h1.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
       h1.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
       h1.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
       h1.setAlignment(HSSFCellStyle.ALIGN_CENTER);

       CellStyle p = wb.createCellStyle();
       p.setFillPattern((short) 0);
       p.setFont(font);
       p.setBorderBottom(HSSFCellStyle.BORDER_THIN); //下边框
       p.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
       p.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
       p.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
       p.setDataFormat((short)0x31);

       s.setColumnWidth(0, 15 * 256);
       s.setColumnWidth(1, 15 * 256);
       s.setColumnWidth(2, 15 * 256);
       s.setColumnWidth(3, 15 * 256);
       s.setColumnWidth(4, 15 * 256);
       s.setColumnWidth(5, 15 * 256);
       s.setColumnWidth(6, 15 * 256);
       s.setColumnWidth(7, 15 * 256);

       s.setDisplayGridlines(true);
       s.setDefaultRowHeightInPoints(20);

       wb.setSheetName(0, "订单详情");
       r = s.createRow((short) 1);
       setRowCellStyle(r, 1,7,p);
       c = r.getCell((short) 1);
       c.setCellValue("订单号");
       c.setCellStyle(h2);
       c = r.getCell(2);
       c.setCellValue(orderInfo.getCode());
       s.addMergedRegion(new CellRangeAddress(1, (short) 1, 2, (short) 7));//指定合并区域

       r = s.createRow(2);
       setRowCellStyle(r, 1,7,p);
       c = r.getCell((short) 1);
       c.setCellValue("下单日期");
       c.setCellStyle(h2);
       s.addMergedRegion(new CellRangeAddress(2, (short) 2, 2, (short) 7));//指定合并区域
       c = r.getCell(2);
       c.setCellValue(DateFormatUtils.format(orderInfo.getCreaterTime(),"yyyy-MM-dd"));


       r = s.createRow(3);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(3, (short) 3, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(h1);
       c.setCellValue("客户信息");

       r = s.createRow(4);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(4, (short) 4, 2, (short) 3));//指定合并区域
       c = r.getCell(1);
       c.setCellValue("用药单位");
       c = r.getCell(2);
       c.setCellValue(orderInfo.getUser().getCompanyFullName());
       c = r.getCell(4);
       c.setCellValue("联系人");
       c = r.getCell(5);
       c.setCellValue(orderInfo.getUser().getContactName());
       c = r.getCell(6);
       c.setCellStyle(p);
       c.setCellValue("联系电话");
       c = r.getCell(7);
       c.setCellStyle(p);
       c.setCellValue(orderInfo.getUser().getContactMobile());

       r = s.createRow(5);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(5, (short) 5, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(p);

       r = s.createRow(6);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(6, (short) 6, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(h1);
       c.setCellValue("配送信息");

       r = s.createRow(7);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(7, (short) 7, 2, (short) 3));//指定合并区域
       c = r.getCell(1);
       c.setCellValue("收货地址");
       c = r.getCell(2);
       c.setCellValue(orderInfo.getAddress().getArea()+orderInfo.getAddress().getDetail());
       c = r.getCell(4);
       c.setCellValue("收货人");
       c = r.getCell(5);
       c.setCellValue(orderInfo.getAddress().getConsignee());
       c = r.getCell(6);
       c.setCellValue("联系电话");
       c = r.getCell(7);
       c.setCellValue(orderInfo.getAddress().getTel());

       r = s.createRow(8);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(8, (short) 8, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(p);

       r = s.createRow(9);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(9, (short) 9, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(h1);
       c.setCellValue("商品清单");

       r = s.createRow(10);
       setRowCellStyle(r, 1,7,p);
       c = r.getCell(1);
       c.setCellValue("商品名称");
       c = r.getCell(2);
       c.setCellValue("片型");
       c = r.getCell(3);
       c.setCellValue("规格等级");
       c = r.getCell(4);
       c.setCellValue("产地");
       c = r.getCell(5);
       c.setCellValue("数量(公斤)");
       c = r.getCell(6);
       c.setCellValue("销售价(元/公斤)");
       if (orderInfo.getAgentId()!= null) {
           c = r.getCell(7);
           c.setCellValue("开票价(元/公斤)"); // 根据身份设置
       }
       int nextRow=11;
       // for 循环 来输出商品值
       for (OrderCommodity commodity:orderInfo.getCommodities()) {
           r = s.createRow(nextRow++);
           setRowCellStyle(r, 1,7,p);
           c = r.getCell(1);
           c.setCellValue(commodity.getName());
           c = r.getCell(2);
           c.setCellValue(commodity.getSpec());
           c = r.getCell(3);
           c.setCellValue(commodity.getLevel());
           c = r.getCell(4);
           c.setCellValue(commodity.getOriginOf());
           c = r.getCell(5);
           c.setCellValue(commodity.getAmount());
           c = r.getCell(6);
           c.setCellValue(commodity.getGuidePrice());
           if (orderInfo.getAgentId()!= null) {
               c = r.getCell(7);
               c.setCellValue(commodity.getPrice()); // 根据身份设置
           }
       }

       r = s.createRow(nextRow++);
       setRowCellStyle(r, 1,7,p);

       if(orderInfo.getStatus().equals(OrderEnum.COMPLETE.getValue())){
           c = r.getCell(6);
           c.setCellValue("订单总额：");
           if (orderInfo.getAgentId()== null) {
               c = r.getCell(7);
               c.setCellValue(orderInfo.getAmountsPayable());

           }
           else{
               c = r.getCell(7);
               c.setCellValue(orderInfo.getDeposit());

           }

           r = s.createRow(nextRow++);
           setRowCellStyle(r, 1,7,p);
           c = r.getCell(6);
           c.setCellValue("高开价总额：");
           if (orderInfo.getAgentId()!= null) {
               c = r.getCell(7);
               c.setCellValue(orderInfo.getAmountsPayable());
           }

       }

       r = s.createRow(nextRow++);
       setRowCellStyle(r, 1,7,p);

       r = s.createRow(nextRow);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(nextRow, (short) nextRow, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(h1);
       c.setCellValue("发票信息");

       r = s.createRow(++nextRow);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(nextRow, (short) nextRow, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(p);
       if (orderInfo.getInvoice()!= null) {
           String invoice = "";
           invoice+=orderInfo.getInvoice().getTypeText()+
                   orderInfo.getInvoice().getName();
           c.setCellValue(invoice);
       }

       r = s.createRow(++nextRow);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(nextRow, (short) nextRow, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(h1);
       c.setCellValue("备注");

       r = s.createRow(++nextRow);
       setRowCellStyle(r, 1,7,p);
       s.addMergedRegion(new CellRangeAddress(nextRow, (short) nextRow, 1, (short) 7));//指定合并区域
       c = r.getCell(1);
       c.setCellStyle(p);
       c.setCellValue(orderInfo.getRemark());

//       File file = new File("/Users/kevin1/Downloads/订单详情.xls");
//       try {
//           file.createNewFile();
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
//       try {
//           FileOutputStream os = new FileOutputStream(file);
//           wb.write(os);
//       } catch (FileNotFoundException e) {
//           e.printStackTrace();
//       } catch (IOException e) {
//           e.printStackTrace();
//       }
       return wb;
   }

    /**
     * 给一列设置默认格式
     * @param r
     * @param start
     * @param end
     */
    private static void setRowCellStyle(Row r, int start, int end, CellStyle style){
        for (int i = start;i<= end;i++) {
            Cell c = r.createCell((short) i);
            c.setCellStyle(style);
        }
    }

    public static void main(String[] args) {
        ExcelParse.exportOrderInfo(null);
    }
}
