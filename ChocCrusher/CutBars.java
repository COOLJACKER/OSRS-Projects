package ChocCrusher;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class CutBars extends Task<ClientContext> {
    public CutBars(ClientContext ctx) {
        super(ctx);
    }

    public int chocBarID = 1973;
    public int knifeID = 946;
    public int chocDustID = 1975;

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(chocBarID).count() == 27
                && ctx.inventory.select().id(knifeID).count() == 1;
    }

    @Override
    public void execute() {
        System.out.println("cut bars");
        Item chocBar = ctx.inventory.select().id(chocBarID).poll();
        Item knife = ctx.inventory.select().id(knifeID).poll();
        knife.interact("Use");
        Condition.sleep(500);
        chocBar.interact("Use");
        Condition.sleep(1500);


    }
}
