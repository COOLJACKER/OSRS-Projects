package ChocCrusher;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="choc bar crusher", description="blll")

public class ChocCrusher extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();

    @Override
    public void start() {
        taskList.add(new CutBars(ctx));
        taskList.add(new Bank(ctx));
        //taskList.add(new Withdraw(ctx));
    }

    @Override
    public void poll() {
        for(Task task : taskList) {
            if (task.activate()) {
                task.execute();
                break;
            }
        }
    }
}
