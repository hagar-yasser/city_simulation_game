package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import model.events.WorldListener;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class DiseaseControlUnit extends MedicalUnit {

	public DiseaseControlUnit(String unitID, Address location,
							  int stepsPerCycle, WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);
		Citizen target = (Citizen) getTarget();
		if (target.getHp() == 0) {
			jobsDone();
			return;
		} else if (target.getToxicity() > 0) {
			target.setToxicity(target.getToxicity() - getTreatmentAmount());
			if (target.getToxicity() == 0)
				target.setState(CitizenState.RESCUED);
		}

		else if (target.getToxicity() == 0)
			heal();

	}

	public void respond(Rescuable r) throws UnitException {


		if(r instanceof Citizen) {
			if (getTarget() != null && canTreat(getTarget())
					&& getState() == UnitState.TREATING)
				reactivateDisaster();
			if(canTreat(r))
				finishRespond(r);
			else {
				if(((Citizen) r).getState()==CitizenState.SAFE)
					throw new CannotTreatException(this,r,"The target is already safe.");
				else throw new IncompatibleTargetException(this,r,"The DiseaseControlUnit can respond to SOS calls from a citizen having infection only .");
			}
		}else throw new IncompatibleTargetException(this,r,"The disease control unit can respond to SOS calls from a citizen only");

	}

}
