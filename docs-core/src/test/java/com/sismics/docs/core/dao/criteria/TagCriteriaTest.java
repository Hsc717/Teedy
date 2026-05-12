package com.sismics.docs.core.dao.criteria;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

/**
 * Test of TagCriteria class.
 * 
 * @author bgamard
 */
public class TagCriteriaTest {

    @Test
    public void testTagCriteriaDefault() {
        // 初始化Criteria
        TagCriteria criteria = new TagCriteria();
        
        // 验证默认值
        Assert.assertNull(criteria.getId());
        Assert.assertNull(criteria.getTargetIdList());
        Assert.assertNull(criteria.getDocumentId());
    }

    @Test
    public void testSetId() {
        TagCriteria criteria = new TagCriteria();
        
        criteria.setId("test_id");
        
        Assert.assertEquals("test_id", criteria.getId());
    }

    @Test
    public void testSetTargetIdList() {
        TagCriteria criteria = new TagCriteria();
        List<String> targetIdList = Arrays.asList("target1", "target2");
        
        criteria.setTargetIdList(targetIdList);
        Assert.assertEquals(targetIdList, criteria.getTargetIdList());
    }

    @Test
    public void testSetDocumentId() {
        TagCriteria criteria = new TagCriteria();
        
        criteria.setDocumentId("doc_123");
        Assert.assertEquals("doc_123", criteria.getDocumentId());
    }

    @Test
    public void testFluentApi() {
        TagCriteria criteria = new TagCriteria()
                .setId("fluent_id")
                .setTargetIdList(Arrays.asList("t1"))
                .setDocumentId("doc_fluent");
        
        Assert.assertEquals("fluent_id", criteria.getId());
        Assert.assertEquals(Arrays.asList("t1"), criteria.getTargetIdList());
        Assert.assertEquals("doc_fluent", criteria.getDocumentId());
    }
}