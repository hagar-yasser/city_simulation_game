package model.disasters;

import model.people.Citizen;


public class Injury extends Disaster {

	public Injury(int startCycle, Citizen target) {
		super(startCycle, target);
	}
	@Override
	public void strike() 
	{
		Citizen target = (Citizen)getTarget();
		target.setBloodLoss(target.getBloodLoss()+30);
		super.strike();
	}
	@Override
	public void cycleStep() {
		Citizen target = (Citizen)getTarget();
		target.setBloodLoss(target.getBloodLoss()+10);
		
	}

}
