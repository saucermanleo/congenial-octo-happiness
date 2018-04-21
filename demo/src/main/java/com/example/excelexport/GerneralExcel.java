package com.example.excelexport;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class GerneralExcel {
	public static void createExcel(ExcelParams ep, OutputStream out) {
		WritableCellFormat hformat = ep.getHeadformat();
		WritableCellFormat cformat = ep.getCellformat();
		WritableWorkbook book = null;
		try {
			if (out == null) {
				book = Workbook.createWorkbook(new File(ep.getFilepath()));
			} else {
				book = Workbook.createWorkbook(out);
			}
			WritableSheet sheet = book.createSheet(ep.getSheetname(), 0);
			SheetSettings setting = sheet.getSettings();
			setting.setVerticalFreeze(1);// 冻结窗口头部
			LinkedHashMap<String, String> map = ep.getMapper();
			int columnIndex = 0;
			for (String key : map.keySet()) {
				sheet.addCell(new Label(columnIndex, 0, map.get(key), hformat));
				columnIndex++;
				sheet.setColumnView(columnIndex, 20);
			}
			Map<String, Method> methods = getMethods(ep.getClz());
			columnIndex = 1;
			for (Object t : ep.getObjets()) {
				int j = 0;
				for (String key : map.keySet()) {
					Method m = methods.get(getkey(key));
					Object o = m.invoke(t, null);
					String value = o == null ? "" : o.toString();
					try {
						BigDecimal bd = new BigDecimal(value);
						value = bd.toPlainString();
					} catch (Exception e) {
					}
					Label label = new Label(j, columnIndex, value, cformat);
					sheet.addCell(label);
					j++;
				}
				columnIndex++;
			}
			book.write();
            System.out.println("导出Excel成功！");

		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}finally{
			try {
				/*if(out!=null){
					out.close();
				}*/
				if(book!=null){
					book.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (WriteException e) {
				e.printStackTrace();
			}
		}

	}

	private static Map<String, Method> getMethods(Class clz) {
		Map<String, Method> resultmap = new HashMap<String, Method>();

		Method[] methods = clz.getMethods();
		for (Method m : methods) {
			resultmap.put(m.getName(), m);
		}
		return resultmap;

	}
	
	private static String getkey(String key){
		key = "get"+key.substring(0, 1).toUpperCase() + key.substring(1);
		return key;
		
	}
}