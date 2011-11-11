package com.tci.utilities

import org.springframework.dao.DataIntegrityViolationException

class StagingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [stagingInstanceList: Staging.list(params), stagingInstanceTotal: Staging.count()]
    }

    def create() {
        [stagingInstance: new Staging(params)]
    }

    def save() {
        def stagingInstance = new Staging(params)
        if (!stagingInstance.save(flush: true)) {
            render(view: "create", model: [stagingInstance: stagingInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'staging.label', default: 'Staging'), stagingInstance.id])
        redirect(action: "show", id: stagingInstance.id)
    }

    def show() {
        def stagingInstance = Staging.get(params.id)
        if (!stagingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'staging.label', default: 'Staging'), params.id])
            redirect(action: "list")
            return
        }

        [stagingInstance: stagingInstance]
    }

    def edit() {
        def stagingInstance = Staging.get(params.id)
        if (!stagingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'staging.label', default: 'Staging'), params.id])
            redirect(action: "list")
            return
        }

        [stagingInstance: stagingInstance]
    }

    def update() {
        def stagingInstance = Staging.get(params.id)
        if (!stagingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'staging.label', default: 'Staging'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (stagingInstance.version > version) {
                stagingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'staging.label', default: 'Staging')] as Object[],
                          "Another user has updated this Staging while you were editing")
                render(view: "edit", model: [stagingInstance: stagingInstance])
                return
            }
        }

        stagingInstance.properties = params

        if (!stagingInstance.save(flush: true)) {
            render(view: "edit", model: [stagingInstance: stagingInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'staging.label', default: 'Staging'), stagingInstance.id])
        redirect(action: "show", id: stagingInstance.id)
    }

    def delete() {
        def stagingInstance = Staging.get(params.id)
        if (!stagingInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'staging.label', default: 'Staging'), params.id])
            redirect(action: "list")
            return
        }

        try {
            stagingInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'staging.label', default: 'Staging'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'staging.label', default: 'Staging'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
