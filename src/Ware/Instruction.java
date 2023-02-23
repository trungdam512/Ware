package Ware;

import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.ui.Button;

/**
 * @Author Trung Dam, Sylvain Zong-naba, and Justin Potts
 * 
 *         This class shows instuctions on how to play the game to the users
 */
public class Instruction {

    private GraphicsText instructionsTitle;
    private GraphicsText actualIntructions1;
    private GraphicsText actualIntructions2;
    private GraphicsText actualIntructions3;
    private GraphicsText actualIntructions4;
    private GraphicsText actualIntructions5;
    private GraphicsText actualIntructions6;
    private GraphicsText actualIntructions7;

    public Button startButton;

    /**
     * Put some texts that explains how to play the game center those texts in the canvas and make the
     * name selector
     * 
     */
    public Instruction(CanvasWindow canvas) {

        instructionsTitle = new GraphicsText("HOW TO PLAY:");
        instructionsTitle.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 6);
        instructionsTitle.setScale(5);
        instructionsTitle.setFontStyle(FontStyle.BOLD);

        actualIntructions1 = new GraphicsText("The game is like Tic Tac Toe with a twist.");

        actualIntructions2 = new GraphicsText(
            "Each game starts with a blank board and each player starts with 3 markers.");
        actualIntructions3 = new GraphicsText("Players take turns placing their markers at vertices of the board.");
        actualIntructions4 = new GraphicsText(
            "You're only allowed to move along lines and you're not allowed to move to an occupied vertex.");
        actualIntructions5 = new GraphicsText(
            "To move, click down on the piece you want to move and drag to the place you want go.");
        actualIntructions6 = new GraphicsText(
            "The game is over after a player puts 3 markers in a row after the original placements.");
        actualIntructions7 = new GraphicsText("Have Fun!!");
        actualIntructions1.setCenter(canvas.getWidth() / 2, (canvas.getHeight() / 2) - 90);
        actualIntructions2.setCenter(canvas.getWidth() / 2, (canvas.getHeight() / 2 - 60));
        actualIntructions3.setCenter(canvas.getWidth() / 2, (canvas.getHeight() / 2 - 30));
        actualIntructions4.setCenter(canvas.getWidth() / 2, (canvas.getHeight() / 2));
        actualIntructions5.setCenter(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 30);
        actualIntructions6.setCenter(canvas.getWidth() / 2, (canvas.getHeight() / 2) + 60);
        actualIntructions7.setCenter(canvas.getWidth() / 2, (canvas.getHeight() / 2 + 90));


        startButton = new Button("Now Choose Your Name!");
        startButton.setScale(3);
        startButton.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 5 / 6);

    }

    /**
     * add the instructionTitle and instructions to the canvas
     * 
     * @param canvas the canvasWindow
     */
    public void makeInstructionScreen(CanvasWindow canvas) {
        canvas.add(instructionsTitle);
        canvas.add(actualIntructions1);
        canvas.add(actualIntructions2);
        canvas.add(actualIntructions3);
        canvas.add(actualIntructions4);
        canvas.add(actualIntructions5);
        canvas.add(actualIntructions6);
        canvas.add(actualIntructions7);

        canvas.add(startButton);
    }

}
