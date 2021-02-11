import org.newdawn.slick.Input;

/** Represents an abstract log object in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public abstract class Log extends FloatingObject {

	//properties of log objects
	private boolean hasExtraLife = false;
    private ExtraLife extralifeObject;
	
    //keeps track of time
	private int timeCounter = 0;
	
	/**
	 * Instantiates a new log.
	 *
	 * @param imageScr the source file location of the image representing the sprite
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public Log(String imageScr, float x, float y, boolean moveRight) {
		super(imageScr, x, y, moveRight);
	}
 	
 	/* (non-Javadoc)
	  * @see Sprite#update(org.newdawn.slick.Input, int)
	  */
	 @Override
	public void update(Input input, int delta) {
		
		//creates the extralife object (when selected) and destroys
		//it after the specified time
		if(this.getHasExtraLife()) {
			if(timeCounter == 0) {
				this.createExtraLifeObject();
			}
			if (timeCounter >= 14*World.ONE_SECOND) {
				this.hasExtraLife = false;
			}
			this.extralifeObject.update(input, delta);
			timeCounter+=delta;
		}
		else if (extralifeObject != null) {
			this.removeExtraLifeObject();
			timeCounter = 0;
		}
		
	}
 	 	
	/* (non-Javadoc)
	 * @see Sprite#render()
	 */
	@Override
	public void render() {
		super.render();
		if (extralifeObject != null) {
		extralifeObject.render();
		}
	}

	/**
	 * checks if log has an extra life object.
	 *
	 * @return true, if log has an extralife object
	 */
	public boolean getHasExtraLife() {return hasExtraLife;}
	
 	/**
	  * Sets the property of possessing an extra life or not.
	  */
	 public void setHasExtraLife(boolean value) {hasExtraLife = value;}
 	
	/**
	 * Creates an extra life object.
	 */
	public void createExtraLifeObject() {
		extralifeObject = new ExtraLife(this);
	}
	
	/**
	 * Removes the extra life object.
	 */
	public void removeExtraLifeObject() {
		extralifeObject = null;
}
	
	/**
	 * Gets the extra life object.
	 *
	 * @return the extra life object
	 */
	public ExtraLife getExtraLifeObject() {
		return extralifeObject;
	}
	
}
