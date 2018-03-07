import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Main {

	private PApplet processing;
	private PImage backgroundImage;
	private Furniture[] furniture;
	private CreateBedButton bedButton;
	private CreateSofaButton sofaButton;
	private SaveButton saveButton;
	private LoadButton loadButton;
	private ArrayList<DormGUI> guiObjects;

	public Main(PApplet processing) {
		this.processing = processing;
		backgroundImage = processing.loadImage("images/background.png");
		bedButton = new CreateBedButton(50, 24, processing);
		sofaButton = new CreateSofaButton(150, 24, processing);
		saveButton = new SaveButton(650, 24, processing);
		loadButton = new LoadButton(750, 24, processing);
		guiObjects = new ArrayList<DormGUI>();
		guiObjects.add(bedButton);
		guiObjects.add(sofaButton);
		guiObjects.add(saveButton);
		guiObjects.add(loadButton);
		furniture = new Furniture[100];
		for (int i = 0; i < furniture.length; i++) {
			furniture[i] = null;
		}


	}

	public static void main(String[] args) {
		Utility.startApplication();

	}


	public void update() {
		processing.background(100,150,250);
		processing.image(backgroundImage, processing.width/2, processing.height/2);
		//		bedButton.update();
		//		sofaButton.update();
		//		saveButton.update();
		//		loadButton.update();

		for (int i=0; i<guiObjects.size(); i++) {
			guiObjects.get(i).update();
		}

	}		

	public void mouseDown() throws FileNotFoundException {

		for (int i=0; i<guiObjects.size(); i++) {
			if (guiObjects.get(i).isMouseOver()) {
				extractFurnitureFromGUIObjects();
				guiObjects.get(i).mouseDown(furniture);
				if (!guiObjects.get(i).equals(bedButton) && !guiObjects.get(i).equals(sofaButton) 
						&& !guiObjects.get(i).equals(saveButton) 
						&& !guiObjects.get(i).equals(loadButton)) {
					break;
				}
				if (guiObjects.get(i).equals(bedButton) || guiObjects.get(i).equals(sofaButton)) {
					//System.out.println("HELLO");
					for (int j=0; j < furniture.length; j++) {
						if (furniture[j] == null) {
							guiObjects.add(furniture[i]);
							break;
						}
					}
				}
				if (!guiObjects.get(i).equals(saveButton)) {
					System.out.println("BBBBBBB");
					replaceFurnitureInGUIObjects(furniture);
				}
				

			}

			//			if (furniture[i] == null) {
			//				break;
			//			}
			//			else if(furniture[i].isMouseOver()) {
			//				furniture[i].mouseDown(furniture);
			//				break;
			//			}

		}
		//		for (int i=0; i<6; i++) {
		//			if (furniture[i] == null && bedButton.isMouseOver()) {
		//				bedButton.mouseDown(furniture);
		//				break;
		//			}
		//		}
		//		for (int i=0; i<6; i++) {
		//			if (furniture[i] == null && sofaButton.isMouseOver()) {
		//				sofaButton.mouseDown(furniture);
		//				break;
		//			}
		//		}
		//		if (saveButton.isMouseOver()) {
		//			saveButton.mouseDown(this.furniture);
		//		}
		//		if (loadButton.isMouseOver()) {
		//			loadButton.mouseDown(this.furniture);
		//		}

	}
	public void mouseUp() {
		for (int i=0; i<guiObjects.size(); i++) {
			if (guiObjects.get(i).isMouseOver()) {
				guiObjects.get(i).mouseUp();
			}
		}
		//			if (furniture[i] == null) {
		//				break;
		//			}
		//			else {
		//				furniture[i].mouseUp();
		//			}
		//		}

	}

	public void keyPressed() {

		if(processing.key == 'D' || processing.key == 'd') {
			extractFurnitureFromGUIObjects();
			for (int i=0; i < guiObjects.size(); i++) {
				if (guiObjects.get(i).isMouseOver()) {
					guiObjects.remove(i);
				}
//			for (int j = 0; j < extractFurnitureFromGUIObjects().length; j++) {
//				if (extractFurnitureFromGUIObjects()[i].isMouseOver()) {
//					extractFurnitureFromGUIObjects()[i] = null;
//				}
//			}
			}
			//			for (int i=0; i<6; i++) {
			//				if(furniture[i] != null) {
			//					if(furniture[i].isMouseOver()) {
			//						furniture[i] = null;
			//						break;
			//					}
			//				}
			//
			//			}
		}
		else if(processing.key == 'R' || processing.key == 'r') {
			//extractFurnitureFromGUIObjects();
			for (int i=0; i < guiObjects.size(); i++) {
				//System.out.println("HELLO2");
				if (guiObjects.get(i).isMouseOver() && !guiObjects.get(i).equals(bedButton)
					&& !guiObjects.get(i).equals(sofaButton) && !guiObjects.get(i).equals(saveButton)
					&& !guiObjects.get(i).equals(loadButton)) {
					Furniture rotateThisFuckingThing = (Furniture)guiObjects.get(i);
					rotateThisFuckingThing.rotate();
					//replaceFurnitureInGUIObjects(furniture);
				}
			}
			//			for (int i=0; i<6; i++) {
			//				if (furniture[i] != null) {
			//					if (furniture[i].isMouseOver()) {
			//						furniture[i].rotate();
			//						break;
			//					}
			//				}
			//			}

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