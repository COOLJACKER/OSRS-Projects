package BarbarianCookAndFish;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class CookTheTrout extends Task<ClientContext> {
    public CookTheTrout(ClientContext ctx) {
        super(ctx);
    }

    private Tile fireTile = new Tile(3075, 3428);
    private int fireID = 5499;
    private int rawTroutID = 335;
    private int rawSalmonID = 331;
    private int cookedTroutID = 333;

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(fireTile) < 8
                && ctx.inventory.select().id(rawTroutID).count() > 0
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        System.out.println("cooking the trout");

        int rawTroutCount = ctx.inventory.select().id(rawTroutID).count();

        GameObject fire = ctx.objects.select().id(fireID).poll();
        Item rawTrout = ctx.inventory.select().id(rawTroutID).poll();
        Item rawSalmon = ctx.inventory.select().id(rawSalmonID).poll();

        rawTrout.interact("Use");
        fire.interact("Use");
        Condition.sleep(2000);
        ctx.input.send("1");
        Condition.sleep((rawTroutCount * 1800));



/*        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return (ctx.inventory.select().id(cookedTroutID).count() == rawTroutCount );
            }
        },250,60);*/

    }
}
