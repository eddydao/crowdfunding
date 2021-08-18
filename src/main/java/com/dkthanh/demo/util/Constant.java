package com.dkthanh.demo.util;

public final class Constant {
    public static final Integer ID_STEP = 1;
//    public static final String
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
        APPROVED(2, "APPROVED"),// approve
        RUNNING(3, "RUNNING"),// process
        STOP(4, "STOP"),//denied
        COMPLETE(5, "COMPLETE"),//
        REJECT(6, "REJECT"),//denied
        EDITING(7, "EDITING"),
        READY(9, "READY_TO_REVIEW"),
        SUSPEND(10, "SUSPEND")
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
        REWARD(2, "reward"),
        STORY(3, "story"),
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
        public static final String KEYWORD = "KEYWORD";
        public static final String CATEGORY = "category";
        public static final String ITEM_ID = "itemId";
        public static final String OPTION_ID = "optionId";
        public static final String QUANTITY = "quantity";
        public static final String POPULAR = "popular";


        private PROJECT_KEY(){}
    }

    public enum Currency {
        USD(1, "USD");

        private Integer id;
        private String name;

        Currency(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static Currency getFormStepById(Integer id){
            for (Currency e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum CommentSection {
        BASIC(1, "BASIC"),
        REWARDS(2, "REWARDS"),
        COMMENT(3, "COMMENT");

        private Integer id;
        private String name;

        CommentSection(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static CommentSection getCommentSectionById(Integer id){
            for (CommentSection e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }
    public enum IS_CLOSED {
        OPEN(0, "OPEN"),
        CLOSE(1, "CLOSE");

        private Integer id;
        private String name;

        IS_CLOSED(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static IS_CLOSED getCommentSectionById(Integer id){
            for (IS_CLOSED e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

    public enum IS_TEMPORARY {
        TEMPORARY(1, "TEMPORARY"),
        NOT_TEMPORARY(0, "NOT_TEMPORARY");

        private Integer id;
        private String name;

        IS_TEMPORARY(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static IS_TEMPORARY getCommentSectionById(Integer id){
            for (IS_TEMPORARY e : values()) {
                if (e.getId().equals(id)) {
                    return e;
                }
            }
            return null;
        }
    }

}
