/**
 * (This class creates a superclass to define a generic button.)
 * <p>
 * Classes inheriting: (LoadButton.java, ClearButton.java, CreateFurnitureButton.java
 * SaveButton.java)
 * <p>
 * Bugs: (no known bugs)
 * @author (Bennett Majerowski and John Gibson)
 */
public class Button implements DormGUI{

	/**
	 * (Fields of Button)
	 */
	private static final int WIDTH = 96;
	private static final int HEIGHT = 32;
	protected PApplet processing;
	private float[] position;
	protected String label;
	 
	/**
	 * Constructor that creates the Load Button
	 * @param x is the horizontal location of the button
	 * @param y is the vertical location of the button
	 * @param processing uses information from the jar file
	 */
	public Button(float x, float y, PApplet processing) {
		this.processing = processing;
		position = new float[2];
		position[0] = x;
		position[1] = y;
		label = new String("Button");
	}
	 
	/**
	 * Mutator method to change the label on new Button Objects.
	 * @param newLabel contains a string to print on the new buttons
	 */
	public String setLabel(String newLabel) {
		label = newLabel;
		return label;
	}
	
	/**
	 * (update prints the button on the java window that can be clicked
	 * to create new furniture objects. When the mouse if over a button,
	 * the button will change shades)
	 */
	public void update() {
		if (isMouseOver() == true) {
			processing.fill(100);
		} else {
			processing.fill(200);
		}
		processing.rect(position[0] - WIDTH/2, position[1] + HEIGHT/2, position[0] + 
				WIDTH/2, position[1] - HEIGHT/2);
		
		processing.fill(0);
		processing.text(label, position[0], position[1]);
	}
	
	/**
	 * (mouseDown prints a generic message when called. This method is overridden in 
	 * all of the subclasses of Button.)
	 */
	public void mouseDown(Furniture[] furniture) {
		System.out.println("A button was pressed.");
	}
	
	/**
	 * (mouseUp does nothing. It exists solely to be consistent with DormGUI interface)
	 */
	public void mouseUp() {
	}
	
	/**
	 * (isMouseOver is a helper method that identifies when the mouse is 
	 * over a button.)
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

}
