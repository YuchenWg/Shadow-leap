import org.newdawn.slick.Input;

/** Represents a chain of three turtles in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Turtles extends FloatingObject {
		
	    //properties specific to floating objects
		private static final String ASSET_PATH = "assets/turtles.png";
		private static final float SPEED = 0.085f;
		private boolean moveRight;
		private boolean active = true;

		//keeps track of time
		private int timeCount = 0;
		
		/**
		 * Instantiates a new turtles.
		 *
		 * @param x the x position
	     * @param y the y position
	     * @param moveRight the direction
		 */
		public Turtles(float x, float y, boolean moveRight) {
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
			timeCounter(delta);	
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
		
		/* (non-Javadoc)
		 * @see Sprite#getActive()
		 */
		@Override
		public boolean getActive() {
			return this.active;
		}
		
		/* (non-Javadoc)
		 * @see Sprite#hasTag(java.lang.String)
		 */
		@Override
		public boolean hasTag(String tag) {
				if (this.active) {
	                return super.hasTag(tag);
		}
				return false;
				
		}
		
		//keeps track of time passed and update turtles accordingly
		private void timeCounter(int delta) {
			if ((active == true && timeCount <= 7*World.ONE_SECOND) ||
					          (active == false && timeCount <= 2*World.ONE_SECOND)) {
				timeCount += delta;
			}
			else {
				active = !active;
				timeCount = 0;
			}
		}
		
}