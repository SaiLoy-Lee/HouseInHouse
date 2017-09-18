package com.fy.controller;

import com.fy.pojo.Order;
import com.fy.pojo.User;
import com.fy.service.OrderService;
import com.fy.tools.POIUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
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

    @RequestMapping("/personal/order/download")
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
        createSheet(wb, list,"总订单报表","cell_blug");

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
        style.setAlignment(HorizontalAlignment.RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        styles.put("cell_g_mon", style);



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
        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        styles.put("cell_blug", style);



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
        cell.setCellValue("House In House"+sheetName);
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
        int i=0;
        for (; i < data.length; i++, rownum++) {
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
                    case 6:
                        cell.setCellValue(Double.parseDouble(data[i][j]));
                        styleName = "cell_g_mon";
                        break;

                    default:
                        cell.setCellValue(data[i][j]);
                        styleName=instylename;
                        break;

                }
                cell.setCellStyle(styles.get(styleName));
            }

        }
        row=sheet.createRow(rownum);
        cell=row.createCell(5);
        cell.setCellValue("总计");
        cell.setCellStyle(styles.get("cell_g"));

        cell=row.createCell(6);
        //=SUM(G169:G171)
        cell.setCellFormula("SUM(G3:G"+rownum+")");
        cell.setCellStyle(styles.get("cell_g_mon"));

        sheet.setColumnWidth(0, 256*6);
        sheet.setColumnWidth(1, 256*33);
        sheet.setColumnWidth(2, 256*20);
        sheet.setColumnWidth(4, 256*13);
        sheet.setColumnWidth(5, 256*13);
        sheet.setColumnWidth(6, 256*10);

        return sheet;

    }
}
