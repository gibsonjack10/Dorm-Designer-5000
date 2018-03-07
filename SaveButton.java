import java.io.File;
import java.io.PrintWriter;
//import java.io.FileNotFoundException;

/**
 * (This class creates a button to make beds.)
 *
 * <p>Bugs: (no known bugs)
 *
 * @author (Bennett Majerowski and John Gibson)
 */
public class SaveButton implements DormGUI {
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	private PApplet processing;
	private float[] position;
	private String label;
	private File f;
//	private File myOutFile;
	
	public SaveButton(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = new String("Save Room");
	}
	
	/**
	 * (update prints the button on the java window that can be clicked
	 * to create new bed objects.)
	 */
	public void update() {
		if (isMouseOver() == true) {
			processing.fill(100);
		} else {
			processing.fill(200);
		}
		processing.rect(position[0] - WIDTH/2, position[1] + HEIGHT/2, position[0] + WIDTH/2, position[1] - HEIGHT/2);
		
		processing.fill(0);
		processing.text(label, position[0], position[1]);
	}
	
	/**
	 * (mouseDown identifies the type "bed" if the mouse is clicked while above the 
	 * Create sofa button.)
	 */
	public void mouseDown(Furniture furniture[]) /*throws FileNotFoundException*/ { 
		if (isMouseOver()) {
			try {
				f = new File("RoomData.ddd");
				PrintWriter pw = new PrintWriter(f);
				for (int i=0; i<furniture.length; i++) {
					if (furniture[i] != null) {
						pw.println(furniture[i].getType() + ":" + furniture[i].getXPosition()
								+ "," + furniture[i].getYPosition() + "," + furniture[i].getRotation());
					} else {
						pw.println();
					}
					
				}
				pw.close();	
			} catch (Exception e) {
				System.out.println("WARNING: Could not save room contents to file RoomData.ddd.");
			}
			
		}
	}
	
	/**
	 * (isMouseOver is a helper method that identifies when the mouse is 
	 * over the Create Bed button.)
	 */
	public boolean isMouseOver() {
		if (processing.mouseX > position[0] - WIDTH/2 && 
				processing.mouseX < position[0] + WIDTH/2 &&
				processing.mouseY > position[1] - HEIGHT/2 &&
				processing.mouseY < position[1] + HEIGHT/2) {
			return true;
		}
		return false;	
	}

	
	public void mouseUp() {
		// TODO Auto-generated method stub
		
	}

	
}