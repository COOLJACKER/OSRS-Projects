package rcingAirs;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class WalkToBank extends Task<ClientContext> {

    private int airRuneID = 556;
    private int runeEssID = 1436;
    final static int ruinsID = 29090;
    final static int altarID = 34760;
    final static int exitPortal = 34748;
    final static int bankBoothID = 24101;
    final static int fallyBankerID = 1036;
    private Tile bankTile = new Tile(3012, 3355);


    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(airRuneID).count() > 0
                || ctx.inventory.select().id(runeEssID).count() == 0;
    }

    @Override
    public void execute() {

        GameObject portal = ctx.objects.select().id(exitPortal).poll();

        portal.interact("Use");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() == -1;
            }
        }, 2000, 3);

        if (ctx.bank.opened()) {
            ctx.bank.depositInventory();
            ctx.bank.withdraw(runeEssID, 28);
            ctx.bank.close();
        } else {


            ctx.movement.step(bankTile);
            ctx.camera.turnTo(bankTile);
            if ((ctx.players.local().tile().distanceTo(bankTile)) < 4) {
                ctx.bank.open();
            }


        }
    }
}