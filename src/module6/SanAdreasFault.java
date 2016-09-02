package module6;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.utils.MapUtils;
import parsing.ParseFeed;
import de.fhpotsdam.unfolding.providers.*;
import de.fhpotsdam.unfolding.providers.Google.*;

import java.util.List;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.GeoJSONReader;
import de.fhpotsdam.unfolding.geo.Location;

import java.util.HashMap;




import de.fhpotsdam.unfolding.marker.Marker;

/**
 * Visualizes life expectancy in different countries. 
 * 
 * It loads the country shapes from a GeoJSON file via a data reader, and loads the population density values from
 * another CSV file (provided by the World Bank). The data value is encoded to transparency via a simplistic linear
 * mapping.
 */
public class SanAdreasFault extends PApplet {

	UnfoldingMap map;

	public void setup() {
		size(1500, 780, OPENGL);
		map = new UnfoldingMap(this, 20, 20, 1480, 760, new Google.GoogleTerrainProvider());
		int zoomLevel = 5;
		map.zoomAndPanTo(zoomLevel, new Location(32.9f, -117.2f));
		map.setZoomRange(5, 8);
		map.setTweening(true);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		
	}

	public void draw() {
		// Draw map tiles and country markers
		map.draw();
	}



}
