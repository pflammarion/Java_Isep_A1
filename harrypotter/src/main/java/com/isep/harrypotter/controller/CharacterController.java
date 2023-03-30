package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.Stuff;
import com.isep.harrypotter.model.characters.AbstractEnemy;
import com.isep.harrypotter.model.characters.Boss;
import com.isep.harrypotter.model.characters.Enemy;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.others.House;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.ForbiddenSpell;
import com.isep.harrypotter.model.spells.Spell;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


@Getter
@Setter
public class CharacterController {
    private InputParser inputParser;
    private OutputManager outputManager;
    private SpellController spellController;
    private PotionController potionController;
    private Wizard wizard;
    private Random random;
    private List<Enemy> enemyList;

    public CharacterController(InputParser inputParser, OutputManager outputManager, SpellController spellController, PotionController potionController){
        this.inputParser = inputParser;
        this.outputManager = outputManager;
        this.spellController = spellController;
        this.potionController = potionController;
        this.wizard = new Wizard();
        this.random = new Random();
        this.enemyList = new ArrayList<>();

        enemyList.add(new Enemy(100, 100, 10, 10, 0.2, "The Insatiable Spider"));
        enemyList.add(new Enemy(120, 120, 15, 12, 0.3, "The Fiery Salamander"));
        enemyList.add(new Enemy(80, 80, 5, 18, 0.4, "The Sneaky Niffler"));
        enemyList.add(new Enemy(150, 150, 20, 8, 0.1, "The Ice-Cold Yeti"));
        enemyList.add(new Enemy(200, 200, 25, 20, 0.5, "The Fearsome Dragon"));
        enemyList.add(new Enemy(80, 80, 5, 18, 0.4, "The Cunning Sphinx"));
        enemyList.add(new Enemy(130, 130, 18, 14, 0.2, "The Venomous Basilisk"));
        enemyList.add(new Enemy(90, 90, 8, 16, 0.3, "The Agile Monkey"));
        enemyList.add(new Enemy(180, 180, 22, 10, 0.1, "The Thunderous Giant"));
        enemyList.add(new Enemy(110, 110, 12, 12, 0.2, "The Tricky Changeling"));
        enemyList.add(new Enemy(140, 140, 18, 14, 0.3, "The Ferocious Werewolf"));
        enemyList.add(new Enemy(70, 70, 5, 20, 0.5, "The Deadly Vampire"));
        enemyList.add(new Enemy(160, 160, 20, 16, 0.2, "The Wise Phoenix"));
        enemyList.add(new Enemy(120, 120, 14, 14, 0.3, "The Devious Imp"));
        enemyList.add(new Enemy(90, 90, 7, 16, 0.4, "The Crafty Pixie"));
        enemyList.add(new Enemy(200, 200, 25, 18, 0.1, "The Colossal Golem"));
        enemyList.add(new Enemy(150, 150, 18, 14, 0.2, "The Elusive Ghost"));
        enemyList.add(new Enemy(110, 110, 12, 10, 0.3, "The Venomous Scorpion"));
        enemyList.add(new Enemy(70, 70, 5, 20, 0.5, "The Destructive Cyclops"));
        enemyList.add(new Enemy(180, 180, 20, 16, 0.2, "The Savage Minotaur"));
    }

    public void initWizard(){
        outputManager.print("Enter your wizard firstname");
        String firstname = inputParser.getString(null);
        outputManager.print("Enter your wizard lastname");
        String lastname = inputParser.getString(null);
        this.wizard.setFirstname(firstname);
        this.wizard.setLastname(lastname);
        this.wizard.setHouse(assignHouse());
        switch (wizard.getHouse()){
            case HUFFLEPUFF -> wizard.setPotionEfficiency(10);
            case SLYTHERIN -> wizard.setDamage(10);

            case GRYFFINDOR -> {
                wizard.setDefense(10);
                List<Stuff> inventory = this.wizard.getInventory();
                inventory.add(new Stuff("Sword", "The Gryffindor sword"));
                this.wizard.setInventory(inventory);
            }
            case RAVENCLAW -> wizard.setAccuracy(0.5);
        }
        outputManager.print("Hello " + firstname + " " + lastname);
        outputManager.print("Welcome to Poudlard");
        outputManager.print("Your pet is " + wizard.getPet() + " and were assigned to " + wizard.getHouse() + " house with your nice " + wizard.getWand().getCore() + " wand core");
    }

    private Object battleChoice(){
        String input = this.getString(this.wizard);
        Spell spell = spellController.getKnownSpellByName(input, this.wizard);
        Potion potion = potionController.getKnownPotionByName(input, this.wizard);
        if (null != spell){
            return spell;
        }
        else if (null != potion){
            return potion;
        }
        else{
            return input;
        }
    }

