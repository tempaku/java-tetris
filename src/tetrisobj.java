
import java.io.*;
import java.util.Random;

class tetrisobj {
    final static int areawidth = 10;
    final static int areaheight = 20;
    
    int [][] area = new int[areawidth][areaheight];    
    
    int score = 0;
    
    long seed = System.currentTimeMillis();
    Random r = new Random( seed );
    
    int numblock = 5;
    block_t [][] block = {
   	 {
   		 new block_t( 0, 0, 1, 0, 2, 0, 1, 1 ),
   		 new block_t( 1, 0, 1, 1, 1, 2, 2, 1 ),
   		 new block_t( 0, 1, 1, 1, 2, 1, 1, 0 ),
   		 new block_t( 1, 0, 1, 1, 1, 2, 0, 1 ),
   	 },
   	 {
   		 new block_t( 0, 0, 0, 1, 1, 0, 1, 1 ),
   		 new block_t( 0, 0, 0, 1, 1, 0, 1, 1 ),
   		 new block_t( 0, 0, 0, 1, 1, 0, 1, 1 ),
   		 new block_t( 0, 0, 0, 1, 1, 0, 1, 1 )
   	 },
   	 {
   		 new block_t( 0, 0, 1, 0, 2, 0, 3, 0 ),
   		 new block_t( 1, 0, 1, 1, 1, 2, 1, 3 ),
   		 new block_t( 0, 0, 1, 0, 2, 0, 3, 0 ),
   		 new block_t( 1, 0, 1, 1, 1, 2, 1, 3 ),
   	 },
   	 {
   		 new block_t( 0, 0, 0, 1, 1, 1, 1, 2 ),
   		 new block_t( 0, 1, 1, 1, 1, 0, 2, 0 ),
   		 new block_t( 0, 0, 0, 1, 1, 1, 1, 2 ),
   		 new block_t( 0, 1, 1, 1, 1, 0, 2, 0 ),
   		 
   	 },
   	 {
   		 new block_t( 1, 0, 1, 1, 0, 1, 0, 2 ),
   		 new block_t( 0, 0, 1, 0, 1, 1, 2, 1 ),
   		 new block_t( 1, 0, 1, 1, 0, 1, 0, 2 ),
   		 new block_t( 0, 0, 1, 0, 1, 1, 2, 1 ),
   	 }
    };
    
    int blockid;
    int rotateid;
    int x;
    int y;
    
    tetrisobj() {
   	 int i, j;
   	 for ( i = 0; i < areaheight; i ++ ) {
   		 for ( j = 0; j < areawidth; j ++ ) {
   			 area[j][i] = 0;
   		 }
   	 }
   	 	newblock();
    }

    void dumpout()
    {
   	 int i, j;
   	 String str;
   	 System.out.println( "-------------  " + score );
   	 for ( i = 0; i < areaheight; i ++ ) {
   		 str = "[";
   		 for ( j = 0; j < areawidth; j ++ ) {
   			 if ( area[j][i] == 1 ) {
   				 str += "+";
   			 } else {
   				 str += " ";
   			 }
   		 }
   		 str += "]";
   		 System.out.println( str );
   	 }
    }
    
    boolean putblock() {
   	 int i;
   	 for ( i = 0; i < 4; i ++ ) {
   		if ( (x + block[ blockid ][ rotateid ].x[i] < 0 ) ||
   	     	(x + block[ blockid ][ rotateid ].x[i] >= areawidth ) ) return false;

   		if ( (y + block[ blockid ][ rotateid ].y[i] < 0 ) ||
   	     	(y + block[ blockid ][ rotateid ].y[i] >= areaheight ) ) return false;
   	 }

   	 if ( (area[ x + block[ blockid ][ rotateid ].x[0] ][ y + block[ blockid ][ rotateid ].y[0] ] != 0 ) ||
   	  	(area[ x + block[ blockid ][ rotateid ].x[1] ][ y + block[ blockid ][ rotateid ].y[1] ] != 0 ) ||
   	  	(area[ x + block[ blockid ][ rotateid ].x[2] ][ y + block[ blockid ][ rotateid ].y[2] ] != 0 ) ||
   	  	(area[ x + block[ blockid ][ rotateid ].x[3] ][ y + block[ blockid ][ rotateid ].y[3] ] != 0 ) ) {
   		 return false;
   	 }
   	 
   	 area[ x + block[ blockid ][ rotateid ].x[0] ][ y + block[ blockid ][ rotateid ].y[0] ] = blockid+1;
   	 area[ x + block[ blockid ][ rotateid ].x[1] ][ y + block[ blockid ][ rotateid ].y[1] ] = blockid+1;
   	 area[ x + block[ blockid ][ rotateid ].x[2] ][ y + block[ blockid ][ rotateid ].y[2] ] = blockid+1;
   	 area[ x + block[ blockid ][ rotateid ].x[3] ][ y + block[ blockid ][ rotateid ].y[3] ] = blockid+1;

   	 return true;
    }

