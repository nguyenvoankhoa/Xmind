package sheet;

import dependency.ISheetManager;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
public class SheetManager implements ISheetManager {
    private static final Logger LOGGER = Logger.getLogger(SheetManager.class.getName());
    List<Sheet> sheets;


    public SheetManager() {
        this.sheets = new ArrayList<>();
        createDefaultSheet();
    }

    @Override
    public Sheet createDefaultSheet() {
        try {
            Sheet sheet = new Sheet();
            sheets.add(sheet);
            return sheet;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to create default sheet", e);
        }
        return null;
    }

    @Override
    public Sheet duplicateSheet(Sheet template) {
        try {
            Sheet newSheet = new Sheet();
            newSheet.setTheme(template.getTheme());
            newSheet.setBackground(template.getBackground());
            newSheet.setGlobalFont(template.getGlobalFont());
            newSheet.setZoomLevel(template.getZoomLevel());
            newSheet.setTitle(template.getTitle());
            newSheet.setViewType(template.getViewType());
            newSheet.setRoot(template.getRoot());
            sheets.add(newSheet);
            return newSheet;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to duplicate sheet", e);
        }
        return null;
    }


}