    public boolean battleEnemy(AbstractEnemy enemy) {
        boolean isActionPassed = false;
        boolean exit = false;
        //TODO parse a summary
        Map<Potion, Integer> potionList = wizard.getPotions();
        List<AbstractSpell> knownSpell = spellController.getAllKnownSpells(wizard);
        List<Stuff> inventory = wizard.getInventory();
        do {
            showWizardStuff(potionList.size(), knownSpell.size(), inventory.size());
            if (potionList.size() > 0 || knownSpell.size() > 0 || inventory.size() > 0) {
                outputManager.displayMessage("Type the name of what you want to use", wizard.getDrunk());
                Object choice = battleChoice();
                if (choice instanceof AbstractSpell spell) {
                    castSpell(spell, enemy);
                    if (enemy instanceof Boss) {
                        isActionPassed = checkSpecialSpellBoss((Boss) enemy, spell);
                    }
                } else if (choice instanceof Potion potion) {
                    drinkPotion(potion);
                }
                else if (choice instanceof String stuff){
                    Optional<Stuff> optionalStuff = inventory.stream()
                            .filter(obj -> obj.getName().equalsIgnoreCase(stuff))
                            .findFirst();

                    if (optionalStuff.isPresent()) {
                        if (enemy instanceof Boss && ((Boss) enemy).getSpecialObject().equalsIgnoreCase(stuff)) {
                            exit = true;
                            outputManager.displayMessage("You used the " + stuff + " and it defeated the " + enemy.getName(), wizard.getDrunk());
                        } else {
                            outputManager.displayMessage("You used the " + stuff + " but it does nothing...", wizard.getDrunk());
                        }
                    }
                }
                else {
                    outputManager.displayMessage("Huoohh... it seems to not exist", wizard.getDrunk());
                }
            }
            outputManager.displayMessage(takeTurn(enemy), wizard.getDrunk());

            if (wizard.getCurrentHealth() < 0) {
                wizard.setCurrentHealth(0);
            }
            outputManager.progressPercentage(wizard.getCurrentHealth(), wizard.getTotalHealth(), "fightWizard");
            outputManager.progressPercentage(enemy.getCurrentHealth(), enemy.getTotalHealth(), "fightEnemy");
            if (enemy.getCurrentHealth() <= 0) {
                if (enemy instanceof Boss) {
                    exit = isActionPassed;
                } else {
                    exit = true;
                }
            }
            if (wizard.getCurrentHealth() <= 0) {
                exit = true;
            }

        } while(!exit);
        //TODO end of the battle
        if (wizard.getCurrentHealth() > 0){
            outputManager.print("\n\nYOU WIN !!! \n\n");
            if (enemy instanceof Boss){
                wizard.setTotalHealth(wizard.getTotalHealth() + 50);
                wizard.setCurrentHealth(wizard.getTotalHealth());
                outputManager.displayMessage("You just became stronger, you now have " + wizard.getCurrentHealth() + " HP, congratulation !", wizard.getDrunk());
            }
            if (enemy instanceof Enemy){
                wizard.setDamage(wizard.getDamage() + random.nextInt(10) + 1);
                wizard.setAccuracy(wizard.getAccuracy() + random.nextInt(10) + 1);
                wizard.setDefense(wizard.getDefense() + random.nextInt(10) + 1);
                outputManager.displayMessage("You just became stronger, you have " + wizard.getDamage() + " points of damage and " + wizard.getAccuracy() + " points of accuracy and " + wizard.getDefense() + " points of defense.", wizard.getDrunk());
            }
            return true;
        }
        else {
            outputManager.print("\n\nPaul Nuls\n\n");
            return false;
        }
    }

