package com.tci.utilities



import org.junit.*
import grails.test.mixin.*
import javax.servlet.http.HttpServletResponse

@TestFor(StagingController)
@Mock(Staging)
class StagingControllerTests {


    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/staging/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.stagingInstanceList.size() == 0
        assert model.stagingInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.stagingInstance != null
    }

    void testSave() {
        controller.save()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.save()

        assert model.stagingInstance != null
        assert view == '/staging/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/staging/show/1'
        assert controller.flash.message != null
        assert Staging.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/staging/list'


        populateValidParams(params)
        def staging = new Staging(params)

        assert staging.save() != null

        params.id = staging.id

        def model = controller.show()

        assert model.stagingInstance == staging
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/staging/list'


        populateValidParams(params)
        def staging = new Staging(params)

        assert staging.save() != null

        params.id = staging.id

        def model = controller.edit()

        assert model.stagingInstance == staging
    }

    void testUpdate() {

        controller.update()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/staging/list'

        response.reset()


        populateValidParams(params)
        def staging = new Staging(params)

        assert staging.save() != null

        // test invalid parameters in update
        params.id = staging.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/staging/edit"
        assert model.stagingInstance != null

        staging.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/staging/show/$staging.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        staging.clearErrors()

        populateValidParams(params)
        params.id = staging.id
        params.version = -1
        controller.update()

        assert view == "/staging/edit"
        assert model.stagingInstance != null
        assert model.stagingInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert response.status == HttpServletResponse.SC_METHOD_NOT_ALLOWED

        response.reset()
        request.method = 'POST'
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/staging/list'

        response.reset()

        populateValidParams(params)
        def staging = new Staging(params)

        assert staging.save() != null
        assert Staging.count() == 1

        params.id = staging.id

        controller.delete()

        assert Staging.count() == 0
        assert Staging.get(staging.id) == null
        assert response.redirectedUrl == '/staging/list'
    }
}
