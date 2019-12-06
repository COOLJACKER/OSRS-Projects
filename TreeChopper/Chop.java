package TreeChopper;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.GameObject;

public class Chop extends Task<ClientContext> {

    private int[] treeID = {10822};

    public Chop(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        GameObject tree = ctx.objects.select().id(treeID).poll();

        return ctx.inventory.select().count() < 28
                && !ctx.objects.select().id(treeID).isEmpty()
                && !ctx.players.local().inMotion()
                && ctx.players.local().animation() == -1
                && ctx.players.local().tile().distanceTo(tree) < 6;
    }

    @Override
    public void execute() {
        GameObject tree = ctx.objects.select().id(treeID).poll();
        if (tree.inViewport()) {
            tree.interact("Chop", "Yew");
        } else {
            ctx.movement.step(tree);
            ctx.camera.turnTo(tree);
        }
    }
}
