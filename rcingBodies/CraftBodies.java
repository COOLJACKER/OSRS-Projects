package rcingBodies;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class CraftBodies extends Task<ClientContext> {

    private int runeEssID = 1436;
    private int bodyRuneID = 559;
    final static int ruinsID = 31584;
    final static int altarID = 34765;
    final static int exitPortal = 34753;
    private Tile bankTile = new Tile(3094, 3491);
    private Tile ruinsTile = new Tile(3054, 3447);


    public CraftBodies(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject altar = ctx.objects.select().id(altarID).poll();

        return ctx.objects.select().id(altarID).poll().inViewport()
                && ctx.inventory.select().id(runeEssID).count() > 1;
    }

    @Override
    public void execute() {
        System.out.println("click on altar and craft body runes");
        GameObject altar = ctx.objects.select().id(altarID).poll();

        altar.interact("Craft");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().id(bodyRuneID).count() > 27
                        && ctx.players.local().animation() == -1;
            }
        },500,8);


    }
}
