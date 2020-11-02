package com.dkthanh.demo.util;

public final class Constant {
//    public static final class ROLES{
//        public static final String INVESTOR     = "investor";
//        public static final String FOUNDER      = "founder";
//        public static final String ADMIN        = "admin";
//        private ROLES(){}
//    }

    public enum Roles {
        ADMIN(1, "ROLE_ADMIN"),
        INVESTOR(2, "ROLE_INVESTOR"),
        CREATOR(3, "ROLE_CREATOR"),
        ANONYMOUS(4, "ROLE_ANONYMOUS");

        private Integer id;
        private String name;

        Roles(Integer id, String name){
            this.id = id;
            this.name = name;
        }

        public Integer getId(){
            return id;
        }

        public String getName(){
            return name;
        }

        public static Roles getById(Integer id) {
            for (Roles e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

}
