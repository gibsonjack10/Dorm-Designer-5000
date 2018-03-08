import java.io.File;
import java.io.PrintWriter;

/**
 * This class saves the room design that has been created in the java window
 * to a file RoomData.ddd. In the case that the array cannot be saved, 
 * it will print a message detailing the error that is occurring.
 * <p>
 * Subclass of: (Button.java)
 * <p>Bugs: (no known bugs)
 * @author John Gibson and Bennett Majerowski
 *
 */
public class SaveButton extends Button implements DormGUI {
	private float[] position;
	private File f;
	
	/**
	 * Constructor that creates the Save Button
	 * @param x is the horizontal location of the button
	 * @param y is the vertical location of the button
	 * @param processing uses information from the jar file
	 */
	public SaveButton(float x, float y, PApplet processing) {
		super(x,y,processing);
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = setLabel("Save Room");
	}
	
	/**
	 * (this method is called when the mouse clicks the save button. It saves the furniture design
	 * on the screen to an external file. Values that it stores are x-position, y-position,
	 * furniture type, and number of rotations. It stores these values in the RoomData.ddd
	 * file in a way that can be parsed and read in by the Load Button.)
	 * @param furniture array used to put in the data about individual pieces of
	 * furniture
	 */
	public void mouseDown(Furniture furniture[]) {
		if (isMouseOver()) {
			try {
				f = new File("RoomData.ddd");
				PrintWriter pw = new PrintWriter(f);
				for (int i = 0; i < furniture.length; i++) {
					if (furniture[i] == null) {
						pw.println();
					} else {
						pw.println(furniture[i].getType() + 
								":" + furniture[i].getXPosition() + 
								"," + furniture[i].getYPosition() + 
								"," + furniture[i].getRotation());
					}
				}	
				pw.close();
			} catch (Exception e) {
				System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
			}
		}
	}
}
