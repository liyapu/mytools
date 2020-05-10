package com.lyp.learn.bean;

import java.util.HashMap;

/**
 * description:
 *
 * @author wxcsdb88
 * @since 2017/11/7 10:03
 */
public interface RequestField {
    // common fields
    String DEFAULT_PASSWD = "123456";

    // 主键之类的，一般为Long型
    String SYSTEM_ID = "systemId";

    String ID = "id";
    String VALID_FLAG = "validFlag";
    String USER = "user";
    String MENU = "menu";
    String MENU_ID = "menuId";
    String MENU_IDS = "menuIds";
    String BUTTON = "button";
    String BUTTON_ID = "buttonId";
    String BUTTON_IDS = "buttonIds";
    String DATA = "data";
    String DATA_ID = "dataId";
    String DATA_IDS = "dataIds";
    String ASSEST = "assest";
    String TYPE = "type";
    String RES_ID = "resId";
    String FLAG = "flag";

    String PUB_KEY = "pubkey";
    String PRI_KEY = "prikey";
    String USERNAME = "username";
    String USER_NAME = "userName";
    String ACCOUNT_NAME = "accountName";
    String ORGAN_ID = "organId";
    String ORGAN_ID_SHOW = "organIdShow";
    String ORGAN_CODE = "organCode";
    String ORGAN_NAME = "organName";
    String PROJECT_ID = "projectId";
    String PASSWD = "password";
    String OLD_PASSWD = "oldPassword";
    String CONFIRM_PASSWD = "confirmPassword";
    String NAME = "name";
    String MOBILE = "mobile";
    String PHONE = "phone";
    String PHONE_NUM = "phoneNum";
    String CERTIFI_TYPE = "certifiType";
    String CERTIFI_NUM = "certifiNum";

    String CATEGORY = "category";
    String USER_STATUS = "userStatus";
    String EMAIL = "email";
    String ADDRESS = "address";
    String ROLE = "role";
    String PRIVILEGE = "privilege";
    String OLD_STATUS = "oldStatus";
    String STATUS = "status";
    String NAME_EN = "nameEn";
    String STARTTIME = "startTime";
    String ENDTIME = "endTime";
    String FORMAT = "format";
    String FILENAME = "filename";
    String LINE = "line";
    String PATH = "path";
    String METHOD_NAME = "methodName";
    String USERID = "userId";
    String USER_ID_SHOW = "userIdShow";
    String ROLEID = "roleId";
    String SOURCE_TYPE = "createSourceType";
    String LINKMAN = "linkman";
    String LINKADDRESS = "linkaddress";
    String LINKPHONE = "linkphone";
    String LINKEMAIL = "linkemail";

    String SERVER_SIGN = "serverSign";

    //add by tiancq start
    String CODE = "code";
    //add by tiancq end

    // 查询条件类参数
    // 按照字段排序，以逗号分割，前面如果有-号，则表示逆序
    String SORT = "sort";

    String ORDER_BY = "";
    // 展示字段
    String FIELD = "field";

    // 分页参数
    String PAGE_NUM = "pageNum";
    String PAGE_SIZE = "pageSize";
    String PAGE_TOTAL = "total";

    // 权限验证相关请求参数
    String APPID = "appId";
    String TOKEN = "token";
    String TIMESTAMP = "timestamp";
    String SIGN = "sign";

    String UNITOKEN = "uniToken";

    // mxGraph and log view
    String XML = "xml";
    String HEIGHT = "h";
    String WIDTH = "w";
    String MIME = "mime";

    // 合约状态参数
    String Contract_Unknown = "Contract_Unknown";
    String Contract_Create = "Contract_Create";
    String Contract_Signature = "Contract_Signature";
    String Contract_In_Process = "Contract_In_Process";
    String Contract_Completed = "Contract_Completed";
    String Contract_Discarded = "Contract_Discarded";

    // 合约相关参数
    String XML_CONTRACT = "xmlContract";
    String JSON_CONTRACT = "jsonContract";
    String CREATE_FlAG = "createFlag";

    String CONTRACT_PUBLISHER = "publishOwner";
    String CONTRACT_AUDIT_OP = "auditOp";
    String CONTRACT_SUGGESTION = "suggestion";

    //合约应用 id
    String CONTRACT_APP_ID = "contractAppId";
    String CONTRACT_ID = "contractId";
    String CONTRACT_PRODUCT_ID = "contractProductId";
    String CONTRACT_TEMPLATE_ID = "contractTemplateId";
    String CONTRACT_OWNER = "contractOwner";
    String CONTRACT_STATE = "contractState";
    String CONTRACT_NAME = "contractName";

