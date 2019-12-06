package rcingBodies;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class enterRuins extends Task<ClientContext> {
    public enterRuins(ClientContext ctx) {
        super(ctx);
    }

    private int runeEssID = 1436;
    private int bodyRuneID = 559;
    final static int ruinsID = 31584;
    final static int altarID = 34765;
    final static int exitPortal = 34753;
    private Tile bankTile = new Tile(3094, 3491);
    private Tile ruinsTile = new Tile(3054, 3447);


    @Override
    public boolean activate() {
        GameObject ruins = ctx.objects.select().id(ruinsID).poll();
        return ruins.inViewport() && ctx.inventory.select().id(bodyRuneID).count() == 0;
    }

    @Override
    public void execute() {

        System.out.println("enter ruins");

        GameObject ruins = ctx.objects.select().id(ruinsID).poll();
        ruins.interact("Enter");
    }
}
