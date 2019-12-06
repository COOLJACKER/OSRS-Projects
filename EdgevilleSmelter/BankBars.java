package EdgevilleSmelter;

import org.powerbot.script.Locatable;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;

import java.util.concurrent.Callable;

public class BankBars extends Task<ClientContext> {

    int tinOreID = 438;
    int copperOreID = 436;
    int bronzeBarID = 2349;
    int furnaceID = 16469;

    public BankBars(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(bronzeBarID).count() == 14
                || ctx.inventory.select().id(tinOreID).count() == 0
                || ctx.inventory.select().id(copperOreID).count() == 0;
    }

    @Override
    public void execute() {
        System.out.println("banking");

        if (ctx.bank.inViewport()) {
            ctx.bank.open();
            ctx.bank.depositInventory();
            ctx.bank.withdraw(tinOreID, 14);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(tinOreID).count() == 14;
                }
            }, 250, 10);
            ctx.bank.withdraw(copperOreID, 14);
        } else {
            ctx.movement.step(ctx.bank.nearest());
            ctx.camera.turnTo(ctx.bank.nearest());
        }
    }
}
