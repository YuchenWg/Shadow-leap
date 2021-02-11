import org.newdawn.slick.Input;

/** Represents a racecar in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Racecar extends Vehicle {
	
	//properties specific to racecars
	private static final String ASSET_PATH = "assets/racecar.png";
	private static final float SPEED = 0.50f;
	private boolean moveRight;

	/**
	 * Instantiates a new racecar.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public Racecar(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y, moveRight);
		this.moveRight = moveRight;
	}

	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		reinitiateX();
	}
	
}
