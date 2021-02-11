import org.newdawn.slick.Input;

/** Represents an extralife object in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class ExtraLife extends Sprite {
	
	//extralife object properties
	private static final String ASSET_PATH = "assets/extralife.png";
	private Log associatedLog;
	private boolean moveRight = true;
	private final int dy = 0;
	private int dx = 0;
		
	//keeps track of time
	private int timeCounter = 0;
	
	/**
	 * Instantiates a new extra life.
	 *
	 * @param log the log that owns the extralife object
	 */
	public ExtraLife(Log log) {
		super(ASSET_PATH, log.getX(), log.getY(), new String[] { Sprite.ELIXIR});
		this.associatedLog = log;
	}
	
	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	@Override
	public void update(Input input, int delta) {
		timeCounter += delta;
		moveAlong(associatedLog);
		extraLifeMove();
        stayOnLog();

	}
	
	//pops the current extralife object
	public void pop() {
		associatedLog.setHasExtraLife(false);		
	}	

	//moves the extralife object on the log
	private void extraLifeMove() {
		if (this.timeCounter >= 2*World.ONE_SECOND) {
			dx += (moveRight ? 1 : -1)*World.TILE_SIZE;
			timeCounter = 0;
		}
		
	}
	
	//makes sure the extralife object stay on the owning log
	private void stayOnLog() {
		if(!this.collides(associatedLog)) {
			dx -= (moveRight ? 1 : -1)*World.TILE_SIZE;
			moveRight = !moveRight;
		}
	}
	
	//moves the extralife object along the owning log
	private void moveAlong(Log log) {
		this.setX(log.getX()+dx);
		this.setY(log.getY()+dy);
	}
	
}
