package TreeChopper;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToBank extends Task<ClientContext> {
    public WalkToBank(ClientContext ctx) {
        super(ctx);
    }

    public static final Tile[] bankTiles= {new Tile(3255, 3367, 0), new Tile(3259, 3368, 0), new Tile(3263, 3370, 0), new Tile(3267, 3371, 0), new Tile(3271, 3373, 0), new Tile(3275, 3374, 0), new Tile(3279, 3373, 0), new Tile(3283, 3373, 0), new Tile(3287, 3374, 0), new Tile(3290, 3377, 0), new Tile(3290, 3381, 0), new Tile(3290, 3385, 0), new Tile(3291, 3389, 0), new Tile(3291, 3393, 0), new Tile(3291, 3397, 0), new Tile(3291, 3401, 0), new Tile(3291, 3405, 0), new Tile(3291, 3409, 0), new Tile(3291, 3413, 0), new Tile(3291, 3417, 0), new Tile(3287, 3419, 0), new Tile(3284, 3422, 0), new Tile(3280, 3422, 0), new Tile(3277, 3425, 0), new Tile(3273, 3426, 0), new Tile(3269, 3426, 0), new Tile(3265, 3427, 0), new Tile(3261, 3428, 0), new Tile(3257, 3428, 0), new Tile(3254, 3425, 0), new Tile(3254, 3421, 0)};
    int yewLogID = 1515;
    TilePath pathToBank = ctx.movement.newTilePath(bankTiles);

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(yewLogID).count() > 26
                && ctx.players.local().tile().distanceTo(ctx.bank.nearest()) > 7;
    }

    @Override
    public void execute() {
        if (!ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 8) {
            pathToBank.traverse();
            ctx.camera.turnTo(ctx.movement.destination());
        }
    }
}
