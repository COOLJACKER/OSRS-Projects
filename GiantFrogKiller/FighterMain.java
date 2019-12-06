package GiantFrogKiller;


import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Giant Frog Killer", description = "Kills them and buries their bones")

public class FighterMain extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();

    int startingExp = 0;


    @Override
    public void start() {
        taskList.add(new BuryBones(ctx));
        taskList.add(new Attack(ctx));
        taskList.add(new Heal(ctx));

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
