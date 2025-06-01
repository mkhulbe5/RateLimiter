package com.mohit.ratelimiter.services;

import java.time.Duration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Refill;

public enum SubscriptionPlan {
	SUBSCRIPTION_FREE(2), SUBSCRIPTION_BASIC(10), SUBSCRIPTION_PROFESSIONAL(20);

	private final int bucketLimit;

	SubscriptionPlan(int bucketLimit) {
		this.bucketLimit = bucketLimit;
	}

	public int getBucketLimit() {
		return this.bucketLimit;
	}

	public Bandwidth getBandwidth() {
		return Bandwidth.classic(bucketLimit, Refill.intervally(bucketLimit, Duration.ofMinutes(2)));
	}

}
