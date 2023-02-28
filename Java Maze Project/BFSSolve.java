import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
public class BFSSolve extends Solve{//BFS Solving Algorithm extends the Super Class solve
    public BFSSolve(Maze m,int d){
        super(m, d); //Send the Maze m, and integer d to the Super Class constructor function
    }
    public void Solve(Cell Current, LinkedList<Cell> ToCheck, LinkedList<Cell> Checked, ArrayList<Cell> Neighbours){
        if(ToCheck.isEmpty()){
            return; //If there are no cells to check, return
        }
        else if(!ToCheck.isEmpty()){ //Otherwise if there are cells to check
           Current = ToCheck.remove();
           //Remove from the front of the ToCheck queue and set the current cell equal to the first Cell ToCheck
           for(int i = 0; i < super.GetNeighbours(Current).size();i++){
               if(Checked.contains(super.GetNeighbours(Current).get(i)) && this.m instanceof BlockMaze){
                   this.Grid[Current.i + (super.GetNeighbours(Current).get(i).i - Current.i)/2][Current.j + (super.GetNeighbours(Current).get(i).j - Current.j)/2].p.setBackground(Color.red);
               }
            }//Loop through all the neighbours using the SuperClass function GetNeighbours, and correct the Colouring, if the maze type is a BlockMaze type.
           if(Current == this.end){
               Current.p.setBackground(Color.green);
               return;
            }//If the current cell is the end of the maze, it has been soloved and the recursive function should return, and set the cell colour to green indiciating it has been found
           for(int i = 0; i < Neighbours.size(); i++){
               if(!ToCheck.contains(Neighbours.get(i)) && !Checked.contains(Neighbours.get(i))){
                   ToCheck.add(Neighbours.get(i));
                   Neighbours.get(i).parent = Current;
               }
           }//Loop through all of the neighbours of the current cell, and if the neighbour is not already into check and it has not already been checked, add the neighbour to the ToCheck queue
           //Set the neighbours parent equal to the Current cell.
           Checked.add(Current);
           Current.p.setBackground(Color.red);
           //Add the current cell to the Checked Queue, and set the background colour of the current cell to red.
           try{
               Thread.sleep(this.DELAY); //Add a delay to the solving process, animating it
            }catch(Exception e){}
           Current = ToCheck.peek();
           Solve(Current,ToCheck,Checked,GetNeighbours(Current));
           //Change the current cell, by looking at the front of the queue, and call the recursive function with a new variables
        }
    }
}