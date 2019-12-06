package EdgevilleSmelter;


import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import java.util.concurrent.Callable;

public class Smelt extends Task<ClientContext> {

    int tinOreID = 438;
    int copperOreID = 436;
    int bronzeBarID = 2349;
    int furnaceID = 16469;

    public Smelt (ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(tinOreID).count() > 0
                && ctx.inventory.select().id(copperOreID).count() > 0
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        Component smeltBox = ctx.widgets.component(270, 5);
        int oreCounter = ctx.inventory.select().id(tinOreID).count();
        System.out.println("smelting");
        GameObject furnace = ctx.objects.select().id(furnaceID).poll();

       if (furnace.inViewport()) {
           furnace.interact("Smelt");
           Condition.wait(new Callable<Boolean>() {
               @Override
               public Boolean call() throws Exception {
                   return smeltBox.visible();
               }
           }, 1500, 1);

           ctx.input.send("1");

           Condition.sleep(2400 * oreCounter);
       } else {
           ctx.movement.step(furnace);
           ctx.camera.turnTo(furnace);
       }


    }
}
