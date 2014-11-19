public class SimpleRace {
	
	static int b = 1;
	
	public static class BIs10 implements Runnable{
		public void run(){
			try{
				Thread.sleep(500);
				b = 10;
				System.out.println("This thread says b is: " + b);
			}
			catch(InterruptedException e){
				
			}
		}
	}
	
	public static class BIs100 implements Runnable{
		public void run(){
			try{
				Thread.sleep(1000);
				b = 100;
				System.out.println("This thread says b is: " + b);
			}
			catch(InterruptedException e){
				
			}
		}
	}
	
	public static void main(String args[]) throws InterruptedException{
		(new Thread(new BIs10())).start();
		(new Thread(new BIs100())).start();
		System.out.println("B is: " + b);
	}
}