package rcingBodies;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;

import java.util.concurrent.Callable;

public class WalkToRuins extends Task<ClientContext> {

    private int runeEssID = 1436;
    private int bodyRuneID = 559;
    final static int ruinsID = 31584;
    final static int altarID = 34765;
    final static int exitPortal = 34753;
    private Tile bankTile = new Tile(3094, 3491);
    private Tile ruinsTile = new Tile(3054, 3447);

    public static final Tile[] ruinsTiles = {new Tile(3094, 3491, 0), new Tile(3090, 3491, 0), new Tile(3086, 3488, 0), new Tile(3083, 3485, 0), new Tile(3081, 3481, 0), new Tile(3080, 3477, 0), new Tile(3080, 3473, 0), new Tile(3080, 3469, 0), new Tile(3084, 3467, 0), new Tile(3086, 3463, 0), new Tile(3082, 3462, 0), new Tile(3079, 3459, 0), new Tile(3076, 3456, 0), new Tile(3075, 3452, 0), new Tile(3072, 3449, 0), new Tile(3071, 3445, 0), new Tile(3067, 3445, 0), new Tile(3063, 3448, 0), new Tile(3059, 3450, 0), new Tile(3056, 3447, 0)};
    TilePath ruinsPath = ctx.movement.newTilePath(ruinsTiles);

    public WalkToRuins(ClientContext ctx) {
        super(ctx);
    }


    @Override
    public boolean activate() {
        GameObject altar = ctx.objects.select().id(altarID).poll();
        GameObject ruins = ctx.objects.select().id(ruinsID).poll();
        return ctx.inventory.select().id(runeEssID).count() == 28 && ctx.players.local().tile().distanceTo(ruinsTile) > 10
                && ctx.inventory.select().id(bodyRuneID).count() == 0 && !altar.inViewport();
    }

    @Override
    public void execute() {
        System.out.println("walk to ruins");

        if (!ctx.players.local().inMotion() || ctx.movement.destination().tile().distanceTo(ctx.players.local().tile()) < 10) {
            ruinsPath.traverse();
            ctx.camera.turnTo(ruinsTile);
        }

    }
}
