package BarbarianCookAndFish;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class GoFishing extends Task<ClientContext> {

    private int rawTroutID = 335;
    private int rawSalmonID = 331;
    private int fireID = 26185;
    private int fishingSpot = 1526;
    private Tile fireTile = new Tile(3075, 3428);
    private  Tile fishingArea = new Tile(3103, 3430);

    public GoFishing(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() < 28
                && ctx.players.local().animation() == -1
                && !ctx.players.local().inMotion()
                && ctx.players.local().tile().distanceTo(fishingArea) < 13;
    }

    @Override
    public void execute() {
        System.out.println("go fishing");
        Npc spot = ctx.npcs.select().id(fishingSpot).nearest().poll();
        if (spot.inViewport()) {
            spot.interact("Lure");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return (ctx.players.local().animation() == 623 );
                }
            },1000,5);
        } else {
            ctx.movement.step(spot);
            ctx.camera.turnTo(spot);
        }

    }
}
