package com.pieces.service.utils;

import com.pieces.service.tag.AuthenticatedTag;
import com.pieces.service.tag.GuestTag;
import com.pieces.service.tag.HasAnyRolesTag;
import com.pieces.service.tag.HasPermissionTag;
import com.pieces.service.tag.HasRoleTag;
import com.pieces.service.tag.LacksPermissionTag;
import com.pieces.service.tag.LacksRoleTag;
import com.pieces.service.tag.NotAuthenticatedTag;
import com.pieces.service.tag.PrincipalTag;
import com.pieces.service.tag.UserTag;

import freemarker.template.SimpleHash;

public class ShiroTags extends SimpleHash {
    public ShiroTags() {
        put("authenticated", new AuthenticatedTag());
        put("guest", new GuestTag());
        put("hasAnyRoles", new HasAnyRolesTag());
        put("hasPermission", new HasPermissionTag());
        put("hasRole", new HasRoleTag());
        put("lacksPermission", new LacksPermissionTag());
        put("lacksRole", new LacksRoleTag());
        put("notAuthenticated", new NotAuthenticatedTag());
        put("principal", new PrincipalTag());
        put("user", new UserTag());
    }
}
