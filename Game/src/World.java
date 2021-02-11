import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.lang.Math;
import java.util.Random;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/** Represents a World in the game.
 * @author Wang Yuchen (Adapted from Sample project 1 by Eleanor McMurtry)
*/
public class World {
		
	/** Size of one Tile, in pixels. */
	public static final int TILE_SIZE = 48;
	
	/**  Number of milliseconds in one second. */
	public static final int ONE_SECOND = 1000;
	
	/** the relative file path of the .lvl files */
	private static final String LEVEL_FILE_PATH = "assets/levels/";
	
	//defines the random time interval
	private static final int RANDOM_TIME_BEGIN = 25;
	private static final int RANDOM_TIME_INTERVAL = 10;

	//attributes associated with randomly generating an extralife object
	private int randomTime;
    private Random randomTimeGenerator = new Random();
	private Random randomLogGenerator = new Random();
	
	//keep track of time
	private int timeCounter = 0;
    
	//Hard coding the location of the holes in as it is not specified in the .lvl files 
	private static final float HOLE_LOCATION_Y = 48f;
	private static final Map<Integer, Float> HOLE_LOCATION_X = new HashMap<Integer, Float>();
	static {
		HOLE_LOCATION_X.put(1, 120f);
		HOLE_LOCATION_X.put(2, 312f);
		HOLE_LOCATION_X.put(3, 504f);
		HOLE_LOCATION_X.put(4, 696f);
		HOLE_LOCATION_X.put(5, 888f);
		
	}
	
	//map to label and store filled holes
	private Map<Integer, Sprite> filledHoles = new HashMap<Integer, Sprite>();
	
	//intermediate variable when reading .lvl files 
	private ArrayList<List<String>> spriteLocations = new ArrayList<>();
	
	//store data about the sprites and create separate copy for logs only
	private ArrayList<Sprite> sprites = new ArrayList<>();
	private ArrayList<Log> logs = new ArrayList<>();
	
