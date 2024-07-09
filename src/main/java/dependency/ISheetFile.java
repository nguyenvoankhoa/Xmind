package dependency;

import sheet.Sheet;
import file.IOMessage;

public interface ISheetFile {
    IOMessage saveMindMap(Sheet sheet, String filepath);
}
