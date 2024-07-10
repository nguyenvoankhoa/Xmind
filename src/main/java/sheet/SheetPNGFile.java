package sheet;

import dependency.ISheetFile;
import file.FileResponse;
import file.FileStatus;

public class SheetPNGFile implements ISheetFile {
    @Override
    public FileResponse saveMindMap(Sheet sheet, String filepath) {
        return new FileResponse(FileStatus.SUCCESS, "Save to PNG success");
    }
}
