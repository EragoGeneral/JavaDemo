package basic.exception;

public class ExceptionDemo {
	
	public static void main(String[] args) {
		double dd = new ExceptionDemo().test(1);
		System.out.println(dd);
		int i = 12;
		System.out.println(i-=i+=i*=i);
	}
	
	public double test(double d){
		
		try {
			d+= 1;
			return d;
		} catch (Exception e) {
			System.out.println("catch");
		}finally{
			System.out.println("finally");	
		}
		System.out.println("return");
		return d;
	}
}
