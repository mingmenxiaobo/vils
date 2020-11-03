package com.csit.web.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 描述:poi根据模板导出excel,根据excel坐标赋值,如(B1)
 */
public class ExcelExportUtil
{

    // 模板map
    private Map<String, Workbook> tempWorkbook = new HashMap<String, Workbook>();
    // 模板输入流map
    private Map<String, InputStream> tempStream = new HashMap<String, InputStream>();

    /**
     * 功能:按模板向Excel中相应地方填充数据
     */
    public void writeData(String templateFilePath, Map<String, Object> dataMap,
            int sheetNo) throws IOException, InvalidFormatException
    {
        if (dataMap == null || dataMap.isEmpty())
        {
            return;
        }
        // 读取模板
        Workbook wbModule = getTempWorkbook(templateFilePath);
        // 数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);

        for (Entry<String, Object> entry : dataMap.entrySet())
        {
            String point = entry.getKey();
            Object data = entry.getValue();
            if(data == null){
            	data = "";
            }
            TempCell cell = getCell(point, data, wsheet);
            // 指定坐标赋值
            setCell(cell, wsheet);
        }

        // 设置生成excel中公式自动计算
        wsheet.setForceFormulaRecalculation(true);
    }

    /**
     * 功能:按模板向Excel中列表填充数据.只支持列合并
     */
    public void writeDateList(String templateFilePath, String[] heads,
            List<Map<Integer, Object>> datalist, int sheetNo)
            throws IOException, InvalidFormatException
    {
        if (heads == null || heads.length<= 0
                || CollectionUtils.isEmpty(datalist))
        {
            return;
        }
        // 读取模板
        Workbook wbModule = getTempWorkbook(templateFilePath);
        // 数据填充的sheet
        Sheet wsheet = wbModule.getSheetAt(sheetNo);

        // 列表数据模板cell
        List<TempCell> tempCells = new ArrayList<TempCell>(heads.length);
        for (Object obj : heads)
        {
        	String point = obj.toString();
            TempCell tempCell = getCell(point, null, wsheet);
            // 取得合并单元格位置 -1:表示不是合并单元格
            int pos = isMergedRegion(wsheet, tempCell.getRow(),
                    tempCell.getColumn());
            if (pos > -1)
            {
                CellRangeAddress range = wsheet.getMergedRegion(pos);
                tempCell.setColumnSize(range.getLastColumn()
                        - range.getFirstColumn());
            }
            tempCells.add(tempCell);
        } 
        // 赋值
        for (int i = 0; i < datalist.size(); i++)
        {// 数据行
            Map<Integer, Object> dataMap = datalist.get(i);
            for (int j = i*datalist.size(); j < (i+1)*datalist.size(); j++)
            {// 列
               // TempCell tempCell = tempCells.get(j);
               // for(Object key:dataMap.keySet()){
               // System.out.println(dataMap.get(j));
               // tempCell.setData(dataMap.get(j));
               // }
                
               // setCell(tempCell, wsheet);
               // tempCell.setRow(tempCell.getRow() + 1);
            }
        }
    }

    /**
     * 功能:获取输入工作区
     */
    private Workbook getTempWorkbook(String templateFilePath)
            throws IOException, InvalidFormatException
    {
        if (!tempWorkbook.containsKey(templateFilePath))
        {
            InputStream inputStream = getInputStream(templateFilePath);
            tempWorkbook.put(templateFilePath,
                    WorkbookFactory.create(inputStream));
        }
        return tempWorkbook.get(templateFilePath);
    }

    /**
     * 功能:获得模板输入流
     */
    private InputStream getInputStream(String templateFilePath)
            throws FileNotFoundException
    {
        if (!tempStream.containsKey(templateFilePath))
        {
            tempStream.put(templateFilePath, new FileInputStream(
                    (templateFilePath)));
        }
        return tempStream.get(templateFilePath);
    }

    /**
     * 功能:获取单元格数据,样式(根据坐标:B3)
     */
    private TempCell getCell(String point, Object data, Sheet sheet)
    {
        TempCell tempCell = new TempCell();

        // 得到列 字母
        String lineStr = "";
        String reg = "[A-Z]+";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(point);
        while (m.find())
        {
            lineStr = m.group();
        }

        // 将列字母转成列号 根据ascii转换
        char[] ch = lineStr.toCharArray();
        int column = 0;
        for (int i = 0; i < ch.length; i++)
        {
            char c = ch[i];
            int post = ch.length - i - 1;
            int r = (int) Math.pow(10, post);
            column = column + r * ((int) c - 65);
        }
        tempCell.setColumn(column);

        // 得到行号
        reg = "[0-9]+";
        p = Pattern.compile(reg);
        m = p.matcher(point);
        while (m.find())
        {
            tempCell.setRow((Integer.parseInt(m.group()) - 1));
            // System.out.println(point + " " + lineStr + " " + m.group() +
            // " ");
        }

        // 获取模板指定单元格样式,设置到tempCell(写列表数据的时候用)
        Row rowIn = sheet.getRow(tempCell.getRow());
        if (rowIn == null)
        {
            rowIn = sheet.createRow(tempCell.getRow());
        }
        Cell cellIn = rowIn.getCell(tempCell.getColumn());
        if (cellIn == null)
        {
            cellIn = rowIn.createCell(tempCell.getColumn());
        }
        tempCell.setCellStyle(cellIn.getCellStyle());
        tempCell.setData(data);
        return tempCell;
    }

