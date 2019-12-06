package BananaPicker;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    Tile bankTile = new Tile(3092, 3245);
    int bananaID = 1963;


    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(bankTile) < 2
                && ctx.inventory.select().id(bananaID).count() > 0;
    }

    @Override
    public void execute() {
        System.out.println("banking");
        ctx.bank.open();
        ctx.bank.deposit(bananaID, 27);
        ctx.bank.close();
    }
}
