package BananaPicker;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

public class WalkToBananaField extends Task<ClientContext> {
    public WalkToBananaField(ClientContext ctx) {
        super(ctx);
    }

    public static final Tile[] tiles = {new Tile(2956, 3146, 0), new Tile(2952, 3146, 0), new Tile(2948, 3146, 0), new Tile(2944, 3146, 0), new Tile(2940, 3146, 0), new Tile(2936, 3146, 0), new Tile(2932, 3147, 0), new Tile(2928, 3148, 0), new Tile(2924, 3150, 0), new Tile(2920, 3152, 0), new Tile(2916, 3153, 0), new Tile(2917, 3157, 0), new Tile(2917, 3161, 0)};
    TilePath pathToBananas = ctx.movement.newTilePath(tiles);
    int karamjaPlankID = 2082;
    int bananaID = 1963;
    Tile standingOnKaramjaBoat = new Tile(2956, 3143);
    int customsOfficerID = 3648;
    Tile bananaTile = new Tile(2917, 3161);


    @Override
    public boolean activate() {
        Npc customsOfficer = ctx.npcs.select().id(customsOfficerID).poll();
        GameObject plank = ctx.objects.select().id(karamjaPlankID).poll();
        return ctx.inventory.select().id(bananaID).count() < 27
                && ctx.players.local().tile().distanceTo(bananaTile) > 10;
    }

    @Override
    public void execute() {
        System.out.println("walking to the bananas");
        if (!ctx.players.local().inMotion() || ctx.movement.destination().distanceTo(ctx.players.local().tile()) < 8) {
            ctx.camera.turnTo(bananaTile);
            pathToBananas.traverse();
        }
    }
}
