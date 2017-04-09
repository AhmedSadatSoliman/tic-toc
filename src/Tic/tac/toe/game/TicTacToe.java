package Tic.tac.toe.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.plaf.ColorUIResource;





public class TicTacToe extends JFrame{
	
	
	
	  
	
   char whoseTurn = 'X' ;
   
   JLabel jlblStatus = new JLabel("X 's turn to play") ;
   
   private Cell[][] cells = new Cell[3][3] ;
   

   //Initialize UI Beginning
   public TicTacToe(){
	   
	   
		
	
	   JPanel p = new JPanel(new GridLayout(3, 3 , 0, 0));
	   
	   for( int i = 0 ; i < 3 ; i++)
		   for(int j = 0 ; j < 3 ; j++)
		   {
			   p.add(cells[i][j] = new Cell());
		   }
	   
	  // p.setBorder(new LineBorder(Color.lightGray , 1));
	   jlblStatus.setBorder(new LineBorder(Color.yellow , 1));
	   
	   add(p , BorderLayout.CENTER);
	   add(jlblStatus , BorderLayout.SOUTH);
	   
	   setSize(250 , 250); 
		setTitle("TicTacToe");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		//setUndecorated(true);
		
		//setDefaultLookAndFeelDecorated(true);
		
		//UIManager.put(("InternalFrame.activeTitleBackground"), new ColorUIResource(Color.black ));
		//UIManager.put("InternalFrame.activeTitleForeground", new ColorUIResource(Color.WHITE));
		//UIManager.put("InternalFrame.titleFont", new Font("Dialog", Font.PLAIN, 11));
	   
   }//UI Initialize end
   
   public boolean isFull()
   {
	   for(int  i = 0 ; i < 3 ; i++)
		   for(int j = 0 ; j < 3 ; j++)
			   if(cells[i][j].getToken() == ' ')
				   return false ;
	   
				   return true;
				
   }
   
   public boolean isWon(char token){
	   
	   for(int i = 0 ; i < 3 ; i++)
		   if((cells[i][0].getToken() == token
				  && cells[i][1].getToken() == token
				  && cells[i][2].getToken() == token))
			   return true ;
	   
	   for(int j = 0 ; j < 3 ; j++)
		   if((cells[0][j].getToken() == token
				  && cells[1][j].getToken() == token
				  && cells[2][j].getToken() == token))
			   return true ;
		   
	   
	   
		   if((cells[0][0].getToken() == token
				  && cells[1][1].getToken() == token
				  && cells[2][2].getToken() ==token))
			   return true ;
	   
		   if((cells[0][2].getToken() == token
					  && cells[1][1].getToken() == token
					  && cells[2][0].getToken() == token))
				   return true ;
		   
	   
	   
	   return false ;
   }
	
	
	void playAgain(){
	
	int play =	JOptionPane.showConfirmDialog(null, "Play Again") ;
	
	if(play == 0)
	{
		
		this.setVisible(false);
		 new TicTacToe() ;
	}
	
	else if(play == 1)
	{
		
		System.exit(1);
	}
	
	else if(play == 2)
	{
		
		System.exit(1);
	}
	
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		TicTacToe m = new TicTacToe() ;
		
		
	
		
		
	}
	
	
	
	//JPanel class Beginning ;
	public class Cell extends JPanel {
		
		// X or O ;
		 char token = ' ' ;
		
		
		
		// constructor ;
		public Cell(){
			
			setBorder(new LineBorder( Color.white, 1 ));
			addMouseListener(new MyMouseListner());
			setBackground(Color.DARK_GRAY);
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
				
				g.setColor(Color.WHITE);
				g.drawLine(10 , 10 , getWidth() - 10, getHeight() - 10);
				g.drawLine(getWidth() - 10 , 10, 10, getHeight() - 10);
				
			}
			
			else if(token =='O'){
				
				g.setColor(Color.YELLOW);
				g.drawOval(10 , 10 , getWidth() - 20 , getHeight() - 20);
			}
			
		}
		
		
		// Listner class Beginning ;
		private class MyMouseListner extends MouseAdapter{
			
			@Override     
			public void mouseClicked(MouseEvent e){ //Action Beginning
				
				if(token == ' ' && whoseTurn != ' ' )
				{
					setToken(whoseTurn) ;
				}
				
				if(isWon(whoseTurn)){
					
					jlblStatus.setText(whoseTurn + "won ! the game is over") ; 
					
					 JOptionPane.showMessageDialog(null, whoseTurn + " won! congrats!");
					 playAgain();
				 	 
					 
					whoseTurn = ' ' ; //Game is over ;
					
					 
					
					
					
				} 
				
				else if(isFull()){
					
					jlblStatus.setText("Draw! the game is over");
					
					 JOptionPane.showMessageDialog(null, "Draw !") ;
					 		
					whoseTurn = ' ' ; //Game is over ;
					
				}
				
				else{
					
					whoseTurn = (whoseTurn == 'X') ? 'O' : 'X' ;
					jlblStatus.setText(whoseTurn + "'s turn");
					
				}
				
				
			} // Action end
			
		}// Listner class end ;
		

	}// JPanel class end ;

}
