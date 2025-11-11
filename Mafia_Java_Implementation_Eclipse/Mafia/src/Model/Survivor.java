package Model;

import java.util.ArrayList;
// TODO: this nigga aint dying 
public class Survivor extends Role{
	public Survivor()  {
		super("survivor", 0,0,false,false,"unaligned","evil", 1);
		
	}

	@Override
	public boolean action(Player survivor, ArrayList<Player> playersList) {
		if(survivor != null && survivor.role.isRoleBlocked == false && survivor.role.actionsLeft > 0) {
			visit(survivor, playersList);
		}
		return false;
		
	}

	@Override
	public boolean visit(Player survivor, ArrayList<Player> PlayersList) {
		
		survivor.role.defence = 2;
		survivor.role.actionsLeft--;
		return false;
		
	}

	
}
	