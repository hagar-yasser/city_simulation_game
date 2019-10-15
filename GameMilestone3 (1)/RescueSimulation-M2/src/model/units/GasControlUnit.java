package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import simulation.Address;
import simulation.Rescuable;

public class GasControlUnit extends FireUnit {

	public GasControlUnit(String unitID, Address location, int stepsPerCycle,
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
				else throw new IncompatibleTargetException(this,r,"The gasUnit can respond to SOS calls from a building having gasleak only .");
			}
		}else throw new IncompatibleTargetException(this,r,"The gas control unit can respond to SOS calls from a residential building only.");


	}

	public void treat() {
		getTarget().getDisaster().setActive(false);

		ResidentialBuilding target = (ResidentialBuilding) getTarget();
		if (target.getStructuralIntegrity() == 0) {
			jobsDone();
			return;
		} else if (target.getGasLevel() > 0)
			target.setGasLevel(target.getGasLevel() - 10);

		if (target.getGasLevel() == 0)
			jobsDone();

	}

}
