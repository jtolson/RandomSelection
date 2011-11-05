
/**
 * Created by IntelliJ IDEA.
 * User: jtolson
 * Date: 11/4/11
 * Time: 11:03 PM
 * To change this template use File | Settings | File Templates.
 */
package com.tci.utilities

import org.apache.poi.hssf.usermodel.HSSFSheet
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFDateUtil
import org.apache.poi.hssf.usermodel.HSSFWorkbook



class TableDDLBuilder {
    def inputStream
    def workbook
    def sheet
    def header
    def theFile
    def cellType = []
    def fieldName = []

    TableDDLBuilder(String file)
    {
        theFile = new File(file)
        inputStream = new FileInputStream(theFile)

        workbook = new HSSFWorkbook(inputStream)
        sheet = workbook.getSheetAt(0)
    }

    def getSpreadSheetValues(String file)
    {
//        def theFile = new File(file)
//        inputStream = new FileInputStream(theFile)
//
//        workbook = new HSSFWorkbook(inputStream)
//        sheet = workbook.getSheetAt(0)
        //header = sheet.getRow(0)



        def cellValue = []
        def vRow = []
            def isFirstRow = true

            sheet.each() { row ->
            row.each() { cell ->
                cell.each() {
                switch (it.getCellType()) {
                    case it.CELL_TYPE_NUMERIC:
                       if(HSSFDateUtil.isCellDateFormatted(it))
                       {
                         // cellValue.add(it.getDateCellValue())
                           print(it.getDateCellValue())
                       }
                       else
                       {
                         // cellValue.add(it.getNumericCellValue())
                          print(it.getNumericCellValue())
                       }
                    break;
                    case it.CELL_TYPE_STRING:
                        //cellValue.add(it.getStringCellValue())
                        print(it.getStringCellValue())
                    break;
                }
            }

         }

     println ""
           if (isFirstRow)
           {
               isFirstRow = false
              println("========================================== First Row ==============================================")
           }
           else
           {
               println("========================================== Row ==============================================")

           }


            }

    }


    def getTableDDL(String file)
    {

        //def cellType = []
        //def cellName = []

        //def theFile = new File(file)
        //inputStream = new FileInputStream(theFile)

        //workbook = new HSSFWorkbook(inputStream)
        sheet = workbook.getSheetAt(0)
        header = sheet.getRow(0)
//


        header.each() {
            //            if (it.getCellType() == it.CELL_TYPE_NUMERIC)
            //               println("${it.getCellType}           ${it.getNumericCellValue()};")

            if (it.getCellType() == it.CELL_TYPE_STRING ){
                // println("${it.getCellType()}      ${it.getStringCellValue()};")
                def value = it.getStringCellValue();
                def replaceValue = value.replaceAll(" ","_")
                def upperCase = replaceValue.toUpperCase()
                // cellName.add(it.getStringCellValue().replaceAll(" ","_").toUpperCase());


                //println "upperCase: ${upperCase}"

                fieldName.add(upperCase);
            }
        }


        def firstRow = sheet.getRow(1)
        firstRow.each() {
            if (it.getCellType() == it.CELL_TYPE_NUMERIC)
            if(HSSFDateUtil.isCellDateFormatted(it))
            cellType.add(99)

            cellType.add(it.getCellType())
        }


        //CELL_TYPE_BLANK = 3
        //CELL_TYPE_STRING = 1
        //CELL_TYPE_NUMERIC = 0

        String colAndVarcharType = "";
        def fieldType = ""
        int count = 0
        fieldName.each() {


            switch(cellType[count]) {
                case 1:
                fieldType = "VARCHAR(100)"
                break
                case 0:
                fieldType = "NUMERIC"
                break
                case 99:
                fieldType = "DATE"
                break
                case 3:
                fieldType = "VARCHAR(100)"
                break
            }
            count = count + 1
            colAndVarcharType = colAndVarcharType + it + " " + fieldType + ","

        }

        def tableName = "STAGING_${String.format('%tY%<tm%<td_%<tN', new Date())}"

        def createDDL =  ""
        if (colAndVarcharType.length() > 0)
            createDDL = "CREATE TABLE ${tableName} (${colAndVarcharType.substring(0,colAndVarcharType.length()-1)})"

        return createDDL
    }

}