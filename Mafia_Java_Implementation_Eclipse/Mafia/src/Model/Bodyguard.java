package Model;

import java.util.ArrayList;
import java.util.Random;

//TODO: zavrsiti bodyguarda; niko ne umire kada bodyguard nekog zastiti

public class Bodyguard extends Role {
	public Player bodyguard;
	ArrayList<Player> attackers = new ArrayList<>();

	public Bodyguard() {
		super("bodyguard", 2, 0, false, false, "town", "protective", 1);

	}
	
	 

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		for (Player player : playersList) {
		    if ("bodyguard".equalsIgnoreCase(player.role.name)) {
		        bodyguard = player;
		        break;
		        
		    }
		}
		
		for (Player player : playersList) {
			if(player.target == bodyguard.target && player != bodyguard && 
					(player.role.name.equals("vigilante") || player.role.name.equals("mafioso") || 
							player.role.name.equals("godfather"))) {
		    	attackers.add(player);
		    	
		    }
		}
		
		if (!bodyguard.role.isRoleBlocked) {
			if ((bodyguard == target && bodyguard.role.hasAction == 0)) {
				return;
			}
			visit(target, playersList);
		}
	}

	@Override
	public void visit(Player target, ArrayList<Player> PlayersList) {
		
		if(attackers.size() == 1) {
			Player attacker = attackers.get(0);
			if(target.role.defence > 0) {
				return;
			}
			else {
				target.role.defence = 2;
			}
			if (target != bodyguard) {
				if(bodyguard.role.attack > attacker.role.defence) {
					bodyguard.isAlive = false;
					attacker.isAlive = false;
				}
			}
		}
		else if(attackers.size() == 2) {
			Player attacker = attackers.get(randomBoolean() ? 0 : 1);
			attacker.isAlive = false;
			bodyguard.isAlive = false;
		}
		
	}
	boolean randomBoolean() {

		return new Random().nextBoolean();

	}

	

}
