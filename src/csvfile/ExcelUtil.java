package csvfile;

import java.io.File;

import jxl.Workbook;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class ExcelUtil {
	public static void main(String args[]) {
		try {
			// 打开文件
			WritableWorkbook book = Workbook.createWorkbook(new File(
					"F://image.xls"));
			// 生成名为“第一页”的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("第一页", 0);
			// 合并单元格 第二行第一列到第二行第二列合并
			sheet.mergeCells(0, 1, 1, 1);
			// 设定单元格高度与宽度 设定第一行高度200 设定第一列宽度30
			sheet.setRowView(0, 2000);
			sheet.setColumnView(0, 30);
			// 指定字体样式：字体TIMES,字号16,加粗显示。
			// WritableFont()属性参数
			// 字体 WritableFont.TIMES
			// 大小 18
			// 是否为粗体 WritableFont.BOLD WritableFont.NO_BOLD
			// 是否为斜体 true
			// 样式 UnderlineStyle.NO_UNDERLINE 下划线
			// 颜色 jxl.format.Colour.RED 字体颜色为红色
			WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
					WritableFont.BOLD, false, UnderlineStyle.NO_UNDERLINE,
					jxl.format.Colour.RED);
			// WritableCellFormat类，通过它可以指定单元格的各种属性
			// 还可以指定数据对齐方式 水平对齐 垂直对齐
			WritableCellFormat format1 = new WritableCellFormat(font1);
			format1.setAlignment(jxl.format.Alignment.LEFT);
			format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
			// 在Label对象的构造子中指名单元格位置是第一列第一行(0,0)
			// 以及单元格内容为test
			// Label()方法三个参数 1：列数 2：行数 3：要写入的内容 4：样式（可选项）
			Label label = new Label(0, 0, "test", format1);

			// 将定义好的单元格添加到工作表中
			sheet.addCell(label);

			// 生成一个保存数字的单元格 必须使用Number的完整包路径，否则有语法歧义 单元格位置是第二列，第一行，值为789.123
			jxl.write.Number number = new jxl.write.Number(1, 0, 555.12541);
			sheet.addCell(number);

			// 插入图片
			String imgPath = "E://photo//1.png";
			File imgFile = new File(imgPath);
			// WritableImage(col, row, width, height, imgFile);

			// col row是图片的起始行起始列 width height是定义图片跨越的行数与列数
			WritableImage image = new WritableImage(4, 4, 2, 5, imgFile);
			sheet.addImage(image);

			// 写入数据并关闭文件
			book.write();
			book.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
