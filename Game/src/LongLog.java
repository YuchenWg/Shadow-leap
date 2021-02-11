import org.newdawn.slick.Input;

/** Represents a long log in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class LongLog extends Log {

		//properties specific to long logs
		private static final String ASSET_PATH = "assets/longlog.png";
		private static final float SPEED = 0.07f;
		private boolean moveRight;
		
		/**
		 * Instantiates a new long log.
		 *
		 * @param x the x position
	     * @param y the y position
	     * @param moveRight the direction
		 */
		public LongLog(float x, float y, boolean moveRight) {
			super(ASSET_PATH, x, y, moveRight);
			this.moveRight = moveRight;
		}
        
		/* (non-Javadoc)
		 * @see Log#update(org.newdawn.slick.Input, int)
		 */
		@Override
		public void update(Input input, int delta) {
			move(SPEED * delta * (moveRight ? 1 : -1), 0);
			reinitiateX();
			
			super.update(input, delta);

		}
		
		/* (non-Javadoc)
		 * @see MovingObject#getSpeed()
		 */
		@Override
		public float getSpeed() {return SPEED;}
		
		/* (non-Javadoc)
		 * @see MovingObject#getDirection()
		 */
		@Override
		public boolean getDirection() {return moveRight;}
}
