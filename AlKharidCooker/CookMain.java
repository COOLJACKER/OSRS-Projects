package AlKharidCooker;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "Al kharid cooker", description = "rtert")

public class CookMain extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();


    @Override
    public void start() {
        taskList.add(new CookTheFood(ctx));
        taskList.add(new BankTheFood(ctx));
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