    int clearblock() {
   	 
   	 area[ x + block[ blockid ][ rotateid ].x[0] ][ y + block[ blockid ][ rotateid ].y[0] ] = 0;
   	 area[ x + block[ blockid ][ rotateid ].x[1] ][ y + block[ blockid ][ rotateid ].y[1] ] = 0;
   	 area[ x + block[ blockid ][ rotateid ].x[2] ][ y + block[ blockid ][ rotateid ].y[2] ] = 0;
   	 area[ x + block[ blockid ][ rotateid ].x[3] ][ y + block[ blockid ][ rotateid ].y[3] ] = 0;

   	 return 0;
    }
    
    void left() {
   	 clearblock();
   	 x --;
   	 if ( !putblock() ){
   		 x ++;
   		 putblock();
   	 }
    }

    void right() {
   	 clearblock();
   	 x ++;
   	 if ( !putblock() ) {
   		 x --;
   		 putblock();
   	 }
    }
    
    void rotate() {
   	 clearblock();
   	 rotateid ++;
   	 if ( rotateid == 4 ) rotateid = 0;
   	 if ( !putblock() ) {
   		 rotateid --;
   		 if ( rotateid < 0 ) rotateid = 3;
   		 putblock();
   	 }
    }
    
    int down() {
   	 clearblock();
   	 y ++;
   	 if ( !putblock() ) {
   		 y --;
   		 putblock();
   		 freeze();

   		 if ( newblock() ) return 2;
   	 
   		 return 1;
   	 }
   	 return 0;
    }
    
    boolean newblock() {
    	
   	 blockid = (int)(r.nextFloat() * numblock);
   	 rotateid = 0;
   	 x = areawidth / 2;
   	 y = 0;
   	 
   	 if ( !putblock() ) {
   		 // gameover
   		 return true;
   	 }
   	 return false;
    }

    void freeze() {
   	 
   	 area[ x + block[ blockid ][ rotateid ].x[0] ][ y + block[ blockid ][ rotateid ].y[0] ] = blockid+1;
   	 area[ x + block[ blockid ][ rotateid ].x[1] ][ y + block[ blockid ][ rotateid ].y[1] ] = blockid+1;
   	 area[ x + block[ blockid ][ rotateid ].x[2] ][ y + block[ blockid ][ rotateid ].y[2] ] = blockid+1;
   	 area[ x + block[ blockid ][ rotateid ].x[3] ][ y + block[ blockid ][ rotateid ].y[3] ] = blockid+1;

   	 linecheck();
    }
    
    
    void linecheck() {
   	 int ix, iy;
   	 int f;
   	 int lines = 0;

   	 for ( iy = 0; iy < areaheight; iy ++ ) {
   		 f = 1;
   		 for ( ix = 0; ix < areawidth; ix ++ ) {
   			 if ( area[ix][iy] == 0 ) f = 0;
   		 }
   		 if ( f == 1 ) {
   			 lines ++;
   			 score ++;
   			 // iy
   			 int jx, jy;
   			 for ( jy = iy; jy > 0; jy -- ) {
   				 for ( jx = 0; jx < areawidth; jx ++ ) {
   					 area[jx][jy] = area[jx][jy-1];
   				 }
   			 }

   			 for ( jx = 0; jx < areawidth; jx ++ ) {
   				 area[jx][0] = 0;
   			 }
   		 }
   	 }
   	 
    }

    class block_t {
        int [] x = new int[4];
        int [] y = new int[4];
        
        block_t( int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4 ) {
       	 x[0] = x1; y[0] = y1;
       	 x[1] = x2; y[1] = y2;
       	 x[2] = x3; y[2] = y3;
       	 x[3] = x4; y[3] = y4;
        }
    }

}

 