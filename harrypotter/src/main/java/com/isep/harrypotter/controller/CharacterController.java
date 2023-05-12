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
import com.isep.harrypotter.view.Colors;
import com.isep.harrypotter.view.GUIParser;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import com.isep.harrypotter.view.scene.FightView;
import com.isep.harrypotter.view.scene.FindObjectView;
import com.isep.harrypotter.view.scene.WizardView;
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

    private WizardView wizardView;
    private FindObjectView findStuffView;
    private FindObjectView findPotionView;
    private FindObjectView findSpellView;
    private FightView fightView;

    public CharacterController(InputParser inputParser, OutputManager outputManager, SpellController spellController,
                               PotionController potionController) {
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

        if (inputParser instanceof GUIParser){
            this.wizardView = new WizardView();
            this.findStuffView = new FindObjectView();
            this.findPotionView = new FindObjectView();
            this.findSpellView = new FindObjectView();
            this.fightView = new FightView();
            eventListener();
        }
    }


    private void eventListener(){
        ((GUIParser) this.inputParser).addScene("wizard", wizardView.getScene());
        ((GUIParser) this.inputParser).addScene("findStuff", findStuffView.getScene());
        ((GUIParser) this.inputParser).addScene("findPotion", findPotionView.getScene());
        ((GUIParser) this.inputParser).addScene("findSpell", findSpellView.getScene());
        ((GUIParser) this.inputParser).addScene("fight", fightView.getScene());

        wizardView.getButtonValidate().setOnAction(event -> ((GUIParser) this.inputParser).changeScene("game"));

        findStuffView.getButtonValidate().setOnAction(event -> {
            ((GUIParser) this.inputParser).nextView();
        });

        findPotionView.getButtonValidate().setOnAction(event -> {
            ((GUIParser) this.inputParser).nextView();
        });

        findSpellView.getButtonValidate().setOnAction(event -> {
            ((GUIParser) this.inputParser).nextView();
        });



    }
    public void initWizard() {
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
        if (inputParser instanceof GUIParser){
           wizardView.updateWizardFirstname(String.valueOf(wizard.getFirstname()));
           wizardView.updateWizardLastname(String.valueOf(wizard.getLastname()));
           wizardView.updateHouse(String.valueOf(wizard.getHouse()));
           wizardView.updatePet(String.valueOf(wizard.getPet()));
           wizardView.updateWand(String.valueOf(wizard.getWand().getCore()));
        }
        else {
            outputManager.print("Enter your wizard firstname. " + Colors.WARNING + "(ex: Harry)" + Colors.ANSI_RESET);
            String firstname = inputParser.getString(null);
            outputManager.print("Enter your wizard lastname. " + Colors.WARNING + "(ex: Potter)" + Colors.ANSI_RESET);
            String lastname = inputParser.getString(null);
            this.wizard.setFirstname(firstname);
            this.wizard.setLastname(lastname);
            outputManager.print("Hello " + firstname + " " + lastname);
            outputManager.print("Welcome to Poudlard");
            outputManager.print("Your pet is " + Colors.VALIDE + wizard.getPet() + Colors.ANSI_RESET + " and were " +
                    "assigned to " + Colors.VALIDE + wizard.getHouse() + Colors.ANSI_RESET +
                    " house with your nice " + Colors.VALIDE + wizard.getWand().getCore() + Colors.ANSI_RESET + " wand core");
        }
    }

    private Object battleChoice() {
        String input = this.getString(this.wizard);
        Spell spell = spellController.getKnownSpellByName(input, this.wizard);
        Potion potion = potionController.getKnownPotionByName(input, this.wizard);
        if (null != spell) {
            return spell;
        }
        else if (null != potion) {
            return potion;
        }
        else {
            return input;
        }
    }

    public boolean battleEnemy(AbstractEnemy enemy) {
        boolean isActionPassed = false;
        boolean exit = false;
        Map<Potion, Integer> potionList = wizard.getPotions();
        List<AbstractSpell> knownSpell = spellController.getAllKnownSpells(wizard);
        List<Stuff> inventory = wizard.getInventory();
        fightView.setLists(potionList, knownSpell, inventory);
        fightView.updateEnemyName(enemy.getName());
        fightView.updateWizardName(wizard.getFirstname(), wizard.getFirstname());
        //TODO ici il faut super coder
        outputManager.displayMessage(Colors.ERROR + Colors.ANSI_BOLD + "\n\nYou have to fight " + enemy.getName() + "\n\n" + Colors.ANSI_RESET, wizard.getDrunk());
        do {
            showWizardStuff(potionList.size(), knownSpell.size(), inventory.size());
            if (potionList.size() > 0 || knownSpell.size() > 0 || inventory.size() > 0) {
                outputManager.displayMessage("Type the name of what you want to use.\nYou can use a " + Colors.SPELL + "spell" + Colors.ANSI_RESET + ", a " + Colors.POTION + "potion" + Colors.ANSI_RESET + " or an " + Colors.ANSI_BROWN +"object" + Colors.ANSI_RESET +". " + Colors.WARNING + "(ex: Lumos)" + Colors.ANSI_RESET,
                        wizard.getDrunk());
                Object choice = battleChoice();
                if (choice instanceof AbstractSpell spell) {
                    castSpell(spell, enemy);
                    if (enemy instanceof Boss) isActionPassed = checkSpecialSpellBoss((Boss) enemy, spell);
                }
                else if (choice instanceof Potion potion) drinkPotion(potion);
                else if (choice instanceof String stuff) {
                    Optional<Stuff> optionalStuff = inventory.stream().filter(obj -> obj.getName().equalsIgnoreCase(stuff)).findFirst();
                    if (optionalStuff.isPresent()) {
                        if (enemy instanceof Boss && ((Boss) enemy).getSpecialObject().equalsIgnoreCase(stuff)) {
                            exit = true;
                            outputManager.displayMessage("You used the " + Colors.ANSI_BROWN + stuff + Colors.ANSI_RESET + " and it defeated the " + enemy.getName(), wizard.getDrunk());
                        } else outputManager.displayMessage("You used the " + Colors.ANSI_BROWN + stuff + Colors.ANSI_RESET + " but it does nothing...", wizard.getDrunk());
                    }
                }
                else outputManager.displayMessage("Huoohh... it seems to not exist. You only can use available " + Colors.SPELL + "spell" + Colors.ANSI_RESET + ", " + Colors.POTION + "potion" + Colors.ANSI_RESET + " or " + Colors.ANSI_BROWN + "object" + Colors.ANSI_RESET + ".\n" + Colors.WARNING +"You can see the helper with 'show help'" + Colors.ANSI_RESET, wizard.getDrunk());
            }
            outputManager.displayMessage(takeTurn(enemy), wizard.getDrunk());
            if (wizard.getCurrentHealth() < 0) wizard.setCurrentHealth(0);
            outputManager.progressPercentage(wizard.getCurrentHealth(), wizard.getTotalHealth(), "fightWizard");
            outputManager.progressPercentage(enemy.getCurrentHealth(), enemy.getTotalHealth(), "fightEnemy");
            if (enemy.getCurrentHealth() <= 0) exit = !(enemy instanceof Boss) || isActionPassed;
            if (wizard.getCurrentHealth() <= 0) exit = true;
        } while(!exit);

       return endBattleRewards(enemy);
    }

    public boolean skippingSchool() {
        this.outputManager.displayMessage("You decided to skip school", this.wizard.getDrunk());
        if (randomProbability(1)) {
            findPotion();
        }
        if (randomProbability(1)) {
            findSpell();
        }
        if (randomProbability(100000) && wizard.getKnownSpells().size() > 0) {
            ((GUIParser) this.inputParser).changeScene("fight");
            return fightRandomEnemy();
        }
        if (randomProbability(1)) {
            findStuff();
        }
        ((GUIParser) this.inputParser).addViewInQueue("game");
        ((GUIParser) this.inputParser).nextView();
        return true;
    }

    private String takeTurn(AbstractEnemy enemy) {
        if (random.nextDouble() < enemy.getAccuracy()) {
            // boss attack succeeds
            double actualDamage = enemy.getDamage() - wizard.getDefense();
            if (actualDamage <= 0) {
                actualDamage = 0;
            }
            wizard.takeDamage(actualDamage);
            return Colors.ERROR +  enemy.getName() + Colors.ANSI_RESET + " attacks you for " + enemy.getDamage() + " damage! But you have " + wizard.getDefense() + " defense points";
        } else {
            // boss attack fails, deal linear damage
            double attack = enemy.getDamage() * enemy.getAccuracy() *  random.nextDouble();
            double actualDamage = Math.max(attack, enemy.getAccuracy());
            actualDamage = (double) Math.round(actualDamage * 100) / 100;
            wizard.takeDamage(actualDamage);
            return Colors.ERROR + enemy.getName() + Colors.ANSI_RESET + " misses, but deals " + actualDamage + " damage due to the backlash!";
        }
    }

    private void drinkPotion(Potion potion) {
        outputManager.displayMessage("You drunk the potion", wizard.getDrunk());
        int chance = 5;
        switch (potion.getType()) {
            case "health" -> {
                double health = wizard.getCurrentHealth() + (potion.getPoint() * wizard.getPotionEfficiency());
                int totalHealth = wizard.getTotalHealth();
                double heal = Math.min(health, totalHealth);
                if (randomProbability(10)) {
                    outputManager.displayMessage(Colors.ERROR + "NOOO POISON.... ARRGGHHH" + Colors.ANSI_RESET, wizard.getDrunk());
                    wizard.setCurrentHealth(0);
                } else wizard.setCurrentHealth(heal);
                outputManager.displayMessage("You healed up, you have " + Colors.VALIDE + wizard.getCurrentHealth() + " HP" + Colors.ANSI_RESET, wizard.getDrunk());
            }
            case "damage" -> {
                wizard.setDamage((int) (wizard.getDamage() + (potion.getPoint() * wizard.getPotionEfficiency())));
                outputManager.displayMessage("You are now stronger, you have " + Colors.VALIDE + wizard.getDamage() + " points of damage" + Colors.ANSI_RESET, wizard.getDrunk());
            }
            case "accuracy" -> {
                wizard.setAccuracy(wizard.getAccuracy() + potion.getPoint() * wizard.getPotionEfficiency());
                outputManager.displayMessage("You are now more accurate, you have " + Colors.VALIDE + wizard.getAccuracy() + " points of Accuracy" + Colors.ANSI_RESET, wizard.getDrunk());
            }
            default -> chance = 2;
        }

        if (randomProbability(chance)) {
            outputManager.displayMessage("HUHOOO there was " + Colors.ERROR +  "super alcohol" + Colors.ANSI_RESET + " in your super potion, gloups....", wizard.getDrunk());
            wizard.setDrunk(wizard.getDrunk() + 3);
        }
        if (randomProbability(chance * 3)) {
            wizard.setNowPet(true);
            outputManager.displayMessage("Bahahhah you just became a pet lol and you are " + wizard.getPet(), wizard.getDrunk());
        }
        outputManager.progressPercentage(wizard.getCurrentHealth(), wizard.getTotalHealth(), "fightWizard");
        wizard.setPotions(potionController.removePotionFromMap(potion, wizard.getPotions()));
    }

    private void castSpell(AbstractSpell spell, AbstractEnemy enemy) {
        List<AbstractSpell> knownSpell = spellController.getAllKnownSpells(wizard);
        if (knownSpell.contains(spell)) {
            outputManager.displayMessage("You cast the spell " + Colors.SPELL + spell.getName() + Colors.ANSI_RESET, wizard.getDrunk());
            double damagePoints = spell.getDamage() + 10 * (Math.log(wizard.getAccuracy() + 1));
            outputManager.displayMessage(spell.getDescription() + " It gave " + Colors.SPELL + (int) damagePoints + " points of damage" + Colors.ANSI_RESET +" to " + Colors.ERROR + enemy.getName() + Colors.ANSI_RESET + " and it cost you " + spell.getEnergyCost() + " points of energy", wizard.getDrunk());
            enemy.setCurrentHealth(enemy.getCurrentHealth() - damagePoints);
            if (enemy.getCurrentHealth() < 0) enemy.setCurrentHealth(0);
        }
        else {
            outputManager.displayMessage("You don't know the spell " + Colors.SPELL + spell.getName() + Colors.ANSI_RESET + "!", wizard.getDrunk());
        }
    }

    public void soberUp() {
        if (wizard.getDrunk() > 0) {
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

    private boolean randomProbability(int chance) {
        //Return 1/n chance
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }

    private void printWizardPotions() {
        Map<Potion, Integer> potionList = this.potionController.getKnownPotions(wizard);
        if (potionList.size() > 0) {
            outputManager.showMapElements("You know those potions:\n", potionList, wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You don't have any potion", wizard.getDrunk());
        }
    }

    private void printWizardSpells() {
        List<Spell> spellList = this.spellController.getKnownSpells(wizard);
        if (spellList.size() > 0) {
            outputManager.showListElements("You know those spells:\n", spellList, wizard.getDrunk());
        }
        else {
            outputManager.displayMessage("You don't know any spell", wizard.getDrunk());
        }
    }

    private void printWizardInventory() {
        List<Stuff> inventory = wizard.getInventory();
        if (inventory.size() > 0) {
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
            if((userInput.startsWith("s") && firstWord.length() == 1) || userInput.startsWith("show")) {
                userInput = userInput.substring(firstSpaceIndex + 1);
                switch (userInput) {
                    case "help" -> outputManager.readHelperFile();
                    case "potions" -> printWizardPotions();
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

    private void showWizardStuff(int numPotions, int numSpells, int numObjects) {
        String message;
        if (numPotions == 0 && numSpells == 0 && numObjects == 0) {
            message = "You don't have any potions, spells, or objects...";
        } else if (numPotions > 0 && numSpells == 0 && numObjects == 0) {
            message = "You can use " + Colors.POTION + numPotions + " potion" + (numPotions > 1 ? "s" : "") + Colors.ANSI_RESET;
        } else if (numPotions == 0 && numSpells > 0 && numObjects == 0) {
            message = "You can use " + Colors.SPELL + numSpells + " spell" + (numSpells > 1 ? "s" : "") + Colors.ANSI_RESET;
        } else if (numPotions == 0 && numSpells == 0 && numObjects > 0) {
            message = "You can use " + Colors.ANSI_BROWN + numObjects + " object" + (numObjects > 1 ? "s" : "") + Colors.ANSI_RESET;
        } else {
            StringBuilder sb = new StringBuilder("You can use ");
            boolean hasPotions = false;
            boolean hasSpells = false;
            boolean hasObjects = false;

            if (numPotions > 0) {
                sb.append(Colors.POTION).append(numPotions).append(" potion").append(numPotions > 1 ? "s" : "").append(Colors.ANSI_RESET);
                hasPotions = true;
            }

            if (numSpells > 0) {
                if (hasPotions) {
                    sb.append(" and ");
                }
                sb.append(Colors.SPELL).append(numSpells).append(" spell").append(numSpells > 1 ? "s" : "").append(Colors.ANSI_RESET);
                hasSpells = true;
            }

            if (numObjects > 0) {
                if (hasPotions || hasSpells) {
                    sb.append(" and ");
                }
                sb.append(Colors.ANSI_BROWN).append(numObjects).append(" object").append(numObjects > 1 ? "s" : "").append(" from your inventory").append(Colors.ANSI_RESET);
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
        if (null == bossSpell) {
            return true;
        }
        return wizardSpell.getName().equalsIgnoreCase(bossSpell);
    }

    private boolean endBattleRewards(AbstractEnemy enemy) {
        if (wizard.getCurrentHealth() > 0){
            outputManager.print("\n\nYOU WIN !!! \n\n");
            if (enemy instanceof Boss){
                wizard.setTotalHealth(wizard.getTotalHealth() + 50);
                wizard.setCurrentHealth(wizard.getTotalHealth());
                outputManager.displayMessage("You just became stronger, you now have " + Colors.VALIDE + wizard.getCurrentHealth() + " HP, "+ Colors.ANSI_RESET +" congratulation !", wizard.getDrunk());
            }
            if (enemy instanceof Enemy) {
                wizard.setDamage(wizard.getDamage() + random.nextInt(10) + 1);
                wizard.setAccuracy(wizard.getAccuracy() + random.nextInt(10) + 1);
                wizard.setDefense(wizard.getDefense() + random.nextInt(10) + 1);
                outputManager.displayMessage("You just became stronger, you have " + Colors.VALIDE + wizard.getDamage() + " points of damage and " + wizard.getAccuracy() + " points of accuracy and " + wizard.getDefense() + " points of defense." + Colors.ANSI_RESET, wizard.getDrunk());
            }
            return true;
        }
        else {
            outputManager.print("\n\nPaul Nuls\n\n");
            return false;
        }
    }

    private void findPotion() {
        Potion potion = potionController.getPotions().get(random.nextInt(potionController.getPotions().size()));
        potionController.learnPotion(potion, wizard);
        if (inputParser instanceof GUIParser){
            this.findPotionView.setObject(potion);
            ((GUIParser) this.inputParser).addViewInQueue("findPotion");
        }
        else {
            this.outputManager.displayMessage( Colors.VALIDE + "What a lucky day, you just found a new potion" + Colors.ANSI_RESET, this.wizard.getDrunk());
            this.outputManager.showMapElements("You have those potions:", this.wizard.getPotions(), this.wizard.getDrunk());
        }
    }

    private void findSpell() {
        List<ForbiddenSpell> forbiddenSpells = spellController.getForbiddenSpells();
        ForbiddenSpell spell = forbiddenSpells.get(random.nextInt(forbiddenSpells.size()));
        spellController.learnSpell(spell, wizard);
        if (inputParser instanceof GUIParser){
            this.findSpellView.setObject(spell);
            ((GUIParser) this.inputParser).addViewInQueue("findSpell");
        }
        else {
            this.outputManager.displayMessage("What a lucky day, you just learned a new spell", this.wizard.getDrunk());
            this.outputManager.showListElements("You know those spells:", this.wizard.getKnownSpells(), this.wizard.getDrunk());
        }

    }

    private boolean fightRandomEnemy() {
        Enemy enemy = this.enemyList.get(random.nextInt(this.enemyList.size()));
        this.outputManager.displayMessage("Huho, you are in front of the enemy " + Colors.ERROR + Colors.ANSI_BOLD +  enemy.getName() + Colors.ANSI_RESET + ", you have to fight him !", wizard.getDrunk());
        return battleEnemy(enemy);
    }

    private void findStuff() {
        boolean foundFireworks = false;
        List<Stuff> inventory = wizard.getInventory();

        for (Stuff stuff : inventory) {
            if (stuff.getName().equalsIgnoreCase("Fireworks")) {
                foundFireworks = true;
                break;
            }
        }

        if (!foundFireworks) {
            Stuff fireworks = new Stuff("Fireworks", "Use this to defeat the Dolores Ombrage enemy");
            inventory.add(fireworks);
            wizard.setInventory(inventory);

            if (inputParser instanceof GUIParser) {
                this.findStuffView.setObject(fireworks);
                ((GUIParser) this.inputParser).addViewInQueue("findStuff");
            } else {
                outputManager.displayMessage("Ho you just found" + Colors.ANSI_BROWN + " Fireworks. " + fireworks.getDescription() + Colors.ANSI_RESET, wizard.getDrunk());
            }
        }
    }
}
