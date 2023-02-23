package Ware;
import edu.macalester.graphics.Ellipse;
import java.awt.*;
import edu.macalester.graphics.Point;

/**
 * This class creates a marker with a given center and color
 */

public class Marker extends Ellipse{

    private static final double MARKER_RADIUS = 15;

    public Marker(Point center, Color color){
        super(0, 0, MARKER_RADIUS*2, MARKER_RADIUS*2);
        setFillColor(color);
        setCenter(center);

    }   
}
