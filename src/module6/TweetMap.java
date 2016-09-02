package module6;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import processing.core.PApplet;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;

public class TweetMap extends PApplet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Marker> tweetMarkers = new ArrayList<Marker>();
	UnfoldingMap map;
	private CommonMarker lastSelected;

	@Override
	public void setup() {
		size(1500, 780, OPENGL);
		map = new UnfoldingMap(this, 20, 20, 1480, 760,
				new Google.GoogleMapProvider());
		int zoomLevel = 10;
		map.zoomAndPanTo(zoomLevel, new Location(37.7749295, -122.4194155));
		map.setZoomRange(5, 15);
		map.setTweening(true);
		MapUtils.createDefaultEventDispatcher(this, map);
		searchTweets();
	    map.addMarkers(tweetMarkers);
	}
	
	@Override
	public void mouseMoved()
	{
		// clear the last selection
		if (lastSelected != null) {
			lastSelected.setSelected(false);
			lastSelected = null;
		
		}
		selectMarkerIfHover(tweetMarkers);
		//loop();
	}
	
	// If there is a marker selected 
	private void selectMarkerIfHover(List<Marker> markers)
	{
		// Abort if there's already a marker selected
		if (lastSelected != null) {
			return;
		}
		
		for (Marker m : markers) 
		{
			CommonMarker marker = (CommonMarker)m;
			if (marker.isInside(map,  mouseX, mouseY)) {
				lastSelected = marker;
				marker.setSelected(true);
				return;
			}
		}
	}
	

	@Override
	public void draw() {
		// Draw map tiles and country markers
		map.draw();
	}

	public void searchTweets() {

		try {
			Twitter twitter = new TwitterFactory().getInstance();

			AccessToken accessToken = new AccessToken(
					"2765106018-SRtlLfM8jxxPUshHcoSjZBv3r3IF5MqUqEBJuUX",
					"wDFVKpvBYDFfzkJzOIYaPldVYqBEoMHlZ3EDVZWLbdmCk");

			twitter.setOAuthConsumer("p2Tj6zJ3wDiO1oq6HJEr6G585",
					"XRN3skuNLmfRfHFOYV7fopx6FnF1FCLulfaNjIXRzbbcP7hAVC");
			twitter.setOAuthAccessToken(accessToken);
			Query query = new Query("earthquakesSF");
			QueryResult result;
			result = twitter.search(query);
			List<Status> tweets = result.getTweets();
			for (Status tweet : tweets) {
				if (tweet.getGeoLocation() != null)
				{
					System.out.println("@" + tweet.getUser().getScreenName()
							+ " - " + tweet.getText() + tweet.getGeoLocation());
				GeoLocation tweetLoc = tweet.getGeoLocation();
				TweetMarker twm = new TweetMarker(new Location(tweetLoc.getLatitude(),tweetLoc.getLongitude()),loadImage("http://d13yacurqjgara.cloudfront.net/users/31260/screenshots/765813/attachments/75674/twitter-icon.png","png"));
				twm.setTweetText("@" + tweet.getUser().getScreenName()
							+ " - " + tweet.getText());
				tweetMarkers.add(twm);
				

				}
			 query = new Query("#earthquake");
			 result = twitter.search(query);
				List<Status> tweets_2 = result.getTweets();
				for (Status tweet_2 : tweets_2) {
					if (tweet_2.getGeoLocation() != null)
					{
						System.out.println("@" + tweet_2.getUser().getScreenName()
								+ " - " + tweet_2.getText() + tweet_2.getGeoLocation());
					GeoLocation tweetLoc = tweet_2.getGeoLocation();
					TweetMarker twm = new TweetMarker(new Location(tweetLoc.getLatitude(),tweetLoc.getLongitude()),loadImage("http://d13yacurqjgara.cloudfront.net/users/31260/screenshots/765813/attachments/75674/twitter-icon.png","png"));
					twm.setTweetText("@" + tweet_2.getUser().getScreenName()
								+ " - " + tweet_2.getText());
					tweetMarkers.add(twm);
					

					}}}}		
		catch (TwitterException te) {
			te.printStackTrace();
			System.out.println("Failed to search tweets: " + te.getMessage());
			System.exit(-1);
		}
	}

}
