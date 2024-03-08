package app.demo.utils;

/**
 * Constants
 **/
public class Constants {

    private Constants() {
    }
    // ******* WEB CONSTANTS *******

    //Textual constants - Planning and deployment
    public static final String PACKET_DESCRIPTION = " devices are planned to set up in forest";
    public static final String PACKET_BG = "Border Gateway ";
    public static final String PACKET_MG = "Mesh Gateway ";
    public static final String PACKET_SENSOR = "SensorNode ";

    //TestNG test group names
    public static final String ALWAYS_RUN = "always";
    public static final String DASHBOARD_TEST = "dashboard_test";
    public static final String DEMO_TEST = "demo_test";
    public static final String SITES_TEST = "sites_test";
    public static final String SITE_INFO_TEST = "site_info_test";

    //Property file readers
    public static final String BASE_URI = "BASE_URI";
    public static final String BROWSER = "Browser";
    public static final String HEADLESS = "Headless";
    public static final String USERNAME = "UserName";
    public static final String PASSWORD = "Password";

    //Browser types
    public static final String EDGE = "EDGE";
    public static final String CHROME = "CHROME";
    public static final String FIREFOX = "FIREFOX";

    // ******* API CONSTANTS *******

    //TestNG test group names
    public static final String API_REG = "api-reg";
    public static final String ML_FT_TEST = "ml-ft-test";
    public static final String ML_FUOTA_TEST = "ml-fuota-test";
    public static final String SN_FT_TEST = "sn-ft-test";
    public static final String SN_FUOTA_TEST = "sn-fuota-test";

    //Request Header Keys
    public static final String AUTH = "Authorization";
    public static final String CONTENT_TYPE = "Content-Type";
    public static final String X_REQUESTED_WITH = "X-Requested-With";
    public static final String CSRF_TOKEN = "Csrf-Token";

    //Location paths
    public static final String PROPERTY_FILE_LOCATION = "/src/test/resources/config.properties";
    public static final String SCHEMA_FILE_LOCATION = "./src/main/resources/jsonSchemaFiles/";
    public static final String TEST_DATA_LOCATION = "./src/test/resources/test-data/";
    public static final String TEST_DATA_FUOTA_HEADERS_LOCATION = "fuota/headers/";
    public static final String TEST_DATA_FUOTA_PAYLOAD_LOCATION = "fuota/payload/";
    public static final String TEST_DATA_ALERT_PAYLOAD_LOCATION = "alert/payload/";

    //Other constants
    public static final String AUTH_TOKEN = "AUTHORIZATION_TOKEN";
    public static final String BEARER = "Bearer ";

    //Endpoints
    public static final String SITES_ENDPOINT = "/api/demo/";
    public static final String SENSOR_NODE_LAST_ENDPOINT = "/api/demo/";
    public static final String SIMULATE_SENSOR_NODE_MESSAGE_ENDPOINT = "/api/demo/";
    public static final String SIMULATE_HEX_ENDPOINT = "/api/demo/";
    public static final String ML_FUOTA_ENDPOINT = "/api/demo/";
    public static final String SN_FUOTA_ENDPOINT = "/api/demo/";
    public static final String ML_FILE_TRANSFER_ENDPOINT = "/api/demo/";
    public static final String SN_FILE_TRANSFER_ENDPOINT = "/api/demo/";
    public static final String FUOTA_SERVICE_DISABLE_JOB_CHECK = "/api/demo/";
    public static final String FUOTA_STATUS_ENDPOINT = "/api/demo/";

    //Time in milliseconds
    public static final int THREE_SECONDS = 3000;
    public static final int ONE_MINUTE = 60000;

    //FUOTA types
    public static final String FUOTA_TYPE_ML = "ml";
    public static final String FUOTA_TYPE_SN = "sn";

    //Job types
    public static final String JOB_TYPE_FUOTA = "fuota";
    public static final String JOB_TYPE_FILE_TRANSFER = "fileTransfer";

    //Stage names for FUOTA
    public static final String DISABLE_PSM_STAGE = "Disable PSM";
    public static final String ENABLE_UL_FILTER_STAGE = "Enable Ul Filter And Set MG Time";
    public static final String START_FUOTA_STAGE = "Start Fuota";
    public static final String SEND_FRAGS_STAGE = "Send Frags";
    public static final String DISABLE_UL_FILTER_STAGE = "Disable Ul Filter";
    public static final String ENABLE_PSM_STAGE = "Enable PSM";

    //Stage names for File Transfer
    public static final String BG_FILE_DOWNLOAD_STAGE = "BG File Download";

