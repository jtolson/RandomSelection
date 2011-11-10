package com.tci.utilities;

import com.tci.utilities.*
import org.junit.After
import org.junit.Before
import org.hibernate.Session
import org.hibernate.SessionFactory
import org.hibernate.cfg.Configuration
import org.springframework.orm.hibernate3.SessionFactoryUtils
import org.springframework.orm.hibernate3.SessionHolder
import org.springframework.transaction.support.TransactionSynchronizationManager
import org.hibernate.Query
import groovy.sql.Sql
import com.tci.services.DBBuilderService

/**
 * Created by IntelliJ IDEA.
 * User: jtolson
 * Date: 11/5/11
 * Time: 9:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExcelBuilderTest extends  GroovyTestCase{

      SessionFactory sessionFactory
      Session session
      def sql

    DBBuilderService dbBuilderService

    @Before
    public void setUp() throws Exception {
       super.setUp();
//       def Configuration cfg = new Configuration()
//            .setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver")
//            .setProperty("hibernate.connection.url", "jdbc:mysql://localhost/test")
//            .setProperty("hibernate.connection.username", "root")
//            .setProperty("hibernate.connection.password", "")
//
//
//
//       SessionFactory sessionFactory = cfg.buildSessionFactory()
//       session = sessionFactory.openSession();
       dbBuilderService = new DBBuilderService()
       sql = Sql.newInstance( 'jdbc:mysql://localhost/test', 'root',
                       '', 'com.mysql.jdbc.Driver' )

    }

    @After
    public void tearDown() throws Exception {
       // session.close()
    }

    public void testDDL()
    {
        //ExcelBuilder  a = new ExcelBuilder("/Users/jtolson/ExampleFile.xls")




//
//        def t = new TableDDLBuilder("/Users/jtolson/Desktop/Transfers 05072011-06142011.xls")
//        def createTableDLL =  t.getTableDDL("/Users/jtolson/Desktop/Transfers 05072011-06142011.xls")
//
//        println   createTableDLL



        //def query = session.createSQLQuery(createTableDLL);
//        sql.execute(createTableDLL.toString())
       // println "Query Result: ${query.executeUpdate()}"



//        t.getSpreadSheetValues(sql)

         // select distinct school_choice_3, next_grade_level from STAGING_20111110_218000000 where school_choice_3 != '' order by school_choice_3;

         dbBuilderService.ProcessXLSFile("/Users/jtolson/Desktop/Transfers 05072011-06142011.xls", sql)

    }
}
