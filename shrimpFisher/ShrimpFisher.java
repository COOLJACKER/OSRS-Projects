package shrimpFisher;

import org.powerbot.script.Condition;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.*;

@Script.Manifest(name = "Shrimp Fisher",
        description = "Collects shrimp from the ocean and drops them",
        properties = "author=Hunter; topic=999; client=4;")

public class ShrimpFisher extends PollingScript<ClientContext> {

    final static int rawShrimp = 317;
    final static int rawAnchovies = 321;
    final static int fishingSpot = 1530;

    @Override
    public void poll() {

        currentInventoryCount();

        if (!isFishing() && !inventoryIsFull()) {
            clickOnFishingSpot();
        }


        if (inventoryIsFull()) {
            while (currentInventoryCount() > 2) {
                dropShrimp();
            }
        }

        //System.out.println(ctx.players.local().animation());
        //System.out.println(currentInventoryCount());


    }

    public boolean isFishing() {
        return (ctx.players.local().animation() == 621);
    }

    public int currentInventoryCount() {
        return (ctx.inventory.count());
    }

    public boolean inventoryIsFull() {
        return ctx.inventory.isFull();
    }

    public int shrimpsInventoryCount() {
        return ctx.inventory.select().id(rawShrimp).count();

    }

    public int anchoviesInventoryCount() {
        return ctx.inventory.select().id(rawAnchovies).count();

    }


    public void clickOnFishingSpot() {
        Npc nearestFishingSpot = ctx.npcs.select().id(fishingSpot).nearest().poll();
        nearestFishingSpot.interact("Net");
        Condition.sleep(2000);
    }


    public void dropShrimp() {
/*        for (int i = 0; i < 26; i++) {
            Item shrimpToDrop = ctx.inventory.select().id(rawShrimp).poll();
            shrimpToDrop.interact("Drop");
            Condition.sleep(650);

            Item anchoviesToDrop = ctx.inventory.select().id(rawAnchovies).poll();
            anchoviesToDrop.interact("Drop");

            Condition.sleep(650);
        }*/
        for (Item i : ctx.inventory.select().id(rawShrimp)) {
            i.interact("Drop");
        }

    }


}
