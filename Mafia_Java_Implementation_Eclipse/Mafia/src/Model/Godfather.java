package Model;

import java.util.ArrayList;

public class Godfather extends Role {

	public Godfather() {
		super("godfather", 1, 1, false, false, "mafia", "attacking", 10); // Call the parent constructor with required parameter
	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Player player, ArrayList<Player> PlayersList) {
		// TODO Auto-generated method stub
		
	}


	
}
