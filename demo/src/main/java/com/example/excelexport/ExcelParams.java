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

/**
 * 生成excel工具类{@link GerneralExcel}的参数封装类
 * <p>{@code headformat} Excel头的格式{@link WritableCellFormat},不提供采用默认格式
 * <p>{@code cellformat} Excel的格式,不提供采用默认格式
 * <P>{@code filepath} 导出的excel的绝对路径地址,默认为D:\\test.xls
 * <p>{@code mapper} 头字段信息,key为头字段,value为要导出对象的对应的属性字段,默认为导出对象的字段,可以用{@link GeneralExcelConfig}设置忽略字段,和头字段名
 * <p>{@code sheetname} sheet名,默认为sheet1
 * <p>{@code objets} 成excel的Beans
 * @author Zangy
 *
 * @param <T> 要导出的Bean
 * @see GerneralExcel
 */
public class ExcelParams<T> {

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
	private List<T> objets;
	private Class<T> clz;
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

	public ExcelParams(List<T> objets, Class<T> clz) throws WriteException {
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

	public List<T> getObjets() {
		return objets;
	}

	public void setObjets(List<T> objets) {
		this.objets = objets;
	}

	public Class<T> getClz() {
		return clz;
	}

	public void setClz(Class<T> clz) {
		this.clz = clz;
	}
}