public class Test
{
    public static void main( String [] args )
    {
        Board newBoard = new Board( );
        System.out.println( "" );
        newBoard.SetStartEnd( 0, 0, 7, 6 );
        System.out.println("Initial Board: \n");
        newBoard.PrintBoard( );
        System.out.println("\n--------------------------------------------------------------------------" );
        newBoard.GetOptimalMoves( );
    }
}