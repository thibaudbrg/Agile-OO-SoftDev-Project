package battleship.gui;

import battleship.game.Game;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
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

    ComboBox<Integer> rowComboBox;
    ComboBox<Integer> colComboBox;




    public SceneManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainScene.getStylesheets().add("battleship/gui/Subscene.css");
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

        InfoLabel help = new InfoLabel("Rules");
        help.setFont(new Font("Verdana", 40));
        rulesSubScene.getPane().getChildren().add(help);

        GridPane rulesGrid = new GridPane();
        rulesSubScene.getPane().getChildren().add(rulesGrid);

        rulesGrid.setLayoutX(100);
        rulesGrid.setLayoutY(100);
        rulesGrid.setHgap(20);
        rulesGrid.setVgap(20);

        Label rulesLabel = new Label("You and your opponent sit facing each other and neighter can see the other's \n" +
                "ocean grid.\n" +
                "Place your fleet of 5 ships on the ocean grid.\n" +
                "Rules for placing ships:\n" +
                "Place each ship in any horizontal or vertical position but not diagonally.\n" +
                "Don't place a ship so that any part of it overlaps letters, numbers, the edge \n" +
                "of the grid or another ship.\n" +
                "Don't change the position of any ships once the game has begun.\n" +
                "You and your opponent will alternate turns, calling out one shot per turn to try \n" +
                "to hit each other's ships.\n" +
                "On your turn, pick a target hole and call out its location by letter and number.\n" +
                " ---> It's a hit!!\n" +
                "If you call out a shot location that is occupied by a ship on your opponent's ocean\n" +
                " grid, your shot is a hit!\n" +
                " ---> It's a miss...\n" +
                "If you call out a shot location not occupied by a ship on your opponent's ocean\n" +
                "grid, its a miss. Once all the holes in any one ship are hit, the ship will sink.\n" +
                " The owner of that ship must announce which ship was sunk.\n\n");
        rulesLabel.setFont(new Font("Verdana", 12));

        rulesGrid.add(rulesLabel, 0, 0);
    }

    private void createDifficultySubScene() {
        difficultySubScene = new BattleShipSubScene();
        mainPane.getChildren().add(difficultySubScene);

        InfoLabel chooseDifficultyLabel = new InfoLabel("CHOOSE YOUR DIFFICULTY");
        //chooseDifficultyLabel.setFont(new Font("Verdana", 20));
        chooseDifficultyLabel.getStyleClass().add("title-label");
        difficultySubScene.getPane().getChildren().add(chooseDifficultyLabel);
        chooseDifficultyLabel.setLayoutX(100);
        chooseDifficultyLabel.setLayoutY(45);
        chooseDifficultyLabel.setPrefSize(400,50);
        chooseDifficultyLabel.setCenterShape(true);

        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid-pane");
        grid.setLayoutX(100);
        grid.setLayoutY(150);
        grid.setHgap(20);
        grid.setVgap(20);

        Label rowLabel = new Label("Rows:");
        rowLabel.getStyleClass().add("grid-label");
        //rowLabel.setFont(new Font(18));
        grid.add(rowLabel, 0, 2);

        Label colLabel = new Label("Columns:");
        colLabel.getStyleClass().add("grid-label");
        //colLabel.setFont(new Font(18));
        grid.add(colLabel, 0, 3);

        rowComboBox = new ComboBox<>();
        rowComboBox.getItems().addAll(6, 7, 8, 9, 10);
        rowComboBox.setValue(8);
        rowComboBox.getStyleClass().add("grid-combo-box");
        grid.add(rowComboBox, 1, 2);

        colComboBox = new ComboBox<>();
        colComboBox.getItems().addAll(6, 7, 8, 9, 10);
        colComboBox.setValue(8);
        colComboBox.getStyleClass().add("grid-combo-box");
        grid.add(colComboBox, 1, 3);

        difficultySubScene.getPane().getChildren().add(grid);

        double widthOfPane = difficultySubScene.getPane().getWidth();
        double heightOfPane = difficultySubScene.getPane().getHeight();


        BattleShipButton multiplayer = createMultiButton(widthOfPane / 10 * 6, heightOfPane * 0.45);
        difficultySubScene.getPane().getChildren().add(multiplayer);

        BattleShipButton easyMode = createEasyModeButton(widthOfPane / 10 * 6, heightOfPane * 0.6);
        difficultySubScene.getPane().getChildren().add(easyMode);

        BattleShipButton hardMode = createHardModeButton(widthOfPane / 10 * 6, heightOfPane * 0.75);
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
                int numRows = rowComboBox.getValue();
                int numCols = colComboBox.getValue();
                mainStage.hide();
                Game.play(GameMode.MULTIPLAYER,numRows,numCols);
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
                //GraphicalGame graphicalGame = new GraphicalGame(GameMode.EASY);
                int numRows = rowComboBox.getValue();
                int numCols = colComboBox.getValue();
                mainStage.hide();
                Game.play(GameMode.EASY,numRows,numCols);
                // TODO
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
                //GraphicalGame graphicalGame = new GraphicalGame(GameMode.HARD);
                // TODO: Go to the Solo mode with easy AI
                //GameViewManager gameViewManagger = new GameViewManager();
                //gameViewManagger.createNewGame(mainStage, chosenShip);
                int numRows = rowComboBox.getValue();
                int numCols = colComboBox.getValue();
                mainStage.hide();
                Game.play(GameMode.HARD,numRows,numCols);
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
        Image bgImage = new Image("assets/background.png", 1920, 1080, false, true);
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


