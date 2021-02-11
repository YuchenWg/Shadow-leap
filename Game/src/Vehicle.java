
public abstract class Vehicle extends MovingObject {
		
	/**
	 * Instantiates a new vehicle.
	 *
	 * @param imageSrc the source file location of the image representing the sprite
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public Vehicle(String imageScr, float x, float y, boolean moveRight) {
		super(imageScr, x, y, moveRight, new String[] { Sprite.HAZARD });
	}
	
	/**
	 * Instantiates a new vehicle.
	 *
	 * @param imageSrc the source file location of the image representing the sprite
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 * @param tag the tags on the sprite
	 */
	public Vehicle(String imageScr, float x, float y, boolean moveRight, String tag) {
		super(imageScr, x, y, moveRight, new String[] {tag});
	}
	
}
