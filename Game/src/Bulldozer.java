import org.newdawn.slick.Input;

/** Represents a bulldozer in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Bulldozer extends Vehicle {
	
	//properties specific to bulldozers
	private static final String ASSET_PATH = "assets/bulldozer.png";
	private static final float SPEED = 0.05f;
	private boolean moveRight;

	/**
	 * Instantiates a new bulldozer.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @param moveRight the direction
	 */
	public Bulldozer(float x, float y, boolean moveRight) {
		super(ASSET_PATH, x, y, moveRight, Sprite.SOLID);
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