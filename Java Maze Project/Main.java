import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;
import java.util.ArrayList;
import java.util.LinkedList;
public class Main implements ActionListener{
    Maze m; //Initialising the Maze variable m, which will be used in Generation and Solving of the maze
    Solve s;//Initialising the Solve Variable s, which will be used in Solving process
    JRadioButton Traditional , Block, Instant, DFS, BFS, AStar; //Initialising the JRadioButton Variables that will be the options on the Control Panel
    public static void main(String args[]){
        new Main(); //Running the constructor function for the main class.
    }
    public Main(){
        JFrame f = new JFrame();
        JPanel p = new JPanel();
        f.setSize(900,700);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(p);
        f.setLayout(null);
        //Creating a JFrame window, size 900x700, located in the middle of the screen
        //Adding the JPanel p, which will hold the Maze JPanels, and Setting the layout to null
        //Which allows for precise placement of JComponents on the JFrame
        p.setLayout(new GridLayout(20,20));
        p.setBorder(BorderFactory.createMatteBorder(1,1,1,1,Color.black));
        p.setLocation(10,10);
        p.setSize(640,640);
        //Setting the JPanel layout to a 20,20 grid to hold a 20x20 maze
        //Setting its location and size on the JFrame
        Maze maze = new Maze(20,20, 0);
        for(int i = 0 ; i < 20; i++){
            for(int j = 0; j < 20; j++){
                p.add(maze.Grid[i][j].p); //Adding the Mazes JPanels to the Main JPanel
                                           //Displaying the maze
            }
        }
        JPanel GenerationOptions = new JPanel();
        JPanel SolvingOptions = new JPanel();
        //Creating the JPanels which will hold the Generation and Solving Options
        //In the form of JRadioButtons and JTextFields
        GridBagConstraints c = new GridBagConstraints();
        GenerationOptions.setLayout(new GridBagLayout());
        //Setting the GenerationOptions' JPanel layout to GridBagLayout
        c.fill = GridBagConstraints.HORIZONTAL;
        Traditional = new JRadioButton("Traditional");
        Block = new JRadioButton("Block");
        Instant = new JRadioButton("Instant");
        DFS= new JRadioButton("DFS");
        BFS= new JRadioButton("BFS");
        AStar = new JRadioButton("A*");
        Traditional.addActionListener(this);
        Block.addActionListener(this);
        DFS.addActionListener(this);
        BFS.addActionListener(this);
        AStar.addActionListener(this);
        //Initialising the JRadioButtons with their correct text and adding an actionListener to each of them
        //So that only one can be selected at any one time
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        GenerationOptions.add(Traditional,c);
        //Adding the traditional JRadioButton to the GenerationOptions JPanel
        //In the top left corner
        c.gridx = 3;
        c.gridy = 0;
        c.gridwidth = 1;
        JTextField height= new SelectAllTextField("Height");
        //Initialising a new SelectAllTextField, a class which selects all the text in it when it gains focus
        //This SelectAllTextField will be used to input height into
        GenerationOptions.add(height,c);
        //Adding the new SelectAllTextField to the GenerationOptions in the top right corner
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        GenerationOptions.add(Block,c);
        //Adding the BlockMaze JRadioButton to the GenerationOptions
        c.gridx = 3;
        c.gridy = 1;
        c.gridwidth = 1;
        JTextField width = new SelectAllTextField("Width");
        //Initialising a new SelectAllTextField, which the user will input maze width into
        GenerationOptions.add(width,c);
        //Adding the SelectAllTextField width to GenerationOptions JPanel
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        GenerationOptions.add(Instant,c);
        c.gridx = 2;
        JTextField Delay = new SelectAllTextField("Delay");
        //Initialising new SelectAllTextField, which the user will input Generation Delay into
        GenerationOptions.add(Delay,c);
        //Adding the SelectAllTextField to GenerationOptions JPanel
        GenerationOptions.setLocation(670,10);
        GenerationOptions.setSize(200,130);
        GenerationOptions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Generation Options"));
        //Setting the location and Size of the JPanel on the JFrame and setting its border to a
        //Black line border, with title, "Generation Options"
        f.add(GenerationOptions);
        f.add(SolvingOptions);
        f.revalidate();
        GenerationOptions.revalidate();
        //Adding the JPanels to the Main JFrame, and revalidating to make sure it updates correctly
        SolvingOptions.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), "Solving Options"));
        SolvingOptions.setLocation(670,150);
        SolvingOptions.setSize(200,150);
        //Setting Size and Location of SolvingOptions and Giving it a black line border
        SolvingOptions.setLayout(new GridBagLayout());
        //Setting SolvingOptions layout to GridBagLayout
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        SolvingOptions.add(DFS,c);
        //Adding Depth First Search JRadioButton
        c.weightx = 0.5;
        c.gridx = 2;
        SolvingOptions.add(BFS,c);
        //Adding Breadth First Search JRadioButton
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0;
        SolvingOptions.add(AStar,c);
        //Adding A* First Search JRadioButton
        c.weightx = 0.5;
        c.insets = new Insets(0,9,1,0);
        //Giving left and bottom padding to the clear path JButton
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        JButton b3 = new JButton("Clear Path");
        SolvingOptions.add(b3,c);
        b3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearPaths(); //Adding a new ActionListener to ClearPath JButton
                //When pressed it will run the function clearPaths();
                //Which clears the path of any solved maze
            }
        });
        
        c.weightx =0.5;
        c.insets = new Insets(0,13,0,0);//13 Pixel padding at the left
        c.gridx = 3;
        c.gridy = 2;
        c.gridwidth = 2;
        SelectAllTextField SolvingDelay = new SelectAllTextField("Delay");
        SolvingOptions.add(SolvingDelay, c);
        //Adding Solving Delay to the Solving Options JPanel
        
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 4;
        JButton b4 = new JButton("Show Path");
        SolvingOptions.add(b4,c);
        //Adding the ShowPath button to Solving Options JPanel
        b4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                clearPaths(); //Clears the rest of the checked paths
                new Thread(){
                    public void run(){
                        s.showPath(m.end);//Adding a new ActionListener to ShowPath JButton
                        //Which shows the Actual path from top left to bottom right
                    }
                }.start(); //Runs it in a separate thread so that it is animated
                //On the main GUI
            }
        });
        
        JButton b = new JButton();
        b.setText("Generate");
        c.weightx = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        GenerationOptions.add(b,c);
        GenerationOptions.validate();
        GenerationOptions.revalidate();
        GenerationOptions.repaint();
        b.setLocation(680,10);
        b.setVisible(true);
        //Adding the JButton which will Generate the maze
        b.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){//Adding the ActionListener to the Generate Button
                int h ,w, delay;//Initialising Integer values
                try{
                    h = Integer.valueOf(height.getText());
                }catch(NumberFormatException ex){
                     h = 20;
                }//Attempt to get the Integer value of the text from the height SelectAllTextField
                //If there is a Number Format Exception, set default to 20
                try{
                    w = Integer.valueOf(width.getText());
                }catch(NumberFormatException ex){
                    w = 20;
                }//Attempt to get the Integer value of the text from the width SelectAllTextField
                //If there is a Number Format Exception, set default to 20
                if(!Instant.isSelected()){
                    try{
                        delay = Integer.valueOf(Delay.getText());
                    }catch(NumberFormatException ex){
                        delay = 20; 
                    }//Attempt to get the Integer value of the text from the delay SelectAllTextField
                    //If the Instant JRadioButton is not currently Selected
                    //If there is a Number Format Exception, set default to 20
                }
                else{
                    delay = 0; //If the Instant JRadioButton is selected
                }//Delay must = 0, so it produces instantly
                w = h; //Make sure height and width are equal to stop any errors
                if(Traditional.isSelected()){
                    m = new Traditional(h,w,delay);
                    //If the Traditional JRadioButton is selected, initialise a new
                    //Traditional Class, with parameters h,w,delay
                }
                else if(Block.isSelected()){//Otherwise if Block JRadioButton
                    //is selected
                    if(h % 2 == 0){
                        h++;
                        w =h;
                    } //Because for a BlockMaze the width and height must be odd
                    //These if statements make sure they are odd and equal in height and width
                    else if(w% 2 ==0){
                        w++;
                        h = w;
                    }
                    m = new BlockMaze(h,w,delay); //Initialise new BlockMaze
                }
                else{
                    m = null;
                }
                p.removeAll();
                f.validate();
                p.validate();
                p.setLayout(new GridLayout(h,w));
                //Remove all components from the JPanel and set GridLayout of
                //Height h and Width w, to prepare for the Maze that is going to be added
                for(int i = 0 ; i < h; i++){
                    for(int j = 0; j < w; j++){
                        p.add(m.Grid[i][j].p); //Go Through the whole maze adding all the JPanels to the Main JPanel
                    }
                }
                if(m instanceof BlockMaze){
                    for(int i = 0 ; i < h; i++){
                        for(int j = 0; j < w; j++){
                            m.Grid[i][j].p.setBackground(Color.black);
                            m.Grid[i][j].p.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Color.black));
                            m.Grid[i][j].p.revalidate();
                            //If the Maze is An Instance Of BlockMaze (i.e. is a BlockMaze type Maze)
                            //Make sure the Colour Scheme is correct as it is different and validate the JPanels of the maze
                            //To Make sure they update correctly
                        }
                    }
                }
                if(!Instant.isSelected()){
                    new Thread(){           //If the instant JRadioButton is not selected, and therefore the user wants the Generation Process to be animated
                        public void run(){ //Create a new Thread to make sure the JPanels update simultaneously so that it looks animated, instead of all being updated at the very end
                            if(m instanceof Traditional){
                                m.CreateMaze(m.GetAvailableMoves(m.Grid[0][0]), m.Grid[0][0], new Stack()); //If it is traditional start in the very top left (Grid[0][0])
                            }
                            else if(m instanceof BlockMaze){
                                m.CreateMaze(m.GetAvailableMoves(m.Grid[1][1]), m.Grid[1][1], new Stack()); //If it is block start 1 in from either side (Grid[1][1])
                            } //PolyMorphism as the methods are overridden in sub-classes
                        }
                    }.start(); //Start new thread, i.e. run the run method, beginning the generation process.
                }
                else if(Instant.isSelected()){
                    if(m instanceof Traditional){
                        m.CreateMaze(m.GetAvailableMoves(m.Grid[0][0]), m.Grid[0][0], new Stack());
                    } //Otherwise, if instant is selected, and user wants Generation Process Instant, dont run on multi-thread, update at very end all at once
                    else if(m instanceof BlockMaze){
                        m.CreateMaze(m.GetAvailableMoves(m.Grid[1][1]), m.Grid[1][1], new Stack());
                    }
                }
            }});
        JButton b2 = new JButton();
        b2.setText("Solve");
        b2.setLocation(680, 200);
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 4;
        SolvingOptions.add(b2,c);
        //Adding the Solve Button to the SolvingOptions JPanel at the very bottom
        f.validate();
        b2.setVisible(true);
        b2.addActionListener(new ActionListener(){ //Adding new actionListener to Solving button
            public void actionPerformed(ActionEvent e){
                int delay;
                try{
                    delay = Integer.valueOf(SolvingDelay.getText());
                }catch(NumberFormatException ex){
                    delay = 50;
                } //Attempt to get integer value from SolvingDelay SelectAllTextField
                //Default to 50 if unable to, due to NumberFormatException
                if(AStar.isSelected()){
                    s = new AStarSolve(m,delay); //If AStar Solving Option Selected, initialise new AStarSolve class, with parameter Maze m and integer delay
                }
                else if(DFS.isSelected()){
                    s = new DFSSolve(m,delay);   //If DFS Solving Option Selected, initialise new DFSSolve class, with parameter Maze m and integer delay
                }
                else if(BFS.isSelected()){
                    s = new BFSSolve(m,delay);   //If BFS Solving Option Selected, initialise new BFSSolve class, with parameter Maze m and integer delay
                }
                else{
                    s = null;
                }
                new Thread(){
                    public void run(){ //Create a new Thread to animate the solving process
                        Cell Current = m.start; //Set a new Cell object "Current" equal to the start of the maze, so the solving algorithms begin solving from the beginning of the maze
                        if(s instanceof AStarSolve){
                            ArrayList<Cell> OpenSet = new ArrayList<Cell>(); //Create new Cell ArrayList OpenSet for A* Algorithm
                            OpenSet.add(Current);//Add start of maze to the OpenSet
                            ArrayList<Cell> ClosedSet = new ArrayList<Cell>(); //Create new Cell ArrayList ClosedSet for A* Algorithm
                            ((AStarSolve) s).Solve(Current, OpenSet,ClosedSet);//Begin Solving the Maze using A* Algorithm
                        }
                        else if(s instanceof DFSSolve){
                            ArrayList<Cell> Checked = new ArrayList<Cell>(); //Create new Cell ArrayList Checked for DFS Algorithm
                            ((DFSSolve) s).Solve(Current,Checked,s.GetNeighbours(Current));//Begin Solving Maze using DFS Algorithm
                        }
                        else if(s instanceof BFSSolve){
                            LinkedList<Cell> ToCheck = new LinkedList<Cell>();//Create new LinkedList(Queue) object for Cells that need to be checked
                            ToCheck.push(Current);//Add the start of the maze to the ToCheck Queue
                            LinkedList<Cell> Checked = new LinkedList<Cell>();//Create a new LinkedList(Queue) object for Cells that have been checked
                            ((BFSSolve) s).Solve(Current,ToCheck,Checked,s.GetNeighbours(Current));//Begin Solving Maze using BFS Algorithm
                        }
                    }
                }.start();//Start solving maze on separate thread, animating the process.
            }
        });
        }
        public void clearPaths(){ //Function to clear any solved paths, and reverting back to blank maze state
            if(m instanceof Traditional){
                    for(int i = 0; i < m.HEIGHT; i++){
                        for(int j = 0 ; j < m.WIDTH; j++){             //Traditional Maze is all black, so reset to all black
                            m.Grid[i][j].p.setBackground(Color.black); //Clears all signs of a solving Attempt
                        }
                    }
                }
                else{
                    for(int i = 0; i < m.HEIGHT; i++){
                        for(int j = 0 ; j < m.WIDTH; j++){
                            if(m.Grid[i][j].visited){                   //BlockMaze is white on visited cells, so revert back to white
                                m.Grid[i][j].p.setBackground(Color.white);
                            }
                            else{                                       //BlockMaze is black on non visited cells, so revert back to black
                                m.Grid[i][j].p.setBackground(Color.black);
                            }
                        }
                    }
                }
        }
    public void actionPerformed(ActionEvent e){ //The ActionListener for the GUI
        JRadioButton source = (JRadioButton)e.getSource(); //Get the source of the Event, in the form of a JRadioButton
        if(source == Traditional && Block.isSelected()){ 
            Block.setSelected(false); //If attempting to select 2 JRadioButtons in GenerationOptions, deselect 1, as only one can be selected
        }
        else if(source == Block && Traditional.isSelected()){
            Traditional.setSelected(false);//If attempting to select 2 JRadioButtons in GenerationOptions, deselect 1, as only one can be selected
        }
        else if(source!=Traditional && source != Block){
            AStar.setSelected(false);
            BFS.setSelected(false);
            DFS.setSelected(false); //Deselect all the JRadioButtons
            source.setSelected(true); //Reselect the source JRadioButton
            //This Ensures only one Solving Algorithm can be selected at one time
        }
    }
    public class SelectAllTextField extends JTextField{ //New SelectAllTextField Class which extends the javax.swing.JTextField class
        public SelectAllTextField(String s){
           this.setText(s); //Set the text of the TextField to the Parameter s
           this.addFocusListener(new FocusListener(){ //Add a FocusListener to the TextField
            public void focusGained(FocusEvent e){
                SelectAllTextField.this.select(0,getText().length()); //On Focus Gained, select all the text in the TextField
            }
            public void focusLost(FocusEvent e){
                SelectAllTextField.this.select(0,0);//On Focus Lost, deselect all the text in the TextField
            }
            });
         }
        }
    }