package barSmelter;

import org.powerbot.script.Condition;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.*;

@Script.Manifest(name = "Bar Smelter",
        description = "Smelts bronze bars at the Al-Kharid furnace",
        properties = "author=Hunter; topic=999; client=4;")

public class BarSmelter extends PollingScript<ClientContext> {

    final static int copperOre = 436;
    final static int tinOre = 438;
    final static int bronzeBar = 2349;

    public static final Tile bankTile = new Tile(3271, 3166);

    public static final Tile furnaceTile = new Tile(3274, 3186, 0);
    public static final int furnace = 24009;

    public int bronzeBarInventoryCount = ctx.inventory.select().id(bronzeBar).count();

    @Override
    public void poll() {

        //System.out.println(ctx.players.local().animation());

        if ((ctx.inventory.select().id(copperOre).count() > 0) && (ctx.inventory.select().id(tinOre).count() > 0)) {
            if (!isByFurnace()) {
                System.out.println("Walking to furnace");
                walkToFurnace();
            }
            if (isByFurnace()) {
                if (ctx.players.local().animation() != 899) {
                    System.out.println("Is next to furnace, start smelting");
                    startSmelting();
                    while (ctx.inventory.select().id(bronzeBar).count() < 14) {
                        Condition.sleep(1000);
                    }
                }
            }

        }


        if (ctx.inventory.select().id(bronzeBar).count() > 13) {
            System.out.println("has max bars");
            walkToBank();
            System.out.println("walking to bank b/c bronze bar above 13");
            depositBars();
            System.out.println("depositing bars");
            withdrawOres();
        }

        if ((ctx.inventory.select().id(copperOre).count() == 0 || ctx.inventory.select().id(tinOre).count() == 0)) {
            walkToBank();
            System.out.println("walkin to bank because inv is 0");
            if (ctx.inventory.select().id(bronzeBar).count() == 0) {
                withdrawOres();
                System.out.println("withdrawing ores");
            } else {
                depositBars();
            }
        }


    }


    public boolean isByBank() {
        return ctx.players.local().tile().equals(bankTile);
    }

    public boolean isByFurnace() {
        return ctx.players.local().tile().equals(furnaceTile);
    }

    public boolean isSmelting() {
        return (ctx.players.local().animation() == 899);
    }


    public void walkToBank() {
        System.out.println("walktobank has been activated");
        ctx.movement.step(bankTile);
        ctx.camera.turnTo(bankTile);
    }

    public void walkToFurnace() {
        System.out.println("walkTofurnace has been activated");
        ctx.movement.step(furnaceTile);
        ctx.camera.turnTo(furnaceTile);
    }

    public void startSmelting() {
        System.out.println("startSmelting has been activated");
        GameObject theFurnace = ctx.objects.select().id(furnace).nearest().poll();
        theFurnace.interact("Smelt");
        Condition.sleep(1000);
        ctx.input.send("1");
        //Condition.sleep(24000);

    }

    public void withdrawOres() {
        System.out.println("opened the bank to withdraw ores");
        //Condition.sleep(1000);
        Bank bank = ctx.bank;
        bank.open();

        if (ctx.inventory.select().id(tinOre).count() > 1) {
            bank.deposit(bronzeBar, 28);
        }

        if (ctx.inventory.select().id(tinOre).count() == 0) {
            ctx.bank.withdraw(tinOre, 14);
        }
        if (ctx.inventory.select().id(copperOre).count() == 0) {
            ctx.bank.withdraw(copperOre, 14);
        }
        ctx.bank.close();


    }

    public void depositBars() {
        //Npc theBanker = ctx.npcs.select().id(bankerIDs).nearest().poll();
        //GameObject bank = ctx.objects.select().id(bankBooth).nearest().poll();
        Bank bank = ctx.bank;
        bank.open();
        //theBanker.interact("Bank");
        System.out.println("opened the bank to deposit BARS");
        //Condition.sleep(2500);
        if (bank.opened()) {
            ctx.bank.deposit(bronzeBar, 14);
        }
        ctx.bank.close();
    }

    public void traverseToBank() {
        ctx.movement.findPath(bankTile).traverse();
    }

    public void traverseToFurnace() {
        ctx.movement.findPath(furnaceTile).traverse();
    }


}
