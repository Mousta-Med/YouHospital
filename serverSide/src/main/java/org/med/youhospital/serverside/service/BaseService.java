package org.med.youhospital.serverside.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BaseService<C, T, R> {
    R save(C c);

    List<R> findAll();

    R findOne(T id);

    R update(T id, C c);

    void delete(T id);


    Page<R> findWithPagination(Pageable pageable);
}