    /**
     * 功能:给指定坐标单元格赋值
     */
    private void setCell(TempCell tempCell, Sheet sheet)
    {
        if (tempCell.getColumnSize() > -1)
        {
            CellRangeAddress rangeAddress = mergeRegion(sheet,
                    tempCell.getRow(), tempCell.getRow(), tempCell.getColumn(),
                    tempCell.getColumn() + tempCell.getColumnSize());
            setRegionStyle(tempCell.getCellStyle(), rangeAddress, sheet);
        }

        Row rowIn = sheet.getRow(tempCell.getRow());
        if (rowIn == null)
        {
            copyRows(tempCell.getRow() - 1, tempCell.getRow() - 1,
                    tempCell.getRow(), sheet);// 复制上一行
            rowIn = sheet.getRow(tempCell.getRow());
        }
        Cell cellIn = rowIn.getCell(tempCell.getColumn());
        switch (cellIn.getCellType())
        { 
        case Cell.CELL_TYPE_FORMULA:
        	cellIn.setCellType(Cell.CELL_TYPE_NUMERIC);
        	break;
        default:
        	break;
        }
        	
        if (cellIn == null)
        {
            cellIn = rowIn.createCell(tempCell.getColumn());
        }
        // 根据data类型给cell赋值
        if (tempCell.getData() instanceof String)
        {
            cellIn.setCellValue((String) tempCell.getData());
        }
        else if (tempCell.getData() instanceof Integer)
        {
            cellIn.setCellValue(Integer.parseInt(tempCell.getData().toString()));
        }
        else if (tempCell.getData() instanceof Double)
        {
            cellIn.setCellValue(Double.parseDouble(tempCell.getData()
                    .toString()));
        }
        else
        {
            cellIn.setCellValue((String) tempCell.getData());
        }
        // 样式
        if (tempCell.getCellStyle() != null && tempCell.getColumnSize() == -1)
        {
            cellIn.setCellStyle(tempCell.getCellStyle());
        }
    }

    /**
     * 功能:写到输出流并移除资源
     */
    public void writeAndClose(String templateFilePath, OutputStream os)
            throws IOException, InvalidFormatException
    {
        if (getTempWorkbook(templateFilePath) != null)
        {
            getTempWorkbook(templateFilePath).write(os);
            tempWorkbook.remove(templateFilePath);
        }
        if (getInputStream(templateFilePath) != null)
        {
            getInputStream(templateFilePath).close();
            tempStream.remove(templateFilePath);
        }
    }

