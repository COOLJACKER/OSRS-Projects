package AlKharidCooker;

import org.powerbot.script.Locatable;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.Bank;

import java.util.concurrent.Callable;

public class BankTheFood extends Task<ClientContext> {

    int cookedTroutID = 333;
    int burntFoodID = 343;
    int rawTroutID = 335;


    public BankTheFood(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(cookedTroutID).count() + ctx.inventory.select().id(burntFoodID).count() == 28
                || ctx.inventory.select().count() == 0;
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

            ctx.bank.withdraw(rawTroutID, 28);
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(rawTroutID).count() == 28;
                }
            }, 100, 20);

            ctx.bank.close();

        } else {
            ctx.movement.step(ctx.bank.nearest());
            ctx.camera.turnTo(ctx.bank.nearest());
            ctx.bank.open();
        }


    }
}
