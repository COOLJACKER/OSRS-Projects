package BananaPicker;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import org.powerbot.script.PaintListener;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "banana", description = "dfgkjdfg")

public class BananaMain extends PollingScript<ClientContext> implements PaintListener {

    private List<Task> taskList = new ArrayList<Task>();


    @Override
    public void start() {
        taskList.add(new Pick(ctx));
        taskList.add(new WalkToBoatFromBananas(ctx));
        taskList.add(new CustomsOfficer(ctx));
        taskList.add(new CrossPlank(ctx));
        taskList.add(new WalkToBankFromBoat(ctx));
        taskList.add(new Bank(ctx));
        taskList.add(new WalkToBoatFromBank(ctx));
        taskList.add(new TakeShipToKaramja(ctx));
        taskList.add(new WalkToBananaField(ctx));
    }
    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
                break;
            }
        }
    }




    public void repaint(Graphics graphics) {

        long milliseconds = this.getTotalRuntime();
        long seconds = (this.getTotalRuntime() / 1000);
        long minutes = ((this.getTotalRuntime() / 1000) / 60);

        Graphics2D g = (Graphics2D)graphics;

        g.setColor(new Color(0, 0,0, 240));
        g.fillRect(0,0, 300, 100);

        g.setColor(new Color(255,255,255));
        g.drawRect(0,0,300,100);

        g.drawString("Banana Picker", 20,20);
        g.drawString("Runtime: " + minutes +" minutes, " + seconds + " seconds", 20, 40);
        //g.drawString("EXP gained: " + expGained, 20,60);
        //g.drawString("EXP per hour: " + expPerHour, 20,80);
    }
}


