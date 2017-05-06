package model;

public class TestString {

	static double aa = 0.0;
	
	static int a = 1;
	int b = 2;
	void test(){
		int aa = ++a;
	}
	
	public static void main(String[] args) {
		
		
		String str1 = new String("Hello ");
		String str2 = new String("World");
		String res = new String();
				
		res = str1 + str2;
				
		int bb = a;
		
		String a = new String("aaa");
		String b = new String("aaa");
		System.out.println("==比较 ："+ (a==b));
		System.out.println("equal比较："+ a.equals(b));
		
		String str3 = "str1";
        String str4 = "str1";
        System.out.println("==比较 ："+ (str3 == str4));
        System.out.println("equal比较："+ str3.equals(str4));
        
      //在-128~127 之外的数
        Integer i1 =200;  
        Integer i2 =200;          
        System.out.println("i1==i2: "+(i1==i2));                   
        // 在-128~127 之内的数
        Integer i3 =100;  
        Integer i4 =100;  
        System.out.println("i3==i4: "+(i3==i4));
				
        int i5 = 200;
        int i6 = 200;
        System.out.println("i5==i6: "+(i5==i6));
        
        int i7 = new Integer(100);
        int i8 = new Integer(100);
        System.out.println("i7==i8: "+(i7==i8));
        
        Integer i9 = new Integer(100);
        Integer i10 = new Integer(100);
        System.out.println("i9==i10: "+(i9==i10));
        
	}
}
