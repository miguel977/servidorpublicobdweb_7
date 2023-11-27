package com.abctreinamentos.servidorpublicobdweb.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.abctreinamentos.servidorpublicobdweb.entity.ServidorPublico;

@Repository
public interface ServidorPublicoRepository extends CrudRepository<ServidorPublico,Long> {

}
