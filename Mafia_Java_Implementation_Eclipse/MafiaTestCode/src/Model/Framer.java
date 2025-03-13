package Model;

import java.util.ArrayList;

public class Framer extends Role {
	public Framer() {
		super("framer", 0, 0, true, false, "mafia", "backing", 10);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void action(Player target, ArrayList<Player> playersList) {

		
		if(target != null) {
			visit(target, playersList);
		}

	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		target.role.isFramed = true;

	}

	
}
