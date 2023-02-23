package Ware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Point;

/**
 * @Author Trung Dam, Sylvain Zong-naba, and Justin Potts
 * 
 *         This class creates the board, checks for the location a marker is allow to move and
 *         contains the different winning ways of winning for a player
 */


public class Board {

    private double scaleWidth;
    private double scaleHeight;
    private Line line1;
    private Line line2;
    private Line line3;
    private Line line4;
    private Line line5;
    private Line line6;
    private Line line7;
    private Line line8;
    public Point TOPLEFT;
    public Point TOP;
    private Point TOPRIGHT;
    private Point LEFT;
    private Point MIDDLE;
    private Point RIGHT;
    private Point BOTTOMLEFT;
    private Point BOTTOM;
    private Point BOTTOMRIGHT;
    public List<Point> pointList;

    public HashMap<Point, List<Point>> pointCheckerDictionary;
    public List<Set<Point>> winningOptions;

    /**
     * Creates the board in which the game will be played on by using lines. The lines passes through
     * the different vertices of the board
     */
    public Board(CanvasWindow canvas) {
        this.pointList = new ArrayList<Point>();
        this.scaleWidth = canvas.getWidth();
        this.scaleHeight = canvas.getHeight();

        pointCheckerDictionary = new HashMap<Point, List<Point>>();
        winningOptions = new ArrayList<Set<Point>>();

        TOPLEFT = new Point(scaleWidth / 4, scaleHeight / 4);
        TOP = new Point(scaleWidth / 2, scaleHeight / 4);
        TOPRIGHT = new Point(3 * scaleWidth / 4, scaleHeight / 4);
        LEFT = new Point(scaleWidth / 4, scaleHeight / 2);
        MIDDLE = new Point(scaleWidth / 2, scaleHeight / 2);
        RIGHT = new Point(3 * scaleWidth / 4, scaleHeight / 2);
        BOTTOMLEFT = new Point(scaleWidth / 4, 3 * scaleHeight / 4);
        BOTTOM = new Point(scaleWidth / 2, 3 * scaleHeight / 4);
        BOTTOMRIGHT = new Point(3 * scaleWidth / 4, 3 * scaleHeight / 4);

        line1 = new Line(TOPLEFT, TOPRIGHT);
        line2 = new Line(LEFT, RIGHT);
        line3 = new Line(BOTTOMLEFT, BOTTOMRIGHT);
        line4 = new Line(TOPLEFT, BOTTOMLEFT);
        line5 = new Line(TOP, BOTTOM);
        line6 = new Line(TOPRIGHT, BOTTOMRIGHT);
        line7 = new Line(TOPLEFT, BOTTOMRIGHT);
        line8 = new Line(TOPRIGHT, BOTTOMLEFT);
        line1.setStrokeWidth(4);
        line2.setStrokeWidth(4);
        line3.setStrokeWidth(4);
        line4.setStrokeWidth(4);
        line5.setStrokeWidth(4);
        line6.setStrokeWidth(4);
        line7.setStrokeWidth(4);
        line8.setStrokeWidth(4);
        canvas.add(line1);
        canvas.add(line2);
        canvas.add(line3);
        canvas.add(line4);
        canvas.add(line5);
        canvas.add(line6);
        canvas.add(line7);
        canvas.add(line8);

        pointList.add(TOPLEFT);
        pointList.add(TOP);
        pointList.add(TOPRIGHT);
        pointList.add(LEFT);
        pointList.add(MIDDLE);
        pointList.add(RIGHT);
        pointList.add(BOTTOMLEFT);
        pointList.add(BOTTOM);
        pointList.add(BOTTOMRIGHT);

    }

    /**
     * Make a dictionary to check for the locations to which a marker is allowed to move to. It map a
     * vertex position(key) to all the different others vertices locations(values) that a marker is
     * allow to move to.
     * 
     * @return a dictionary mapping all vertices to the ones a marker is allowed to move into
     */

    public HashMap<Point, List<Point>> dictionaryMaker() {
        pointCheckerDictionary.put(TOPLEFT, List.of(TOP, LEFT, MIDDLE));
        pointCheckerDictionary.put(TOP, List.of(TOPLEFT, TOPRIGHT, MIDDLE));
        pointCheckerDictionary.put(TOPRIGHT, List.of(TOP, RIGHT, MIDDLE));
        pointCheckerDictionary.put(LEFT, List.of(TOPLEFT, BOTTOMLEFT, MIDDLE));
        pointCheckerDictionary.put(RIGHT, List.of(TOPRIGHT, BOTTOMRIGHT, MIDDLE));
        pointCheckerDictionary.put(BOTTOMLEFT, List.of(BOTTOM, LEFT, MIDDLE));
        pointCheckerDictionary.put(BOTTOM, List.of(BOTTOMLEFT, BOTTOMRIGHT, MIDDLE));
        pointCheckerDictionary.put(BOTTOMRIGHT, List.of(RIGHT, BOTTOM, MIDDLE));
        pointCheckerDictionary.put(MIDDLE,
            List.of(TOP, LEFT, RIGHT, TOPRIGHT, TOPLEFT, BOTTOMLEFT, BOTTOM, BOTTOMRIGHT));
        return pointCheckerDictionary;
    }

    /**
     * Make a list of sets of all the points leading to a winning condition in the game
     * 
     * @return the list of those winning sets
     */
    public List<Set<Point>> makeWinningOption() {
        winningOptions.add(Set.of(TOPLEFT, LEFT, BOTTOMLEFT));
        winningOptions.add(Set.of(TOP, MIDDLE, BOTTOM));
        winningOptions.add(Set.of(TOPRIGHT, RIGHT, BOTTOMRIGHT));
        winningOptions.add(Set.of(TOPLEFT, TOP, TOPRIGHT));
        winningOptions.add(Set.of(LEFT, MIDDLE, RIGHT));
        winningOptions.add(Set.of(BOTTOMLEFT, BOTTOM, BOTTOMRIGHT));
        winningOptions.add(Set.of(TOPLEFT, MIDDLE, BOTTOMRIGHT));
        winningOptions.add(Set.of(TOPRIGHT, MIDDLE, BOTTOMLEFT));
        return winningOptions;
    }

}
