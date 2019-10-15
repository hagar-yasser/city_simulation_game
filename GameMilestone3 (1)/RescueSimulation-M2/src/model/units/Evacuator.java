package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import model.people.CitizenState;
import simulation.Address;
import simulation.Rescuable;

public class Evacuator extends PoliceUnit {

	public Evacuator(String unitID, Address location, int stepsPerCycle,
					 WorldListener worldListener, int maxCapacity) {
		super(unitID, location, stepsPerCycle, worldListener, maxCapacity);

	}

	public void respond(Rescuable r) throws UnitException {

		if(r instanceof ResidentialBuilding) {
			if(canTreat(r))
				super.respond(r);
			else {
				if(((ResidentialBuilding) r).getStructuralIntegrity()==100)
					throw new CannotTreatException(this,r,"The target is already safe.");
				else throw new IncompatibleTargetException(this,r,"The Evacuator can respond to SOS calls from a building  having collapse only .");
			}
		}else {
			throw new IncompatibleTargetException(this,r,"The evacuator can respond to SOS calls from a residential building only.");
		}

	}
	@Override
	public void treat() {
		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0
				|| target.getOccupants().size() == 0) {
			jobsDone();
			return;
		}

		for (int i = 0; getPassengers().size() != getMaxCapacity()
				&& i < target.getOccupants().size(); i++) {
			getPassengers().add(target.getOccupants().remove(i));
			i--;
		}

		setDistanceToBase(target.getLocation().getX()
				+ target.getLocation().getY());

	}

}
