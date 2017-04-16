/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author ON TRACK
 */

import java.awt.*;

import javax.swing.*;

import java.util.*;

 

import java.awt.event.MouseListener;

import java.awt.event.MouseEvent;

class TTTPanel extends JPanel implements MouseListener {

    private int size = 300;

    private TicTocToe tttGame;


    public TTTPanel(TicTocToe tttGame) {

        setPreferredSize(new Dimension(size, size));

        addMouseListener(this);

        this.tttGame = tttGame;

    }

    public void paint(Graphics g) {

        g.setColor(Color.WHITE);

        g.fillRect(0, 0, getWidth(), getHeight());

        g.setColor(Color.BLACK);

        g.drawLine(0,100,300,100);

        g.drawLine(0,200,300,200);

        g.drawLine(100,0,100,300);

        g.drawLine(200,0,200,300);

 

        Font f = new Font("Times", Font.PLAIN, 50);

        g.setFont(f);

        FontMetrics fm = g.getFontMetrics();

 

        int a = fm.getAscent();

        int h = fm.getHeight();

 

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                char curSquare = tttGame.getSquare(i,j);

                String curSqStr = Character.toString(curSquare);

                int w = fm.stringWidth(curSqStr);

                g.drawString(curSqStr, 100*i + 50 - w/2, 100*j + 50 + a - h/2);

            }

        }

    }

 

    public void mousePressed(MouseEvent e) { }

    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

 

    public void mouseClicked(MouseEvent e) {

        int x = e.getX() / 100;

        int y = e.getY() / 100;

 

        if (tttGame.getSquare(x,y) == tttGame.EMPTY) {

            tttGame.makeMove(x,y);

            repaint();

            tttGame.checkForGameEnd();

            tttGame.randomMove();

            tttGame.checkForGameEnd();

        }

    }

}

public class TicTocToe extends JFrame {

    public static final int ROWS = 3, COLS = 3;

    public static final char EMPTY=' ', PLAYER1='X', PLAYER2='O';

    private char[][] board;

    private int turn;

 

    public TicTocToe() {

        super("Tic Tac Toe");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

 

        board = new char[3][3]; // initialize board

        initBoard();

         

        getContentPane().add(new TTTPanel(this));

        pack();

        setVisible(true); // Show the JFrame.

    }

     

    private void initBoard() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                board[i][j] = EMPTY; // blank char

            }

        }

        turn = 0;

    }

         

         

    public char getCurPlayer() { return (turn%2==0) ? PLAYER1 : PLAYER2; }

    public void makeMove(int x, int y) {

        setSquare(x, y, getCurPlayer());

        turn++;

    }

    private void setSquare(int x, int y, char c) { board[x][y] = c; }

    public char getSquare(int x, int y) {

        return board[x][y];

    }

 

    public void printBoard() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                System.out.print(" " + board[i][j]);

            }

            System.out.println();

        }

    }

    private boolean checkForWin(char p) {

        for (int i = 0; i < 3; i++) {

            if (board[i][0] == p && board[i][1] == p && board[i][2] == p) { return true; }

            if (board[0][i] == p && board[1][i] == p && board[2][i] == p) { return true; }

        }

        if (board[0][0] == p && board[1][1] == p && board[2][2] == p) { return true; }

        if (board[0][2] == p && board[1][1] == p && board[2][0] == p) { return true; }
        return false;

    }

    private int getMovesLeft() { return (ROWS*COLS)-turn; }

     

    private boolean checkFullBoard() { return getMovesLeft()<1; }

 

    public void checkForGameEnd() {

        if (checkForWin(PLAYER1)) {

            JOptionPane.showMessageDialog(this, PLAYER1 + " wins!!!!");

            System.exit(0);

        } else if (checkForWin(PLAYER2)) {
            
            JOptionPane.showMessageDialog(this, PLAYER2 + " wins!!!!");

            System.exit(0);

        } else if (checkFullBoard()) {

            JOptionPane.showMessageDialog(this, "Game over, draw.");

            System.exit(0);

        }

    }

     

    public void randomMove() {

        int pos = new Random().nextInt(getMovesLeft());

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == EMPTY) {

                    if (--pos<0) {

                        makeMove(i,j);

                        return;

                    }

                }

            }

        }

    }

    public static void main(String[] args) {

        TicTocToe newGame = new TicTocToe();

    }

}
