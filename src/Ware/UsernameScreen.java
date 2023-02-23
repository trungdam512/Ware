package Ware;

import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;

/**
 * @Author Trung Dam, Sylvain Zong-naba, and Justin Potts
 * 
 *         This class gets and puts the user names on the screen
 */
public class UsernameScreen {
    public TextField userName1;
    public TextField userName2;
    private GraphicsText title;
    public Button startButton;
    private GraphicsText player1;
    private GraphicsText player2;

    /**
     * Asks the users to input their names
     * 
     * @param canvas the canvasWindow
     */
    public UsernameScreen(CanvasWindow canvas) {

        player1 = new GraphicsText("Player 1, GIVE ME YOUR NAME");
        player1.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 5 / 16);
        player1.setScale(2);
        player2 = new GraphicsText("Player 2, GIVE ME YOURS TOO");
        player2.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 9 / 16);
        player2.setScale(2);

        userName1 = new TextField();
        userName1.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 3 / 8);
        userName1.setScale(3);
        userName2 = new TextField();
        userName2.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 5 / 8);
        userName2.setScale(3);

        title = new GraphicsText("ADD YOUR NAMES!!!");
        title.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 7);
        title.setScale(4);

        startButton = new Button("Let's Play!");
        startButton.setScale(3);
        startButton.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 5 / 6);
    }

    /**
     * add the usernames to the canvas
     * 
     * @param canvas the canvasWindows
     */
    public void makeUsernameScreen(CanvasWindow canvas) {
        canvas.add(title);
        canvas.add(userName2);
        canvas.add(userName1);
        canvas.add(startButton);
        canvas.add(player1);
        canvas.add(player2);
    }


}
