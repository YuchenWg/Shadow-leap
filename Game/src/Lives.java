/** Represents a life in the life number display.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Lives extends Sprite {

	//the source of the image representing a life in the life number display
	private static final String ASSET_PATH = "assets/lives.png";
	
	/**
	 * Instantiates a new life for life number display.
	 *
	 * @param x the x position
	 * @param y the y position
	 */
	public Lives(float x, float y) {
		super(ASSET_PATH, x, y);
	}


}
