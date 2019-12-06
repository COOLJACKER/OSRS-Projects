package VarrockMiner;

import org.powerbot.script.Condition;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

import java.awt.*;
import java.util.concurrent.Callable;

public class MineCopperOne extends Task<ClientContext> {
    public MineCopperOne(ClientContext ctx) {
        super(ctx);
    }

    private int[] copperRockIDs = {11161, 10943};
    private int tinOreID = 438;
    private int copperOreID = 436;
    Tile rockTile1 = new Tile(3286, 3364);
    Tile rockTile2 = new Tile(3287, 3365);
    int badRockID = 11391;



    @Override
    public boolean activate() {
        GameObject copperRock1 = ctx.objects.select().id(copperRockIDs).at(rockTile1).poll();


        return ctx.inventory.select().id(tinOreID).count() == 14
                && ctx.inventory.select().id(copperOreID).count() < 14
                && ctx.players.local().animation() == -1
                && !ctx.players.local().inMotion()
                && ctx.inventory.select().count() < 28
                && copperRock1.valid();
    }

    @Override
    public void execute() {
        GameObject copperRock1 = ctx.objects.select().id(copperRockIDs).at(rockTile1).poll();
        GameObject copperRock2 = ctx.objects.select().id(copperRockIDs).at(rockTile2).poll();

        GameObject badRock1 = ctx.objects.select().id(badRockID).at(rockTile1).poll();
        GameObject badRock2 = ctx.objects.select().id(badRockID).at(rockTile2).poll();


        copperRock1.interact("Mine", "Rocks");
        System.out.println("clicked mine for rock 1");

        ctx.input.move(copperRock2.centerPoint());
        ctx.input.move(badRock2.centerPoint());

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return !copperRock1.valid();
            }
        },200,90);

    }
}
