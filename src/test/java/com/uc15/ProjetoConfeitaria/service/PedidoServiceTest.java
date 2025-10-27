
package com.uc15.ProjetoConfeitaria.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class PedidoServiceTest {
    
    PedidoService serviceTest;
    public PedidoServiceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        serviceTest= new PedidoService();
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testarQuantPedido(){
        int quantEsperada = 3;
        var quantPedido= serviceTest.listarTodosPedidos().size();
         assertEquals(quantEsperada,quantPedido);
               
    }
}
