package de.chritte.testmvc.aview.tui.states.boat;

import java.util.List;
import java.util.UUID;

import android.content.Context;
import android.content.Intent;
import de.chritte.testmvc.aview.tui.StateContext;
import de.chritte.testmvc.aview.tui.TuiState;
import de.chritte.testmvc.aview.tui.activity.BoatActivity;
import de.chritte.testmvc.aview.tui.activity.TripActivity;
import de.chritte.testmvc.controller.IBoatController;

public class StartState implements TuiState {

	private List<UUID> boats;

	@Override
	public String buildString(StateContext context) {
		IBoatController controller = ((BoatActivity) context).getController();
		boats = controller.getBoats();
		StringBuilder sb = new StringBuilder();
		sb.append("n \t- New Boat\n");
		sb.append("[<X> \t- Show Boat\n");
		sb.append("---------------------------------------\n");
		int i = 1;
		for (UUID uuid : boats) {
			sb.append(i++).append(")\t").append(controller.getBoatName(uuid))
					.append("\n");
		}
		return sb.toString();
	}

	@Override
	public boolean process(StateContext context, String input) {
		Integer number;
		try {
			number = Integer.valueOf(input);
		} catch (NumberFormatException e) {
			if (input.equals("n"))
				context.setState(new NewState());
			return false;
		}
		UUID boat = boats.get(number - 1);
		Intent intent = new Intent((Context) context, TripActivity.class);
		intent.putExtra("Boat", boat.toString());
		((Context) context).startActivity(intent);
		return false;
	}

}