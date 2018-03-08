///////////////////////////////////////////////////////////////////////////////
//                  
// Title:            (Dorm Designer 5000)
// Files:            (Button.java, Furniture.java, SaveButton.java,
//                    LoadButton.java, ClearButton.java, 
//                    CreateButton.java, DormGUI.java, RoomData.ddd, 
//                    DormDesigner.jar, background.png, bed.png, sofa.png,
//                    desk.png, dresser.png, sink.png)
// Semester:         (CS 300) Spring 2018
//
// Author:           (Bennett Majerowski)
// Email:            (majerowski@wisc.edu)
// Lecturer's Name:  (Gary Dahl)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     (John Gibson)
// Email:            (jdgibson@wisc.edu)
// Lecturer's Name:  (Gary Dahl)
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//
// Persons:          no other people
//
// Online sources:   "https://stackoverflow.com/questions/11437442/restarting-
//                     the-while-loop" courtesy of Eng.Fouad. This page helped
//                     us learn how to go back to the beginning of a while loop
//                     without finishing the code in the loop
//
//////////////////////////// 80 columns wide ////////////////////////////////// 

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * (This class opens a window in which the user can design
 *  a dorm room layout. They can create, rotate and delete
 *  furniture.)
 * <p>
 * Bugs: (no known bugs)
 * <p>
 * @author (Bennett Majerowski and John Gibson)
 */
public class Main {
	
	/**
	 * (Fields of Main)
	 */
	private PApplet processing;
	private PImage backgroundImage;
	private Furniture[] furniture;
	private CreateFurnitureButton bedButton;
	private CreateFurnitureButton sofaButton;
	private CreateFurnitureButton dresserButton;
	private CreateFurnitureButton deskButton;
	private CreateFurnitureButton sinkButton;
	private ClearButton clearButton;
	private SaveButton saveButton;
	private LoadButton loadButton;
	private ArrayList<DormGUI> guiObjects;
	
	/**
	 * Constructor that creates the all of the buttons and places these objects
	 * in a new ArrayList
	 * @param processing uses information from the jar file
	 */
	public Main(PApplet processing) {
		this.processing = processing;
		backgroundImage = processing.loadImage("images/background.png");
		bedButton = new CreateFurnitureButton("bed", 50, 24, processing);
		sofaButton = new CreateFurnitureButton("sofa", 150, 24, processing);
		dresserButton = new CreateFurnitureButton("dresser", 250, 24, processing);
		deskButton = new CreateFurnitureButton("desk", 350, 24, processing);
		sinkButton = new CreateFurnitureButton("sink", 450, 24, processing);
		clearButton = new ClearButton(550, 24, processing);
		saveButton = new SaveButton(650, 24, processing);
		loadButton = new LoadButton(750, 24, processing);
		guiObjects = new ArrayList<DormGUI>();
		guiObjects.add(bedButton);
		guiObjects.add(sofaButton);
		guiObjects.add(dresserButton);
		guiObjects.add(deskButton);
		guiObjects.add(sinkButton);
		guiObjects.add(clearButton);
		guiObjects.add(saveButton);
		guiObjects.add(loadButton);
	}

	/**
	 * (Main starts the application and runs it from the .jar file)
	 */
	public static void main(String[] args) {
		Utility.startApplication();
	}

	/**
	 * (Update runs repeatedly and checks for user input. It calls the other classes to
	 * save those changes. It reads the ArrayList to see if the user has moved any objects
	 * or created/deleted any objects)
	 */
	public void update() {
		processing.background(100,150,250);
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		for (int i=0; i<guiObjects.size(); i++) {
			guiObjects.get(i).update();
		}
	}		

	/**
	 * (Called when the mouse is pressed down and checks to see if the mouse 
	 * has clicked on an object. If it clicks on furniture, it moves the center of the 
	 * furniture to the mouses position and allows the furniture to be moved. If a create
	 * button is clicked, this method will call the class methods mouseDown.
	 * @throws FileNotFoundException if the file required is not found in directory)
	 */	
	public void mouseDown() throws FileNotFoundException {
		furniture = extractFurnitureFromGUIObjects();
		for (int i=0; i<guiObjects.size(); i++) {
			if (guiObjects.get(i).isMouseOver()) { 
				guiObjects.get(i).mouseDown(furniture);
				if (guiObjects.get(i).equals(bedButton) || guiObjects.get(i).equals(sofaButton)) {
					for (int j=0; j<furniture.length; j++) {
						if (furniture[j] == null) {
							guiObjects.add(furniture[j-1]);
							break;
						}
					}
				}
			break;
			}
		}
		replaceFurnitureInGUIObjects(furniture); //puts furniture in array back into ArrayList
	}
	
	/**
	 * (Called after the mouse button is released and it further calls
	 * the the mouseUp method for the desired furniture object to make
	 * the bed stay where it is release.)
	 */
	public void mouseUp() {
		furniture = extractFurnitureFromGUIObjects();
		for (int i=0; i<furniture.length; i++) {
			if (furniture[i] == null) {
				break;
			}
			else {
				furniture[i].mouseUp();
			}
		}
	}

	/**
	 * (Checks any keys pressed by the user and, if it is one of 'd' or 'r',
	 * calls the furniture class to delete or rotate that furniture object)
	 */
	public void keyPressed() {
		furniture = extractFurnitureFromGUIObjects();
		if(processing.key == 'D' || processing.key == 'd') {
			for (int i=0; i<guiObjects.size(); i++) {
				if (guiObjects.get(i).isMouseOver() && guiObjects.get(i) instanceof Furniture) {
					guiObjects.remove(i);
				}
			}
		}
		else if(processing.key == 'R' || processing.key == 'r') {
			for (int i=0; i<guiObjects.size(); i++) {
				if (guiObjects.get(i).isMouseOver() && guiObjects.get(i) instanceof Furniture) {
					Furniture toRotate = (Furniture)guiObjects.get(i);
					toRotate.rotate();
					replaceFurnitureInGUIObjects(furniture);
				}
			}
		}
	}
	
	// Max number of furniture that LoadButton will be allowed to load at once.    
	private final static int MAX_LOAD_FURNITURE = 100;        
	/**
	 * This method creates a new Furniture[] for the old mouseDown() methods
	 * to make use of.  It does so by copying all Furniture references from
	 * this.guiObjects into a temporary array of size MAX_LOAD_FURNITURE.
	 * @return that array of Furniture references.
	 */
	private Furniture[] extractFurnitureFromGUIObjects() {
	    Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
	    int nextFreeIndex = 0;
	    for(int i=0;i<guiObjects.size() && nextFreeIndex < furniture.length;i++)
	        if(guiObjects.get(i) instanceof Furniture)
	            furniture[nextFreeIndex++] = (Furniture)guiObjects.get(i);        
	    return furniture;        
	}    
	/**
	 * This method first removes all Furniture references from this.guiObjects,
	 * and then adds back in all of the non-null references from it's parameter.
	 * @param furniture contains the only furniture that will be left in 
	 *   this.guiObjects after this method is invoked (null references ignored).
	 */
	private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
	    for(int i=0;i<guiObjects.size();i++)
	        if(guiObjects.get(i) instanceof Furniture)
	            guiObjects.remove(i--);
	    for(int i=0;i<furniture.length;i++)
	        if(furniture[i] != null)
	            guiObjects.add(furniture[i]);
	}
}
