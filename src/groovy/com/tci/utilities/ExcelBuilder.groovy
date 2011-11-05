
/**
 * Created by IntelliJ IDEA.
 * User: jtolson
 * Date: 11/4/11
 * Time: 12:05 PM
 * To change this template use File | Settings | File Templates.
 */


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tci.utilities

import org.apache.poi.hssf.usermodel.HSSFCell
import org.apache.poi.hssf.usermodel.HSSFDateUtil
import org.apache.poi.hssf.usermodel.HSSFRow
import org.apache.poi.hssf.usermodel.HSSFWorkbook

/**
 * Groovy Builder that extracts data from
 * Microsoft Excel spreadsheets.
 * @author Goran Ehrsson
 */
class ExcelBuilder {

    def workbook
    def labels
    def row

    ExcelBuilder(String fileName) {
        HSSFRow.metaClass.getAt = {int idx ->
            def cell = delegate.getCell(idx)
            if(! cell) {
                return null
            }
            def value
            switch(cell.cellType) {
                case HSSFCell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)) {
                    value = cell.dateCellValue
                } else {
                    value = cell.numericCellValue
                }
                break
                case HSSFCell.CELL_TYPE_BOOLEAN:
                value = cell.booleanCellValue
                break
                default:
                value = cell.stringCellValue
                break
            }
            return value
        }

        new File(fileName).withInputStream{is->
            workbook = new HSSFWorkbook(is)
        }
    }

    def getSheet(idx) {
        def sheet
        if(! idx) idx = 0
        if(idx instanceof Number) {
            sheet = workbook.getSheetAt(idx)
        } else if(idx ==~ /^\d+$/) {
            sheet = workbook.getSheetAt(Integer.valueOf(idx))
        } else {
            sheet = workbook.getSheet(idx)
        }
        return sheet
    }

    def cell(idx) {
        if(labels && (idx instanceof String)) {
            idx = labels.indexOf(idx.toLowerCase())
        }
        return row[idx]
    }

    def propertyMissing(String name) {
        cell(name)
    }


}