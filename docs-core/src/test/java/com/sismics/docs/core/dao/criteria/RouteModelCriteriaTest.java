package com.sismics.docs.core.dao.criteria;

import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

public class RouteModelCriteriaTest {
    @Test
    public void testDefault() {
        RouteModelCriteria criteria = new RouteModelCriteria();
        Assert.assertNull(criteria.getTargetIdList());
    }

    @Test
    public void testSetTargetIdList() {
        RouteModelCriteria criteria = new RouteModelCriteria();
        List<String> targetIds = Arrays.asList("user1", "user2");
        criteria.setTargetIdList(targetIds);
        Assert.assertEquals(targetIds, criteria.getTargetIdList());
    }

    @Test
    public void testFluentApi() {
        RouteModelCriteria criteria = new RouteModelCriteria().setTargetIdList(Arrays.asList("fluent-user"));
        Assert.assertEquals(1, criteria.getTargetIdList().size());
    }
}