package BarbarianCookAndFish;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkToFire extends Task<ClientContext> {
    public WalkToFire(ClientContext ctx) {
        super(ctx);
    }

    private Tile fireTile = new Tile(3075, 3428);


    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28
                && ctx.players.local().tile().distanceTo(fireTile) > 10;
    }

    @Override
    public void execute() {
        System.out.println("walk to fire");
        ctx.movement.step(fireTile);
        ctx.camera.turnTo(fireTile);
    }
}
