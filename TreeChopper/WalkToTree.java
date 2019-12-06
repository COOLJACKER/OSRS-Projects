package TreeChopper;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToTree extends Task<ClientContext> {
    public WalkToTree(ClientContext ctx) {
        super(ctx);
    }

    public static final Tile[] treeTiles = {new Tile(3253, 3420, 0), new Tile(3253, 3424, 0), new Tile(3256, 3427, 0), new Tile(3260, 3428, 0), new Tile(3264, 3428, 0), new Tile(3268, 3428, 0), new Tile(3272, 3428, 0), new Tile(3276, 3425, 0), new Tile(3277, 3421, 0), new Tile(3280, 3418, 0), new Tile(3283, 3414, 0), new Tile(3286, 3410, 0), new Tile(3288, 3406, 0), new Tile(3290, 3402, 0), new Tile(3290, 3398, 0), new Tile(3290, 3394, 0), new Tile(3289, 3390, 0), new Tile(3289, 3386, 0), new Tile(3290, 3382, 0), new Tile(3290, 3378, 0), new Tile(3287, 3375, 0), new Tile(3283, 3375, 0), new Tile(3279, 3374, 0), new Tile(3275, 3373, 0), new Tile(3272, 3370, 0), new Tile(3268, 3369, 0), new Tile(3264, 3370, 0), new Tile(3260, 3370, 0), new Tile(3256, 3368, 0)};
    TilePath pathToTree = ctx.movement.newTilePath(treeTiles);
    int yewLogID = 1515;


    @Override
    public boolean activate() {
        return ctx.inventory.select().id(yewLogID).count() == 0;
    }

    @Override
    public void execute() {
        if (!ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 8) {
            pathToTree.traverse();
            ctx.camera.turnTo(ctx.movement.destination());
        }
    }
}