    //FUOTA Job response messages
    public static final String JOB_SUBMITTED = "SUBMITTED";
    public static final String JOB_ALREADY_RUNNING_ERROR = "Job already Running for the Site !!..";
    public static final String GTW_HEADER_INVALID_ERROR = "Expected header gtw is not valid !!.";
    public static final String GTW_TTI_HEADERS_INVALID_ERROR = "Expected headers (tti or gtw) are not valid !!.";
    public static final String EU_ID_BLANK_ERROR = "[EU ID must not be blank]";
    public static final String GATEWAY_EUI_BLANK_ERROR = "[Gateway Eui must not be blank]";
    public static final String MG_ID_ERROR = "[MG ID must be greater than or equal to 1]";
    public static final String SENSOR_IDS_EMPTY_ERROR = "[Sensor ID List must not be empty]";
    public static final String FT_JOB_ID_BLANK_ERROR = "[File Transfer job id must not be blank]";
    public static final String FT_JOB_NOT_FOUND_ERROR = "File Transfer job not found.";
    public static final String DATA_FREQUENCY_ERROR = "[Uplink rate must be greater than or equal to 10]";
    public static final String FRAG_DELAY_ERROR = "[Frag Delay must be greater than or equal to 1000]";
    public static final String FILE_NOT_FOUND_ERROR = "File not found.";
    public static final String FILE_NAME_ERROR = "[File Name must not be blank]";
    public static final String FILE_ID_MIN_ERROR = "[File Id must be greater than or equal to 150]";
    public static final String FILE_ID_MAX_ERROR = "[File Id must be less than or equal to 199]";
    public static final String VERSION_BLANK_ERROR = "[Version must not be blank]";
    public static final String SITE_ID_BLANK_ERROR = "[Site id must not be blank]";
    public static final String GATEWAY_NOT_FOUND_ERROR = "Gateway not found, EUI is not valid.";
    public static final String SITE_NOT_FOUND_ERROR = "Site not found, Site ID is not valid.";

    //FUOTA Job statuses
    public static final String JOB_COMPLETED = "Completed";
    public static final String JOB_IN_PROGRESS = "In-Progress";

    //File Transfer and FUOTA payload keys
    public static final String GATEWAY_EUI_KEY = "gatewayEui";
    public static final String FILE_ID_KEY = "fileId";
    public static final String SITE_ID_KEY = "siteId";
    public static final String MG_ID = "mg";
    public static final String FILE_NAME = "fileName";
    public static final String VERSION = "version";
    public static final String FILE_TRANSFER_JOB_ID = "fileTransferJobId";
    public static final String DATA_FREQUENCY = "dataFreq";
    public static final String FRAG_DELAY = "fragDelay";

    //Header file names
    public static final String GTW_PROD_HEADER = "GtwProdHeader.json";
    public static final String TTI_PILOT_HEADER = "TtiPilotHeader.json";
    public static final String GTW_PROD_AND_TTI_PILOT_HEADER = "GtwProdAndTtiPilotHeader.json";

    //File Transfer Payload file names
    public static final String FT_ML_SUCCESS_PAYLOAD = "FtMlSuccessful.json";
    public static final String FT_SN_SUCCESS_PAYLOAD = "FtSnSuccessful.json";
    public static final String FT_NO_GATEWAY_EUI = "FtNoGatewayEui.json";
    public static final String FT_NO_FILE_NAME = "FtNoFileName.json";
    public static final String FT_NO_VERSION = "FtNoVersion.json";
    public static final String FT_NO_FILE_ID = "FtNoFileId.json";
    public static final String FT_NO_SITE_ID = "FtNoSiteId.json";
    public static final String FT_FILE_NOT_FOUND = "FtFileNotFound.json";
    public static final String FT_GATEWAY_NOT_FOUND = "FtGatewayNotFound.json";
    public static final String FT_SITE_NOT_FOUND = "FtSiteNotFound.json";
    public static final String FT_FILE_ID_BELOW_MIN = "FtFileIdBelowMin.json";
    public static final String FT_FILE_ID_ABOVE_MAX = "FtFileIdAboveMax.json";

    //Fuota Payload file names
    public static final String FUOTA_ML_SUCCESS_PAYLOAD = "FuotaMlSuccessful.json";
    public static final String FUOTA_SN_SUCCESS_PAYLOAD = "FuotaSnSuccessful.json";
    public static final String FUOTA_NO_GATEWAY_EUI = "FuotaNoGatewayEui.json";
    public static final String FUOTA_NO_FT_JOB_ID = "FuotaNoFtJobId.json";
    public static final String FUOTA_NO_SENSORS = "FuotaNoSensors.json";
    public static final String FUOTA_NO_SITE_ID = "FuotaNoSiteId.json";
    public static final String FUOTA_NO_MG_ID = "FuotaNoMgId.json";
    public static final String FUOTA_DATA_FREQ_BELOW_MIN = "FuotaDataFreqMin.json";
    public static final String FUOTA_FRAG_DELAY_BELOW_MIN = "FuotaFragDelayMin.json";
    public static final String FUOTA_GATEWAY_NOT_FOUND = "FuotaGatewayNotFound.json";
    public static final String FUOTA_FT_JOB_NOT_FOUND = "FuotaFtJobNotFound.json";
    public static final String FUOTA_SITE_NOT_FOUND = "FuotaSiteNotFound.json";
}
