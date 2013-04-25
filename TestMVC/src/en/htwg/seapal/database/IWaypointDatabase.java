package en.htwg.seapal.database;

import java.util.List;
import java.util.UUID;

import en.htwg.seapal.model.IWaypoint;

public interface IWaypointDatabase {

	UUID newWaypoint();

	void saveWaypoint(IWaypoint waypoint);

	void deleteWaypoint(UUID id);

	IWaypoint getWaypoint(UUID id);

	List<IWaypoint> getWaypoints();

	boolean closeDB();
}