    public boolean skippingSchool(){
        this.outputManager.displayMessage("You decided to skip school.", this.wizard.getDrunk());
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just found a new potion", this.wizard.getDrunk());
            Potion potion = potionController.getPotions().get(random.nextInt(potionController.getPotions().size()));
            potionController.learnPotion(potion, wizard);
            this.outputManager.showMapElements("You have those potions:", this.wizard.getPotions(), this.wizard.getDrunk());
        }
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just learned a new spell", this.wizard.getDrunk());
            List<ForbiddenSpell> forbiddenSpells = spellController.getForbiddenSpells();
            spellController.learnSpell(forbiddenSpells.get(random.nextInt(forbiddenSpells.size())), wizard);
            this.outputManager.showListElements("You know those spells:", this.wizard.getKnownSpells(), this.wizard.getDrunk());
        }
        if (randomProbability(5) && wizard.getKnownSpells().size() > 0){
            Enemy enemy = this.enemyList.get(random.nextInt(this.enemyList.size()));
            this.outputManager.displayMessage("Huho, you are in front of the enemy " + enemy.getName() + ", you have to fight him !", wizard.getDrunk());
            return battleEnemy(enemy);
        }
        if (randomProbability(10)){
            List<Stuff> inventory = wizard.getInventory();
            for (Stuff stuff : inventory) {
                if (!stuff.getName().equalsIgnoreCase("Fireworks")) {
                    Stuff fireworks = new Stuff("Fireworks", "Use this to defeat the Dolores Ombrage enemy");
                   inventory.add(fireworks);
                   wizard.setInventory(inventory);
                   outputManager.displayMessage("Ho you juste found Fireworks. " + fireworks.getDescription(), wizard.getDrunk());
                   break;
                }
            }
        }
        return true;
    }

    private String takeTurn(AbstractEnemy enemy) {
        if (random.nextDouble() < enemy.getAccuracy()) {
            // boss attack succeeds
            double actualDamage = enemy.getDamage() - wizard.getDefense();
            if (actualDamage <= 0) {
                actualDamage = 0; // ensure at least 1 damage is dealt
            }
            wizard.takeDamage(actualDamage);
            return enemy.getName() + " attacks you for " + enemy.getDamage() + " damage! But you have " + wizard.getDefense();
        } else {
            // boss attack fails, deal linear damage
            double attack = enemy.getDamage() * enemy.getAccuracy() *  random.nextDouble();
            double actualDamage = Math.max(attack, enemy.getAccuracy());
            actualDamage = (double) Math.round(actualDamage * 100) / 100;
            wizard.takeDamage(actualDamage);
            return enemy.getName() + " misses, but deals " + actualDamage + " damage due to the backlash!";
        }
    }

    private void drinkPotion(Potion potion){
        outputManager.displayMessage("You drunk the potion", wizard.getDrunk());
        int chance = 5;
        switch (potion.getType()) {
            case "health" -> {
                double health = wizard.getCurrentHealth() + (potion.getPoint() * wizard.getPotionEfficiency());
                int totalHealth = wizard.getTotalHealth();
                double heal = Math.min(health, totalHealth);
                if (randomProbability(10)) {
                    outputManager.displayMessage("NOOO POISON.... ARRGGHHH", wizard.getDrunk());
                    wizard.setCurrentHealth(0);
                } else wizard.setCurrentHealth(heal);
                outputManager.displayMessage("You healed up, you have " + wizard.getCurrentHealth() + " HP", wizard.getDrunk());
            }
            case "damage" -> {
                wizard.setDamage((int) (wizard.getDamage() + (potion.getPoint() * wizard.getPotionEfficiency())));
                outputManager.displayMessage("You are now stronger, you have " + wizard.getDamage() + " points of damage", wizard.getDrunk());
            }
            case "accuracy" -> {
                wizard.setAccuracy(wizard.getAccuracy() + potion.getPoint() * wizard.getPotionEfficiency());
                outputManager.displayMessage("You are now more accurate, you have " + wizard.getAccuracy() + " points of Accuracy", wizard.getDrunk());
            }
            default -> chance = 2;
        }

        if (randomProbability(chance)){
            outputManager.displayMessage("HUHOOO there was super alcohol in your super potion, gloups....", wizard.getDrunk());
            wizard.setDrunk(wizard.getDrunk() + 3);
        }
        if (randomProbability(chance * 3)){
            wizard.setNowPet(true);
            outputManager.displayMessage("Bahahhah you just became a pet lol and you are " + wizard.getPet(), wizard.getDrunk());
        }
        outputManager.displayMessage("\nYour current health is : " + wizard.getCurrentHealth() + "/" + wizard.getTotalHealth(),wizard.getDrunk());
        wizard.setPotions(potionController.removePotionFromMap(potion, wizard.getPotions()));
    }

    private void castSpell(AbstractSpell spell, AbstractEnemy enemy){
        List<AbstractSpell> knownSpell = spellController.getAllKnownSpells(wizard);
        if (knownSpell.contains(spell)){
            outputManager.displayMessage("You cast the spell " + spell.getName(), wizard.getDrunk());
            outputManager.displayMessage(spell.getDescription() + " It gave " + spell.getDamage() + " points of damage to " + enemy.getName() + " and it cost you " + spell.getEnergyCost() + " points of energy", wizard.getDrunk());
            enemy.setCurrentHealth(enemy.getCurrentHealth() - (spell.getDamage() + 100 * (Math.log(wizard.getAccuracy() + 1))));
            if (enemy.getCurrentHealth()<0) enemy.setCurrentHealth(0);
        }
        else {
            outputManager.displayMessage("You don't know the spell " + spell.getName() + "!", wizard.getDrunk());
        }
    }

    public void soberUp(){
        if (wizard.getDrunk() > 0){
            wizard.setDrunk(wizard.getDrunk() - 1);
        }
    }

    private House assignHouse() {

        // Generate a random number between 0 and 3
        int randomNum = (int) (Math.random() * 4);

        // Assign a house based on the random number
        if (randomNum == 0) {
            return House.GRYFFINDOR;
        } else if (randomNum == 1) {
            return House.RAVENCLAW;
        } else if (randomNum == 2) {
            return House.SLYTHERIN;
        } else {
            return House.HUFFLEPUFF;
        }
    }

    private boolean randomProbability(int chance){
        //Return 1/n chance
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }

    private void printWizardPotions(){
        Map<Potion, Integer> potionList = this.potionController.getKnownPotions(wizard);
        if (potionList.size() > 0) {
            outputManager.showMapElements("You know those potions:\n", potionList, wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You don't have any potion", wizard.getDrunk());
        }
    }

    private void printWizardSpells(){
        List<Spell> spellList = this.spellController.getKnownSpells(wizard);
        if (spellList.size() > 0){
            outputManager.showListElements("You know those spells:\n", spellList, wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You don't know any spell", wizard.getDrunk());
        }
    }

    private void printWizardInventory(){
        List<Stuff> inventory = wizard.getInventory();
        if (inventory.size() > 0){
            outputManager.showListElements("Here is your inventory:\n", inventory, wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You don't have anything in your inventory", wizard.getDrunk());
        }
    }

    public String getString(Wizard wizard) {
        String userInput;
        boolean isHelp;
        do {
            userInput =  inputParser.getString(wizard);
            isHelp = helperHandler(userInput);
        } while (isHelp);
        return userInput;
    }

    private boolean helperHandler(String userInput) {
        int firstSpaceIndex = userInput.indexOf(" ");
        if (firstSpaceIndex != -1) {
            String firstWord = userInput.substring(0, firstSpaceIndex);
            if((userInput.startsWith("s") && firstWord.length() == 1) || userInput.startsWith("show")){
                userInput = userInput.substring(firstSpaceIndex + 1);
                switch (userInput) {
                    case "help" -> outputManager.readHelperFile();
                    case "potions" ->
                        printWizardPotions();
                    case "spells" -> printWizardSpells();
                    case "inventory" -> printWizardInventory();
                    default -> outputManager.displayMessage("Nothing to see there", wizard.getDrunk());
                }
                outputManager.displayMessage("\nYou can continue your previous action", wizard.getDrunk());
                return true;
            }
        }
        return false;
    }

    private void showWizardStuff(int numPotions, int numSpells, int numObjects){
        String message;

        if (numPotions == 0 && numSpells == 0 && numObjects == 0) {
            message = "You don't have any potions, spells, or objects...";
        } else if (numPotions > 0 && numSpells == 0 && numObjects == 0) {
            message = "You can use " + numPotions + " potion" + (numPotions > 1 ? "s" : "");
        } else if (numPotions == 0 && numSpells > 0 && numObjects == 0) {
            message = "You can use " + numSpells + " spell" + (numSpells > 1 ? "s" : "");
        } else if (numPotions == 0 && numSpells == 0 && numObjects > 0) {
            message = "You can use " + numObjects + " object" + (numObjects > 1 ? "s" : "");
        } else {
            StringBuilder sb = new StringBuilder("You can use ");
            boolean hasPotions = false;
            boolean hasSpells = false;
            boolean hasObjects = false;

            if (numPotions > 0) {
                sb.append(numPotions).append(" potion").append(numPotions > 1 ? "s" : "");
                hasPotions = true;
            }

            if (numSpells > 0) {
                if (hasPotions) {
                    sb.append(" and ");
                }
                sb.append(numSpells).append(" spell").append(numSpells > 1 ? "s" : "");
                hasSpells = true;
            }

            if (numObjects > 0) {
                if (hasPotions || hasSpells) {
                    sb.append(" and ");
                }
                sb.append(numObjects).append(" object").append(numObjects > 1 ? "s" : "").append(" from your inventory");
                hasObjects = true;
            }

            if (!hasObjects) {
                sb.append(" from your ").append(hasPotions && hasSpells ? "potions and spells" : "inventory");
            }

            message = sb.toString();
        }
        outputManager.displayMessage(message, wizard.getDrunk());
    }

    private boolean checkSpecialSpellBoss(Boss boss, AbstractSpell wizardSpell) {
        String bossSpell = boss.getSpecialSpell();
        if (null == bossSpell){
            return true;
        }
        return wizardSpell.getName().equalsIgnoreCase(bossSpell);
    }
}
