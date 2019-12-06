package BarbFisher;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Component;
import org.powerbot.script.rt4.GameObject;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class ChopAndLight extends Task<ClientContext> {

    private int treeID = 1278;
    private int logID = 1511;
    private int tinderboxID = 590;
    private int rawTroutID = 335;
    private int salmonID = 334;
    private int fireID = 26185;

    public ChopAndLight(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(logID).count() == 0
                && ctx.inventory.select().count() == 27;
    }

    @Override
    public void execute() {
        Item trout = ctx.inventory.select().id(rawTroutID).poll();
        trout.interact("Drop");


        GameObject tree = ctx.objects.select().id(treeID).nearest().poll();
        tree.interact("Chop");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return (ctx.inventory.select().id(logID).count() == 1);
            }
        }, 1000, 20);

        //if (ctx.inventory.select().id(logID).count() == 1) {
            Item logToBurn = ctx.inventory.select().id(logID).poll();
            logToBurn.interact("Use");

            Item tinderbox = ctx.inventory.select().id(tinderboxID).poll();
            tinderbox.interact("Use");
            Condition.sleep(3000);
        //}




    }
}
