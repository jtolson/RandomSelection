package com.tci.utilities

import org.springframework.dao.DataIntegrityViolationException

class SelectionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [selectionInstanceList: Selection.list(params), selectionInstanceTotal: Selection.count()]
    }

    def create() {
        [selectionInstance: new Selection(params)]
    }

    def save() {
        def selectionInstance = new Selection(params)
        if (!selectionInstance.save(flush: true)) {
            render(view: "create", model: [selectionInstance: selectionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'selection.label', default: 'Selection'), selectionInstance.id])
        redirect(action: "show", id: selectionInstance.id)
    }

    def show() {
        def selectionInstance = Selection.get(params.id)
        if (!selectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'selection.label', default: 'Selection'), params.id])
            redirect(action: "list")
            return
        }

        [selectionInstance: selectionInstance]
    }

    def edit() {
        def selectionInstance = Selection.get(params.id)
        if (!selectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'selection.label', default: 'Selection'), params.id])
            redirect(action: "list")
            return
        }

        [selectionInstance: selectionInstance]
    }

    def update() {
        def selectionInstance = Selection.get(params.id)
        if (!selectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'selection.label', default: 'Selection'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (selectionInstance.version > version) {
                selectionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'selection.label', default: 'Selection')] as Object[],
                        "Another user has updated this Selection while you were editing")
                render(view: "edit", model: [selectionInstance: selectionInstance])
                return
            }
        }

        selectionInstance.properties = params

        if (!selectionInstance.save(flush: true)) {
            render(view: "edit", model: [selectionInstance: selectionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'selection.label', default: 'Selection'), selectionInstance.id])
        redirect(action: "show", id: selectionInstance.id)
    }

    def delete() {
        def selectionInstance = Selection.get(params.id)
        if (!selectionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'selection.label', default: 'Selection'), params.id])
            redirect(action: "list")
            return
        }

        try {
            selectionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'selection.label', default: 'Selection'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'selection.label', default: 'Selection'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
