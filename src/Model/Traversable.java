package Model;

//-----------------------------------------------
//Programming 2 -- Semester 2, 2013
//Maze Engine implementation
//Peter Tilmanis, 22 July 2013
//-----------------------------------------------
//Traversable interface
//-----------------------------------------------

public interface Traversable
{
public boolean reset();

public Player getPlayer();

public Room[] getAllRooms();

public MazeRef getMazeSize();

public Room getRoom(MazeRef location);

public boolean move(String exitPointName) throws GameException;
}

