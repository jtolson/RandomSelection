
import grails.test.GrailsUnitTestCase
import  com.tci.utilities.*





class ExcelReaderUnitTestTests extends GrailsUnitTestCase {
    protected void setUp() {
       
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateTableDllBuilder() {

        TableDDLBuilder tableDllBuilder = new TableDDLBuilder("/Users/jtolson/Desktop/exportexcel.xls")
        assert tableDllBuilder.getTableDDL("/Users/jtolson/Desktop/exportexcel.xls").length() > 0
    }
}
