package org.exercise.ssss.controller;

import java.io.Serializable;
import java.util.List;

import org.exercise.ssss.service.SsssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public abstract class SsssController<T, ID extends Serializable, R extends JpaRepository<T, ID>, S extends SsssService<T, ID, R>> {

    @Autowired
    private S service;

    public S getService() {
        return service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<T>> list(@RequestParam(required = false) String keyword) {
        return ResponseEntity.ok(service.list(keyword));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<T> get(@PathVariable ID id) {
        T entity = service.get(id);
        if (entity == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(entity);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<T> add(@RequestBody T entity) {
        if (!validateAdd(entity)) {
            ResponseEntity.badRequest();
        }
        T newEntity = service.add(entity);
        if (newEntity == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(newEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        if (!validateUpdate(entity)) {
            ResponseEntity.badRequest();
        }
        T updatedEntity = service.update(id, entity);
        if (updatedEntity == null) {
            ResponseEntity.notFound();
        }
        return ResponseEntity.ok(updatedEntity);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable ID id) {
        if (!validateDelete(id)) {
            ResponseEntity.badRequest();
        }
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    public boolean validateAdd(T entity) {
        return entity != null;
    }

    public boolean validateUpdate(T entity) {
        return entity != null;
    }

    public boolean validateDelete(ID id) {
        T entity = service.get(id);
        return entity != null;
    }

}
