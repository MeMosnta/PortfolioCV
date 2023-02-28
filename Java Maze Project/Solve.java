import java.util.ArrayList;
import java.awt.Color;
public class Solve{
    Cell start, end;
    Cell[][] Grid;
    final int HEIGHT, WIDTH, DELAY;
    Maze m;
    public Solve(Maze m,int d){ //Cell[][] Grid > Maze m
        this.start = m.start;
        this.end = m.end;
        this.HEIGHT = m.HEIGHT;
        this.WIDTH = m.WIDTH;
        this.Grid = m.Grid;
        this.m = m;
        this.DELAY = d;
        //Set the variables of the start, end, HEIGHT, WIDTH, Grid, Maze, DELAY
        //Specific to this instance of the Maze class
    }
    public ArrayList<Cell> GetNeighbours(Cell Current){//Get the Neighbours that the solving algorithms will use when attempting to solve the Mazes
        int x = Current.i;
        int y = Current.j;
        ArrayList<Cell>Neighbours = new ArrayList<Cell>();
        if(this.m instanceof Traditional){
            if(Current.walls[2] == 0 && Current.i !=HEIGHT){
                Neighbours.add(Grid[Current.i+1][Current.j]);
            }
            if(Current.walls[3] == 0 && Current.j != WIDTH){
                Neighbours.add(Grid[Current.i][Current.j+1]);
            }
            if(Current.walls[0] == 0 && Current.i != 0){
                Neighbours.add(Grid[Current.i-1][Current.j]);
            }
            if(Current.walls[1] == 0 && Current.j != 0){
                Neighbours.add(Grid[Current.i][Current.j-1]);
            }
        }//If the maze is in an instance of the Traditional Class
        //If the walls are not present between to cells, that is an available neighbour
        else if(this.m instanceof BlockMaze){
            if(x<=this.HEIGHT-2){
            if(this.Grid[x+1][y].visited){
                Neighbours.add(this.Grid[x+2][y]);
            }
        }
        if(x>1){
            if(this.Grid[x-1][y].visited){
                Neighbours.add(this.Grid[x-2][y]);
            }
        }
        if(y<this.WIDTH - 2){
            if(this.Grid[x][y+1].visited){
                Neighbours.add(this.Grid[x][y+2]);
            }
        }
        if(y>1){
            if(this.Grid[x][y-1].visited){
                Neighbours.add(this.Grid[x][y-2]);
            }
        }
        }//If the Maze is an instance of the BlockMaze class, check whether the
        //Tile has been visited next to it as it only wants the moves that are actually connected
        return Neighbours;
    }
    public void showPath(Cell Current){
        if(Current != this.start){
            Current.p.setBackground(Color.magenta);
            if(this.m instanceof BlockMaze){
                this.Grid[Current.parent.i + (Current.i - Current.parent.i)/2][Current.parent.j + (Current.j - Current.parent.j)/2].p.setBackground(Color.magenta);
            }
            Current = Current.parent;
            Current.p.setBackground(Color.magenta);
        }
        else if(Current == this.start){
            return;
        }
        try{
            Thread.sleep(this.DELAY / 3);
        }catch(Exception e){}
        showPath(Current);
        //Recursively set the current cell to parent of the current cell, highlighting the maze
        //Along the way, and adding a DELAY so that it is animated
    }
}