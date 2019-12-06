package VarrockMiner;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

import java.util.concurrent.Callable;

public class BankOres extends Task<ClientContext> {
    public BankOres(ClientContext ctx) {
        super(ctx);
    }

    private Tile bankTile = new Tile(3253, 3420);
    private Tile mineTile = new Tile(3285, 3366);


    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(bankTile) <= 8
                && ctx.inventory.select().count() > 0;
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
        } else {
            ctx.camera.turnTo(bankTile);
            ctx.bank.open();
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.players.local().inMotion();
                }
            }, 100, 20);
        }


/*        System.out.println("bank the ores");
        ctx.bank.open();
        ctx.bank.depositInventory();
        ctx.bank.close();*/
    }
}
