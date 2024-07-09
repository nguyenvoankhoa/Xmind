package sheet;

import dependency.ISheetFile;
import file.IOMessage;
import file.IOStatus;

public class SheetPDFFile implements ISheetFile {
    @Override
    public IOMessage saveMindMap(Sheet sheet, String filepath) {
        return new IOMessage(IOStatus.SUCCESS, "Save to PDF success");
    }
}
