package BananaPicker;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;
import org.powerbot.script.rt4.TilePath;

public class WalkToBoatFromBank extends Task<ClientContext> {
    public WalkToBoatFromBank(ClientContext ctx) {
        super(ctx);
    }

    public static final Tile[] tiles = {new Tile(3092, 3245, 0), new Tile(3088, 3247, 0), new Tile(3084, 3247, 0), new Tile(3080, 3249, 0), new Tile(3078, 3253, 0), new Tile(3077, 3257, 0), new Tile(3074, 3260, 0), new Tile(3074, 3264, 0), new Tile(3074, 3268, 0), new Tile(3074, 3272, 0), new Tile(3071, 3276, 0), new Tile(3067, 3276, 0), new Tile(3064, 3273, 0), new Tile(3061, 3270, 0), new Tile(3059, 3266, 0), new Tile(3055, 3265, 0), new Tile(3051, 3265, 0), new Tile(3047, 3264, 0), new Tile(3044, 3260, 0), new Tile(3043, 3256, 0), new Tile(3043, 3252, 0), new Tile(3043, 3248, 0), new Tile(3042, 3244, 0), new Tile(3042, 3240, 0), new Tile(3039, 3237, 0), new Tile(3035, 3237, 0), new Tile(3031, 3237, 0), new Tile(3028, 3234, 0), new Tile(3028, 3230, 0), new Tile(3028, 3226, 0), new Tile(3028, 3222, 0), new Tile(3027, 3218, 0)};
    TilePath pathToBoat = ctx.movement.newTilePath(tiles);
    int bananaID = 1963;
    Tile bankTile = new Tile(3092, 3245);
    int plankID = 2084;
    int[] seamanID = {3645, 3644, 3646};
    int customsOfficerID = 3648;
    Tile bananaTile = new Tile(2917, 3161);







    @Override
    public boolean activate() {
        Npc seaman = ctx.npcs.select().id(seamanID).poll();
        Npc customsOfficer = ctx.npcs.select().id(customsOfficerID).poll();


        return ctx.inventory.select().id(bananaID).count() == 0
                && ctx.players.local().tile().distanceTo(seaman) > 6
                && ctx.players.local().tile().distanceTo(customsOfficer) > 20
                && ctx.players.local().tile().distanceTo(bananaTile) > 50;
    }

    @Override
    public void execute() {
        System.out.println("walking to boat from bank");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 8) {
            pathToBoat.traverse();
            ctx.camera.turnTo(ctx.movement.destination());
        }
    }
}
