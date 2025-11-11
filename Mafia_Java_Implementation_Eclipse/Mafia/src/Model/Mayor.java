package Model;

import java.util.ArrayList;

public class Mayor extends Role{
	public Mayor()  {
		super("mayor", 0,0,false,false,"town", "support", 10);
		
	}

	@Override
	public boolean action(Player target, ArrayList<Player> playersList) {
		return false;
		
	}

	@Override
	public boolean visit(Player player, ArrayList<Player> PlayersList) {
		return false;
		
	}

	
}
	