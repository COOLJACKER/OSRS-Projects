package VarrockMiner;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToMine extends Task<ClientContext> {
    public WalkToMine(ClientContext ctx) {
        super(ctx);
    }

    private Tile mineTile = new Tile(3285, 3366);
    public static final Tile[] minePathTiles = {new Tile(3253, 3420, 0), new Tile(3253, 3424, 0), new Tile(3256, 3427, 0), new Tile(3260, 3428, 0), new Tile(3264, 3428, 0), new Tile(3268, 3428, 0), new Tile(3272, 3428, 0), new Tile(3276, 3425, 0), new Tile(3277, 3421, 0), new Tile(3280, 3418, 0), new Tile(3283, 3414, 0), new Tile(3286, 3411, 0), new Tile(3288, 3407, 0), new Tile(3290, 3403, 0), new Tile(3290, 3399, 0), new Tile(3290, 3395, 0), new Tile(3291, 3391, 0), new Tile(3291, 3387, 0), new Tile(3291, 3383, 0), new Tile(3290, 3379, 0), new Tile(3290, 3375, 0), new Tile(3287, 3371, 0), new Tile(3287, 3367, 0)};
    public static final Tile[] minePathTiles2 = {new Tile(3251, 3420, 0), new Tile(3253, 3424, 0), new Tile(3256, 3427, 0), new Tile(3260, 3428, 0), new Tile(3264, 3428, 0), new Tile(3268, 3428, 0), new Tile(3272, 3428, 0), new Tile(3276, 3428, 0), new Tile(3279, 3424, 0), new Tile(3283, 3423, 0), new Tile(3285, 3419, 0), new Tile(3285, 3415, 0), new Tile(3285, 3411, 0), new Tile(3288, 3408, 0), new Tile(3289, 3404, 0), new Tile(3290, 3400, 0), new Tile(3290, 3396, 0), new Tile(3290, 3392, 0), new Tile(3290, 3388, 0), new Tile(3290, 3384, 0), new Tile(3293, 3381, 0), new Tile(3293, 3377, 0), new Tile(3290, 3374, 0), new Tile(3287, 3371, 0), new Tile(3287, 3367, 0), new Tile(3284, 3364, 0)};
    public static final Tile[] minePathTiles3 = {new Tile(3254, 3420, 0), new Tile(3254, 3424, 0), new Tile(3257, 3428, 0), new Tile(3261, 3428, 0), new Tile(3265, 3428, 0), new Tile(3269, 3428, 0), new Tile(3273, 3428, 0), new Tile(3277, 3428, 0), new Tile(3281, 3428, 0), new Tile(3284, 3425, 0), new Tile(3284, 3421, 0), new Tile(3287, 3418, 0), new Tile(3290, 3414, 0), new Tile(3290, 3410, 0), new Tile(3290, 3406, 0), new Tile(3290, 3402, 0), new Tile(3290, 3398, 0), new Tile(3290, 3394, 0), new Tile(3290, 3390, 0), new Tile(3290, 3386, 0), new Tile(3290, 3382, 0), new Tile(3290, 3378, 0), new Tile(3288, 3374, 0), new Tile(3287, 3370, 0), new Tile(3286, 3366, 0)};


    TilePath mineTilePath = ctx.movement.newTilePath(minePathTiles);


    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 0
                && ctx.players.local().tile().distanceTo(mineTile) > 10;
    }

    @Override
    public void execute() {
        if (!ctx.players.local().inMotion() || ctx.movement.destination().tile().distanceTo(ctx.players.local().tile()) < 10) {
            System.out.println("walk to mine");
            mineTilePath.traverse();
            ctx.camera.turnTo(mineTile);
        }
    }
}
