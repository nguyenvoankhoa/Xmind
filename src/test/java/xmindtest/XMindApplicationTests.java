package xmindtest;

import content.XMind;
import dependency.ISheetManager;
import sheet.*;
import dependency.ISheetSerialize;
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
    void testNewXMind() {
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
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.pdf");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportPNG() {
        ISheetSerialize sheetSerialize = new SheetPNGSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.png");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportWord() {
        ISheetSerialize sheetSerialize = new SheetWordSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.docx");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testExportXmind() {
        ISheetSerialize sheetSerialize = new SheetXmindSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = sheet.getISheetSerialize().saveMindMap(sheet, "test.xmind");
        assertEquals(IOStatus.SUCCESS, message.getExportStatus());
    }

    @Test
    void testOpenXmind() {
        SheetXmindSerializer sheetSerialize = new SheetXmindSerializer();
        sheet.setISheetSerialize(sheetSerialize);
        IOMessage message = ((SheetXmindSerializer) sheet.getISheetSerialize()).openMindMap("test.xmind");
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
        root.addChild(topic);
        long beforeRemoveSize = root.getChildren().stream().count();
        root.removeChild(topic.getId());
        long afterRemoveSize = root.getChildren().stream().count();
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
    void testTopicMove() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "abc", "Topic 1");
        Topic topic1 = new Topic(sheet.getPropertiesLoader(), "def", "Topic 2");
        root.addChild(topic);
        root.addChild(topic1);
        topic.changeParent("def", sheet.getRoot());
        assertEquals(topic.getParent().getId(), topic1.getId());
    }


    @Test
    void testFloatContentBecomeLeaf() {
        Topic topic = new Topic(sheet.getPropertiesLoader(), "Hello", "New Topic");
        topic.setFloating(true);
        topic.changeParent("root", sheet.getRoot());
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
        sheet.getIRelationshipManager().addRelationship(sheet.getPropertiesLoader(), src, target);
        Relationship relationship = sheet.getIRelationshipManager().getRelationships().get(0);
        int relaBefore = sheet.getIRelationshipManager().getRelationships().size();
        sheet.getIRelationshipManager().removeRelationship(relationship);
        int relaAfter = sheet.getIRelationshipManager().getRelationships().size();
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
