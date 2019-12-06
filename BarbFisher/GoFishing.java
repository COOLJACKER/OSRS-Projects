package BarbFisher;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class GoFishing extends Task<ClientContext> {

    private int rawTroutID = 335;
    private int salmonID = 334;
    private int fireID = 26185;
    private int fishingSpot = 1526;
    private int feathersID = 314;

    public GoFishing(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 28
                && ctx.players.local().animation() == -1
                && !ctx.players.local().inMotion()
                && ctx.inventory.select().id(feathersID).count() != 0;
    }

    @Override
    public void execute() {

        Npc spot = ctx.npcs.select().id(fishingSpot).nearest().poll();
        if (spot.inViewport()) {
            spot.interact("Lure");

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return (ctx.players.local().animation() == 623 );
                }
            },1000,10);

        } else {
            ctx.movement.step(spot);
            ctx.camera.turnTo(spot);
        }

    }
}
