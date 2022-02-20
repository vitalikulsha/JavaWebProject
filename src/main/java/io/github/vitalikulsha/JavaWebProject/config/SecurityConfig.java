package io.github.vitalikulsha.JavaWebProject.config;

import io.github.vitalikulsha.JavaWebProject.domain.Role;

import java.util.*;

public class SecurityConfig {
    private final static Map<Role, List<String>> mapConfig = new HashMap<>();

    static {
        initReader();
        initLibrarian();
        initAdmin();
    }

    private static void initReader() {
        List<String> urlPatternsReader = new ArrayList<>();
        urlPatternsReader.add("/book-catalog");
        urlPatternsReader.add("/book-search");
        urlPatternsReader.add("/order");
        mapConfig.put(Role.READER, urlPatternsReader);
    }

    private static void initLibrarian() {
        List<String> urlPatternsLibrarian = new ArrayList<>();
        mapConfig.put(Role.LIBRARIAN, urlPatternsLibrarian);
    }

    private static void initAdmin() {
        List<String> urlPatternsAdmin = new ArrayList<>();
        mapConfig.put(Role.ADMIN, urlPatternsAdmin);
    }

    public static Set<Role> getAllRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }

}
