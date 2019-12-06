package BarbFisher;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class CookFish extends Task<ClientContext> {

    private int rawTroutID = 335;
    private int salmonID = 334;
    private int fireID = 26185;
    private int cookedTroutID = 333;

    public CookFish(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {

        Item rawTrout = ctx.inventory.select().id(rawTroutID).poll();

        return ctx.inventory.select().count() == 27
                && ctx.inventory.select().contains(rawTrout)
                //|| ctx.inventory.select().id(salmonID).count() > 0
                && !ctx.objects.select().id(fireID).nearest().isEmpty();
    }

    @Override
    public void execute() {

        //Widget widget = ctx.widgets.widget(270);
        Component troutBox = ctx.widgets.component(270, 5);

        Item trout = ctx.inventory.select().id(rawTroutID).poll();
        GameObject fire = ctx.objects.select().id(fireID).nearest().poll();

        trout.interact("Use");
        fire.interact("Use");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return troutBox.visible();
            }
        }, 1500, 20);

        ctx.input.send("1");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().animation() == -1;
            }
        }, 1500, 20);




    }
}
