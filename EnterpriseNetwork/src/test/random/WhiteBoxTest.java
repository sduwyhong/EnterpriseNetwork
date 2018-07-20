package random;

import org.junit.Test;

/**
 * @author wyhong
 * @date 2018-5-22
 */
public class WhiteBoxTest {

	int logic(int x, int y){
		int num = 0;
		if(x>0 && y>0){
			num = x+y+10; // 语句块1 
		}
		else{
			num = x+y-10; // 语句块2
		}

		if(num < 0){
			num = 0;         // 语句块3
		}
		return num;       // 语句块4
	}

	public void test1(){
		logic(3,3);
	}

	public void test2(){
		logic(-3,0);
	}

	public void test3(){
		logic(3,3);
	}

	public void test4(){
		logic(3,0);
	}

	public void test5(){
		logic(-3,2);
	}

	public void test6(){
		logic(-3,0);
	}

	int isLeap(int year){
		int leap = 0;
		if (year % 4 == 0) {
			if (year % 100 == 0){
				if ( year % 400 == 0){
					leap = 1;
				}
				else{
					leap = 0;

				}
			}
			else{
				leap = 1;
			}
		}
		else{
			leap = 0;
		}
		return leap;
	}
	
	public void test7(){
		isLeap(1999);
	}
	
	public void test8(){
		isLeap(2000);
	}
	
	public void test9(){
		isLeap(1900);
	}
	
	public void test10(){
		isLeap(2004);
	}
	//设最大循环次数为11
	void loop(int i){
		int sum = 0;
		while(i <= 10){
			sum += i;
			i++;
		}
		System.out.printf("%d\n", sum);
	}
	
	@Test
	public void test11(){
		//跳过循环
		loop(11);
	}
	@Test
	public void test12(){
		//循环1次
		loop(10);
	}
	@Test
	public void test13(){
		//循环2次
		loop(9);
	}
	@Test
	public void test14(){
		//循环6次（6 < 11）
		loop(5);
	}
	@Test
	public void test15(){
		//循环n-1次
		loop(1);
	}
	@Test
	public void test16(){
		//循环n次
		loop(0);
	}
	@Test
	public void test17(){
		//循环n+1次
		loop(-1);
	}
}
