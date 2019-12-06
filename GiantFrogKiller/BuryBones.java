package GiantFrogKiller;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GroundItem;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class BuryBones extends Task<ClientContext> {
    public BuryBones(ClientContext arg0) {
        super(arg0);
    }

    int bigBonesID = 532;

    @Override
    public boolean activate() {
        GroundItem bones = ctx.groundItems.select().id(bigBonesID).nearest().poll();

        return ctx.inventory.select().count() < 28
                && ctx.players.local().animation() == -1
                && ctx.players.local().tile().distanceTo(bones) < 6;
    }

    @Override
    public void execute() {
        GroundItem bones = ctx.groundItems.select().id(bigBonesID).nearest().poll();
        bones.interact("Take", "Big bones");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().id(bigBonesID).count() > 0;
            }
        });

        while (ctx.inventory.select().id(bigBonesID).count() > 0) {
            Item bonesToBury = ctx.inventory.select().id(bigBonesID).poll();
            bonesToBury.interact("Bury");
        }

    }
}
