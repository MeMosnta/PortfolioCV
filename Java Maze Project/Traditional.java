import java.util.ArrayList;
import java.util.Stack;
public class Traditional extends Maze{//Traditional maze class, extends the SuperClass Maze
    public Traditional(int h,int w, int delay){
        super(h,w, delay); //Sends the parameters, int h, int w, and int delay to the SuperClass constructor function, setting the HEIGHT, WIDTH and DELAY of the maze
    }
    @Override
    public void CreateMaze(ArrayList<Cell> PotentialMoves, Cell Current, Stack s){
        if(PotentialMoves.size() > 0){ //If the maze can move
            int next = (int)(Math.random() * PotentialMoves.size()); //Select a random direction from the amount of directions that it can take
            Cell Previous = Current; //Keep a record of the Previous "Current" cell
            while(PotentialMoves.get(next).visited){
                next = (next+1)%(PotentialMoves.size());
                //Make sure the next cell has not been visited by looping through the PotentialMoves and making sure that it can be moved to
            }
            Current = PotentialMoves.get(next);
            //Set the Current cell equal to one of the Cells from the PotentialMoves ArrayList
            Previous.clearPath();
            Current.clearPath();
            Current.clearWalls(Previous);
            s.push(Current);
            Current.visited = true;
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
        if(this.DELAY>0){
            try{
                Thread.sleep(this.DELAY); //If there are is a delay to be added, add a delay which animates the generation process.
            }catch(Exception e){}
        }
        CreateMaze(GetAvailableMoves(Current), Current, s); //Recursively call the function with paramters of ArrayList<Cell>, Cell, and Stack
    }
    @Override
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
        //For each of the 4 direction, check whether the the Cells have been visited or not, if they havent they are added to an ArrayList of available moves for the maze to take
        return AvailableMoves;//Return the ArrayList of available moves for the maze to move to.
    }
}