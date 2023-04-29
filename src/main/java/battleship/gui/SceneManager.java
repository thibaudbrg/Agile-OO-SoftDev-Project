package battleship.gui;

import battleship.controller.GameCreationListener;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class SceneManager {
    private static final int HEIGHT = 768;
    private static final int WIDTH = 1024;
    private static final int MENU_BUTTON_START_X = 100;
    private static final int MENU_BUTTON_START_Y = 325;
    private final GameCreationListener gameCreationListener;
    private final AnchorPane mainPane;
    private final Scene mainScene;
    private final Stage mainStage;
    private final List<BattleShipButton> menuButtons;

    private BattleShipSubScene rulesSubScene;
    private BattleShipSubScene difficultySubScene;
    private BattleShipSubScene sceneToHide;
    private ComboBox<Integer> rowComboBox;
    private ComboBox<Integer> colComboBox;




    public SceneManager(GameCreationListener listener) {
        this.gameCreationListener = listener;
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainScene.getStylesheets().add("Subscene.css");
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubScenes();
        createButtonsMainMenu();
        createBackground();
        createLogo();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void createSubScenes() {
        createRulesSubScene();
        createDifficultySubScene();
    }

    private void createRulesSubScene() {
        rulesSubScene = new BattleShipSubScene();
        mainPane.getChildren().add(rulesSubScene);

        InfoLabel help = new InfoLabel(StringsEn.RULES_BUTTON);
        help.setFont(new Font("Verdana", 40));
        help.setTextFill(Color.WHITE);
        help.setLayoutX(60);
        help.setLayoutY(10);
        help.setPrefSize(200, 15);
        rulesSubScene.getPane().getChildren().add(help);

        GridPane rulesGrid = new GridPane();
        rulesSubScene.getPane().getChildren().add(rulesGrid);

        rulesGrid.setLayoutX(100);
        rulesGrid.setLayoutY(100);
        rulesGrid.setHgap(20);
        rulesGrid.setVgap(20);

        Label rulesLabel = new Label(StringsEn.RULES);
        rulesLabel.setFont(new Font("Verdana", 11));
        rulesLabel.setTextFill(Color.WHITE);
        rulesGrid.add(rulesLabel, 0, 0);

        // Fade in animation
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), rulesSubScene);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(10);
        fadeTransition.play();
    }

    private void createDifficultySubScene() {
        difficultySubScene = new BattleShipSubScene();
        mainPane.getChildren().add(difficultySubScene);
        InfoLabel chooseDifficultyLabel = new InfoLabel(StringsEn.DIFFICULTY_LABEL);
        chooseDifficultyLabel.setTextFill(Color.WHITE);
        chooseDifficultyLabel.getStyleClass().add("title-label");
        difficultySubScene.getPane().getChildren().add(chooseDifficultyLabel);
        chooseDifficultyLabel.setFont(new Font("Verdana", 40));
        chooseDifficultyLabel.setLayoutX(100);
        chooseDifficultyLabel.setLayoutY(45);
        chooseDifficultyLabel.setPrefSize(400, 50);
        chooseDifficultyLabel.setCenterShape(true);

        GridPane grid = new GridPane();
        grid.getStyleClass().add("grid-pane");
        grid.setLayoutX(100);
        grid.setLayoutY(150);
        grid.setHgap(20);
        grid.setVgap(20);

        Label rowLabel = new Label("Rows:");
        rowLabel.setTextFill(Color.WHITE);
        rowLabel.getStyleClass().add("grid-label");
        grid.add(rowLabel, 0, 2);

        Label colLabel = new Label("Columns:");
        colLabel.setTextFill(Color.WHITE);
        colLabel.getStyleClass().add("grid-label");
        grid.add(colLabel, 0, 3);

        rowComboBox = new ComboBox<>();
        rowComboBox.getItems().addAll(6, 7, 8, 9, 10, 11, 12);
        rowComboBox.setValue(10);
        rowComboBox.getStyleClass().add("grid-combo-box");
        grid.add(rowComboBox, 1, 2);

        colComboBox = new ComboBox<>();
        colComboBox.getItems().addAll(6, 7, 8, 9, 10, 11, 12);
        colComboBox.setValue(10);
        colComboBox.getStyleClass().add("grid-combo-box");
        grid.add(colComboBox, 1, 3);

        difficultySubScene.getPane().getChildren().add(grid);


        double widthOfPane = difficultySubScene.getPane().getWidth();
        double heightOfPane = difficultySubScene.getPane().getHeight();

        BattleShipButton multiplayer1 = createMultiplayerButton(widthOfPane / 10 * 6, heightOfPane * 0.35, false);
        multiplayer1.getStyleClass().add("three-buttons");
        difficultySubScene.getPane().getChildren().add(multiplayer1);

        BattleShipButton multiplayer2 = createMultiplayerButton(widthOfPane / 10 * 6, heightOfPane * 0.50, true);
        multiplayer2.getStyleClass().add("three-buttons");
        difficultySubScene.getPane().getChildren().add(multiplayer2);

        BattleShipButton easyMode = createEasyModeButton(widthOfPane / 10 * 6, heightOfPane * 0.65);
        easyMode.getStyleClass().add("three-buttons");
        difficultySubScene.getPane().getChildren().add(easyMode);

        BattleShipButton hardMode = createHardModeButton(widthOfPane / 10 * 6, heightOfPane * 0.80);
        hardMode.getStyleClass().add("three-buttons");
        difficultySubScene.getPane().getChildren().add(hardMode);

        // Fade in animation
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), difficultySubScene);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(10);
        fadeTransition.play();

    }


    private void showSubScene(BattleShipSubScene subScene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }
        subScene.moveSubScene();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), subScene);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(10);
        fadeTransition.play();
        sceneToHide = subScene;
    }

    private void createButtonsMainMenu() {
        BattleShipButton startMenu = createStartButton(MENU_BUTTON_START_X, MENU_BUTTON_START_Y + menuButtons.size() * 100);
        startMenu.getStyleClass().add("three-buttons");
        menuButtons.add(startMenu);
        BattleShipButton rulesMenu = createRulesButton(MENU_BUTTON_START_X, MENU_BUTTON_START_Y + menuButtons.size() * 100);
        rulesMenu.getStyleClass().add("three-buttons");
        menuButtons.add(rulesMenu);
        BattleShipButton exitMenu = createExitButton(MENU_BUTTON_START_X, MENU_BUTTON_START_Y + menuButtons.size() * 100);
        exitMenu.getStyleClass().add("three-buttons");
        menuButtons.add(exitMenu);
        mainPane.getChildren().addAll(menuButtons);
    }


    private BattleShipButton createRulesButton(double x, double y) {
        BattleShipButton rulesButton = new BattleShipButton(StringsEn.RULES_BUTTON);
        rulesButton.setLayoutX(x);
        rulesButton.setLayoutY(y);
        rulesButton.setOnAction(arg0 ->
                showSubScene(rulesSubScene));
        return rulesButton;
    }

    private BattleShipButton createStartButton(double x, double y) {
        BattleShipButton startButton = new BattleShipButton(StringsEn.START_BUTTON);
        startButton.setLayoutX(x);
        startButton.setLayoutY(y);
        startButton.setOnAction(arg0 -> showSubScene(difficultySubScene));
        return startButton;
    }

    private BattleShipButton createMultiplayerButton(double x, double y, boolean isTimed) {
        BattleShipButton multiButton = isTimed ?
                new BattleShipButton(StringsEn.MULTIPLAYER_BUTTON_TIMER) : new BattleShipButton(StringsEn.MULTIPLAYER_BUTTON_NO_TIMER);
        multiButton.setLayoutX(x);
        multiButton.setLayoutY(y);
        multiButton.setOnAction(event -> {
            int numRows = rowComboBox.getValue();
            int numCols = colComboBox.getValue();
            gameCreationListener.onGameCreate(GameMode.MULTIPLAYER, numCols, numRows, isTimed);

        });
        return multiButton;
    }


    private BattleShipButton createEasyModeButton(double x, double y) {
        BattleShipButton easyModeButton = new BattleShipButton(StringsEn.EASY_BUTTON);
        easyModeButton.setLayoutX(x);
        easyModeButton.setLayoutY(y);
        easyModeButton.setOnAction(event -> {
            int numRows = rowComboBox.getValue();
            int numCols = colComboBox.getValue();
            gameCreationListener.onGameCreate(GameMode.EASY, numCols, numRows, false);
        });
        return easyModeButton;
    }

    private BattleShipButton createHardModeButton(double x, double y) {
        BattleShipButton HardModeButton = new BattleShipButton(StringsEn.HARD_BUTTON);
        HardModeButton.setLayoutX(x);
        HardModeButton.setLayoutY(y);
        HardModeButton.setOnAction(event -> {
            int numRows = rowComboBox.getValue();
            int numCols = colComboBox.getValue();
            System.out.println("to remove");
            gameCreationListener.onGameCreate(GameMode.HARD, numCols, numRows, false);
        });
        return HardModeButton;
    }

    private BattleShipButton createExitButton(double x, double y) {
        BattleShipButton exitButton = new BattleShipButton(StringsEn.EXIT_BUTTON);
        exitButton.setLayoutX(x);
        exitButton.setLayoutY(y);
        exitButton.setOnAction(arg0 -> {
            Platform.exit();
        });
        return exitButton;
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
        logo.setOnMouseEntered(e -> {
            logo.setEffect(new DropShadow(100, Color.YELLOW));
        });
        logo.setOnMouseExited(e -> {
            logo.setEffect(null);
        });
    }

}
