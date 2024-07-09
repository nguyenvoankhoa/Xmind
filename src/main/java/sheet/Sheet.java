package sheet;

import content.Root;
import dependency.ISheetFile;
import dependency.IRelationshipManager;
import relationship.RelationshipManager;
import setting.PropertiesLoader;
import setting.ViewType;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public class Sheet {

    private String theme;
    private String background;
    private String globalFont;
    private int zoomLevel;
    private String title;
    private ViewType viewType;
    private Root root;
    private IRelationshipManager iRelationshipManager;
    private ISheetFile iSheetFile;
    private PropertiesLoader propertiesLoader;

    public Sheet() throws IOException {
        this.propertiesLoader = new PropertiesLoader("application.properties");
        this.root = new Root(propertiesLoader);
        this.title = propertiesLoader.getProperty("sheet.title");
        this.theme = propertiesLoader.getProperty("sheet.theme");
        this.globalFont = propertiesLoader.getProperty("sheet.globalFont");
        this.background = propertiesLoader.getProperty("sheet.background");
        this.zoomLevel = Integer.parseInt(propertiesLoader.getProperty("sheet.zoomLevel", "0"));
        this.viewType = ViewType.THREE_BY_FOUR;
        this.iRelationshipManager = new RelationshipManager();
        this.iSheetFile = new SheetXMindFile();
    }

}
