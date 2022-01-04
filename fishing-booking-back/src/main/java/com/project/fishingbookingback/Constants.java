package com.project.fishingbookingback;

public class Constants {

    public static final String AUTH_URL = "/api/auth/";
    public static final long EXPIRATION_TIME = 1000 * 60 * 60 * 10;
    public static final byte[] SECRET = javax.xml.bind.DatatypeConverter.parseHexBinary("c6233207fe4b048d40cb94dee1dde87284460d409ff0d65b754e69873978f593");
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
