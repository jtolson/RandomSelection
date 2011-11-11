package com.tci.utilities



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(SelectionController)
@Mock(Selection)
class SelectionControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/selection/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.selectionInstanceList.size() == 0
        assert model.selectionInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.selectionInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.selectionInstance != null
        assert view == '/selection/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/selection/show/1'
        assert controller.flash.message != null
        assert Selection.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/selection/list'


        populateValidParams(params)
        def selection = new Selection(params)

        assert selection.save() != null

        params.id = selection.id

        def model = controller.show()

        assert model.selectionInstance == selection
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/selection/list'


        populateValidParams(params)
        def selection = new Selection(params)

        assert selection.save() != null

        params.id = selection.id

        def model = controller.edit()

        assert model.selectionInstance == selection
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/selection/list'

        response.reset()


        populateValidParams(params)
        def selection = new Selection(params)

        assert selection.save() != null

        // test invalid parameters in update
        params.id = selection.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/selection/edit"
        assert model.selectionInstance != null

        selection.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/selection/show/$selection.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        selection.clearErrors()

        populateValidParams(params)
        params.id = selection.id
        params.version = -1
        controller.update()

        assert view == "/selection/edit"
        assert model.selectionInstance != null
        assert model.selectionInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/selection/list'

        response.reset()

        populateValidParams(params)
        def selection = new Selection(params)

        assert selection.save() != null
        assert Selection.count() == 1

        params.id = selection.id

        controller.delete()

        assert Selection.count() == 0
        assert Selection.get(selection.id) == null
        assert response.redirectedUrl == '/selection/list'
    }
}
