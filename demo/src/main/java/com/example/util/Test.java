package com.example.util;

import java.io.File;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.DateFormat;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableHyperlink;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Test {

	private static final String DTS_LINK_GETMETHOD_NAME = "getdTSLink;getDimension";

	private static final String DTS_LINK_URL = "http://dts.huawei.com/net/dts/DTS/DTSWorkFlowPage.aspx?No=";

	/**
	 * 导出Excel
	 *
	 * @param list：结果集合
	 * @param filePath：指定的路径名
	 * @param out：输出流对象
	 *            通过response.getOutputStream()传入
	 * @param mapFields：导出字段
	 *            key:对应实体类字段 value：对应导出表中显示的中文名
	 * @param sheetName：工作表名称
	 */
	public static synchronized void createExcel(List<?> list, String filePath, OutputStream out,
			Map<String, String> mapFields, String sheetName) throws Exception {
		sheetName = sheetName != null && !sheetName.equals("") ? sheetName : "sheet1";
		WritableWorkbook wook = null;// 可写的工作薄对象
		Object objClass = null;
		try {
			if (filePath != null && !filePath.equals("")) {
				wook = Workbook.createWorkbook(new File(filePath));// 指定导出的目录和文件名
				// 如：D:\\test.xls
			} else {
				wook = Workbook.createWorkbook(out);// jsp页面导出用
			}

			// 设置头部字体格式
			WritableFont font = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
					UnderlineStyle.NO_UNDERLINE, Colour.RED);
			// 应用字体
			WritableCellFormat wcfh = new WritableCellFormat(font);
			// 设置其他样式
			wcfh.setAlignment(Alignment.CENTRE);// 水平对齐
			wcfh.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直对齐
			wcfh.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
			wcfh.setBackground(Colour.YELLOW);// 背景色
			// wcfh.setWrap(false);//不自动换行
			wcfh.setWrap(true);
			// 设置内容日期格式
			DateFormat df = new DateFormat("yyyy-MM-dd HH:mm:ss");
			// 应用日期格式
			WritableCellFormat wcfc = new WritableCellFormat(df);

			wcfc.setAlignment(Alignment.CENTRE);
			wcfc.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直对齐
			wcfc.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
			// wcfc.setWrap(false);//不自动换行
			wcfc.setWrap(true);
			// 创建工作表
			WritableSheet sheet = wook.createSheet(sheetName, 0);
			SheetSettings setting = sheet.getSettings();
			setting.setVerticalFreeze(1);// 冻结窗口头部

			int columnIndex = 0; // 列索引
			List<String> methodNameList = new ArrayList<String>();
			if (mapFields != null) {
				String key = "";
				Map<String, Method> getMap = null;
				Method method = null;
				// 开始导出表格头部
				for (Iterator<String> i = mapFields.keySet().iterator(); i.hasNext();) {
					key = i.next();
					// 应用wcfh样式创建单元格
					sheet.addCell(new Label(columnIndex, 0, mapFields.get(key), wcfh));
					// 记录字段的顺序，以便于导出的内容与字段不出现偏移
					methodNameList.add(key);
					// 创建每列的宽度为20
					sheet.setColumnView(columnIndex, 20);
					columnIndex++;
				}

				if (list != null && list.size() > 0) {
					// 导出表格内容
					for (int i = 0, len = list.size(); i < len; i++) {
						objClass = list.get(i);
						getMap = getAllMethod(objClass);// 获得对象所有的get方法
						// 按保存的字段顺序导出内容
						for (int j = 0; j < methodNameList.size(); j++) {
							// 根据key获取对应方法
							method = getMap.get("GET" + methodNameList.get(j).toString().toUpperCase());
							if (method != null) {
								// 从对应的get方法得到返回值
								Object o = method.invoke(objClass, null);
								String value = o == null ? "" : o.toString();
								try {
									// 将科学计数法转换为正常string类型数字
									BigDecimal bd = new BigDecimal(value);
									value = bd.toPlainString();
								} catch (Exception e) {

								}
								Label label = null;
								// 如果含有DTS单号，则设置DTS单号超链接
								Pattern pattern = Pattern.compile("http:\\\\");
								Matcher matcher = pattern.matcher(value);
								if (// DTS_LINK_GETMETHOD_NAME.indexOf(method.getName())
									// >= 0 &&
								matcher.find()) {
									// 单元格添加超链接
									sheet.addHyperlink(new WritableHyperlink(j, i + 1, new URL(DTS_LINK_URL + value)));
									// 设置文体格式
									WritableFont linkFont = new WritableFont(WritableFont.TIMES, 11,
											WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLUE);
									WritableCellFormat linkCellFormat = new WritableCellFormat(linkFont);
									linkCellFormat.setAlignment(Alignment.CENTRE);
									linkCellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);// 垂直对齐
									linkCellFormat.setBorder(Border.ALL, BorderLineStyle.THIN);// 边框
									// 添加单元格显示文本
									label = new Label(j, i + 1, value, linkCellFormat);
									// for (int j = 0; j < methodNameList.size(); j++)
									// 根据key获取对应方法

								} else {
									// 应用wcfc样式创建单元格
									label = new Label(j, i + 1, value, wcfc);
								}
								sheet.addCell(label);
							} else {
								// 如果没有对应的get方法，则默认将内容设为""
								sheet.addCell(new Label(j, i + 1, "", wcfc));
							}

						}
					}
				}
				wook.write();
				System.out.println("导出Excel成功！");
			} else {
				throw new Exception("传入参数不合法");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (wook != null) {
					wook.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * 获取类的所有get方法
	 *
	 * @param
	 * @return
	 */
	public static HashMap<String, Method> getAllMethod(Object obj) throws Exception {
		HashMap<String, Method> map = new HashMap<String, Method>();
		Method[] methods = obj.getClass().getMethods();// 得到所有方法
		String methodName = "";
		for (int i = 0; i < methods.length; i++) {
			methodName = methods[i].getName().toUpperCase();// 方法名
			if (methodName.startsWith("GET")) {
				map.put(methodName, methods[i]);// 添加get方法至map中
			}
		}
		return map;
	}
}