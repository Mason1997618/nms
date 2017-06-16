package cn.edu.uestc.platform.test.newTest1;

public class ZKTest {

	public static void main(String[] args) {
		Father f = new Son();
		f.eat();
	}
}

interface Father {
	public void eat();
}

class Son implements Father {
	@Override
	public void eat() {
		System.out.println(" son eat");
	}
}