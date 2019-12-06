package rcingAirs;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Air Runecrafter", description="blaaa")

public class AirRunecafter extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();


    @Override
    public void start() {
        //taskList.add(new Run(ctx));
        taskList.add(new WalkToBank(ctx));
        taskList.add(new CraftAirs(ctx));
        taskList.add(new WalkToRuins(ctx));

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
