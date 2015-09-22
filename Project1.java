import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class Project1 {
	private int m;
	private int n;
	private WeightedQuickUnionUF qu;
	private int[][] grid;
	private ArrayList<Point> connections;

	// add an int array to store input location to map
	private int[] intMap;
	// add an int arraylist to install pairs
	private ArrayList<Integer> intPairs;
	// add integer to indicate number of connections
	private int num_connect = 0;
	// add integer array to mark parent
	private int[] parent = null;


	/**
	 * initializes UnionFind structure, grid and connection list
	 * @param m
	 * @param n
	 */
	public Project1(int m, int n){
	// initialize UnionFind structure
 		this.m = m;
		this.n = n;
		grid = new int[m][n];
		intMap = new int[m * n];
		// initiate intMap
		for (int i = 0; i < m * n; i++) 
			intMap[i] = i;

	}

	/**
	 * Reads input from user (pair of connections presented as points), store the input in a list  
	 */
	public void read_input() {
		String inputLine = null;
		String[] twoLocation;

		StdIn si = new StdIn(System.in);
		System.out.println("Enter number of pairs of connections: ");
		num_connect = si.readInt();

		// initiate intPairs
		intPairs = new int[num_connect * 2];

		for (int i = 0; i < num_connect; i++) {
			// Read next line
			inputLine = si.nextline();
			twoLocation = inputLine.split(" ");
			Point p1 = new Point(twoLocation[0], twoLocation[1]);
			int intP1 = map(p1);
			intPairs.add(intP1);
			Point p2 = new Point(twoLocation[2], twoLocation[3]);
			int intP2 = map(p2);
			intPairs.add(intP2);
		}
		
	}

	/**
	 * converts point into an integer
	 * @param p
	 * @return
	 */

	public int map(Point p) {
		int mapValue = 0;
		int x = (int)p.getX();
		int y = (int)p.getY();
		
		mapValue = x * m + y + 1;
		return mapValue;
		
	}

	/***
	 * converts integer into a point
	 * @param i
	 * @return
	 */
	public  Point unmap(int i) {
		int x = 0;
		int y = 0;
		Point p = null;	
	
		if ( i % m == 0 ) {
			// edge of the matrix
			x = i / m;
			y = n - 1;
		} else {
			x = i / m;
			y = i % m;
			y -= 1;
		}
		// initilize P value
		p = Point(x, y);
		return p;
	}

	/***
	 * scans connections and populates UnionFind structure
	 */
	public void process_connections(){
		// define parent count
		int parent_count = 0;
		// initiate weightedquickunion
		uf = new WeightedQuickUnionUF(m*n);
		// weighted quick union
		int size_arraylistConnect = intpairs.size();
		for (int i = 0; i < size_arraylistConnect - 1; i++) {
			int p = intpairs[i];
			int q = intpairs[i + 1];
			if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
		}
		parent = uf.getParent();

		// Put parent array into grid
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) ｛
				gird[m][n] = parent[count];
				count++;
			}
		}

	}

	/**
	 * retrieve the connected sets from the UnionFind structure
	 * @return connected sets
	 */	
	public ArrayList<Point> retrieve_connected_sets() {
		// initiate point arraylist
		new ArrayList<Point> listp = new ArrayList<Point>();
		// define first parent point
		// ************** do I need always check null? ************
		int tem = 0;
		// transfer integer array to arraylist
		for (int i = 0; i < parent.length; i++) {
			// traversal parent array and put its value to point array list
			if (parent[i] != tem) {
				Point temp = unmap(parent[i]);
				listp.add(temp);
				// renew tem value
				tem = parent[i];
			}
		}

		return listp;
	}

	/**
	 * Tests whether two Cells are connected in the grid
	 * @param p1
	 * @param p2
	 * @return
	 */
	public boolean is_adjacent(Point p1, Point p2) {
		boolean result = false;
		int p1x = (int)p1.getX();
		int p1y = (int)p1.getY();
		int p2x = (int)p2.getX();
		int p2y = (int)p2.getY();

		if (p1x == p2x) {
			if ( p1y - p2y == 1 || p1y - p2y == -1 )
				result = true;
		} else if (p1y == p2y) {
			if ( p1x - p2x == 1 || p1x - p2x == -1 )
				result = true;
		}

		return result;
	}

	/**
	* The purpose of this function is to 
	*/
	public int[] findBundaries(int candidate) {
		// int[0] Xmin; int[1] Xmax; int[2] Ymin; int[3] Ymax
		int[] boundaryValue = new int[4];
		
		int Xmin = 0;
		int Xmax = 0;
		int Ymin = 0;
		int Ymax = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) ｛
				if (gird[m][n] == candidate) {
					if (m < Xmin)
						Xmin = m;
					if (m > Xmax)
						Xmax = m;
					if (n < Ymin)
						Ymin = n;
					if (n > Ymax)
						Ymax = n; 
				}
				
			}
		}

		boundaries[0] = Xmin;
		boundaries[1] = Xmax;
		boundaries[2] = Ymin;
		boundaries[3] = Ymax;

		return bundaryValue;
	}

	/**
	 * outputs the boundaries and size of each connected set
	 * @param sets
	 */
	public void output_boundaries_size(ArrayList<Point> sets) {
		// need to find set numeber - parents number perhaps

		// define an arraylist for recording set size 
		new ArrayList<integer> setofSetSize = new Arraylist<integer>();
		// size of parent point
		int parentSize = sets.size();
		// set size count
		int setSize = 0;

		// find corresponding arraylist size
		for (int cs = 0; cs < sets.size(); cs++) {
			Point temP = sets.get(cs);
			int temI = map(temP);
			// count set size
			for (int ps = 0; ps < parent.length; ps++) {
				if (parent[ps] == temI)
					setSize++;
			}
			// store corresponding size value into setSize
			setofSetSize.add(setSize);
			// reset setSize value;
			setSize = 0;
		}

		// output the result
		System.out.println("number of sets: %d", parentSize);
		// print second part of the result
		for (int i = 0; i < parentSize; i++) {
			//Point parent = unmap(sets[i])
			int xValue = (int)sets[i].getX();
			int yValue = (int)sets[i].getY();
			int outSetSize = sefofSetSize[i];
			System.out.println("Parent (%d, %d) with size %d", xValue, yValue, parentSize);
		}
		// print third part of the result
		for (int j = 0; j < parentSize; j++) {
			//Point parent = unmap(sets[j])
			int xValue = (int)sets[i].getX();
			int yValue = (int)sets[i].getY();
			int intParent = map(sets[i]);
			// implement find boundaries
			int[] boundariesValue = findBundaries(intParent);
			System.out.println("Bounds for parent (%d, %d): %d<=x<=%d %d<=y<=%d", 
				xValue, yValue, 
				boundariesValue[0], boundariesValue[1],
				boundariesValue[2], boundariesValue[3]);
		}		
	}



	public static void main(String args[]) {
		int m, n;
		Scanner input = new Scanner(System.in);
		System.out.print("Enter size of grid(m n): ");
		m = input.nextInt();
		n = input.nextInt();

		Project1 project1 = new Project1(m,n);
		project1.read_input();
		project1.process_connections();
		ArrayList<Point> sets = project1.retrieve_connected_sets();
		project1.output_boundaries_size(sets);
	}
}
