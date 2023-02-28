import java.util.ArrayList;
import java.util.Stack;
public class Maze{
    int HEIGHT,WIDTH, DELAY;
    Cell[][] Grid;
    Cell start, end;
    public Maze(int h, int w, int d){
        this.HEIGHT = h;
        this.WIDTH = w;
        this.DELAY = d;
        this.Grid = new Cell[h][w];
        //Sets the private variables of HEIGHT,WIDTH,DELAY,Grid for this instance of the Maze class
        for(int i = 0; i < w; i++){
            for(int j = 0; j < h; j++){
                this.Grid[i][j] = new Cell(i,j);
            }
        }//Create a new Grid of Cells for a Maze
        if(this instanceof Traditional){
            this.start = this.Grid[0][0];
            this.end = this.Grid[this.Grid.length - 1][this.Grid.length-1];
            //If the maze is a traditional maze, the start is top left corner
            //And the end is the very bottom left corner
        }
        else if(this instanceof BlockMaze){
            this.start = this.Grid[1][1];
            this.end = this.Grid[this.Grid.length - 2][this.Grid.length-2];
            //If the maze is a BlockMaze, the start is 1 in from each side,
            //the end is one in from each side in the bottom left
        }
    }
    public void CreateMaze(ArrayList<Cell> PotentialMoves, Cell Current, Stack s){
        if(PotentialMoves.size() > 0){
            int next = (int)(Math.random() * PotentialMoves.size());
            Cell Previous = Current;
            while(PotentialMoves.get(next).visited){
                next = (next+1)%(PotentialMoves.size());
            }
            Current = PotentialMoves.get(next);
            Previous.clearPath();
            Current.clearPath();
            Current.clearWalls(Previous);
            s.push(Current);
            Current.visited = true;
        }
        else if(PotentialMoves.size() == 0 && !s.isEmpty()){
            while(GetAvailableMoves(Current).size() == 0 && !s.isEmpty()){
                Current = (Cell)s.pop();
            }
        }
        else if(PotentialMoves.size() == 0 && s.isEmpty()){
            return;
        }
        if(this.DELAY>0){
            try{
                Thread.sleep(this.DELAY);
            }catch(Exception e){}
        }
        CreateMaze(GetAvailableMoves(Current), Current, s);
        //These functions get overriden in the Subclasses, and are just placeholders, as they are never run
    }
    public ArrayList<Cell> GetAvailableMoves(Cell Current){
        int x = Current.i;
        int y = Current.j;
        ArrayList<Cell> AvailableMoves = new ArrayList<Cell>();
        if(x<this.WIDTH-1){
            if(!Grid[x+1][y].visited){
                AvailableMoves.add(Grid[x+1][y]);
            }
        }
        if(x>0){
            if(!Grid[x-1][y].visited){
                AvailableMoves.add(Grid[x-1][y]);
            }
        }
        if(y<this.HEIGHT-1){
            if(!Grid[x][y+1].visited){
                AvailableMoves.add(Grid[x][y+1]);
            }
        }
        if(y>0){
            if(!Grid[x][y-1].visited){
                AvailableMoves.add(Grid[x][y-1]);
            }
        }
        return AvailableMoves;
    }
}