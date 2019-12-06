package rcingBodies;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Body Runecrafter", description="blaaa")

public class BodyRunecrafter extends PollingScript<ClientContext> implements PaintListener {

    private List<Task> taskList = new ArrayList<Task>();

    int startingExp = 0;


    @Override
    public void start() {
        //taskList.add(new Run(ctx));
        //taskList.add(new Run(ctx));
        taskList.add(new ExitThruPortal(ctx));
        taskList.add(new WalkToBank(ctx));
        taskList.add(new CraftBodies(ctx));
        taskList.add(new WalkToRuins(ctx));
        taskList.add(new enterRuins(ctx));

        startingExp = ctx.skills.experience(Constants.SKILLS_RUNECRAFTING);


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

    @Override
    public void repaint(Graphics graphics) {
        int expGained = ctx.skills.experience(Constants.SKILLS_RUNECRAFTING) - startingExp;

        long milliseconds = this.getTotalRuntime();
        //long seconds = (milliseconds / 1000);
        //long minutes = (seconds / 60);
        //long hours = minutes / 60;
        //double expPerHour = (expGained / (milliseconds * 360000));


        Graphics2D g = (Graphics2D)graphics;

        g.setColor(new Color(0, 0,0, 132));
        g.fillRect(0,0, 750, 100);

        g.setColor(new Color(255,255,255));
        g.drawRect(0,0,750,100);

        g.drawString("Body Runecrafter", 20,20);
        //g.drawString("Runtime: " + minutes +" minutes, " + seconds + " seconds", 20, 40);
        g.drawString("EXP gained: " + expGained, 20,60);
        //g.drawString("EXP per hour: " + expPerHour, 20,80);

    }
}
