
import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

class tetrisview {
    tframewin frame;
    
    tetrisview() {

   	 frame = new tframewin();
   	 frame.setVisible( true );
    }
    
    public static void main( String args[] ) {
   	 new tetrisview();
    }
}

class tframewin extends JFrame implements KeyListener {

    drawpanel panel;
    Timer timer;
    
    tframewin() {
   	 setTitle( "TETRIS" );
   	 setBounds( 100, 100, 200, 320 );
   	 setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
 
   	 panel = new drawpanel();
   	 getContentPane().add( panel );
         
   	 addKeyListener( this );

   	 panel.repaint();
   	
   	 timer = new Timer();
   	 timer.schedule( new timerclass(), 1000, 1000 );
    }
    
    class timerclass extends TimerTask {
    	public void run() {
    		panel.tetobj.down();
    		panel.repaint();
    	}
    }
    
    public void keyPressed( KeyEvent e ) {

   	 if ( e.getKeyCode() == e.VK_LEFT ) {
   		 panel.tetobj.left();
   	 } else if ( e.getKeyCode() == e.VK_RIGHT ) {
   		 panel.tetobj.right();
   	 } else if ( e.getKeyCode() == e.VK_DOWN ) {
   		 if ( panel.tetobj.down() == 2 ) {
   			 // gameover
   		 }
   	 } else if ( e.getKeyCode() == e.VK_SPACE ) {
   		 panel.tetobj.rotate();
   	 }

   	 panel.repaint();
   	 
    }
    
    public void keyReleased( KeyEvent e ) {
    }

    public void keyTyped( KeyEvent e ) {
    }



}

class drawpanel extends JPanel {
	tetrisobj tetobj;
	
	Color [] blockcolor = {
		new Color( 255, 0, 0 ),
		new Color( 0, 255, 0 ),
		new Color( 0, 0, 255 ),
		new Color( 255, 255, 0 ),
		new Color( 255, 0, 255 ),
		new Color( 0, 255, 255 )
	};
	
	drawpanel() {
		tetobj = new tetrisobj();
		
	}
	
	void drawarea( Graphics g ) {
		int width = getWidth();
		int height = getHeight();

		int cx = tetobj.areawidth;
		int cy = tetobj.areaheight;
		
		int bx = width / cx;
		int by = height / cy;
		
		g.setColor( new Color( 0, 0, 0 ));
		g.fillRect( 0, 0, bx*cx, by*cy );
		
		int ix, iy;
		for ( iy = 0; iy < cy; iy ++ ) {
			for ( ix = 0; ix < cx; ix ++) {
				if ( tetobj.area[ix][iy] != 0 ) {
					g.setColor(blockcolor[ tetobj.area[ix][iy]-1] );
					g.fillRect( ix*bx, iy*by, bx, by );
				}
			}
		}
	}
	
    public void paintComponent( Graphics g) {
   	 super.paintComponent(g);
   	 
   	 drawarea( g );
   	 
    }
}