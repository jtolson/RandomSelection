import com.tci.utilities.Staging
import com.tci.utilities.Selection


class BootStrap {

    def init = { servletContext ->
        100.times {  i ->
            new Staging(
                    customerId: i,
                    studentName: "jack${i}",
                    studentNumber: i*5,
                    customerDataFileName: "excelfile.xls"
            ).save()

            new Selection(
                    selectionDate: new Date(),
                    selectionTypeCode: "T"
            ).save()
        }


    }
    def destroy = {
    }
}
