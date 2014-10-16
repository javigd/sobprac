/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author javigd
 */
public class SOBDAO {
    protected EntityManagerFactory factory;

    public SOBDAO(EntityManagerFactory emf) {
        this.factory = emf;
    }
}
