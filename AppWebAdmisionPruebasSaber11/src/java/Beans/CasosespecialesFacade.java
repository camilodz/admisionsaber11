/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

import Modelo.Casosespeciales;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JORGE
 */
@Stateless
public class CasosespecialesFacade extends AbstractFacade<Casosespeciales> {

    @PersistenceContext(unitName = "ProyectoII_Saber11PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CasosespecialesFacade() {
        super(Casosespeciales.class);
    }
    
}