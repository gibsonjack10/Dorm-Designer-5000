public class Furniture implements DormGUI {

	private PApplet processing;
	private PImage image;
	private float[] position;
	private boolean isDragging;
	private int rotations;
	private String type;
	
	// initializes the fields of a new bed object positioned in the center of the display
	public Furniture(String type, PApplet processing) {
		this.type = type;
		this.processing = processing;
		image = processing.loadImage("images/" + type + ".png");
		position = new float[2];
		position[0] = processing.width/2;
		position[1] = processing.height/2;
		isDragging = false;
		rotations = 0;
		
	}
	
	public Furniture(String type, float xPos, float yPos, int rotate, PApplet processing) {
		this.type = type;
		this.processing = processing;
		image = processing.loadImage("images/" + type + ".png");
		position = new float[2];
		position[0] = xPos;
		position[1] = yPos;
		rotations = rotate;
		isDragging = false;
	}
	
	
	// draws this bed at its current position
	public void update() { 
		if (isDragging == true) {
			position[0] = processing.mouseX;
			position[1] = processing.mouseY;
			processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
		} else {
			processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
		}
	}
	
	// used to start dragging the bed, when the mouse is over this bed when it is pressed
	public void mouseDown(Furniture[] furniture) {
		if (isMouseOver() == true);
			isDragging = true;
	}
	
	// used to indicate that the bed is no longer being dragged
	public void mouseUp() {
		isDragging = false;
	}
	
	// helper method to determine whether the mouse is currently over this bed
	public boolean isMouseOver() {
		if (rotations % 2 == 0) {
			if (processing.mouseX > position[0] - image.width/2 && 
					processing.mouseX < position[0] + image.width/2 &&
					processing.mouseY > position[1] - image.height/2 &&
					processing.mouseY < position[1] + image.height/2) {
				return true;
			}
		} else if (processing.mouseX > position[0] - image.height/2 && 
					processing.mouseX < position[0] + image.height/2 &&
					processing.mouseY > position[1] - image.width/2 &&
					processing.mouseY < position[1] + image.width/2) {
				return true;
		}
		
		return false;	
	}
	
	public void rotate() {
		rotations++;
	}
	
	public String getType() {
		return type;
	}
	
	public float getXPosition() {
		return position[0];
	}
	
	public float getYPosition() {
		return position[1];
	}
	
	public int getRotation() {
		return rotations;
	}

}