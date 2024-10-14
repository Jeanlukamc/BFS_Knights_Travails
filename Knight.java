//Knight Class
//By: Jean Luka Molina
//15/04/2022

public class Knight
{
    private int [] [] knightMoves;
    private char theKnight;
    private int xCurrentPosition, yCurrentPosition;

    /**
     * Default Constructor, Sets the position of the knight
     * @param x X-Point
     * @param y Y-Point
     */
    public Knight( int x, int y )
    {
        int [][] moves = { {1, 2}, {1,-2}, {-1, 2}, {-1,-2}, {2, 1}, {2,-1}, {-2, 1}, {-2,-1} };
        knightMoves = moves;
        xCurrentPosition = x;
        yCurrentPosition = y;
        theKnight = '7';
    }

    /**
     * Change the coordinates of the Knight
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public void ChangeCoordinates( int x, int y )
    {
        xCurrentPosition = x;
        yCurrentPosition = y;
    }

    /**
     * Get all the possible moves that can be done by the knight
     * @return The Moves
     */
    public int [][] GetMoves( )
    {
        return( this.knightMoves );
    }

    /**
     * Returns X value from possible move based on index
     * @param idx The chosen index
     * @return Chosen X
     */
    public int GetXMove( int idx)
    {
        return( GetMoves( )[idx][0]);
    }

    /**
     * Returns Y value from possible move based on index
     * @param idx The chosen index
     * @return Chosen Y
     */
    public int GetYMove( int idx)
    {
        return( GetMoves( )[idx][1]);
    }
    


    /**
     * Gets the X-Coordinate
     * @return X-Coordinate
     */
    public int GetX( )
    {
        return( this.xCurrentPosition );
    }

    /**
     * Gets the Y-Coordinate
     * @return Y-Coordinate
     */
    public int Gety( )
    {
        return( this.yCurrentPosition );
    }


    /**
     * Returns the Knight
     * @return The Knight
     */
    public char GetKnight( )
    {
        return( this.theKnight );
    }

    /**
     * Prints the Knight
     */
    @Override
    public String toString( )
    {
        return( "|Knight's Position (7): (" + xCurrentPosition + ", " + yCurrentPosition + ")|" );
    }
}
