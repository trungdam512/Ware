package Ware;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Ellipse;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Line;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.Button;

/**
 * @Author Trung Dam, Sylvain Zong-naba, and Justin Potts
 * 
 *         The main class that runs the game
 * 
 *         Entire Project Acknolegements: Jermey the Preceptor Profe P We used Canvas(www.canva.com)
 *         to make the logo of our game
 */
public class Game {

    private Board board;
    private CanvasWindow canvas;
    private static final int CANVAS_WIDTH = 600;
    private static final int CANVAS_HEIGHT = 600;
    private int turnsCount;
    private Ellipse chosenMarker;
    private List<Point> availableMoves;
    private Color chosenColor;
    private List<Marker> allMarkers;
    private List<Marker> blueMarkers;
    private List<Marker> redMarkers;
    private GraphicsText redWins;
    private GraphicsText redSucks;
    private GraphicsText blueWins;
    private GraphicsText blueSucks;
    private Boolean gameStatus;
    private HomeScreen homeScreen;
    private Button resetGameButton;
    private Image trophy;
    private GraphicsText playerOneLabel;
    private GraphicsText playerTwoLabel;
    private final Color REDCOLOR = Color.red;
    private final Color BLUECOLOR = Color.blue;
    private List<Color> colorLst = List.of(REDCOLOR, BLUECOLOR);
    private Line linePlayerOne;
    private Line linePlayerTwo;
    private List<Marker> displayMarkersPlayerOne;
    private List<Marker> displayMarkersPlayerTwo;
    private Button backToInstructions;


    public Game() {
        canvas = new CanvasWindow("Waré", CANVAS_WIDTH, CANVAS_HEIGHT);

        gameStatus = false;

        turnsCount = 0;

        backToInstructions = new Button("Instructions/Change Names");
        backToInstructions.onClick(() -> {
            canvas.removeAll();
            blueMarkers.clear();
            redMarkers.clear();
            allMarkers.clear();
            homeScreen.instructions.makeInstructionScreen(canvas);
        });
        backToInstructions.setCenter(CANVAS_WIDTH / 6, CANVAS_HEIGHT * 14 / 15);

        displayMarkersPlayerOne = new ArrayList<>();
        displayMarkersPlayerTwo = new ArrayList<>();

        homeScreen = new HomeScreen(canvas);

        System.out.println(homeScreen.user1 + "doesn't work");
        System.out.println(homeScreen.user2 + "pleaseee");

        playerOneLabel = new GraphicsText("abc");
        playerOneLabel.setFontStyle(FontStyle.BOLD);
        playerOneLabel.setCenter(CANVAS_WIDTH / 8, CANVAS_HEIGHT / 5);
        playerOneLabel.setScale(1.75);
        playerOneLabel.setFillColor(REDCOLOR);

        playerTwoLabel = new GraphicsText("bcd");
        playerTwoLabel.setFontStyle(FontStyle.BOLD);
        playerTwoLabel.setCenter(CANVAS_WIDTH * 7 / 8, CANVAS_HEIGHT / 5);
        playerTwoLabel.setScale(1.75);
        playerTwoLabel.setFillColor(BLUECOLOR);

        allMarkers = new ArrayList<Marker>();
        blueMarkers = new ArrayList<Marker>();
        redMarkers = new ArrayList<Marker>();

        redWins = new GraphicsText("RED WINS");
        redWins.setCenter(CANVAS_WIDTH / 2 - 25, CANVAS_HEIGHT / 11);
        redWins.setScale(3);
        redWins.setFontStyle(FontStyle.BOLD);
        redWins.setFillColor(REDCOLOR);

        redSucks = new GraphicsText("RED SUCKS!!!");
        redSucks.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 6);
        redSucks.setScale(2);
        redSucks.setFontStyle(FontStyle.ITALIC);
        redSucks.setFillColor(REDCOLOR);

        blueWins = new GraphicsText("BLUE WINS ");
        blueWins.setCenter(CANVAS_WIDTH / 2 - 25, CANVAS_HEIGHT / 11);
        blueWins.setScale(3);
        blueWins.setFontStyle(FontStyle.BOLD);
        blueWins.setFillColor(BLUECOLOR);

        blueSucks = new GraphicsText("BLUE SUCKS!!!");
        blueSucks.setCenter(CANVAS_WIDTH / 2, CANVAS_HEIGHT / 6);
        blueSucks.setScale(2);
        blueSucks.setFontStyle(FontStyle.ITALIC);
        blueSucks.setFillColor(BLUECOLOR);


