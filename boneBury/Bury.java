package boneBury;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

public class Bury extends Task<ClientContext> {

    private int bonesID = 526;

    public Bury(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28;
    }

    @Override
    public void execute() {
        for (Item i : ctx.inventory.select().id(bonesID)) {
            i.interact("Bury");
            Condition.sleep(1200);
        }
    }
}
