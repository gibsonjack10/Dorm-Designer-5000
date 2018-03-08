/**
 * (This class creates a button to clear all furniture from the room.)
 *
 * <p>Bugs: (no known bugs)
 *
 * @author (Bennett Majerowski and John Gibson)
 */
public class ClearButton extends Button implements DormGUI {
	private float[] position;
	
	/**
	 * Constructor that creates the Clear Button
	 * @param x is the horizontal location of the button
	 * @param y is the vertical location of the button
	 * @param processing uses information from the jar file
	 */
	public ClearButton(float x, float y, PApplet processing) {
		super(x,y,processing);
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = setLabel("Clear Room");
	}
	
	/**
	 * (mouseDown deletes all references in the furniture array)
	 */
	@Override
	public void mouseDown(Furniture[] furniture) {
		for (int i=0; i<furniture.length; i++) {
			furniture[i] = null;
		}
	}
}
