package Tic.tac.toe.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;



//JPanel class Beginning ;
public class Cell extends JPanel {
	
	// X or O ;
	 char token = ' ' ;
	
	private TicTacToe gm = new TicTacToe() ;
	
	// constructor ;
	public Cell(){
		
		setBorder(new LineBorder( Color.BLACK, 1 ));
		addMouseListener(new MyMouseListner());
		
	} //constructor end ;
	
	
	//Getter ;
	public char getToken()
	{
		return token;
	}
	
	//Setter
	public void setToken(char token)
	{
		this.token = token ;
		repaint() ;
		
	}
	
	//Draw Token X or O ;
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		if(token == 'X'){
			
			g.drawLine(10 , 10 , getWidth() - 10, getHeight() - 10);
			g.drawLine(getWidth() - 10 , 10, 10, getHeight() - 10);
		}
		
		else if(token =='O'){
			
			g.drawOval(10 , 10 , getWidth() - 20 , getHeight() - 20);
		}
		
	}
	
	
	// Listner class Beginning ;
	private class MyMouseListner extends MouseAdapter{
		
		@Override     
		public void mouseClicked(MouseEvent e){ //Action Beginning
			
			if(token == ' ' && gm.whoseTurn != ' ' )
			{
				setToken(gm.whoseTurn) ;
			}
			
			if(gm.isWon(gm.whoseTurn)){
				
				gm.jlblStatus.setText(gm.whoseTurn + "won ! the game is over") ; 
				
				gm.whoseTurn = ' ' ; //Game is over ;
			} 
			
			else if(gm.isFull()){
				
				gm.jlblStatus.setText("Draw! the game is over");
				gm.whoseTurn = ' ' ; //Game is over ;
				
			}
			
			else{
				
				gm.whoseTurn = (gm.whoseTurn == 'X') ? 'O' : 'X' ;
				gm.jlblStatus.setText(gm.whoseTurn + "'s turn");
				
			}
			
			
		} // Action end
		
	}// Listner class end ;
	

}// JPanel class end ;
