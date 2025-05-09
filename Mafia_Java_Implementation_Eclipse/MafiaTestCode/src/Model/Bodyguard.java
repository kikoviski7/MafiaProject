package Model;

import java.util.ArrayList;
import java.util.Random;

//TODO: zavrsiti bodyguarda; niko ne umire kada bodyguard nekog zastiti

public class Bodyguard extends Role {
	Player bodyguard;
	Player godfather;
	Player mafioso;
	Player mafiaBacking;
	ArrayList<Player> attackers = new ArrayList<>();

	public Bodyguard() {
		super("bodyguard", 2, 0, false, false, "town", "protective", 1);

	}
	
	 

	@Override
	public void action(Player target, ArrayList<Player> playersList) {
		for (Player player : playersList) {
		    if ("godfather".equalsIgnoreCase(player.role.name)) {
		    	godfather = player;
		        break;
		    }
		}
		
		for (Player player : playersList) {
		    if ("mafioso".equalsIgnoreCase(player.role.name)) {
		    	mafioso = player;
		        break;
		    }
		}
		
		for (Player player : playersList) {
		    if ("framer".equalsIgnoreCase(player.role.name) || "consort".equalsIgnoreCase(player.role.name)) {
		    	mafiaBacking = player;
		        break;
		    }
		}
		
		
		for (Player player : playersList) {
		    if ("bodyguard".equalsIgnoreCase(player.role.name)) {
		        bodyguard = player;
		        break;
		        
		    }
		}
		
		// ako role ne targetuje bodyguarda i ako je role napadac
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
			if(target != null) {
				visit(target, playersList);
			}
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
					if(attacker == godfather) {
						mafioso.role.inherits = true;
					}
					else if(attacker == mafioso && godfather.isAlive == false) {
						mafiaBacking.role.inherits = true;
					}
					
				}
			}
		}
		else if(attackers.size() == 2) {
			Player attacker = attackers.get(randomBoolean() ? 0 : 1);
			attacker.isAlive = false;
			bodyguard.isAlive = false;
			if(attacker == godfather) {
				mafioso.role.inherits = true;
			}
			else if(attacker == mafioso && godfather.isAlive == false) {
				mafiaBacking.role.inherits = true;
			}
			
		}
		
	}
	boolean randomBoolean() {

		return new Random().nextBoolean();

	}

	

}
