package BarbarianCookAndFish;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.*;

import java.util.concurrent.Callable;

public class DropFish extends Task<ClientContext> {

    private int rawTroutID = 335;
    private int rawSalmonID = 331;
    private int fireID = 26185;
    private int cookedTroutID = 333;
    private int cookedSalmonID = 329;
    private int burntFishID = 343;
    private int[] fishIDs = {331, 335};
    private int [] cookedAndBurntIDs = {333, 329, 343};

    public DropFish(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().id(cookedAndBurntIDs).count() > 0;
    }


    @Override
    public void execute() {
        System.out.println("drop the fish");
        for (Item i : ctx.inventory.select()) {
            if (i.id() == cookedSalmonID || i.id() == cookedTroutID || i.id() == burntFishID) {
                i.interact("Drop");
            }
        }


    }
}
