package GiantFrogKiller;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Heal extends Task<ClientContext> {
    public Heal(ClientContext arg0) {
        super(arg0);
    }

    int cookedTroutID = 333;

    @Override
    public boolean activate() {
        return ctx.players.local().healthPercent() <= 50;
    }

    @Override
    public void execute() {
        Item troutToEat = ctx.inventory.select().id(cookedTroutID).poll();
        while (ctx.players.local().healthPercent() < 95) {
            troutToEat.interact("Eat");
        }
    }
}
