import org.newdawn.slick.Input;

/** Represents a bus in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Bus extends Vehicle {
	
	//properties specific to buses
	private static final String ASSET_PATH = "assets/bus.png";
	private static final float SPEED = 0.15f;
	private boolean moveRight;

	/**
	 * Instantiates a new bus.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public Bus(float x, float y, boolean moveRight) {
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
