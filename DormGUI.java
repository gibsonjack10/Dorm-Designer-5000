/**
 * (This Interface defines an implementation where an object will have the ability to
 * have it's location and appearance updated, do something when it is clicked, revert to 
 * doing nothing when the mouse is released, and tells when the mouse is over it.)
 * <p>
 * Classes implementing: (LoadButton.java, ClearButton.java, CreateFurnitureButton.java
 * SaveButton.java, Button.java, Furniture.java)
 * <p>
 * Bugs: (no known bugs)
 * @author (Bennett Majerowski and John Gibson)
 */
public interface DormGUI {
	public void update();
	public void mouseDown(Furniture[] furniture);
	public void mouseUp();
	public boolean isMouseOver();
}
