package lindenmeyer.axiom;

import lindenmeyer.symbols.*;
import java.util.*;

public class Axiom{
	
	private String axiom;
	
	public Axiom(String axiom){
		this.axiom= axiom;
	}
	
	public String getContent(){
		return this.axiom;
	}
	
	public void setContent(String new_axiom){
		this.axiom= new_axiom;
	}
	
	public boolean isInAlphabet(Alphabet alphabet){
		for(int i=0; i<this.axiom.length(); i++){
			char med= this.axiom.charAt(i);
			if(!(alphabet.isInAlphabet(new Symbol(med)))){
				return false;
			}
		}
		return true;
	}
}
