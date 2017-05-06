package model.staticdemo;

public class StaticTest {
	private static int a; 
    private int b; 

    static { 
            StaticTest.a = 3; 
            System.out.println(a); 
            StaticTest t = new StaticTest(); 
            t.f(); 
            t.b = 1000; 
            System.out.println(t.b); 
    } 

    static { 
            StaticTest.a = 4; 
            System.out.println(a); 
    } 

    public static void main(String[] args) { 
            System.out.println(StaticTest.a); 
            new StaticTest().f();
            System.out.println(StaticTest.a);
    } 

    static { 
            StaticTest.a = 5; 
            System.out.println(a); 
    } 

    public void f() { 
            System.out.println("hhahhahah"); 
            StaticTest.a = 10;
    } 

}
