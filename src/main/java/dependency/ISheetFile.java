package dependency;

import sheet.Sheet;
import file.FileResponse;

public interface ISheetFile {
    FileResponse saveMindMap(Sheet sheet, String filepath);
}
