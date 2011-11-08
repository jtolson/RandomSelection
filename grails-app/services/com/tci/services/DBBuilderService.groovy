package com.tci.services

import com.tci.utilities.TableDDLBuilder

class DBBuilderService {


    def ProcessXLSFile(def filename, def sql)  {
        // "/Users/jtolson/Desktop/Transfers 05072011-06142011.xls"
        def t = new TableDDLBuilder(filename)
        def createTableDLL =  t.getTableDDL(filename)

        sql.execute(createTableDLL.toString())
        t.getSpreadSheetValues(sql)

        return true
    }
}
