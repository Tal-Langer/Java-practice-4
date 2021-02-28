package mines;

import java.awt.Checkbox;
import java.util.Random;

public class Mines {
	Place[][] board;
	int numMines, width, height;
	boolean showAll;

	public Mines(int height, int width, int numMines) {// constructor
		board = new Place[height][width];
		this.numMines = numMines;
		this.width = width;
		this.height = height;
		showAll=false;
		set_places();
		Set_random_mines(numMines);

	}
	
	/*fill the board with places*/
	private void set_places() {
		for (int i = 0; i < height; i++) 
			for (int j = 0; j <width; j++)
				board[i][j]=new Place(0,i,j);//create new place (no mine)
	}

	public class Place {
		int mine_flag, is_open = 0, flag = 0;
		int i,j;

		public Place(int mine_flag,int i ,int j) {// constructor
			this.mine_flag = mine_flag;
			this.i=i;
			this.j=j;
		}
		public String toString()
		{
			if (!showAll) {
				
				if ((is_open == 0 && flag == 0))// place closed && not flag
					return ".";
				
				if (is_open == 0 && flag == 1)// place close && flag is set
					return "F";
			} // if(!showAll)

			if ((showAll || is_open == 1) && mine_flag == 1)// place open && mine
				return "X";

			/* Place is open && not mine && not flag */
			int neighbors = cnt_mines();
			if (neighbors == 0)// Neighbor mines=0
				return  " ";
			else
				return "" + neighbors;// print num of mines Neighbor
		}

		public boolean neighbors(int i, int j) {
/*up=row-- , down = row++ , left = col-- , right = col++ */
			
			if (i > 0)// check up
				if (board[i - 1][j].mine_flag == 1)
					return false;
			
			if (i < height - 1)// check down
				if (board[i + 1][j].mine_flag == 1)
					return false;
			
			if (j < width - 1)// check right
				if (board[i][j + 1].mine_flag == 1)
					return false;
			
			if (j > 0)// check left
				if (board[i][j - 1].mine_flag == 1)
					return false;
			
			if (i > 0 && j > 0)// check up and left
				if (board[i - 1][j - 1].mine_flag == 1)
					return false;
			
			if (i < height - 1 && j < width - 1)// check down and right
				if (board[i + 1][j + 1].mine_flag == 1)
					return false;
			
			if (i < height - 1 && j > 0)// check down and left
				if (board[i + 1][j - 1].mine_flag == 1)
					return false;
			
			if (i > 0 && j < width - 1)// check up and right
				if (board[i - 1][j + 1].mine_flag == 1)
					return false;
			
			return true; // no mines near the place
		}

		public int cnt_mines() {
			int cnt = 0;
			if (i > 0)// check up
				if (board[i - 1][j].mine_flag == 1)
					cnt++;
			if (i < height - 1)// check down
				if (board[i + 1][j].mine_flag == 1)
					cnt++;
			if (j < width - 1)// check right
				if (board[i][j + 1].mine_flag == 1)
					cnt++;
			if (j > 0)// check left
				if (board[i][j - 1].mine_flag == 1)
					cnt++;
			if (i > 0 && j > 0)// check up and left
				if (board[i - 1][j - 1].mine_flag == 1)
					cnt++;
			if (i < height - 1 && j < width - 1)// check down and right
				if (board[i + 1][j + 1].mine_flag == 1)
					cnt++;
			if (i < height - 1 && j > 0)// check down and left
				if (board[i + 1][j - 1].mine_flag == 1)
					cnt++;
			if (i > 0 && j < width - 1)// check up and right
				if (board[i - 1][j + 1].mine_flag == 1)
					cnt++;
			return cnt;

		}
	}// inner class

	public boolean addMine(int i, int j) {
		if(board[i][j].mine_flag==1)
			return false;// Place is already a mine*/
		
		board[i][j].mine_flag=1;
		return true;
	}

	private void Set_random_mines(int numMines) {
		int i, j;
		Random rand = new Random();
		/* add numMines to the board randomly */
		while (numMines > 0) {
			i = rand.nextInt(height);
			j = rand.nextInt(width);
			if (addMine(i, j))
				numMines--;
		}
	}

	public boolean open(int i, int j) {
		if (board[i][j].mine_flag == 1)// its a mine
			return false;
		if(board[i][j].is_open==1)
			return true;
		/*mark place has open*/
		board[i][j].is_open = 1;
		
		/*check if can open neighbors*/
		if (board[i][j].neighbors(i, j))// need to open the neighbors
		{
			/*all neighbors not mines*/
			if (i > 0)// check up
				open(i - 1, j);
			
			if (i < height - 1)// check down
				open(i + 1, j);
			
			if (j < width - 1)// check right
				open(i, j + 1);
			
			if (j > 0)// check left
				open(i, j - 1);
			
			if (i > 0 && j > 0)// check up and left
				open(i - 1, j - 1);
			
			if (i > 0 && j < width - 1)// check up and right
				open(i - 1, j + 1);
			
			if (i < height - 1 && j < width - 1)// check down and right
				open(i + 1, j + 1);
			
			if (i < height - 1 && j > 0)// check down and left
				open(i + 1, j - 1);

		}
		
		return true;
	}// open

	public void toggleFlag(int x, int y) {
		board[x][y].flag = board[x][y].flag == 0 ? 1 : 0;
	}

	public boolean isDone() {
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				if (board[i][j].is_open == 0 && board[i][j].mine_flag == 0)// not mine && not open
					return false;
			}
		return true;
	}

	public String get(int i, int j) {
		return board[i][j].toString();
	}

	public void setShowAll(boolean showAll) {
		this.showAll = showAll;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < height; i++) {
			for (int j = 0; j <width; j++)
				s += get(i, j);
			s += "\n";
		}
		return s;
	}
private static void main(String[] args) {
	Mines m = new Mines(3, 4, 0);
	m.addMine(0, 1);
	m.addMine(2, 3);
	m.open(2, 0);
	System.out.println(m);
	m.toggleFlag(0, 1);
	System.out.println(m);



}
}// class