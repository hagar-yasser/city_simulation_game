package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import simulation.Address;
import simulation.Rescuable;

public class FireTruck extends FireUnit {

	public FireTruck(String unitID, Address location, int stepsPerCycle,
					 WorldListener worldListener) {
		super(unitID, location, stepsPerCycle, worldListener);
	}

	public void respond(Rescuable r) throws UnitException {

		if(r instanceof ResidentialBuilding) {
			if(canTreat(r))
				super.respond(r);
			else {
				if(((ResidentialBuilding) r).getStructuralIntegrity()==100)
					throw new CannotTreatException(this,r,"The target is already safe.");
				else throw new IncompatibleTargetException(this,r,"The FireTruck can respond to SOS calls from a citizen having fire only .");
			}
		}else throw new IncompatibleTargetException(this,r,"The fire truck can respond to SOS calls from a residential building only.");


	}

	@Override
	public void treat() {
		getTarget().getDisaster().setActive(false);

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0) {
			jobsDone();
			return;
		} else if (target.getFireDamage() > 0)

			target.setFireDamage(target.getFireDamage() - 10);

		if (target.getFireDamage() == 0)

			jobsDone();

	}

}
