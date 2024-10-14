
import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collections;

public class BFS 
{

    /**
     * Class used to make coordinate storing much easier on the queue rather than having two queues
     */
    private class Coordinates
    {
        int x1, y1;
        String x2, y2;

        /**
         * Simple set of Coordinates
         * @param x1 Current X-Coordinate
         * @param y1 Current Y-Coordinate
         * @param x2 Previous X-Coordinate
         * @param y2 Previous Y-Coordinate
         */
        Coordinates( int x1, int y1, String x2, String y2)
        {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    private Queue<Coordinates> Queue;
    private Knight knight;
    private ArrayList<String> visited;
    private ArrayList<Coordinates> previous;

    /**
     * Constructor for the BFS
     * @param myKnight Need the knight for available movements and coordinates
     */
    public BFS(Knight myKnight )
    {
        this.Queue = new LinkedList<Coordinates>( );
        this.knight = myKnight;
        this.visited = new ArrayList<String>( );
        this.previous = new ArrayList<Coordinates>( );
    }

    /**
     * Does Breadth First Search to find the shortest path to the end
     * @param startX X-Starting Point
     * @param startY Y-Starting Point
     * @param endX X-Ending Point
     * @param endY Y-Ending Point
     * @return Array List of the moves required to get there
     */
    public ArrayList<String> DoBFS(  int startX, int startY, int endX, int endY )
    {
        //Add the start to the Queue and to visited
        Coordinates start = new Coordinates( startX, startY, null, null );
        Queue.add( start );
        this.visited.add( "(" + startX + ", " + startY + ")" );
        this.previous.add( start );

        //Keep going until the Queue is completely empty
        while( !Queue.isEmpty( ) )
        {
            int queueSize = Queue.size( );

            //Go through every possible direction in the queue
            for( int i = 0; i < queueSize; i++ )
            {
                //Get the next coordinate and test the possible directions
                Coordinates current = Queue.remove( );
                
                //If we are at the end, return the total amount of moves
                if( current.x1 == endX && current.y1 == endY )
                {
                    ArrayList<String> moves = new ArrayList<String>( );
                    Coordinates temp = current;
                    
                    moves.add( "(" + temp.x1 + ", " + temp.y1 + ")" );
                    while( true )
                    {
                        //If we have reached the beginning, we can stop
                        if( temp.x2 == null && temp.y2 == null)
                        {
                            break;
                        }

                        //Search for the previous path through this loop
                        for( Coordinates c : previous )
                        {
                            //Check for the previous one to see if it matches the appropriate path
                            if( Integer.parseInt( temp.x2) == c.x1  && Integer.parseInt( temp.y2) ==  c.y1  )
                            {
                                moves.add( "(" + temp.x2 + ", " + temp.y2 + ")" );
                                temp = c;
                                if( temp.x2 == null && temp.y2 == null)
                                {
                                    break;
                                }
                            }
                        }

                    }

                    //Reverse the list so the appropriate path is shown
                    Collections.reverse( moves );
                    return( moves );
                }

                //Queue up all possible directions that can be traversed
                for( int j = 0; j < knight.GetMoves( ).length; j++ )
                {
                    int newX = current.x1 + knight.GetXMove( j );
                    int newY = current.y1 + knight.GetYMove( j );
                    
                    //Do not cue any coordinates that leave the board
                    if( ( newX < 0 || newX > 7 ) || ( newY < 0 || newY > 7 ) )
                    {
                        continue;
                    }
                    //Add to the queue if the places have not been visited
                    else if ( !visited.contains( "(" + newX + ", " + newY + ")" ) )
                    {
                        Coordinates temp = new Coordinates( newX, newY, Integer.toString( current.x1 ) , Integer.toString(current.y1)  );
                        Queue.add( temp );
                        visited.add( "(" + newX + ", " + newY + ")" );
                        previous.add( temp );
                    }
                }
            }

            PrintQueue( );
        }

        return( null );
    }

    /**
     * Prints the Queue (Both as if they were one)
     */
    public void PrintQueue( )
    {
        System.out.print( "Current Queue: \n-> [" );
        for( Coordinates c : Queue )
        {
            System.out.print( "(" + c.x1 + ", " + c.y1 + "), " );
        }
        System.out.println( "] \n--------------------------------------------------------------------------" );
    }

    /**
     * Prints the Coordinates that have already been tried
     */
    public void PrintVisited( )
    {
        System.out.print( "Visited Nodes: \n-> [" );
        for( String c : visited )
        {
            System.out.print( c );
        }
        System.out.println( "] \n--------------------------------------------------------------------------" );
    }


    /**
     * Prints the Queue (Both as if they were one)
     */
    public void PrintPrevious( )
    {
        System.out.print( "Current Previous List: \n-> [" );
        for( Coordinates c : previous )
        {
            System.out.print( "Previous: " + "(" + c.x2 + ", " + c.y2 + ")" + ", Current: (" + c.x1 + ", " + c.y1 + ") || " );
        }
        System.out.println( "] \n--------------------------------------------------------------------------" );
    }

}
