package BarbarianCookAndFish;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class CookTheSalmon extends Task<ClientContext> {
    public CookTheSalmon(ClientContext ctx) {
        super(ctx);
    }

    private Tile fireTile = new Tile(3075, 3428);
    private int fireID = 5499;
    private int rawTroutID = 335;
    private int rawSalmonID = 331;



    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(fireTile) < 8
                && ctx.inventory.select().id(rawSalmonID).count() > 0
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {

        int rawSalmonCount = ctx.inventory.select().id(rawSalmonID).count();

        System.out.println("cooking the salmon");
        GameObject fire = ctx.objects.select().id(fireID).poll();
        Item rawTrout = ctx.inventory.select().id(rawTroutID).poll();
        Item rawSalmon = ctx.inventory.select().id(rawSalmonID).poll();

        rawSalmon.interact("Use");
        fire.interact("Use");
        Condition.sleep(2000);
        ctx.input.send("1");
        Condition.sleep((rawSalmonCount * 1800));

    }
}