    // common fields
    // 主键之类的，一般为Long型
    HashMap<String, Boolean> COMMON_FIELDS_MAP = new HashMap<String, Boolean>() {
        private static final long serialVersionUID = -4683167907376040562L;

        {
            put(ID, true);
            put(VALID_FLAG, true);
            put(USER, true);
            put(ASSEST, true);
            put(MENU, true);
            put(MENU_ID, true);
            put(MENU_IDS, true);
            put(BUTTON, true);
            put(BUTTON_ID, true);
            put(BUTTON_IDS, true);
            put(DATA, true);
            put(DATA_ID, true);
            put(DATA_IDS, true);
            put(USERNAME, true);
            put(PASSWD, true);
            put(OLD_PASSWD, true);
            put(CONFIRM_PASSWD, true);
            put(NAME, true);
            put(MOBILE, true);
            put(PHONE, true);
            put(EMAIL, true);
            put(PRIVILEGE, true);
            put(ROLE, true);
            put(STATUS, true);
            put(NAME_EN, true);
            put(STARTTIME, true);
            put(ENDTIME, true);
            put(FORMAT, true);
            put(FILENAME, true);
            put(LINE, true);
            put(PATH, true);
            put(METHOD_NAME, true);
            put(TYPE, true);
            put(RES_ID, true);
            put(FLAG, true);
        }
    };

    HashMap<String, Boolean> QUERY_FIELDS_MAP = new HashMap<String, Boolean>() {
        private static final long serialVersionUID = 7775136902227806197L;

        {
            put(SORT, true);
            put(FIELD, true);
        }
    };
    // 分页参数
    HashMap<String, Boolean> PAGE_FIELDS_MAP = new HashMap<String, Boolean>() {
        private static final long serialVersionUID = 7775136902227806197L;

        {
            put(PAGE_NUM, true);
            put(PAGE_SIZE, true);
            put(PAGE_TOTAL, true);
        }
    };

    HashMap<String, Boolean> AUTH_FIELDS_MAP = new HashMap<String, Boolean>() {
        private static final long serialVersionUID = 7678601511286988624L;

        {
            put(APPID, true);
            put(TOKEN, true);
            put(TIMESTAMP, true);
            put(SIGN, true);
        }
    };

    HashMap<String, Boolean> OTHERS_FIELDS_MAP = new HashMap<String, Boolean>() {
        private static final long serialVersionUID = 6430092683526767280L;

        {
            put(XML, true);
            put(HEIGHT, true);
            put(WIDTH, true);
            put(MIME, true);
        }
    };

    HashMap<String, Boolean> CONTRACT_STATE_FIELDS_MAP = new HashMap<String, Boolean>() {

        private static final long serialVersionUID = 1326337925772304485L;

        {
            put(Contract_Unknown, true);
            put(Contract_Create, true);
            put(Contract_Signature, true);
            put(Contract_In_Process, true);
            put(Contract_Completed, true);
            put(Contract_Discarded, true);
        }
    };

    HashMap<String, Boolean> CONTRACT_FIELDS_MAP = new HashMap<String, Boolean>() {

        private static final long serialVersionUID = 6027192644764110097L;

        {
            put(PUB_KEY, true);
            put(PRI_KEY, true);
            put(XML_CONTRACT, true);
            put(JSON_CONTRACT, true);
            put(CREATE_FlAG, true);

            put(CONTRACT_PUBLISHER, true);
            put(CONTRACT_AUDIT_OP, true);
            put(CONTRACT_SUGGESTION, true);
            put(CONTRACT_APP_ID, true);
            put(CONTRACT_ID, true);
            put(CONTRACT_PRODUCT_ID, true);
            put(CONTRACT_TEMPLATE_ID, true);
            put(CONTRACT_OWNER, true);
            put(CONTRACT_STATE, true);
            put(CONTRACT_NAME, true);
        }
    };

    HashMap<String, Boolean> ALL_FIELDS_MAP = new HashMap<String, Boolean>() {

        private static final long serialVersionUID = -2881395865014355693L;

        {
            putAll(COMMON_FIELDS_MAP);
            putAll(QUERY_FIELDS_MAP);
            putAll(PAGE_FIELDS_MAP);
            putAll(AUTH_FIELDS_MAP);
            putAll(OTHERS_FIELDS_MAP);
            putAll(CONTRACT_STATE_FIELDS_MAP);
            putAll(CONTRACT_FIELDS_MAP);
        }
    };
}
