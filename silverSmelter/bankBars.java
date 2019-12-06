package silverSmelter;

import org.powerbot.script.Locatable;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;

import java.util.concurrent.Callable;

public class bankBars extends Task<ClientContext> {

    final static int silverOreID = 442;
    final static int silverBarID = 2355;
    final static int furnaceID = 24009;
    final static int bankBooth = 10355;

    public bankBars(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(silverBarID).count() == 28;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.depositInventory();

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().count() == 0;
                }
            }, 100, 20);

            ctx.bank.withdraw(silverOreID, 28);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(silverOreID).count() == 28;
                }
            }, 100, 20);

        } else {
            ctx.movement.step(ctx.bank.nearest());
            ctx.camera.turnTo(ctx.bank.nearest());
            ctx.bank.open();
        }


    }
}
