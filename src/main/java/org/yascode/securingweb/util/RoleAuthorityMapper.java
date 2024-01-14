package org.yascode.securingweb.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoleAuthorityMapper {

    private static final Map<String, List<String>> roleAuthorityMap;

    static {
        roleAuthorityMap = new HashMap<>();
        roleAuthorityMap.put("USER", List.of("READ_PRIVILEGE"));
        roleAuthorityMap.put("ADMIN", List.of("READ_PRIVILEGE", "WRITE_PRIVILEGE"));
    }

    public static List<String> getAuthority(String role) {
        return roleAuthorityMap.get(role);
    }

}
