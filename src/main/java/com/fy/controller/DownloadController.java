package com.fy.controller;

import com.fy.pojo.Order;
import com.fy.pojo.User;
import com.fy.service.OrderService;
import com.fy.tools.POIUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/9/15.
 */
@Controller
public class DownloadController {
   @Autowired
   private OrderService orderService;

    @RequestMapping("download")
    //@ResponseBody
    public ResponseEntity<byte[]> download(HttpServletResponse response) throws Exception {
        // ServletOutputStream stream = response.getOutputStream();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String fname = sdf.format(new Date())+".xls";

        //response.setHeader("Content-Disposition","attachment;filename="+fname);

        Workbook wb=getWorkbook();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        wb.write(os);

        byte[] body = null;

        body=os.toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attchement;filename=" + fname);
        HttpStatus statusCode = HttpStatus.OK;
        ResponseEntity<byte[]> entity=new ResponseEntity<byte[]>(body,headers,statusCode);
        return entity;
    }

    private Workbook getWorkbook() throws Exception {
        Workbook wb=new HSSFWorkbook();
        List<Order> list=orderService.findExcel();
        List<Order>[] lists=POIUtils.split(list);

        createSheet(wb, lists[0], "审核中订单", "cell_yellow");
        createSheet(wb, lists[1], "审核未通过订单", "cell_red");
        createSheet(wb, lists[2], "已入住订单", "cell_green");
        createSheet(wb, lists[3], "已退房订单", "cell_g");
        createSheet(wb, lists[4], "已取消订单", "cell_g");

        String[] titles = {
                "序号", "房屋ID", "用户ID", "Days", "入住时间", "退房时间","金额"};

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");




        String[][] data=POIUtils.toArray(list);

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet("总订单报表");

        //turn off gridlines
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);

