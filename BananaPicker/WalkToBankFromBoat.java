package BananaPicker;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.TilePath;

public class WalkToBankFromBoat extends Task<ClientContext> {
    public WalkToBankFromBoat(ClientContext ctx) {
        super(ctx);
    }

    public static final Tile[] tiles = {new Tile(3029, 3217, 0), new Tile(3027, 3221, 0), new Tile(3027, 3225, 0), new Tile(3027, 3229, 0), new Tile(3027, 3233, 0), new Tile(3028, 3237, 0), new Tile(3029, 3241, 0), new Tile(3033, 3244, 0), new Tile(3037, 3246, 0), new Tile(3039, 3250, 0), new Tile(3039, 3254, 0), new Tile(3041, 3258, 0), new Tile(3044, 3261, 0), new Tile(3048, 3263, 0), new Tile(3052, 3264, 0), new Tile(3056, 3264, 0), new Tile(3059, 3267, 0), new Tile(3061, 3271, 0), new Tile(3064, 3274, 0), new Tile(3068, 3275, 0), new Tile(3072, 3276, 0), new Tile(3075, 3273, 0), new Tile(3078, 3270, 0), new Tile(3078, 3266, 0), new Tile(3080, 3262, 0), new Tile(3080, 3258, 0), new Tile(3081, 3254, 0), new Tile(3085, 3253, 0), new Tile(3088, 3250, 0), new Tile(3092, 3248, 0), new Tile(3093, 3243, 0)};
    TilePath pathToBank = ctx.movement.newTilePath(tiles);
    int bananaID = 1963;
    int plankID = 2084;
    Tile bankTile = new Tile(3092, 3245);
    Tile customsOfficerTile= new Tile(2954, 3147);





    @Override
    public boolean activate() {
        GameObject plank = ctx.objects.select().id(plankID).poll();
        return ctx.inventory.select().id(bananaID).count() > 26
                && ctx.players.local().tile().distanceTo(bankTile) > 8
                && ctx.players.local().tile().distanceTo(customsOfficerTile) > 100;
    }

    @Override
    public void execute() {
        System.out.println("walking to bank");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 8) {
            pathToBank.traverse();
            ctx.camera.turnTo(ctx.movement.destination());
        }
    }
}
