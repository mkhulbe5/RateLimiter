package com.mohit.ratelimiter.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;

@Service
public class SubscriptionService {

	private final Map<String, Bucket> subscriptionCacheMap = new ConcurrentHashMap<>();

	public Bucket resolveBucket(String subscriptionKey) {
		return subscriptionCacheMap.computeIfAbsent(subscriptionKey, this::getSubscriptionBucket);
	}

	private Bucket getSubscriptionBucket(String subscriptionKey) {
		return buildBucket(resolveSubscriptionPlanByKey(subscriptionKey).getBandwidth());
	}

	private Bucket buildBucket(Bandwidth limit) {
		return Bucket.builder().addLimit(limit).build();
	}

	private SubscriptionPlan resolveSubscriptionPlanByKey(String subscriptionKey) {
		if (subscriptionKey.startsWith("PS1129-")) {
			return SubscriptionPlan.SUBSCRIPTION_PROFESSIONAL;
		} else if (subscriptionKey.startsWith("BS1129-")) {
			return SubscriptionPlan.SUBSCRIPTION_BASIC;
		}

		return SubscriptionPlan.SUBSCRIPTION_FREE;
	}
}
