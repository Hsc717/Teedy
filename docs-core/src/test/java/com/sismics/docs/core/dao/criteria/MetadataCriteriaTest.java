package com.sismics.docs.core.dao.criteria;

import org.junit.Assert;
import org.junit.Test;

public class MetadataCriteriaTest {
    @Test
    public void testConstructor() {
        MetadataCriteria criteria = new MetadataCriteria();
        Assert.assertNotNull(criteria);
    }
}