package ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.text.Position;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.Serializable;
import java.awt.event.ActionEvent;

import mancala.GameNotOverException;
import mancala.InvalidMoveException;
import mancala.MancalaGame;
import mancala.NoSuchPlayerException;
import mancala.Player;
import mancala.PitNotFoundException;
import mancala.Saver;
import mancala.UserProfile;
import mancala.KalahRules;

public class TextUI extends JFrame{
    private JPanel gameContainer;
    private JPanel menuContainer;
    private JPanel ruleContainer;
    private JPanel storeContainer1;
    private JPanel storeContainer2;
    private JLabel messageLabel;
    private JPanel gameButtons;
    private JMenuBar menuBar;
    private PositionAwareButton[][] buttons;
    private MancalaGame game;
    private Player player1;
    private Player player2;

    private TextUI(String title){
        super();
        basicSetUp(title);
        setupMainMenuContainer();
        add(menuContainer,BorderLayout.CENTER);
        player1 = new Player();
        player2 = new Player();
        game= new MancalaGame();
        pack();
    }

    private void basicSetUp(String title){
        this.setTitle(title);
        gameContainer = new JPanel();
        menuContainer = new JPanel();
        ruleContainer = new JPanel();
        gameButtons = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
    }
    private void mainMenu(){
        getContentPane().removeAll();
        basicSetUp("Mancala");
        setupMainMenuContainer();
        add(menuContainer,BorderLayout.CENTER);
        game= new MancalaGame();
        pack();
    }
    public static void main(String[] args){
        TextUI ui = new TextUI("Mancala Game");
        ui.setVisible(true);
    }
    private void setupGameContainer(){
        gameContainer.add(makeMancalaGrid(6, 2));
        storeContainer1.add(makeMancalaStore1());
        storeContainer2.add(makeMancalaStore2());
        gameButtons = new JPanel();
        gameButtons.add(makeSaveGameButton());
        gameButtons.add(makeExitButton());

    }
    private JButton makeMancalaStore1(){
        int num1=0;
        num1 = game.getBoard().getDataStructure().getStoreCount(1);
        JButton store1 = new JButton(Integer.toString(num1));
        store1.setPreferredSize(new Dimension(220,440));
        return store1;
    }
    private JButton makeMancalaStore2(){
        int num2=0;
        num2 = game.getBoard().getDataStructure().getStoreCount(2);
        JButton store2 = new JButton(Integer.toString(num2));
        store2.setPreferredSize(new Dimension(220,440));
        return store2;
    }
    private void setupMainMenuContainer(){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeNewGameButton());
        buttonPanel.add(makeLoadProfileButton());
        buttonPanel.add(makeLoadGameButton());
        buttonPanel.add(makeViewProfileButton());
        buttonPanel.add(makeViewProfileButton2());
        buttonPanel.add(makeExitButton());
        menuContainer.add(buttonPanel);
    }
    private JButton makeNewGameButton(){
        JButton button = new JButton("New Game");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> ruleChoice());
        return button;
    }
    private void newGame(){
        game.startNewGame();
        game.setPlayers(player1, player2);
        getContentPane().removeAll();
        gameContainer = new JPanel();
        storeContainer1 = new JPanel();
        storeContainer2 = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setupGameContainer();
        add(gameContainer,BorderLayout.CENTER);
        add(storeContainer2,BorderLayout.WEST);
        add(storeContainer1,BorderLayout.EAST);
        add(gameButtons,BorderLayout.SOUTH);
        revalidate();
        repaint();
        pack();
    }
    private JButton makeSaveGameButton(){
        JButton button = new JButton("Save Game");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e->saveGame());
        return button;
    }
    private void saveGame(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JTextField textField = new JTextField(20);
        textField.setBounds(10,10,200,25);
        JButton submitButton = new JButton("Enter name for savefile");
        submitButton.setBounds(10,40,200,25);
        submitButton.addActionListener(e -> {
            String userInput = textField.getText();
            Serializable objectToSave = game;
            try{
            Saver.saveObject(objectToSave,userInput + ".ser");
            } catch (IOException m){

            }
            mainMenu();
        });
        getContentPane().setLayout(null);
        add(textField);
        add(submitButton);
        revalidate();
        repaint();
        pack();
        setSize(new Dimension(600,600));
    }
    private void updateGameGUI(){
        getContentPane().removeAll();
        gameContainer = new JPanel();
        storeContainer1 = new JPanel();
        storeContainer2 = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setupGameContainer();
        add(gameContainer,BorderLayout.CENTER);
        add(storeContainer2,BorderLayout.WEST);
        add(storeContainer1,BorderLayout.EAST);
        add(gameButtons,BorderLayout.SOUTH);
        revalidate();
        repaint();
        pack();
    }
    private void ruleChoice(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeKalahButton());
        buttonPanel.add(makeAyoButton());
        ruleContainer.add(buttonPanel);
        add(ruleContainer,BorderLayout.CENTER);
        revalidate();
        repaint();
        pack();

    }
    private JButton makeKalahButton(){
        JButton button = new JButton("Kalah Rules");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> setKalahRules());      
        return button;
    }
    private void setKalahRules(){
        game.setRules(1);
        newGame();
    }
    private void setAyoRules(){
        game.setRules(2);
        newGame();
    }
    private JButton makeAyoButton(){
        JButton button = new JButton("Ayo Rules");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> setAyoRules());      
        return button;
    }
    private JButton makeLoadProfileButton(){
        JButton button = new JButton("Load/Create Profile");
        button.setMaximumSize(new Dimension(150,40));  
        button.addActionListener(e -> makeProfileButtons());    
        return button;
    }
    private void makeProfileButtons(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makeCreateProfileButton());
        buttonPanel.add(makeLoadButton());
        menuContainer.add(buttonPanel);
        add(menuContainer,BorderLayout.CENTER);
        revalidate();
        repaint();
        pack();
    }
    private JButton makeCreateProfileButton(){
        JButton button = new JButton("Create Profile");
        button.setMaximumSize(new Dimension(150,40));  
        button.addActionListener(e -> createProfile());    
        return button;
    }
    private void createProfile(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JTextField textField = new JTextField(20);
        textField.setBounds(10, 10, 200, 25);
        JButton submitButton = new JButton("Submit Name & Load into player 1");
        submitButton.setBounds(10, 40, 250, 25);
        submitButton.addActionListener(e -> {
            String userInput = textField.getText();
            player1.getUser().setName(userInput);
            Serializable objectToSave = player1.getUser();
            try{
            Saver.saveObject(objectToSave,userInput + ".ser");
            } catch (IOException m){

            }
            mainMenu();
        });
        getContentPane().setLayout(null);
        add(textField);
        add(submitButton);
        JButton returnButton = makeReturnToMenuButton();
        returnButton.setBounds(10,75,200,25);
        add(returnButton);
        revalidate();
        repaint();
        pack();
        setSize(new Dimension(600,600));
    }
    private JButton makeLoadButton(){
        JButton button = new JButton("Load Profile");
        button.setMaximumSize(new Dimension(150,40));  
        button.addActionListener(e -> loadProfile());    
        return button;
    }
    private void loadProfile(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JTextField textField = new JTextField(20);
        textField.setBounds(10, 10, 200, 25);
        JButton submitButton = new JButton("Load Profile Player 1");
        submitButton.setBounds(10, 40, 200, 25);
        submitButton.addActionListener(e -> {
            String userInput = textField.getText();
            Serializable objectToSave = player1.getUser();
            try{
            player1.setUser((UserProfile)Saver.loadObject(userInput + ".ser"));
            player1.setName(player1.getUser().getName());
            } catch (IOException m){
                JOptionPane.showMessageDialog(null,"That profile does not exist.");
            }
            mainMenu();
        });
        JButton submitButton2 = new JButton("Load Profile Player 2");
        submitButton2.setBounds(10, 75, 200, 25);
        submitButton2.addActionListener(e -> {
            String userInput = textField.getText();
            Serializable objectToSave = player2.getUser();
            try{
            player2.setUser((UserProfile)Saver.loadObject(userInput + ".ser"));
            player2.setName(player2.getUser().getName());
            } catch (IOException m){
                JOptionPane.showMessageDialog(null,"That profile does not exist.");
            }
            mainMenu();
        });
        getContentPane().setLayout(null);
        add(textField);
        add(submitButton2);
        add(submitButton);
        JButton returnButton = makeReturnToMenuButton();
        returnButton.setBounds(10,110,200,25);
        add(returnButton);
        revalidate();
        repaint();
        pack();
        setSize(new Dimension(600,600));
    }
    private JButton makeLoadGameButton(){
        JButton button = new JButton("Load Game");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> loadGame());  
        return button;
    }
    private void loadGame(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JTextField textField = new JTextField(20);
        textField.setBounds(10, 10, 200, 25);
        JButton submitButton = new JButton("Load Game");
        submitButton.setBounds(10, 40, 150, 25);
        submitButton.addActionListener(e -> {
            String userInput = textField.getText();
            try{
            continueGame((MancalaGame)Saver.loadObject(userInput + ".ser"));
            } catch (IOException m){

            }
        });
        getContentPane().setLayout(null);
        add(textField);
        add(submitButton);
        revalidate();
        repaint();
        pack();
        setSize(new Dimension(600,600));
    }
    private void continueGame(MancalaGame continuegame){
        game = continuegame;
        getContentPane().removeAll();
        gameContainer = new JPanel();
        storeContainer1 = new JPanel();
        storeContainer2 = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setupGameContainer();
        add(gameContainer,BorderLayout.CENTER);
        add(storeContainer2,BorderLayout.WEST);
        add(storeContainer1,BorderLayout.EAST);
        add(gameButtons,BorderLayout.SOUTH);
        revalidate();
        repaint();
        pack();
    }
    private JButton makeViewProfileButton(){
        JButton button = new JButton("View Profile 1");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> viewProfile());
        return button;
    }
    private JButton makeViewProfileButton2(){
        JButton button = new JButton("View Profile 2");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> viewProfile2());
        return button;
    }
    private void viewProfile(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JTextField textField = new JTextField(20);
        textField.setBounds(10, 10, 200, 25);
        textField.setText(player1.getUser().getName());
        textField.setEditable(false);
        JTextField textField1 = new JTextField(20);
        textField1.setBounds(10, 45, 200, 25);
        textField1.setText("Total Wins: " + Integer.toString(player1.getUser().getWins()));
        textField1.setEditable(false);
        JTextField textField2 = new JTextField(20);
        textField2.setBounds(10, 80, 200, 25);
        textField2.setText("Kalah Wins: " + Integer.toString(player1.getUser().getKalah()));
        textField2.setEditable(false);
        JTextField textField3 = new JTextField(20);
        textField3.setBounds(10, 115, 200, 25);
        textField3.setText("Ayo Wins: " + Integer.toString(player1.getUser().getAyo()));
        textField3.setEditable(false);

        JButton exitButton = makeReturnToMenuButton();
        exitButton.setBounds(10,150,150,25);
        getContentPane().setLayout(null);
        add(textField);
        add(textField1);
        add(textField2);
        add(textField3);
        add(exitButton);
        revalidate();
        repaint();
        pack();
        setSize(new Dimension(600,600));
    }
    private void viewProfile2(){
        getContentPane().removeAll();
        basicSetUp("Mancala Game");
        JTextField textField = new JTextField(20);
        textField.setBounds(10, 10, 200, 25);
        textField.setText(player2.getUser().getName());
        textField.setEditable(false);
        JTextField textField1 = new JTextField(20);
        textField1.setBounds(10, 45, 200, 25);
        textField1.setText("Total Wins: " + Integer.toString(player2.getUser().getWins()));
        textField1.setEditable(false);
        JTextField textField2 = new JTextField(20);
        textField2.setBounds(10, 80, 200, 25);
        textField2.setText("Kalah Wins: " + Integer.toString(player2.getUser().getKalah()));
        textField2.setEditable(false);
        JTextField textField3 = new JTextField(20);
        textField3.setBounds(10, 115, 200, 25);
        textField3.setText("Ayo Wins: " + Integer.toString(player2.getUser().getAyo()));
        textField3.setEditable(false);

        JButton exitButton = makeReturnToMenuButton();
        exitButton.setBounds(10,150,150,25);
        getContentPane().setLayout(null);
        add(textField);
        add(textField1);
        add(textField2);
        add(textField3);
        add(exitButton);
        revalidate();
        repaint();
        pack();
        setSize(new Dimension(600,600));
    }
    private JButton makeExitButton(){
        JButton button = new JButton("Exit");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> System.exit(0));
        return button;
    }
    
    private void makeMove(int startPit){
        try{
            game.getBoard().moveStones(startPit, game.getBoard().getCurrentPlayer());
        } catch (InvalidMoveException e){
            JOptionPane.showMessageDialog(null,"Invalid move! Try Again");
            game.getBoard().swapGoAgain();
        }
        updateGameGUI();
        if(game.getBoard().isGoAgain() == false){
            game.swapTurn();
            game.getBoard().swapCurrentPlayer();
        } else {
            JOptionPane.showMessageDialog(null, "Go Again!");
            game.getBoard().swapGoAgain();
        }
        if(game.isGameOver() == true){
            try{
            game.getWinner();
            } catch (GameNotOverException e){

            }
            updateGameGUI();
            try{
            makeWinnerScreen(game.getWinner());
            } catch (GameNotOverException e){

            }
        }
    }
    private void makeWinnerScreen(Player winner){
        if(winner == null){
            JOptionPane.showMessageDialog(null, "The game is a Tie!");
        } else {
            JOptionPane.showMessageDialog(null, winner.getName() + " Wins!");
            winner.getUser().addWins();
            if(game.getBoard() instanceof KalahRules){
                winner.getUser().addKalah();
            } else {
                winner.getUser().addAyo();
            }
            Serializable objectToSave = winner.getUser();
            try{
                Saver.saveObject(objectToSave,winner.getUser().getName() + ".ser");
            } catch (IOException m){

            }
        }
        getContentPane().removeAll();
        menuContainer = new JPanel();
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.add(makePlayAgainButton());
        buttonPanel.add(makeReturnToMenuButton());
        buttonPanel.add(makeExitButton());
        menuContainer.add(buttonPanel);
        add(menuContainer,BorderLayout.CENTER);
        revalidate();
        repaint();
        pack();
    }
    private JButton makePlayAgainButton(){
        JButton button = new JButton("Play Again");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> ruleChoice());
        return button;
    }
    private JButton makeReturnToMenuButton(){
        JButton button = new JButton("Return to Menu");
        button.setMaximumSize(new Dimension(150,40));
        button.addActionListener(e -> mainMenu());
        return button;
    }
    private JPanel makeMancalaGrid(int wide, int tall){
        JPanel panel = new JPanel();
        buttons = new PositionAwareButton[tall][wide];
        panel.setLayout(new GridLayout(tall,wide));
        for(int y = 0; y<tall;y++){
            for(int x = 0; x<wide; x++){
                final int k = x;
                final int j = y;
                buttons[y][x] = new PositionAwareButton();
                buttons[y][x].setAcross(x+1);
                buttons[y][x].setDown(y+1);
                buttons[y][x].setPreferredSize(new Dimension(220,220));
                try{
                if(y== 1){
                    buttons[y][x].setText(Integer.toString(game.getNumStones((x+1))));
                } else {
                    buttons[y][x].setText(Integer.toString(game.getNumStones(12-x)));
                }
                } catch (PitNotFoundException e){

                }
                buttons[y][x].addActionListener(e -> {
                    if(j==1){
                        makeMove(k+1);
                    } else {
                        makeMove(12-k);
                    }
                });
                panel.add(buttons[y][x]);
            }
        }
        return panel;
    }
}
