package Model;

import java.util.ArrayList;

public class Player {
	public String name;
	public int number;
	public Role role;
	public boolean isAlive = true;
	public Player target;
	public ArrayList<Player> targetedBy;
	
	public Player(String name, int number, Role role) {
		this.name = name;
		this.number = number;
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
}


