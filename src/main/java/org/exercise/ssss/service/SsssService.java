package org.exercise.ssss.service;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

public abstract class SsssService<T, ID extends Serializable, R extends JpaRepository<T, ID>> {

    @Autowired
    private R repository;

    public R getRepository() {
        return repository;
    }

    public List<T> list() {
        return repository.findAll();
    }

    public List<T> list(String keyword) {
        if (keyword == null || StringUtils.isEmpty(keyword)) {
            return list();
        } else {
            return search(keyword);
        }
    }

    public abstract List<T> search(String keyword);

    public T get(ID id) {
        return repository.findOne(id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public T add(T entity) {
        return repository.save(entity);
    }

    public T update(ID id, T entity) {
        T oldEntity = get(id);
        copyValues(entity, oldEntity);
        return repository.save(oldEntity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void delete(ID id) {
        T entity = get(id);
        repository.delete(entity);
    }

    public abstract void copyValues(T fromEntity, T toEntity);

}
