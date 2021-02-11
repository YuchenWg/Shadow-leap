/** Represents an abstract object that moves independently in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public abstract class MovingObject extends Sprite {

	//properties specific to moving objects
	private boolean moveRight;
	private static float SPEED;
	
	/**
	 * Instantiates a new moving object.
	 *
	 * @param imageSrc the source file location of the image representing the sprite
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public MovingObject(String imageSrc, float x, float y, boolean moveRight) {
		super(imageSrc, x, y);
		this.moveRight = moveRight;
	}

	/**
	 * Instantiates a new moving object.
	 *
	 * @param imageSrc the source file location of the image representing the sprite
	  *@param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 * @param tags the tags on the moving object
	 */
	public MovingObject(String imageSrc, float x, float y, boolean moveRight, String[] tags) {
		super(imageSrc, x, y, tags);
		this.moveRight = moveRight;
	}
		
	/**
	 * Reinitiate the x position of the moving object when it moves off the screen.
	 */
	public void reinitiateX() {
			
			// check if the floatingObject has moved off the screen
			if (getX() > App.SCREEN_WIDTH + World.TILE_SIZE / 2 || getX() < -World.TILE_SIZE / 2
			 || getY() > App.SCREEN_HEIGHT + World.TILE_SIZE / 2 || getY() < -World.TILE_SIZE / 2) {
				setX(getInitialX());
			}
		}
	    
	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public float getSpeed() {return SPEED;}
	
	/**
	 * Gets the direction.
	 *
	 * @return the direction
	 */
	public boolean getDirection() {return moveRight;}
	
	//gets the position to reinitialise the sprite to
	//when the sprite moves off the screen on one side.
	private final float getInitialX() {
		return moveRight ? -World.TILE_SIZE / 2
						 : App.SCREEN_WIDTH + World.TILE_SIZE / 2;
	}
	
}