	/**
	 * Instantiates a new world.
	 *
	 * @param level the current level
	 */
	public World(int level) {
		
		//picking an initial random time (in seconds) for first extralife object 
		randomTime = RANDOM_TIME_BEGIN+randomTimeGenerator.nextInt(
				                                     RANDOM_TIME_INTERVAL);
		
		// read, parse and store sprite data from .lvl files
		String levelFile = LEVEL_FILE_PATH + level + ".lvl";
	    try (BufferedReader br = 
				 new BufferedReader(new FileReader(levelFile))){
		   
		    String line;
		    while ((line = br.readLine()) != null) {

               List<String> spriteInfo = Arrays.asList(line.split(","));
                                               
                spriteLocations.add(spriteInfo);
               }
		    		    }
		    
		    catch (Exception e) {
		    	e.printStackTrace();
		    	}

        //create and store sprites from stored sprite data
		for (List<String> sprite : spriteLocations) {
		    switch (sprite.get(0)) {
		    	case "water":
		    		sprites.add(Tile.createWaterTile(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2))));
		    		break;
		    	case "tree":
		    		sprites.add(Tile.createTreeTile(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2))));
		    		break;
		    	case "grass":
		    		sprites.add(Tile.createGrassTile(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2))));
		    		break;
		    	case "turtle":
		    	    sprites.add(new Turtles(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2)),Boolean.parseBoolean(sprite.get(3))));
		    		break;
		    	case "log":
		    	    sprites.add(new ShortLog(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2)),Boolean.parseBoolean(sprite.get(3))));
		    		break;
		    	case "longLog":
		    	    sprites.add(new LongLog(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2)),Boolean.parseBoolean(sprite.get(3))));
		    		break;
		    	case "bus":
		    	    sprites.add(new Bus(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2)),Boolean.parseBoolean(sprite.get(3))));
		    		break;
		    	case "racecar":
		    	    sprites.add(new Racecar(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2)),Boolean.parseBoolean(sprite.get(3))));
		    		break;
		    	case "bulldozer":
		    	    sprites.add(new Bulldozer(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2)),Boolean.parseBoolean(sprite.get(3))));
		    		break;
		    	case "bike":
		    	    sprites.add(new Bike(Integer.parseInt(sprite.get(1)),Integer.parseInt(sprite.get(2)),Boolean.parseBoolean(sprite.get(3))));
		    		break;
		
		
		}
		}
		
		//create new references for logs (facilitates random selection of log)
		for (Sprite sprite: sprites) {
			if (sprite instanceof Log) {
				Log log = (Log) sprite;
			    logs.add(log);
			}
		}
		
		// create and store player
		sprites.add(new Player(App.SCREEN_WIDTH / 2, App.SCREEN_HEIGHT - TILE_SIZE));
	}
	
	/**
	 * Update all the sprites in world according to specified rules.
	 *
	 * @param input The current input (keyboard state)
	 * @param delta Time passed since last frame (milliseconds).
	 */
	public void update(Input input, int delta) {
		
		//update all sprites according to default movement
		for(Sprite sprite: sprites) {
			sprite.update(input, delta);
		}
		
		//counting up to random time place an extralife object on a random log
		timeCounter += delta;
		if(timeCounter >= randomTime*ONE_SECOND) {
			getRandomLog().setHasExtraLife(true);
			randomTime = RANDOM_TIME_BEGIN+randomTimeGenerator.nextInt(RANDOM_TIME_INTERVAL);
	        timeCounter = 0;	
		}
		
		//check and update current player status
		for (Sprite sprite1: sprites) {
			 if(sprite1 instanceof Player) {
				Player player = (Player) sprite1;
				player.setProtectStatus(false);
				
				//check and update if player is on a floating object
				for (Sprite sprite2: sprites) {
					 if (sprite2.hasTag(Sprite.PROTECT) && player.collides(sprite2)) {
					    	player.setProtectStatus(true);
					    }
				 }
				
				//check and update if player has collided with another object
			    for (Sprite sprite3: sprites) {
				    if (player.collides(sprite3)) {
				    	player.onCollision(sprite3,delta);
				    	}
				    
				}
			    
			    //check if log collides with player, if it has an extralife object
			    // and if player collides with the extralife object
			    for (Log log: logs) {
				    if (player.collides(log)) {
				    	if(log.getExtraLifeObject() != null) {
				    		if(player.collides(log.getExtraLifeObject())) {
				    	        player.onCollision(log.getExtraLifeObject(),delta);
				    	        log.getExtraLifeObject().pop();
				    		}
				    	}
				    }
				}
			    
			    //kill player if collides with a filled hole
			    for (Integer key: filledHoles.keySet()) {
				    Sprite filledHole = filledHoles.get(key);
			    	if (player.collides(filledHole)) {
				    	player.onCollision(filledHole,delta);
				    }
				    
				}
			    
			    //fill hole and reset player location if the player enters the hole
			    if (Math.abs(player.getY()-HOLE_LOCATION_Y) < TILE_SIZE) {
			    	for (Integer key: HOLE_LOCATION_X.keySet()) {
			    			if (Math.abs(player.getX()-HOLE_LOCATION_X.get(key)) < TILE_SIZE) {
			    				filledHoles.put(key, new FilledHole(HOLE_LOCATION_X.get(key)));
			    				player.reinitialiseLoc();
			    				break;
			    			}
			    		}
			    	}
			    }
			}	
		}

    
	/**
	 * Render all the sprites and filled holes.
	 * 
	 * @param g The Slick graphics object, used for drawing.
	 */
	public void render(Graphics g) {
		//render all active sprites
		for (Sprite sprite : sprites) {
			if(sprite.getActive()) {
			sprite.render();
			}
		}
		
		//render filled holes with player image
		for (Integer key: filledHoles.keySet()) {
			filledHoles.get(key).render();
		}
	}
	
	/**
	 * Gets the level progress.
	 *
	 * @return the number of filled holes for the current level 
	 */
	public int getLevelProgress() {
		return filledHoles.size();
	}
	
	//randomly selects a log object and returns it
    private Log getRandomLog() {
		int logIndex = randomLogGenerator.nextInt(logs.size());
		return logs.get(logIndex);
   }
}
