package com.example.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class ExportExcel {
	public static void export(Map<String,String> mapper,OutputStream out ,List<?> objects,String sheetName) {
		sheetName = StringUtils.isEmpty(sheetName)?sheetName:"sheet1";
		try {
			WritableWorkbook wook =Workbook.createWorkbook(out);
			WritableFont font = new WritableFont(WritableFont.TIMES,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.RED);
			WritableCellFormat wcf = new WritableCellFormat(font);
			wcf.setAlignment(Alignment.CENTRE);
			wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			wcf.setBackground(Colour.YELLOW);
			wcf.setWrap(true);
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/DD hh:mm:ss");
			 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
