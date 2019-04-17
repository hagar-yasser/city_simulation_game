package model.disasters;

import simulation.Rescuable;
import simulation.Simulatable;
import views.MainLook;

public abstract class Disaster implements Simulatable{
	private int startCycle;
	private Rescuable target;
	private boolean active;
	private MainLook mainLookListener;
	public Disaster(int startCycle, Rescuable target) {
		this.startCycle = startCycle;
		this.target = target;
	}

	public void setMainLookListener(MainLook mainLookListener) {
		this.mainLookListener = mainLookListener;
	}
	public String getName(){

		if(this instanceof Collapse)
			return "Collapse";
		if(this instanceof Fire)
			return "Fire";
		if(this instanceof GasLeak)
			return "GasLeak";
		if(this instanceof Infection)
			return "Infection";
		return "Injury";

	}

	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public int getStartCycle() {
		return startCycle;
	}
	public Rescuable getTarget() {
		return target;
	}
	public void strike() 
	{
		//(mainLookListener.getPanelsinGrid())[this.getTarget().getLocation().getX()][this.getTarget().getLocation().getY()].getRescButton().setDisaster(this);
		target.struckBy(this);
		active=true;
	}
}
