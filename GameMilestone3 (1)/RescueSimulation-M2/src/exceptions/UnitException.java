package exceptions;

import model.units.Unit;
import simulation.Rescuable;

public abstract class UnitException extends SimulationException {
    Unit unit;
    Rescuable target;
    public UnitException(Unit u,Rescuable t){
        super();
        target=t;
        unit=u;
    }
    public UnitException(Unit u,Rescuable t,String message){
        super(message);
        target=t;
        unit=u;
    }

    public Unit getUnit() {
        return unit;
    }

    public Rescuable getTarget() {
        return target;
    }
}
