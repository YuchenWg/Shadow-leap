/** Represents an abstract floating object in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public abstract class FloatingObject extends MovingObject {

	/**
	 * Instantiates a new floating object.
	 *
	 * @param imageScr the source file location of the image representing the sprite
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public FloatingObject(String imageScr, float x, float y, boolean moveRight) {
		super(imageScr, x, y, moveRight, new String[] { Sprite.PROTECT});
	}
    
}


