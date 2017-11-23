package ffhs.pa5.controller;

import ffhs.pa5.Constants;
import ffhs.pa5.model.*;
import ffhs.pa5.model.highscore.Highscore;
import ffhs.pa5.model.highscore.HighscoreGame;
import ffhs.pa5.model.highscore.HighscorePlayer;
import ffhs.pa5.model.highscore.HighscoreXML;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * This is the main controller class.
 *
 * @author Tamara Burri
 * @author Yves Riedener
 * @version 1.0
 */
public class Controller implements Observer, Initializable {

    // view stuff
    private Label[] fields;
    private Stage stage;
    private FlowPane[] seeds;
    private ImageView[] image;
    private Image img;

    // animation stuff
    private ArrayList<ViewState> viewStatesToUpdate = new ArrayList<>();
    private Timeline timeline;

    // view-model stuff
    private ArrayList<Player> playersComboA;
    private ArrayList<Player> playersComboB;

    // model stuff
    private Game game;
    private HighscoreXML highscore;


    // ********************************************************************************
    // fxml components
    // ********************************************************************************

    @FXML
    @SuppressWarnings("unused")
    private BorderPane background, menu;

    @FXML
    @SuppressWarnings("unused")
    private StackPane statistics;

    @FXML
    @SuppressWarnings("unused")
    private VBox strategiesVBox, enterName;

    @FXML
    @SuppressWarnings("unused")
    private HBox playerA, playerB, gameBoard;

    @FXML
    @SuppressWarnings("unused")
    private Label playerAResult, playerBResult, lPlayerA, lPlayerB, turns, winner;

    @FXML
    @SuppressWarnings("unused")
    private Label field0, field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11;

    @FXML
    @SuppressWarnings("unused")
    private FlowPane seedResultPlayerB, seedResultPlayerA;

    @FXML
    @SuppressWarnings("unused")
    private FlowPane seed0, seed1, seed2, seed3, seed4, seed5, seed6, seed7, seed8, seed9, seed10, seed11;

    @FXML
    @SuppressWarnings("unused")
    private TableView<HighscorePlayer> tableHighscore;

    @FXML
    @SuppressWarnings("unused")
    private TableColumn<HighscorePlayer, Integer> columnRank, columnScore;

    @FXML
    @SuppressWarnings("unused")
    private TableColumn<HighscorePlayer, String> columnName;

    @FXML
    @SuppressWarnings("unused")
    private TextField player1Name, player2Name;

    @FXML
    @SuppressWarnings("unused")
    private Button deleteScoreButton, resetButton, statisticButton;

    @FXML
    @SuppressWarnings("unused")
    private ComboBox comboBoxA, comboBoxB;

    // ********************************************************************************
    // main code
    // ********************************************************************************

    /**
     * The constructor of the controller.
     */
    public Controller() {
        // model stuff
        this.game = new Game();
        game.initialize();
        game.addObserver(this);
        game.getGameBoard().addObserver(this);
        game.getPlayerA().addObserver(this);
        game.getPlayerB().addObserver(this);

        this.highscore = new HighscoreXML();
        if (!highscore.readFromXML()) {
            highscore.setHighscore(new Highscore());
        }

        // model-view stuff
        playersComboA = new ArrayList<>();
        playersComboB = new ArrayList<>();

        playersComboA.add(game.getPlayerA());
        for (ComputerStrategy computerStrategy : game.getComputerStrategies()) {
            playersComboA.add(genNewPlayer(computerStrategy.toString(), computerStrategy));
        }

        playersComboB.add(game.getPlayerB());
        for (ComputerStrategy computerStrategy : game.getComputerStrategies()) {
            playersComboB.add(genNewPlayer(computerStrategy.toString(), computerStrategy));
        }

        // view stuff
        this.image = new ImageView[GameBoard.GAME_RESET_TOTAL_SEEDS];
    }

