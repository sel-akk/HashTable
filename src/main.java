import java.io.*;
import java.util.*;


public class main {     


	public static void main(String[] args) {
		//TODO:build the hash table and insert keys using the insertKeyArray function.
		
		
		
		Open_Addressing test = new Open_Addressing(10, 0, -1);
		
		
		//System.out.println(test.collidingKeys(0, 10, 10));
		System.out.println(test.insertKey(32));
		System.out.println(test.insertKey(52));
		System.out.println(test.insertKey(72));
		//System.out.println(test.insertKey(92));
		System.out.println(test.removeKey(52));
		System.out.println(test.insertKey(92));
		
		
		/*
		System.out.println(test.collidingKeys(33, 10, 10));
		System.out.println(test.probe(33, 1));
		System.out.println(test.probe(9249, 1));
		*/
		
		/*
		System.out.println(test.insertKey(40));
		System.out.println(test.insertKey(20));
		System.out.println(test.insertKey(60));
		//System.out.println(test.insertKey(13));
		System.out.println(test.removeKey(20));
		System.out.println(test.searchKeyOptimized(60));
		*/
		
		
		
		
		/*
		Chaining test = new Chaining(10, 0, -1);
		System.out.println(test.insertKey(32));
		System.out.println(test.insertKey(52));
		System.out.println(test.insertKey(72));
		
		
		*/
		
		/*
		Open_Addressing test3 = new Open_Addressing(10, 0, -1);
		
		System.out.println(test3.insertKeyResize(25));
		System.out.println(test3.insertKeyResize(26));
		
		*/
		
		
		/*
		Open_Addressing test2 = new Open_Addressing(10, 0, -1);
		System.out.println(test2.insertKey(55));
		System.out.println(test2.insertKey(50));
		System.out.println(test2.insertKey(30));
		System.out.println(test2.insertKey(25));
		System.out.println(test2.insertKey(26));
		System.out.println(test2.insertKey(31));
		System.out.println(test2.insertKey(54));
		System.out.println(test2.insertKey(40));
		System.out.println(test2.insertKey(45));
		System.out.println(test2.insertKey(35));
		System.out.println(test2.insertKey(15));
		System.out.println(test2.insertKey(55));
		System.out.println(test2.insertKey(50));
		System.out.println(test2.insertKey(30));
		System.out.println(test2.insertKey(25));
		System.out.println(test2.insertKey(26));
		System.out.println(test2.insertKey(31));
		System.out.println(test2.insertKey(54));
		System.out.println(test2.insertKey(40));
		System.out.println(test2.insertKey(45));
		System.out.println(test2.insertKey(35));
		System.out.println(test2.insertKey(15));
		System.out.println(test2.insertKey(55));
		System.out.println(test2.insertKey(50));
		System.out.println(test2.insertKey(30));
		
		
		
		System.out.println(test2.Table.length);
		//System.out.println(test2.m);
		//System.out.println(test2.r);
		System.out.println("after");
		
		
		System.out.println(test2.insertKeyResize(25));
		System.out.println(test2.Table.length);
		//System.out.println(test2.m);
		//System.out.println(test2.r);
		*/
		
		//insert key resize
		/*
		int[] temp = Table;
		int newm = (2 * m);
		Table = new int[newm];
		
		this.m = newm;
		this.r = (int) (Math.log(newm) / Math.log(2));
		this.w = (2 * (r - 1)) + 1;
		
		for (int v = 0; v < 2 * m; v++) {
			Table[v] = -1;
		}
		this.size = 0;
		
		for(int b = 0; b < this.m; b++) {
			insertKey(temp[b]);
		}
		j = insertKey(key);
		*/
		
		/*
		//update variables
		this.r = (int) (Math.log(2*m) / Math.log(2));
		//this.Table = new int[2 * m];
		//this.r = r + 1;
		//this.r = (int) (Math.log(2 * m) / Math.log(2));
		this.w = (2 * (r - 1)) + 1;
		this.m = power2(r);
		//this.m = m * 2;
		this.Table = new int[m];
		*/
		
		/*
		//this.m = 2 * m;
		//this.Table = new int[this.m];
		//this.r = (int) (Math.log(2 * m) / Math.log(2));
		this.r = r + 1;
		this.w = (2 * this.r) - 1;
		//this.m = power2(r);
		this.m = 2 * m;
		
		if (A == -1) {
			this.A = generateRandom((int) power2(this.w - 1), (int) power2(this.w), seed);
		}
		this.Table = new int[this.m];
		*/ 
		
		 
	}
}