package TreeChopper;

import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {
    public Bank(ClientContext ctx) {
        super(ctx);
    }

    int yewLogID = 1515;


    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28
                && ctx.players.local().tile().distanceTo(ctx.bank.nearest()) < 7;
    }

    @Override
    public void execute() {
        if (ctx.bank.opened()) {
            ctx.bank.deposit(yewLogID, 27);
        } else {
            if (ctx.bank.inViewport()) {
                ctx.bank.open();
            } else {
                ctx.camera.turnTo(ctx.bank.nearest());
            }
        }
    }
}
