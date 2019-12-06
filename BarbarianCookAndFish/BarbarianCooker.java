package BarbarianCookAndFish;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;


@Script.Manifest(name= "BarbarianCookAndFish", description="cuts normal logs and drops them")

public class BarbarianCooker extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new GoFishing(ctx));
        taskList.add(new WalkToFire(ctx));
        taskList.add(new CookTheTrout(ctx));
        taskList.add(new CookTheSalmon(ctx));
        taskList.add(new DropFish(ctx));
        taskList.add(new WalkToFishingArea(ctx));

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


}
