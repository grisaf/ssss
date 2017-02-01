package org.exercise.ssss.model.controller;

import java.io.Serializable;
import java.util.List;

import org.exercise.ssss.model.service.SsssService;
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
        return ResponseEntity.ok(service.get(id));
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<T> add(@RequestBody T entity) {
        return ResponseEntity.ok(service.add(entity));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<T> update(@PathVariable ID id, @RequestBody T entity) {
        return ResponseEntity.ok(service.update(id, entity));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable ID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
