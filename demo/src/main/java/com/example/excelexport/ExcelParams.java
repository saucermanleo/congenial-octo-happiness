package com.example.excelexport;

import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.List;

import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WriteException;

public class ExcelParams {

	public String getSheetname() {
		return sheetname;
	}

	public void setSheetname(String sheetname) {
		this.sheetname = sheetname;
	}

	private WritableCellFormat headformat;
	private WritableCellFormat cellformat;
	private String filepath = "D:\\test.xls";
	private LinkedHashMap<String, String> mapper = new LinkedHashMap<String, String>();
	@SuppressWarnings("rawtypes")
	private List objets;
	@SuppressWarnings("rawtypes")
	private Class clz;
	private String sheetname = "sheet1";

	private void getFieldname() {
		Field[] fields = clz.getDeclaredFields();
		for (Field f : fields) {
			if (f.isAnnotationPresent(GeneralExcelConfig.class)) {
				GeneralExcelConfig g = f.getAnnotation(GeneralExcelConfig.class);
				if (g.ingore()) {
					continue;
				} else {
					// f.setAccessible(true);
					this.mapper.put(f.getName(), g.value());
				}
			} else {
				this.mapper.put(f.getName(), f.getName());
			}

		}
	}

	@SuppressWarnings("rawtypes")
	public ExcelParams(List objets, Class clz) throws WriteException {
		this.objets = objets;
		this.clz = clz;
		WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
				UnderlineStyle.NO_UNDERLINE, Colour.RED);
		headformat = new WritableCellFormat(font);
		headformat.setAlignment(Alignment.CENTRE);// 水平对齐
		headformat.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直对齐
		headformat.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
		headformat.setBackground(Colour.YELLOW);// 背景色
		headformat.setWrap(true);// 自动换行

		DateFormat df = new DateFormat("yyyy-MM-dd HH:mm:ss");// 应用日期格式
		cellformat = new WritableCellFormat(df);
		cellformat.setAlignment(Alignment.CENTRE);
		cellformat.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直对齐
		cellformat.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
		cellformat.setWrap(true);

		getFieldname();
	}

	public ExcelParams(WritableCellFormat headformat, WritableCellFormat cellformat, String filepath) {
		super();
		this.headformat = headformat;
		this.cellformat = cellformat;
		this.filepath = filepath;
	}

	public WritableCellFormat getHeadformat() {
		return headformat;
	}

	public void setHeadformat(WritableCellFormat headformat) {
		this.headformat = headformat;
	}

	public WritableCellFormat getCellformat() {
		return cellformat;
	}

	public void setCellformat(WritableCellFormat cellformat) {
		this.cellformat = cellformat;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public LinkedHashMap<String, String> getMapper() {
		return mapper;
	}

	public void setMapper(LinkedHashMap<String, String> mapper) {
		this.mapper = mapper;
	}

	@SuppressWarnings("rawtypes")
	public List getObjets() {
		return objets;
	}

	@SuppressWarnings("rawtypes")
	public void setObjets(List objets) {
		this.objets = objets;
	}

	@SuppressWarnings("rawtypes")
	public Class getClz() {
		return clz;
	}

	@SuppressWarnings("rawtypes")
	public void setClz(Class clz) {
		this.clz = clz;
	}
}