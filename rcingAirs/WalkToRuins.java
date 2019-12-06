package rcingAirs;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class WalkToRuins extends Task<ClientContext> {

    private int airRuneID = 556;
    public int runeEssID = 1436;
    final static int ruinsID = 29090;
    final static int altarID = 34760;
    private Tile ruinsTile = new Tile(2985, 3294);


    public WalkToRuins(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject altar = ctx.objects.select().id(altarID).nearest().poll();


        return ctx.inventory.select().id(runeEssID).count() == 28
                && !altar.inViewport();
    }

    @Override
    public void execute() {
        GameObject ruins = ctx.objects.select().id(ruinsID).nearest().poll();

        ctx.movement.running();

        if (ruins.inViewport()) {
            ruins.interact("Enter");

        } else {
            ctx.movement.step(ruinsTile);
            ctx.camera.turnTo(ruinsTile);
        }



    }
}
