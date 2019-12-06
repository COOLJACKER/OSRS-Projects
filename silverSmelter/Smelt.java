package silverSmelter;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class Smelt extends Task<ClientContext> {

    final static int silverOreID = 442;
    final static int silverBarID = 2355;
    final static int furnaceID = 24009;

    public Smelt (ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(silverOreID).count() > 0
                && ctx.players.local().animation() == -1;
    }

    @Override
    public void execute() {
        GameObject furnace = ctx.objects.select().id(furnaceID).poll();
        Component smeltBox = ctx.widgets.component(270, 5);

        if (furnace.inViewport()) {
            furnace.interact("Smelt");
        } else {
            ctx.movement.step(furnace);
            ctx.camera.turnTo(furnace);
        }

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return smeltBox.visible();
            }
        }, 1500, 1);

        ctx.input.send("3");

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.inventory.select().id(silverBarID).count() == 28;
            }
        }, 1000, 30);


    }
}
