package org.featherlessbipeds.ashpath._02;

import jakarta.persistence.CacheRetrieveMode;
import jakarta.persistence.TypedQuery;
import java.util.HashMap;
import java.util.Map;
import org.featherlessbipeds.ashpath.utils.TestHelper;
import org.featherlessbipeds.ashpath.entity.DeathRegistrar;
import static org.junit.Assert.*;
import org.junit.Test;

import static org.featherlessbipeds.ashpath.utils.IdsUtil.*;

public class DeathRegistrarTest extends TestHelper
{
    @Test
    public void updateDeathRegistrar()
    {
        var newEmail = "newemail@gmail.com";
        var newFullName = "Never Say Ever";
        
        Long id = DR_ID_5;
        DeathRegistrar dr = em.find(DeathRegistrar.class, id);
        dr.setEmail(newEmail);
        dr.setFullName(newFullName);
        
        em.flush();
        
        String jpql = "SELECT a FROM DeathRegistrar a WHERE a.id = ?1";
        TypedQuery<DeathRegistrar> query = em.createQuery(jpql, DeathRegistrar.class);
        query.setHint("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        query.setParameter(1, id);
        dr = query.getSingleResult();
        
        assertEquals(dr.getEmail(), newEmail);
        assertEquals(dr.getFullName(), newFullName);
    }

    @Test
    @SuppressWarnings("UnusedAssignment")
    public void updateDeathRegistrarMerge()
    {
        var newEmail = "somemail@gmail.com";
        var newFullName = "Help Me";
        
        Long id = DR_ID_5;
        DeathRegistrar dr = em.find(DeathRegistrar.class, id);
        dr.setEmail(newEmail);
        dr.setFullName(newFullName);
        
        em.clear();
        dr = (DeathRegistrar) em.merge(dr);
        
        Map<String, Object> properties = new HashMap<>();
        properties.put("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        dr = em.find(DeathRegistrar.class, id, properties);
        
        assertEquals(dr.getEmail(), newEmail);
        assertEquals(dr.getFullName(), newFullName);
    }

    @Test
    public void removeDeathRegistrar()
    {
        Long id = DR_ID_6;
        DeathRegistrar dr = em.find(DeathRegistrar.class, id);
        em.remove(dr);
        dr = em.find(DeathRegistrar.class, id);
        assertNull(dr);
    }
}
