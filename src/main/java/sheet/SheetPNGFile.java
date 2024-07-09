package sheet;

import dependency.ISheetFile;
import file.IOMessage;
import file.IOStatus;

public class SheetPNGFile implements ISheetFile {
    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to PNG success");
    }
}
