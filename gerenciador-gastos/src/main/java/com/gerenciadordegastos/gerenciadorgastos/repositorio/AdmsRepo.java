package com.gerenciadordegastos.gerenciadorgastos.repositorio;

import com.gerenciadordegastos.gerenciadorgastos.models.Adm;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface AdmsRepo extends CrudRepository <Adm, Integer> {
    @Query(value = "select * from administradores where email=:email and senha=:senha", nativeQuery = true)
    public Adm login(String email, String senha);

}
