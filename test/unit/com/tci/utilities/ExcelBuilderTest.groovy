package com.tci.utilities;

import com.tci.utilities.*
import org.junit.After
import org.junit.Before

/**
 * Created by IntelliJ IDEA.
 * User: jtolson
 * Date: 11/5/11
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExcelBuilderTest {
    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    public void testDDL()
    {
        ExcelBuilder  a = new ExcelBuilder("/Users/jtolson/ExampleFile.xls")

        def t = new TableDDLBuilder("/Users/jtolson/Desktop/StudentTransfer2011Results.xls")
        t.getSpreadSheetValues()
    }
}
