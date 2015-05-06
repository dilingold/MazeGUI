package Model;


//-----------------------------------------------
//Programming 2 -- Semester 2, 2013
//Maze Engine implementation
//Peter Tilmanis, 22 July 2013
//-----------------------------------------------
//Maze grid reference class
//-----------------------------------------------

public class MazeRef
{
private int x, y;

public MazeRef()
{
   this(-1, -1);
}

public MazeRef(int x, int y)
{
   this.x = x;
   this.y = y;
}

public int getX()
{
   return x;
}

public int getY()
{
   return y;
}


public boolean equals(Object in)
{
   if ( !(in instanceof MazeRef) )
      return false;
   else
   {
      MazeRef temp = (MazeRef) in;
      if ( (this.x == temp.getX()) &&
           (this.y == temp.getY()))
         return true;
      else
         return false;
   }
}

public int hashCode()
{
   return this.toString().hashCode();
}

public String toString()
{
   return x + "," + y;
}
}
