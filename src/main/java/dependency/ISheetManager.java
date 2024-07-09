package dependency;

import sheet.Sheet;

import java.io.IOException;
import java.util.List;

public interface ISheetManager {
    List<Sheet> getSheets();
    Sheet createDefaultSheet();
    Sheet duplicateSheet(Sheet template);
}
