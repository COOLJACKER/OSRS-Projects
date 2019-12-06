package boneBury;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.Condition;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.*;

public class Pickup extends Task<ClientContext> {

    private int bonesID = 526;

    public Pickup(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() != 28
                && !ctx.groundItems.select().id(bonesID).isEmpty()
                && !ctx.players.local().inMotion();
    }

    @Override
    public void execute() {
        GroundItem bones = ctx.groundItems.nearest().poll();
        if (bones.inViewport()) {
            bones.interact("Take", "Bones");
        } else {
            ctx.movement.step(bones);
            ctx.camera.turnTo(bones);
        }
    }
}
