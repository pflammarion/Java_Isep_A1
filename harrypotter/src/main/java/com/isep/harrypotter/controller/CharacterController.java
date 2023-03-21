package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Potion;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
public class CharacterController {
    private InputParser inputParser;
    private OutputManager outputManager;
    private SpellController spellController;
    private PotionController potionController;
    private Wizard wizard;
    private Random random;
    public void initWizard(){
        outputManager.print("Enter your wizard firstname");
        String firstname = inputParser.getString(null);
        outputManager.print("Enter your wizard lastname");
        String lastname = inputParser.getString(null);
        this.wizard.setFirstname(firstname);
        this.wizard.setLastname(lastname);
        this.wizard.setHouse(assignHouse());
        switch (wizard.getHouse()){
            case HUFFLEPUFF ->{
                wizard.setPotionEfficiency(10);
            }
            case SLYTHERIN -> {
                wizard.setDamage(10);
            }
            case GRYFFINDOR -> {
                wizard.setDefense(10);
            }
            case RAVENCLAW -> {
                wizard.setAccuracy(0.5);
            }
        }
        outputManager.print("Hello " + firstname + " " + lastname);
        outputManager.print("Welcome to Poudlard");
        outputManager.print("Your pet is " + wizard.getPet() + " and were assigned to " + wizard.getHouse() + " house with your nice " + wizard.getWand().getCore() + " wand core");
    }

    public Enemy initEnemy(){
        return new Enemy(100, 100, 10, 10, 0.2, "Cha");
    }

    public Object battleChoice(){
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
            return null;
        }
    }

    public boolean battleEnemy(AbstractEnemy enemy) {
        //TODO parse a summary
        List<Potion> potionList = wizard.getPotions();
        List<AbstractSpell> knownSpell = spellController.getAllKnownSpells(wizard);
        String message;
        do {
            if (potionList.size() > 0 && knownSpell.size() > 0){
                message = "You can use " + potionList.size() + " potions and " + knownSpell.size() + " spells";
            }
            else {
                if (potionList.size() > 0){
                    message = "You can use " + potionList.size() + " potions";
                } else if (knownSpell.size() > 0) {
                    message = "You can use " + knownSpell.size() + " spells";
                }
                else {
                    message = "You don't have any potion or spell...";
                }
            }
            if (potionList.size() > 0 || knownSpell.size() > 0){
                outputManager.displayMessage(message, wizard.getDrunk());
                outputManager.displayMessage("Type the name of what you want to use", wizard.getDrunk());
                Object choice = battleChoice();
                if (choice instanceof AbstractSpell spell){
                    castSpell(spell, enemy);
                }
                else if (choice instanceof Potion potion){
                    drinkPotion(potion);
                }
                else {
                    outputManager.displayMessage("Huoohh... it seems to not exist", wizard.getDrunk());
                }
            }
            outputManager.displayMessage(takeTurn(enemy), wizard.getDrunk());

            if (wizard.getCurrentHealth() < 0){
                wizard.setCurrentHealth(0);
            }
            outputManager.displayMessage("Your life : " + wizard.getCurrentHealth() + "/" + wizard.getTotalHealth() + " and enemy : " + enemy.getCurrentHealth() + "/" + enemy.getTotalHealth(), wizard.getDrunk() );

        } while(wizard.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0);
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

    public void skippingSchool(){
        this.outputManager.displayMessage("You decided to skip school.", this.wizard.getDrunk());
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just found a new potion", this.wizard.getDrunk());
            Potion potion = potionController.getAvailablePotionByName("Felix Felicis");
            potionController.learnPotion(potion, wizard);
            this.outputManager.showListElements("You have those potions:", this.wizard.getPotions(), this.wizard.getDrunk());
        }
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just learned a new spell", this.wizard.getDrunk());
            spellController.learnSpell(new ForbiddenSpell("super forbidden spell","forbid desc", 20, 100, "Explose"), wizard);
            this.outputManager.showListElements("You know those spells:", this.wizard.getKnownSpells(), this.wizard.getDrunk());
        }
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
        switch (potion.getType()){
            case "health":
                double health = wizard.getCurrentHealth() + (potion.getPoint() * wizard.getPotionEfficiency());
                int totalHealth = wizard.getTotalHealth();
                double heal = Math.min(health, totalHealth);
                if (randomProbability(10)){
                    wizard.setCurrentHealth(0);
                }
                else wizard.setCurrentHealth(heal);
                break;
            case "damage":
                //TODO damage
                break;
            case "precision":
                //TODO precision
                break;
            default:
                chance = 2;
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
    }

    private void castSpell(AbstractSpell spell, AbstractEnemy enemy){
        List<AbstractSpell> knownSpell = spellController.getAllKnownSpells(wizard);
        if (knownSpell.contains(spell)){
            outputManager.displayMessage("You cast the spell " + spell.getName(), wizard.getDrunk());
            outputManager.displayMessage(spell.getDescription() + " It gave " + spell.getDamage() + " to " + enemy.getName() + " and it cost you " + spell.getEnergyCost() + " points of energy", wizard.getDrunk());
            enemy.setCurrentHealth(enemy.getCurrentHealth() - spell.getDamage());
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
        Random random = new Random();
        int random1 = random.nextInt(chance);
        int random2 = random.nextInt(chance);
        return random1 == random2;
    }
    private void printWizardPotions(){
        List<Potion> potionList = this.potionController.getKnownPotions(wizard);
        if (potionList.size() > 0) {
            outputManager.showListElements("You know those potions:\n", potionList, wizard.getDrunk());
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

    public String getString(Wizard wizard){
        String userInput;
        boolean isHelp;
        do {
            userInput =  inputParser.getString(wizard);
            isHelp = helperHandler(userInput);
        } while (isHelp);
        return userInput;
    }

    private boolean helperHandler(String userInput){
        int firstSpaceIndex = userInput.indexOf(" ");
        if (firstSpaceIndex != -1) {
            String firstWord = userInput.substring(0, firstSpaceIndex);
            if((userInput.startsWith("s") && firstWord.length() == 1) || userInput.startsWith("show")){
                userInput = userInput.substring(firstSpaceIndex + 1);
                switch (userInput) {
                    //TODO add helper
                    case "potions" -> {
                        printWizardPotions();
                    }
                    case "spells" -> {
                        printWizardSpells();
                    }
                    default -> outputManager.displayMessage("Nothing to see there", wizard.getDrunk());
                }
                outputManager.displayMessage("\nYou can continue your previous action", wizard.getDrunk());
            }
            return true;
        }
        return false;
    }
}
