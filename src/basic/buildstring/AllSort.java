package basic.buildstring;

public class AllSort {
	public static void main(String[] args) {
		char buf[] = { '1', '2', '2', '3', '4', '5' };

		perm(buf, 0, buf.length-1);
	}

	public static void perm(char[] buf, int start, int end) {
		if (start == end) {// ��ֻҪ���������һ����ĸ����ȫ����ʱ��ֻҪ�Ͱ��������������
			for (int i = 0; i <= end; i++) {
				System.out.print(buf[i]+",");
			}
			System.out.println();
		} else {// �����ĸȫ����
			for (int i = start; i <= end; i++) {
				char temp = buf[start];// ���������һ��Ԫ���������Ԫ��
				buf[start] = buf[i];
				buf[i] = temp;

				perm(buf, start + 1, end);// ����Ԫ�صݹ�ȫ����

				temp = buf[start];// ������������黹ԭ
				buf[start] = buf[i];
				buf[i] = temp;
			}
		}
	}
}
