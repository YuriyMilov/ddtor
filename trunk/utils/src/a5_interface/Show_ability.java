package a5_interface;

public class Show_ability {
public static void main(String[] args) {
	Ability tab = null;
	
	tab = new Q1_with_ability();
	tab.tell();
	
	tab = new Q2_with_ability();
	tab.tell(); 
}
}
