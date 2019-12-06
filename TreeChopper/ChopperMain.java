package TreeChopper;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Script.Manifest(name="Chop Normal Trees", description="cuts normal logs and drops them")

public class ChopperMain extends PollingScript<ClientContext> implements PaintListener {

    private List<Task> taskList = new ArrayList<Task>();

    long startingExp = 0;

    @Override
    public void start() {
        taskList.add(new Chop(ctx));
        taskList.add(new WalkToBank(ctx));
        taskList.add(new Bank(ctx));
        taskList.add(new WalkToTree(ctx));

        startingExp = ctx.skills.experience(Constants.SKILLS_WOODCUTTING);

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
        long expGained = ctx.skills.experience(Constants.SKILLS_WOODCUTTING) - startingExp;

        long milliseconds = this.getTotalRuntime();
        long seconds = (this.getTotalRuntime() / 1000);
        long minutes = ((this.getTotalRuntime() / 1000) / 60);
        long expPerHour = ((expGained / seconds) * 3600);
        long logsCut = expGained / 175;


        Graphics2D g = (Graphics2D)graphics;

        g.setColor(new Color(0, 0,0, 240));
        g.fillRect(0,0, 300, 300);

        g.setColor(new Color(255,255,255));
        g.drawRect(0,0,300,300);

        g.drawString("Woodcutter", 20,20);
        g.drawString("Runtime: " + minutes +" minutes, " + seconds + " seconds", 20, 40);
        g.drawString("EXP gained: " + expGained, 20,60);
        g.drawString("EXP per hour: " + expPerHour, 20,80);
        g.drawString("Logs cut: " + logsCut, 20,100);

    }
}



