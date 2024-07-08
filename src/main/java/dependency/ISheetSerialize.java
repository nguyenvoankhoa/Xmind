package dependency;

import sheet.Sheet;
import file.IOMessage;

public interface ISheetSerialize {
    IOMessage saveMindMap(Sheet sheet, String filepath);
}