    /**
     * generates a new player and add the observer
     *
     * @param name             the name of the new player
     * @param computerStrategy the strategy of the new player
     * @return the new player
     */
    private Player genNewPlayer(String name, ComputerStrategy computerStrategy) {
        Player result = new Player();

        result.setName(name);
        result.setComputerStrategy(computerStrategy);
        result.setSeeds(0);

        result.addObserver(this);

        return result;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.fields = new Label[]{
                field0, field1, field2, field3, field4, field5,
                field6, field7, field8, field9, field10, field11
        };
        this.seeds = new FlowPane[]{
                seed0, seed1, seed2, seed3, seed4, seed5,
                seed6, seed7, seed8, seed9, seed10, seed11,
        };
        this.img = new Image(Constants.IMAGE_SEED);

        this.timeline = new Timeline(new KeyFrame(
                Duration.millis(Constants.GAME_TURN_DELAY),
                ae -> {
                    if (viewStatesToUpdate.size() > 0) {
                        ViewState viewState = viewStatesToUpdate.get(0);
                        updateGameBoardWithViewState(viewState);
                        viewStatesToUpdate.remove(viewState);

                        if (viewStatesToUpdate.size() <= 0) {
                            timeline.stop();

                            doNextTurn();
                        }
                    }
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    /**
     * shows the different strategies
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings({"unchecked", "unused"})
    public void handleStart(ActionEvent event) {
        try {

            Node node = (Node) event.getSource();
            this.stage = (Stage) node.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.FXML_GAMEBOARD));
            loader.setController(this);
            this.background = loader.load();
            Scene scene = new Scene(background, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
            scene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());
            stage.setScene(scene);
            winner.setVisible(false);
            resetButton.setDisable(true);
            statisticButton.setDisable(true);

            comboBoxA.getItems().setAll(playersComboA);
            comboBoxA.getSelectionModel().select(0);
            comboBoxB.getItems().setAll(playersComboB);
            comboBoxB.getSelectionModel().select(0);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * handles combo box select event
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void handleComboBoxA(ActionEvent event) {
        handleComboBoxPlayer(comboBoxA, player1Name);
    }

    /**
     * handles combo box select event
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void handleComboBoxB(ActionEvent event) {
        handleComboBoxPlayer(comboBoxB, player2Name);
    }

    /**
     * updates the player label of the depending player on key released
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void handlePlayerNameKeyReleased(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        Player player = null;

        if (textField == player1Name) {
            player = (Player) comboBoxA.getSelectionModel().getSelectedItem();
        } else if (textField == player2Name) {
            player = (Player) comboBoxB.getSelectionModel().getSelectedItem();
        }

        if (player != null) {
            player.setName(textField.getText());
        }
    }

    /**
     * generic function to handle the combo box select event for player-selection<br>
     * sets the text field depending on the selected player
     *
     * @param cbPlayer     the combo box
     * @param tfPlayerName the player name
     */
    private void handleComboBoxPlayer(ComboBox cbPlayer, TextField tfPlayerName) {
        Object selectedItem = cbPlayer.getSelectionModel().getSelectedItem();
        if (selectedItem instanceof Player) {
            Player player = (Player) selectedItem;

            tfPlayerName.setDisable(player.getComputerStrategy() != null);
            tfPlayerName.setText(player.getName());
        }
    }

    /**
     * starts the game
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void handleGameStart(ActionEvent event) {
        Object objPlayerA = comboBoxA.getSelectionModel().getSelectedItem();
        if (objPlayerA != null && objPlayerA instanceof Player) {
            Player playerA = (Player) objPlayerA;
            game.setPlayerA(playerA);

            lPlayerA.setText(playerA.getName());
        }

        Object objPlayerB = comboBoxB.getSelectionModel().getSelectedItem();
        if (objPlayerB != null && objPlayerB instanceof Player) {
            Player playerB = (Player) objPlayerB;
            game.setPlayerB(playerB);

            lPlayerA.setText(playerB.getName());
        }

        game.reset();
        resetButton.setDisable(false);
        statisticButton.setDisable(false);
        enterName.setVisible(false);
    }

    /**
     * back to main menu
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void handleBack(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            this.stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.FXML_START_MENU));
            loader.setController(this);
            this.menu = loader.load();
            Scene scene = new Scene(menu, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

            scene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());

            if (!game.isOver()) {
                onActiveGameLeave();
            }
            stage.setScene(scene);
            stage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * set the game to 4 seeds each
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void handleReset(ActionEvent event) {
        game.reset();
        winner.setVisible(false);
    }

    /**
     * calls next turn
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void onIncrease(ActionEvent event) {
        if (!isAnimationInProgress()) {
            if (game.getCurrentPlayer().getComputerStrategy() == null) {
                Button btn = (Button) event.getSource();
                String id = btn.getId();
                int index = Integer.parseInt(id.substring(3));
                doNextTurn(index);
            } else {
                System.out.println("WARN: You can't play for the computer!");
            }
        } else {
            System.out.println("WARN: An animation is still in progress!");
        }
    }

    /**
     * calls the next turn
     *
     * @param index the index of the turn, use strategy if the index is &lt; 0
     */
    private void doNextTurn(int index) {
        if (!game.isOver()) {
            if (index < 0) {
                switch (game.doNextTurnIfStrategyExists()) {
                    case PLAYER_OUT_OF_SEEDS:
                        int firstIndex = GameBoard.getIndexRangeFrom(game.getCurrentPlayer() == game.getPlayerA());
                        game.nextTurn(firstIndex);
                        doNextTurn();
                        break;
                    case GAME_OVER:
                        onGameOver();
                        break;
                }
            } else {
                switch (game.nextTurn(index)) {
                    case GAME_OVER:
                        onGameOver();
                        break;
                }
            }
        } else {
            System.out.println("WARN: The current game is already over!");
        }
    }

    /**
     * Called when the game is over.<br>
     * This will update the interface, depending on who has won.
     */
    private void onGameOver() {
        game.setOver(true);

        gameBoard.setDisable(true);
        winner.setVisible(true);
        if (game.getPlayerA().getSeeds() > game.getPlayerB().getSeeds()) {
            winner.setText("You're the winner: " + game.getPlayerA().getName());
        } else if (game.getPlayerB().getSeeds() > game.getPlayerA().getSeeds()) {
            winner.setText("You're the winner: " + game.getPlayerB().getName());
        } else {
            winner.setText("It is a draw");
        }
        if (highscore.addGame(new HighscoreGame(game))) {
            highscore.writeToXML();
        }
    }

    /**
     * calls the next turn
     */
    private void doNextTurn() {
        doNextTurn(-1);
    }


    /**
     * shows the statistics
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings({"unused", "unchecked"})
    public void handleStatistics(ActionEvent event) {
        try {

            Node node = (Node) event.getSource();
            this.stage = (Stage) node.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource(Constants.FXML_STATISTICS));
            loader.setController(this);
            this.statistics = loader.load();
            Scene scene = new Scene(statistics, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);

            scene.getStylesheets().add(getClass().getResource(Constants.CSS_MAIN).toExternalForm());

            tableHighscore.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
            tableHighscore.getColumns().setAll(columnRank, columnName, columnScore);

            columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
            columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
            columnScore.setCellValueFactory(new PropertyValueFactory<>("score"));

            ObservableList highscoreList = tableHighscore.getItems();
            highscoreList.clear();
            if (highscore.getHighscore() != null) {
                List<HighscoreGame> games = highscore.getHighscore().getGames();
                int rank = 1;
                for (HighscoreGame hsGame : games) {
                    HighscorePlayer winner = hsGame.getWinnerPlayer();
                    if (winner != null && !winner.isComputer()) {
                        winner.setRank(rank);

                        highscoreList.add(winner);

                        rank++;
                    }
                }
            }

            deleteScoreButton.setDisable(highscore.getHighscore() == null);

            if (!game.isOver()) {
                onActiveGameLeave();
            }
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * clears highscore if exists
     *
     * @param event the event
     */
    @FXML
    @SuppressWarnings("unused")
    public void handleDeleteHighscore(ActionEvent event) {
        highscore.clearAndDelete();
        deleteScoreButton.setDisable(highscore.getHighscore() == null);
    }

    /**
     * update the different observed objects gameboard, player
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     *            method.
     */
    @Override
    public void update(Observable o, Object arg) {

        // update the game board dependencies
        // they must be updated outside of the runLater call to show the animation
        GameBoard gameBoard = game.getGameBoard();
        Player playerA = game.getPlayerA();
        Player playerB = game.getPlayerB();
        Player currentPlayer = game.getCurrentPlayer();
        int currentTurn = game.getCurrentTurn();

        // check if any object is null and just continue if all objects are valid
        if (gameBoard != null
                && playerA != null
                && playerB != null
                && currentPlayer != null) {

            ViewState viewState = new ViewState(
                    // clone to remove the reference link
                    gameBoard.getField().clone(),
                    playerA.getName(),
                    playerA.getSeeds(),
                    playerB.getName(),
                    playerB.getSeeds(),
                    currentPlayer == playerA,
                    currentTurn
            );

            if (currentTurn < 0) {
                Platform.runLater(() -> updateGameBoardWithViewState(viewState));
            } else {
                viewStatesToUpdate.add(viewState);
                timeline.play();
            }
        } else {
            System.out.println("WARN: Any of the objects is null, this should not happen, but you can ignore it.");
        }
    }

    /**
     * update the observed object game and the view of the seeds
     *
     * @param viewState the view state to update
     */
    private void updateGameBoardWithViewState(ViewState viewState) {
        int[] values = viewState.getValues();

        // update Label
        for (int i = 0; i < 12; i++) {
            fields[i].setText("" + values[i]);
        }

        // update seeds
        for (int i = 0; i < 12; i++) {
            int count = values[i];

            seeds[i].getChildren().clear();
            seeds[i].setMaxWidth(93);
            seeds[i].setMaxHeight(100);

            if (count < 9) {
                seeds[i].setMaxWidth(80);
                seeds[i].setHgap(-10);
                seeds[i].setVgap(-10);
            }
            if (count > 17) {
                seeds[i].setHgap(-15);
                seeds[i].setVgap(-15);
            }
            if (count > 25) {
                seeds[i].setHgap(-20);
                seeds[i].setVgap(-20);
            }
            if (count > 30) {
                seeds[i].setHgap(-30);
                seeds[i].setVgap(-30);
            }


            for (int j = 0; j < count; j++) {
                image[j] = new ImageView(img);
                seeds[i].getChildren().add(image[j]);
            }
        }

        seedResultPlayerA.getChildren().clear();
        seedResultPlayerB.getChildren().clear();


        for (int j = 0; j < viewState.getSeedsA(); j++) {
            image[j] = new ImageView(img);
            seedResultPlayerA.getChildren().add(image[j]);
        }

        for (int j = 0; j < viewState.getSeedsB(); j++) {
            image[j] = new ImageView(img);
            seedResultPlayerB.getChildren().add(image[j]);
        }

        // update the player seeds
        playerAResult.setText("" + viewState.getSeedsA());
        playerBResult.setText("" + viewState.getSeedsB());

        // update the game dependencies
        playerB.setDisable(viewState.isCurrentPlayerA());
        playerA.setDisable(!viewState.isCurrentPlayerA());

        if (viewState.isCurrentPlayerA()) {
            lPlayerA.setText("It's your turn: " + viewState.getNameA());
            lPlayerB.setText(viewState.getNameB());
        } else if (!viewState.isCurrentPlayerA()) {
            lPlayerA.setText(viewState.getNameA());
            lPlayerB.setText(viewState.getNameB() + ": It's your turn");
        }

        turns.setText("Turns: " + viewState.getCurrentTurn());
    }

    /**
     * will return true if currently an animation is in progress.
     *
     * @return true if animation is in progress
     */
    private boolean isAnimationInProgress() {
        return viewStatesToUpdate.size() > 0;
    }

    /**
     * will end the current game when leaving the scene
     */
    private void onActiveGameLeave() {
        game.setOver(true);
    }
}