package model.staticdemo;

public class Person {
	private static String name = "Zhang San";
	private int age = 20; 
	
	public static void main(String[] args) {
		System.out.println(Person.name);
		
		Person p = null;
		//访问静态变量时，可以不给对象分配内存，在加载类时，静态变量已经分配完毕
		System.out.println(p.name);
		
		//访问非静态变量时，必须实例化对象
		p = new Person();
		System.out.println(p.age);
		
		System.out.println(name);
		//System.out.println(age);   //静态方法不能访问非静态变量
	}
}
