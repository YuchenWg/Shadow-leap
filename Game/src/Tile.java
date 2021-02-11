/** Represents a Tile in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class Tile extends Sprite {
	
	//file locations of images representing the tiles
	private static final String GRASS_PATH = "assets/grass.png";
	private static final String WATER_PATH = "assets/water.png";
	private static final String TREE_PATH = "assets/tree.png";
	
	/**
	 * Creates the grass tile.
	 *
	 * @param x the x position of the grass tile created
	 * @param y the y position of the grass tile created
	 * @return grass tile object created at the specified location
	 */
	public static Tile createGrassTile(float x, float y) {
		return new Tile(GRASS_PATH, x, y);
	}
	
	/**
	 * Creates the water tile.
	 *
	 * @param x the x position of the water tile created
	 * @param y the y position of the water tile created
	 * @return water tile object created at the specified location
	 */
	public static Tile createWaterTile(float x, float y) {
		return new Tile(WATER_PATH, x, y, new String[] { Sprite.HAZARD });
	}
	
	/**
	 * Creates the tree tile.
	 *
	 * @param x the x position of the tree tile created
	 * @param y the y position of the tree tile created
	 * @return tree tile object created at the specified location
	 */
	public static Tile createTreeTile(float x, float y) {
		return new Tile(TREE_PATH, x, y, new String[] { Sprite.SOLID });
	}	
	
	//creates the tile objects
	private Tile(String imageSrc, float x, float y) {		
		super(imageSrc, x, y);
	}
	
	//creates the tile objects with tags
	private Tile(String imageSrc, float x, float y, String[] tags) {		
		super(imageSrc, x, y, tags);
	}
}