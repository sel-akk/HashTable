import java.io.*;
import java.util.*;

public class Universal_Hashing extends Open_Addressing{
	int a;
	int b;
	int p;

	protected Universal_Hashing(int w, int seed) {
		super(w, seed, -1);
		int temp = this.m+1; // m is even, so temp is odd here
		while(!isPrime(temp)) {
			temp += 2;
		}
		this.p = temp;
		a = generateRandom(0, p, seed);
		b = generateRandom(-1, p, seed);
	}
	
	/**
	 * Checks if the input int is prime
	 */
	public static boolean isPrime(int n) {
        if (n <= 1) return false;
        for (int i = 2; i*i <= n; i++) {
        	if (n % i == 0) return false;
        }
        return true;
    }
	
	/**
     * Implements universal hashing
     */
	@Override
    public int probe(int key, int i) {
    	//TODO: implement this function and change the return statement
		
		
		int index = ((((a * key +b) % p) % m) + i) % power2(r); 
		
		return index;
    }

    /**
     * Inserts key k into hash table. Returns the number of collisions encountered,
     * and resizes the hash table if needed
     */
	@Override
    public int insertKeyResize(int key) {
    	//TODO: implement this function and change the return statement
		
		int j = 0;
		
		double load = (double) (size + 1) / (double) m;
		
		if (load > MAX_LOAD_FACTOR) {
			
			//array to store old table values
			int[] t = Table;
			
			
			//update variables
			this.m = 2 * m;
			int temp = this.m+1;
			while(!isPrime(temp)) {
				temp += 2;
			}
			this.p = temp;
			a = generateRandom(0, p, seed);
			b = generateRandom(-1, p, seed);
			this.r = (int) (Math.log(m) / Math.log(2));
			this.Table = new int[m];
			
			
			
			//put values of new value to -1
			for (int v = 0; v < m; v++) {
				Table[v] = -1;
			}
			this.size = 0;
			
			//reinsert keys to new table
			for(int b = 0; b < (m/2) ; b++) {
				if(t[b] != -1 && t[b] != -2) {
					insertKey(t[b]);
				}
			}
			
			j = insertKey(key);
			
		}
		else {
			j = insertKey(key);
		}
		
		return j;
		
    }
}
