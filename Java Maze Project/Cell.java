import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
public class Cell{
    int i, j, g,h,f;
    boolean visited;
    JPanel p;
    Cell parent;
    int[] walls = new int[4];
    public Cell(int i, int j){
        for(int z = 0; z < 4; z++){
            this.walls[z] = 1;
//To Begin with, every cell should have all their walls present, and the walls should be manipulated while being generated. This is done by storing the data in a single dimension integer list, which is stored by every single cell object. A 1 indicates a wall of thickness 1, and therefore present, and a 0 indicates a non present wall. The BorderFactory class requires the borders to be kept in the order Top-Left-Bottom-Right, and so the walls are manipulated accordingly. A Static data structure is appropriate here, as the wall number will never change, only the data inside will change.
        }
        this.i = i;
        this.j = j;
//Each cell is constructed with a specific i and j value, basically x’s and y’s of the cell but in vector form. Each cell knows where they are positioned in the maze, which is useful when comparing cells to one another and finding information about the cells next to the current cell.
        this.p = new JPanel();
	   //The main attribute of each cell object is their own JPanel as this will make up the visuals of the maze, and the JPanel of specific cells will need to manipulated so that the borders and background colours are appropriate for the current state of the cell.
        this.p.setBackground(Color.white);
	   //Default Colour of the maze is white
        this.p.setBorder(BorderFactory.createMatteBorder(this.walls[0], this.walls[1], this.walls[2], this.walls[3], Color.black));
//Draw the borders of the JPanel to a matte black border with thickness of 1 all around the cell.
        this.visited = false;
	   //A Boolean attribute that is used in the generation process so that a cell that is already in the maze cannot be used again so this makes no loops, and creates “perfect mazes”. Therefore, the default of the visited Boolean should be set to false
        this.g = g;
        this.h = h;
        this.f = f;
	  //Each cell stores a integer value for each cost in the A* algorithm, a g cost, which describes how far the cell is from the beginning of the maze, a bigger g cost is further from the beginning of the maze, a h cost, which stores the optimal distance from the end of the maze, if there were no obstacles, lower h cost = closer to the end of the maze, and the f cost, is the combined cost of the g and h, which gives the overall efficiency of moving to that point in the maze.
        this.parent = parent;
//Each cell stores information about its parent cell, as this is used in re-creating the path taken to get to the end, as the path can be reconstructed re-using the parent of the current cell, until it reaches the start of the maze again.	
    }
    public void drawWalls(){
        this.p.setBorder(BorderFactory.createMatteBorder(this.walls[0], this.walls[1], this.walls[2], this.walls[3], Color.white));
    } //An abstraction and decomposition of re-drawing the walls of a specific cell, so that the code is not cluttered and is more clear and readable
    public void clearPath(){
        this.p.setBackground(Color.black);
        this.visited = true;
    } //Simple code to make sure the code is not cluttered and is easy to read, just changes the background colour of the cell and sets its visited attribute to true.
    public void clearWalls(Cell Previous){
//A simple function for deleting the wall between two cells, which is required when generating a traditional maze. Which wall to take down can be calculated by checking the position of each cell and comparing them, and determining which way the maze must have been moving to get between them, and deleting the appropriate wall.
        if(Previous.j == this.j){
            if(this.i > Previous.i){
                this.walls[0] = 0;
                Previous.walls[2] = 0;
            }
            else if(this.i < Previous.i){
                this.walls[2] = 0;
                Previous.walls[0] = 0;
            }
        }
        else if(Previous.i == this.i){
            if(this.j > Previous.j){
                this.walls[1] = 0;
                Previous.walls[3] = 0;
            }
            else if(this.j < Previous.j){
                this.walls[3] = 0;
                Previous.walls[1] = 0;
            }
        }
        Previous.drawWalls();
        this.drawWalls();
//Drawing the walls after altering the wall values for each cell.
    }
} 