        homeScreen.letPlayButton.onClick(() -> {
            // Get the typed name and initialize two lines according to the width of the names.
            playerOneLabel.setText(homeScreen.user1.getText());
            playerTwoLabel.setText(homeScreen.user2.getText());
            linePlayerOne = new Line(0, 0, 1.75 * playerOneLabel.getWidth(), 0);
            linePlayerOne.setCenter(playerOneLabel.getCenter().getX() + 5,
                playerOneLabel.getCenter().getY() + playerOneLabel.getHeight() / 2 + 10);
            linePlayerOne.setStrokeWidth(3);

            linePlayerTwo = new Line(0, 0, 1.75 * playerTwoLabel.getWidth(), 0);
            linePlayerTwo.setCenter(playerTwoLabel.getCenter().getX() + 5,
                playerTwoLabel.getCenter().getY() + playerTwoLabel.getHeight() / 2 + 10);
            linePlayerTwo.setStrokeWidth(3);

            redWins.setText(homeScreen.user1.getText() + " WINS!!!");
            redSucks.setText(homeScreen.user1.getText() + " SUCKS!!!");
            blueWins.setText(homeScreen.user2.getText() + " WINS!!!");
            blueSucks.setText(homeScreen.user2.getText() + " SUCKS!!!");
            canvas.removeAll();

            playGame();
        });

