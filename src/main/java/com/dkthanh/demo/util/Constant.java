package com.dkthanh.demo.util;

public final class Constant {
    public static final Integer ID_STEP = 1;
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

    public enum ProjectStatus{
        WAITING(1, "WAITING"),
        APPROVED(2, "APPROVED"),
        RUNNING(3, "RUNNING"),
        PAUSE(4, "PAUSE"),
        COMPLETE(5, "COMPLETE"),
        REJECT(6, "REJECT"),
        EDITING(7, "EDITING")
        ;

        private Integer id;
        private String name;

        ProjectStatus(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static ProjectStatus getStatusById(Integer id){
            for (ProjectStatus e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum PaypalPaymentIntent{
        SALE(1, "sale"),
        AUTHORIZE(2, "authorize"),
        ORDER(3, "order");

        private Integer id;
        private String name;

        PaypalPaymentIntent(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static PaypalPaymentIntent getStatusById(Integer id){
            for (PaypalPaymentIntent e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum PaymentMethod{
        CREDIT_CARD(1, "credit_card"),
        PAYPAL(2, "paypal"),
        COD(3, "cod");

        private Integer id;
        private String name;

        PaymentMethod(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static PaymentMethod getPaymentMethodById(Integer id){
            for (PaymentMethod e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum MaterialType{
        THUMBNAIL(1, "thumbnail"),
        IMAGE(2, "image"),
        VIDEO(3, "video"),
        TEXT(4, "text"),
        ;

        private Integer id;
        private String name;

        MaterialType(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static MaterialType getMaterialTypeById(Integer id){
            for (MaterialType e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum ProjectFormStep{
        BASIC(1, "basic"),
        FUNDING(2, "funding"),
        REWARD(3, "reward"),
        PAYMENT(4, "payment"),
        ;

        private Integer id;
        private String name;

        ProjectFormStep(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static ProjectFormStep getFormStepById(Integer id){
            for (ProjectFormStep e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

    public static final class PROJECT_KEY{
        public static final String PROJECT_ID = "projectId";
        public static final String IS_RECOMMENDED = "isRecommended";
        public static final String PROJECT_STATUS = "projectStatus";
        public static final String USER_ID = "userId";


        private PROJECT_KEY(){}
    }


}
