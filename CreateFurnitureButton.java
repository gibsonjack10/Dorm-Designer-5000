/**
 * (This class creates a button to make beds.)
 * <p>
 * Subclass of: (Button.java)
 * <p>
 * Bugs: (no known bugs)
 * @author (Bennett Majerowski and John Gibson)
 */
public class CreateFurnitureButton extends Button implements DormGUI {
	private PApplet processing;
	private float[] position;
	private String type;
	
	/**
	 * Constructor that creates the bed button
	 * @param x is the horizontal location of the button
	 * @param y is the vertical location of the button
	 * @param processing uses information from the jar file
	 */
	public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
		super(x,y,processing);
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		this.type = type;
		label = setLabel("Create " + type);
	}
	
	/**
	 * (mouseDown identifies the type "bed" if the mouse is clicked while above the 
	 * Create sofa button.)
	 */
	@Override
	public void mouseDown(Furniture[] furniture) {
		for (int i=0; i<furniture.length; i++) {
			if (furniture[i] == null) {
				Furniture furn = new Furniture(type, processing);
				furniture[i] = furn;
				break;
			}
		}
	}
}

