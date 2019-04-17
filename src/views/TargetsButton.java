package views;

import model.disasters.Disaster;
import model.disasters.Infection;
import model.disasters.Injury;
import simulation.Rescuable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TargetsButton extends JButton {
    ArrayList<Rescuable> target=new ArrayList<>();
    ArrayList<Disaster> disaster=new ArrayList<>();
    public TargetsButton(String message){
        super(message);
    }
    public TargetsButton(){
        super();
    }

    public void addTarget (Rescuable target) {
        this.target.add(target) ;
    }

    public void addDisaster(Disaster disaster) {
        this.disaster.add(disaster);
        this.setBackground(Color.RED);


    }

    public ArrayList<Rescuable> getTarget() {
        return target;
    }

    public Rescuable getTargetItem(int i) {

        return target.get(i);
    }

    public ArrayList<Disaster> getDisaster() {
        return disaster;
    }

}
