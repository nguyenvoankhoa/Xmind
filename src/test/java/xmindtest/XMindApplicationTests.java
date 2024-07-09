package xmindtest;

import content.XMind;
import dependency.ISheetManager;
import sheet.*;
import dependency.ISheetFile;
import content.Topic;
import content.Root;
import file.IOMessage;
import file.IOStatus;
import relationship.Relationship;
import setting.Structure;
import setting.ViewType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class XMindApplicationTests {
    XMind xMind;
    Sheet sheet;
    Root root;

    @BeforeEach
    public void setUp() throws IOException {
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
    void testDuplicateSheet() throws IOException {
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
        IOMessage message = sheet.getISheetFile().saveMindMap(sheet, "test.pdf");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportPNG() {
        ISheetFile sheetFile = new SheetPNGFile();
        sheet.setISheetFile(sheetFile);
        IOMessage message = sheet.getISheetFile().saveMindMap(sheet, "test.png");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportWord() {
        ISheetFile sheetFile = new SheetWordFile();
        sheet.setISheetFile(sheetFile);
        IOMessage message = sheet.getISheetFile().saveMindMap(sheet, "test.docx");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportXMind() {
        ISheetFile sheetFile = new SheetXMindFile();
        sheet.setISheetFile(sheetFile);
        IOMessage message = sheet.getISheetFile().saveMindMap(sheet, "test.xmind");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testOpenXMind() {
        SheetXMindFile sheetFile = new SheetXMindFile();
        sheet.setISheetFile(sheetFile);
        IOMessage message = ((SheetXMindFile) sheet.getISheetFile()).openMindMap("test.xmind");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
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
        long afterRemoveSize = root.removeChild(topic.getId()).stream().count();
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
        topic.changeParent("1", sheet.getRoot());
        assertNotEquals(null, topic.getParent());
    }

    @Test
    void testTopicBecomeFloat() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "abc", "Topic 1");
        root.addChild(topic);
        topic.changeParent("", sheet.getRoot());
        assertTrue(topic.isFloating());
    }


    @Test
    void testFloatContentAddParent() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "Hello", "New Topic");
        topic.setFloating(true);
        topic.changeParent("Root", sheet.getRoot());
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
    void testRemoveRelationship() throws IOException {
        Topic src = new Topic(sheet.getPropertiesLoader(), "abc", "Node 1");
        Topic target = new Topic(sheet.getPropertiesLoader(), "def", "Node 2");
        root.addChild(src);
        root.addChild(target);
        long relaBefore = sheet.getIRelationshipManager().addRelationship(sheet.getPropertiesLoader(), src, target).stream().count();
        Relationship relationship = sheet.getIRelationshipManager().getRelationships().get(0);
        sheet.getIRelationshipManager().removeRelationship(relationship);
        long relaAfter = sheet.getIRelationshipManager().getRelationships().stream().count();
        assertEquals(relaBefore - 1, relaAfter);
    }

    @Test
    void testAddRelationship() throws IOException {
        Topic src = new Topic(sheet.getPropertiesLoader(), "abc", "Node 1");
        Topic target = new Topic(sheet.getPropertiesLoader(), "def", "Node 2");
        root.addChild(src);
        root.addChild(target);
        sheet.getIRelationshipManager().addRelationship(sheet.getPropertiesLoader(), src, target);
        assertEquals(1, sheet.getIRelationshipManager().getRelationships().stream().count());
    }

    @Test
    void testChangeTargetNodeRelationship() throws IOException {
        Topic src = new Topic(sheet.getPropertiesLoader(), "abc", "Node 1");
        Topic target = new Topic(sheet.getPropertiesLoader(), "def", "Node 2");
        root.addChild(src);
        root.addChild(target);
        sheet.getIRelationshipManager().addRelationship(sheet.getPropertiesLoader(), src, target);
        sheet.getIRelationshipManager().getRelationships().get(0).changeTargetRelationship(root);
        assertEquals(sheet.getIRelationshipManager().getRelationships().get(0).getTargetNode(), root);
    }

    @Test
    void testStructure() {
        root.setStructure(Structure.FISH_BONE);
        assertEquals(Structure.FISH_BONE, root.getStructure());
    }


}
