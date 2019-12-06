package goblinKiller;

import org.powerbot.script.Condition;
import org.powerbot.script.Filter;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.*;


import java.util.concurrent.Callable;

@Script.Manifest(name = "Goblin Killer", description = "Kills goblins and buries their bones", properties = "author=Hunter; topic=999; client=4;")

public class HelloWorld extends PollingScript<ClientContext> {

    final static int GOBLIN_IDS[] = {3029, 3030, 3031, 3032, 3033, 3034, 3035, 3036};
    final static int FOOD_ID = 315;
    final static int BONES_ID = 526;

    int killCounter = 0;

    @Override
    public void start() {
        System.out.println("Started.");
    }

    @Override
    public void stop() {
        System.out.println("Stopped.");
    }

    @Override
    public void poll() {
        if (hasFood()) {
            if (needsHeal()) {
                heal();
            } else if(shouldAttack() && hold()){
                //buryBones();
                attack();
            }
            //we have food

            if (bonesVisibleOnGround() && shouldAttack()) {
                //pickupBones();
                //buryBones();
            }

        }

    }

    public boolean hold(){
        return ctx.npcs.select().select(n -> n.interacting().equals(ctx.players.local())).isEmpty();
    }

    public boolean needsHeal() {
        return ctx.combat.health() < 6;
    }

    public boolean shouldAttack() {
        return !ctx.players.local().healthBarVisible();
        // if (currentTargetHealth = 0) return true
    }

    public boolean hasFood() {
        return ctx.inventory.select().id(FOOD_ID).count() > 0;
    }

    public boolean bonesVisibleOnGround() {
        return ctx.groundItems.select().id(BONES_ID).poll().inViewport();
    }

    public boolean hasBones() {
        return ctx.inventory.select().id(BONES_ID).count() > 0;
    }

    public void attack() {
        final Npc goblinToAttack = ctx.npcs.select().id(GOBLIN_IDS).select(new Filter<Npc>() {
            @Override
            public boolean accept(Npc npc) {
                return !npc.healthBarVisible();
            }
        }).nearest().poll();

        goblinToAttack.interact("Attack");

        killCounter++;
        System.out.println(killCounter);

        Condition.wait(new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                return ctx.players.local().healthBarVisible();
            }
        }, 200, 20);
    }


    public void heal() {
        Item foodToEat = ctx.inventory.select().id(FOOD_ID).poll(); //poll takes the top result, peek looks at the top result
        foodToEat.interact("Eat");

        final int startingHealth = ctx.combat.health();

        Condition.wait(new Callable<Boolean>() {

            @Override
            public Boolean call() throws Exception {
                final int currentHealth = ctx.combat.health();
                return currentHealth != startingHealth;
            }
        }, 200, 20);
    }

    public void buryBones() {
        Item bonesToBury = ctx.inventory.select().id(BONES_ID).poll();
        bonesToBury.interact("Bury");
    }

    public void pickupBones() {
        GroundItem bonesToPickup = ctx.groundItems.select().id(BONES_ID).nearest().poll();
        bonesToPickup.interact("Take", "Bones");
    }

}

