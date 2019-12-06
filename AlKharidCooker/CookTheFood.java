package AlKharidCooker;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class CookTheFood extends Task<ClientContext> {
    public CookTheFood(ClientContext ctx) {
        super(ctx);
    }

    int rangeID =26181;
    int rawTroutID = 335;
    public static final Tile[] tilesToRange = {new Tile(3269, 3167, 0), new Tile(3273, 3167, 0), new Tile(3275, 3171, 0), new Tile(3275, 3175, 0), new Tile(3277, 3179, 0), new Tile(3273, 3180, 0), new Tile(3272, 3180, 0)};
    TilePath path = ctx.movement.newTilePath(tilesToRange);

    @Override
    public boolean activate() {
        GameObject range = ctx.objects.select().id(rangeID).poll();
        return ctx.inventory.select().count() == 28
                && !ctx.players.local().inMotion()
                && ctx.inventory.select().id(rawTroutID).count() > 0;
    }

    @Override
    public void execute() {
        int foodCounter = ctx.inventory.select().id(rawTroutID).count();
        GameObject range = ctx.objects.select().id(rangeID).poll();
        Component troutBox = ctx.widgets.component(270, 5);
        if (ctx.players.local().tile().distanceTo(range) < 4) {
            range.interact("Cook", "Range");
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return troutBox.visible();
                }
            },100, 30);

            ctx.input.send("1");
            Condition.sleep(2000 * foodCounter);

        } else {
            if (ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 5 || !ctx.players.local().inMotion()) {
                path.traverse();
                ctx.camera.turnTo(range);
            }
        }
    }
}
