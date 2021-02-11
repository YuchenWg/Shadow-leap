import java.util.ArrayList;
import org.newdawn.slick.Input;

/** Represents the player in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Player extends Sprite {
	
	//the source of the image representing a player 
	private static final String ASSET_PATH = "assets/frog.png";
	
	//properties of the player
	private int lifeNumber = 3;
	private boolean isProtected = false;
	private int dx = 0,dy = 0;
	
	//attributes associated with displaying number of lives of the player
	private static final int LIFE_DISPLAY_Y = 744;
	private static final int LIFE_DISPLAY_X_INIT = 24;
	private static final int LIFE_DISPLAY_X_STEP = 32;
	private ArrayList<Lives> livesDisplay = new ArrayList<>();

	
	/**
	 * Instantiates a new player.
	 *
	 * @param x the x position
	 * @param y the y position
	 */
	public Player(float x, float y) {
		super(ASSET_PATH, x, y);
		displayLives();
	}
	
	/**
	 * Gets the protect status.
	 *
	 * @return the protect status
	 */
	public boolean getProtectStatus() {return isProtected;}
	
	/**
	 * Sets the protect status.
	 *
	 * @param playerStatus the new protect status
	 */
	public void setProtectStatus(boolean playerStatus) {this.isProtected =  playerStatus;}

	/**
	 * Gets the life number.
	 *
	 * @return the life number
	 */
	public int getlifeNumber() {
		return lifeNumber;
	}
	
	/**
	 * Sets the life number.
	 *
	 * @param lifeNum the new life number
	 */
	public void setlifeNumber(int lifeNum) {
		lifeNumber += lifeNum;
	}
	

	/* (non-Javadoc)
	 * @see Sprite#update(org.newdawn.slick.Input, int)
	 */
	public void update(Input input, int delta) {
		movePlayer(input, delta);
		
		if (!this.onScreen() && !isProtected) {
			loseLife();
		}
			
		displayLives(); 
	}
	
	/* (non-Javadoc)
	 * @see Sprite#render()
	 */
	@Override
	public void render() {
		super.render();
		for (Lives life: livesDisplay) {
			life.render();
		}
	}
	
	
	/* (non-Javadoc)
	 * @see Sprite#onCollision(Sprite, int)
	 */
	@Override
	public void onCollision(Sprite other, int delta) {
		if (!this.isProtected && other.hasTag(Sprite.HAZARD)) {
			loseLife();
		}
		if (other.hasTag(Sprite.ELIXIR)) {
			this.lifeNumber++;
		}
		if(other.hasTag(Sprite.SOLID)) { 
			this.pushed(other);
		}
		if(other.hasTag(Sprite.PROTECT)) { 
			this.moveWith(other,delta);
		}
	}
	
	 /**
 	 * Reinitialise location of the player when a life is lost.
 	 */
 	public void reinitialiseLoc() {
			this.setX( App.SCREEN_WIDTH / 2);
		    this.setY(App.SCREEN_HEIGHT - World.TILE_SIZE);
		} 
	
	//moves the player according to keyboard input
	private void movePlayer(Input input, int delta) {
		    dx = 0;
		    dy = 0;
			if (input.isKeyPressed(Input.KEY_LEFT)) {
				dx -= World.TILE_SIZE;
			}
			if (input.isKeyPressed(Input.KEY_RIGHT)) {
				dx += World.TILE_SIZE;
			}
			if (input.isKeyPressed(Input.KEY_DOWN)) {
				dy += World.TILE_SIZE;
			}
			if (input.isKeyPressed(Input.KEY_UP)) {
				dy -= World.TILE_SIZE;
			}
			
			// make sure the frog stays on screen
			if (getX() + dx - World.TILE_SIZE / 2 < 0 || getX() + dx + World.TILE_SIZE / 2 	> App.SCREEN_WIDTH) {
				dx = 0;
			}
			if (getY() + dy - World.TILE_SIZE / 2 < 0 || getY() + dy + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT) {
				dy = 0;
			}
			
			move(dx, dy);
	}
	
    //moves the player when it's on top of a floating object
	private void moveWith(Sprite other, int delta) {
		FloatingObject ride = (FloatingObject) other;
		
		if (getX() - World.TILE_SIZE / 2 > 0 && getX() + World.TILE_SIZE / 2 < App.SCREEN_WIDTH) {
			move(ride.getSpeed() * delta * (ride.getDirection() ? 1 : -1), 0);
		}
	}
	
	//updates the player position when pushed by another sprite
	private void pushed(Sprite other) {
    move(-dx,-dy);
	if (other.getX() > this.getX() ) {
		this.setX(other.getX()-World.TILE_SIZE);
	}
	if (other.getX() < this.getX() ) {
		this.setX(other.getX()+World.TILE_SIZE);
	}
	if (other.getY() > this.getY() ) {
		this.setY(other.getY()-World.TILE_SIZE);
	}
	if (other.getY() > this.getY() ) {
		this.setY(other.getY()+World.TILE_SIZE);
	}
}
	//updates the player/game when a life is lost		
	private void loseLife() {
		//exits the game when all lives are lost
		if (lifeNumber == 0) {
			System.exit(0);
		}
		else {
			this.lifeNumber--;
			reinitialiseLoc();
		}
	}	

	//update lives display objects to be displayed
	private void displayLives() {
		livesDisplay.clear();
		for (int i = 0; i < lifeNumber; i++) {
			livesDisplay.add(new Lives(LIFE_DISPLAY_X_INIT + i*LIFE_DISPLAY_X_STEP,
					                                                      LIFE_DISPLAY_Y));
		}
	}
}
