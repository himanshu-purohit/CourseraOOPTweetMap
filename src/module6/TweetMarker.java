package module6;

import java.util.HashMap;

import de.fhpotsdam.unfolding.geo.Location;
import processing.core.PConstants;
import processing.core.PGraphics;
import processing.core.PImage;

public class TweetMarker extends CommonMarker
{
	protected PImage img;
	protected String tweetText;

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

	public TweetMarker(Location location,PImage img) {
		super(location);
		this.img = img;
		// TODO Auto-generated constructor stub
	}
	

	public TweetMarker(Location location, HashMap<String, Object> properties) {
		super(location, properties);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void drawMarker(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub
pg.pushStyle();
		
pg.imageMode(PConstants.CORNER);
pg.image(img, x , y , 40 ,40);
		
		pg.fill(150, 30, 30);
		//pg.triangle(x, y-3, x-3, y+3, x+3, y+3);
		
		// Restore previous drawing style
		pg.popStyle();
	}

	@Override
	public void showTitle(PGraphics pg, float x, float y) {
		// TODO Auto-generated method stub
        pg.pushStyle();
		
		pg.rectMode(PConstants.CORNER);
		
		pg.stroke(110);
		pg.fill(229,242,247);
		pg.rect(x+40, y, pg.textWidth(tweetText)/2 +6, 40, 7);
		
		pg.textAlign(PConstants.CENTER, PConstants.CENTER);
		pg.fill(0);
		pg.text(tweetText,x+43,y+3,pg.textWidth(tweetText)/2 +6,40);
		
		
		pg.popStyle();
	}

}
