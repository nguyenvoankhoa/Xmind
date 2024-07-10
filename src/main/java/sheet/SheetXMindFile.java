package sheet;

import dependency.ISheetFile;
import file.FileResponse;
import file.FileStatus;

public class SheetXMindFile implements ISheetFile {
    @Override
    public FileResponse saveMindMap(Sheet sheet, String filepath) {
        return new FileResponse(FileStatus.SUCCESS, "Save to XMind success");
    }

    public FileResponse openMindMap(String filepath) {
        return new FileResponse(FileStatus.SUCCESS, "Open success " + filepath);
    }
}