    /**
     * 功能:判断指定的单元格是否是合并单元格
     */
    private Integer isMergedRegion(Sheet sheet, int row, int column)
    {
        for (int i = 0; i < sheet.getNumMergedRegions(); i++)
        {
            CellRangeAddress range = sheet.getMergedRegion(i);
            int firstColumn = range.getFirstColumn();
            int lastColumn = range.getLastColumn();
            int firstRow = range.getFirstRow();
            int lastRow = range.getLastRow();
            if (row >= firstRow && row <= lastRow)
            {
                if (column >= firstColumn && column <= lastColumn)
                {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * 功能:合并单元格
     */
    private CellRangeAddress mergeRegion(Sheet sheet, int firstRow,
            int lastRow, int firstCol, int lastCol)
    {
        CellRangeAddress rang = new CellRangeAddress(firstRow, lastRow,
                firstCol, lastCol);
        sheet.addMergedRegion(rang);
        return rang;
    }

    /**
     * 功能:设置合并单元格样式
     */
    private void setRegionStyle(CellStyle cs, CellRangeAddress region,
            Sheet sheet)
    {
        for (int i = region.getFirstRow(); i <= region.getLastRow(); i++)
        {
            Row row = sheet.getRow(i);
            if (row == null)
                row = sheet.createRow(i);
            for (int j = region.getFirstColumn(); j <= region.getLastColumn(); j++)
            {
                Cell cell = row.getCell(j);
                if (cell == null)
                {
                    cell = row.createCell(j);
                    cell.setCellValue("");
                }
                cell.setCellStyle(cs);
            }
        }
    }

    /**
     * 功能:copy rows
     */
    private void copyRows(int startRow, int endRow, int pPosition, Sheet sheet)
    {
        int pStartRow = startRow - 1;
        int pEndRow = endRow - 1;
        int targetRowFrom;
        int targetRowTo;
        int columnCount;
        CellRangeAddress region = null;
        int i;
        int j;
        if (pStartRow == -1 || pEndRow == -1)
        {
            return;
        }
        // 拷贝合并的单元格
        for (i = 0; i < sheet.getNumMergedRegions(); i++)
        {
            region = sheet.getMergedRegion(i);
            if ((region.getFirstRow() >= pStartRow)
                    && (region.getLastRow() <= pEndRow))
            {
                targetRowFrom = region.getFirstRow() - pStartRow + pPosition;
                targetRowTo = region.getLastRow() - pStartRow + pPosition;
                CellRangeAddress newRegion = region.copy();
                newRegion.setFirstRow(targetRowFrom);
                newRegion.setFirstColumn(region.getFirstColumn());
                newRegion.setLastRow(targetRowTo);
                newRegion.setLastColumn(region.getLastColumn());
                sheet.addMergedRegion(newRegion);
            }
        }
        // 设置列宽
        for (i = pStartRow; i <= pEndRow; i++)
        {
            Row sourceRow = sheet.getRow(i);
            columnCount = sourceRow.getLastCellNum();
            if (sourceRow != null)
            {
                Row newRow = sheet.createRow(pPosition - pStartRow + i);
                newRow.setHeight(sourceRow.getHeight());
                for (j = 0; j < columnCount; j++)
                {
                    Cell templateCell = sourceRow.getCell(j);
                    if (templateCell != null)
                    {
                        Cell newCell = newRow.createCell(j);
                        copyCell(templateCell, newCell);
                    }
                }
            }
        }
    }

    /**
     * 功能:copy cell,不copy值
     */
    private void copyCell(Cell srcCell, Cell distCell)
    {
        distCell.setCellStyle(srcCell.getCellStyle());
        if (srcCell.getCellComment() != null)
        {
            distCell.setCellComment(srcCell.getCellComment());
        }
        int srcCellType = srcCell.getCellType();
        distCell.setCellType(srcCellType);
    }

    /**
     * 描述:临时单元格数据
     */
    class TempCell
    {
        private int row;
        private int column;
        private CellStyle cellStyle;
        private Object data;
        // 用于列表合并,表示几列合并
        private int columnSize = -1;

        public int getColumn()
        {
            return column;
        }

        public void setColumn(int column)
        {
            this.column = column;
        }

        public int getRow()
        {
            return row;
        }

        public void setRow(int row)
        {
            this.row = row;
        }

        public CellStyle getCellStyle()
        {
            return cellStyle;
        }

        public void setCellStyle(CellStyle cellStyle)
        {
            this.cellStyle = cellStyle;
        }

        public Object getData()
        {
            return data;
        }

        public void setData(Object data)
        {
            this.data = data;
        }

        public int getColumnSize()
        {
            return columnSize;
        }

        public void setColumnSize(int columnSize)
        {
            this.columnSize = columnSize;
        }
    }

    public static String getRandomFileName()
    {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        Random random = new Random();

        int rannum = (int) (random.nextDouble() * (99999 - 10000 + 1)) + 10000;// 获取5位随机数

        return rannum + str;// 当前时间
    }

    public static void main(String[] args) throws FileNotFoundException,
            IOException, InvalidFormatException
    {
            try
            {
                String templateFilePath = "D:/csit/File/myExcel5.xls"; //导出保存的地址
                File file = new File("D:/csit/File/downloadFile/myExcel_"
                        + getRandomFileName() + ".xls");
                OutputStream os = new FileOutputStream(file);

                ExcelExportUtil excel = new ExcelExportUtil();

               Map<String, Object> dataMap = new HashMap<String, Object>();
               String [] listStr={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
                       "AA","AB","AC","AD","AE","AF","AG","AH","AI","AJ","AK","AL","AM","AN","AO","AP","AQ","AR","AS","AT","AU","AV","AW","AX","AY","AZ",
                       "BA","BB","BC","BD","BE","BF","BG","BH","BI","BJ","BK","BL","BM","BN","BO","BP","BQ","BR","BS","BT","BU","BV","BW","BX","BY","BZ",
                       "CA","CB","CC","CD","CE","CF","CG","CH","CI","CJ","CK","CL","CM","CN","CO","CP","CQ","CR","CS","CT","CU","CV","CW","CX","CY","CZ",
                       "DA","DB","DC","DD","DE","DF","DG","DH","DI","DJ","DK","DL","DM","DN","DO","DP","DQ","DR","DS","DT","DU","DV","DW","DX","DY","DZ"};

               for(int j=1;j<=7;j++) {  //代表设备总数

                   dataMap.put("A1" , "日期");
                   dataMap.put(listStr[j]+"1" , "设备" + j);

                   for (int i = 2; i <=23; i++) {
                       dataMap.put("A" + i, "2020-06-" + i);// 日期

                       dataMap.put(listStr[j]+ i, 2 * i);  //使用率
                   }
               }
               excel.writeData(templateFilePath, dataMap, 1);



                // 写到输出流并移除资源
                excel.writeAndClose(templateFilePath, os);

                os.flush();
                os.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

