package VarrockMiner;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class DropGems extends Task<ClientContext> {
    public DropGems(ClientContext ctx) {
        super(ctx);
    }

    int sapphireID = 1623;

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(sapphireID).count() > 0;
    }

    @Override
    public void execute() {
        for (Item i : ctx.inventory.select()) {
            if (i.id() == sapphireID) {
                i.interact("Drop");
            }
        }
    }
}
