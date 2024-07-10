package xmindtest;

import content.XMind;
import dependency.ISheetManager;
import sheet.*;
import dependency.ISheetFile;
import content.Topic;
import content.Root;
import file.FileResponse;
import file.FileStatus;
import relationship.Relationship;
import setting.Structure;
import setting.ViewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class XMindApplicationTests {
    XMind xMind;
    Sheet sheet;
    Root root;

    @BeforeEach
    public void setUp() {
        ISheetManager sheetManager = new SheetManager();
        xMind = new XMind(sheetManager);
        sheet = xMind.getSheetManager().getSheets().get(0);
        root = sheet.getRoot();
    }

    @Test
    void testCreateNew() {
        assertEquals(1, xMind.getSheetManager().getSheets().stream().count());
    }

    @Test
    void testSheetDefaultInit() {
        assertEquals(4, sheet.getRoot().getChildren().stream().count());
    }

    @Test
    void testDuplicateSheet() {
        Sheet duplicateSheet = xMind.getSheetManager().duplicateSheet(sheet);
        assertNotEquals(null, duplicateSheet);
    }

    @Test
    void testRenameSheet() {
        String newSheetName = "Test";
        sheet.setTitle(newSheetName);
        assertEquals(sheet.getTitle(), newSheetName);
    }

    @Test
    void testExportPDF() {
        ISheetFile sheetFile = new SheetPDFFile();
        sheet.setISheetFile(sheetFile);
        FileResponse message = sheet.getISheetFile().saveMindMap(sheet, "test.pdf");
        assertEquals(FileStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportPNG() {
        ISheetFile sheetFile = new SheetPNGFile();
        sheet.setISheetFile(sheetFile);
        FileResponse message = sheet.getISheetFile().saveMindMap(sheet, "test.png");
        assertEquals(FileStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportWord() {
        ISheetFile sheetFile = new SheetWordFile();
        sheet.setISheetFile(sheetFile);
        FileResponse message = sheet.getISheetFile().saveMindMap(sheet, "test.docx");
        assertEquals(FileStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportXMind() {
        ISheetFile sheetFile = new SheetXMindFile();
        sheet.setISheetFile(sheetFile);
        FileResponse message = sheet.getISheetFile().saveMindMap(sheet, "test.xmind");
        assertEquals(FileStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testOpenXMind() {
        SheetXMindFile sheetFile = new SheetXMindFile();
        sheet.setISheetFile(sheetFile);
        FileResponse message = ((SheetXMindFile) sheet.getISheetFile()).openMindMap("test.xmind");
        assertEquals(FileStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testAddChildren() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "abc", "New Topic");
        long beforeAddSize = root.getChildren().stream().count();
        root.addChild(topic);
        long afterAddSize = root.getChildren().stream().count();
        assertEquals(beforeAddSize + 1, afterAddSize);
    }

    @Test
    void testRemoveChildren() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "abc", "Leaf 1");
        long beforeRemoveSize = root.addChild(topic).stream().count();
        long afterRemoveSize = root.removeChild(topic).stream().count();
        assertEquals(beforeRemoveSize - 1, afterRemoveSize);
    }

    @Test
    void testCollapse() {
        root.collapse();
        assertFalse(root.isOpen());
    }

    @Test
    void testExpand() {
        root.expand();
        assertTrue(root.isOpen());
    }

    @Test
    void testClearText() {
        assertEquals("", root.clearText());
    }

    @Test
    void testTopicChangeParent() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "abc", "Topic 1");
        root.addChild(topic);
        topic.changeParent(root);
        assertNotEquals(null, topic.getParent());
    }

    @Test
    void testTopicBecomeFloat() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "abc", "Topic 1");
        root.addChild(topic);
        topic.changeToFloat(sheet.getIFloatingTopicManager());
        assertTrue(topic.isFloating());
    }


    @Test
    void testFloatContentAddParent() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "Hello", "New Topic");
        topic.setFloating(true);
        topic.changeParent(root);
        assertEquals(topic.getParent().getId(), root.getId());
    }


    @Test
    void testEditContent() {
        root.setContent("New Content");
        assertEquals("New Content", root.getContent());
    }

    @Test
    void testRemoveAll() {
        root.removeAll();
        assertNull(root.getChildren());
    }


    @Test
    void testAdjustZoomLevel() {
        sheet.setZoomLevel(100);
        assertEquals(100, sheet.getZoomLevel());
    }

    @Test
    void testAdjustViewport() {
        sheet.setViewType(ViewType.THREE_BY_FOUR);
        assertEquals(ViewType.THREE_BY_FOUR, sheet.getViewType());
    }


    @Test
    void testRemoveRelationship() {
        Topic src = new Topic(sheet.getPropertiesLoader(), "abc", "Topic 1");
        Topic target = new Topic(sheet.getPropertiesLoader(), "def", "Topic 2");
        long relaBefore = sheet.getIRelationshipManager().addRelationship(sheet.getPropertiesLoader(), src, target).stream().count();
        Relationship relationship = sheet.getIRelationshipManager().getRelationships().get(0);
        sheet.getIRelationshipManager().removeRelationship(relationship);
        long relaAfter = sheet.getIRelationshipManager().getRelationships().stream().count();
        assertEquals(relaBefore - 1, relaAfter);
    }

    @Test
    void testAddRelationship() {
        Topic src = new Topic(sheet.getPropertiesLoader(), "abc", "Topic 1");
        Topic target = new Topic(sheet.getPropertiesLoader(), "def", "Topic 2");
        sheet.getIRelationshipManager().addRelationship(sheet.getPropertiesLoader(), src, target);
        assertEquals(1, sheet.getIRelationshipManager().getRelationships().stream().count());
    }

    @Test
    void testChangeTargetNodeRelationship() {
        Topic src = new Topic(sheet.getPropertiesLoader(), "abc", "Topic 1");
        Topic newTarget = new Topic(sheet.getPropertiesLoader(), "def", "Topic 2");
        sheet.getIRelationshipManager().addRelationship(sheet.getPropertiesLoader(), src, root);
        sheet.getIRelationshipManager().getRelationships().get(0).changeTargetRelationship(newTarget);
        assertEquals(newTarget, sheet.getIRelationshipManager().getRelationships().get(0).getRela().get(src));
    }

    @Test
    void testStructure() {
        root.setStructure(Structure.FISH_BONE);
        assertEquals(Structure.FISH_BONE, root.getStructure());
    }

}
