package BarbFisher;


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

    public DropFish(ClientContext ctx) {
        super(ctx);
    }

    @Override
    public boolean activate() {
        return ctx.inventory.select().count() == 28;
    }


    @Override
    public void execute() {

        for (Item i : ctx.inventory.select()) {

            if (ctx.controller.isStopping()) {
                break;
            }

            int startAmountFish = ctx.inventory.select().id(rawTroutID).count() +
                    ctx.inventory.select().id(rawSalmonID).count();

            if (i.id() == rawSalmonID || i.id() == rawTroutID) {
                i.interact( "Drop");
                Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return (ctx.inventory.select().id(rawTroutID).count() +
                                ctx.inventory.select().id(rawSalmonID).count()) != startAmountFish;
                    }
                },25,20);
            }
        }


    }
}
