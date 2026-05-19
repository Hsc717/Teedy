package com.sismics.docs.core.dao.criteria;

import com.sismics.docs.core.constant.WebhookEvent;
import org.junit.Assert;
import org.junit.Test;

public class WebhookCriteriaTest {
    @Test
    public void testDefault() {
        WebhookCriteria criteria = new WebhookCriteria();
        Assert.assertNull(criteria.getEvent());
    }

    @Test
    public void testSetEvent() {
        WebhookCriteria criteria = new WebhookCriteria();
        criteria.setEvent(WebhookEvent.DOCUMENT_CREATED);
        Assert.assertEquals(WebhookEvent.DOCUMENT_CREATED, criteria.getEvent());
    }

    @Test
    public void testFluentApi() {
        WebhookCriteria criteria = new WebhookCriteria().setEvent(WebhookEvent.DOCUMENT_UPDATED);
        Assert.assertEquals(WebhookEvent.DOCUMENT_UPDATED, criteria.getEvent());
    }
}