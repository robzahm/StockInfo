package com.stockinfo.health;

import com.codahale.metrics.health.HealthCheck;

public class CompanyHealthCheck extends HealthCheck {

    public CompanyHealthCheck() {
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}
