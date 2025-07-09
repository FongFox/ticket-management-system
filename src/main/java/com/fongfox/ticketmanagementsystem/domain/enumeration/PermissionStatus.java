package com.fongfox.ticketmanagementsystem.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * PermissionStatus
 * <p>
 * Provides business logic for managing employment details.
 * <p>
 * Version 1.0
 * Date: 7/9/2025
 * <p>
 * Copyright
 * <p>
 * Modification Logs:
 * DATE         AUTHOR       DESCRIPTION
 * -------------------------------------
 * 7/9/2025      User      Create
 */
@AllArgsConstructor
@Getter
public enum PermissionStatus {
    DISABLED(0), ACTIVE(1), DELETED(2);
    private final int value;
}
