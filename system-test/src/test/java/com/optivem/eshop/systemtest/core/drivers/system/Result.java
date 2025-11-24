package com.optivem.eshop.systemtest.core.drivers.system;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

public class Result<T> {
    private final boolean success;
    private final T value;
    private final String error;

    private Result(boolean success, T value, String error) {
        this.success = success;
        this.value = value;
        this.error = error;
    }

    public static <T> Result<T> success(T value) {
        return new Result<>(true, value, null);
    }

    public static <T> Result<T> failure(String error) {
        return new Result<>(false, null, error);
    }

    public static Result<Void> success() {
        return new Result<>(true, null, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isFailure() {
        return !success;
    }

    public T getValue() {
        if (!success) {
            throw new IllegalStateException("Cannot get value from a failed result. Error: " + error);
        }
        return value;
    }

    public Optional<T> getValueOptional() {
        return success ? Optional.ofNullable(value) : Optional.empty();
    }

    public String getError() {
        if (success) {
            throw new IllegalStateException("Cannot get error from a successful result");
        }
        return error;
    }

    public Optional<String> getErrorOptional() {
        return !success ? Optional.ofNullable(error) : Optional.empty();
    }

    public T getValueOrDefault(T defaultValue) {
        return success ? value : defaultValue;
    }

    public <U> Result<U> map(Function<T, U> mapper) {
        if (!success) {
            return Result.failure(error);
        }
        try {
            return Result.success(mapper.apply(value));
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    public <U> Result<U> flatMap(Function<T, Result<U>> mapper) {
        if (!success) {
            return Result.failure(error);
        }
        try {
            return mapper.apply(value);
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    public Result<T> onSuccess(Consumer<T> action) {
        if (success) {
            action.accept(value);
        }
        return this;
    }

    public Result<T> onFailure(Consumer<String> action) {
        if (!success) {
            action.accept(error);
        }
        return this;
    }

    @Override
    public String toString() {
        if (success) {
            return "Success(" + value + ")";
        } else {
            return "Failure(" + error + ")";
        }
    }
}
