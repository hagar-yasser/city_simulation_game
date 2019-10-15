package view;

import model.disasters.Disaster;
import model.disasters.Infection;
import model.disasters.Injury;
import model.infrastructure.ResidentialBuilding;
import model.people.Citizen;
import simulation.Rescuable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class TargetsButton extends JButton {
    ArrayList<Rescuable> target = new ArrayList<>();
    ArrayList<Disaster> disaster = new ArrayList<>();
    
    public TargetsButton(String message){
        super(message);
    }
    public TargetsButton(){
        super();
    }

    public void addTarget (Rescuable target) {
        this.target.add(target) ;
        if(target instanceof Citizen)
       this.setIcon(new ImageIcon("/home/omar/Desktop/Hagar's Desktop/Webp.net-resizeimage (1).jpg"));


        else{
            ResidentialBuilding rb=(ResidentialBuilding)target;
            if(rb.getOccupants().size()==0)
                setIcon(new ImageIcon("/home/omar/Desktop/Hagar's Desktop/Webp.net-resizeimage (2).jpg"));
            else setIcon(new ImageIcon("/home/omar/Desktop/Hagar's Desktop/Webp.net-resizeimage (3).jpg"));
        }
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