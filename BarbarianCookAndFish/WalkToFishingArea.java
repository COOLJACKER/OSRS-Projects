package BarbarianCookAndFish;

import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkToFishingArea extends Task<ClientContext> {
    public WalkToFishingArea(ClientContext ctx) {
        super(ctx);
    }

    private Tile fishingArea = new Tile(3103, 3430);
    private int rawTroutID = 335;
    private int rawSalmonID = 331;

    @Override
    public boolean activate() {
        return ctx.players.local().tile().distanceTo(fishingArea) > 13
                && (ctx.inventory.select().id(rawTroutID).count() == 0 && ctx.inventory.select().id(rawSalmonID).count() == 0);
    }

    @Override
    public void execute() {
        System.out.println("walking to fishing area");
        ctx.movement.step(fishingArea);
        ctx.camera.turnTo(fishingArea);
    }
}
