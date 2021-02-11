import utilities.BoundingBox;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/** Represents an abstract Sprite object in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public abstract class Sprite {
	
	/** The Constant string Hazard defined to avoid typos. */
	public final static String HAZARD = "hazard";
	
	/** The Constant string SOLID defined to avoid typos. */
	public final static String SOLID = "solid";
	
	/** The Constant string PROTECT defined to avoid typos. */
	public final static String PROTECT = "protect";
	
	/** The Constant string ELIXIR defined to avoid typos. */
	public final static String ELIXIR = "elixir";
	
	//properties of the sprite
	private BoundingBox bounds;
	private Image image;
	private float x;
	private float y;
	private boolean active = true;
    private String[] tags;
	
	/**
	 * Instantiates a new sprite.
	 *
	 * @param imageSrc the source file location of the image representing the sprite
	 * @param x the x position
	 * @param y the y position
	 */
	public Sprite(String imageSrc, float x, float y) {
		setupSprite(imageSrc, x, y);
	}
	
	/**
	 * Instantiates a new sprite.
	 *
	 * @param imageSrc the source file location of the image representing the sprite
	 * @param x the x position
	 * @param y the y position
	 * @param tags the tags associated with the sprite
	 */
	public Sprite(String imageSrc, float x, float y, String[] tags) {
		setupSprite(imageSrc, x, y);
		this.tags = tags;
	}
	
	//setting up the sprite by associating an image, setting a position
	//and creating bounds and tags
	private void setupSprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;
		
		bounds = new BoundingBox(image, (int)x, (int)y);
		
		tags = new String[0];		
	}
	
	/**
	 * Update the sprite according to specified rules.
	 *
	 * @param input The current input (keyboard state)
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) { }	
	
	/**
	 * Render the sprite (draws it centered at the (x,y) position)
	 */
	public void render() {
		image.drawCentered(x, y);
	}	

	/**
	 * Gets the tags.
	 *
	 * @return the tags
	 */
	public String[] getTag() {
		return tags;
	}
	
	/**
	 * Gets the active status i.e: if the sprite is active or not.
	 *
	 * @return the active status
	 */
	public boolean getActive() {
		return active;
	}
	
	/**
	 * Gets the bounds of the sprite.
	 *
	 * @return the bounds
	 */
	public final BoundingBox getBounds() { return this.bounds; }
	
	/**
	 * Sets the x position of the sprite.
	 * @param x	 the target x position
	 */
	public final void setX(float x) { this.x = x; bounds.setX((int)x); }
	/**
	 * Sets the y position of the sprite.
	 * @param y	 the target y position
	 */
	public final void setY(float y) { this.y = y; bounds.setY((int)y); }
	/**
	 * Accesses the x position of the sprite.
	 * @return	the x position of the sprite
	 */
	public final float getX() { return x; }
	/**
	 * Accesses the y position of the sprite.
	 * @return	the y position of the sprite
	 */
	public final float getY() { return y; }
	
	/**
	 * Move the sprite according to the input.
	 *
	 * @param dx the change in x position
	 * @param dy the change in y position
	 */
	public final void move(float dx, float dy) {
		setX(x + dx);
		setY(y + dy);
	}
	
	/**
	 * Determines if given position is on screen.
	 *
	 * @param x the x position
	 * @param y the y position
	 * @return true, if given position is on screen
	 */
	public final boolean onScreen(float x, float y) {
		return !(x + World.TILE_SIZE / 2 > App.SCREEN_WIDTH || x - World.TILE_SIZE / 2 < 0
			 || y + World.TILE_SIZE / 2 > App.SCREEN_HEIGHT || y - World.TILE_SIZE / 2 < 0);
	}
	
	/**
	 * Determines if the sprite is on screen.
	 *
	 * @return true, if current position is on screen
	 */
	public final boolean onScreen() {
		return onScreen(getX(), getY());
	}
	
	/**
	 * Determines if the sprite has collided with another sprite.
	 *
	 * @param other the other sprite
	 * @return true, if the sprite collides with the other sprite
	 */
	public final boolean collides(Sprite other) {
		return bounds.intersects(other.bounds);
	}
		
	/**
	 * Updates the sprite according to rules defined it collides with 
	 * the other sprite 
	 *
	 * @param other the other sprite
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void onCollision(Sprite other, int delta) { }
	
	/**
	 * Pops the current sprite.
	 */
	public void pop() {};
	
	/**
	 * Checks for tag.
	 *
	 * @param tag the test tag
	 * @return true, if sprite has test tag
	 */
	public boolean hasTag(String tag) {
		for (String test : tags) {
			if (tag.equals(test)) {
				return true;
			}
		}
		return false;
	}
}
