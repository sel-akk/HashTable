import java.io.*;
import java.util.*;

public class Open_Addressing {
	public static final double MAX_LOAD_FACTOR = 0.75;
	
	public int m; // number of slots
	public int A; // the default random number
	int w;
	int r;
	int seed;
	public int[] Table;
	int size; // number of elements stored in the hash table

	protected Open_Addressing(int w, int seed, int A) {
		this.seed = seed;
		this.w = w;
		this.r = (int) (w - 1) / 2 + 1;
		this.m = power2(r);
		if (A == -1) {
			this.A = generateRandom((int) power2(w - 1), (int) power2(w), seed);
		} else {
			this.A = A;
		}
		this.Table = new int[m];
		for (int i = 0; i < m; i++) {
			Table[i] = -1;
		}
		this.size = 0;
	}

	/**
	 * Calculate 2^w
	 */
	public static int power2(int w) {
		return (int) Math.pow(2, w);
	}

	public static int generateRandom(int min, int max, int seed) {
		Random generator = new Random();
		if (seed >= 0) {
			generator.setSeed(seed);
		}
		int i = generator.nextInt(max - min - 1);
		return i + min + 1;
	}

	/**
	 * Implements the hash function g(k)
	 */
	public int probe(int key, int i) {
		//TODO: implement this function and change the return statement.
		
		//g(k, i) = (h(k) + i) mod 2^r
		
		int index = (((((A*key) % power2(w)) >> (w - r)) + i) % m);
		
		
		return index;
	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions encountered
	 */
	public int insertKey(int key) {
		//TODO: implement this function and change the return statement.
		
		//inserts a key k into the hash table and returns the number of collisions 
		//encountered before insertion, or the number of collisions encountered 
		//before giving up on inserting, if the table is full. 
		
		int i = 0;
		while(this.Table[probe(key, i)] != -1) {
			if(i < m) {
				i++;
			}
			else {
				break;
			}
		}
		
		this.Table[probe(key, i)] = key;
		size++;
		
		
		return i;
		
		
	}


	/**
	 * Sequentially inserts a list of keys into the HashTable. Outputs total number of collisions
	 */
	public int insertKeyArray(int[] keyArray) {
		int collision = 0;
		for (int key : keyArray) {
			collision += insertKey(key);
		}
		return collision;
	}

	/**
	 * @param the key k to be searched
	 * @return an int array containing 2 elements:
	 * first element = index of k in this.Table if the key is present, = -1 if not present
	 * second element = number of collisions occured during the search
	 */
	public int[] searchKey(int k) {
		//TODO: implement this function and change the return statement
		
		/* This method should take as input a key k, and should return an array of two integers. 
		 * The first one will be the index of k in the table if k is found, otherwise it returns -1.
		 *  The second element of the array will be the number of collisions that happened before 
		 *  finding the key. If the key is not in the hash table, the element should show the number 
		 *  of slots visited. The search phase should be optimized: you should visit as few slots as
		 *  possible.
		 */
				
		int i = 0;
		int index = probe(k, i);
		while(Table[index] != k) {
			
			if(Table[index] == -1) {
				i++;
				break;
			}
			
			if(i < m) {
				i++;
				index = probe(k, i);
			}
			else {
				int[] output = {-1, i};
				return output;
			
			}
		}
		
		
		int[] output = {index, i};
		return output;
		
		
	}
	
	/**
	 * Removes key k from hash table. Returns the number of collisions encountered
	 */
	public int removeKey(int k){
		//TODO: implement this function and change the return statement.
		
		//when element is removed, -2
		System.out.println(Arrays.toString(Table));
		
		int[] i = searchKey(k);
		
		if(i[0] != -1) {
			
			Table[i[0]] = -2;
			size--;
			//System.out.println(Arrays.toString(Table));
			return i[1];
		}
		else {
			return i[1];
		}
		
		
	}

	/**
	 * Inserts key k into hash table. Returns the number of collisions encountered,
	 * and resizes the hash table if needed
	 */
	public int insertKeyResize(int key) {
		//TODO: implement this function and change the return statement
		
		int j = 0;
		
		double a  = (double) (size + 1) / (double) m;
		
		if (a > MAX_LOAD_FACTOR) {
			
			//array to store old table values
			int[] temp = Table;
			
			
			//update variables
			this.m = 2 * m;
			this.r = (int) (Math.log(m) / Math.log(2));
			this.w = (2 * r) - 1;
			if (A == -1) {
				this.A = generateRandom((int) power2(this.w - 1), (int) power2(this.w), seed);
			}
			this.Table = new int[m];
			
			
			//put values of new value to -1
			for (int v = 0; v < m; v++) {
				Table[v] = -1;
			}
			this.size = 0;
			
			//reinsert keys to new table
			for(int b = 0; b < (m/2) ; b++) {
				if(temp[b] != -1 && temp[b] != -2) {
					insertKey(temp[b]);
				}
			}
			
			j = insertKey(key);
			
		}
		else {
			j = insertKey(key);
		}
		
		return j;
		
	}

	/**
	 * Sequentially inserts a list of keys into the HashTable, and resize the hash table
	 * if needed. Outputs total number of collisions
	 */
	public int insertKeyArrayResize(int[] keyArray) {
		int collision = 0;
		for (int key : keyArray) {
			collision += insertKeyResize(key);
		}
		return collision;
	}

	/**
	 * @param the key k to be searched (and relocated if needed)
	 * @return an int array containing 2 elements:
	 * first element = index of k in this.Table (after the relocation) if the key is present, 
	 * 				 = -1 if not present
	 * second element = number of collisions occured during the search
	 */
	public int[] searchKeyOptimized(int k) {
		//TODO: implement this function and change the return statement
		
		//search spot where a key was removed (-2)
		int[] temp = searchKey(k);
		int j = 0;
		if(temp[0] > -1) {
			while(Table[probe(k, j)] != -2) {
				if(j < temp[0]) {
					j++;
				}
				else {
					break;
				}
			}
		}
		else {
			int[] output = {-1, temp[1]};
			return output;
		}
		
		
		//relocate k 
		Table[probe(k, j)] = k;
		Table[temp[0]] = -2;
		int[] output2 = {probe(k, j), (temp[1])};
		return output2;
		
		
		
		
	
	}

	/**
	 * @return an int array of n keys that would collide with key k
	 */
	public int[] collidingKeys(int k, int n, int w) {
		//TODO: implement this function and change the return statement
		
		int[] output = new int[n];
		output[0] = k;
		int hash = (k % power2(w)) ; 
		
		for(int key = 0, i = 1; key < Integer.MAX_VALUE && i < (n);) {
			if(key == k) {
				key++;
			}
			
			if(hash == (key % power2(w))) {
				output[i] = key;
				i++;
				key++;
			}
			else {
				key++;
			}
		}
		return output;
	}
}
