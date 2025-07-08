package com.fongfox.ticketmanagementsystem.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * UserStatus
 *
 * Provides business logic for managing employment details.
 *
 * Version 1.0
 * Date: 7/8/2025
 *
 * Copyright
 *
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 7/8/2025      User      Create
 */
@AllArgsConstructor
@Getter
public enum UserStatus {
    BANNED(0), ACTIVE(1), DELETED(2);
    private final int value;
}
