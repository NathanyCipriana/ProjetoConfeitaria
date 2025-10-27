
package com.uc15.ProjetoConfeitaria.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="pedidos")
public class Pedido {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; 
    private String produto; 
    private Integer quantidade; 
    private BigDecimal valorUn; 
    
    @Column(name = "data_entrega")
    private LocalDate dataEntrega;
    private String observacoes;
    
    @ManyToOne
    @JoinColumn(name = "idcliente")
    private Cliente cliente;
    
    
}


