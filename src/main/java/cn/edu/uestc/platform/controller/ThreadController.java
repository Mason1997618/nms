package cn.edu.uestc.platform.controller;

public class ThreadController implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	public void run(String linkName,int sleepTime){
		try {
			System.out.println("现在"+linkName+"开始睡眠"+sleepTime+"s");
			Thread.sleep(sleepTime*1000);
			System.out.println(linkName+"睡眠结束");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
