package com.isep.harrypotter.controller;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.characters.AbstractEnemy;
import com.isep.harrypotter.model.characters.Enemy;
import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.model.others.House;
import com.isep.harrypotter.model.spells.AbstractSpell;
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
        String input = inputParser.getString(this.wizard);
        Spell spell = Spell.getSpellByName(input, this.wizard, true);
        Potion potion = Potion.getPotionByName(input, this.wizard);
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
        List<Spell> knownSpell = wizard.getKnownSpells();
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
                    enemy.setCurrentHealth(enemy.getCurrentHealth() - spell.getDamage());
                    if (enemy.getCurrentHealth()<0)enemy.setCurrentHealth(0);
                }
                else if (choice instanceof Potion potion){
                    drinkPotion(potion);
                    outputManager.displayMessage(potion.drinkPotion(wizard), wizard.getDrunk());
                }
                else {
                    outputManager.displayMessage("Huoohh... it seems to not exist", wizard.getDrunk());
                }
            }
            outputManager.displayMessage(takeTurn(enemy), wizard);
            outputManager.displayMessage(wizard.takeTurn(enemy), wizard.getDrunk());

            if (wizard.getCurrentHealth() < 0){
                wizard.setCurrentHealth(0);
            }
            outputManager.displayMessage("Your life : " + wizard.getCurrentHealth() + "/" + wizard.getTotalHealth() + " and enemy : " + enemy.getCurrentHealth() + "/" + enemy.getTotalHealth(), wizard.getDrunk() );

        } while(wizard.getCurrentHealth() > 0 && enemy.getCurrentHealth() > 0);
        //TODO end of the battle
        if (wizard.getCurrentHealth() > 0){
            System.out.println("\n\nYOU WIN !!! \n\n");
            return true;
        }
        else {
            System.out.println("\n\nPaul Nuls\n\n");
            return false;
        }
    }

    public void skippingSchool(){
        this.outputManager.displayMessage("You decided to skip school.", this.wizard.getDrunk());
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just found a new potion", this.wizard.getDrunk());
            List<Potion> potions = this.wizard.getPotions();
            potions.add(new Potion("super potion", "Potion to get hp", 3, "health", 30));
            this.wizard.setPotions(potions);
            this.outputManager.printAvailablePotions(this.wizard.getPotions(), this.wizard.getDrunk());
        }
        if (randomProbability(10)){
            this.outputManager.displayMessage("What a lucky day, you just learned a new spell", this.wizard.getDrunk());
            List<Spell> knownSpells = this.wizard.getKnownSpells();
            knownSpells.add(new Spell("super forbidden spell", 20, 100));
            this.wizard.setKnownSpells(knownSpells);
            this.outputManager.printKnownSpells(this.wizard.getKnownSpells(), this.wizard.getDrunk());
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
        outputManager.displayMessage("You drunk the potion", wizard);
        int chance = 5;
        switch (potion.getType()){
            case "health":
                double health = wizard.getCurrentHealth() + (potion.getPoint() * wizard.getPotionEfficiency());
                int totalHealth = wizard.getTotalHealth();
                double heal = Math.min(health, totalHealth);
                if (wizard.randomProbability(10)){
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

        if (wizard.randomProbability(chance)){
            outputManager.displayMessage("HUHOOO there was super alcohol in your super potion, gloups....", wizard);
            wizard.setDrunk(wizard.getDrunk() + 3);
        }
        if (wizard.randomProbability(chance * 3)){
            wizard.setNowPet(true);
            outputManager.displayMessage("Bahahhah you just became a pet lol and you are " + wizard.getPet(), wizard);
        }
        outputManager.displayMessage("\nYour current health is : " + wizard.getCurrentHealth() + "/" + wizard.getTotalHealth(),wizard);
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


}
