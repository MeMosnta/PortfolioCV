import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
public class DFSSolve extends Solve{//DFS Solving Algorithm extends SuperClass Solving
    boolean Solved = false; //The solved variable indicates whether the maze has been solved
    public DFSSolve(Maze m,int d){
        super(m,d);//Send the parameters, Maze m, and Integer d to the SuperClass constructor
    }
    public void Solve(Cell Current, ArrayList<Cell> Checked, ArrayList<Cell>Neighbours){
        if(Current == this.end || Solved){ //If the current is equal to the end or the Maze has been solved
            Solved = true; //Make solved true
            return;//Return, and delete all instances of the recursive function in the call stack.
        }
        else if(Neighbours.size() > 0 && !Solved){ //Otherwise if the Cell has neighbours to check and the maze is not solved
            Current.p.setBackground(Color.red);//Change current cell background colour to red
            Checked.add(Current); //Add the current cell to the ArrayList of checked cells
            for(int i = 0; i < Neighbours.size(); i++){  //Loop through all of the cells
                if(!Checked.contains(Neighbours.get(i))){ //If the neighbour has not been checked and the maze is not solved
                    Neighbours.get(i).parent = Current;
                    Current = Neighbours.get(i);
                    //Set neighbours parent to current, and set current cell to the neighbour
                    if(this.m instanceof BlockMaze){
                        this.Grid[Current.i + (Current.parent.i - Current.i)/2][Current.j + (Current.parent.j - Current.j)/2].p.setBackground(Color.red);
                        //If the maze is a BlockMaze type, correct the colouring as each move is 2 instead of one.
                    } 
                    if(Current == this.end){ //If the current cell is the end
                        Solved = true; //The maze has beeb solved
                        Current.p.setBackground(Color.green); //Set the Current(end) cell to green, indicating it has been found
                        return; //Return, and delete all instances of the Recursive Function
                    }
                    try{
                        Thread.sleep(this.DELAY); //Add a delay to the process, animating it,
                    }catch(Exception e){}
                    Current.p.setBackground(Color.red);
                    Solve(Current,Checked,GetNeighbours(Current));
                    //Set the current cell to red, and call the recursive function
                }
            }
        }
    }
}