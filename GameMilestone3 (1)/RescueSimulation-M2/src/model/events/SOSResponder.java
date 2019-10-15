package model.events;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import simulation.Rescuable;

public interface SOSResponder {
    public void respond(Rescuable r) throws UnitException;
}
