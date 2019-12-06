package BananaPicker;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class Pick extends Task<ClientContext> {
    public Pick(ClientContext ctx) {
        super(ctx);
    }

    int[] bananaTreeID = {2073, 2074, 2075, 2076, 2077};
    int bananaID = 1963;
    Tile bananaTile = new Tile(2917, 3161);

    int howManyBananasPicked = 0;

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(bananaID).count() < 27
                && ctx.players.local().tile().distanceTo(bananaTile) < 25
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        int bananaCount = ctx.inventory.select().id(bananaID).count();

        System.out.println("picking bananas");
        GameObject tree = ctx.objects.select().id(bananaTreeID).nearest().poll();

        howManyBananasPicked++;


        if (tree.inViewport()) {
            tree.interact("Pick");

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return ctx.inventory.select().id(bananaID).count() != bananaCount;
                }
            }, 50, 10);
        } else {
            ctx.camera.turnTo(ctx.objects.select().id(bananaTreeID).nearest().poll());
        }

    }


}
