
/** Represents a filled hole object in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class FilledHole extends Sprite {

	//properties of the filled hole class
	private static final String ASSET_PATH = "assets/frog.png";
    private static final float FILLEDHOLE_LOCATION_Y = 48f;
	
	/**
	 * Instantiates a new filled hole.
	 *
	 * @param xLocation the x location of the filled hole
	 */
	public FilledHole(Float xLocation) {
		super(ASSET_PATH, xLocation, FILLEDHOLE_LOCATION_Y, new String[] { Sprite.HAZARD });
	}

}
