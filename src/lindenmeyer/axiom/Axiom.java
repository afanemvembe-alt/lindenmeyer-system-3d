package lindenmeyer.axiom;

import lindenmeyer.symbols.*;
import java.util.*;

/**Represente l'axiome de depart 
 */
public class Axiom{
	
	//Chaine de caractere representant l'axiome de depart
	private String axiom;
	
	/**
     Constructeur
     @param axiom l'axiome
     */
	public Axiom(String axiom){
		this.axiom= axiom;
	}
	
	//Accesseur pour l'axiome
	public String getContent(){
		return this.axiom;
	}
	
	//Setter pour l'axiome
	public void setContent(String new_axiom){
		this.axiom= new_axiom;
	}
	
	/**Verifie si l'axiome est dans l'alphabet autorise
	 */
	public boolean isInAlphabet(Alphabet alphabet){
		for(int i=0; i<this.axiom.length(); i++){
			char med= this.axiom.charAt(i);
			if(!(alphabet.isInAlphabet(new Symbol(med)))){
				return false;
			}
		}
		return true;
	}
	
	@Override
	public String toString(){
		return this.axiom;
	}
}
