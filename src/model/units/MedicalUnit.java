package model.units;

import model.events.WorldListener;
import model.people.Citizen;
import simulation.Address;
import views.MainLook;

import java.awt.*;

public abstract class MedicalUnit extends Unit {
	private int healingAmount;
	private int treatmentAmount;
	private MainLook mainLookListener;

	public MainLook getMainLookListener() {
		return mainLookListener;
	}

	public void setMainLookListener(MainLook mainLookListener) {

		this.mainLookListener = mainLookListener;
	}

	public MedicalUnit(String unitID, Address location, int stepsPerCycle, WorldListener worldListener) {
		super(unitID, location, stepsPerCycle,worldListener);
		healingAmount=10;
		treatmentAmount=10;
	}

	public int getTreatmentAmount() {
		return treatmentAmount;
	}
	public  void heal()
	{
		Citizen target = (Citizen)getTarget();
		if(target.getHp()<100)
			target.setHp(target.getHp()+healingAmount);
		
		
		if(target.getHp() == 100) {
			//(mainLookListener.getPanelsinGrid())
					//[this.getTarget().getLocation().getX()][this.getTarget().getLocation().getY()].getRescButton().setBackground(Color.BLUE);
			jobsDone();

		}
		
	}
	
	
}
