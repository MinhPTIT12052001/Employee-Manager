package com.globits.da.constants;

public class MessageConst {
    public static final class PROVINCE_MESSAGE_ERROR {
        public static final String NAME_IS_EXIST = "Province with this name is already exist!";
        public static final String NAME_NOT_EMPTY = "Province name is not empty!";
        public static final String ID_NOT_EXIST = "Province Id not Exist!";
    }
    public static final class EMPLOYEE_MESSAGE_ERROR {
        public static final String ID_NOT_EXIST = "Employee Id not exist!";
        public static final String CODE_INVALID = "Employee with code invalid.";
        public static final String CODE_IS_EXIST = "Employee with code is already exist!";
        public static final String EMAIL_INVALID = "Employee with email invalid.";
        public static final String NAME_NOT_EMPTY = "Employee name is not empty.";
        public static final String PHONE_INVALID = "Employee Phone invalid.";
        public static final String AGE_INVALID = "Employee Age invalid.";
    }
    public static final class DISTRICT_MESSAGE_ERROR {
        public static final String NAME_IS_EXIST = "District with this name is already exist!";
        public static final String NAME_NOT_EMPTY = "District name is not empty!";
        public static final String ID_NOT_EXIST = "District Id not Exist!";
    }
    public static final class TOWN_MESSAGE_ERROR {
        public static final String NAME_IS_EXIST = "Town with this name is already exist!";
        public static final String NAME_NOT_EMPTY = "Town name is not empty!";
        public static final String ID_NOT_EXIST = "Town Id not Exist!";
    }
    public static final class CERTIFICATE_MESSAGE_ERROR {
        public static final String NOT_FOUND_ID = "Certificate with this ID is not found!";
        public static final String NAME_NOT_EMPTY = "Name of Certificate must not be empty!";
        public static final String BEGIN_DATE_NOT_EMPTY = "Begin date of Certificate must not be empty!";
        public static final String EXPIRE_DATE_NOT_EMPTY = "Expire date of Certificate must not be empty!";

    }
}
