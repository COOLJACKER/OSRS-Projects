package rcingBodies;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class ExitThruPortal extends Task<ClientContext> {

    private int runeEssID = 1436;
    private int bodyRuneID = 559;
    final static int ruinsID = 31584;
    final static int altarID = 34765;
    final static int exitPortal = 34753;
    private Tile bankTile = new Tile(3094, 3491);
    private Tile ruinsTile = new Tile(3054, 3447);

    public ExitThruPortal(ClientContext ctx) {
        super(ctx);
    }



    @Override
    public boolean activate() {
        GameObject portal = ctx.objects.select().id(exitPortal).poll();
        return portal.inViewport() && ctx.inventory.select().id(bodyRuneID).count() > 0;
    }

    @Override
    public void execute() {
        GameObject portal = ctx.objects.select().id(exitPortal).poll();

        System.out.println("exit through the portal");
        if (portal.inViewport()) {
            portal.interact("Use");

            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return !ctx.players.local().inMotion();
                }
            }, 1000, 10);

        } else {
            ctx.movement.step(portal);
        }
    }
}
