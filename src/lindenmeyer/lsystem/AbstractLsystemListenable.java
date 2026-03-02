package lindenmeyer.lsystem;

import java.util.*;

public abstract class AbstractLsystemListenable implements LsystemListenable{
	
	public List<LsystemListener> listeners;
	
	public AbstractLsystemListenable(){
		this.listeners= new ArrayList<>();
	}
	
	public void addListener(LsystemListener e){
		this.listeners.add(e);
	}
	
	public void removeListener(LsystemListener e){
		this.listeners.remove(e);
	}
	
	protected void lsystemChange(){
		for (LsystemListener e : listeners){
			e.lsystemUpdated(this);
		 }
	}
	
}
