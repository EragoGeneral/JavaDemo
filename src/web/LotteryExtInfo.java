package web;

public class LotteryExtInfo {
	private int id;
	
	private String period;
	
	private int redBall1;
	
	private int redBall2;
	
	private int redBall3;
	
	private int redBall4;
	
	private int redBall5;
	
	private int redBall6;
	
	private int blueBall;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public int getRedBall1() {
		return redBall1;
	}

	public void setRedBall1(int redBall1) {
		this.redBall1 = redBall1;
	}

	public int getRedBall2() {
		return redBall2;
	}

	public void setRedBall2(int redBall2) {
		this.redBall2 = redBall2;
	}

	public int getRedBall3() {
		return redBall3;
	}

	public void setRedBall3(int redBall3) {
		this.redBall3 = redBall3;
	}

	public int getRedBall4() {
		return redBall4;
	}

	public void setRedBall4(int redBall4) {
		this.redBall4 = redBall4;
	}

	public int getRedBall5() {
		return redBall5;
	}

	public void setRedBall5(int redBall5) {
		this.redBall5 = redBall5;
	}

	public int getRedBall6() {
		return redBall6;
	}

	public void setRedBall6(int redBall6) {
		this.redBall6 = redBall6;
	}

	public int getBlueBall() {
		return blueBall;
	}

	public void setBlueBall(int blueBall) {
		this.blueBall = blueBall;
	}

	public LotteryExtInfo(String period, int redBall1, int redBall2,
			int redBall3, int redBall4, int redBall5, int redBall6, int blueBall) {
		super();
		this.period = period;
		this.redBall1 = redBall1;
		this.redBall2 = redBall2;
		this.redBall3 = redBall3;
		this.redBall4 = redBall4;
		this.redBall5 = redBall5;
		this.redBall6 = redBall6;
		this.blueBall = blueBall;
	}
}
