package battleship.gui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SceneManager {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private static final int MENU_BUTTON_START_X = 100;
    private static final int MENU_BUTTON_START_Y = 325;

    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;

    private BattleShipSubScene rulesSubScene;
    private BattleShipSubScene difficultySubScene;
    private BattleShipSubScene sceneToHide;

    List<BattleShipButton> menuButtons;

    public SceneManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtonsMainMenu();
        createBackground();
        createLogo();
    }

    private void createSubScenes() {
        createRulesSubScene();
        createDifficultySubScene();
    }

    private void createRulesSubScene() {
        rulesSubScene = new BattleShipSubScene();
        mainPane.getChildren().add(rulesSubScene);

        InfoLabel help = new InfoLabel("<<<< Rules >>>>");
        rulesSubScene.getPane().getChildren().add(help);

        GridPane rulesGrid = new GridPane();
        rulesSubScene.getPane().getChildren().add(rulesGrid);

        rulesGrid.setLayoutX(100);
        rulesGrid.setLayoutY(100);
        rulesGrid.setHgap(20);
        rulesGrid.setVgap(20);

        Label rulesLabel = new Label("You and your opponent sit facing each other and neighter can see the other's ocean grid.\n" +
                "Place your fleet of 5 ships on the ocean grid.\n\n" +
                "Rules for placing ships:\n" +
                "Place each ship in any horizontal or vertical position but not diagonally.\n" +
                "Don't place a ship so that any part of it overlaps letters, numbers, the edge of the grid or another ship.\n" +
                "Don't change the position of any ships once the game has begun.\n\n\n" +
                "You and your opponent will alternate turns, calling out one shot per turn to try to hit each other's ships.\n" +
                "On your turn, pick a target hole and call out its location by letter and number.\n\n" +
                " ---> It's a hit!!\n" +
                "           If you call out a shot location that is occupied by a ship on your opponent's ocean grid, your shot is a hit!\n" +
                " ---> It's a miss...\n" +
                "           If you call out a shot location not occupied by a ship on your opponent's ocean grid, its a miss.\n\n" +
                "Once all the holes in any one ship are hit, the ship will sink. The owner of that ship must announce which ship was sunk.\n\n");
        rulesGrid.add(rulesLabel, 0, 0);
    }

    private void createDifficultySubScene() {
        difficultySubScene = new BattleShipSubScene();
        mainPane.getChildren().add(difficultySubScene);

        InfoLabel chooseDifficultyLabel = new InfoLabel("CHOOSE YOUR DIFFICULTY"); // TODO: TRY TO SOLVE THE FONT
        difficultySubScene.getPane().getChildren().add(chooseDifficultyLabel);
        chooseDifficultyLabel.setLayoutX(110);
        chooseDifficultyLabel.setLayoutY(45);

        double widthOfPane = difficultySubScene.getPane().getWidth();
        double heightOfPane = difficultySubScene.getPane().getHeight();
        // TODO: SOLVE THE DISPLAY OF BUTTONS AND TRY TO IMPLEMENTS ATTRIBUTES FOR 192 AND 49 (H and W of image button)


        BattleShipButton multiplayer = createMultiButton(widthOfPane / 10, heightOfPane * 2 / 5);
        difficultySubScene.getPane().getChildren().add(multiplayer);

        BattleShipButton easyMode = createEasyModeButton(widthOfPane / 10 * 3.33, heightOfPane * 2 / 5);
        difficultySubScene.getPane().getChildren().add(easyMode);

        BattleShipButton hardMode = createHardModeButton(widthOfPane / 10 * 6.66, heightOfPane * 2 / 5);
        difficultySubScene.getPane().getChildren().add(hardMode);
    }


    private void showSubScene(BattleShipSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();
        sceneToHide = subScene;
    }

    private void createButtonsMainMenu() {
        BattleShipButton startMenu = createStartButton(MENU_BUTTON_START_X, MENU_BUTTON_START_Y + menuButtons.size() * 100);
        menuButtons.add(startMenu);
        BattleShipButton rulesMenu = createRulesButton(MENU_BUTTON_START_X, MENU_BUTTON_START_Y + menuButtons.size() * 100);
        menuButtons.add(rulesMenu);
        BattleShipButton exitMenu = createExitButton(MENU_BUTTON_START_X, MENU_BUTTON_START_Y + menuButtons.size() * 100);
        menuButtons.add(exitMenu);


        mainPane.getChildren().addAll(menuButtons);
    }

    private BattleShipButton createRulesButton(double x, double y) {
        BattleShipButton rulesButton = new BattleShipButton("Rules");
        rulesButton.setLayoutX(x);
        rulesButton.setLayoutY(y);
        //addMenuButtons(rulesButton);
        rulesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                /*try {
                    SoundEffects.playSound(new URI(BUTTON_SFX));
                } catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
                showSubScene(rulesSubScene);
            }
        });
        return rulesButton;
    }

    private BattleShipButton createStartButton(double x, double y) {
        BattleShipButton startButton = new BattleShipButton("Start");
        startButton.setLayoutX(x);
        startButton.setLayoutY(y);
        //addMenuButtons(startButton, );
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                /*try {
                    SoundEffects.playSound(new URI(BUTTON_SFX));
                } catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }*/
                showSubScene(difficultySubScene);
            }
        });
        return startButton;
    }

    /*
    private BattleShipButton createPlayButton(double x, double y) {
            BattleShipButton playButton = new BattleShipButton("Play");
        playButton.setLayoutX(x);
        playButton.setLayoutY(y);
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.hide();
                //GameViewManager gameViewManager = new GameViewManager();
                //gameViewManagger.createNewGame(mainStage, chosenShip);
            }
        });
        return playButton;
    }

     */
    private BattleShipButton createMultiButton(double x, double y) {
        BattleShipButton multiButton = new BattleShipButton("Multiplayer");
        multiButton.setLayoutX(x);
        multiButton.setLayoutY(y);
        multiButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.hide();
                GraphicalGame graphicalGame = new GraphicalGame(GameMode.MULTIPLAYER);
                //GameViewManager gameViewManagger = new GameViewManager();
                //gameViewManagger.createNewGame(mainStage, chosenShip);
            }
        });
        return multiButton;
    }

    private BattleShipButton createEasyModeButton(double x, double y) {
        BattleShipButton easyModeButton = new BattleShipButton("Easy Bot");
        easyModeButton.setLayoutX(x);
        easyModeButton.setLayoutY(y);
        easyModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.hide();
                GraphicalGame graphicalGame = new GraphicalGame(GameMode.EASY);
                // TODO: Go to the Solo mode with easy AI
                //GameViewManager gameViewManagger = new GameViewManager();
                //gameViewManagger.createNewGame(mainStage, chosenShip);
            }
        });
        return easyModeButton;
    }

    private BattleShipButton createHardModeButton(double x, double y) {
        BattleShipButton HardModeButton = new BattleShipButton("Hard Bot");
        HardModeButton.setLayoutX(x);
        HardModeButton.setLayoutY(y);
        HardModeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.hide();
                GraphicalGame graphicalGame = new GraphicalGame(GameMode.HARD);
                // TODO: Go to the Solo mode with easy AI
                //GameViewManager gameViewManagger = new GameViewManager();
                //gameViewManagger.createNewGame(mainStage, chosenShip);
            }
        });
        return HardModeButton;
    }

    private BattleShipButton createExitButton(double x, double y) {
        BattleShipButton exitButton = new BattleShipButton("Exit");
        exitButton.setLayoutX(x);
        exitButton.setLayoutY(y);
        //addMenuButtons(exitButton, MENU_BUTTON_START_X, MENU_BUTTON_START_Y + menuButtons.size() * 100);
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Platform.exit();
                // mainStage.close();
            }
        });

        return exitButton;
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createBackground() {
        Image bgImage = new Image("background.jpeg", 1920, 1080, false, true);
        BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(1920, 1080, false, false, false, false));
        mainPane.setBackground(new Background(bg));
    }

    private void createLogo() {
        Image logoImage = new Image("logo_white-removebg.png", 500, 185, false, false);
        ImageView logo = new ImageView(logoImage);
        mainPane.getChildren().add(logo);
        logo.setLayoutX(400);
        logo.setLayoutY(50);
        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                logo.setEffect(new DropShadow(100, Color.YELLOW));
            }
        });
        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                logo.setEffect(null);
            }
        });
    }
}

