package com.sismics.docs.core.dao.criteria;

import org.junit.Assert;
import org.junit.Test;

public class RouteCriteriaTest {
    @Test
    public void testDefault() {
        RouteCriteria criteria = new RouteCriteria();
        Assert.assertNull(criteria.getDocumentId());
    }

    @Test
    public void testSetDocumentId() {
        RouteCriteria criteria = new RouteCriteria();
        criteria.setDocumentId("test-doc-id");
        Assert.assertEquals("test-doc-id", criteria.getDocumentId());
    }

    @Test
    public void testFluentApi() {
        RouteCriteria criteria = new RouteCriteria().setDocumentId("fluent-doc-id");
        Assert.assertEquals("fluent-doc-id", criteria.getDocumentId());
    }
}