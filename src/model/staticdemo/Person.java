package model.staticdemo;

public class Person {
	private static String name = "Zhang San";
	private int age = 20; 
	
	public static void main(String[] args) {
		System.out.println(Person.name);
		
		Person p = null;
		//���ʾ�̬����ʱ�����Բ�����������ڴ棬�ڼ�����ʱ����̬�����Ѿ��������
		System.out.println(p.name);
		
		//���ʷǾ�̬����ʱ������ʵ��������
		p = new Person();
		System.out.println(p.age);
		
		System.out.println(name);
		//System.out.println(age);   //��̬�������ܷ��ʷǾ�̬����
	}
}
