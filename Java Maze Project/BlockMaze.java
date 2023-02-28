import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Stack;
import java.util.ArrayList;
public class BlockMaze extends Maze{ //BlockMaze class extends the Maze SuperClass
    public BlockMaze(int h,int w, int delay){
        super(h,w, delay); //Send the parameters of int h, int w, and int delay, to the SuperClass constructor
    }
    @Override
    public void CreateMaze(ArrayList<Cell> PotentialMoves, Cell Current, Stack s){
        if(PotentialMoves.size()>0){ //If the maze can move
            Current.visited = true;//Set the current cell's boolean visited to true
            int next = (int)(Math.random()*PotentialMoves.size());//Select a random direction from the amount of directions that it can take
            while(PotentialMoves.get(next).visited){
                next=(next+1)%(PotentialMoves.size());
                //Make sure the next cell has not been visited by looping through the PotentialMoves and making sure that it can be moved to
            }
            Cell nextCell = PotentialMoves.get(next);
            //Set the Current cell equal to one of the Cells from the PotentialMoves ArrayList
            ClearPath(Current, nextCell);
            Current = nextCell;
            Current.visited = true;
            s.push(Current);
            //Clear the path of Previous and current cells, and remove the Wall between the two, and add the Current cell to the Stack,and make the current cell's visited boolean true.
        }
        else if(PotentialMoves.size() == 0 && !s.isEmpty()){
            while(GetAvailableMoves(Current).size() == 0 && !s.isEmpty()){
                Current = (Cell)s.pop();
            }
            //Otherwise if there are not potential moves and the maze can backtrack using the stack(not empty)
            //While the same condition applies, keep popping from the stack, and backtracking while doing so.
        }
        else if(PotentialMoves.size() == 0 && s.isEmpty()){
            return; //Otherwise if there are no potentialmoves and the stack is empty, and therefore cannot backtrack
            //It must be finished generating the maze as the stack has been fully backtracked
        }
        if(this.DELAY > 0){
            try{
                Thread.sleep(this.DELAY); //If there are is a delay to be added, add a delay which animates the generation process.
            }catch(Exception e){}
        }
        CreateMaze(GetAvailableMoves(Current),Current, s);//Recursively call the function with paramters of ArrayList<Cell>, Cell, and Stack
    }
    public void ClearPath(Cell Current, Cell nextCell){
        Current.p.setBackground(Color.white);//Set Current cell background to white
        int iDiff = nextCell.i - Current.i;
        int jDiff = nextCell.j - Current.j;
        iDiff*=0.5;
        jDiff*=0.5;
        //Calculate the difference in i and j from the current and nextCell
        this.Grid[Current.i + iDiff][Current.j + jDiff].p.setBackground(Color.white);//Change the cell in between the Current and nextCell, to background white
        this.Grid[Current.i + iDiff][Current.j + jDiff].visited = true;//And visited = true
        nextCell.p.setBackground(Color.white);//Change nextCell to white
    }
    @Override
    public ArrayList<Cell> GetAvailableMoves(Cell Current){
        int x = Current.i;
        int y = Current.j;
        ArrayList<Cell> AvailableMoves = new ArrayList<Cell>();
        if(x<this.HEIGHT-2){
            if(!Grid[x+2][y].visited){
                AvailableMoves.add(Grid[x+2][y]);
            }
        }
        if(x>2){
            if(!Grid[x-2][y].visited){
                AvailableMoves.add(Grid[x-2][y]);
            }
        }
        if(y<this.WIDTH-2){
            if(!Grid[x][y+2].visited){
                AvailableMoves.add(Grid[x][y+2]);
            }
        }
        if(y>2){
            if(!Grid[x][y-2].visited){
                AvailableMoves.add(Grid[x][y-2]);
            }
        }
        //For each of the 4 direction, check whether the the Cells have been visited or not, if they havent they are added to an ArrayList of available moves for the maze to take
        return AvailableMoves;//Return the ArrayList of available moves for the maze to move to. Checks to cells into the distance because it moves 2 cells every time it moves
    }
}