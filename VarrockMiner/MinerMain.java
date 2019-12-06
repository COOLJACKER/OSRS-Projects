package VarrockMiner;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name="Varrock Miner", description = "dfngg")

public class MinerMain extends PollingScript<ClientContext> {

    private List<Task> taskList = new ArrayList<Task>();


    @Override
    public void poll() {

        if (ctx.controller.isStopping()) {
        }

        for (Task task : taskList) {
            if (ctx.controller.isStopping()) {
                break;
            }

            if (task.activate()) {
                task.execute();
                break;
            }
        }
    }

    @Override
    public void start() {
        taskList.add(new MineTinOne(ctx));
        taskList.add(new MineCopperOne(ctx));
        taskList.add(new MineCopperTwo(ctx));
        taskList.add(new DropGems(ctx));
        taskList.add(new WalkToBank(ctx));
        taskList.add(new BankOres(ctx));
        taskList.add(new WalkToMine(ctx));
    }
}