        //the following three statements are required only for HSSF
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);

        //the header row: centered text in 48pt font
        Row headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(12.75f);
        for (int i = 0; i < titles.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(styles.get("header"));
        }
        //columns for 11 weeks starting from 9-6
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        calendar.setTime(fmt.parse("2017-6-9"));
        calendar.set(Calendar.YEAR, year);

        //freeze the first row
        sheet.createFreezePane(0, 1);

        Row row;
        Cell cell;

        int rownum = 1;
        for (int i = 0; i < data.length; i++, rownum++) {
            row = sheet.createRow(rownum);
            if(data[i] == null) continue;

            for (int j = 0; j < data[i].length; j++) {
                cell = row.createCell(j);
                String styleName;
                boolean isHeader = i == 0;
                switch(j){
                    case 0:
                        if(isHeader) {
                            styleName = "cell_b";
                            cell.setCellValue(Double.parseDouble(data[i][j]));
                        } else {
                            styleName = "cell_normal";
                            cell.setCellValue(data[i][j]);
                        }
                        break;
                    case 1:
                        if(isHeader) {
                            styleName = i == 0 ? "cell_h" : "cell_bb";
                        } else {
                            styleName = "cell_indented";
                        }
                        cell.setCellValue(data[i][j]);
                        break;
                    case 2:
                        styleName = isHeader ? "cell_b" : "cell_normal";
                        cell.setCellValue(data[i][j]);
                        break;
                    case 3:
                        styleName = isHeader ? "cell_b_centered" : "cell_normal_centered";
                        cell.setCellValue(Integer.parseInt(data[i][j]));
                        break;
                    case 4: {
                        calendar.setTime(fmt.parse(data[i][j]));
                        calendar.set(Calendar.YEAR, year);
                        cell.setCellValue(calendar);
                        styleName = isHeader ? "cell_b_date" : "cell_normal_date";
                        break;
                    }
                    case 5: {
                        calendar.setTime(fmt.parse(data[i][j]));
                        calendar.set(Calendar.YEAR, year);
                        cell.setCellValue(calendar);
                        styleName = isHeader ? "cell_bg" : "cell_g";
                        break;
                    }
                    case 6: {
                        styleName = isHeader ? "cell_b" : "cell_normal";
                        cell.setCellValue(data[i][j]);
                        break;
                    }
                    default:
                        styleName = data[i][j] != null ? "cell_blue" : "cell_normal";
                }

                cell.setCellStyle(styles.get(styleName));
            }
        }

        //group rows for each phase, row numbers are 0-based
        sheet.groupRow(4, 6);
        sheet.groupRow(9, 13);
        sheet.groupRow(16, 18);

        //set column widths, the width is measured in units of 1/256th of a character width
        sheet.setColumnWidth(0, 256*6);
        sheet.setColumnWidth(1, 256*33);
        sheet.setColumnWidth(2, 256*20);
        sheet.setColumnWidth(4, 256*13);
        sheet.setColumnWidth(5, 256*13);

        //sheet.setZoom(75); //75% scale
        return  wb;
    }

    private static Map<String, CellStyle> createStyles(Workbook wb){

        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        DataFormat df = wb.createDataFormat();

        CellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setFont(headerFont);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("header_date", style);

        Font font1 = wb.createFont();
        font1.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font1);
        styles.put("cell_b", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font1);
        styles.put("cell_b_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_b_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_g", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_green", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.ROSE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_red", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_yellow", style);





        Font font2 = wb.createFont();
        font2.setColor(IndexedColors.BLUE.getIndex());
        font2.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setFont(font2);
        styles.put("cell_bb", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_bg", style);

        Font font3 = wb.createFont();
        font3.setFontHeightInPoints((short)14);
        font3.setColor(IndexedColors.DARK_BLUE.getIndex());
        font3.setBold(true);
        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setFont(font3);
        style.setWrapText(true);
        styles.put("cell_h", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setWrapText(true);
        styles.put("cell_normal", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setWrapText(true);
        styles.put("cell_normal_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setWrapText(true);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_normal_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setIndention((short)1);
        style.setWrapText(true);
        styles.put("cell_indented", style);

        style = createBorderedStyle(wb);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        styles.put("cell_blue", style);

        return styles;
    }

    private static CellStyle createBorderedStyle(Workbook wb){
        BorderStyle thin = BorderStyle.THIN;
        short black = IndexedColors.BLACK.getIndex();

        CellStyle style = wb.createCellStyle();
        style.setBorderRight(thin);
        style.setRightBorderColor(black);
        style.setBorderBottom(thin);
        style.setBottomBorderColor(black);
        style.setBorderLeft(thin);
        style.setLeftBorderColor(black);
        style.setBorderTop(thin);
        style.setTopBorderColor(black);
        return style;
    }



    public  static List<Order> getList(){
        List<Order> list=new ArrayList<Order>();
        Date in=new Date(2017, 3, 5);
        Date out=new Date(2017, 5, 6);
        for (int i = 0; i < 5; i++) {
            Order order=new Order();

            order.setHhOrdersIntime(in);
            order.setHhOrdersOuttime(out);
            order.setHhOrdersPrice(50.0);
            //System.out.println(order.toString());
            list.add(order);
        }


        return list;
    }

    private static Sheet createSheet(Workbook wb,List<Order> list,String sheetName,String instylename) throws ParseException{
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        String[] titles = {
                "序号", "房屋地址", "住户姓名", "入住天数", "入住时间", "退房时间","金额"};



        String[][] data= POIUtils.toArray(list);

        Map<String, CellStyle> styles = createStyles(wb);

        Sheet sheet = wb.createSheet(sheetName);

        //turn off gridlines
        sheet.setDisplayGridlines(false);
        sheet.setPrintGridlines(false);
        sheet.setFitToPage(true);
        sheet.setHorizontallyCenter(true);
        PrintSetup printSetup = sheet.getPrintSetup();
        printSetup.setLandscape(true);

        //the following three statements are required only for HSSF
        sheet.setAutobreaks(true);
        printSetup.setFitHeight((short)1);
        printSetup.setFitWidth((short)1);

        CellRangeAddress cra=new CellRangeAddress(0, 0, 0, 6);
        sheet.addMergedRegion(cra);

        Row headerRow = sheet.createRow(0);
        Cell cell=headerRow.createCell(0);
        cell.setCellValue("House in house"+sheetName);
        cell.setCellStyle(styles.get("cell_h"));

        headerRow = sheet.createRow(1);
        headerRow.setHeightInPoints(12.75f);
        for (int i = 0; i < titles.length; i++) {
            cell = headerRow.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(styles.get("header"));
        }

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);

        calendar.setTime(fmt.parse("2017-6-9"));
        calendar.set(Calendar.YEAR, year);
        Row row;
        int rownum=2;
        for (int i = 0; i < data.length; i++, rownum++) {
            row = sheet.createRow(rownum);
            if(data[i] == null) continue;

            for (int j = 0; j < data[i].length; j++) {
                cell = row.createCell(j);
                String styleName;
                switch(j){
                    case 5:
                        calendar.setTime(fmt.parse(data[i][j]));
                        calendar.set(Calendar.YEAR, year);
                        cell.setCellValue(calendar);
                        styleName = "cell_g";
                        break;
                    case 4:
                        calendar.setTime(fmt.parse(data[i][j]));
                        calendar.set(Calendar.YEAR, year);
                        cell.setCellValue(calendar);
                        styleName =  instylename;
                        break;

                    default:
                        cell.setCellValue(data[i][j]);
                        styleName=instylename;
                        break;

                }
                cell.setCellStyle(styles.get(styleName));
            }

        }

        sheet.setColumnWidth(0, 256*6);
        sheet.setColumnWidth(1, 256*33);
        sheet.setColumnWidth(2, 256*20);
        sheet.setColumnWidth(4, 256*13);
        sheet.setColumnWidth(5, 256*13);

        return sheet;

    }
}
