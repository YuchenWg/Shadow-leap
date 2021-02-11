import org.newdawn.slick.Input;

/** Represents a bike in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Bike extends Vehicle {

	//bike reverses direction when these bounds are reached
	private static final float BIKE_BOUND_RIGHT = 1000f;
	private static final float BIKE_BOUND_LEFT = 24f;
		
	//properties specific to bikes
	private static final String ASSET_PATH = "assets/bike.png";
	private static final float SPEED = 0.20f;
	private boolean moveRight;

	/**
	 * Instantiates a new bike.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public Bike(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y, moveRight);
		this.moveRight = moveRight;
	}
   	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		move(SPEED * delta * (moveRight ? 1 : -1), 0);
		reverseX();
	}
	
	//reverses direction of the bike	
	private void reverseX() {
		if (getX() > BIKE_BOUND_RIGHT || getX() < BIKE_BOUND_LEFT) {
					moveRight = !moveRight;
				}
	}
	
}
