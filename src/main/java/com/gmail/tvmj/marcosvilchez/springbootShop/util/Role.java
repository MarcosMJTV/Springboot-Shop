package com.gmail.tvmj.marcosvilchez.springbootShop.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum Role {

    CLIENT(Arrays.asList(Permission.USER_LEVER1)),
    INVENTORY(Arrays.asList(Permission.USER_LEVER1, Permission.USER_LEVEL2)),
    TASK_COORDINATOR(Arrays.asList(Permission.USER_LEVER1, Permission.USER_LEVEL3)),
    ADMIN(Arrays.asList(Permission.USER_LEVER1, Permission.USER_LEVEL2, Permission.USER_LEVEL3, Permission.USER_ADMIN));

    private List<Permission> permissions;
    Role(List<Permission> permissions){ this.permissions = permissions; }

}