        resetGameButton = new Button("Play Again?");
        resetGameButton.setScale(3);
        resetGameButton.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 6 / 7);
        resetGameButton.onClick(() -> {
            canvas.removeAll();
            blueMarkers.clear();
            redMarkers.clear();
            allMarkers.clear();
            playGame();
        });

        trophy = new Image("trophy-removebg-preview.png");
        trophy.setScale(0.45);
        trophy.setCenter(35, CANVAS_HEIGHT / 11);
    }

    /**
     * Runs the game
     */
    private void playGame() {
        makeBoard();
        turnsCount = 0;
        gameStatus = false;
        moveMarkers();
    }

    /**
     * Move marker to an allowed position after all marker have been placed to the board or place the
     * marker to a selected location if a player is still placing their markers
     */
    private void moveMarkers() {
        canvas.onMouseDown(event -> startingPositions(event.getPosition()));
        canvas.onMouseUp(event -> movementRestrictions(event.getPosition()));
    }

    /**
     * Check to see if a given point is at around 20 distance from a vertex
     * 
     * @param point the selected point
     * @return the point of the vertex that is closer
     */
    public List<Point> checkPoint(Point point) {
        double xOfPoint = point.getX();
        double yOfPoint = point.getY();
        List<Point> returnedPoint = new ArrayList<>();
        for (Point thePoint : board.pointList) {
            double listPointX = thePoint.getX();
            double listPointY = thePoint.getY();
            double leftXBound = listPointX - 20;
            double rightXBound = listPointX + 20;
            double topYBound = listPointY - 20;
            double bottomYBound = listPointY + 20;
            if ((xOfPoint > leftXBound && xOfPoint < rightXBound && yOfPoint > topYBound && yOfPoint < bottomYBound)) {
                returnedPoint.add(thePoint);
            }
        }
        return returnedPoint;
    }

    /**
     * Put a marker on the board
     * 
     * @param position the vertex at which the marker will be placed
     * @param color    the color of the marker
     */
    private void addMarkers(Point position, Color color) {
        Marker marker = new Marker(position, color);
        canvas.add(marker);
        canvas.draw();
        allMarkers.add(marker);
    }

    /**
     * Choose a marker at a given vertex
     * 
     * @param point the vertex of the selected marker
     * @return the marker
     */
    private Ellipse selectMarker(Point point) {
        GraphicsObject object = canvas.getElementAt(point);
        if (object instanceof Ellipse ellipse) {
            return ellipse;
        }
        return null;
    }

    /**
     * Places markers for both players to have 3 turns before being able to move their markers Also
     * highlights selected markers before finishing move.
     */
    private void startingPositions(Point point) {
        if ((turnsCount < 6)) {
            if(placeMarker(point, colorLst.get(turnsCount % 2))){
                turnsCount++;
                if (turnsCount % 2 != 0) {
                    canvas.remove(displayMarkersPlayerOne.get(0));
                    displayMarkersPlayerOne.remove(0);
                } else {
                    canvas.remove(displayMarkersPlayerTwo.get(0));
                    displayMarkersPlayerTwo.remove(0);
                }
            }

        } else {
            chosenMarker = selectMarker(point);
            chosenMarker.setStrokeColor(Color.WHITE);
            chosenMarker.setStrokeWidth(2);
            Point choesenMarkerCenter = chosenMarker.getCenter();
            availableMoves = board.dictionaryMaker().get(choesenMarkerCenter);
            chosenColor = (Color) chosenMarker.getFillColor();
            if (turnsCount % 2 == 0 && chosenColor.equals(BLUECOLOR)) {
                unHighlightMarker();
            } else if (turnsCount % 2 != 0 && chosenColor.equals(REDCOLOR)) {
                unHighlightMarker();
            }
        }
    }

    /**
     * Takes away highlights of the selected marker
     */
    private void unHighlightMarker() {
        chosenMarker.setStrokeColor(Color.BLACK);
        chosenMarker.setStrokeWidth(1);
        chosenMarker = null;
    }

    /**
     * Restricts the movement of the markers as well as places them in their allowed movement positions
     * Also checks after every move if one player has won or lost.
     */
    private void movementRestrictions(Point point) {
        chosenMarker.setStrokeColor(Color.BLACK);
        chosenMarker.setStrokeWidth(1);
        if (!checkPoint(point).isEmpty() && (canvas.getElementAt(point) == null)
            && (!gameStatus) && turnsCount>=6) {
            Point toMoveToPoint = checkPoint(point).get(0);
            for (Point endPoint : availableMoves) {
                if (endPoint.equals(toMoveToPoint) && (placeMarker(endPoint, chosenColor))) {
                    canvas.remove(chosenMarker);
                    allMarkers.remove(chosenMarker);
                    turnsCount++;
                    makeMarkerList();
                    Set<Point> redCenters = getMarkerCenters(redMarkers);
                    Set<Point> blueCenters = getMarkerCenters(blueMarkers);
                    if (winBoolean(redCenters)) {
                        redWinsGraphics();
                    }
                    if (winBoolean(blueCenters)) {
                        blueWinsGraphics();
                    }
                    ;
                }
            }
        }
    }


    /**
     * Add graphics for when player with red markers wins
     */

    private void redWinsGraphics() {
        canvas.add(redWins);
        trophy.setCenter(redWins.getPosition().getX() - 110, CANVAS_HEIGHT / 12);
        canvas.add(trophy);
        canvas.add(blueSucks);
        canvas.add(resetGameButton);
        gameStatus = true;
    }

    /**
     * Add graphics for when player with blue markers wins
     */
    private void blueWinsGraphics() {
        canvas.add(blueWins);
        trophy.setCenter(blueWins.getPosition().getX() - 110, CANVAS_HEIGHT / 12);
        canvas.add(trophy);
        canvas.add(redSucks);
        canvas.add(resetGameButton);
        gameStatus = true;

    }

    /**
     * Make a list of markers for each player by using the their markers color
     */
    private void makeMarkerList() {
        redMarkers = new ArrayList<>();
        blueMarkers = new ArrayList<>();
        for (Marker eachMarker : allMarkers) {
            if (eachMarker.getFillColor().equals(REDCOLOR)) {
                redMarkers.add(eachMarker);
            } else {
                blueMarkers.add(eachMarker);
            }
        }
    }

    /**
     * Make a set of the centers of a given marker list
     * 
     * @param markerList list of blue/red markers
     * @return a set of the centers of the markers in the list
     */
    private Set<Point> getMarkerCenters(List<Marker> markerList) {
        Set<Point> centerList = new HashSet<Point>();
        for (Marker eachMarker : markerList) {
            centerList.add(eachMarker.getCenter());
        }
        return centerList;
    }

    /**
     * check to see if any of set of centers is equal to one of the winning options
     * 
     * @return true if does and false if it is not the case
     */

    private boolean winBoolean(Set<Point> centerList) {
        for (Set<Point> eachSet : board.makeWinningOption()) {
            if (eachSet.equals(centerList)) {
                return true;
            }

        }
        return false;
    }

    /**
     * Makes the board in which the game is being played on
     */
    private void makeBoard() {
        board = new Board(canvas);
        makeDisplayMarkers();
        canvas.add(playerOneLabel);
        canvas.add(playerTwoLabel);
        canvas.add(linePlayerOne);
        canvas.add(linePlayerTwo);
        canvas.add(backToInstructions);
        canvas.draw();
    }

    /**
     * Place a marker if it is put in an empty spot
     * 
     * @param endPoint location where the marker will be placed
     * @param color    the color of the marker
     * @return true if the marker is placed and false otherwise
     */
    private boolean placeMarker(Point endPoint, Color color) {
        if (!checkPoint(endPoint).isEmpty()) {
            Point whereToPlace = checkPoint(endPoint).get(0);
            if (canvas.getElementAt(whereToPlace) == null) {
                addMarkers(whereToPlace, color);
                return true;
            }
        }
        return false;
    }

    /*
     * Display three markers for each of the opponents below their name
     */
    private void makeDisplayMarkers() {
        displayMarkersPlayerOne.clear();
        displayMarkersPlayerTwo.clear();
        Point displayMarkerOnePoint = new Point(playerOneLabel.getCenter().getX(),
            playerOneLabel.getCenter().getY() + 120);
        Point displayMarkerTwoPoint = new Point(playerTwoLabel.getCenter().getX(),
            playerTwoLabel.getCenter().getY() + 120);

        for (int i = 0; i < 3; i++) {
            Marker marker = new Marker(new Point(displayMarkerOnePoint.getX(), displayMarkerOnePoint.getY() - (i * 40)),
                REDCOLOR);
            canvas.add(marker);
            displayMarkersPlayerOne.add(marker);
        }

        for (int i = 0; i < 3; i++) {
            Marker marker = new Marker(new Point(displayMarkerTwoPoint.getX(), displayMarkerTwoPoint.getY() - (i * 40)),
                BLUECOLOR);
            canvas.add(marker);
            displayMarkersPlayerTwo.add(marker);
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
