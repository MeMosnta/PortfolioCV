import java.util.ArrayList;
import java.awt.Color;
public class AStarSolve extends Solve{//A* Solving Algorithm Extending the Superclass Solving
    public AStarSolve(Maze m,int d){//Obtaining the functions and variables already defined in that class
        super(m,d); //Sends the parameters Maze m, and int d, to the SuperClass constructor Function
    }       //Which Sets the variables for HEIGHT,WIDTH,start and end of the maze
    public void Solve(Cell Current, ArrayList<Cell>OpenSet, ArrayList<Cell>ClosedSet){
        for(int i = 0 ; i < ClosedSet.size(); i++){
            ClosedSet.get(i).p.setBackground(Color.red);
            ClosedSet.get(i).p.validate();
            ClosedSet.get(i).p.revalidate();
            ClosedSet.get(i).p.repaint();
        }//Looping through all of the ClosedSet of cells(the cells that have been checked)
        //And colouriong them red, and validating them to make sure they update correctly
        for(int i = 0 ; i < OpenSet.size(); i++){
            OpenSet.get(i).p.setBackground(Color.green);
            OpenSet.get(i).p.validate();
            OpenSet.get(i).p.revalidate();
            OpenSet.get(i).p.repaint();
        }//Looping through all of the OpenSet(The cells that are to be checked)
        //And colouring them green and validating them to make sure they update correctly
        if(OpenSet.isEmpty()){ //If there are no cells to check
            return; //Stop calling this recursive function
        }
        else if(!OpenSet.isEmpty()){ //Otherwise, if there are cells to check
            if(this.m instanceof BlockMaze){ //And this maze is a block maze
                for(int i = 0; i < super.GetNeighbours(Current).size(); i++){
                    this.Grid[Current.i + (super.GetNeighbours(Current).get(i).i - Current.i)/2][Current.j + (super.GetNeighbours(Current).get(i).j - Current.j)/2].p.setBackground(Color.red);
                } //Correct the colouring as each move needs to be 2 colours instead of 1
            }
            Current = GetNext(OpenSet, ClosedSet, Current);
            //Move onto the lowest F Score cell in the OpenSet, Cell type returned from GetNext function
            //And set current equal to it
            if(Current == this.end){ //If the Current cell is the end of the maze
                this.end.p.setBackground(Color.blue); //Colour it blue, indicating it has bene found
                OpenSet.clear();//Clear the OpenSet of cells to check, as the end has been found
                return; //Return, and continue returning until all stack instances are deleteed
            }
            ArrayList<Cell>Neighbours = GetNeighbours(Current);
            //Create a new Cell ArrayList to store the information about the Neighbours of the Current Cell
            EvaluateNeighbours(Current, Neighbours, OpenSet, ClosedSet); // Calculate g, h, f values for neighbours of cell
             try{
                 Thread.sleep(this.DELAY);//Add a delay, to make the solving process look animated
             }catch(Exception e){}
             Solve(Current, OpenSet,ClosedSet);//Recursively call the function to solve the maze
             //Which accepts the Current cell, OpenSet, ClosedSet
        }
        
    }
    public void EvaluateNeighbours(Cell Current, ArrayList<Cell>Neighbours,ArrayList<Cell>OpenSet, ArrayList<Cell>ClosedSet){
        for(int i = 0; i < Neighbours.size(); i++){ //Loop through all the neighbours
            if(!ClosedSet.contains(Neighbours.get(i))){ //If the Neighbours has not already been checked
                //And is therefore not in the ClosedSet
               int DistanceFromStart = Current.g;//The distance from the start is the g cost of the current cell
               //As it is the distance the path has taken since the start of the maze.
               if(!OpenSet.contains(Neighbours.get(i))){ //If the neighbour is not already discovered (Not in the closed set, and openset)
                   OpenSet.add(Neighbours.get(i));//Add the neighbourto the openset
                   Neighbours.get(i).g = DistanceFromStart + 10;//Make the g score the distance from the start + the distance required to get the neighbour
                   Neighbours.get(i).parent = Current; //Make the parent node of the neighbour the current cell
               }
               else if(Neighbours.get(i).g > DistanceFromStart + 10){ //Otherwise if the neighbour has been discovered already, make sure the g score of the path is the lowest possible
                   Neighbours.get(i).g = DistanceFromStart + 10;//As this means it will take the most efficient path to that point
                   Neighbours.get(i).parent = Current;//As the path has changed change the neighbour's parent must also changed
                }
            int h = CalcHeuristic(Neighbours.get(i).i, Neighbours.get(i).j); //Calculate the h cost of the neighbour
            Neighbours.get(i).h = h;//Set the h cost of the neighbour to h cost calculated
            Neighbours.get(i).f = Neighbours.get(i).g + Neighbours.get(i).h; //Add the h cost and g cost to get the total f cost.
        }
    }
    }
    public int CalcHeuristic(int x , int y){ //Calculate heuristic cost of cell at int x, and int y
        int distance = 0;
        while(!(x==this.end.i&& y ==this.end.j)){ //While the checking has not reached the end
            if(x==this.end.i && y < this.end.j){ //If y is not equal to the end y, but x is
                y++;        //Add one to the y variable
                distance+=10;//Increment distance by 10
            }
            else if(x < this.end.i && y == this.end.j){ //If x is not equal to the end x, but y is
                x++;        //Add one to the x variable
                distance+=10;//Increment distance by 10
            }
            else if(x < this.end.i && y < this.end.j){//If both are not equal
                x++;//Increment x and y by 1
                y++;
                distance+=14;  //Increment distance by 14, as it should be more efficient moving diagonally then straight
            }
        }
        return distance;//Return ineger value for distance
    }
    public Cell GetNext(ArrayList<Cell>OpenSet, ArrayList<Cell>ClosedSet, Cell Current){
        Cell next;
        int temp = 0;
        for(int i = 0 ; i < OpenSet.size(); i++){
            if(OpenSet.get(i).f < OpenSet.get(temp).f){
                temp = i;
            }
            else if(OpenSet.get(i).f == OpenSet.get(temp).f){
                if(OpenSet.get(i).g > OpenSet.get(temp).g){
                    temp = i;
                }
            }
        }//Loop through all the potential cells to move (OpenSet) and get the lowest f score cell, the most efficient move
        //Otherwise if the f scores are equal, take the one with the highest g score, as this indicates a cell furthur from the end
        next = OpenSet.get(temp);
        OpenSet.remove(next);
        ClosedSet.add(next);
        //Remove the cell from OpenSet(Cells to Check) and add to ClosedSet(Cells Checked)
        if(next == this.end){
            this.end.parent = Current;
            OpenSet.clear();
            ///If the next cell is the end, set the parent of the end to the current cell, and clear the OpenSet, so it indicates there are no cells to check
        }
        return next;//Return next cell.
    }
}