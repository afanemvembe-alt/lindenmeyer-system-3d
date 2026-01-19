package lindenmeyer.axiom;

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
	
	public boolean isInSymbol(){
		for(int i=0; i<this.axiom.length; i++){
			String med= this.axiom.charAt(i);
			if(!(Symbol.includes(med))){
				return false;
			}
		return true;
	}
}
