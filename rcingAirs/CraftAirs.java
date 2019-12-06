package rcingAirs;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.util.concurrent.Callable;

public class CraftAirs extends Task<ClientContext> {

    private int airRuneID = 556;
    private int runeEssID = 1436;
    private int ruinsID = 29090;
    private int altarID = 34760;
    private int exitPortal = 34748;

    public CraftAirs(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject altar = ctx.objects.select().id(altarID).nearest().poll();


        return ctx.objects.select().id(altarID).poll().inViewport()
                && ctx.inventory.select().id(runeEssID).count() > 1;
    }

    @Override
    public void execute() {
        GameObject altar = ctx.objects.select().id(altarID).nearest().poll();
        GameObject portal = ctx.objects.select().id(exitPortal).nearest().poll();
        GameObject ruins = ctx.objects.select().id(ruinsID).nearest().poll();


        altar.interact("Craft");


        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().id(airRuneID).count() > 27
                        && ctx.players.local().animation() == -1;
            }
        },500,8);


    }
}
