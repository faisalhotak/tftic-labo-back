package be.portal.job.utils;

public class Constants {

    // AUTH
    public static final String AUTH_ENDPOINT = "/v1/auth/";
    public static final String AUTH_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";

    // ROLES
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String MANAGER_ROLE = "MANAGER";
    public static final String SUPERVISOR_ROLE = "SUPERVISOR";
    public static final String ADVERTISER_ROLE = "ADVERTISER";
    public static final String SEEKER_ROLE = "SEEKER";

    // PAGINATION
    public static final int PAGE_SIZE = 10;
}
