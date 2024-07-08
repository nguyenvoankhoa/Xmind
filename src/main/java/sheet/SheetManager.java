package sheet;

import dependency.ISheetManager;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SheetManager implements ISheetManager {
    List<Sheet> sheets;

    public SheetManager() throws IOException {
        this.sheets = new ArrayList<>();
        createDefaultSheet();
    }

    @Override
    public Sheet createDefaultSheet() throws IOException {
        Sheet sheet = new Sheet();
        sheets.add(sheet);
        return sheet;
    }

    @Override
    public Sheet duplicateSheet(Sheet template) throws IOException {
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
    }


}
