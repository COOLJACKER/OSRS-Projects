package rcingBodies;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;

import java.util.concurrent.Callable;

public class WalkToBank extends Task<ClientContext> {

    private int runeEssID = 1436;
    private int bodyRuneID = 559;
    final static int ruinsID = 31584;
    final static int altarID = 34765;
    final static int exitPortal = 34753;
    private Tile bankTile = new Tile(3094, 3491);
    private Tile ruinsTile = new Tile(3054, 3447);

    public static final Tile[] bankTiles = {new Tile(3054, 3447, 0), new Tile(3057, 3450, 0), new Tile(3061, 3450, 0), new Tile(3065, 3449, 0), new Tile(3068, 3446, 0), new Tile(3072, 3449, 0), new Tile(3076, 3451, 0), new Tile(3079, 3454, 0), new Tile(3082, 3457, 0), new Tile(3085, 3461, 0), new Tile(3082, 3465, 0), new Tile(3080, 3469, 0), new Tile(3080, 3473, 0), new Tile(3080, 3477, 0), new Tile(3080, 3481, 0), new Tile(3084, 3484, 0), new Tile(3087, 3487, 0), new Tile(3090, 3490, 0), new Tile(3094, 3492, 0)};

    TilePath bankPath = ctx.movement.newTilePath(bankTiles);


    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }



    @Override
    public boolean activate() {
        GameObject portal = ctx.objects.select().id(exitPortal).poll();
        GameObject altar = ctx.objects.select().id(altarID).poll();
        return !altar.inViewport() && ctx.inventory.select().id(runeEssID).count() == 0;
    }

    @Override
    public void execute() {

        System.out.println("Walk to bank");

        if (ctx.bank.opened()) {
            ctx.bank.depositInventory();
            ctx.bank.withdraw(runeEssID, 28);
            ctx.bank.close();
        } else if ((ctx.players.local().tile().distanceTo(bankTile)) < 8) {
            ctx.bank.open();
        } else {
            if (!ctx.players.local().inMotion() || ctx.movement.destination().tile().distanceTo(ctx.players.local().tile()) < 10) {
                ctx.camera.turnTo(bankTile);
                bankPath.traverse();
            }

        }

    }
}
