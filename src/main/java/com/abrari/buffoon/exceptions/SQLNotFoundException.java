package com.abrari.buffoon.exceptions;

import lombok.NoArgsConstructor;

import java.sql.SQLException;

@NoArgsConstructor
public class SQLNotFoundException extends SQLException {

    public SQLNotFoundException(String reason) {
        super(reason);
    }
}
