package com.abrari.buffoon.utils;

public interface BiDirectionalMapping<A, B> {

    A mapToLeft(B b);
    B mapToRight(A a);
}
