package sheet;

import dependency.ISheetFile;
import file.IOMessage;
import file.IOStatus;
public class SheetWordFile implements ISheetFile {
    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to Word success");
    }
}
