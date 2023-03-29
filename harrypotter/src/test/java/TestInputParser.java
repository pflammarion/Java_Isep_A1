import com.isep.harrypotter.model.characters.Wizard;
import com.isep.harrypotter.view.InputParser;

public class TestInputParser implements InputParser {
    @Override
    public int getInt(String messageWhenMismatch) {
        return 0;
    }

    @Override
    public String getString(Wizard wizard) {
        return null;
    }

    // Implement the other methods of InputParser interface as needed
}
