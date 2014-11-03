/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cat.urv.deim.sob.db;

import cat.urv.deim.sob.persistence.ConnectionPool;

/**
 *
 * @author javigd
 */
public class SOBPersistence {
    protected ConnectionPool pool;

    public SOBPersistence(ConnectionPool pool) {
        this.pool = pool;
    }
}
