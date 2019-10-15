package model.units;

import exceptions.CannotTreatException;
import exceptions.IncompatibleTargetException;
import exceptions.UnitException;
import model.disasters.Collapse;
import model.disasters.Disaster;
import model.disasters.Fire;
import model.disasters.GasLeak;
import model.events.SOSResponder;
import model.events.WorldListener;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Address;
import simulation.Rescuable;
import simulation.Simulatable;

import java.util.Vector;

public abstract class Unit implements Simulatable, SOSResponder {
    private String unitID;
    private UnitState state;
    private Address location;
    private Rescuable target;


    private int distanceToTarget;
    private int stepsPerCycle;
    private WorldListener worldListener;

    public Unit(String unitID, Address location, int stepsPerCycle,
                WorldListener worldListener) {
        this.unitID = unitID;
        this.location = location;
        this.stepsPerCycle = stepsPerCycle;
        this.state = UnitState.IDLE;
        this.worldListener = worldListener;
    }

    public void setWorldListener(WorldListener listener) {
        this.worldListener = listener;
    }

    public WorldListener getWorldListener() {
        return worldListener;
    }

    public UnitState getState() {
        return state;
    }

    public void setState(UnitState state) {
        this.state = state;
    }

    public Address getLocation() {
        return location;
    }

    public void setLocation(Address location) {
        this.location = location;
    }

    public String getUnitID() {
        return unitID;
    }

    public Rescuable getTarget() {
        return target;
    }


    public int getStepsPerCycle() {
        return stepsPerCycle;
    }

    public void setDistanceToTarget(int distanceToTarget) {
        this.distanceToTarget = distanceToTarget;
    }

    @Override
    public void respond(Rescuable r) throws UnitException {

        if (target != null && state == UnitState.TREATING)
            reactivateDisaster();
        finishRespond(r);

    }

    public void reactivateDisaster() {
        Disaster curr = target.getDisaster();
        curr.setActive(true);
    }

    public void finishRespond(Rescuable r) {
        target = r;
        state = UnitState.RESPONDING;
        Address t = r.getLocation();
        distanceToTarget = Math.abs(t.getX() - location.getX())
                + Math.abs(t.getY() - location.getY());

    }

    public abstract void treat();

    public void cycleStep() {
        if (state == UnitState.IDLE)
            return;
        if (distanceToTarget > 0) {
            distanceToTarget = distanceToTarget - stepsPerCycle;
            if (distanceToTarget <= 0) {
                distanceToTarget = 0;
                Address t = target.getLocation();
                worldListener.assignAddress(this, t.getX(), t.getY());
            }
        } else {
            state = UnitState.TREATING;
            treat();
        }
    }

    public void jobsDone() {
        target = null;
        state = UnitState.IDLE;

    }

    public boolean canTreat(Rescuable r) {
        if (r instanceof ResidentialBuilding) {
            ResidentialBuilding rb = (ResidentialBuilding) r;

            if (rb.getStructuralIntegrity() > 0) {
                if (this instanceof Evacuator && (rb.getDisaster() instanceof Collapse))
                    return true;
                if (this instanceof FireTruck && rb.getDisaster() instanceof Fire)
                    return true;
                if (this instanceof GasControlUnit && rb.getDisaster() instanceof GasLeak)
                    return true;
            }
        } else {
            Citizen c = (Citizen) r;
            if (this instanceof Ambulance && c.getBloodLoss() > 0)
                return true;
            else if (this instanceof DiseaseControlUnit && c.getToxicity() > 0)
                return true;

        }
        return false;
    }

    public Vector<String> toStringU() {
        Vector<String> r = new Vector<>();

        if (this instanceof Ambulance)
            r.add("Ambulance");

        if (this instanceof Evacuator) {
            r.add("Unit's type :- Evacuator");
            r.add("No. of passengers :-"+ ((Evacuator) this).getPassengers().size());
            for (Citizen c:((Evacuator) this).getPassengers() ){


                    r.add("Name :-" + c.getName());
                    r.add("ID number:-" + c.getNationalID());
                    r.add("HP:-" + c.getHp());
                    r.add("Age:-" + c.getAge());
                    r.add("toxicityLevel:-" + c.getToxicity());
                    r.add("BloodLossLevel:-" + c.getBloodLoss());
                    r.add("Citizen's State" + c.getState());
                    r.add("Location:- (" + c.getLocation().getX() + "," + c.getLocation().getY() + ")");
                    r.add("DisasterOnTarget:-" + (c.getDisaster()));
                }

        }
        if (this instanceof GasControlUnit)
            r.add("Unit's type :- GasControlUnit");
        if (this instanceof DiseaseControlUnit)
            r.add("Unit's type :- DiseaseControlUnit");
        if (this instanceof FireTruck)
            r.add("Unit's type :- FireTruck");
        r.add("Unit's ID :- " + unitID);
        r.add("Unit's state :- " + getState());
        r.add("Unit's Location :- ( " + location.getX() + "," + location.getY() + " )");
        if(target!=null) {
            r.add("Unit's Target :- ");
            if (target instanceof Citizen) {
                r.add("Citizen");

            } else
                r.add("Residential Building");

            r.add("(" + target.getLocation().getX() + "," + target.getLocation().getY() + " )");
            r.add("Distance to target :- " + distanceToTarget);
        }
        r.add("Steps per Cycle :- "+ stepsPerCycle);
        r.add("------------------------------");

        return  r;



    }


}
