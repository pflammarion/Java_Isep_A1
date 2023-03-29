import com.isep.harrypotter.controller.CharacterController;
import com.isep.harrypotter.controller.PotionController;
import com.isep.harrypotter.controller.SpellController;
import com.isep.harrypotter.model.characters.Enemy;
import com.isep.harrypotter.view.InputParser;
import com.isep.harrypotter.view.OutputManager;
import org.junit.Before;
import org.junit.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CharacterControllerTest {

    private CharacterController characterController;

    @Before
    public void setUp() {
        InputParser inputParser = new TestInputParser();
        OutputManager outputManager = new TestOutputManager();
        SpellController spellController = new SpellController(inputParser, outputManager);
        PotionController potionController = new PotionController(inputParser, outputManager);
        this.characterController = new CharacterController(inputParser, outputManager, spellController, potionController);
    }

    @Test
    public void testInitWizard() {
        // Call the initWizard() method
        characterController.initWizard();

        // Assert that the wizard's house is not null
        assertNotNull(characterController.getWizard().getHouse());

        // Assert that the wizard's pet is not null
        assertNotNull(characterController.getWizard().getPet());

        // Assert that the wizard's wand is not null
        assertNotNull(characterController.getWizard().getWand());
    }

    @Test
    public void testBattleEnemy() {
        Enemy enemy = new Enemy(100, 100, 10, 10, 0.2, "The Insatiable Spider");
        assertFalse(characterController.battleEnemy(enemy));
    }

    @Test
    public void testSkippingSchool() {
        assertTrue(characterController.skippingSchool());
    }

}
