package com.uc15.ProjetoConfeitaria.repository;

import com.uc15.ProjetoConfeitaria.model.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
        

}
