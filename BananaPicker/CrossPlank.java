package BananaPicker;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class CrossPlank extends Task<ClientContext> {
    public CrossPlank(ClientContext ctx) {
        super(ctx);
    }

    Tile plankTilePortSarim = new Tile(3032, 3217, 1);
    Tile plankTileKaramja = new Tile(2956, 3143, 1);

    int[] plankID = {2084, 2082};


    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(plankTilePortSarim) == 0
                || ctx.players.local().tile().distanceTo(plankTileKaramja) == 0;
    }

    @Override
    public void execute() {
        GameObject plank = ctx.objects.select().id(plankID).poll();
        plank.interact("Cross");
    }
}
