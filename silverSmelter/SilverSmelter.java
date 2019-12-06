package silverSmelter;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Script.Manifest(name="Silver Smelter", description="cuts normal logs and drops them")

public class SilverSmelter extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new bankBars(ctx));
        taskList.add(new Smelt(ctx));

    }

    @Override
    public void poll() {
        for (Task task : taskList) {
            if (task.activate()) {
                task.execute();
            }
        }
    }


}
