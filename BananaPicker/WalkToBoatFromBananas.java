package BananaPicker;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.TilePath;

public class WalkToBoatFromBananas extends Task<ClientContext> {
    public WalkToBoatFromBananas(ClientContext ctx) {
        super(ctx);
    }

    public static final Tile[] tiles = {new Tile(2918, 3160, 0), new Tile(2917, 3156, 0), new Tile(2920, 3153, 0), new Tile(2924, 3153, 0), new Tile(2928, 3152, 0), new Tile(2932, 3151, 0), new Tile(2936, 3149, 0), new Tile(2939, 3146, 0), new Tile(2943, 3146, 0), new Tile(2947, 3146, 0), new Tile(2951, 3146, 0)};
    TilePath pathToBoat = ctx.movement.newTilePath(tiles);
    int bananaID = 1963;
    Tile bananaTile = new Tile(2917, 3161);

    Tile customsOfficerTile = new Tile(2954, 3147);


    @Override
    public boolean activate() {
        return ctx.inventory.select().id(bananaID).count() > 26
                && ctx.players.local().tile().distanceTo(customsOfficerTile) > 10
                && ctx.players.local().tile().distanceTo(customsOfficerTile) < 100;
    }

    @Override
    public void execute() {
        System.out.println("walking to the boat from banana field");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 8) {
            ctx.camera.turnTo(ctx.movement.destination());
            pathToBoat.traverse();
        }
    }
}
