package com.api.framework.security;

import java.util.Objects;

public abstract class BearerContextHolder {
    private BearerContextHolder() {

    }

    private static final ThreadLocal<BearerContext> contextHolder = new ThreadLocal<>();

    public static void clearContext() { contextHolder.remove(); }

    public static BearerContext getContext() {
        BearerContext ctx = contextHolder.get();
        if (Objects.nonNull(ctx)) {
            return ctx;
        }
        ctx = createEmptyContext();
        contextHolder.set(ctx);
        return ctx;
    }

    private static void setContextHolder(BearerContext context) {
        if (Objects.nonNull(context)) {
            contextHolder.set(context);
        }
    }

    private static BearerContext createEmptyContext() {
        return new BearerContext();
    }
}
