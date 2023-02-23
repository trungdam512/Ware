package Ware;

import java.awt.Color;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.ui.Button;
import edu.macalester.graphics.ui.TextField;


/**
 * @Author Trung Dam, Sylvain Zong-naba, and Justin Potts
 * 
 *         This class configures how the first screen looks like and give the option to play to the
 *         user
 */

public class HomeScreen {
    private GraphicsText title;
    private final Color MAROON = new Color(159, 11, 11);
    private final Color DUSTY_ROSE = new Color(237, 174, 192);
    public Instruction instructions;
    private UsernameScreen usernameScreen;
    public Button letPlayButton;
    public TextField user1;
    public TextField user2;
    private Button startButton;
    private Image logo;


    private Button playButton;

    /**
     * Sets the background of the first screen of the game and allow players to select the play option
     * 
     * @param canvas
     */
    public HomeScreen(CanvasWindow canvas) {
        canvas.setBackground(DUSTY_ROSE);

        title = new GraphicsText("WARÉ");
        title.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 5);
        title.setScale(4);
        title.setFontStyle(FontStyle.BOLD);
        title.setFillColor(MAROON);
        canvas.add(title);

        logo = new Image("logo.png"); // Making the logo
        logo.setCenter(canvas.getWidth() / 2, canvas.getHeight() / 2);
        canvas.add(logo);

        instructions = new Instruction(canvas);
        startButton = instructions.startButton;

        usernameScreen = new UsernameScreen(canvas);
        letPlayButton = usernameScreen.startButton;
        user1 = usernameScreen.userName1;
        user2 = usernameScreen.userName2;

        playButton = new Button("PLAY!");
        playButton.setCenter(canvas.getWidth() / 2, canvas.getHeight() * 5 / 6);
        playButton.setScale(5);
        canvas.add(playButton);

        playButton.onClick(() -> {
            canvas.removeAll();
            instructions.makeInstructionScreen(canvas);
        });

        startButton.onClick(() -> {
            canvas.removeAll();
            usernameScreen.makeUsernameScreen(canvas);
        });


    }

}
