package com.isep.harrypotter.model.characters;

import com.isep.harrypotter.model.Potion;
import com.isep.harrypotter.model.Stuff;
import com.isep.harrypotter.model.others.Core;
import com.isep.harrypotter.model.others.House;
import com.isep.harrypotter.model.others.Pet;
import com.isep.harrypotter.model.others.Wand;
import com.isep.harrypotter.model.spells.AbstractSpell;
import com.isep.harrypotter.model.spells.Spell;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class WizardTest {

    Wizard wizard;

    @BeforeEach
    void init() {
        wizard = new Wizard(Pet.CAT, new Wand(Core.DRAGON_HEARTSTRING, 20), House.GRYFFINDOR, new ArrayList<>(), new HashMap<>(), "Harry", "Potter", 1, false, 4, new Random(), new ArrayList<>());
        wizard.setTotalHealth(100);
        wizard.setCurrentHealth(100);
        wizard.setDefense(20);
        wizard.setDamage(10);
        wizard.setAccuracy(80);
    }

    @Test
    void takeDamage() {
        double currentHealth = wizard.getCurrentHealth();
        wizard.takeDamage(20);
        assertEquals(currentHealth - 20, wizard.getCurrentHealth());
    }

    @Test
    void getTotalHealth() {
        assertEquals(100, wizard.getTotalHealth());
    }

    @Test
    void getCurrentHealth() {
        assertEquals(100, wizard.getCurrentHealth());
    }

    @Test
    void getDefense() {
        assertEquals(20, wizard.getDefense());
    }

    @Test
    void getDamage() {
        assertEquals(10, wizard.getDamage());
    }

    @Test
    void getAccuracy() {
        assertEquals(80, wizard.getAccuracy());
    }

    @Test
    void setTotalHealth() {
        wizard.setTotalHealth(150);
        assertEquals(150, wizard.getTotalHealth());
    }

    @Test
    void setCurrentHealth() {
        wizard.setCurrentHealth(50);
        assertEquals(50, wizard.getCurrentHealth());
    }

    @Test
    void setDefense() {
        wizard.setDefense(30);
        assertEquals(30, wizard.getDefense());
    }

    @Test
    void setDamage() {
        wizard.setDamage(15);
        assertEquals(15, wizard.getDamage());
    }

    @Test
    void setAccuracy() {
        wizard.setAccuracy(90);
        assertEquals(90, wizard.getAccuracy());
    }

    @Test
    void getPet() {
        assertEquals(Pet.CAT, wizard.getPet());
    }

    @Test
    void getWand() {
        assertEquals(Core.DRAGON_HEARTSTRING, wizard.getWand().getCore());
        assertEquals(20, wizard.getWand().getSize());
    }

    @Test
    void getHouse() {
        assertEquals(House.GRYFFINDOR, wizard.getHouse());
    }

    @Test
    void getKnownSpells() {
        assertTrue(wizard.getKnownSpells().isEmpty());
    }

    @Test
    void getPotions() {
        assertTrue(wizard.getPotions().isEmpty());
    }

    @Test
    void getFirstname() {
        assertEquals("Harry", wizard.getFirstname());
    }

    @Test
    void getLastname() {
        assertEquals("Potter", wizard.getLastname());
    }

    @Test
    void getPotionEfficiency() {
        assertEquals(1.0, wizard.getPotionEfficiency());
    }

    @Test
    void isNowPet() {
        assertFalse(wizard.isNowPet());
    }

    @Test
    void getDrunk() {
        assertEquals(4, wizard.getDrunk());
    }

    @Test
    void getRandom() {
        assertNotNull(wizard.getRandom());
    }

    @Test
    void getInventory() {
        assertTrue(wizard.getInventory().isEmpty());
    }

    @Test
    void setPet() {
        wizard.setPet(Pet.CAT);
        assertEquals(Pet.CAT, wizard.getPet());
    }

    @Test
    void setWand() {
        Wand wand = new Wand(Core.PHOENIX_FEATHER, 40);
        wizard.setWand(wand);
        assertEquals(wand, wizard.getWand());
    }

    @Test
    void setHouse() {
        wizard.setHouse(House.SLYTHERIN);
        assertEquals(House.SLYTHERIN, wizard.getHouse());
    }
    @Test
    void setKnownSpells() {
        List<AbstractSpell> spells = new ArrayList<>();
        spells.add(new Spell("Wingardium Leviosa", "A charm that levitates objects", 50, 10, 1));
        spells.add(new Spell("Lumos", "A charm that creates light at the tip of the wand", 10, 0, 1));
        spells.add(new Spell("Alohomora", "A charm that unlocks and opens doors", 30, 0, 2));
        wizard.setKnownSpells(spells);
        assertEquals(spells, wizard.getKnownSpells());
    }

    @Test
    void setPotions() {
        Map<Potion, Integer> potions = new HashMap<>();
        potions.put(new Potion("Elixir of Life", "Doubles the drinker's current health", "health", 0, 10, 2), 1);
        potions.put(new Potion("Healing Salve", "Restores a moderate amount of the drinker's health", "health", 50, 2, 1), 1);
        potions.put(new Potion("Rejuvenation Tonic", "Gradually restores the drinker's health over time", "health", 20, 3, 3), 3);
        wizard.setPotions(potions);
        assertEquals(potions, wizard.getPotions());
    }

    @Test
    void setFirstname() {
        wizard.setFirstname("Harry");
        assertEquals("Harry", wizard.getFirstname());
    }

    @Test
    void setLastname() {
        wizard.setLastname("Potter");
        assertEquals("Potter", wizard.getLastname());
    }

    @Test
    void setPotionEfficiency() {
        wizard.setPotionEfficiency(0.75);
        assertEquals(0.75, wizard.getPotionEfficiency());
    }

    @Test
    void setNowPet() {
        wizard.setNowPet(true);
        assertTrue(wizard.isNowPet());
    }

    @Test
    void setDrunk() {
        int drunkDays = 3;
        wizard.setDrunk(drunkDays);
        assertEquals(drunkDays, wizard.getDrunk());
    }

    @Test
    void setRandom() {
        Random random = new Random(12345);
        wizard.setRandom(random);
        assertEquals(random, wizard.getRandom());
    }

    @Test
    void setInventory() {
        List<Stuff> inventory = new ArrayList<>();
        inventory.add(new Stuff("Wand", "To cast spell"));
        inventory.add(new Stuff("Fireworks", "To help you in difficult situations"));
        wizard.setInventory(inventory);
        assertEquals(inventory, wizard.getInventory());
    }

}