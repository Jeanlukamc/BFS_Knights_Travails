//Board Class
//By: Jean Luka Molina
//15/04/2022

import java.util.ArrayList;

public class Board
{
    private char [] [] board;
    private Knight myKnight;
    private int xStart, yStart;
    private int xEnd, yEnd;
    private BFS optimalMoves;

    /**
     * Default Constructor that creates the board
     */
    public Board( )
    {
        board = new char [8][8];
        xStart = 0;
        yStart = 0;
        xEnd = 7;
        yEnd = 7;

        myKnight = new Knight( xStart, yStart );

        optimalMoves = new BFS( myKnight );

        ChangeBoard( xStart, yStart );

        board[0][0] = myKnight.GetKnight( );
    }

    /**
     * Change the location of the Knight on the Board
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    private void ChangeBoard( int x, int y )
    {
        for( int i = 0; i < 8; i++ )
        {
            for( int j = 0; j < 8; j++ )
            {
                if( i % 2 == 0 )
                {
                    if( j % 2 == 0 )
                    {
                        board[i][j] = ' ';
                    }
                    else
                    {
                        board[i][j] = 'W';
                    }
                }
                else
                {
                    if( j % 2 == 0 )
                    {
                        board[i][j] = 'W';
                    }
                    else
                    {
                        board[i][j] = ' ';
                    }
                }
            }
        }

        board[xEnd][yEnd] = '@';
        board[x][y] = myKnight.GetKnight( );
    }

    /**
     * Set the points that we will work with
     * @param x1 Starting X-Point
     * @param y1 Starting Y-Point
     * @param x2 End X-Point
     * @param y2 End Y-Point
     */
    public void SetStartEnd( int x1, int y1, int x2, int y2 )
    {
        if( ( x1 < 0 || x1 > 7 ) || ( x2 < 0 || x2 > 7 ) ||
            ( y1 < 0 || y1 > 7 ) || ( y2 < 0 || y2 > 7 ) )
        {
            System.out.println( "One of the points is not valid" );
            return;
        }

        xStart = x1;
        yStart = y1;
        xEnd = x2;
        yEnd = y2;

        myKnight.ChangeCoordinates( xStart, yStart );
        //optimalMoves.UpdateData( xStart, yStart );
        ChangeBoard( myKnight.GetX( ), myKnight.Gety( ) );

        this.board[xEnd][yEnd] = '@';

        System.out.println( "New Starting Point: " + "(" + xStart + ", " + yStart + ") | New Ending Point: (" + xEnd + ", " + yEnd + ")" );
        System.out.println( myKnight );
    }

    /**
     * Displays the appropriate number of moves
     */
    public void GetOptimalMoves( )
    {
        ArrayList<String> moves = optimalMoves.DoBFS(xStart, yStart, xEnd, yEnd );
        int totalMoves = moves.size( ) - 1;
        
        
        for( int i = 1; i < moves.size( ); i++ )
        {
            char stringX = moves.get( i ).charAt( 1 );
            char stringY = moves.get( i ).charAt( 4 );
            int x = Integer.parseInt( String.valueOf( stringX ) );
            int y = Integer.parseInt( String.valueOf( stringY ) );

            ChangeBoard( x, y );
            System.out.println("Current move: " + moves.get( i ) );
            PrintBoard( );
            System.out.println("\n--------------------------------------------------------------------------" );
        }
        
        System.out.println( "Optimal Number of Moves: " +  totalMoves );
        System.out.println( "Moves: " + moves );
    }

    /**
     * Displays the Board Accordingly
     */
    public void PrintBoard( )
    {
        System.out.println( "" );
        for( int i = 0; i < 8; i++ )
        {
            System.out.print( i + " | " );
            for( int j = 0; j < 8; j++ )
            {
                System.out.print( board[i][j] + " | " );
            }
            System.out.println( "\n   -------------------------------" );
        }

        System.out.print( "    " );
        for( int i = 0; i < board[0].length; i++ )
        {
            System.out.print( i + "   " );
        }
        System.out.println( );
    }
}