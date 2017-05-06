package basic.buildstring;

public class AllSort {
	public static void main(String[] args) {
		char buf[] = { '1', '2', '2', '3', '4', '5' };

		perm(buf, 0, buf.length-1);
	}

	public static void perm(char[] buf, int start, int end) {
		if (start == end) {// 当只要求对数组中一个字母进行全排列时，只要就按该数组输出即可
			for (int i = 0; i <= end; i++) {
				System.out.print(buf[i]+",");
			}
			System.out.println();
		} else {// 多个字母全排列
			for (int i = start; i <= end; i++) {
				char temp = buf[start];// 交换数组第一个元素与后续的元素
				buf[start] = buf[i];
				buf[i] = temp;

				perm(buf, start + 1, end);// 后续元素递归全排列

				temp = buf[start];// 将交换后的数组还原
				buf[start] = buf[i];
				buf[i] = temp;
			}
		}
	}
}
